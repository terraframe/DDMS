/**
 * Base class to support functionality for all query use cases.
 */
MDSS.QueryBase = Mojo.Class.create();
MDSS.QueryBase.prototype = {

  initialize : function()
  {

    this._queryPanel = new MDSS.QueryPanel('queryPanel', 'mapPanel', {
      executeQuery: MDSS.util.bind(this, this.executeQuery),
      mapQuery: MDSS.util.bind(this, this.mapQuery),
      saveQuery: MDSS.util.bind(this, this.saveQuery),
      loadQuery: MDSS.util.bind(this, this.loadQuery),
      addLayer: MDSS.util.bind(this, this.addLayer),
      editLayer: MDSS.util.bind(this, this.editLayer),
      deleteLayer: MDSS.util.bind(this, this.deleteLayer),
      editVariableStyles: MDSS.util.bind(this, this.editVariableStyles),
      exportXLS : MDSS.util.bind(this, this.exportXLS)
    });

    // The class name of the GeoEntity that will be used for
    // mapping.
    this._geoEntityQueryType = '';

    this._geoEntityTypes = {};
    this._geoEntitySelectables = {};
    this._geoIdConditions = {};
  },

  /**
   * Handler to export the current query to an Excel file as a download.
   */
  exportXLS : function(form, xmlInput, geoEntityTypeInput, searchIdInput)
  {
    var queryXML = this._constructQuery();
    var xml = queryXML.getXML();

    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
    var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");

  	var action = this._getExportXLSAction();
    form.action = action;

    xmlInput.innerHTML = xml;
    geoEntityTypeInput.value = this._geoEntityQueryType;
    searchIdInput.value = savedSearchId;
    form.submit();
  },

  _getExportXLSAction : function()
  {
    // abstract
  },

  /**
   * Method called to render to set up the QueryPanel
   * this QueryBase uses.
   */
  render : function()
  {
  	// Abstract
  },

  /**
   * Called when the user tries to execute the query.
   */
  executeQuery : function()
  {
    // Abstract
  },

  /**
   * Called when the user tries to map a query.
   */
  mapQuery : function()
  {
    // Abstract
  },

  /**
   * Called when a user tries to save a query.
   */
  saveQuery : function()
  {
  	// Abstract
  },

  /**
   * Called when a user tries to load a query.
   */
  loadQuery : function(savedSearchId)
  {
    var request = new MDSS.Request({
      onSuccess: function(savedSearchView){

        this._queryPanel.setCurrentSavedSearch(savedSearchView);

        // set the XML

        // set the layers
      }
    });

    Mojo.$.dss.vector.solutions.query.SavedSearch.getAsView(request, savedSearchId, true);
  },

  /**
   * Cancels saving a new query.
   */
  _cancelQueryListener : function(modal, params, action)
  {
    modal.destroy();
  },

  /**
   * Saves the current state of the query.
   */
  _saveQueryListener : function(modal, params, action)
  {
    var queryXML = this._constructQuery();
    var xml = queryXML.getXML();

    var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
    view.setQueryName(params['savedQueryView.queryName']);
    view.setQueryXml(xml);
    view.setThematicLayer(this._geoEntityQueryType);

    var request = new MDSS.Request({
      thisRef: this,
      modal:modal,
      onSuccess: function(savedSearchView){

        this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);

        var obj = {
          id: savedSearchView.getSavedQueryId(),
          name: savedSearchView.getQueryName()
        };
        this.thisRef._queryPanel.addAvailableQuery(obj);

        this.modal.destroy();
      }
    });

    var method = this._getSaveQueryMethod();
    method(request, view);
  },

  /**
   * Subclasses must override this to return the controller method
   * that will be executed to save a search.
   */
  _getSaveQueryMethod : function()
  {
  	// Abstract
  },

  /**
   * Subclasses must override this and return true
   * if there is any group by clauses in the query.
   * This is necessary to auto-group by the GeoEntity
   * attributes.
   */
  _containsGroupBy : function()
  {
  	// Abstract
  },

  /**
   * Creates and returns an MDSS.QueryXML.Query object.
   * Subclasses must override this method and use the returned
   * object according their specific use case.
   */
  _constructQuery : function()
  {
  	var queryXML = new MDSS.QueryXML.Query();

    // geo entity
    var entities = Mojo.util.getValues(this._geoEntityTypes);
    for(var i=0; i<entities.length; i++)
    {
     var entity = entities[i];
     queryXML.addEntity(entity);
    }

    var groupBy = this._containsGroupBy() ? queryXML.getGroupBy() : null;

    var geoSelectables = Mojo.util.getKeys(this._geoEntitySelectables);
    for(var i=0; i<geoSelectables.length; i++)
    {
     var name = geoSelectables[i];
     var selectable = this._geoEntitySelectables[name];

     queryXML.addSelectable(name, selectable);

     if(groupBy != null)
     {
       groupBy.addSelectable(name, selectable);
     }
    }

    // geo id restrictions (WHERE clause)
    var conditions = Mojo.util.getValues(this._geoIdConditions);
    if(conditions.length > 0)
    {
      var or = new MDSS.QueryXML.Or();
      var entityAlias = null; // all will be of same type
      for(var i=0; i<conditions.length; i++)
      {
        var condition = conditions[i];

      	if(i==0)
      	{
      	  entityAlias = condition.getSelectable().getComponent().getEntityAlias();
      	}

        or.addCondition('geoIdCondition_'+i, condition);
      }

      var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);

      var geoEntityQuery = queryXML.getEntity(entityAlias);
      geoEntityQuery.setCondition(compositeCondition);
    }


    return queryXML;
  },

  /**
   * Handler for a selected GeoEntity. The selected GeoEntity
   * is added as restricting criteria and the type is added
   * as a column for the query output.
   */
  _selectHandler: function(geoEntityView)
  {
    // Earth is not allowed in the Select
    if(geoEntityView.getEntityType() === 'dss.vector.solutions.geo.generated.Earth')
    {
      return;
    }

    var type = geoEntityView.getEntityType();
    this._geoEntityQueryType = type;
    var typeName = type.substring(type.lastIndexOf('.')+1);

    var entityNameColumn = typeName+'_'+geoEntityView.getEntityNameMd().getName();
    var geoIdColumn = typeName+'_'+geoEntityView.getGeoIdMd().getName();


    // only add the column if it does not exist
    if(this._queryPanel.getColumn(entityNameColumn) == null)
    {
      var obj = {
        key: entityNameColumn,
        label: (geoEntityView.getTypeDisplayLabel() + " " + geoEntityView.getEntityNameMd().getDisplayLabel())
      };

      var column = new YAHOO.widget.Column(obj);
      this._queryPanel.insertColumn(column);
    }

    if(this._queryPanel.getColumn(geoIdColumn) == null)
    {
      var obj = {
        key: geoIdColumn,
        label: (geoEntityView.getTypeDisplayLabel() + " " + geoEntityView.getGeoIdMd().getDisplayLabel())
      };

      var column = new YAHOO.widget.Column(obj);
      this._queryPanel.insertColumn(column);
    }

    // add the GeoEntity as a query entity
    var geoEntityQuery = this._geoEntityTypes[type];
    if(!Mojo.util.isObject(geoEntityQuery))
    {
     geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
     this._geoEntityTypes[type] = geoEntityQuery;

     // selectables (entityName, geoId and spatial attribute)
     var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getEntityNameMd().getName(), entityNameColumn);
     var entityNameSel = new MDSS.QueryXML.Selectable(entityNameAttr);
     this._geoEntitySelectables[type+'_'+entityNameAttr.getName()] = entityNameSel;

     var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getGeoIdMd().getName(), geoIdColumn);
     var geoIdSel = new MDSS.QueryXML.Selectable(geoIdAttr);
     this._geoEntitySelectables[type+'_'+geoIdAttr.getName()] = geoIdSel;
    }
  },

  /**
   * Uses the given GeoEntityView objects to add
   * restrictions to the GeoEntity query.
   */
  _hideHandler : function(geoEntityViews)
  {
    for(var i=0; i<geoEntityViews.length; i++)
    {
      var geoEntityView = geoEntityViews[i];

      var geoEntityQuery = this._geoEntityTypes[geoEntityView.getEntityType()];

      // add restriction based on geoId
      var attribute = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getGeoIdMd().getName());
      var selectable = new MDSS.QueryXML.Selectable(attribute);
      var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, geoEntityView.getGeoId());

      this._geoIdConditions[geoEntityView.getGeoId()] = geoIdCondition;
    }
  },

  /**
   * Displays the SelectSearch (handler for click event on Area/Target menu).
   */
  _displaySearch : function()
  {
    if(this._selectSearch != null && this._selectSearch.isInitialized())
    {
      this._selectSearch.show();
    }
    else
    {
      var selectBound = MDSS.util.bind(this, this._selectHandler);
      var hideBound = MDSS.util.bind(this, this._hideHandler);

      //this._selectSearch = new MDSS.SingleSelectSearch();
      this._selectSearch = new MDSS.MultipleSelectSearch();
      this._selectSearch.setSelectHandler(selectBound);
      this._selectSearch.setTreeSelectHandler(selectBound);
      this._selectSearch.setHideHandler(hideBound);
      this._selectSearch.setFilter('');
      this._selectSearch.render();
    }
  },

  /**
   * Creates a modal with the given HTML as its body and the given title
   * as the modal title, wrapped in an H3.
   */
  _createModal : function(html, title)
  {
    var executable = MDSS.util.extractScripts(html);
    var html = MDSS.util.removeScripts(html);

    var modal = new YAHOO.widget.Panel("editQuery", {
      width:"400px",
      height: "400px",
      fixedcenter:true,
      close: false,
      draggable:false,
      zindex:4,
      modal:true,
      visible:true
    });

    // wrap content in divs
    var outer = document.createElement('div');

    var header = document.createElement('div');
    header.innerHTML = '<h3>'+title+'</h3><hr />';
    outer.appendChild(header);

    var contentDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
    contentDiv.innerHTML = html;
    outer.appendChild(contentDiv);

    modal.setBody(outer);
    modal.render(document.body);

    eval(executable);

    return modal;
  },


  /**
   * Adds a layer of the given type to the map.
   * The map is not refreshed.
   */
  addLayer : function(type)
  {
    var request = new MDSS.Request({
      type: type,
      thisRef: this,
      onSuccess: function(layerId){

        layerId = MDSS.util.stripWhitespace(layerId);
        this.thisRef._queryPanel.addDefinedLayer(layerId, this.type);
      }
    });

    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
    var savedSearchId = savedSearchView.getSavedQueryId();
    Mojo.$.dss.vector.solutions.query.MappingController.createLayer(request, savedSearchId, type);
  },

  /**
   * Locks a layer and its components to put them
   * in edit mode.
   */
  editLayer : function(layerId)
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;

    var request = new MDSS.Request({
      thisRef: this,
      layerId: layerId,
      controller: controller,
      onSuccess: function(html){

        var modal = this.thisRef._createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.bind(this.thisRef, this.thisRef._updateLayerListener, modal);
        var canceled = MDSS.util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, layerId, true);
        this.controller.setUpdateLayerListener(update);
        this.controller.setCancelLayerListener(canceled);
      }
    });

    controller.editLayer(request, layerId);
  },

  _updateLayerListener : function(modal, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){

        this.modal.destroy();
      }
    });

    return request;
  },

  _cancelLayerListener : function(modal, layerId, unlock)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.MappingController.cancelLayer(request, layerId, unlock);
  },

  /**
   *
   */
  deleteLayer : function()
  {

  },

  /**
   * Listener for when a use requesets a new AbstractCategory.
   * The returned HTML form is appended to the list of editable
   * categories.
   */
  _newCategoryListener : function(params, action)
  {
    var request = new MDSS.Request({
      thisRef: this,
      onSuccess: function(html)
      {
        this.thisRef._queryPanel.addCategoryHTML(html);
      }
    });

    return request;
  },

  _editThematicVariable : function(modal, layerId, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function()
      {
        this.modal.destroy();
      }
    });


    var thematicVarStr = params['variable'][0];
    var thematicVar;
    if(thematicVarStr !== '')
    {
      thematicVar = new Mojo.$.dss.vector.solutions.query.ThematicVariable();
      var pieces = thematicVarStr.split('-');
      thematicVar.setEntityAlias(pieces[0]);
      thematicVar.setAttributeName(pieces[1]);
    }
    else
    {
      thematicVar = null;
    }

    this._queryPanel.setCurrentThematicVariable(thematicVar);

    var categories = this._queryPanel.scrapeCategories();
    Mojo.$.dss.vector.solutions.query.ThematicLayer.updateThematicVariable(request, layerId, thematicVar, categories);
  },

  /**
   * Handler to edit the styles associated with a thematic variable.
   */
  editVariableStyles : function()
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;
    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
    var thematicLayerId = savedSearchView.getThematicLayerId();

    var request = new MDSS.Request({
      thisRef: this,
      controller: controller,
      thematicLayerId: thematicLayerId,
      onSuccess: function(html){

        var modal = this.thisRef._createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.bind(this.thisRef, this.thisRef._editThematicVariable, modal, this.thematicLayerId);
        var canceled = MDSS.util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, this.thematicLayerId,true);
        var newCategory = MDSS.util.bind(this.thisRef, this.thisRef._newCategoryListener);

        this.controller.setUpdateThematicVariableListener(update);
        this.controller.setCancelLayerListener(canceled);

        Mojo.$.dss.vector.solutions.query.RangeCategoryController.setNewInstanceListener(newCategory);
        Mojo.$.dss.vector.solutions.query.NonRangeCategoryController.setNewInstanceListener(newCategory);
      }
    });

    var thematicVars = this._queryPanel.getThematicVariables();

    controller.editThematicLayer(request, thematicLayerId, thematicVars);
  }

};