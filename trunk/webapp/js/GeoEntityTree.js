/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
MDSS.GeoEntityTree = (function(){

  // key/value is node id/GeoEntity id
  var _nodeToGeoEntityMap = {};

  // key/value is entity id/[node ids]
  var _geoEntityIdToNodeIdMap = {};

  // key/value is GeoEntity id/GeoEntityView
  var _geoEntityViewCache = {};

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
  	_geoEntityViewCache = {};
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
  function _setMapping(node, geoEntityView)
  {
    var nodeId = node.contentElId;
    var geId = geoEntityView.getGeoEntityId();
    
    // overwrite any existing entries
    _nodeToGeoEntityMap[nodeId] = geId;
    _geoEntityViewCache[geId] = geoEntityView;
    
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
  function _getGeoEntityView(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _nodeToGeoEntityMap[nodeId];
    return _geoEntityViewCache[geId];
  }
  
  /**
   * Removes the node to GeoEntity mapping.
   */
  function _removeMapping(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _getGeoEntityView(nodeId).getGeoEntityId();
    
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
      delete _geoEntityViewCache[geId];
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
          var view = _copyEntityToView(geoEntity);
          var div = _createNodeDiv(geoEntity);
          
          // add the node to all parent nodes
          var parentGeoEntityView = _getGeoEntityView(_selectedNode);
          var nodeIds = _geoEntityIdToNodeIdMap[parentGeoEntityView.getGeoEntityId()];
          for(var i=0; i<nodeIds.length; i++)
          {
          	var parentEl = document.getElementById(nodeIds[i]);
          	var parent = _geoTree.getNodeByElement(parentEl);
          	
            // don't expand the node if the parent's children haven't been loaded (it's wasteful)
            if(parent.dynamicLoadComplete)
            {
              var node = new YAHOO.widget.HTMLNode(div, parent);
              _setMapping(node, view);

              // only re-expand the selected node or the parent if its already expanded
              if(parent.getElId() === _selectedNode.getElId() || parent.expanded === true)
              {              
                parent.expanded = false; // force a re-expansions
                parent.refresh();
              }
            }
          }
        }

        _selectedNode.expand();
        
        _modal.destroy();
      }
    });
    
    var parentGeoEntityView = _getGeoEntityView(_selectedNode);
    geoEntity.applyWithParent(request, parentGeoEntityView.getGeoEntityId(), false);
  }
  
  function _copyEntityToView(geoEntity)
  {
    var view = new Mojo.$.dss.vector.solutions.geo.GeoEntityView();
    
    view.setGeoEntityId(geoEntity.getId());
    view.setGeoId(geoEntity.getGeoId());
    view.setActivated(geoEntity.getActivated());
    view.setEntityName(geoEntity.getEntityName());
    view.setEntityType(geoEntity.getType());
    
    return view;
  }
  
  function _performUpdate(params, geoEntity)
  {
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
        
        // update mapping
        var view = _copyEntityToView(geoEntity);
        _setMapping(_selectedNode, view);
        
        _updateActivatedOnNodes(ids, geoEntity.getActivated());
        
        _modal.destroy();
      }
    });
    
    geoEntity.updateFromTree(request);
  }
  
  /**
   * Updates a node.
   */
  function _updateNode(params, actions)
  {
    var geoEntityView = _getGeoEntityView(_selectedNode);

    var request = new MDSS.Request({
      params: params,
      onSuccess: function(geoEntity)
      {
      	_performUpdate(this.params, geoEntity);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, geoEntityView.getGeoEntityId());
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
    var geoEntityView = _getGeoEntityView(_selectedNode);
    var type = geoEntityView.getEntityType();
    
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

    var geoEntityView = _getGeoEntityView(_selectedNode);
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.unlock(request, geoEntityView.getGeoEntityId());
  }
  
  /**
   * Performs the DOM level cleanup after an
   * entity has been deleted. The deleteAll
   * param denotes if the deleted entity should
   * be removed from all parent nodes (as opposed
   * to the current parent).
   */
  function _postDeleteCleanup(deleteAll)
  {
  	if(deleteAll)
  	{
  	   var geoEntityView = _getGeoEntityView(_selectedNode);
  	   var nodeIds = _geoEntityIdToNodeIdMap[geoEntityView.getGeoEntityId()];
  	   for(var i=nodeIds.length-1; i>=0; i--)
  	   {
  	     var nodeId = nodeIds[i];
  	     var nodeEl = document.getElementById(nodeId);
         var node = _geoTree.getNodeByElement(nodeEl);
         
         _removeMapping(node);

         var parent = node.parent;
         _geoTree.removeNode(node);
         
         parent.refresh();
  	   }
  	}
  	else
  	{
      _removeMapping(_selectedNode);

      var parent = _selectedNode.parent;
      _geoTree.removeNode(_selectedNode);
      parent.refresh();
  	}
  }
  
  /**
   * Callback for when a user has chosen to delete a GeoEntity
   * and had to confirm whether to delete the entity itself or
   * its relationship with the current parent.
   */
  function _deleteAfterConfirmation(e, obj)
  {
  	var geoEntity = obj.childEntity;
  	
  	var request = new MDSS.Request({
  	  // deleting the GeoEntity means all parent nodes containing
  	  // the child must delete the child node.
  	  deleteAll: obj.deleteEntity,
  	  modal:obj.modal,
  	  onSuccess: function()
  	  {
  	  	this.modal.destroy();
  	  	
  	  	_postDeleteCleanup(this.deleteAll);
  	  }
  	});
  	
  	if(obj.deleteEntity)
  	{
  	  geoEntity.remove(request); 
  	}
  	else
  	{
  	  geoEntity.deleteRelationship(request, obj.parentId);
  	}
  }
  
  /**
   * Performs a delete request, and handles the
   * case where a child has multiple are
   */
  function _performDelete(destroyModal, geoEntity)
  {
    // get its immediate parent
    var parent = _selectedNode.parent;
    var parentGeoEntityView = _getGeoEntityView(parent);

    var request = new MDSS.Request({
      destroyModal:destroyModal,
      childEntity : geoEntity,
      parentId : parentGeoEntityView.getGeoEntityId(),
      onSuccess: function(){
        
        if(this.destroyModal)
        {
          _modal.destroy();
        }
        
        _postDeleteCleanup();
      },
      onConfirmDeleteEntityException: function(e){
      	
  	  	var modal = new YAHOO.widget.Panel("confirmDelete", {
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

        var delEntityObj = {
          deleteEntity:true,
          childEntity:this.childEntity,
          parentId:this.parentId,
          modal:modal
        }
  	  	var delEntity = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(delEntity, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(delEntity, 'value', MDSS.Localized.Delete.Entity);
  	  	YAHOO.util.Event.on(delEntity, 'click', _deleteAfterConfirmation, delEntityObj);
        lowerDiv.appendChild(delEntity);

        var delRelObj = {
          deleteEntity:false,
          childEntity:this.childEntity,
          parentId:this.parentId,
          modal:modal
        }
  	  	var delRel = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(delRel, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(delRel, 'value', MDSS.Localized.Delete.Relationship);
  	  	YAHOO.util.Event.on(delRel, 'click', _deleteAfterConfirmation, delRelObj);
  	  	lowerDiv.appendChild(delRel);
  	  	
  	  	var wrapperDiv = document.createElement('div');
  	  	wrapperDiv.appendChild(upperDiv);
  	  	wrapperDiv.appendChild(lowerDiv);
  	  	
  	  	modal.bringToTop();
  	  	modal.setBody(wrapperDiv);
        modal.render(document.body);
      }
    });

    
    if(parent == null)
    {
      geoEntity.remove();
    }
    else
    {
      geoEntity.confirmDeleteEntity(request, parentGeoEntityView.getGeoEntityId());
    }
  }
  
  /**
   * Deletes the current node from the tree.
   */
  function _deleteNode()
  {
    var geoEntityView = _getGeoEntityView(_selectedNode);

    var request = new MDSS.Request({
      onSuccess: function(geoEntity)
      {
      	_performDelete(true, geoEntity);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, geoEntityView.getGeoEntityId());
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
    
    var geoEntityView = _getGeoEntityView(_selectedNode);

    var controller = Mojo.util.getType(geoEntityView.getEntityType()+"Controller");
    controller.setDeleteListener(_deleteNode);
    controller.setUpdateListener(_updateNode);
    controller.setCancelListener(_cancelNode);
    controller.edit(request, geoEntityView.getGeoEntityId());
  }
  
  /**
   * Deletes the selected node from the tree.
   */
  function _deleteNodeHandler()
  {
    var geoEntityView = _getGeoEntityView(_selectedNode);

    var request = new MDSS.Request({
      onSuccess: function(geoEntity)
      {
      	_performDelete(false, geoEntity);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, geoEntityView.getGeoEntityId());
  }
  
  /**
   * Invokes _selectedCallback with the id of the GeoEntity
   * represented by the currently selected node.
   */
  function _customSelectHandler()
  {
    var geoEntityView = _getGeoEntityView(_selectedNode);
    
    _selectCallback(geoEntityView, _selectedNode);
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

    var geoEntityView = _getGeoEntityView(parentNode);
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, geoEntityView.getGeoEntityId(), _filterType);
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
        
        var childNode = null;
        if(obj.clone)
        {
          // clone the node (do not clone its children)
          var div = ddThis.node.getContentEl().innerHTML;
          childNode = new YAHOO.widget.HTMLNode(div);
        }
        else
        {
          // not cloning, so remove the old node
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
          
          childNode = ddThis.node;
        }
  
        // Only add the node if the children have loaded via Ajax.
        // Otherwise, the node will appear twice (i.e., once from
        // the drag and drop and once from the Ajax load).
        if(destNode.dynamicLoadComplete)
        {
          childNode.appendTo(destNode);
          destNode.refresh();  
        }

        destNode.expanded = false; // force re-expansion
        destNode.expand();
        
        if(obj.clone)
        {
          // copy the mapping from the old node to the new one
          var geoEntityView = _getGeoEntityView(ddThis.node);
          _setMapping(childNode, geoEntityView);
        }
      }
    });
    
    
    var childGeoEntityView = _getGeoEntityView(obj.references.childId);
    var parentGeoEntityView = _getGeoEntityView(obj.references.parentId);
    
    var childId = childGeoEntityView.getGeoEntityId();
    var parentId = parentGeoEntityView.getGeoEntityId();
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.applyWithParent(request, childId, parentId, obj.clone);

    obj.references.modal.destroy();
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
  	
  	
    var childGeoEntityView = _getGeoEntityView(this.id);
    
    var childEl = document.getElementById(this.id);
    var childNode = _geoTree.getNodeByElement(childEl);
    var parentGeoEntityView = _getGeoEntityView(childNode.parent);
    
    var childId = childGeoEntityView.getGeoEntityId();
    var parentId = parentGeoEntityView.getGeoEntityId();
    
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.confirmChangeParent(request, childId, parentId); 
  }
  
  /**
   * Creates a div element as a string that represents the given GeoEntity.
   */
  function _createNodeDiv(geoEntityView)
  {
    var activeClass = geoEntityView.getActivated() === true ? 'activeEntity' : 'inactiveEntity';
    
    var span = _createContentSpan(geoEntityView);
    var div = "<div class='"+activeClass+"'>"+span+"</div>";
          
    return div;
  }
  
  function _createContentSpan(geoEntityView)
  {
    return "<span title='"+geoEntityView.getGeoId()+"'>"+geoEntityView.getEntityName()+"</span>";
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
    var view = _copyEntityToView(geoEntity);

  	var div = _createNodeDiv(view);
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
    _setMapping(_geoTree.getRoot().children[0], view);
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
    destroyAll : _destroyAll
  };
})();