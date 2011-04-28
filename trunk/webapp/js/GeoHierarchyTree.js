/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
MDSS.GeoHierarchyTree = (function(){

  // key/value is node id/GeoHierarchy id
  var _nodeToGeoHierarchyMap = {};

  // key/value is GeoEntity id/GeoHierarchy
  var _geoHierarchyCache = {};

  // key/value is geo hierarchy id/[node ids]
  var _geoHierarchyIdToNodeIdMap = {};

  // The selected node in tree
  var _selectedNode = null;

  // The tree for GeoEntities
  var _hierarchyTree = null;

  // The menu for CRUD operations
  var _menu = null;

  // reference to modal for node create/edit
  var _modal = null;
  
  // reference to the Ontology browser used to select the proper
  // term for a univeral
  var _sharedBrowser = null
  
  /**
   * Removes everything from the current Tree
   */
  function _destroyAll()
  {
  	_nodeToGeoHierarchyMap = {};

  	_geoHierarchyCache = {};

  	_selectedNode = null;

  	_modal = null;

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

  	_hierarchyTree.destroy();
  	_hierarchyTree = null;
  }

  /**
   * Sets the mapping between a node and GeoEntity.
   * More than one node may point to a GeoEntity.
   */
  function _setMapping(node, geoEntity)
  {
    var nodeId = node.contentElId;
    var geId = geoEntity.getGeoHierarchyId();

    // overwrite any existing entries
    _nodeToGeoHierarchyMap[nodeId] = geId;
    _geoHierarchyCache[geId] = geoEntity;

    // map the entity id to the node id
    var nodeIds = _geoHierarchyIdToNodeIdMap[geId];
    if(Mojo.Util.isArray(nodeIds))
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
      _geoHierarchyIdToNodeIdMap[geId] = nodeIds;
    }
  }

  /**
   * Gets the GeoEntity that maps to the given
   * node.
   */
  function _getGeoHierarchyView(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _nodeToGeoHierarchyMap[nodeId];
    return _geoHierarchyCache[geId];
  }

  /**
   * Removes the node to GeoEntity mapping.
   */
  function _removeMapping(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    var geId = _getGeoHierarchyView(nodeId).getGeoHierarchyId();

    delete _nodeToGeoHierarchyMap[nodeId];

    var nodeIds = _geoHierarchyIdToNodeIdMap[geId];
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
      delete _geoHierarchyCache[geId];
    }
  }

  /**
   * Creates a new node.
   */
  function _createNode(params, action)
  {
    var request = new MDSS.Request({
      onSuccess : function(geoHierarchyId){

        var request = new MDSS.Request({
          onSuccess : function(geoHierarchyView){

            // add the node directly if the children have already been dynamically loaded
            if(_selectedNode.dynamicLoadComplete)
            {
              // add the node to all parent nodes
              var parentGeoHierarchy = _getGeoHierarchyView(_selectedNode);
              var nodeIds = _geoHierarchyIdToNodeIdMap[parentGeoHierarchy.getGeoHierarchyId()];
              for(var i=0; i<nodeIds.length; i++)
              {
                var parentEl = document.getElementById(nodeIds[i]);
                var parent = _hierarchyTree.getNodeByElement(parentEl);

                // don't expand the node if the parent's children haven't been loaded (it's wasteful)
                if(parent.dynamicLoadComplete)
                {
                  var node = new YAHOO.widget.HTMLNode(geoHierarchyView.getDisplayLabel(), parent);
                  _setMapping(node, geoHierarchyView);

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

        // fetch the view
        Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, geoHierarchyId);
      }
    });

    return request;
  }

  /**
   * Updates a node.
   */
  function _updateNode(params, actions)
  {
    var request = new MDSS.Request({
      onSuccess: function(geoHierarchyId){

      	var request = new MDSS.Request({
      	  onSuccess : function(geoHierarchy)
      	  {
            // update selected node and all copies
            //_selectedNode.setHtml(div);
            var nodeIds = _geoHierarchyIdToNodeIdMap[geoHierarchy.getGeoHierarchyId()];
            for(var i=0; i<nodeIds.length; i++)
            {
              var id = nodeIds[i];
              var el = document.getElementById(id);
              el.innerHTML = geoHierarchy.getDisplayLabel();
            }

            // update mapping FIXME (needed?)
            _setMapping(_selectedNode, geoHierarchy);

            _modal.destroy();
      	  }
      	});

        Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, geoHierarchyId);
      }
    });

    return request;
  }

  /**
   * Creates a modal to contain GeoEntity create/edit operations.
   */
  function _createModal(html, closeWin)
  {
    _modal = new YAHOO.widget.Panel("select",
      { width:"400px",
        height: "500px",
        fixedcenter:true,
        close: arguments.length > 1 ? closeWin : false,
        draggable:false,
        zindex:4,
        modal:true,
        visible:true
      }
    );

    _modal.setBody(html);
    _modal.render(document.body);
    
    // hook events to launch ontology browser
    YAHOO.util.Event.on('termBtn', 'click', _openBrowser);
    var search = new MDSS.GenericSearch('termDisplay', 'term', _displayFunction, _displayFunction, _idFunction, _searchFunction);
    
    // Setup validator
    new MDSS.OntologyValidator('term', search, _getParameters, _setField);
  }
  
  function _displayFunction(valueObject)
  {
    if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
    {
      return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
    }

    return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
  }
  
  function _idFunction(valueObject)
  {
    if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
    {
      return valueObject.getTermId();
    }
  
    return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
  }  
  
  function _getParameters()
  {
    return ['dss.vector.solutions.geo.GeoEntityDefinition', 'term'];
  }
    
  function  _searchFunction(request, value)
  {
    var params = _getParameters();
    
    Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request, value, params);
  }    

  /**
   * Handler for new node request.
   */
  function _addNodeHandler()
  {
    var geoHierarchyView = _getGeoHierarchyView(_selectedNode);

    var request = new MDSS.Request({
      parentLabel:geoHierarchyView.getDisplayLabel(),
      onSuccess : function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        var header = MDSS.Localized.New_Universal_Located_In;
        header = header.replace(/\[parent\]/, this.parentLabel);

        var labelEl = "<h3>"+header+"</h3><hr />";
        html = labelEl + html;
       _createModal(html);
       
        eval(executable);
      }
    });


    var controller = Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;
    controller.setCreateDefinitionListener(_createNode);
    controller.setCancelCreateDefinitionListener(function(){
      _modal.destroy();
    });
    controller.newDefinition(request, geoHierarchyView.getGeoHierarchyId());
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

    return request;
  }

  /**
   * Performs the DOM level cleanup after a hierarchy
   * has been deleted. The deleteAll
   * param denotes if the deleted entity should
   * be removed from all parent nodes (as opposed
   * to the current parent).
   */
  function _postDeleteCleanup(deleteAll, ids)
  {
  	if(deleteAll)
  	{
  	   function deleteNodeFromTree(geoHierarchyId)
  	   {
   	     var nodeIds = _geoHierarchyIdToNodeIdMap[geoHierarchyId];
   	     if(!nodeIds) return;
  	     for(var i=nodeIds.length-1; i>=0; i--)
  	     {
  	       var nodeId = nodeIds[i];
  	       var nodeEl = document.getElementById(nodeId);
           var node = _hierarchyTree.getNodeByElement(nodeEl);

           _removeMapping(node);

           var parent = node.parent;
           _hierarchyTree.removeNode(node);

           parent.refresh();
  	     } 	   
  	   } 
  	   
   	   // remove is_a children entries (this happens after a full deletion
  	   // of a parent. Remove the parent itself
//  	   var getHierarchyView = _getGeoHierarchyView(_selectedNode);
//  	   ids.push(getHierarchyView.getGeoHierarchyId());
  	   for(var i=0; i<ids.length; i++)
  	   {
  	     deleteNodeFromTree(ids[i]);
  	   }
  	}
  	else
  	{
      _removeMapping(_selectedNode);

      var parent = _selectedNode.parent;
      _hierarchyTree.removeNode(_selectedNode);
      parent.refresh();
  	}
  }

  /**
   * Callback for when a user has chosen to delete a GeoHierarchy
   * and had to confirm whether to delete the object itself or
   * its relationship with the current parent.
   */
  function _deleteAfterConfirmation(e, obj)
  {
  	var geoHierarchyView = obj.childHierarchy;

  	var request = new MDSS.Request({
  	  // deleting the GeoEntity means all parent nodes containing
  	  // the child must delete the child node.
  	  deleteAll: obj.deleteHierarchy,
  	  modal:obj.modal,
  	  onSuccess: function(ids)
  	  {
  	  	this.modal.destroy();

  	  	_postDeleteCleanup(this.deleteAll, ids);
  	  }
  	});

  	var geoHierarchyId = geoHierarchyView.getGeoHierarchyId();
  	if(obj.deleteHierarchy)
  	{
  	  Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request, geoHierarchyId);
  	}
  	else
  	{
  	  Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteRelationship(request, geoHierarchyId, obj.parentId);
  	}
  }

  /**
   * Performs the delete operation.
   */
  function _performDelete(destroyModal, geoHierarchyView)
  {
    // get its immediate parent
    var parent = _selectedNode.parent;
    var parentGeoHierarchyView = _getGeoHierarchyView(parent);
    var parentId = parentGeoHierarchyView != null ? parentGeoHierarchyView.getGeoHierarchyId() : null;

    var request = new MDSS.Request({
      destroyModal:destroyModal,
      childHierarchy : geoHierarchyView,
      parentId : parentId,
      onSuccess: function(ids){

        if(this.destroyModal)
        {
          _modal.destroy();
        }

        _postDeleteCleanup(true, ids);
      },
      onConfirmDeleteHierarchyException: function(e){

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
          deleteHierarchy:true,
          childHierarchy:this.childHierarchy,
          parentId:this.parentId,
          modal:modal
        }
  	  	var delEntity = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(delEntity, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(delEntity, 'value', MDSS.Localized.Delete_Universal);
  	  	YAHOO.util.Event.on(delEntity, 'click', _deleteAfterConfirmation, delEntityObj);
        lowerDiv.appendChild(delEntity);

        var delRelObj = {
          deleteHierarchy:false,
          childHierarchy:this.childHierarchy,
          parentId:this.parentId,
          modal:modal
        }
  	  	var delRel = document.createElement('input');
  	  	YAHOO.util.Dom.setAttribute(delRel, 'type', 'button');
  	  	YAHOO.util.Dom.setAttribute(delRel, 'value', MDSS.Localized.Delete_Relationship);
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

    var geoHierarchyId = geoHierarchyView.getGeoHierarchyId();
    if(parent == null || parentGeoHierarchyView == null)
    {
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request, geoHierarchyId);
    }
    else
    {
      var parentGeoHierarchyId = parentGeoHierarchyView.getGeoHierarchyId();
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmDeleteHierarchy(request, geoHierarchyId, parentGeoHierarchyId);
    }
  }

  /**
   * Deletes the current node from the tree.
   */
  function _deleteNode()
  {
    var geoHierarchyView = _getGeoHierarchyView(_selectedNode);
    _performDelete(true, geoHierarchyView);
  }

  /**
   * Handler for edit node request.
   */
  function _editNodeHandler()
  {
    var geoHierarchyView = _getGeoHierarchyView(_selectedNode);

    var request = new MDSS.Request({
      displayLabel: geoHierarchyView.getDisplayLabel(),
      onSuccess: function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        var labelEl = "<h3>"+this.displayLabel+"</h3><hr />";
        html = labelEl + html;
        _createModal(html, false);
        
        eval(executable);
      }
    });


    var controller = Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;
    controller.setUpdateDefinitionListener(_updateNode);
    controller.setCancelUpdateDefinitionListener(_cancelNode);
    controller.editDefinition(request, geoHierarchyView.getGeoHierarchyId());
  }

  /**
   * Deletes the selected node from the tree.
   */
  function _deleteNodeHandler()
  {
    var geoHierarchyView = _getGeoHierarchyView(_selectedNode);
    _performDelete(false, geoHierarchyView);
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
      _selectedNode = _hierarchyTree.getNodeByElement(htmlNode);
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
          var node = new YAHOO.widget.HTMLNode(child.getDisplayLabel()); // DIFF Call
          parentNode.appendChild(node);

          _setMapping(node, child);
        }

        fnLoadComplete();
        parentNode.refresh();
      }
    });

    var geoHierarchyView = _getGeoHierarchyView(parentNode); // DIFF Call
    Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getOrderedChildren(request, geoHierarchyView.getGeoHierarchyId());
  }

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
        var alreadyLoaded = destNode.dynamicLoadComplete;
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
        if(alreadyLoaded)
        {
          childNode.appendTo(destNode);
          destNode.refresh();
          
          if(obj.clone)
          {
            // copy the mapping from the old node to the new one
            var geoHierarchyView = _getGeoHierarchyView(ddThis.node);
            _setMapping(childNode, geoHierarchyView);
          }
        }

        destNode.expanded = false; // force re-expansion
        destNode.expand();
      }
    });


    var childGeoHierarchyView = _getGeoHierarchyView(obj.references.childId);
    var parentGeoHierarchyView = _getGeoHierarchyView(obj.references.parentId);

    var childId = childGeoHierarchyView.getGeoHierarchyId();
    var parentId = parentGeoHierarchyView.getGeoHierarchyId();

    Mojo.$.dss.vector.solutions.geo.GeoHierarchy.applyExistingWithParent(request, childId, parentId, obj.clone);

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
  	  onConfirmHierarchyParentChangeException : function(e)
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


    var childGeoHierarchyView = _getGeoHierarchyView(this.id);

    var childEl = document.getElementById(this.id);
    var childNode = _hierarchyTree.getNodeByElement(childEl);
    var parentGeoHierarchyView = _getGeoHierarchyView(childNode.parent);

    var childId = childGeoHierarchyView.getGeoHierarchyId();
    var parentId = parentGeoHierarchyView.getGeoHierarchyId();

    Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmChangeParent(request, childId, parentId);
  }

  /**
   * Renders the actual tree with the given root GeoEntity
   */
  function _renderTree(treeId, geoHierarchyView)
  {
    var node = {type:"HTML", html:geoHierarchyView.getDisplayLabel()};

    _hierarchyTree = new YAHOO.widget.TreeViewDD(treeId, [node], _dragDropHandler);
    _hierarchyTree.setDynamicLoad(_dynamicLoad);
    _hierarchyTree.render();

    var itemData = [];

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
      itemdata: itemData
    });

    _menu.subscribe("triggerContextMenu", _nodeMenuSelect);

    // map node to GeoEntity
    _setMapping(_hierarchyTree.getRoot().children[0], geoHierarchyView);
  }
  
  
  function _openBrowser(e)
  {
    var termId = document.getElementById('term').value;
    var selected = [];
    if(termId !== '')
    {
      selected.push(termId); 
    }
 
    if(_sharedBrowser.isRendered())
    {
      _sharedBrowser.reset();
      _sharedBrowser.show();
      _sharedBrowser.setSelection(selected); 
    }
    else
    {
      _sharedBrowser.render();
      _sharedBrowser.setSelection(selected); 
    }
  }
  
  function _setField(selected)
  {
    var el = document.getElementById('term');
    var dEl = document.getElementById('termDisplay');
    if(selected.length > 0)
    {
      var sel = selected[0];
      el.value = _idFunction(sel);
      dEl.value = _displayFunction(sel);
    }
    else
    {
      el.value = '';
      dEl.value = '';
    }
  }

  /**
   * Initializes the tree by setting the GeoEntity with the
   * given id as first node under the root.
   */
  function _initializeTree(treeId) {
    var request = new MDSS.Request({
      onSuccess : function(geoHierarchyView){
        // build tree
        _renderTree(treeId, geoHierarchyView);
      }
    });

      // Create the one instance of the OntologyBrowser that will
      // be shared by all BrowserRoots. This is done because only one
      // BrowserRoot can be edited at a time.
      _sharedBrowser = new MDSS.OntologyBrowser(false, 'dss.vector.solutions.geo.GeoEntityDefinition', 'term');
      _sharedBrowser.setHandler(_setField);
    
    // Fetch the root node
    Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, MDSS.GeoHierarchyTreeRootId);
  }

  // return all public methods/properties
  return {
    initializeTree : _initializeTree,
    getGeoEntity : _getGeoHierarchyView
    //destroyAll : _destroyAll
  };
})();