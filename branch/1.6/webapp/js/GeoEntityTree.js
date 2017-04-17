/**
 * GeoEntityTree object manages the GeoEntity tree operations (e.g., drag and
 * drop, create node, delete node).
 */
Mojo.Meta.newClass('MDSS.GeoEntityTree', {
  Instance : {
    initialize : function(treeId, selectCallback, rootId)
    {
      this._treeId = treeId;

      this._selectCallback = selectCallback;

      this._rootId = rootId;

      // The selected node in tree
      this._selectedNode = null;

      // The tree for GeoEntities
      this._geoTree = null;

      // The menu for CRUD operations
      this._menu = null;

      // reference to modal for node create/edit
      this._modal = null;

      this._uploadModal = null;

      // Map between universal type and Browser
      this._browsers = {};
      this._currentBrowser = null;
      this._currentType = null;

      // Selection validator for the tree
      this._validator = null;

      // ID of the download interval timer
      this._intervalId = null;
    },

    /**
     * Action to upload a template file.
     */
    _uploadImport : function()
    {
      if (this._uploadModal == null)
      {
        var formId = 'importUploadForm';
        var action = 'excelimport';

        var html = MDSS.localize('File_Upload_Status') + ":<br />";
        html += "<iframe name='importIframe' id='importIframe' style='height:200px; width:350px; margin-bottom: 15px'></iframe>";
        html += "<form action='" + action + "' enctype='multipart/form-data' target='importIframe' id='" + formId + "' method='post'>";
        html += "<input type='hidden' name='parentGeoEntityId' id='parentGeoEntityId' value='' />";
        html += "<input type='hidden' name='excelType' id='excelType' value='dss.vector.solutions.export.GeoEntityExcelView' />";
        html += "<input type='file' name='importFile' id='importFile' /><br />";
        html += "<input type='submit' name='import' value='" + MDSS.localize('Submit') + "' />"
        html += "</form>";

        this._uploadModal = new YAHOO.widget.Panel("uploadTemplateModal", {
          width : "400px",
          height : "400px",
          fixedcenter : true,
          close : true,
          draggable : false,
          zindex : 8,
          modal : true,
          visible : true
        });

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>' + MDSS.localize('Upload_Template') + '</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        var that = this;
        this._uploadModal.subscribe('hide', function()
        {
          that._uploadClearStatus();

          that._selectedNode.collapse();
          that._selectedNode.dynamicLoadComplete = false;
          that._selectedNode.expanded = false;
          that._selectedNode.expand();
        });
        this._uploadModal.setBody(outer);
        this._uploadModal.render(document.body);
        this._uploadModal.bringToTop();

        
        YAHOO.util.Event.on('importFile', 'change', this._uploadClearStatus, null, this);
        YAHOO.util.Event.on(formId, 'submit', this._uploadImportOnSubmit, null, this);
      }
      else
      {
        this._uploadModal.show();
      }
    },
    
    _uploadClearStatus : function(e)
    {
      // Clear out the status list of any existing imports
      var myIFrame = document.getElementById('importIframe');

      if (myIFrame != null)
      {
        myIFrame.contentWindow.document.body.innerHTML = "";
      }    	
    },

    _uploadImportOnSubmit : function(e)
    {
      var input = document.getElementById('parentGeoEntityId');
      input.value = this._selectedNode.data.geoEntityView.getGeoEntityId();
      
      this._uploadClearStatus();
      
      return true;
    },

    /**
     * Removes everything from the current Tree
     */
    _destroyAll : function()
    {
      this._selectedNode = null;
      this._modal = null;
      this._selectCallback = null;

      // this.cfg of the ContextMenu is null which throws an error.
      // TODO find official fix for this
      try
      {
        this._menu.destroy();
      } catch (e)
      {
        this._menu = null;
      }

      this._geoTree.destroy();
      this._geoTree = null;
    },

    _setGeoEntityAttributes : function(params, geoEntity)
    {
      var entityLabel = params['dto.entityLabel'];
      var geoId = params['dto.geoId'];
      var activatedVal = params['dto.activated'];
      var activated = (activatedVal === "true") ? true : false;
      var geoData = params['dto.geoData'];
      var term = document.getElementById('term');

      geoEntity.getEntityLabel().setLocalizedValue(entityLabel);
      geoEntity.setGeoId(geoId);
      geoEntity.setActivated(activated);
      geoEntity.setGeoData(geoData);

      if (term != null)
      {
        geoEntity.setTerm(term.value);
      }
    },

    _createNode : function(ids, geoEntity)
    {
      // add the node directly if the children have already been dynamically
      // loaded
      if (this._selectedNode.dynamicLoadComplete)
      {
        var view = this._copyEntityToView(geoEntity);
        var div = this._createNodeDiv(view, true);

        // add the node to all parent nodes
        var parentGeoEntityView = this._selectedNode.data.geoEntityView;
        var nodes = this._geoTree.getNodesByProperty('geoEntityId', parentGeoEntityView.getGeoEntityId());

        Mojo.Iter.forEach(nodes, function(parent)
        {
          if (parent.dynamicLoadComplete)
          {
            var data = {
              geoEntityView : view,
              geoEntityId : view.getGeoEntityId()
            };
            var node = new YAHOO.widget.HTMLNode({
              html : div,
              data : data
            }, parent);

            // only re-expand the selected node or the parent if its already
            // expanded
            if (parent.getElId() === this._selectedNode.getElId() || parent.expanded === true)
            {
              parent.expanded = false; // force a re-expansions
              parent.refresh();
            }
          } else if (parent === this._selectedNode)
          {
            parent.expand();
          }
        }, this);
      }

      this._selectedNode.expand();

      this._destroyModal();
    },

    /**
     * Creates a new node.
     */
    _createNodeListener : function(type, params, action)
    {
      var geConstructor = Mojo.Meta.findClass(type);
      var geoEntity = new geConstructor();

      var parentId = this._selectedNode.data.geoEntityId;
      params['parentId'] = parentId;

      this._setGeoEntityAttributes(params, geoEntity);

      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(ids, geoEntity)
        {
          that._createNode(ids, geoEntity);
        }
      });

      var parentGeoEntityView = this._selectedNode.data.geoEntityView;
      geoEntity.applyWithParent(request, parentGeoEntityView.getGeoEntityId(), false, null);
    },

    _copyEntityToView : function(geoEntity)
    {
      var view = new Mojo.$.dss.vector.solutions.geo.GeoEntityView();

      var label = geoEntity.getEntityLabel();

      view.setGeoEntityId(geoEntity.getId());
      view.setGeoId(geoEntity.getGeoId());
      view.setActivated(geoEntity.getActivated());
      view.setEntityLabel(label.getLocalizedValue());
      view.setEntityType(geoEntity.getType());
      view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());

      return view;
    },

    _performUpdate2 : function(ids, geoEntity)
    {
      // replace the contents (active status will be modified in
      // another operation).
      var div = this._selectedNode.getContentEl().innerHTML;
      var view = this._copyEntityToView(geoEntity);
      var span = this._createContentSpan(view, true);
      div = div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/, '$1' + span + '$2');

      // update selected node and all copies
      var nodes = this._geoTree.getNodesByProperty('geoEntityId', geoEntity.getId());
      Mojo.Iter.forEach(nodes, function(node)
      {
        node.getContentEl().innerHTML = div;
      }, this);

      this._selectedNode.data.geoEntityView = view;
      this._selectedNode.data.geoEntityId = view.getGeoEntityId();

      this._updateActivatedOnNodes(ids, geoEntity.getActivated());

      this._destroyModal();
    },

    _performUpdate : function(params, geoEntity)
    {
      this._setGeoEntityAttributes(params, geoEntity);

      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(ids, geoEntity)
        {
          that._performUpdate2(ids, geoEntity);
        }
      });

      geoEntity.updateFromTree(request);
    },

    /**
     * Updates a node.
     */
    _updateNodeListener : function(params, actions)
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;

      var that = this;
      var request = new MDSS.Request({
        params : params,
        onSuccess : function(geoEntity)
        {
          that._performUpdate(this.params, geoEntity);
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, geoEntityView.getGeoEntityId());
    },

    _setCurrentBrowser : function(type)
    {
      // set the current browser for the type
      if (!this._browsers[type])
      {
        var browser = new MDSS.OntologyBrowser(false, true, type);
        browser.setHandler(this._setField);
        this._browsers[type] = browser;
      }

      this._currentBrowser = this._browsers[type];
      this._currentType = type;
    },

    _createTypeSelected2 : function(html, label)
    {
      var executable = MDSS.util.extractScripts(html);
      html = MDSS.util.removeScripts(html);

      // wrap content in divs
      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>' + label + '</h3><hr />';
      outer.appendChild(header);

      var contentDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);

      this._modal.setBody(outer);

      // FIXME
      var termDisplay = document.getElementById('termDisplay')

      if (termDisplay != null)
      {
        var disabled = termDisplay.disabled;

        if (disabled == false)
        {
          YAHOO.util.Event.on('termBtn', 'click', this._openBrowser, null, this);
        }

        var search = new MDSS.GenericSearch('termDisplay', 'term', Mojo.Util.bind(this, this._displayFunction), Mojo.Util.bind(this, this._displayFunction), Mojo.Util.bind(this, this._idFunction), Mojo.Util.bind(this, this._searchFunction));

        // Setup validator
        new MDSS.OntologyValidator('term', search, Mojo.Util.bind(this, this._getParameters), Mojo.Util.bind(this, this._setField));
      }
      eval(executable);
    },

    /**
     * Event handler for when a GeoEntity type is selected to create a new
     * instance of that type.
     */
    _createTypeSelected : function(e, obj)
    {
      var type = obj.type;
      this._setCurrentBrowser(type);

      var that = this;
      var request = new MDSS.Request({
        label : obj.label,
        onSuccess : function(html)
        {
          that._createTypeSelected2(html, this.label);
        }
      });

      var controller = Mojo.Meta.findClass(type + "Controller");
      controller.setCreateListener(Mojo.Util.bind(this, this._createNodeListener, type));
      controller.setCancelListener(Mojo.Util.bind(this, this._cancelNodeListener));
      controller.newInstance(request);
    },

    _setField : function(selected)
    {
      var el = document.getElementById('term');
      var dEl = document.getElementById('termDisplay');
      if (selected.length > 0)
      {
        var sel = selected[0];
        el.value = this._idFunction(sel);
        dEl.value = this._displayFunction(sel);
      } else
      {
        el.value = '';
        dEl.value = '';
      }
    },

    /**
     * Opens the ontology browser to set the value of the term attribute on the
     * geo entity.
     */
    _openBrowser : function(e)
    {
      var termId = document.getElementById('term').value;
      var selected = [];
      if (termId !== '')
      {
        selected.push(termId);
      }

      if (this._currentBrowser.isRendered())
      {
        this._currentBrowser.reset();
        this._currentBrowser.show();
        this._currentBrowser.setSelection(selected);
      } else
      {
        this._currentBrowser.render();
        this._currentBrowser.setSelection(selected);
      }
    },

    /**
     * Creates a modal to contain GeoEntity create/edit operations.
     */
    _createModal : function(html, closeWin)
    {
      this._modal = new YAHOO.widget.Panel(Mojo.Util.generateId() + '_modal', {
        width : "400px",
        height : "400px",
        fixedcenter : true,
        close : closeWin || false,
        draggable : false,
        zindex : 4,
        modal : true,
        visible : true
      });

      if (closeWin)
      {
        this._modal.subscribe('hide', Mojo.Util.bind(this, this._destroyModal));
      }

      this._modal.setBody(html);
      this._modal.render(document.body);
    },

    _destroyModal : function()
    {
      if (this._modal.cfg != null)
      {
        this._modal.destroy();
      }
      this._modal = null;
    },

    _changeType2 : function(oldId, view)
    {
      var div = this._selectedNode.getContentEl().innerHTML;
      var span = this._createContentSpan(view);
      div = div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/, '$1' + span + '$2');

      var newId = view.getGeoEntityId();

      // update selected node and all copies
      var nodes = that._geoTree.getNodesByProperty('geoEntityId', oldId);
      Mojo.Iter.forEach(nodes, function(node)
      {
        node.getContentEl().innerHTML = div;
      }, this);

      // copy node mappings to new id
      var nodes = that._geoTree.getNodesByProperty('geoEntityId', parentId);
      Mojo.Iter.forEach(nodes, function(node)
      {
        node.data.geoEntityId = newId;
      }, this);

      this._selectedNode.data.geoEntityView = view;
      this._selectedNode.data.geoEntityId = view.getGeoEntityId();

      this._destroyModal();
    },

    /**
     * Changes the currently selected node to the given type.
     */
    _changeType : function(e, type)
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;

      var request = new MDSS.Request({
        onSuccess : function(view)
        {
          that._changeType2(geoEntityView.getGeoEntityId(), view);
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.changeUniversalType(request, geoEntityView.getGeoEntityId(), type);
    },

    _changeRootGeoEntity : function()
    {
      var request = new MDSS.Request({
        onSuccess : function()
        {
          // TODO highlight the node
        }
      });

      var geoEntityView = this._selectedNode.data.geoEntityView;
      Mojo.$.dss.vector.solutions.MDSSUser.changeRootGeoEntity(request, geoEntityView.getGeoEntityId());
    },

    /**
     * Handler to change a node's type.
     */
    _changeTypeHandler : function()
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(types)
        {

          var ul = document.createElement('ul');
          YAHOO.util.Dom.addClass(ul, 'selectableList');
          YAHOO.util.Event.on(ul, 'mouseover', function(e, obj)
          {

            var li = e.target;
            var ul = e.currentTarget;
            if (li.nodeName !== 'LI')
            {
              return;
            }

            // clear all lis of their current class
            var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
            for ( var i = 0; i < lis.length; i++)
            {
              YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
            }

            YAHOO.util.Dom.addClass(e.target, 'currentSelection');
          });

          for ( var i = 0; i < types.length; i++)
          {
            var type = types[i];

            var li = document.createElement('li');
            li.innerHTML = MDSS.GeoTreeSelectables.types[type].label;

            YAHOO.util.Event.on(li, 'click', that._changeType, type, that);

            ul.appendChild(li);
          }

          var outer = document.createElement('div');

          var header = document.createElement('div');
          header.innerHTML = '<h3>' + MDSS.localize('Change_Type') + '</h3><hr />';
          outer.appendChild(header);

          var listDiv = document.createElement('div');
          YAHOO.util.Dom.addClass(listDiv, 'innerContentModal');
          listDiv.appendChild(ul);
          outer.appendChild(listDiv);

          that._createModal(outer, false);
        }
      });

      var geoEntityView = this._selectedNode.data.geoEntityView;
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getCompatibleTypes(request, geoEntityView.getGeoEntityId());
    },

    /**
     * Handler for new node request.
     */
    _addNodeHandler : function()
    {
      // create ul to hold labels
      var ulRaw = document.createElement('ul');
      ul = new YAHOO.util.Element(ulRaw);
      ul.addClass('selectableList');
      ul.on('mouseover', function(e, obj)
      {

        var li = e.target;
        var ul = e.currentTarget;
        if (li.nodeName !== 'LI')
        {
          return;
        }

        // clear all lis of their current class
        var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
        for ( var i = 0; i < lis.length; i++)
        {
          YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
        }

        YAHOO.util.Dom.addClass(e.target, 'currentSelection');
      });

      // add all labels
      var geoEntityView = this._selectedNode.data.geoEntityView;
      var type = geoEntityView.getEntityType();

      var allowedTypes = [];
      function collectSubtypes(types, parent)
      {
        var allowedChildren = MDSS.GeoTreeSelectables.types[parent].children;
        for ( var i = 0; i < allowedChildren.length; i++)
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

      for ( var i = 0; i < ordered.length; i++)
      {
        var allowedType = ordered[i];

        var liRaw = document.createElement('li');
        var li = new YAHOO.util.Element(liRaw);

        var displayLabel = MDSS.GeoTreeSelectables.types[allowedType].label;

        li.on('click', this._createTypeSelected, {
          type : allowedType,
          label : displayLabel
        }, this);

        liRaw.innerHTML = displayLabel;

        ul.appendChild(liRaw);
      }

      // wrap content in divs
      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>' + MDSS.localize('Select_Universal_Type') + '</h3><hr />';
      outer.appendChild(header);

      var listDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(listDiv, 'innerContentModal');
      listDiv.appendChild(ulRaw);
      outer.appendChild(listDiv);

      this._createModal(outer, true);
    },

    /**
     * Cancels an action to update a node.
     */
    _cancelNodeListener : function(params, a, b)
    {
      if (params['dto.isNew'] === 'true')
      {
        this._destroyModal();
      } else
      {
        var that = this;
        var request = new MDSS.Request({
          onSuccess : function()
          {
            that._destroyModal();
          }
        });

        var geoEntityView = this._selectedNode.data.geoEntityView;
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.unlock(request, geoEntityView.getGeoEntityId());
      }
    },

    /**
     * Performs the DOM level cleanup after an entity has been deleted. The
     * deleteAll param denotes if the deleted entity should be removed from all
     * parent nodes (as opposed to the current parent).
     */
    _postDeleteCleanup : function(deleteAll)
    {
      if (deleteAll)
      {
        var geoEntityView = this._selectedNode.data.geoEntityView;
        var nodes = this._geoTree.getNodesByProperty('geoEntityId', geoEntityView.getGeoEntityId());

        for ( var i = nodes.length - 1; i >= 0; i--)
        {
          var node = nodes[i];
          var parent = node.parent;
          this._geoTree.removeNode(node);

          parent.refresh();
        }
      } else
      {
        var parent = this._selectedNode.parent;
        this._geoTree.removeNode(this._selectedNode);
        parent.refresh();
      }
    },

    /**
     * Callback for when a user has chosen to delete a GeoEntity and had to
     * confirm whether to delete the entity itself or its relationship with the
     * current parent.
     */
    _deleteAfterConfirmation : function(e, obj)
    {
      var geoEntity = obj.childEntity;
      var that = this;
      var request = new MDSS.Request({

        // deleting the GeoEntity means all parent nodes containing
        // the child must delete the child node.
        deleteAll : obj.deleteEntity,
        modal : obj.modal,
        onSuccess : function()
        {
          this.modal.destroy();

          that._postDeleteCleanup(this.deleteAll);
        }
      });

      if (obj.deleteEntity)
      {
        geoEntity.deleteEntity(request);
      } else
      {
        geoEntity.deleteRelationship(request, obj.parentId);
      }
    },

    /**
     * Performs a delete request, and handles the case where a child has
     * multiple parents.
     */
    _performDelete : function(geoEntity)
    {
      // get its immediate parent
      var parent = this._selectedNode.parent;
      var parentGeoEntityView = parent.data.geoEntityView;

      var parentId = parentGeoEntityView != null ? parentGeoEntityView.getGeoEntityId() : null;
      var that = this;
      var request = new MDSS.Request({
        childEntity : geoEntity,
        parentId : parentId,
        onSuccess : function()
        {

          if (that._modal != null)
          {
            that._destroyModal();
          }

          // the entity is being deleted
          that._postDeleteCleanup(true);
        },
        onConfirmDeleteEntityException : function(e)
        {

          var modal = new YAHOO.widget.Panel("confirmDelete", {
            fixedcenter : true,
            width : '300px',
            visible : true,
            draggable : false,
            zindex : 8000,
            modal : true
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
            deleteEntity : true,
            childEntity : this.childEntity,
            parentId : this.parentId,
            modal : modal
          }
          var delEntity = document.createElement('input');
          YAHOO.util.Dom.setAttribute(delEntity, 'type', 'button');
          YAHOO.util.Dom.setAttribute(delEntity, 'value', MDSS.localize('Delete_Entity'));
          YAHOO.util.Event.on(delEntity, 'click', that._deleteAfterConfirmation, delEntityObj, that);
          lowerDiv.appendChild(delEntity);

          var delRelObj = {
            deleteEntity : false,
            childEntity : this.childEntity,
            parentId : this.parentId,
            modal : modal
          }
          var delRel = document.createElement('input');
          YAHOO.util.Dom.setAttribute(delRel, 'type', 'button');
          YAHOO.util.Dom.setAttribute(delRel, 'value', MDSS.localize('Delete_Relationship'));
          YAHOO.util.Event.on(delRel, 'click', that._deleteAfterConfirmation, delRelObj, that);
          lowerDiv.appendChild(delRel);

          var wrapperDiv = document.createElement('div');
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);

          modal.bringToTop();
          modal.setBody(wrapperDiv);
          modal.render(document.body);
        }
      });

      if (parent == null || parentGeoEntityView == null)
      {
        geoEntity.remove(request);
      } else
      {
        geoEntity.confirmDeleteEntity(request, parentGeoEntityView.getGeoEntityId());
      }
    },

    /**
     * Deletes the current node from the tree.
     */
    _deleteNodeListener : function()
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;

      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(geoEntity)
        {
          that._performDelete(geoEntity);
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, geoEntityView.getGeoEntityId());
    },

    /**
     * Handler for edit node request.
     */
    _editNodeHandler : function()
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;
      var typeToEdit = geoEntityView.getEntityType();
      this._setCurrentBrowser(typeToEdit);

      var that = this;
      var request = new MDSS.Request({
        typeToEdit : typeToEdit,
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var html = MDSS.util.removeScripts(html);

          // wrap content in divs
          var outer = document.createElement('div');

          var header = document.createElement('div');
          header.innerHTML = '<h3>' + MDSS.GeoTreeSelectables.types[this.typeToEdit].label + '</h3><hr />';
          outer.appendChild(header);

          var contentDiv = document.createElement('div');
          YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
          contentDiv.innerHTML = html;
          outer.appendChild(contentDiv);

          that._createModal(outer, false);

          var termDisplay = document.getElementById('termDisplay');

          if (termDisplay != null)
          {
            var disabled = termDisplay.disabled;

            if (disabled == false)
            {
              YAHOO.util.Event.on('termBtn', 'click', that._openBrowser, null, that);
            }

            var search = new MDSS.GenericSearch('termDisplay', 'term', Mojo.Util.bind(that, that._displayFunction), Mojo.Util.bind(that, that._displayFunction), Mojo.Util.bind(that, that._idFunction), Mojo.Util.bind(that, that._searchFunction));

            // Setup validator
            new MDSS.OntologyValidator('term', search, Mojo.Util.bind(that, that._getParameters), Mojo.Util.bind(that, that._setField));
          }

          eval(executable);
        }
      });

      var controller = Mojo.Meta.findClass(typeToEdit + "Controller");
      controller.setDeleteListener(Mojo.Util.bind(this, this._deleteNodeListener));
      controller.setUpdateListener(Mojo.Util.bind(this, this._updateNodeListener));
      controller.setCancelListener(Mojo.Util.bind(this, this._cancelNodeListener));
      controller.edit(request, geoEntityView.getGeoEntityId());
    },

    _displayFunction : function(valueObject)
    {
      if (valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }

      return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
    },

    _idFunction : function(valueObject)
    {
      if (valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return valueObject.getTermId();
      }

      return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
    },

    _getParameters : function()
    {
      return [ this._currentType, null ];
    },

    _searchFunction : function(request, value)
    {
      var params = this._getParameters();

      Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request, value, params);
    },

    /**
     * Invokes _selectedCallback with the id of the GeoEntity represented by the
     * currently selected node.
     */
    _customSelectHandler : function()
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;

      this._selectCallback(geoEntityView, this._selectedNode);
    },

    /**
     * Event handler for a triggered context menu. This method cancels the
     * action if the menu was not triggered on a node.
     */
    _nodeMenuSelect : function()
    {
      var oTarget = this._menu.contextEventTarget;

      var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");

      if (htmlNode)
      {
        this._selectedNode = this._geoTree.getNodeByElement(htmlNode);

        // If the tree allows selection, disable the Select menu item by default
        // and check if it should be enabled via an Ajax request.
        if (this._selectCallback != null)
        {
          var geoEntityView = this._selectedNode.data.geoEntityView;
          var geoId = geoEntityView.getGeoId();

          // IMPORTANT:
          // We have to access itemData directly as a property instead of using
          // getItem()
          // because the ContextMenu only loads items after the first render
          // (possibly because
          // of lazy loading).
          if (this._validator != null)
          {
            var item = this._menu.itemData[0];
            item.cfg.setProperty('disabled', true);

            if (geoEntityView.getActivated())
            {

              var request = new MDSS.Request({
                item : item,
                onSend : function()
                {
                },
                onComplete : function()
                {
                },
                onFailure : function()
                {
                },
                onProblemExceptionDTO : function()
                {
                },
                onSuccess : function()
                {
                  this.item.cfg.setProperty('disabled', false);
                }
              });

              this._validator(request, geoId);
            }
          }

        }

        this._menu.bringToTop();
      } else
      {
        this._menu.cancel();
      }
    },

    /**
     * Loads child nodes dynamically
     */
    _dynamicLoad : function(parentNode, fnLoadComplete)
    {
      var parentId;
      var pageNumber;
      var isPageNode;
      if (parentNode instanceof YAHOO.widget.TextNode && Mojo.Util.isNumber(parentNode.data.pageNumber))
      {
        parentId = parentNode.data.parentId;
        pageNumber = parentNode.data.pageNumber;
        isPageNode = true;
      } else
      {
        pageNumber = 1;
        parentId = parentNode.data.geoEntityId;
        isPageNode = false;
      }

      // request to fetch children
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(query)
        {

          var children = query.getResultSet();

          for ( var i = 0; i < children.length; i++)
          {
            var child = children[i];

            var div = that._createNodeDiv(child);

            var node = new YAHOO.widget.HTMLNode(div);
            node.data.geoEntityView = child;
            node.data.geoEntityId = child.getGeoEntityId();

            // either begin the replacing process if we're loading a new child
            // page
            // or append to the end of the parent node.
            if (isPageNode)
            {
              node.insertAfter(parentNode);
            } else
            {
              parentNode.appendChild(node);
            }
          }

          if (!isPageNode)
          {
            // append any page nodes if we're loading a normal parent entity
            // with overflow children
            var information = this.getInformation();
            var overflowClass = Mojo.$.dss.vector.solutions.geo.ChildEntityOverflowInformation;
            for ( var i = 0, len = information.length; i < len; i++)
            {
              // create the overflow page node. It will not be draggable
              var info = information[i];
              if (info instanceof overflowClass)
              {
                var overflowNode = new YAHOO.widget.TextNode(info.getMessage());
                overflowNode.data = {
                  pageNumber : info.getOverflowPage(),
                  parentId : parentId
                };
                parentNode.appendChild(overflowNode);
              }
            }
          }

          fnLoadComplete();

          if (isPageNode)
          {
            // remove the placeholder page node (must go after fnLoadComplete()
            // to avoid an error)
            that._geoTree.removeNode(parentNode, true);
          } else
          {
            parentNode.refresh();
          }
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildrenPage(request, parentId, '', pageNumber);
    },

    /**
     * Adds a parent to a new child.
     * 
     * HACK: The *this* reference is that of the YUI tree, not the
     * GeoEntityTree. This is due to legacy code and YUI integration issues.
     */
    _addChildToParent : function(e, obj)
    {
      var clone = obj.clone;
      var that = this;
      var ddThis = this;
      var request = new MDSS.Request({
        onSuccess : function()
        {

          var ddThis = obj.references.ddThis;
          var parentId = obj.references.parentId;

          // remove drag-hint class
          ddThis.getElDom(parentId).className = ddThis.getElDom(parentId).classNameBeforeDrag;

          var destNode = YAHOO.util.DDM.getDDById(parentId).node;

          var childNode = null;
          if (obj.clone)
          {
            // clone the node (do not clone its children)
            var div = ddThis.node.getContentEl().innerHTML;
            childNode = new YAHOO.widget.HTMLNode(div);
          } else
          {
            // not cloning, so remove the old node
            thisParent = ddThis.node.parent;
            ddThis.node.tree.popNode(ddThis.node);

            // fixes bug where a parent with 1 item is still marked
            // expanded, so when a new node is dragged to it, it doesn't
            // draw the new node (thinks it's already expanded)
            if (thisParent.children.length == 0)
            {
              thisParent.expanded = false;
            }

            // make removal changes visible
            thisParent.refresh();

            childNode = ddThis.node;
          }

          // Only add the node if the children have loaded via Ajax.
          // Otherwise, the node will appear twice (i.e., once from
          // the drag and drop and once from the Ajax load).
          if (destNode.dynamicLoadComplete)
          {
            childNode.appendTo(destNode);
            destNode.refresh();

            if (obj.clone)
            {
              // copy the mapping from the old node to the new one
              var geoEntityView = ddThis.node.data.geoEntityView;
              childNode.data.geoEntityView = geoEntityView;
              childNode.data.geoEntityId = geoEntityView.getGeoEntityId();
            }
          }

          destNode.expanded = false; // force re-expansion
          destNode.expand();
        }
      });

      var childEl = document.getElementById(obj.references.childId);
      var childNode = this._geoTree.getNodeByElement(childEl);

      var parentEl = document.getElementById(obj.references.parentId);
      var parentNode = this._geoTree.getNodeByElement(parentEl);

      var childGeoEntityView = childNode.data.geoEntityView;
      var parentGeoEntityView = parentNode.data.geoEntityView;

      var childId = childGeoEntityView.getGeoEntityId();
      var parentId = parentGeoEntityView.getGeoEntityId();

      var oldParentId = obj.references.ddThis.node.parent.data.geoEntityView.getGeoEntityId();

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.applyWithParent(request, childId, parentId, obj.clone, oldParentId);

      obj.references.modal.destroy();
    },

    /**
     * Handler for the drag/drop operation. The this reference is set to the
     * YAHOO.util.DDNodeProxy instance.
     */
    _dragDropHandler : function(source, id)
    {
      var tree = source.node.tree;
      var that = this;
      // create popup asking if this is for a copy operation
      var request = new MDSS.Request({
        references : {
          childId : source.id,
          parentId : id,
          ddThis : source
        },
        onConfirmParentChangeException : function(e)
        {
          var modal = new YAHOO.widget.Panel("confirmParentChange", {
            fixedcenter : true,
            width : '300px',
            visible : true,
            draggable : false,
            zindex : 8000,
            modal : true
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
          YAHOO.util.Event.on(yes, 'click', that._addChildToParent, {
            clone : false,
            references : this.references
          }, that);
          lowerDiv.appendChild(yes);

          var no = document.createElement('input');
          YAHOO.util.Dom.setAttribute(no, 'type', 'button');
          YAHOO.util.Dom.setAttribute(no, 'value', MDSS.localize('Choice_No'));
          YAHOO.util.Event.on(no, 'click', that._addChildToParent, {
            clone : true,
            references : this.references
          }, that);
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
      var childNode = this._geoTree.getNodeByElement(childEl);
      var childGeoEntityView = childNode.data.geoEntityView;
      var parentGeoEntityView = childNode.parent.data.geoEntityView;

      var childId = childGeoEntityView.getGeoEntityId();
      var parentId = parentGeoEntityView.getGeoEntityId();

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.confirmChangeParent(request, childId, parentId);
    },

    /**
     * Creates a div element as a string that represents the given GeoEntity.
     */
    _createNodeDiv : function(geoEntityView, scrapeTerm)
    {
      var activeClass = geoEntityView.getActivated() === true ? 'activeEntity' : 'inactiveEntity';

      var span = this._createContentSpan(geoEntityView, scrapeTerm);
      var div = "<div class='" + activeClass + "'>" + span + "</div>";

      return div;
    },

    _createContentSpan : function(geoEntityView, scrapeTerm)
    {
      if (scrapeTerm)
      {
        var display = document.getElementById('termDisplay');
        if (display && Mojo.Util.trim(display.value).length > 0)
        {
          var termName = MDSS.OntologyBrowser.extractName(display.value);
          geoEntityView.setMoSubType(termName);
        }
      }

      var display = MDSS.AbstractSelectSearch.getDisplayWithSubtype(geoEntityView);

      return "<span title='" + geoEntityView.getGeoId() + "'>" + display + "</span>";
    },

    /**
     * Flips the activated status (via a class) on all nodes represented by the
     * given ids, if that node exists in the DOM.
     */
    _updateActivatedOnNodes : function(ids, activated)
    {
      for ( var i = 0; i < ids.length; i++)
      {
        var id = ids[i];

        var nodes = that._geoTree.getNodesByProperty('geoEntityId', id);

        if (nodes != null)
        {
          Mojo.Iter.forEach(nodes, function(node)
          {
            var el = node.getContentEl();
            var nodeDiv = new YAHOO.util.Element(el.firstChild);

            if (activated === true)
            {
              nodeDiv.removeClass('inactiveEntity');
              nodeDiv.addClass('activeEntity');
            } else
            {
              nodeDiv.removeClass('activeEntity');
              nodeDiv.addClass('inactiveEntity');
            }
          }, this);
        }
      }
    },

    _exportEntitiesHandler : function()
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;
      var id = geoEntityView.getGeoEntityId();
      this._waitForDownload();
      document.getElementById('exportIframe').src = 'dss.vector.solutions.geo.GeoEntityTreeController.export.mojo?parentId=' + id + '&includeGeoData=true';
    },

    _exportEntitiesNoGISHandler : function()
    {
      var geoEntityView = this._selectedNode.data.geoEntityView;
      var id = geoEntityView.getGeoEntityId();
      this._waitForDownload();
      document.getElementById('exportIframe').src = 'dss.vector.solutions.geo.GeoEntityTreeController.export.mojo?parentId=' + id + '&includeGeoData=false';
    },

    _waitForDownload : function()
    {
      this._clearDownload(true);
      MDSS.util.wait_for_ajax.show();
      var that = this;
      this._intervalId = setInterval(function()
      {
        var value = YAHOO.util.Cookie.get("downloadToken");
        if (value != null)
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
      if (text.length > 0)
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
    _renderTree : function(geoEntity)
    {
      var view = this._copyEntityToView(geoEntity);

      var div = this._createNodeDiv(view);
      var node = {
        type : "HTML",
        html : div
      };

      this._geoTree = new YAHOO.widget.TreeViewDD(this._treeId, [ node ], Mojo.Util.bind(this, this._dragDropHandler));
      this._geoTree.setDynamicLoad(Mojo.Util.bind(this, this._dynamicLoad));
      this._geoTree.render();

      var itemData = [];

      // the select callback is optional
      if (Mojo.Util.isFunction(this._selectCallback))
      {
        var selectMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Select'));
        selectMenuItem.subscribe("click", Mojo.Util.bind(this, this._customSelectHandler));
        itemData.push(selectMenuItem);
      }

      var importMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Import_Button'));
      importMenuItem.subscribe("click", Mojo.Util.bind(this, this._uploadImport));
      itemData.push(importMenuItem);

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
      deleteMenuItem.subscribe("click", Mojo.Util.bind(this, this._deleteNodeListener));
      itemData.push(deleteMenuItem);

      this._menu = new YAHOO.widget.ContextMenu("treeMenu" + Mojo.Util.generateId(), {
        trigger : this._treeId,
        lazyload : true,
        itemdata : itemData,
        zindex : 500
      });

      this._menu.subscribe("triggerContextMenu", Mojo.Util.bind(this, this._nodeMenuSelect));

      // map node to GeoEntity
      this._geoTree.getRoot().children[0].data.geoEntityView = view;
      this._geoTree.getRoot().children[0].data.geoEntityId = view.getGeoEntityId();

      YAHOO.util.Event.on('exportIframe', 'load', this._handleExport, null, this);
    },

    /**
     * Initializes the tree by setting the GeoEntity with the given id as first
     * node under the root.
     */
    render : function()
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(geoEntity)
        {
          // build tree
          that._renderTree(geoEntity);
        }
      });

      // Fetch the root node
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, this._rootId);
    },

    setValidator : function(validator)
    {
      this._validator = validator;
    }
  }
});