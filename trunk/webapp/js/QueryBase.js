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
      exportXLS : MDSS.util.bind(this, this.exportXLS),
      exportCSV : MDSS.util.bind(this, this.exportCSV),
      exportReport : MDSS.util.bind(this, this.exportReport),
      toggleDates : MDSS.util.bind(this, this.toggleDates),
      paginationHandler : MDSS.util.bind(this, this.paginationHandler),
      postRender : MDSS.util.bind(this, this.postRender),
    });

    // Set of GeoEntity subclasses that will be used for the query/mapping
    this._geoEntityQueryType = '';

    this._geoEntityTypes = {};
    this._geoEntitySelectables = {};
    this._geoIdConditions = {};
    
    this._currentPage = 1;
    
    this.PAGE_SIZE = 1;
  },
  
  getCurrentPage : function()
  {
    return this._currentPage;
  },
  
  setCurrentPage : function(page)
  {
    this._currentPage = page;
  },

  exportCSV : function(form, xmlInput, geoEntityTypeInput, searchIdInput)
  {
    var queryXML = this._constructQuery();
    var xml = queryXML.getXML();

    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
    var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");

  	var action = this._getExportCSVAction();
    form.action = action;

    xmlInput.innerHTML = xml;
    geoEntityTypeInput.value = this._geoEntityQueryType; // FIXME
    searchIdInput.value = savedSearchId;
    form.submit();
  },


  exportReport : function(form, xmlInput, geoEntityTypeInput, searchIdInput)
  {
    var queryXML = this._constructQuery();
    var xml = queryXML.getXML();

    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
    var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");

  	var action = this._getExportReportAction();
    form.action = action;

    xmlInput.innerHTML = xml;
    geoEntityTypeInput.value = this._geoEntityQueryType; // FIXME
    searchIdInput.value = savedSearchId;
    form.submit();
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
    geoEntityTypeInput.value = this._geoEntityQueryType; // FIXME
    searchIdInput.value = savedSearchId;
    form.submit();
  },
  
  /**
   * Resets the query panel with the result contents of the query.
   */
  resetQueryResults : function(query)
  {
     // column key is selectable alias name
     var columnSet = this._queryPanel.getColumnSet();
     var columns = columnSet.keys;

     // add query results to table
     var resultSet = query.getResultSet();
     var jsonData = [];
     for(var i=0; i<resultSet.length; i++)
     {
       var result = resultSet[i];

       var entry = {};
       for(var j=0; j<columns.length; j++)
       {
         var column = columns[j];
         var attr = column.getKey();
         entry[attr] = result.getAttributeDTO(attr).getValue();
       }

       jsonData.push(entry);
     }
     
     this._queryPanel.clearAllRecords();
     this._queryPanel.setRowData(jsonData);
     this._queryPanel.setPagination(query.getCount(), this.getCurrentPage(), this.PAGE_SIZE);
  },
  
  /**
   * Called after the QueryPanel has performed all of its rendering operations.
   */
  postRender : function()
  {
  },
  
  /**
   * Called when a user clicks a page number in the query panel.
   */
  paginationHandler : function(pageNumber)
  {
    this.setCurrentPage(pageNumber);
    this.executeQuery();
  },
  
  /**
   * Called when a user toggles the value in the checkbox next
   * to the start and end date fields. This generally means the user
   * wants to add the respective dates to the select clause in the result
   * set.
   */
  toggleDates : function()
  {
    // abstract
  },

  _getExportXLSAction : function()
  {
    // abstract
  },

  _getExportCSVAction : function()
  {
    // abstract
  },

  _getExportReportAction : function()
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

    //var groupBy = this._containsGroupBy() ? queryXML.getGroupBy() : null;

    var geoSelectables = Mojo.util.getKeys(this._geoEntitySelectables);
    for(var i=0; i<geoSelectables.length; i++)
    {
     var name = geoSelectables[i];
     var selectable = this._geoEntitySelectables[name];

     queryXML.addSelectable(name, selectable);

     /*
     if(groupBy != null)
     {
       groupBy.addSelectable(name, selectable);
     }
     */
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
   * Adds a universal type as a selectable to the query results. The universal
   * is represented as a GeoEntityView, which is a view instance of that geo entity
   * type. This is done to grab any necessary metadata/display labels.
   */
  _addUniversalEntity: function(geoEntityView, addColumn)
  {
    // Earth is not allowed in the Select
    if(geoEntityView.getEntityType() === 'dss.vector.solutions.geo.generated.Earth')
    {
      return;
    }

    var type = geoEntityView.getEntityType();


    // add the GeoEntity as a query entity
    var geoEntityQuery = this._geoEntityTypes[type];
    if(!Mojo.util.isObject(geoEntityQuery))
    {
      geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
      this._geoEntityTypes[type] = geoEntityQuery;
    }

    if(addColumn)
    {
      var typeName = type.substring(type.lastIndexOf('.')+1);

      // only add the column if it does not exist
      if(this._geoEntitySelectables[type+'_'+geoEntityView.getEntityNameMd().getName()] == null)
      {
        var entityNameColumn = typeName+'_'+geoEntityView.getEntityNameMd().getName();
        var obj = {
          key: entityNameColumn,
          label: (geoEntityView.getTypeDisplayLabel() + " " + geoEntityView.getEntityNameMd().getDisplayLabel())
        };

        var column = new YAHOO.widget.Column(obj);
        this._queryPanel.insertColumn(column);

        var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getEntityNameMd().getName(), entityNameColumn);
        var entityNameSel = new MDSS.QueryXML.Selectable(entityNameAttr);
        this._geoEntitySelectables[type+'_'+entityNameAttr.getName()] = entityNameSel;
      }

      if(this._geoEntitySelectables[type+'_'+geoEntityView.getGeoIdMd().getName()] == null)
      {
        var geoIdColumn = typeName+'_'+geoEntityView.getGeoIdMd().getName();
        var obj = {
          key: geoIdColumn,
          label: (geoEntityView.getTypeDisplayLabel() + " " + geoEntityView.getGeoIdMd().getDisplayLabel())
        };

        var column = new YAHOO.widget.Column(obj);
        this._queryPanel.insertColumn(column);

        var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getGeoIdMd().getName(), geoIdColumn);
        var geoIdSel = new MDSS.QueryXML.Selectable(geoIdAttr);
        this._geoEntitySelectables[type+'_'+geoIdAttr.getName()] = geoIdSel;
      }
    }
  },

  /**
   * Removes the given universal type as a selectable from the query results.
   */
  _removeUniversalEntity : function(type)
  {
    var entityNameSuffix = '_' + Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.ENTITYNAME;
    var geoIdSuffix = '_' + Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.GEOID;

    // remove the column and entry as a query entity
    delete this._geoEntitySelectables[type+entityNameSuffix];
    delete this._geoEntitySelectables[type+geoIdSuffix];

    // the columns may not exist because the type exists only
    // for restricting criteria, not selection.
    var typeName = type.substring(type.lastIndexOf('.')+1);
    var entityNameColumn = this._queryPanel.getColumn(typeName+entityNameSuffix);
    if(entityNameColumn != null)
    {
      this._queryPanel.removeColumn(entityNameColumn);
    }

    var geoIdColumn = this._queryPanel.getColumn(typeName+geoIdSuffix);
    if(geoIdColumn != null)
    {
      this._queryPanel.removeColumn(geoIdColumn);
    }

    delete this._geoEntityTypes[type];
  },

  /**
   * Uses the given GeoEntityView objects to add
   * restrictions to the GeoEntity query.
   */
  _hideHandler : function(criteriaEntities, selectedEntities, restrictingType)
  {
    this._queryPanel.clearAllRecords();

    // remove existing columns
    var types = Mojo.util.getKeys(this._geoEntityTypes);
    for(var i=0; i<types.length; i++)
    {
      this._removeUniversalEntity(types[i]);
    }

    // add new columns
    var keys = Mojo.util.getKeys(selectedEntities);
    for(var i=0; i<keys.length; i++)
    {
      var geoEntityView = selectedEntities[keys[i]];
      this._addUniversalEntity(geoEntityView, true);
    }

    // remove all prior conditions
    this._geoIdConditions = {};
    this._geoEntityQueryType = restrictingType;

    for(var i=0; i<criteriaEntities.length; i++)
    {
      var geoEntityView = criteriaEntities[i];

      var type = geoEntityView.getEntityType();

      // add the type as a selectable if it does not exist
      if(!Mojo.util.isObject(this._geoEntityTypes[type]))
      {
        this._addUniversalEntity(geoEntityView, false);
      }

      var geoEntityQuery = this._geoEntityTypes[type];

      // add restriction based on geoId
      var attribute = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getGeoIdMd().getName());
      var selectable = new MDSS.QueryXML.Selectable(attribute);
      var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, geoEntityView.getGeoId());

      this._geoIdConditions[geoEntityView.getGeoId()] = geoIdCondition;
    }

    this._queryPanel.addSelectedGeoEntities(criteriaEntities);
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
      var hideBound = MDSS.util.bind(this, this._hideHandler);

      this._selectSearch = new MDSS.MultipleSelectSearch();
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