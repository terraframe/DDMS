/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
Mojo.Meta.newClass('MDSS.GeoHierarchyTree', {
	Instance : {
		initialize : function (treeId, rootId) {
			
			this._treeId = treeId;
			
			this._rootId = rootId;
			
      // The selected node in tree
      this._selectedNode = null;
    
      // The tree for GeoEntities
      this._hierarchyTree = null;
    
      // The menu for CRUD operations
      this._menu = null;
    
      // reference to modal for node create/edit
      this._modal = null;
      
      // reference to the Ontology browser used to select the proper
      // term for a univeral
      this._sharedBrowser = null
      
      // ID of the download interval timer
      this._intervalId = null;
  
    },
     
    /**
     * Removes everything from the current Tree
     */
    _destroyAll : function()
    {
    	this._selectedNode = null;
  
    	this._modal = null;
  
    	// this.cfg of the ContextMenu is null which throws an error.
    	// TODO find official fix for this
      try
      {
    	  this._menu.destroy();
      }
      catch(e)
      {
        this._menu = null;
      }
  
    	this._hierarchyTree.destroy();
    	this._hierarchyTree = null;
    },
  
    _createNodeHTML : function(view)
    {
      return "<div>"+view.getDisplayLabel()+"</div>";
    },
    
    /**
     * Creates an HTML node from the the given TermView.
     * If pureObject is set to true, then the returned node
     * will be an object literal as opposed to a HTMLNode.
     */
    _createNode : function(view, parent)
    {
      var html = this._createNodeHTML(view);
      
      var data = {geoHierarchyId: view.getGeoHierarchyId(), geoHierarchyView: view};
      
      return new YAHOO.widget.HTMLNode({html: html, data: data}, parent);
    },
    
    /**
     * Creates a new node.
     */
    _createNodeListener : function(type, params, action)
    {
      var parentId = this._selectedNode.data.geoHierarchyId;
      params['parentId'] = parentId;
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(geoHierarchyId){
  
          var request = new MDSS.Request({
            onSuccess : function(geoHierarchyView){
              // add the node directly if the children have already been dynamically loaded
              if(that._selectedNode.dynamicLoadComplete)
              {
                // add the node to all parent nodes
                var parentGeoHierarchy = that._selectedNode.data.geoHierarchyView;
                
                var nodes = that._hierarchyTree.getNodesByProperty('geoHierarchyId', parentId);
                
                Mojo.Iter.forEach(nodes, function(parent){
                  // don't expand the node if the parent's children haven't been loaded (it's wasteful)
                  if(parent.dynamicLoadComplete)
                  {
                    // create and append the child (non-Ajax)
                  	var node = this._createNode(geoHierarchyView, parent);
                  	
                    // BUG FIX: The element that contains the children is hidden
                    // if there is only one child and it is already expanded. So force
                    // the children element to show.
                  	if (parent.children.length === 1)
                  	{
                  		parent.getChildrenEl().style.display = 'block';
                  	}
                  	
                    // Calling refresh on an unexpanded yet loaded node
                    // will cause the children to be displayed but the 
                    // parent will not be marked as expanded.
                  	if (parent.expanded)
                  	{
                  		parent.refresh();
                  	}
                  	else
                  	{
                  		parent.expand();
                  	}
                  }
                  else if (parent === this._selectedNode)
                  {
                    // force re-expansion (Ajax)
                  	parent.expand();
                  }
                
                }, that);
              }
              
              that._selectedNode.expand();
        
              that._modal.destroy();
            }
          });
  
          // fetch the view
          Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, geoHierarchyId);
        }
      });
  
      return request;
    },
      
    /**
     * Updates a node.
     */
    _updateNodeListener : function(type, params, action)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess: function(geoHierarchyId){
          that._updateNode(geoHierarchyId);
        }
      });
      
      return request;
    },
    
    _updateNode : function(geoHierarchyId)
    {
    	var that = this;
    	var request = new MDSS.Request({
    	  onSuccess : function(geoHierarchy)
    	  {
          // update selected node and all copies
   	      var nodes = that._hierarchyTree.getNodesByProperty('geoHierarchyId', geoHierarchy.getGeoHierarchyId());
   	      
   	      Mojo.Iter.forEach(nodes, function(node) {
            node.getContentEl().innerHTML = geoHierarchy.getDisplayLabel();
   	      }, that);
  
          that._selectedNode.data.geoHierarchyView = geoHierarchy;
    	  }
    	});
  
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, geoHierarchyId);
      this._destroyModal();
    },
  
    /**
     * Creates a modal to contain GeoEntity create/edit operations.
     */
    _createModal : function(html, closeWin)
    {
      this._modal = new YAHOO.widget.Panel("select",
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
      
      if (closeWin)
      {
      	this._modal.subscribe('hide', Mojo.Util.bind(this, this._destroyModal));
      }
  
      this._modal.setBody(html);
      this._modal.render(document.body);
      
      // hook events to launch ontology browser
      YAHOO.util.Event.on('termBtn', 'click', Mojo.Util.bind(this, this._openBrowser));
      var search = new MDSS.GenericSearch('termDisplay', 'term', 
        Mojo.Util.bind(this, this._displayFunction),
        Mojo.Util.bind(this, this._displayFunction), 
        Mojo.Util.bind(this, this._idFunction), 
        Mojo.Util.bind(this, this._searchFunction)
      );
      
      // Setup validator
      new MDSS.OntologyValidator('term', search, 
        Mojo.Util.bind(this, this._getParameters), 
        Mojo.Util.bind(this, this._setField)
      );
    },
    
    _destroyModal : function()
    {
      if(this._modal.cfg != null){
        this._modal.destroy();
      }
      this._modal = null;
    },
    
    _displayFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }
  
      return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
    },
    
    _idFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return valueObject.getTermId();
      }
    
      return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
    }, 
    
    _getParameters : function()
    {
      return ['dss.vector.solutions.geo.GeoEntityDefinition', 'term'];
    },
      
    _searchFunction : function(request, value)
    {
      var params = this._getParameters();
      
      Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request, value, params);
    },    
  
    /**
     * Handler for new node request.
     */
    _addNodeHandler : function()
    {
      var geoHierarchyView = this._selectedNode.data.geoHierarchyView;
      
      var that = this;
      var request = new MDSS.Request({
        parentLabel:geoHierarchyView.getDisplayLabel(),
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var html = MDSS.util.removeScripts(html);
  
          var header = MDSS.localize('New_Universal_Located_In');
          header = header.replace(/\[parent\]/, this.parentLabel);
  
          var labelEl = "<h3>"+header+"</h3><hr />";
          html = labelEl + html;
          that._createModal(html);
         
          eval(executable);
        }
      });
  
  
      var controller = Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;
      controller.setCreateDefinitionListener(Mojo.Util.bind(this, this._createNodeListener));
      controller.setCancelCreateDefinitionListener(Mojo.Util.bind(this, this._cancelNodeListener));
      controller.newDefinition(request, geoHierarchyView.getGeoHierarchyId());
    },
  
    /**
     * Cancels an action to update a node.
     */
    _cancelNodeListener : function(params, a, b)
    {
      if(params['dto.isNew'] === 'true')
      {
        this._destroyModal();
      }
      else
      {
        var that = this;
        var request = new MDSS.Request({
          onSuccess: function(){
            that._destroyModal();
          }
        });
  
        return request;
      }
    },
  
    /**
     * Performs the DOM level cleanup after a hierarchy
     * has been deleted.
     * 
     * @param deleteAll denotes if the deleted entity should
     * be removed from all parent nodes (as opposed
     * to the current parent).
     * 
     * @param ids array of ids whose geo hierarchy nodes need to be moved under Earth
     * 
     * @param geoHierarchyId The id of the geohierarchy to be deleted.
     */
    _postDeleteCleanup : function(deleteAll, ids, geoHierarchyId)
    {
    	if(deleteAll)
    	{  	     
    	   function deleteNodeFromTree(id, earth)
    	   {
    	     // remove the node from the DOM
     	     var nodes = this._hierarchyTree.getNodesByProperty('geoHierarchyId', id);
     	     
     	     if(!nodes || nodes.length === 0)
     	     {
     	       // there is no DOM representation of the object so exit
     	       return;
     	     }
     	     
     	     Mojo.Iter.forEach(nodes, function(node) {
     	       // simplify things by moving the first node under earth. Anything else can be discarded.
     	     	 if(arguments[0] === node && earth !== null)
             {
               this._hierarchyTree.popNode(node);
               earth.appendChild(node);
             }
             else
             {
               var parent = node.parent;
               this._hierarchyTree.removeNode(node);
  
               parent.refresh();
             }
     	     }, this);
    	   }   	   
    	   
    	   // place the necessary children under Earth
 	 	   if(ids.length > 0)
	  	   {
	  	     var earth = this._hierarchyTree.getRoot().children[0];
	  	     for(var i=0; i<ids.length; i++)
	  	     {
	  	       deleteNodeFromTree.call(this, ids[i], earth);
	  	     }
 	 	     earth.refresh();
	  	   }
    	   
    	   // remove the geo hierarchy from the tree
    	   deleteNodeFromTree.call(this, geoHierarchyId, null);
    	}
    	else
    	{
        var parent = this._selectedNode.parent;
        this._hierarchyTree.removeNode(this._selectedNode);
        parent.refresh();
    	}
    },
  
    /**
     * Callback for when a user has chosen to delete a GeoHierarchy
     * and had to confirm whether to delete the object itself or
     * its relationship with the current parent.
     */
    _deleteAfterConfirmation : function(e, obj)
    {
    	var geoHierarchyView = obj.childHierarchy;
    	var geoHierarchyId = geoHierarchyView.getGeoHierarchyId();
  
    	var request = new MDSS.Request({
    	  // deleting the GeoEntity means all parent nodes containing
    	  // the child must delete the child node.
    	  deleteAll: obj.deleteHierarchy,
    	  modal:obj.modal,
    	  geoHierarchyId : geoHierarchyId,
    	  onSuccess: function(ids)
    	  {
    	  	this.modal.destroy();
  
    	  	_postDeleteCleanup(this.deleteAll, ids, this.geoHierarchyId);
    	  }
    	});
  
    	if(obj.deleteHierarchy)
    	{
    	  Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request, geoHierarchyId);
    	}
    	else
    	{
    	  Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteRelationship(request, geoHierarchyId, obj.parentId);
    	}
    },
  
    /**
     * Performs the delete operation.
     */
    _performDelete : function(destroyModal, geoHierarchyView)
    {
      // get its immediate parent
      var parent = this._selectedNode.parent;
      var parentGeoHierarchyView = parent.data.geoHierarchyView;
      var geoHierarchyId = geoHierarchyView.getGeoHierarchyId();
      var parentId = parentGeoHierarchyView != null ? parentGeoHierarchyView.getGeoHierarchyId() : null;
  
      var that = this;
      var request = new MDSS.Request({
        destroyModal:destroyModal,
        childHierarchy : geoHierarchyView,
        parentId : parentId,
        onSuccess: function(ids){
  
          if(this.destroyModal)
          {
            that._modal.destroy();
          }
  
          that._postDeleteCleanup(true, ids, this.childHierarchy.getGeoHierarchyId());
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
    	  	YAHOO.util.Dom.setAttribute(delEntity, 'value', MDSS.localize('Delete_Universal'));
    	  	YAHOO.util.Event.on(delEntity, 'click', Mojo.Util.bind(that, that._deleteAfterConfirmation), delEntityObj);
          lowerDiv.appendChild(delEntity);
  
          var delRelObj = {
            deleteHierarchy:false,
            childHierarchy:this.childHierarchy,
            parentId:this.parentId,
            modal:modal
          }
    	  	var delRel = document.createElement('input');
    	  	YAHOO.util.Dom.setAttribute(delRel, 'type', 'button');
    	  	YAHOO.util.Dom.setAttribute(delRel, 'value', MDSS.localize('Delete_Relationship'));
    	  	YAHOO.util.Event.on(delRel, 'click', Mojo.Util.bind(that._deleteAfterConfirmation), delRelObj);
    	  	lowerDiv.appendChild(delRel);
  
    	  	var wrapperDiv = document.createElement('div');
    	  	wrapperDiv.appendChild(upperDiv);
    	  	wrapperDiv.appendChild(lowerDiv);
  
    	  	modal.bringToTop();
    	  	modal.setBody(wrapperDiv);
          modal.render(document.body);
        }
      });
  
      if(parent == null || parentGeoHierarchyView == null)
      {
        Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request, geoHierarchyId);
      }
      else
      {
        var parentGeoHierarchyId = parentGeoHierarchyView.getGeoHierarchyId();
        Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmDeleteHierarchy(request, geoHierarchyId, parentGeoHierarchyId);
      }
    },
  
    /**
     * Handler for edit node request.
     */
    _editNodeHandler : function()
    {
      var geoHierarchyView = this._selectedNode.data.geoHierarchyView;
  
      var that = this;
      var request = new MDSS.Request({
        displayLabel: geoHierarchyView.getDisplayLabel(),
        onSuccess: function(html){
          var executable = MDSS.util.extractScripts(html);
          var html = MDSS.util.removeScripts(html);
  
          var labelEl = "<h3>"+this.displayLabel+"</h3><hr />";
          html = labelEl + html;
          that._createModal(html, false);
          
          eval(executable);
        }
      });
  
  
      var controller = Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;
      controller.setUpdateDefinitionListener(Mojo.Util.bind(this, this._updateNodeListener));
      controller.setCancelUpdateDefinitionListener(Mojo.Util.bind(this, this._cancelNodeListener));
      controller.editDefinition(request, geoHierarchyView.getGeoHierarchyId());
    },
  
    /**
     * Deletes the selected node from the tree.
     */
    _deleteNodeHandler : function()
    {
      var geoHierarchyView = this._selectedNode.data.geoHierarchyView;
      this._performDelete(false, geoHierarchyView);
    },
  
    /**
     * Event handler for a triggered context menu. This method
     * cancels the action if the menu was not triggered on a node.
     */
    _nodeMenuSelect : function()
    {
      var oTarget = this._menu.contextEventTarget;
  
      var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");
      if (htmlNode) {
        this._selectedNode = this._hierarchyTree.getNodeByElement(htmlNode);
      }
      else {
        this.cancel();
      }
    },
  
    /**
     * Loads child nodes dynamically
     */
    _dynamicLoad : function(parentNode, fnLoadComplete)
    {
    	var that = this;
      // request to fetch children
      var request = new MDSS.Request({
        onSuccess : function(query){
  
          var childNodes = query.getResultSet();
          Mojo.Iter.forEach(childNodes, function(view)
          {
          	var node = this._createNode(view, parentNode);
          }, that);
  
          fnLoadComplete();
          parentNode.refresh();
        }
      });
      var geoHierarchyView = parentNode.data.geoHierarchyView;
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getOrderedChildren(request, geoHierarchyView.getGeoHierarchyId());
    },
    
    _addChildToParent : function(e, obj)
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
              var geoHierarchyView = ddThis.node.data.geoHierarchyView;
              childNode.data.geoHierarchyView = geoHierarchyView;
              childNode.data.geoHierarchyId = ddThis.node.data.geoHierarchyId;
            }
          }
  
          destNode.expanded = false; // force re-expansion
          destNode.expand();
        }
      });
  
      var childEl = document.getElementById(obj.references.childId);
      var childNode = this._hierarchyTree.getNodeByElement(childEl);
      
      var parentEl = document.getElementById(obj.references.parentId);
      var parentNode = this._hierarchyTree.getNodeByElement(parentEl);
  
      var childGeoHierarchyView = childNode.data.geoHierarchyView;
      var parentGeoHierarchyView = parentNode.data.geoHierarchyView;
  
      var childId = childGeoHierarchyView.getGeoHierarchyId();
      var parentId = parentGeoHierarchyView.getGeoHierarchyId();
  
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.applyExistingWithParent(request, childId, parentId, obj.clone);
  
      obj.references.modal.destroy();
    },
    
    _handleExport : function(e)
    {
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }
    },
  
    /**
     * Handler for the drag/drop operation. The this
     * reference is set to the YAHOO.util.DDNodeProxy instance.
     */
    _dragDropHandler : function(source, id)
    {
    	var that = this;
    	// create popup asking if this is for a copy operation
    	var request = new MDSS.Request({
    	  references: {childId:source.id, parentId:id, ddThis:source},
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
    	  	YAHOO.util.Dom.setAttribute(yes, 'value', MDSS.localize('Choice_Yes'));
    	  	YAHOO.util.Event.on(yes, 'click', Mojo.Util.bind(that, that._addChildToParent), {clone:false, references:this.references}); // this == tree
          lowerDiv.appendChild(yes);
  
    	  	var no = document.createElement('input');
    	  	YAHOO.util.Dom.setAttribute(no, 'type', 'button');
    	  	YAHOO.util.Dom.setAttribute(no, 'value', MDSS.localize('Choice_No'));
    	  	YAHOO.util.Event.on(no, 'click', Mojo.Util.bind(that, that._addChildToParent), {clone:true, references:this.references}); // this == tree
    	  	lowerDiv.appendChild(no);
  
    	  	var wrapperDiv = document.createElement('div');
    	  	wrapperDiv.appendChild(upperDiv);
    	  	wrapperDiv.appendChild(lowerDiv);
  
    	  	modal.bringToTop();
    	  	modal.setBody(wrapperDiv);
          modal.render(document.body);
    	  }
    	});
  
  
  
      var childEl = document.getElementById(source.id);
      var childNode = this._hierarchyTree.getNodeByElement(childEl);
      var childGeoHierarchyView = childNode.data.geoHierarchyView;
      var parentGeoHierarchyView = childNode.parent.data.geoHierarchyView;
  
      var childId = childGeoHierarchyView.getGeoHierarchyId();
      var parentId = parentGeoHierarchyView.getGeoHierarchyId();
  
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmChangeParent(request, childId, parentId);
    },
    
    _exportEntitiesHandler : function()
    {
      var geoHierarchyView = this._selectedNode.data.geoHierarchyView;
      var id = geoHierarchyView.getGeoHierarchyId();
      this._waitForDownload();
      document.getElementById('exportIframe').src='dss.vector.solutions.geo.GeoEntityTypeController.export.mojo?hierarchyId='+id+'&includeGeoData=true';
    },
  
    _exportEntitiesNoGISHandler : function()
    {
      var geoHierarchyView = this._selectedNode.data.geoHierarchyView;
      var id = geoHierarchyView.getGeoHierarchyId();
      this._waitForDownload();
      document.getElementById('exportIframe').src='dss.vector.solutions.geo.GeoEntityTypeController.export.mojo?hierarchyId='+id+'&includeGeoData=false';
    },
    
    _waitForDownload : function()
    {
  	  this._clearDownload(true);
      MDSS.util.wait_for_ajax.show();
      var that = this;
      this._intervalId = setInterval(function(){
        var value = YAHOO.util.Cookie.get("downloadToken");
        if(value != null)
        {
          that._clearDownload(false);
        }
      }, 1000);
    },
    
    _handleExport : function(e)
    {
  	  this._clearDownload(false);
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }
    },
    
    _clearDownload : function(cookieOnly)
    {
      YAHOO.util.Cookie.remove("downloadToken");
    	if (!cookieOnly)
    	{
    	  MDSS.util.wait_for_ajax.hide();
    	  clearInterval(this._intervalId);    
    	}
    },
  
    /**
     * Renders the actual tree with the given root GeoEntity
     */
    
    _setupTree : function(geoHierarchyView)
    {
      var node = {type:"HTML", html:geoHierarchyView.getDisplayLabel()};
      
      var loadB = Mojo.Util.bind(this, this._dynamicLoad);
  
      this._hierarchyTree = new YAHOO.widget.TreeViewDD(this._treeId, [node], Mojo.Util.bind(this, this._dragDropHandler));
      this._hierarchyTree.setDynamicLoad(loadB);
      this._hierarchyTree.render();
      
      this._setupMenu();
      
      // map node to GeoEntity
      this._hierarchyTree.getRoot().children[0].data.geoHierarchyView = geoHierarchyView;
    },
    
    _setupMenu : function()
    {
    	var itemData = [];
  
      var exportEntities = new YAHOO.widget.ContextMenuItem(MDSS.localize('export_entities'));
      exportEntities.subscribe("click", Mojo.Util.bind(this, this._exportEntitiesHandler));
      itemData.push(exportEntities);
  
      var exportEntitiesMin = new YAHOO.widget.ContextMenuItem(MDSS.localize('export_entities_without_gis'));
      exportEntitiesMin.subscribe("click", Mojo.Util.bind(this, this._exportEntitiesNoGISHandler));
      itemData.push(exportEntitiesMin);
  
      var createMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Create'));
      createMenuItem.subscribe("click", Mojo.Util.bind(this, this._addNodeHandler));
      itemData.push(createMenuItem);
  
      var editMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Edit'));
      editMenuItem.subscribe("click", Mojo.Util.bind(this, this._editNodeHandler));
      itemData.push(editMenuItem);
  
      var deleteMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Delete'));
      deleteMenuItem.subscribe("click", Mojo.Util.bind(this, this._deleteNodeHandler));
      itemData.push(deleteMenuItem);
  
      this._menu = new YAHOO.widget.ContextMenu("treeMenu", {
        trigger:this._treeId,
        lazyload:true,
        itemdata: itemData
      });
  
      this._menu.subscribe("triggerContextMenu", Mojo.Util.bind(this, this._nodeMenuSelect));
  
      
      YAHOO.util.Event.on('exportIframe', 'load', Mojo.Util.bind(this, this._handleExport), null, this);
    },    
    
    _openBrowser : function(e)
    {
      var termId = document.getElementById('term').value;
      var selected = [];
      if(termId !== '')
      {
        selected.push(termId); 
      }
   
      if(this._sharedBrowser.isRendered())
      {
        this._sharedBrowser.reset();
        this._sharedBrowser.show();
        this._sharedBrowser.setSelection(selected); 
      }
      else
      {
        this._sharedBrowser.render();
        this._sharedBrowser.setSelection(selected); 
      }
    },
    
    _setField : function(selected)
    {
      var el = document.getElementById('term');
      var dEl = document.getElementById('termDisplay');
      if(selected.length > 0)
      {
        var sel = selected[0];
        el.value = this._idFunction(sel);
        dEl.value = this._displayFunction(sel);
      }
      else
      {
        el.value = '';
        dEl.value = '';
      }
    },
  
    /**
     * Initializes the tree by setting the GeoEntity with the
     * given id as first node under the root.
     */    
    render : function() {
    	var that = this;
    	var request = new MDSS.Request({
        onSuccess : function(geoHierarchyView){
          // build tree
          that._setupTree(geoHierarchyView);
        }
      });
      
      // Create the one instance of the OntologyBrowser that will
      // be shared by all BrowserRoots. This is done because only one
      // BrowserRoot can be edited at a time.
      this._sharedBrowser = new MDSS.OntologyBrowser(false, 'dss.vector.solutions.geo.GeoEntityDefinition', 'term');
      this._sharedBrowser.setHandler(this._setField);
      
      // Fetch the root node
      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request, this._rootId);
    }
	}
});