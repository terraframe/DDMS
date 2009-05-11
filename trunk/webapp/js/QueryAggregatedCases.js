MDSS.QueryAggregatedCases = (function(){

  var _selectSearch = null;

  var _preconfiguredColumns = [];

  var _queryPanel = null;

  // Ref to instance of AggregatedCase (used as template for display labels)
  var _aggregatedCase = null;

  var _geoEntityQueryType = null;


  // START: query objects that dictate state of the query.
  var _startDate = null;
  var _endDate = null;

  var _startAgeGroupBySel = null;
  var _endAgeGroupBySel = null;
  var _ageGroupCriteria = {};

  var _visibleSelectables = {};
  var _visibleAggregateSelectables = {};
  var _visibleGroupBySelectables = {};

  var _geoEntityTypes = {};
  var _geoEntitySelectables = {};

  var _gridEntities = {};
  var _gridSelectables = {};
  var _gridAggregateSelectables = {};
  var _gridGroupBySelectables = {};

  // END: query objects

  /**
   * Loads available queries.
   */
  function _loadQuery(savedSearchId)
  {
    var request = new MDSS.Request({
      onSuccess: function(savedSearchJSON){
        var savedSearchObj = Mojo.util.getObject(savedSearchJSON);

        _queryPanel.setCurrentSavedSearch(savedSearchObj);

        // set the XML

        // set the layers
      }
    });

    Mojo.$.dss.vector.solutions.query.AggregatedCasesSearch.get(request, savedSearchId);
  }

  /**
   * Saves the query.
   */
  function _saveQueryListener(modal, params, action)
  {
    var geoEntityQueryType = _geoEntityQueryType;

    var queryXML = _constructQuery();
    var xml = queryXML.getXML();

    var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
    view.setQueryName(params['savedQueryView.queryName']);
    view.setQueryXml(xml);
    view.setThematicLayer(geoEntityQueryType);

    var request = new MDSS.Request({
      modal:modal,
      onSuccess: function(savedSearchJSON){

        var savedSearchObj = Mojo.util.getObject(savedSearchJSON);

        var obj = {id: savedSearchObj.id, name: savedSearchObj.queryName };
        _queryPanel.addAvailableQuery(obj);
        _queryPanel.setCurrentSavedSearch(savedSearchObj);
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.QueryController.saveAggregatedCasesQuery(request, view);
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
    var controller = Mojo.$.dss.vector.solutions.query.QueryController;
    var request = new MDSS.Request({
      controller: controller,
      onSuccess: function(html)
      {

        var modal = _createModal(html, MDSS.Localized.Query.Save);

        var saved = MDSS.util.curry(_saveQueryListener, modal);
        var canceled = MDSS.util.curry(_cancelQueryListener, modal);
        this.controller.setSaveAggregatedCasesQueryListener(saved);
        this.controller.setCancelQueryListener(canceled);
      }
    });

    controller.newAggregatedCasesQuery(request);
  }

  /**
   * Final function called before query is executed.
   * Any last minute cleanup is done here. The this
   * reference is that of the QueryPanel.
   */
  function _executeQuery()
  {
  	var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;

    // calculate the date criteria
    var startDateEl = this.getStartDate();
    var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
    if(startDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(startDate);

      var attribute = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTDATE);
      var selectable = new MDSS.QueryXML.Selectable(attribute);
      var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
      _startDate = startDateCondition;
    }
    else
    {
      _startDate = null;
    }

    var endDateEl = this.getEndDate();
    var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
    if(endDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(endDate);

      var attribute = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTDATE);
      var selectable = new MDSS.QueryXML.Selectable(attribute);
      var endDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, formatted);

      _endDate = endDateCondition;
    }
    else
    {
      _endDate = null;
    }

    // execute the query
    var queryXML = _constructQuery();
    var xml = queryXML.getXML();

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
      }
    });

    var geoEntityQueryType = _geoEntityQueryType;
    Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.queryAggregatedCase(request, xml, geoEntityQueryType);
  }

  /**
   * Handler called to generate a map with a thematic variable.
   */
  function _mapQuery()
  {
    var geoEntityQueryClass = _geoEntityQueryType;
    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;

    var types = [];
    types.push(geoEntityQueryClass);
    types.push(aggregatedCase);

    var selectables = [];
    //selectables.push(types[0]+'_geoId');

    var queryXML = _constructQuery();
    var xml = queryXML.getXMLForMap(types, selectables);

    var request = new MDSS.Request({
      queryPanelRef: this,
      onSuccess : function(layers){
        var layersObj = Mojo.util.getObject(layers);
        this.queryPanelRef.createMap(layersObj);
      }
    });

    var layerIds = this.getSelectedLayers();
    var savedSearch = this.getCurrentSavedSearch();
    var savedSearchId = (savedSearch != null ? savedSearch.id : null);
    Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.mapQuery(request, xml, geoEntityQueryClass, layerIds, savedSearchId);
  }

  /**
   * Constructs the query with all the subcomponents.
   */
  function _constructQuery()
  {
  	var queryXML = new MDSS.QueryXML.Query();

    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    var aggregatedCaseQuery = new MDSS.QueryXML.Entity(aggregatedCase, aggregatedCase);
    queryXML.addEntity(aggregatedCaseQuery);

    var groupBy = queryXML.getGroupBy();


    // Age Group
    var ageGroupIds = Mojo.util.getKeys(_ageGroupCriteria);
    var ageGroupOr = null;
    if(ageGroupIds.length > 0)
    {
      var ageGroupOr = new MDSS.QueryXML.Or();

      // add each age group
      for(var i=0; i<ageGroupIds.length; i++)
      {
        var id = ageGroupIds[i];
        var compositeCondition = _ageGroupCriteria[id];

        ageGroupOr.addCondition(id, compositeCondition);
      }
    }

    // group by age (age will always be included)
    if(_startAgeGroupBySel != null)
    {
      groupBy.addSelectable('startAge', _startAgeGroupBySel);
    }

    if(_endAgeGroupBySel != null)
    {
      groupBy.addSelectable('endAge', _endAgeGroupBySel);
    }

    // Visible Attributes
    var selNames = Mojo.util.getKeys(_visibleSelectables);
    for(var i=0; i<selNames.length; i++)
    {
      var name = selNames[i];
      var selectable = _visibleSelectables[name];

      queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name, selectable);
    }

    var aggNames = Mojo.util.getKeys(_visibleAggregateSelectables);
    for(var i=0; i<aggNames.length; i++)
    {
      var name = aggNames[i];
      var selectable = _visibleAggregateSelectables[name];

      queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name, selectable);
    }

    var gbNames = Mojo.util.getKeys(_visibleGroupBySelectables);
    for(var i=0; i<gbNames.length; i++)
    {
      var name = gbNames[i];
      var selectable = _visibleGroupBySelectables[name];

      groupBy.addSelectable(name, selectable);
    }

    // start and end dates
    var conditions = [];
    if(_startDate != null)
    {
      conditions.push(_startDate);
    }

    if(_endDate != null)
    {
      conditions.push(_endDate);
    }

    var dateAndOr = null;
    if(conditions.length === 2)
    {
      dateAndOr = new MDSS.QueryXML.And();
      dateAndOr.addCondition('date1', conditions[0]);
      dateAndOr.addCondition('date2', conditions[1]);
    }
    else if(conditions.length === 1)
    {
      dateAndOr = new MDSS.QueryXML.Or();
      dateAndOr.addCondition('date1', conditions[0]);
    }

    // now all possible criteria, both age group and date
    if(ageGroupOr != null && dateAndOr != null)
    {
      var and = new MDSS.QueryXML.And();
      and.addCondition('ageGroup', ageGroupOr);
      and.addCondition('dateRange', dateAndOr);

      var composite = new MDSS.QueryXML.CompositeCondition(and);

      aggregatedCaseQuery.setCondition(composite);
    }
    else if(ageGroupOr != null)
    {
      var composite = new MDSS.QueryXML.CompositeCondition(ageGroupOr);
      aggregatedCaseQuery.setCondition(composite);
    }
    else if(dateAndOr != null)
    {
      var composite = new MDSS.QueryXML.CompositeCondition(dateAndOr);
      aggregatedCaseQuery.setCondition(composite);
    }

    // geo entity
    var entities = Mojo.util.getValues(_geoEntityTypes);
    for(var i=0; i<entities.length; i++)
    {
      var entity = entities[i];
      queryXML.addEntity(entity);
    }

    var geoSelectables = Mojo.util.getKeys(_geoEntitySelectables);
    for(var i=0; i<geoSelectables.length; i++)
    {
      var name = geoSelectables[i];
      var selectable = _geoEntitySelectables[name];

      queryXML.addSelectable(name, selectable);
    }

    // grid
    var eAliases = Mojo.util.getKeys(_gridEntities);
    for(var i=0; i<eAliases.length; i++)
    {
      var alias = eAliases[i];
      var entity = _gridEntities[alias];
      queryXML.addEntity(entity);
    }

    var sAliases = Mojo.util.getKeys(_gridSelectables);
    for(var i=0; i<sAliases.length; i++)
    {
      var alias = sAliases[i];
      var selectable = _gridSelectables[alias];
      queryXML.addSelectable(alias, selectable);
    }

    var gAliases = Mojo.util.getKeys(_gridGroupBySelectables);
    for(var i=0; i<gAliases.length; i++)
    {
      var alias = gAliases[i];
      var selectable = _gridGroupBySelectables[alias];
      groupBy.addSelectable(alias, selectable);
    }

    var gridAggAliases = Mojo.util.getKeys(_gridAggregateSelectables);
    for(var i=0; i<gridAggAliases.length; i++)
    {
      var alias = gridAggAliases[i];
      var selectable = _gridAggregateSelectables[alias];

      queryXML.addSelectable(alias, selectable);
    }

    return queryXML;
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
     _geoEntityQueryType = type;
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
    var geoEntityQuery = _geoEntityTypes[type];
    if(!Mojo.util.isObject(geoEntityQuery))
    {
      geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
      _geoEntityTypes[type] = geoEntityQuery;

      // selectables (entityName, geoId and spatial attribute)
      var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getEntityNameMd().getName(), entityNameColumn);
      var entityNameSel = new MDSS.QueryXML.Selectable(entityNameAttr);
      _geoEntitySelectables[type+'_'+entityNameAttr.getName()] = entityNameSel;

      var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName(), geoIdColumn);
      var geoIdSel = new MDSS.QueryXML.Selectable(geoIdAttr);
      _geoEntitySelectables[type+'_'+geoIdAttr.getName()] = geoIdSel;
    }

    // add restriction based on geoId
    var attribute = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName());
    var selectable = new MDSS.QueryXML.Selectable(attribute);
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
      var filterType = '';

      _selectSearch = new MDSS.SingleSelectSearch();
      _selectSearch.setSelectHandler(_selectHandler);
      _selectSearch.setTreeSelectHandler(_selectHandler);
      _selectSearch.setFilter(filterType);
      _selectSearch.render();
    }
  }

  /**
   * Handler to toggle grouping by startAge/endAge.
   */
  function _groupByAgeGroupHandler(e)
  {
    var check = e.target;

    if(check.checked)
    {
      var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;
      var startAge = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTAGE, aggregatedCase.STARTAGE);
      var startAgeSel = new MDSS.QueryXML.Selectable(startAge);

      var endAge = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.ENDAGE, aggregatedCase.ENDAGE);
      var endAgeSel = new MDSS.QueryXML.Selectable(endAge);

      _startAgeGroupBySel = startAgeSel;
      _endAgeGroupBySel = endAgeSel;
    }
    else
    {
      _startAgeGroupBySel = null;
      _endAgeGroupBySel = null;
    }
  }

  /**
   * Handler for when an age group checkbox is selected as WHERE criteria.
   */
  function _ageGroupCheckHandler(e, ageGroup)
  {
    var check = e.target;

    if(check.checked)
    {
      // All Ages was selected so simply clear the criteria
      // and other checkboxes
      if(ageGroup == null)
      {
        var checks = YAHOO.util.Selector.query('input[type="checkbox"]', 'ageGroupsList');
        for(var i=0; i<checks.length; i++)
        {
          var check = checks[i];
          if(check.checked && check.id !== 'allAgesCheck')
          {
            check.checked = false;
          }
        }

        _ageGroupCriteria = {};

        return;
      }
      else
      {
        // uncheck the All Ages option
        document.getElementById('allAgesCheck').checked = false;
      }

      var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;

      var leftSide = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTAGE, aggregatedCase.STARTAGE);
      var leftSelectable = new MDSS.QueryXML.Selectable(leftSide);
      var leftCondition = new MDSS.QueryXML.BasicCondition(leftSelectable, MDSS.QueryXML.Operator.GE, ageGroup.startAge);

      var rightSide = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.ENDAGE, aggregatedCase.ENDAGE);
      var rightSelectable = new MDSS.QueryXML.Selectable(rightSide);
      var rightCondition = new MDSS.QueryXML.BasicCondition(rightSelectable, MDSS.QueryXML.Operator.LE, ageGroup.endAge);

      var and = new MDSS.QueryXML.And();
      and.addCondition('startAge', leftCondition);
      and.addCondition('endAge', rightCondition);
      var andCondition = new MDSS.QueryXML.CompositeCondition(and);

      _ageGroupCriteria[ageGroup.id] = andCondition;
    }
    else if(ageGroup != null)
    {
      delete _ageGroupCriteria[ageGroup.id];
    }
  }

  /**
   * Helper method to add AggregatedCase attributes to selectables and as a column.
   */
  function _addVisibleAttribute(attributeObj)
  {
    var column = new YAHOO.widget.Column(attributeObj);
    column = _queryPanel.insertColumn(column);

    var aggregatedCaseClass = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;

    var attribute = new MDSS.QueryXML.Attribute(aggregatedCaseClass, attributeObj.key, attributeObj.key);
    var selectable = new MDSS.QueryXML.Selectable(attribute);

    _visibleSelectables[attributeObj.key] = selectable;
  }

  /**
   * Adds a grid attribute as a selectable.
   */
  function _addGridAttribute(attributeObj)
  {
    var column = new YAHOO.widget.Column(attributeObj);
    column = _queryPanel.insertColumn(column);

    var optionName = attributeObj.key;

    /*
     * Relationship
     */
    var meta = attributeObj.meta;

    var relType = meta.relType;
    var relAlias = _getGridRelAlias(relType, optionName);
    var relEntity = new MDSS.QueryXML.Entity(relType, relAlias);

    _gridEntities[relAlias] = relEntity;

    // selectable
    var attribute = new MDSS.QueryXML.Attribute(relAlias, meta.relAttribute, optionName);
    var selectable = new MDSS.QueryXML.Selectable(attribute);
    _gridSelectables[relAlias] = selectable;

    /*
     * Business
     */
    var busType = attributeObj.type;
    var busAlias = _getGridBusAlias(relType, optionName, busType);

    // entity and criteria
    var entity = new MDSS.QueryXML.Entity(busType, busAlias);
    var cAttribute = new MDSS.QueryXML.Attribute(busAlias, attributeObj.attributeName);
    var cSelectable = new MDSS.QueryXML.Selectable(cAttribute);

    var condition = new MDSS.QueryXML.BasicCondition(cSelectable, MDSS.QueryXML.Operator.EQ, optionName);
    entity.setCondition(condition);

    _gridEntities[busAlias] = entity;
  }

  /**
   * Returns the alias for a grid relationship.
   */
  function _getGridRelAlias(relType, optionName)
  {
    var relTypeName = _extractTypeName(relType);
    return relTypeName+'_'+optionName;
  }

  /**
   * Returns the alias for a grid business.
   */
  function _getGridBusAlias(relType, optionName, busType)
  {
    var relTypeName = _extractTypeName(relType);
    var busTypeName = _extractTypeName(busType);
    return relTypeName+'_'+optionName+'_'+busTypeName;
  }

  function _extractTypeName(type)
  {
    var ind = type.lastIndexOf('.');
    var typeName = type.substring(ind+1);
    return typeName;
  }

  /**
   * Removes an attribute as a selectable and column.
   */
  function _removeVisibleAttribute(attributeName, removeColumn, removeSelectable)
  {
  	if(removeSelectable === true)
  	{
      delete _visibleSelectables[attributeName];
  	}

  	// remove all possible query references
    delete _visibleAggregateSelectables[attributeName];
    delete _visibleGroupBySelectables[attributeName];

    if(removeColumn === true)
    {
      var column = _queryPanel.getColumn(attributeName);
      _queryPanel.removeColumn(column);
    }
  }

  /**
   * Removes a grid attribute from the selectables and column.
   */
  function _removeGridAttribute(relAlias, busAlias, attributeName, removeColumn, removeSelectable)
  {
  	if(removeSelectable === true)
  	{
      delete _gridSelectables[relAlias];
  	}

  	// remove all possible query references
    delete _gridAggregateSelectables[relAlias];
    delete _gridGroupBySelectables[relAlias];

    if(removeColumn === true)
    {
      var column = _queryPanel.getColumn(attributeName);
      _queryPanel.removeColumn(column);

      delete _gridEntities[busAlias];
    }
  }

  /**
   * Handler to toggle visible attributes as selectables
   * to the AggregatedCase query.
   */
  function _visibleAttributeHandler(e, attributeObj)
  {
    var check = e.target;
    if(check.checked)
    {
      _addVisibleAttribute(attributeObj);
      check.nextSibling.disabled = false;
    }
    else
    {
      _removeVisibleAttribute(attributeObj.key, true, true);

      var select = check.nextSibling;
      select.selectedIndex = 0;
      select.disabled = true;
    }
  }

  /**
   * Handler when a new grid attribute is checked/unchecked.
   */
  function _gridAttributeHandler(e, attributeObj)
  {
    var check = e.target;
    if(check.checked)
    {
      _addGridAttribute(attributeObj);
      check.nextSibling.disabled = false;
    }
    else
    {
      var optionName = attributeObj.key;

      var busType = attributeObj.type;
      var relType = attributeObj.meta.relType;

      var relAlias = _getGridRelAlias(relType, optionName);
      var busAlias = _getGridBusAlias(relType, optionName, busType);

      _removeGridAttribute(busType, relType, optionName, true, true);

      var select = check.nextSibling;
      select.selectedIndex = 0;
      select.disabled = true;
    }
  }

  function _gridAggregateHandler(e, obj)
  {
    var func = obj.func;
    var attributeObj = obj.attributeObj;

    var optionName = attributeObj.key;
    var relAttribute = attributeObj.meta.relAttribute;

    var busType = attributeObj.type;
    var relType = attributeObj.meta.relType;

    var relAlias = _getGridRelAlias(relType, optionName);
    var busAlias = _getGridBusAlias(relType, optionName, busType);

    var option = e.target;

    var attribute = new MDSS.QueryXML.Attribute(relAlias, relAttribute, optionName);
    var selectable = new MDSS.QueryXML.Selectable(attribute);

    // special cases
    if(func === 'GB')
    {
  	  _removeGridAttribute(relAlias, busAlias, optionName, false, false);
      _gridSelectables[relAlias] = selectable;
      _gridGroupBySelectables[relAlias] = selectable;
      return;
    }
    else if(func === '')
    {
      // Use regular selectable (this is just here for clarity).
  	  _removeGridAttribute(relAlias, busAlias, optionName, false, true);
      _gridSelectables[relAlias] = selectable;
      return;
    }

    // aggregate functions
    var aggFunc = null;
    if(func === 'SUM')
    {
      aggFunc = new MDSS.QueryXML.SUM(selectable, optionName);
    }
    else if(func === 'MIN')
    {
      aggFunc = new MDSS.QueryXML.MIN(selectable, optionName);
    }
    else if(func === 'MAX')
    {
      aggFunc = new MDSS.QueryXML.MAX(selectable, optionName);
    }
    else if(func === 'AVG')
    {
      aggFunc = new MDSS.QueryXML.AVG(selectable, optionName);
    }

  	_removeGridAttribute(relAlias, busAlias, optionName, false, true);

    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
    _gridAggregateSelectables[relAlias] = aggSelectable;
  }

  /**
   * Handler when someone selects an aggregate function
   * on a visible attribute.
   */
  function _visibleAggregateHandler(e, obj)
  {
    var func = obj.func;
    var attributeName = obj.attribute;


    var option = e.target;

    var attribute = new MDSS.QueryXML.Attribute(Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS, attributeName, attributeName);
    var selectable = new MDSS.QueryXML.Selectable(attribute);

    // special cases
    if(func === 'GB')
    {
  	  _removeVisibleAttribute(attributeName, false, false);
      _visibleSelectables[attributeName] = selectable;
      _visibleGroupBySelectables[attributeName] = selectable;
      return;
    }
    else if(func === '')
    {
      // Use regular selectable (this is just here for clarity).
  	  _removeVisibleAttribute(attributeName, false, true);
      _visibleSelectables[attributeName] = selectable;
      return;
    }

    // aggregate functions
    var aggFunc = null;
    if(func === 'SUM')
    {
      aggFunc = new MDSS.QueryXML.SUM(selectable, attributeName);
    }
    else if(func === 'MIN')
    {
      aggFunc = new MDSS.QueryXML.MIN(selectable, attributeName);
    }
    else if(func === 'MAX')
    {
      aggFunc = new MDSS.QueryXML.MAX(selectable, attributeName);
    }
    else if(func === 'AVG')
    {
      aggFunc = new MDSS.QueryXML.AVG(selectable, attributeName);
    }

  	_removeVisibleAttribute(attributeName, false, true);

    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
    _visibleAggregateSelectables[attributeName] = aggSelectable;
  }

  /**
   * Builds the query items for the left column.
   */
  function _buildQueryItems(ageGroups, visibleAttributes, orderedGrids)
  {
  	/*
  	 * Target
  	 */
    // area (geo entity search)
    _queryPanel.addQueryItem({
      html: MDSS.Localized.Target_Search+' <img src="./imgs/icons/world.png"/>',
      onclick: {handler: _displaySearch},
      id: "areaItem"
    });

    /*
     * Age Group
     */
    var ageGroupDiv = document.createElement('div');

    var ageSpan = document.createElement('span');
    ageSpan.innerHTML = MDSS.Localized.Age_Group;

    // group by
    var groupBy = document.createElement('ul');
    var groupByLi = document.createElement('li');
    var groupBySpan = document.createElement('span');

    var groupByCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(groupByCheck, 'type', 'checkbox');
    // send null to signal this is for All Ages
    YAHOO.util.Event.on(groupByCheck, 'click', _groupByAgeGroupHandler, null, this);

    groupBySpan.innerHTML = 'GB';

    groupByLi.appendChild(groupByCheck);
    groupByLi.appendChild(groupBySpan);
    groupBy.appendChild(groupByLi);


    var groups = document.createElement('ul');
    YAHOO.util.Dom.setAttribute(groups, 'id', 'ageGroupsList');

    // default All Ages option
    var allLi = document.createElement('li');
    var allSpan = document.createElement('span');
    allSpan.innerHTML = MDSS.Localized.All_Ages;

    var allCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(allCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(allCheck, 'id', 'allAgesCheck');
    // send null to signal this is for All Ages
    YAHOO.util.Event.on(allCheck, 'click', _ageGroupCheckHandler, null, this);

    allLi.appendChild(allCheck);
    allLi.appendChild(allSpan);

    groups.appendChild(allLi);

    for(var i=0; i<ageGroups.length; i++)
    {
      var group = ageGroups[i];

      var li = document.createElement('li');
      var span = document.createElement('span');
      span.innerHTML = group.startAge + " - " + group.endAge;

      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(check, 'value', group.id);
      YAHOO.util.Event.on(check, 'click', _ageGroupCheckHandler, group, this);

      li.appendChild(check);
      li.appendChild(span);

      groups.appendChild(li);
    }

    ageGroupDiv.appendChild(ageSpan);
    ageGroupDiv.appendChild(groupBy);
    ageGroupDiv.appendChild(groups);

    _queryPanel.addQueryItem({
      html: ageGroupDiv,
      id:"ageGroupItem"
    });

    /*
     * Visible Attributes
     */
    var visibleDiv = document.createElement('div');
    var visibleSpan = document.createElement('span');
    visibleSpan.innerHTML = MDSS.Localized.Aggregated_Case;

    visibleDiv.appendChild(visibleSpan);

    var visibleUl = document.createElement('ul');
    for(var i=0; i<visibleAttributes.length; i++)
    {
      var visible = visibleAttributes[i];
      var md = _aggregatedCase.getAttributeDTO(visible).getAttributeMdDTO();
      var display = md.getDisplayLabel();

      var li = document.createElement('li');

      var span = document.createElement('span');
      span.innerHTML = display;

      // used to build a column
      var attributeObj = {
      	key: visible,
      	label: display
      }

      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(check, 'value', visible);
      YAHOO.util.Event.on(check, 'click', _visibleAttributeHandler, attributeObj, this);

      var select = document.createElement('select');
      var options =  ['', 'GB', 'SUM', 'MIN', 'MAX', 'AVG'];
      for(var j=0; j<options.length; j++)
      {
      	var option = options[j];
        var optionEl = document.createElement('option');
        optionEl.innerHTML = option;
        YAHOO.util.Dom.setAttribute(optionEl, 'value', option);

        var obj = {
          func: option,
          attribute: visible
        };

        YAHOO.util.Event.on(optionEl, 'click', _visibleAggregateHandler, obj, this);

        select.appendChild(optionEl);
      }
      select.disabled = true; // default (must be checked to enabled)

      li.appendChild(check);
      li.appendChild(select);
      li.appendChild(span);

      visibleUl.appendChild(li);
    }

    visibleDiv.appendChild(visibleUl);

    _queryPanel.addQueryItem({
      html: visibleDiv,
      id: 'visibleAttributesItem'
    });

    /*
     * Grids
     */
    for(var i=0; i<orderedGrids.length; i++)
    {
      var order = orderedGrids[i];

      var gridDiv = document.createElement('div');
      var labelSpan = document.createElement('span');
      labelSpan.innerHTML = order.label;

      gridDiv.appendChild(labelSpan);

      var ul = document.createElement('ul');
      YAHOO.util.Dom.addClass(ul, 'gridList');

      var options = order.options;
      for(var j=0; j<options.length; j++)
      {
      	var option = options[j];
      	var display = option.display;
      	var optionName = option.optionName;
      	var type = option.type;
      	var attributeName = option.attributeName;

        var li = document.createElement('li');

        var span = document.createElement('span');
        span.innerHTML = display;

        // used to build a column
        var attributeObj = {
          key: optionName,
          attributeName: attributeName,
          label: display,
          type: type,
          meta: order
        }

        var check = document.createElement('input');
        YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
        YAHOO.util.Dom.setAttribute(check, 'value', optionName);
        YAHOO.util.Event.on(check, 'click', _gridAttributeHandler, attributeObj, this);

        var select = document.createElement('select');
        var aggOptions =  ['', 'GB', 'SUM', 'MIN', 'MAX', 'AVG'];
        for(var k=0; k<aggOptions.length; k++)
        {
          var aggOption = aggOptions[k];
          var optionEl = document.createElement('option');
          optionEl.innerHTML = aggOption;
          YAHOO.util.Dom.setAttribute(optionEl, 'value', aggOption);

          var obj = {
            func: aggOption,
            attributeObj: attributeObj
          };

          YAHOO.util.Event.on(optionEl, 'click', _gridAggregateHandler, obj, this);

          select.appendChild(optionEl);
        }
        select.disabled = true; // default (must be checked to enabled)

        li.appendChild(check);
        li.appendChild(select);
        li.appendChild(span);

        ul.appendChild(li);
      }

      gridDiv.appendChild(ul);

      _queryPanel.addQueryItem({
        html: gridDiv,
        id: "gridItem_"+1
      });
    }
  }

  /**
   * Builds the column information (pre-render) for the table
   * in the the QueryPanel.
   */
  function _buildColumns()
  {
    // startAge
    _preconfiguredColumns.push({
      key: _aggregatedCase.getStartAgeMd().getName(),
      label: _aggregatedCase.getStartAgeMd().getDisplayLabel()
    });

    // endAge
    _preconfiguredColumns.push({
      key: _aggregatedCase.getEndAgeMd().getName(),
      label: _aggregatedCase.getEndAgeMd().getDisplayLabel()
    });
  }

  /**
   * Adds a layer of the given type to the map.
   * The map is not refreshed.
   */
  function _addLayer(type)
  {
    var request = new MDSS.Request({
      type: type,
      onSuccess: function(layerId){

        layerId = MDSS.util.stripWhitespace(layerId);
        _queryPanel.addDefinedLayer(layerId, this.type);
      }
    });

    var savedSearch = this.getCurrentSavedSearch();
    var savedSearchId = savedSearch.id;
    Mojo.$.dss.vector.solutions.query.MappingController.createLayer(request, savedSearchId, type);
  }

  /**
   * Locks a layer and its components to put them
   * in edit mode.
   */
  function _editLayer(layerId)
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;

    var request = new MDSS.Request({
      layerId: layerId,
      controller: controller,
      onSuccess: function(html){

        var modal = _createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.curry(_updateLayerListener, modal);
        var canceled = MDSS.util.curry(_cancelLayerListener, modal, layerId, true);
        this.controller.setUpdateLayerListener(update);
        this.controller.setCancelLayerListener(canceled);
      }
    });

    controller.editLayer(request, layerId);
  }

  function _updateLayerListener(modal, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){

        this.modal.destroy();
      }
    });

    return request;
  }

  function _cancelLayerListener(modal, layerId, unlock)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.MappingController.cancelLayer(request, layerId, unlock);
  }

  /**
   *
   */
  function _deleteLayer()
  {

  }

  /**
   * Listener for when a use requesets a new AbstractCategory.
   * The returned HTML form is appended to the list of editable
   * categories.
   */
  function _newCategoryListener(params, action)
  {
    var request = new MDSS.Request({
      onSuccess: function(html)
      {
        _queryPanel.addCategoryHTML(html);
      }
    });

    return request;
  }

  function _editThematicVariable(modal, layerId, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function()
      {
        this.modal.destroy();
      }
    });

    var thematicVar = params['variable'][0];
    var categories = _queryPanel.scrapeCategories();
    Mojo.$.dss.vector.solutions.query.MappingController.updateThematicVariable(request, layerId, thematicVar, categories);
  }

  /**
   * Handler to edit the styles associated with a thematic variable.
   */
  function _editVariableStyles()
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;
    var savedSearch = this.getCurrentSavedSearch();
    var thematicLayerId = savedSearch.thematicLayerId;

    var request = new MDSS.Request({
      controller: controller,
      thematicLayerId: thematicLayerId,
      onSuccess: function(html){

        var modal = _createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.curry(_editThematicVariable, modal, this.thematicLayerId);
        var canceled = MDSS.util.curry(_cancelLayerListener, modal, this.thematicLayerId,true);
        this.controller.setUpdateThematicVariableListener(update);
        this.controller.setCancelLayerListener(canceled);

        Mojo.$.dss.vector.solutions.query.RangeCategoryController.setNewInstanceListener(_newCategoryListener);
        Mojo.$.dss.vector.solutions.query.NonRangeCategoryController.setNewInstanceListener(_newCategoryListener);
      }
    });

    var thematicVars = Mojo.util.getKeys(_visibleSelectables);

    controller.editThematicLayer(request, thematicLayerId, thematicVars);
  }

  /**
   * Creates a modal with the given HTML as its body and the given title
   * as the modal title, wrapped in an H3.
   */
  function _createModal(html, title)
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
  }

  /**
   * Initializes the configuration for the QueryPanel to
   * Query on Aggregated Cases.
   */
  function _initialize(ageGroups, visibleAttributes, orderedGrids, queryList)
  {
    _queryPanel = new MDSS.QueryPanel('queryPanel', 'mapPanel', {
      executeQuery: _executeQuery,
      mapQuery: _mapQuery,
      saveQuery: _saveQuery,
      loadQuery: _loadQuery,
      addLayer: _addLayer,
      editLayer: _editLayer,
      deleteLayer: _deleteLayer,
      editVariableStyles: _editVariableStyles
    });

    for(var i=0; i<queryList.length; i++)
    {
      _queryPanel.addAvailableQuery(queryList[i]);
    }

    _aggregatedCase = new Mojo.$.dss.vector.solutions.surveillance.AggregatedCase();

    _buildQueryItems(ageGroups, visibleAttributes, orderedGrids);

    _buildColumns();

  }

  /**
   * Renders the QueryPanel to query on AggregatedCases.
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
    for(var i=0; i<_preconfiguredColumns.length; i++)
    {
      var column = _preconfiguredColumns[i];
      _addVisibleAttribute(column);
    }
  }

  // public methods/proeprties
  return {
    initialize : _initialize,
    render: _render
  };

})();