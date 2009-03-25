/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
MDSS.GeoEntityTree = (function(){

  // key/value is node id/GeoEntity id
  var _nodeToGeoEntityMap = {};

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
    delete _geoEntityCache[geId];
  }
  
  /**
   * Creates a new node.
   */
  function _createNode(type, params, action)
  {
    var geConstructor = Mojo.util.getType(type);
    var geoEntity = new geConstructor();

    var entityName = params['dto.entityName'];
    var geoId = params['dto.geoId'];
    var activated = params['dto.activated'];
    
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);

    var request = new Mojo.ClientRequest({
      onSuccess : function(ids, geoEntity){
        
        // add the node directly if the children have already been dynamically loaded
        if(_selectedNode.dynamicLoadComplete)
        {
          var node = new YAHOO.widget.HTMLNode(geoEntity.getEntityName(), _selectedNode);
          
          _selectedNode.expanded = false // force a re-expansions
          _selectedNode.refresh();
          
          // must reset mapping with new id
          _setMapping(node, geoEntity);
        }

        _selectedNode.expand();
        
        _modal.destroy();
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
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
    var entityName = params['dto.entityName'];
    var geoId = params['dto.geoId'];
    var activated = params['dto.activated'];
    
    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);
    
    var request = new Mojo.ClientRequest({
      onSuccess: function(ids, geoEntity){
        _selectedNode.setHtml(geoEntity.getEntityName());
        _setMapping(_selectedNode, geoEntity);
        
        _modal.destroy();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      label : obj.label,
      onSuccess : function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        var labelEl = "<h3>"+this.label+"</h3><hr />";
        html = labelEl + html;      
        _modal.setBody(html);
        
        eval(executable);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      onSuccess: function(){
        _modal.destroy();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      onSuccess: function(){
        
        _modal.destroy();
        
        _removeMapping(_selectedNode);

        var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      onSuccess: function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);
        
        _createModal(html);
        
        eval(executable);
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      onSuccess : function(){
        
        _removeMapping(_selectedNode);

        var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
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
    var request = new Mojo.ClientRequest({
      onSuccess : function(query){
        
        var childNodes = query.getResultSet();
        
        for(var i=0; i<childNodes.length; i++)
        {
          var child = childNodes[i];
          var node = new YAHOO.widget.HTMLNode(child.getEntityName());
          parentNode.appendChild(node);
          
          _setMapping(node, child);
        }
        
        fnLoadComplete();
        parentNode.refresh();
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(parentNode);
    geoEntity.getOrderedChildren(request, _filterType);
  }
  
  /**
   * Handler for the drag/drop operation. The this
   * reference is set to the YAHOO.util.DDNodeProxy instance.
   */
  function _dragDropHandler(id)
  {
      /* JN change: create new relationship between parent and child */
      var ddThis = this;
      var request = new Mojo.ClientRequest({
        ddThis : ddThis,
        onSuccess : function(){
          
          // remove drag-hint class
          this.ddThis.getElDom(id).className = this.ddThis.getElDom(id).classNameBeforeDrag;
      
          var destNode = YAHOO.util.DDM.getDDById(id).node;
          
          // remove
          thisParent = this.ddThis.node.parent;
          this.ddThis.node.tree.popNode(this.ddThis.node);
      
          // fixes bug where a parent with 1 item is still marked
          // expanded, so when a new node is dragged to it, it doesn't
          // draw the new node (thinks it's already expanded)
          if (thisParent.children.length == 0) {
            thisParent.expanded = false;
          }
      
          // make removal changes visible
          thisParent.refresh();
          
          // Only add the node if the children have loaded via Ajax.
          // Otherwise, the node will appear twice (i.e., once from
          // the drag and drop and once from the Ajax load).
          if(destNode.dynamicLoadComplete)
          {
            this.ddThis.node.appendTo(destNode);
            destNode.refresh();  
          }

          destNode.expanded = false; // force re-expansion
          destNode.expand();
        },
        onFailure : function(e){
          alert(e.getLocalizedMessage());
        }
      });
      
      var childGeoEntity = MDSS.GeoEntityTree.getGeoEntity(this.id);
      var parentGeoEntity = MDSS.GeoEntityTree.getGeoEntity(id);
      childGeoEntity.applyWithParent(request, parentGeoEntity.getId(), false);
  }
  
  /**
   * Renders the actual tree with the given root GeoEntity
   */
  function _renderTree(treeId, geoEntity, selectCallback)
  {
    var node = {type:"HTML", html:geoEntity.getEntityName()};

    _geoTree = new YAHOO.widget.TreeViewDD(treeId, [node], _dragDropHandler);
    _geoTree.setDynamicLoad(_dynamicLoad);
    _geoTree.render();

    var itemData = [];

    // the select callback is optional
    if(Mojo.util.isFunction(selectCallback))
    {
      _selectCallback = selectCallback;
      var selectMenuItem = new YAHOO.widget.ContextMenuItem("Select");
      selectMenuItem.subscribe("click", _customSelectHandler);
      itemData.push(selectMenuItem);
    }

    var createMenuItem = new YAHOO.widget.ContextMenuItem("Create");
    createMenuItem.subscribe("click", _addNodeHandler);
    itemData.push(createMenuItem);
    
    var editMenuItem = new YAHOO.widget.ContextMenuItem("Edit");
    editMenuItem.subscribe("click", _editNodeHandler);
    itemData.push(editMenuItem);
    
    var deleteMenuItem = new YAHOO.widget.ContextMenuItem("Delete");
    deleteMenuItem.subscribe("click", _deleteNodeHandler);
    itemData.push(deleteMenuItem);

    _menu = new YAHOO.widget.ContextMenu("treeMenu", {
      trigger:treeId,
      lazyload:true,
      itemdata: itemData,
      zindex:9999
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
    var request = new Mojo.ClientRequest({
      onSuccess : function(geoEntity){
        // build tree
        _renderTree(treeId, geoEntity, selectCallback);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
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