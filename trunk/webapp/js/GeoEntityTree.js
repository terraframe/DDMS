/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
MDSS.GeoEntityTree = (function(){

  // key/value is node id/GeoEntity id
  var _nodeToGeoEntityMap = {};

  // key/value is entity id/[node ids]
  var _geoEntityIdToNodeIdMap = {};

  // key/value is GeoEntity id/GeoEntity
  var _geoEntityCache = {};

  // The selected node in tree
  var _selectedNode = null;
  
  // The tree for GeoEntities
  var _geoTree = null;
  
  // The menu for CRUD operations
  var _menu = null;
  
  // reference to modal for node create/edit
  var _modal = null;
  
  // callback function for selecting a node in the tree
  var _selectCallback = null;
  
  // the geo entity class name to filter by
  var _filterType = '';
  
  /**
   * Removes everything from the current Tree
   */
  function _destroyAll()
  {
  	_nodeToGeoEntityMap = {};
  	_geoEntityCache = {};
  	_selectedNode = null;
  	_modal = null;
  	_selectCallback = null;
  	
  	// this.cfg of the ContextMenu is null which throws an error.
  	// TODO find official fix for this
    try
    {
  	  _menu.destroy();
    }
    catch(e)
    {
      _menu = null;
    }
  	
  	_geoTree.destroy();
  	_geoTree = null;
  }
  
  /**
   * Sets the mapping between a node and GeoEntity.
   * More than one node may point to a GeoEntity.
   */
  function _setMapping(node, geoEntity)
  {
    var nodeId = node.contentElId;
    var geId = geoEntity.getId();
    
    // overwrite any existing entries
    _nodeToGeoEntityMap[nodeId] = geId;
    _geoEntityCache[geId] = geoEntity;
    
    // map the entity id to the node id
    var nodeIds = _geoEntityIdToNodeIdMap[geId];
    if(Mojo.util.isArray(nodeIds))
    {
      var match = false;
      for(var i=0; i<nodeIds.length; i++)
      {
        if(nodeIds[i] === nodeId)
        {
          match = true;
          break;
        }
      }
      
      if(!match)
      {
        nodeIds.push(nodeId);
      }
    }
    else
    {
      nodeIds = [];
      nodeIds.push(nodeId);
      _geoEntityIdToNodeIdMap[geId] = nodeIds;
    }
  }
  
  /**
   * Gets the GeoEntity that maps to the given
   * node.
   */
  function _getGeoEntity(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _nodeToGeoEntityMap[nodeId];
    return _geoEntityCache[geId];
  }
  
  /**
   * Removes the node to GeoEntity mapping.
   */
  function _removeMapping(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _getGeoEntity(nodeId).getId();
    
    delete _nodeToGeoEntityMap[nodeId];
    
    var nodeIds = _geoEntityIdToNodeIdMap[geId];
    for(var i=0; i<nodeIds.length; i++)
    {
      if(nodeIds[i] === nodeId)
      {
      	nodeIds.splice(i, 1);
        break;
      }
    }
    
    if(nodeIds.length === 0)
    {
      // no more nodes pointing to the GeoEntity
      delete _geoEntityCache[geId];
    }
  }
  
  function _setGeoEntityAttributes(params, geoEntity)
  {
    var entityName = params['dto.entityName'];
    var gazId = params['dto.gazId'];
    var geoId = params['dto.geoId'];
    var activatedVal = params['dto.activated'];
    var activated = (activatedVal === "true") ? true : false;
    
    var geoSetter = null;
    var geoValue = '';
    if('setPoint' in geoEntity)
    {
      geoSetter = 'setPoint';
      geoValue = params['dto.point'];
    }
    else if('setLineString' in geoEntity)
    {
      geoSetter = 'setLineString';
      geoValue = params['dto.lineString'];
    }
    else if('setPolygon' in geoEntity)
    {
      geoSetter = 'setPolygon';
      geoValue = params['dto.polygon'];
    }
    else if('setMultiPoint' in geoEntity)
    {
      geoSetter = 'setMultiPoint';
      geoValue = params['dto.multiPoint'];
    }
    else if('setMultiLineString' in geoEntity)
    {
      geoSetter = 'setMultiLineString';
      geoValue = params['dto.multiLineString'];
    }
    else if('setMultiPolygon' in geoEntity)
    {
      geoSetter = 'setMultiPolygon';
      geoValue = params['dto.multiPolygon'];
    }
    
    geoEntity.setEntityName(entityName);
    geoEntity.setGazId(gazId);
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);
    
    // Earth doesn't have a geoSetter
    if(geoSetter != null)
    {
      geoEntity[geoSetter](geoValue);
    }
  }
  
  /**
   * Creates a new node.
   */
  function _createNode(type, params, action)
  {
    var geConstructor = Mojo.util.getType(type);
    var geoEntity = new geConstructor();

   _setGeoEntityAttributes(params, geoEntity);

    var request = new MDSS.Request({
      onSuccess : function(ids, geoEntity){
        
        // add the node directly if the children have already been dynamically loaded
        if(_selectedNode.dynamicLoadComplete)
        {
          var div = _createNodeDiv(geoEntity);
          
          var node = new YAHOO.widget.HTMLNode(div, _selectedNode);
          
          _selectedNode.expanded = false; // force a re-expansions
          _selectedNode.refresh();
          
          // must reset mapping with new id
          _setMapping(node, geoEntity);
        }

        _selectedNode.expand();
        
        _modal.destroy();
      }
    });
    
    var parentGeoEntity = _getGeoEntity(_selectedNode);
    geoEntity.applyWithParent(request, parentGeoEntity.getId(), false);
  }
  
  /**
   * Updates a node.
   */
  function _updateNode(params, actions)
  {
    var geoEntity = _getGeoEntity(_selectedNode);

    _setGeoEntityAttributes(params, geoEntity);
    
    var request = new MDSS.Request({
      onSuccess: function(ids, geoEntity){
      	
      	// replace the contents (active status will be modified in
      	// another operation).
      	var div = _selectedNode.getContentEl().innerHTML;
      	var span = _createContentSpan(geoEntity);
      	div = div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/, '$1'+span+'$2');
      	
      	// update selected node and all copies
        //_selectedNode.setHtml(div);
        var nodeIds = _geoEntityIdToNodeIdMap[geoEntity.getId()];
        for(var i=0; i<nodeIds.length; i++)
        {
          var id = nodeIds[i];
          var el = document.getElementById(id);
          el.innerHTML = div;
        }
        
        //_setMapping(_selectedNode, geoEntity); Is this needed?
        
        _updateActivatedOnNodes(ids, geoEntity.getActivated());
        
        _modal.destroy();
      }
    });
    
    geoEntity.updateFromTree(request);
  }
  
  /**
   * Event handler for when a GeoEntity type is selected
   * to create a new instance of that type.
   */
  function _createTypeSelected(e, obj)
  {
  	var type = obj.type;
    var request = new MDSS.Request({
      label : obj.label,
      onSuccess : function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        var labelEl = "<h3>"+this.label+"</h3><hr />";
        html = labelEl + html;      
        _modal.setBody(html);
        
        eval(executable);
      }
    });

    var controller = Mojo.util.getType(type+"Controller");
    controller.setCreateListener(MDSS.util.curry(_createNode, type));
    controller.newInstance(request);    	
  }
  
  /**
   * Creates a modal to contain GeoEntity create/edit operations.
   */
  function _createModal(html)
  {
    _modal = new YAHOO.widget.Panel("select",  
      { width:"400px", 
        height: "400px",
        fixedcenter:true, 
        close:true, 
        draggable:false, 
        zindex:4,
        modal:true,
        visible:true
      } 
    );
    
    _modal.setBody(html);
    _modal.render(document.body);
  }
  
  /**
   * Handler for new node request.
   */
  function _addNodeHandler()
  {
    // create ul to hold labels
    var ulRaw = document.createElement('ul');
    ul = new YAHOO.util.Element(ulRaw);
    
    // add all labels
    var geoEntity = _getGeoEntity(_selectedNode);
    var type = geoEntity.getType();
    
    var allowedMap = {};
    function collectSubtypes(map, parent)
    {
      var selectable = MDSS.GeoTreeSelectables.types[parent];
      for(var i=0; i<selectable.length; i++)
      {
      	var entry = selectable[i];
      	map[entry.type] = entry;
      	
      	collectSubtypes(map, entry.type);
      }
    }
    
    collectSubtypes(allowedMap, type);
    
    var allowedTypes = Mojo.util.getValues(allowedMap);
    for(var i=0; i<allowedTypes.length; i++)
    {
      var entry = allowedTypes[i];
    	
      var liRaw = document.createElement('li');
      var li = new YAHOO.util.Element(liRaw);
      
      li.on('click', _createTypeSelected, {
        type : entry.type,
        label : entry.label
      });
      
      liRaw.innerHTML = entry.label;
      
      ul.appendChild(liRaw);
    }
    
    _createModal(ulRaw);
  }
  
  /**
   * Cancels an action to update a node.
   */
  function _cancelNode()
  {
    var request = new MDSS.Request({
      onSuccess: function(){
        _modal.destroy();
      }
    });

    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.unlock(request);
  }
  
  /**
   * Deletes the current node from the tree.
   */
  function _deleteNode()
  {
    var request = new MDSS.Request({
      onSuccess: function(){
        
        _modal.destroy();
        
        _removeMapping(_selectedNode);

        var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
      }
    });

    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.remove(request);
  }
  
  /**
   * Handler for edit node request.
   */
  function _editNodeHandler()
  {
    var request = new MDSS.Request({
      onSuccess: function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);
        
        _createModal(html);
        
        eval(executable);
      }
    });
    
    var geoEntity = _getGeoEntity(_selectedNode);

    var controller = Mojo.util.getType(geoEntity.getType()+"Controller");
    controller.setDeleteListener(_deleteNode);
    controller.setUpdateListener(_updateNode);
    controller.setCancelListener(_cancelNode);
    controller.edit(request, geoEntity.getId());
  }
  
  /**
   * Deletes the selected node from the tree.
   */
  function _deleteNodeHandler()
  {
    var request = new MDSS.Request({
      onSuccess : function(){
        
        _removeMapping(_selectedNode);

        var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
      }
    });
    
    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.remove(request);
  }
  
  /**
   * Invokes _selectedCallback with the id of the GeoEntity
   * represented by the currently selected node.
   */
  function _customSelectHandler()
  {
    var geoEntity = _getGeoEntity(_selectedNode);
    
    _selectCallback(geoEntity, _selectedNode);
  }
  
  /**
   * Event handler for a triggered context menu. This method
   * cancels the action if the menu was not triggered on a node.
   */
  function _nodeMenuSelect()
  {
    var oTarget = this.contextEventTarget;

    var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");
    if (htmlNode) {
      _selectedNode = _geoTree.getNodeByElement(htmlNode);
    }
    else {
      this.cancel();
    }
  }
  
  /**
   * Loads child nodes dynamically
   */
  function _dynamicLoad(parentNode, fnLoadComplete)
  {
    // request to fetch children
    var request = new MDSS.Request({
      onSuccess : function(query){
        
        var childNodes = query.getResultSet();
        
        for(var i=0; i<childNodes.length; i++)
        {
          var child = childNodes[i];

          var div = _createNodeDiv(child);
          
          var node = new YAHOO.widget.HTMLNode(div);
          parentNode.appendChild(node);
          
          _setMapping(node, child);
        }
        
        fnLoadComplete();
        parentNode.refresh();
      }
    });

    var geoEntity = _getGeoEntity(parentNode);
    geoEntity.getOrderedChildren(request, _filterType);
  }
  
  /**
   * Adds a parent to a new child.
   */
  function _addChildToParent(e, obj)
  {
    var clone = obj.clone;

    var ddThis = this;
    var request = new MDSS.Request({
      onSuccess : function(){
        
        var ddThis = obj.references.ddThis;
        var parentId = obj.references.parentId;
        
        // remove drag-hint class
        ddThis.getElDom(parentId).className = ddThis.getElDom(parentId).classNameBeforeDrag;
    
        var destNode = YAHOO.util.DDM.getDDById(parentId).node;
        
        // remove
        if(!obj.clone)
        {
          thisParent = ddThis.node.parent;
          ddThis.node.tree.popNode(ddThis.node);
    
          // fixes bug where a parent with 1 item is still marked
          // expanded, so when a new node is dragged to it, it doesn't
          // draw the new node (thinks it's already expanded)
          if (thisParent.children.length == 0) {
            thisParent.expanded = false;
          }
    
          // make removal changes visible
          thisParent.refresh();
        
        }
  
        // Only add the node if the children have loaded via Ajax.
        // Otherwise, the node will appear twice (i.e., once from
        // the drag and drop and once from the Ajax load).
        if(destNode.dynamicLoadComplete)
        {
          ddThis.node.appendTo(destNode);
          destNode.refresh();  
        }

        destNode.expanded = false; // force re-expansion
        destNode.expand();
      }
    });
    
    
    var childGeoEntity = MDSS.GeoEntityTree.getGeoEntity(obj.references.childId);
    var parentGeoEntity = MDSS.GeoEntityTree.getGeoEntity(obj.references.parentId);
    
    obj.references.modal.destroy();

    childGeoEntity.applyWithParent(request, parentGeoEntity.getId(), obj.clone);
  }
  
  /**
   * Handler for the drag/drop operation. The this
   * reference is set to the YAHOO.util.DDNodeProxy instance.
   */
  function _dragDropHandler(id)
  {
  	// create popup asking if this is for a copy operation
  	var request = new MDSS.Request({
  	  references: {childId:this.id, parentId:id, ddThis:this},
  	  onConfirmParentChangeException : function(e)
  	  {
  	  	var modal = new YAHOO.widget.Panel("confirmParentChange", {
  	  	  fixedcenter: true,
  	  	  width: '300px',
  	  	  visible: true,
  	  	  draggable: false,
  	  	  zindex: 8000,
  	  	  modal:true
  	  	});
  	  	
  	  	var upperDiv = document.createElement('div');
  	  	YAHOO.util.Dom.addClass(upperDiv, 'modalAlertBox');
  	  	
  	  	var message = document.createElement('span');
  	  	message.innerHTML = e.getLocalizedMessage();
  	  	upperDiv.appendChild(message);
  	  	
  	  	// yes/no buttons
  	  	var lowerDiv = document.createElement('div');
  	  	YAHOO.util.Dom.addClass(lowerDiv, 'modalAlertBox');

        this.references.modal = modal;

  	  	var yes = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(yes, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(yes, 'value', MDSS.Localized.Choice.Yes);
  	  	YAHOO.util.Event.on(yes, 'click', _addChildToParent, {clone:false, references:this.references}); // this == tree
        lowerDiv.appendChild(yes);

  	  	var no = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(no, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(no, 'value', MDSS.Localized.Choice.No);
  	  	YAHOO.util.Event.on(no, 'click', _addChildToParent, {clone:true, references:this.references}); // this == tree
  	  	lowerDiv.appendChild(no);
  	  	
  	  	var wrapperDiv = document.createElement('div');
  	  	wrapperDiv.appendChild(upperDiv);
  	  	wrapperDiv.appendChild(lowerDiv);
  	  	
  	  	modal.bringToTop();
  	  	modal.setBody(wrapperDiv);
        modal.render(document.body);
  	  }
  	});
  	
  	
    var childGeoEntity = MDSS.GeoEntityTree.getGeoEntity(this.id);
    var parentGeoEntity = MDSS.GeoEntityTree.getGeoEntity(id);
    childGeoEntity.confirmChangeParent(request, parentGeoEntity.getId()); 
  }
  
  /**
   * Creates a div element as a string that represents the given GeoEntity.
   */
  function _createNodeDiv(geoEntity)
  {
    var activeClass = geoEntity.getActivated() === true ? 'activeEntity' : 'inactiveEntity';
    
    var span = _createContentSpan(geoEntity);
    var div = "<div class='"+activeClass+"'>"+span+"</div>";
          
    return div;
  }
  
  function _createContentSpan(geoEntity)
  {
    return "<span title='"+geoEntity.getGeoId()+"'>"+geoEntity.getEntityName()+"</span>";
  }
  
  /**
   * Flips the activated status (via a class) on all nodes
   * represented by the given ids, if that node exists in the DOM.
   */
  function _updateActivatedOnNodes(ids, activated)
  {
    for(var i=0; i<ids.length; i++)
    {
      var id = ids[i];
      
      var nodeIds = _geoEntityIdToNodeIdMap[id];
      for(var j=0; j<nodeIds.length; j++)
      {
        var el = document.getElementById(nodeIds[j]);
        var nodeDiv = new YAHOO.util.Element(el.firstChild);

        if(activated === true)
        {
          nodeDiv.removeClass('inactiveEntity');
          nodeDiv.addClass('activeEntity');
        }
        else
        {
          nodeDiv.removeClass('activeEntity');
          nodeDiv.addClass('inactiveEntity');
        }
      }
    }
  }
  
  /**
   * Renders the actual tree with the given root GeoEntity
   */
  function _renderTree(treeId, geoEntity, selectCallback)
  {
  	var div = _createNodeDiv(geoEntity);
    var node =  {type:"HTML", html:div};

    _geoTree = new YAHOO.widget.TreeViewDD(treeId, [node], _dragDropHandler);
    _geoTree.setDynamicLoad(_dynamicLoad);
    _geoTree.render();

    var itemData = [];

    // the select callback is optional
    if(Mojo.util.isFunction(selectCallback))
    {
      _selectCallback = selectCallback;
      var selectMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Select);
      selectMenuItem.subscribe("click", _customSelectHandler);
      itemData.push(selectMenuItem);
    }

    var createMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Create);
    createMenuItem.subscribe("click", _addNodeHandler);
    itemData.push(createMenuItem);
    
    var editMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Edit);
    editMenuItem.subscribe("click", _editNodeHandler);
    itemData.push(editMenuItem);
    
    var deleteMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Delete);
    deleteMenuItem.subscribe("click", _deleteNodeHandler);
    itemData.push(deleteMenuItem);

    _menu = new YAHOO.widget.ContextMenu("treeMenu", {
      trigger:treeId,
      lazyload:true,
      itemdata: itemData,
      zindex:500
    });
    
    _menu.subscribe("triggerContextMenu", _nodeMenuSelect);
    
    // map node to GeoEntity
    _setMapping(_geoTree.getRoot().children[0], geoEntity);
  }
  
  /**
   * Initializes the tree by setting the GeoEntity with the
   * given id as first node under the root.
   */
  function _initializeTree(treeId, selectCallback, filterType) {
  	
    var request = new MDSS.Request({
      onSuccess : function(geoEntity){
        // build tree
        _renderTree(treeId, geoEntity, selectCallback);
      }
    });
    
    _filterType = arguments.length === 3 ? filterType : '';
    
    // Fetch the root node
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, MDSS.GeoEntityTreeRootId);
  }
  
  // return all public methods/properties
  return {
    initializeTree : _initializeTree,
    getGeoEntity : _getGeoEntity,
    destroyAll : _destroyAll
  };
})();