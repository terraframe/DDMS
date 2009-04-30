MDSS.QueryEntomology = (function(){

  var _selectSearch = null;

  var _thematicVariableColumnKey = null;

  var _mosquitoColumns = [];

  var _queryPanel = null;

  var _queryXML = null;

  /**
   * Loads available queries.
   */
  function _loadQuery(savedSearchId)
  {
    var request = new MDSS.Request({
      onSuccess: function(savedSearch){
        _queryPanel.setCurrentSavedSearch(savedSearch);

        // set the XML

        // set the layers
      }
    });

    Mojo.$.dss.vector.solutions.query.EntomologySearch.get(request, savedSearchId);
  }

  /**
   * Saves the query.
   */
  function _saveQueryListener(modal, params, action)
  {
  	// FIXME hardcoded for testing
    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;

  	var xml = _queryXML.getXML();

  	var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
    view.setQueryName(params['savedQueryView.queryName']);
    view.setQueryXml(xml);
    view.setThematicLayer(sentinelSite);

    var request = new MDSS.Request({
      modal:modal,
      onSuccess: function(savedSearch){

        var obj = {id: savedSearch.getId(), name: savedSearch.getQueryName() };
        _queryPanel.addAvailableQuery(obj);
        _queryPanel.setCurrentSavedSearch(savedSearch);
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.EntomologySearch.saveSearch(request, view);
  }

  function _cancelQueryListener(modal, params, action)
  {
    modal.destroy();
  }

  /**
   * Saves the current state of the QueryXML.
   */
  function _saveQuery()
  {
    var controller = Mojo.$.dss.vector.solutions.entomology.QueryController;
    var request = new MDSS.Request({
      controller: controller,
      onSuccess: function(html)
      {
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);

        var modal = new YAHOO.widget.Panel("saveQuery", {
          width:"400px",
          height: "400px",
          fixedcenter:true,
          close: true,
          draggable:false,
          zindex:4,
          modal:true,
          visible:true
        });

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>'+MDSS.Localized.Query.Save+'</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        modal.setBody(outer);
        modal.render(document.body);

        eval(executable);

        var saved = MDSS.util.curry(_saveQueryListener, modal);
        var canceled = MDSS.util.curry(_cancelQueryListener, modal);
        this.controller.setSaveQueryListener(saved);
        this.controller.setCancelQueryListener(canceled);
      }
    });

    controller.newQuery(request);
  }

  /**
   * Final function called before query is executed.
   * Any last minute cleanup is done here. The this
   * reference is that of the QueryPanel.
   */
  function _executeQuery()
  {
  	var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    var mosquitoEntity = _queryXML.getEntity(mosquito);

    // always clear any prior conditions
    mosquitoEntity.clearCondition();

    var conditions = [];

    // Add start and end dates WHERE criteria
    // if values exist
    var startDateEl = this.getStartDate();
    var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
    var testDate = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    if(startDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(startDate);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), testDate);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
      conditions.push(startDateCondition);
    }

    var endDateEl = this.getEndDate();
    var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
    if(endDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(endDate);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), testDate);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      var endDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, formatted);
      conditions.push(endDateCondition);
    }

    if(conditions.length == 2)
    {
      var and = new MDSS.QueryXML.And();
      and.addCondition('date1', conditions[0]);
      and.addCondition('date2', conditions[1]);
      var compositeCondition = new MDSS.QueryXML.CompositeCondition(and);
      mosquitoEntity.setCondition(compositeCondition);
    }
    else if(conditions.length == 1)
    {
      var or = new MDSS.QueryXML.Or();
      or.addCondition('date1', conditions[0]);
      var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);
      mosquitoEntity.setCondition(compositeCondition);
    }

    // execute the query
    var xml = _queryXML.getXML();

    var request = new MDSS.Request({
      onSuccess : function(query)
      {
        // column key is selectable alias name
        var columnSet = _queryPanel.getColumnSet();
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

        // clear previous records
        _queryPanel.clearAllRecords();

        _queryPanel.setRowData(jsonData);
        _queryPanel.enableMapping();
      }
    });

    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;
    Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml, sentinelSite);
  }

  /**
   * Handler called to generate a map with a thematic variable.
   */
  function _mapQuery()
  {

    // FIXME hardcoded for testing
    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;
    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;

    var types = [];
    types.push(sentinelSite);
    types.push(mosquito);

    var selectables = [];
    selectables.push(types[0]+'_entityName');
    selectables.push(types[0]+'_geoId');
    selectables.push(types[1]+'_isofemale');
    var xml = _queryXML.getXMLForMap(types, selectables);

    var request = new MDSS.Request({
      queryPanelRef: this,
      onSuccess : function(layers){
      	var layersObj = Mojo.util.getObject(layers);
        this.queryPanelRef.createMap(layersObj);
      }
    });

  	var layerIds = _queryPanel.getSelectedLayers();
    Mojo.$.dss.vector.solutions.entomology.Mosquito.mapQuery(request, xml, sentinelSite, layerIds);
  }

   /**
    * Handler for a selected GeoEntity. The selected GeoEntity
    * is added as restricting criteria and the type is added
    * as a column for the query output.
    */
   function _selectHandler(selected)
   {
     var listIdSuffix = '_entry';

     bestFit = selected;

     // Earth is not allowed in the Select
     var earth = Mojo.$.dss.vector.solutions.geo.generated.Earth.CLASS;
     if(bestFit.getEntityType() === earth)
     {
       return;
     }

     // do nothing if the GeoEntity has already been added
     if(YAHOO.util.Dom.inDocument(bestFit.getId()+listIdSuffix))
     {
       return;
     }

     var type = bestFit.getEntityType();
     var typeName = type.substring(type.lastIndexOf('.')+1);

     var entityNameColumn = typeName+'_'+bestFit.getEntityNameMd().getName();
     var geoIdColumn = typeName+'_'+bestFit.getGeoIdMd().getName();


     // only add the column if it does not exist
     if(_queryPanel.getColumn(entityNameColumn) == null)
     {
       var obj = {
         key: entityNameColumn,
         label: (bestFit.getTypeDisplayLabel() + " " + bestFit.getEntityNameMd().getDisplayLabel())
       };

       var column = new YAHOO.widget.Column(obj);
       _queryPanel.insertColumn(column);
     }

     if(_queryPanel.getColumn(geoIdColumn) == null)
     {
       var obj = {
         key: geoIdColumn,
         label: (bestFit.getTypeDisplayLabel() + " " + bestFit.getGeoIdMd().getDisplayLabel())
       };

       var column = new YAHOO.widget.Column(obj);
       _queryPanel.insertColumn(column);
     }

    // add the GeoEntity as restricting criteria
    // FIXME not compatible w/ two entities of the same type
    var geoEntityQuery = _queryXML.getEntity(type);
    if(geoEntityQuery == null)
    {
      var geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
      _queryXML.addEntity(geoEntityQuery);

      // selectables (entityName, geoId and spatial attribute)
      var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getEntityNameMd().getName(), entityNameColumn);
      var entityNameSel = new MDSS.QueryXML.SimpleSelectable(entityNameAttr);

      _queryXML.addSelectable(type+'_'+entityNameAttr.getName(), entityNameSel);

      var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName(), geoIdColumn);
      var geoIdSel = new MDSS.QueryXML.SimpleSelectable(geoIdAttr);

      _queryXML.addSelectable(type+'_'+geoIdAttr.getName(), geoIdSel, geoIdAttr.getName());
    }

    // add restriction based on geoId
    var attribute = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName());
    var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
    var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, bestFit.getGeoId());

    var and = new MDSS.QueryXML.And();
    and.addCondition('geoIdCondition', geoIdCondition);
    var compositeCondition = new MDSS.QueryXML.CompositeCondition(and);
    geoEntityQuery.setCondition(compositeCondition);
  }

  /**
   * Displays the SelectSearch (handler for click event on Area menu).
   */
  function _displaySearch()
  {
    if(_selectSearch != null && _selectSearch.isInitialized())
    {
      _selectSearch.show();
    }
    else
    {
      var radios = YAHOO.util.Selector.query('input[type="radio"]', 'searchMosquitoCollections');
      var filterType = '';
      for(var i=0; i<radios.length; i++)
      {
        var radio = radios[i];
        if(radio.checked)
        {
          filterType = radio.value;
        }
      }

      _selectSearch = new MDSS.SingleSelectSearch();
      _selectSearch.setSelectHandler(_selectHandler);
      _selectSearch.setTreeSelectHandler(_selectHandler);
      _selectSearch.setFilter(filterType);
      _selectSearch.render();
    }
  }

  /**
   * Execute when the user requests that the given
   * column is to be removed from the table.
   */
  function _removeSelectableColumn(columnId)
  {
    var column = _queryPanel.getColumn(columnId);
  }


  function _clickTrueSpecies(e, obj)
  {
    _trueSpeciesHandler(obj);
  }

  function _addTrueSpecies(eventType, event, obj)
  {
    _trueSpeciesHandler(obj);
  }

  function _trueSpeciesHandler(obj)
  {
    // do nothing if the column already exists
    if(_queryPanel.getColumn(obj.key) != null)
    {
      return;
    }

    var column = new YAHOO.widget.Column(obj);
    _queryPanel.insertColumn(column);

    // add Specie entity to the query
    var specie = Mojo.$.dss.vector.solutions.mo.Specie.CLASS;
    var specieEntity = new MDSS.QueryXML.Entity(specie, specie);
    _queryXML.addEntity(specieEntity);

    var displayLabel = Mojo.$.dss.vector.solutions.mo.Specie.DISPLAYLABEL;
    var dlAttribute = new MDSS.QueryXML.Attribute(specieEntity.getAlias(), displayLabel);
    var dlSelectable = new MDSS.QueryXML.SimpleSelectable(dlAttribute);
    _queryXML.addSelectable(specieEntity.getAlias()+'_'+displayLabel, dlSelectable);
  }

  function _clickSpeciesRatio(e, obj)
  {
    _speciesRatioHandler(obj);
  }

  function _addSpeciesRatio(eventType, event, obj)
  {
    _speciesRatioHandler(obj);
  }

  function _speciesRatioHandler(obj)
  {
    var column = new YAHOO.widget.Column(obj);
    _queryPanel.insertColumn(column);
  }

  function _addAssayHandler(eventType, event, obj)
  {
    var column = new YAHOO.widget.Column(obj);
    _queryPanel.insertColumn(column);
  }

  /**
   * Builds menu items for attributes native to Mosquito
   */
  function _buildMenuForAttributes(column)
  {
    var items = [];

    // all attributes are removable
    items.push({
      text: "Remove", onclick: {fn: _removeAttribute, obj:{column: column}}
    });

    // enable thematic selection if mapping is enabled
    if(_queryPanel.isMappingEnabled())
    {
      items.push({
        text: "Mark Thematic", onclick: {fn: _markThematic, obj:{column: column}}
      });
    }

    return items;
  }

  /**
   * Handler to remove Mosquito attributes from the table
   * and query.
   */
  function _removeAttribute(eventType, event, obj)
  {
    var column = obj.column;

    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    var mosquitoEntity = _queryXML.getEntity(mosquito);

    _queryXML.removeSelectable(mosquitoEntity.getAlias()+'_'+column.getKey());

    _queryPanel.removeColumn(column);
  }

  function _markThematic(eventType, event, obj)
  {
    var thematicClass = "thematicVariable";

    // remove current thematic variable
    var currentColumn = _queryPanel.getColumn(_thematicVariableColumnKey);
    if(currentColumn != null)
    {
      YAHOO.util.Dom.removeClass(currentColumn.getThEl().firstChild, thematicClass);
    }

    // add new thematic var
    var column = obj.column;
    YAHOO.util.Dom.addClass(column.getThEl().firstChild, thematicClass);

    _thematicVariableColumnKey = column.getKey();
  }

  /**
   * Helper method to add mosquito attributes to selectables and as a column.
   */
  function _addAttribute(attributeObj)
  {
    var column = new YAHOO.widget.Column(attributeObj);
    column = _queryPanel.insertColumn(column, _buildMenuForAttributes);

    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    var mosquitoEntity = _queryXML.getEntity(mosquito);

    var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), attributeObj.key, attributeObj.key);
    var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
    _queryXML.addSelectable(mosquitoEntity.getAlias()+'_'+column.getKey(), selectable);
  }

  /**
   * Builds the query items for the left column.
   */
  function _buildQueryItems(assayTree)
  {
    // area (geo entity search)
    _queryPanel.addQueryItem({
      displayLabel: 'Area <img src="./imgs/icons/world.png"/>',
      onclick: {handler: _displaySearch},
      id: "areaItem"
    });

    // true species
    var tsObj = {key: "trueSpecies", label: "True Species"};
    _queryPanel.addQueryItem({
      displayLabel: tsObj.label,
      onclick: {handler: _clickTrueSpecies, obj: tsObj},
      id:"trueSpeciesItem",
      menuBuilder: (function(onClickHandler, obj){
        return function(){
          return [
            {text:"Add", onclick: {fn: onClickHandler, obj: obj}}
          ];
        };
      })(_addTrueSpecies, tsObj)
    });

    // species ratio
    var srObj = {key: "speciesRatio", label: "Species Ratio"};
    _queryPanel.addQueryItem({
      displayLabel: srObj.label,
      id:"speciesRatioItem",
      onclick: {handler: _clickSpeciesRatio, obj: srObj},
      menuBuilder: (function(onClickHandler, obj){
        return function(){
          return [
            {text:"Add", onclick: {fn: onClickHandler, obj: obj}}
          ];
        }
      })(_addSpeciesRatio, srObj)
    });

    // recurses through the assay hierarchy and builds menu items
    function recurseAssays(parent)
    {
      var parentItem = {};

      parentItem.text = parent.displayLabel;

      // add click handler
      if(!parent.isAbstract)
      {
        parentItem.onclick = {
          fn: _addAssayHandler,
          obj: {
            key: parent.type,
            label: parent.displayLabel
          }
        };
      }

      var children = parent.children;
      if(children.length > 0)
      {
        var submenu = {};
        submenu.id = parent.id;
        submenu.itemdata = [];

        for(var i=0; i<children.length; i++)
        {
          var child = children[i];
          var childItem = recurseAssays(child);
          submenu.itemdata.push(childItem);
        }

        parentItem.submenu = submenu;
      }

      return parentItem;
    }

    var assayMenuData = [];
    assayMenuData.push(recurseAssays(assayTree[0]));

    _queryPanel.addQueryItem({
      displayLabel: "Assay",
      id: "assayItem",
      menuBuilder: (function(menuData){
        return function(){
          return menuData;
        };
      })(assayMenuData)
    });
  }

  /**
   * Builds the column information (pre-render) for the table
   * in the the QueryPanel.
   */
  function _buildColumns()
  {
    // Define the columns for the mosquito attributes
    // (uses a new instance of Mosquito as a template)
    var mosquito = new Mojo.$.dss.vector.solutions.entomology.Mosquito();

    // entity definition
    var mosquitoType = mosquito.getType();
    var mosquitoEntity = new MDSS.QueryXML.Entity(mosquitoType, mosquitoType);
    _queryXML.addEntity(mosquitoEntity);

    // generation
    _mosquitoColumns.push({
      key: mosquito.getGenerationMd().getName()+".displayLabel",
      label: mosquito.getGenerationMd().getDisplayLabel()
    });

    // isofemale
    _mosquitoColumns.push({
      key: mosquito.getIsofemaleMd().getName(),
      label: mosquito.getIsofemaleMd().getDisplayLabel()
    });

    // sex
    _mosquitoColumns.push({
      key: mosquito.getSexMd().getName()+".displayLabel",
      label: mosquito.getSexMd().getDisplayLabel()
    });

    // test date
    _mosquitoColumns.push({
      key: mosquito.getTestDateMd().getName(),
      label: mosquito.getTestDateMd().getDisplayLabel()
    });
  }

  /**
   * Adds a layer of the given type to the map.
   * The map is not refreshed.
   */
  function _addLayer(savedSearch, type)
  {
    var request = new MDSS.Request({
      type: type,
      onSuccess: function(layerId){

        layerId = MDSS.util.stripWhitespace(layerId);
        _queryPanel.addDefinedLayer(layerId, this.type);
      }
    });

    var selectSearchId = savedSearch.getId();
    Mojo.$.dss.vector.solutions.query.LayerController.createSummary(request, selectSearchId, type);
  }

  /**
   * Locks a layer and its components to put them
   * in edit mode.
   */
  function _editLayer(layerId, type)
  {
    var controller = Mojo.$.dss.vector.solutions.query.LayerController;

    var request = new MDSS.Request({
      layerId: layerId,
      controller: controller,
      onSuccess: function(html){

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
        header.innerHTML = '<h3>'+MDSS.Localized.Update+'</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        modal.setBody(outer);
        modal.render(document.body);

        eval(executable);

        var update = MDSS.util.curry(_updateSummaryListener, modal);
        var canceled = MDSS.util.curry(_cancelSummaryListener, modal);
        this.controller.setUpdateSummaryListener(update);
        //this.controller.setCancelSummaryListener(canceled);
      }
    });

    controller.editSummary(request, layerId);
  }

  function _updateSummaryListener(modal, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){

        this.modal.destroy();
      }
    });

    return request;
  }

  function _cancelSummaryListener(modal)
  {
  	modal.destroy();
  }

  function _deleteLayer()
  {

  }

  /**
   * Initializes the configuration for the QueryPanel to
   * Query on Entomology.
   */
  function _initialize(assayTree, queryList)
  {
    _queryPanel = new MDSS.QueryPanel('queryPanel', 'mapPanel', {
      executeQuery: _executeQuery,
      mapQuery: _mapQuery,
      saveQuery: _saveQuery,
      loadQuery: _loadQuery,
      addLayer: _addLayer,
      editLayer: _editLayer,
      deleteLayer: _deleteLayer
    });

    for(var i=0; i<queryList.length; i++)
    {
      _queryPanel.addAvailableQuery(queryList[i]);
    }

    _queryXML = new MDSS.QueryXML.Query();

    _buildQueryItems(assayTree);

    _buildColumns();
  }

  /**
   * Renders the QueryPanel to query on Entomology.
   */
  function _render()
  {
    // render the panel
    _queryPanel.render();

    // modify the right panel to accept GeoEntity data as a list
    var rightUnit = _queryPanel.getQueryRightUnit();
    var body = rightUnit.body;
    var ul = document.createElement('ul');
    YAHOO.util.Dom.addClass(ul, 'geoEntityPanelList');
    body.appendChild(ul);

  	// add pre-configured columns
    for(var i=0; i<_mosquitoColumns.length; i++)
    {
      var column = _mosquitoColumns[i];
      _addAttribute(column);
    }
  }

  // public methods/proeprties
  return {
    initialize : _initialize,
    render: _render
  };

})();