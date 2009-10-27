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
  
  var _uploadModal = null;
  
  // reference to the Ontology browser used to select the proper
  // term for a univeral
  var _sharedBrowser = null
  
  /**
   * Action to upload a template file.
   */
  function _uploadImport()
  {
    if(_uploadModal == null)
    {
      var formId = 'importUploadForm';
      var action = 'excelimport';
      

      var html = MDSS.Localized.File_Upload_Status+":<br />";
      html += "<iframe name='importIframe' id='importIframe' style='height:65px; width:350px; margin-bottom: 15px'></iframe>";
      html += "<form action='"+action+"' enctype='multipart/form-data' target='importIframe' id='"+formId+"' method='post'>";
      html += "<input type='hidden' name='parentGeoEntityId' id='parentGeoEntityId' value='' />";
      html += "<input type='hidden' name='excelType' id='excelType' value='dss.vector.solutions.export.GeoEntityExcelView' />";
      html += "<input type='file' name='importFile' id='importFile' /><br />";
      html += "<input type='submit' name='import' value='"+MDSS.Localized.Submit+"' />"
      html += "</form>";

      _uploadModal = new YAHOO.widget.Panel("uploadTemplateModal", {
        width:"400px",
        height: "400px",
        fixedcenter:true,
        close: true,
        draggable:false,
        zindex:8,
        modal:true,
        visible:true
      });

      // wrap content in divs
      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>'+MDSS.Localized.Upload_Template+'</h3><hr />';
      outer.appendChild(header);

      var contentDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);

      _uploadModal.subscribe('hide', function(){
        
        _selectedNode.collapse();
        _selectedNode.dynamicLoadComplete = false;
        _selectedNode.expanded = false;
        _selectedNode.expand();
      });
      _uploadModal.setBody(outer);
      _uploadModal.render(document.body);
      _uploadModal.bringToTop();

      YAHOO.util.Event.on(formId, 'submit', _uploadImportOnSubmit, null, this);
    }
    else
    {
      _uploadModal.show();
    }
  }
  
  function _uploadImportOnSubmit(e)
  {
    var input = document.getElementById('parentGeoEntityId');
    input.value = _getGeoEntityView(_selectedNode).getGeoEntityId();
    
    return true;
  }

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
    var geoId = params['dto.geoId'];
    var activatedVal = params['dto.activated'];
    var term = document.getElementById('term').value;
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
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);
    geoEntity.setTerm(term);

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
    var geConstructor = Mojo.Meta.findClass(type);
    var geoEntity = new geConstructor();

    _setGeoEntityAttributes(params, geoEntity);

    var request = new MDSS.Request({
      onSuccess : function(ids, geoEntity){

        // add the node directly if the children have already been dynamically loaded
        if(_selectedNode.dynamicLoadComplete)
        {
          var view = _copyEntityToView(geoEntity);
          var div = _createNodeDiv(view);

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
    view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());

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
        var view = _copyEntityToView(geoEntity);
      	var span = _createContentSpan(view);
      	div = div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/, '$1'+span+'$2');

      	// update selected node and all copies
        var nodeIds = _geoEntityIdToNodeIdMap[geoEntity.getId()];
        for(var i=0; i<nodeIds.length; i++)
        {
          var id = nodeIds[i];
          var el = document.getElementById(id);
          el.innerHTML = div;
        }

        // update mapping FIXME (needed?)
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

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>'+this.label+'</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        _modal.setBody(outer);
        
        YAHOO.util.Event.on('termBtn', 'click', _openBrowser);
        YAHOO.util.Event.on('termDisplay', 'click', _openBrowser);

        eval(executable);
      }
    });

    var controller = Mojo.Meta.findClass(type+"Controller");
    controller.setCreateListener(Mojo.Util.curry(_createNode, type));
    controller.newInstance(request);
  }
  
  function _setField(selected)
  {
    var el = document.getElementById('term');
    var dEl = document.getElementById('termDisplay');
    if(selected.length > 0)
    {
      var sel = selected[0];
      el.value = sel.getTermId();
      dEl.innerHTML = MDSS.OntologyBrowser.formatLabel(sel);
    }
    else
    {
      el.value = '';
      dEl.innerHTML = MDSS.Localized.no_value;
    }
  }
  
  /**
   * Opens the ontology browser to set the value of the
   * term attribute on the geo entity.
   */
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

  /**
   * Creates a modal to contain GeoEntity create/edit operations.
   */
  function _createModal(html, closeWin)
  {
    _modal = new YAHOO.widget.Panel("select",
      { width:"400px",
        height: "400px",
        fixedcenter:true,
        close: arguments.length > 1 ? closeWin : true,
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
   * Changes the currently selected node to the given type.
   */
  function _changeType(e, type)
  {
    var geoEntityView = _getGeoEntityView(_selectedNode);

    var request = new MDSS.Request({
      oldId : geoEntityView.getGeoEntityId(),
      onSuccess : function(geoEntity){

      	var div = _selectedNode.getContentEl().innerHTML;
        var view = _copyEntityToView(geoEntity);
      	var span = _createContentSpan(view);
      	div = div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/, '$1'+span+'$2');

        var newId = view.getGeoEntityId();

      	// update selected node and all copies
        var nodeIds = _geoEntityIdToNodeIdMap[this.oldId];
        for(var i=0; i<nodeIds.length; i++)
        {
          var id = nodeIds[i];
          var el = document.getElementById(id);
          el.innerHTML = div;
        }

        // delete the old cached object
        delete _geoEntityViewCache[this.oldId];

        // copy node mappings to new id
  	    var nodeIds = _geoEntityIdToNodeIdMap[this.oldId];
  	    delete _geoEntityIdToNodeIdMap[this.oldId];
  	    _geoEntityIdToNodeIdMap[newId] = nodeIds;
  	    for(var i=0; i<nodeIds.length; i++)
  	    {
  	      var nodeId = nodeIds[i];
  	      _nodeToGeoEntityMap[nodeId] = newId;
  	    }

        _setMapping(_selectedNode, view);

        _modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.changeUniversalType(request, geoEntityView.getGeoEntityId(), type);
  }

  function _changeRootGeoEntity()
  {
    var request = new MDSS.Request({
      onSuccess : function(){
        // TODO highlight the node
      }
    });

    var geoEntityView = _getGeoEntityView(_selectedNode);
    Mojo.$.dss.vector.solutions.MDSSUser.changeRootGeoEntity(request, geoEntityView.getGeoEntityId());
  }

  /**
   * Handler to change a node's type.
   */
  function _changeTypeHandler()
  {
    var request = new MDSS.Request({
      onSuccess : function(types){

        var ul = document.createElement('ul');
        YAHOO.util.Dom.addClass(ul, 'selectableList');
        YAHOO.util.Event.on(ul, 'mouseover', function(e, obj){

          var li = e.target;
          var ul = e.currentTarget;
          if(li.nodeName !== 'LI')
          {
            return;
          }

          // clear all lis of their current class
          var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
          for(var i=0; i<lis.length; i++)
          {
            YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
          }

          YAHOO.util.Dom.addClass(e.target, 'currentSelection');
        });

        for(var i=0; i<types.length; i++)
        {
          var type = types[i];

          var li = document.createElement('li');
          li.innerHTML = MDSS.GeoTreeSelectables.types[type].label;

          YAHOO.util.Event.on(li, 'click', _changeType, type, this);

          ul.appendChild(li);
        }

        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>'+MDSS.Localized.Change_Type+'</h3><hr />';
        outer.appendChild(header);

        var listDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(listDiv, 'innerContentModal');
        listDiv.appendChild(ul);
        outer.appendChild(listDiv);

        _createModal(outer);
      }
    });

    var geoEntityView = _getGeoEntityView(_selectedNode);
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getCompatibleTypes(request, geoEntityView.getGeoEntityId());
  }

  /**
   * Handler for new node request.
   */
  function _addNodeHandler()
  {
    // create ul to hold labels
    var ulRaw = document.createElement('ul');
    ul = new YAHOO.util.Element(ulRaw);
    ul.addClass('selectableList');
    ul.on('mouseover', function(e, obj){

      var li = e.target;
      var ul = e.currentTarget;
      if(li.nodeName !== 'LI')
      {
        return;
      }

      // clear all lis of their current class
      var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
      for(var i=0; i<lis.length; i++)
      {
        YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
      }

      YAHOO.util.Dom.addClass(e.target, 'currentSelection');
    });

    // add all labels
    var geoEntityView = _getGeoEntityView(_selectedNode);
    var type = geoEntityView.getEntityType();

    var allowedTypes = [];
    function collectSubtypes(types, parent)
    {
      var allowedChildren = MDSS.GeoTreeSelectables.types[parent].children;
      for(var i=0; i<allowedChildren.length; i++)
      {
      	var childType = allowedChildren[i];
      	types.push(childType);

      	collectSubtypes(types, childType);
      }
    }

    collectSubtypes(allowedTypes, type);
    
    var set = new MDSS.Set();
    set.addAll(allowedTypes);
    var ordered = set.values();
    ordered.sort();

    for(var i=0; i<ordered.length; i++)
    {
      var allowedType = ordered[i];

      var liRaw = document.createElement('li');
      var li = new YAHOO.util.Element(liRaw);

      var displayLabel = MDSS.GeoTreeSelectables.types[allowedType].label;

      li.on('click', _createTypeSelected, {
        type : allowedType,
        label : displayLabel
      });

      liRaw.innerHTML = displayLabel;

      ul.appendChild(liRaw);
    }

    // wrap content in divs
    var outer = document.createElement('div');

    var header = document.createElement('div');
    header.innerHTML = '<h3>'+MDSS.Localized.Select_Universal_Type+'</h3><hr />';
    outer.appendChild(header);

    var listDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(listDiv, 'innerContentModal');
    listDiv.appendChild(ulRaw);
    outer.appendChild(listDiv);

    _createModal(outer);
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
   * case where a child has multiple parents.
   */
  function _performDelete(destroyModal, geoEntity)
  {
    // get its immediate parent
    var parent = _selectedNode.parent;
    var parentGeoEntityView = _getGeoEntityView(parent);

    var parentId = parentGeoEntityView != null ? parentGeoEntityView.getGeoEntityId() : null;
    var request = new MDSS.Request({
      destroyModal:destroyModal,
      childEntity : geoEntity,
      parentId : parentId,
      onSuccess: function(){

        if(this.destroyModal)
        {
          _modal.destroy();
        }

        // the entity is being deleted
        _postDeleteCleanup(true);
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
  	  	YAHOO.util.Dom.setAttribute(delEntity, 'value', MDSS.Localized.Delete_Entity);
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


    if(parent == null || parentGeoEntityView == null)
    {
      geoEntity.remove(request);
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
    var geoEntityView = _getGeoEntityView(_selectedNode);
    var typeToEdit = geoEntityView.getEntityType();

    var request = new MDSS.Request({
      typeToEdit: typeToEdit,
      onSuccess: function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>'+MDSS.GeoTreeSelectables.types[this.typeToEdit].label+'</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        _createModal(outer, false);
        
        YAHOO.util.Event.on('termBtn', 'click', _openBrowser);
        YAHOO.util.Event.on('termDisplay', 'click', _openBrowser);

        eval(executable);
      }
    });

    var controller = Mojo.Meta.findClass(typeToEdit+"Controller");
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
      this.bringToTop();
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
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, geoEntityView.getGeoEntityId(), '');
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
  	var entityType = geoEntityView.getEntityType();
  	var type = MDSS.GeoTreeSelectables.types[entityType];
  	var label = type != null ? type.label : '';
    return "<span title='"+geoEntityView.getGeoId()+"'>"+geoEntityView.getEntityName()+" ("+label+")</span>";
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
    if(Mojo.Util.isFunction(selectCallback))
    {
      _selectCallback = selectCallback;
      var selectMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Select);
      selectMenuItem.subscribe("click", _customSelectHandler);
      itemData.push(selectMenuItem);
    }
    
    var importMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Import_Button);
    importMenuItem.subscribe("click", _uploadImport);
    itemData.push(importMenuItem);

    var createMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Create);
    createMenuItem.subscribe("click", _addNodeHandler);
    itemData.push(createMenuItem);

    var changeTypeMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.Localized.Change_Type);
    changeTypeMenuItem.subscribe("click", _changeTypeHandler);
    itemData.push(changeTypeMenuItem);

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
  function _initializeTree(treeId, selectCallback) {

    var request = new MDSS.Request({
      onSuccess : function(geoEntity){
        // build tree
        _renderTree(treeId, geoEntity, selectCallback);
      }
    });
    
      // Create the one instance of the OntologyBrowser that will
      // be shared by all BrowserRoots. This is done because only one
      // BrowserRoot can be edited at a time.
      _sharedBrowser = new MDSS.OntologyBrowser(false, 'dss.vector.solutions.geo.generated.GeoEntity', 'term');
      _sharedBrowser.setHandler(_setField);

    // Fetch the root node
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, MDSS.GeoEntityTreeRootId);
  }

  // return all public methods/properties
  return {
    initializeTree : _initializeTree
    //destroyAll : _destroyAll
  };
})();