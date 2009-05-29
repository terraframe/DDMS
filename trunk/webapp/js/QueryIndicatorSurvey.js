/**
 * Class to query for AggregatedCases.
 */
MDSS.QueryIndicatorSurvey = Mojo.Class.create();
MDSS.QueryIndicatorSurvey.prototype = Mojo.Class.extend(MDSS.QueryBase, {

	  initialize : function(ageGroups, visibleAttributes, orderedGrids, queryList)
	  {
	  	MDSS.QueryBase.prototype.initialize.call(this);

	    // list of columns that have been added before a call to render()
	    this._preconfiguredColumns = [];

	    // Ref to instance of AggregatedCase (used as template for display labels)
	    this._aggregatedCase = new Mojo.$.dss.vector.solutions.surveillance.AggregatedCase();;

	    // START: query objects that dictate state of the query.

	    this._startDate = null;
	    this._endDate = null;

	    this._startAgeGroupBySel = null;
	    this._endAgeGroupBySel = null;
	    this._ageGroupCriteria = {};

	    this._visibleSelectables = {};
	    this._visibleAggregateSelectables = {};
	    this._visibleGroupBySelectables = {};

	    this._gridEntities = {};
	    this._gridSelectables = {};
	    this._gridAggregateSelectables = {};
	    this._gridGroupBySelectables = {};

	    this._thematicSearchList = [];  //list of map to search for thematic variable.
	    this._thematicSearchList.push(this._gridEntities);
	    this._thematicSearchList.push(this._gridSelectables);
	    this._thematicSearchList.push(this._gridAggregateSelectables);
	    this._thematicSearchList.push(this._gridGroupBySelectables);

	    // END: query objects

	    for(var i=0; i<queryList.length; i++)
	    {
	      this._queryPanel.addAvailableQuery(queryList[i]);
	    }

	    this._buildQueryItems(ageGroups, visibleAttributes, orderedGrids);

	    this._buildColumns();
	  },

	  /**
	   * Returns the method to save this AggregatedCase search.
	   */
	  _getSaveQueryMethod : function()
	  {
	  	return Mojo.$.dss.vector.solutions.query.AggregatedCasesSearch.saveSearch;
	  },

	  /**
	   * Saves the current state of the QueryXML.
	   */
	  saveQuery : function()
	  {
	    var controller = Mojo.$.dss.vector.solutions.query.QueryController;
	    var request = new MDSS.Request({
	      thisRef: this,
	      controller: controller,
	      onSuccess: function(html)
	      {
	        var modal = this.thisRef._createModal(html, MDSS.Localized.Query.Save);

	        var saved = MDSS.util.bind(this.thisRef, this.thisRef._saveQueryListener, modal);
	        var canceled = MDSS.util.bind(this.thisRef, this.thisRef._cancelQueryListener, modal);

	        this.controller.setSaveAggregatedCasesQueryListener(saved);
	        this.controller.setCancelQueryListener(canceled);
	      }
	    });

	    controller.newAggregatedCasesQuery(request);
	  },

	  /**
	   * Final function called before query is executed.
	   * Any last minute cleanup is done here. The this
	   * reference is that of the QueryPanel.
	   */
	  executeQuery : function()
	  {
	  	var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;

	    // calculate the date criteria
	    var startDateEl = this._queryPanel.getStartDate();
	    var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
	    if(startDate.length > 0)
	    {
	      var formatted = MojoCal.getMojoDateString(startDate);

	      var attribute = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTDATE);
	      var selectable = new MDSS.QueryXML.Selectable(attribute);
	      var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
	      this._startDate = startDateCondition;
	    }
	    else
	    {
	      this._startDate = null;
	    }

	    var endDateEl = this._queryPanel.getEndDate();
	    var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
	    if(endDate.length > 0)
	    {
	      var formatted = MojoCal.getMojoDateString(endDate);

	      var attribute = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTDATE);
	      var selectable = new MDSS.QueryXML.Selectable(attribute);
	      var endDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, formatted);

	      this._endDate = endDateCondition;
	    }
	    else
	    {
	      this._endDate = null;
	    }

	    // execute the query
	    var queryXML = this._constructQuery();
	    var xml = queryXML.getXML();

	    var request = new MDSS.Request({
	      thisRef : this,
	      onSuccess : function(query)
	      {
	        // column key is selectable alias name
	        var columnSet = this.thisRef._queryPanel.getColumnSet();
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
	        this.thisRef._queryPanel.clearAllRecords();

	        this.thisRef._queryPanel.setRowData(jsonData);
	      }
	    });

	    Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.queryAggregatedCase(request, xml, this._geoEntityQueryType);
	  },

	  /**
	   * Handler called to generate a map with a thematic variable.
	   */
	  mapQuery : function()
	  {
	    var queryXML = this._constructQuery();
	    var xml = queryXML.getXML();

	    var request = new MDSS.Request({
	      thisRef: this,
	      onSuccess : function(layers){
	        var layersObj = Mojo.util.getObject(layers);
	        this.thisRef._queryPanel.createMap(layersObj);
	      }
	    });

	    var layerIds = this._queryPanel.getSelectedLayers();
	    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
	    var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");
	    Mojo.$.dss.vector.solutions.query.MappingController.mapAggregatedCaseQuery(request, xml, this._geoEntityQueryType, layerIds, savedSearchId);
	  },

	  /**
	   * Constructs the query with all the subcomponents.
	   */
	  _constructQuery : function()
	  {
	  	var queryXML = MDSS.QueryBase.prototype._constructQuery.call(this); // super

	    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
	    var aggregatedCaseQuery = new MDSS.QueryXML.Entity(aggregatedCase, aggregatedCase);
	    queryXML.addEntity(aggregatedCaseQuery);

	    var groupBy = queryXML.getGroupBy();


	    // Age Group
	    var ageGroupIds = Mojo.util.getKeys(this._ageGroupCriteria);
	    var ageGroupOr = null;
	    if(ageGroupIds.length > 0)
	    {
	      var ageGroupOr = new MDSS.QueryXML.Or();

	      // add each age group
	      for(var i=0; i<ageGroupIds.length; i++)
	      {
	        var id = ageGroupIds[i];
	        var compositeCondition = this._ageGroupCriteria[id];

	        ageGroupOr.addCondition(id, compositeCondition);
	      }
	    }

	    // group by age (age will always be included)
	    if(this._startAgeGroupBySel != null)
	    {
	      groupBy.addSelectable('startAge', this._startAgeGroupBySel);
	    }

	    if(this._endAgeGroupBySel != null)
	    {
	      groupBy.addSelectable('endAge', this._endAgeGroupBySel);
	    }

	    // Visible Attributes
	    var selNames = Mojo.util.getKeys(this._visibleSelectables);
	    for(var i=0; i<selNames.length; i++)
	    {
	      var name = selNames[i];
	      var selectable = this._visibleSelectables[name];

	      queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name, selectable);
	    }

	    var aggNames = Mojo.util.getKeys(this._visibleAggregateSelectables);
	    for(var i=0; i<aggNames.length; i++)
	    {
	      var name = aggNames[i];
	      var selectable = this._visibleAggregateSelectables[name];

	      queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name, selectable);
	    }

	    var gbNames = Mojo.util.getKeys(this._visibleGroupBySelectables);
	    for(var i=0; i<gbNames.length; i++)
	    {
	      var name = gbNames[i];
	      var selectable = this._visibleGroupBySelectables[name];

	      groupBy.addSelectable(name, selectable);
	    }

	    // start and end dates
	    var conditions = [];
	    if(this._startDate != null)
	    {
	      conditions.push(this._startDate);
	    }

	    if(this._endDate != null)
	    {
	      conditions.push(this._endDate);
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

	    // grid
	    var eAliases = Mojo.util.getKeys(this._gridEntities);
	    for(var i=0; i<eAliases.length; i++)
	    {
	      var alias = eAliases[i];
	      var entity = this._gridEntities[alias];
	      queryXML.addEntity(entity);
	    }

	    var sAliases = Mojo.util.getKeys(this._gridSelectables);
	    for(var i=0; i<sAliases.length; i++)
	    {
	      var alias = sAliases[i];
	      var selectable = this._gridSelectables[alias];
	      queryXML.addSelectable(alias, selectable);
	    }

	    var gAliases = Mojo.util.getKeys(this._gridGroupBySelectables);
	    for(var i=0; i<gAliases.length; i++)
	    {
	      var alias = gAliases[i];
	      var selectable = this._gridGroupBySelectables[alias];
	      groupBy.addSelectable(alias, selectable);
	    }

	    var gridAggAliases = Mojo.util.getKeys(this._gridAggregateSelectables);
	    for(var i=0; i<gridAggAliases.length; i++)
	    {
	      var alias = gridAggAliases[i];
	      var selectable = this._gridAggregateSelectables[alias];

	      queryXML.addSelectable(alias, selectable);
	    }

	    return queryXML;
	  },

	  /**
	   * Handler to toggle grouping by startAge/endAge.
	   */
	  _groupByAgeGroupHandler : function(e)
	  {
	    var check = e.target;

	    if(check.checked)
	    {
	      var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;
	      var startAge = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.STARTAGE, aggregatedCase.STARTAGE);
	      var startAgeSel = new MDSS.QueryXML.Selectable(startAge);

	      var endAge = new MDSS.QueryXML.Attribute(aggregatedCase.CLASS, aggregatedCase.ENDAGE, aggregatedCase.ENDAGE);
	      var endAgeSel = new MDSS.QueryXML.Selectable(endAge);

	      this._startAgeGroupBySel = startAgeSel;
	      this._endAgeGroupBySel = endAgeSel;
	    }
	    else
	    {
	      this._startAgeGroupBySel = null;
	      this._endAgeGroupBySel = null;
	    }
	  },

	  /**
	   * Handler for when an age group checkbox is selected as WHERE criteria.
	   */
	  _ageGroupCheckHandler : function(e, ageGroup)
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

	        this._ageGroupCriteria = {};

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

	      this._ageGroupCriteria[ageGroup.id] = andCondition;
	    }
	    else if(ageGroup != null)
	    {
	      delete this._ageGroupCriteria[ageGroup.id];
	    }
	  },

	  /**
	   * Helper method to add AggregatedCase attributes to selectables and as a column.
	   */
	  _addVisibleAttribute : function(attributeObj)
	  {
	    var column = new YAHOO.widget.Column(attributeObj);
	    column = this._queryPanel.insertColumn(column);

	    var aggregatedCaseClass = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;

	    var attribute = new MDSS.QueryXML.Attribute(aggregatedCaseClass, attributeObj.key, attributeObj.key);
	    var selectable = new MDSS.QueryXML.Selectable(attribute);

	    this._visibleSelectables[attributeObj.key] = selectable;

	    // ADD THEMATIC VARIABLE
	    this._queryPanel.addThematicVariable(aggregatedCaseClass, attributeObj.key, attributeObj.label);
	  },

	  /**
	   * Adds a grid attribute as a selectable.
	   */
	  _addGridAttribute : function(attributeObj)
	  {
	    var column = new YAHOO.widget.Column(attributeObj);
	    column = this._queryPanel.insertColumn(column);

	    var optionName = attributeObj.key;

	    /*
	     * Relationship
	     */
	    var meta = attributeObj.meta;

	    var relType = meta.relType;
	    var relAlias = this._getGridRelAlias(relType, optionName);
	    var relEntity = new MDSS.QueryXML.Entity(relType, relAlias);

	    this._gridEntities[relAlias] = relEntity;

	    // selectable
	    var attribute = new MDSS.QueryXML.Attribute(relAlias, meta.relAttribute, optionName);
	    var selectable = new MDSS.QueryXML.Selectable(attribute);
	    this._gridSelectables[relAlias] = selectable;

	    /*
	     * Business
	     */
	    var busType = attributeObj.type;
	    var busAlias = this._getGridBusAlias(relType, optionName, busType);

	    // entity and criteria
	    var entity = new MDSS.QueryXML.Entity(busType, busAlias);
	    var cAttribute = new MDSS.QueryXML.Attribute(busAlias, attributeObj.attributeName);
	    var cSelectable = new MDSS.QueryXML.Selectable(cAttribute);

	    var condition = new MDSS.QueryXML.BasicCondition(cSelectable, MDSS.QueryXML.Operator.EQ, optionName);
	    entity.setCondition(condition);

	    this._gridEntities[busAlias] = entity;

	    // ADD THEMATIC VARIABLE
	    this._queryPanel.addThematicVariable(relAlias, optionName, attributeObj.label);
	  },

	  /**
	   * Returns the alias for a grid relationship.
	   */
	  _getGridRelAlias : function(relType, optionName)
	  {
	    var relTypeName = this._extractTypeName(relType);
	    return relTypeName+'_'+optionName;
	  },

	  /**
	   * Returns the alias for a grid business.
	   */
	  _getGridBusAlias : function(relType, optionName, busType)
	  {
	    var relTypeName = this._extractTypeName(relType);
	    var busTypeName = this._extractTypeName(busType);
	    return relTypeName+'_'+optionName+'_'+busTypeName;
	  },

	  _extractTypeName : function(type)
	  {
	    var ind = type.lastIndexOf('.');
	    var typeName = type.substring(ind+1);
	    return typeName;
	  },

	  /**
	   * Removes an attribute as a selectable and column.
	   */
	  _removeVisibleAttribute : function(attributeName, removeColumn, removeSelectable, removeThematic)
	  {
	  	if(removeSelectable === true)
	  	{
	      delete this._visibleSelectables[attributeName];
	  	}

	  	// remove all possible query references
	    delete this._visibleAggregateSelectables[attributeName];
	    delete this._visibleGroupBySelectables[attributeName];

	    if(removeColumn === true)
	    {
	      var column = this._queryPanel.getColumn(attributeName);
	      this._queryPanel.removeColumn(column);
	    }

	   if(removeThematic === true)
	   {
	      var aggregatedCaseClass = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
	      this._queryPanel.removeThematicVariable(aggregatedCaseClass, attributeName);
	   }
	  },

	  /**
	   * Removes a grid attribute from the selectables and column.
	   */
	  _removeGridAttribute : function(relAlias, busAlias, optionName, removeColumn, removeSelectable, removeThematic)
	  {
	  	if(removeSelectable === true)
	  	{
	      delete this._gridSelectables[relAlias];
	  	}

	  	// remove all possible query references
	    delete this._gridAggregateSelectables[relAlias];
	    delete this._gridGroupBySelectables[relAlias];

	    if(removeColumn === true)
	    {
	      var column = this._queryPanel.getColumn(optionName);
	      this._queryPanel.removeColumn(column);

	      delete this._gridEntities[busAlias];
	    }

	    if(removeThematic === true)
	    {
	      this._queryPanel.removeThematicVariable(relAlias, optionName);
	    }
	  },

	  /**
	   * Handler to toggle visible attributes as selectables
	   * to the AggregatedCase query.
	   */
	  _visibleAttributeHandler : function(e, attributeObj)
	  {
	    var check = e.target;
	    if(check.checked)
	    {
	      this._addVisibleAttribute(attributeObj);
	      check.nextSibling.disabled = false;
	    }
	    else
	    {
	      this._removeVisibleAttribute(attributeObj.key, true, true, true);

	      var select = check.nextSibling;
	      select.selectedIndex = 0;
	      select.disabled = true;
	    }
	  },

	  /**
	   * Handler when a new grid attribute is checked/unchecked.
	   */
	  _gridAttributeHandler : function(e, attributeObj)
	  {
	    var check = e.target;
	    if(check.checked)
	    {
	      this._addGridAttribute(attributeObj);
	      check.nextSibling.disabled = false;
	    }
	    else
	    {
	      var optionName = attributeObj.key;

	      var busType = attributeObj.type;
	      var relType = attributeObj.meta.relType;

	      var relAlias = this._getGridRelAlias(relType, optionName);
	      var busAlias = this._getGridBusAlias(relType, optionName, busType);

	      this._removeGridAttribute(relAlias, busAlias, optionName, true, true, true);

	      var select = check.nextSibling;
	      select.selectedIndex = 0;
	      select.disabled = true;
	    }
	  },

	  _gridAggregateHandler : function(e, obj)
	  {
	    var func = obj.func;
	    var attributeObj = obj.attributeObj;

	    var optionName = attributeObj.key;
	    var attributeName = attributeObj.attributeName;
	    var relAttribute = attributeObj.meta.relAttribute;

	    var busType = attributeObj.type;
	    var relType = attributeObj.meta.relType;

	    var relAlias = this._getGridRelAlias(relType, optionName);
	    var busAlias = this._getGridBusAlias(relType, optionName, busType);

	    var option = e.target;

	    var attribute = new MDSS.QueryXML.Attribute(relAlias, relAttribute, optionName);
	    var selectable = new MDSS.QueryXML.Selectable(attribute);

	    // special cases
	    if(func === MDSS.QueryXML.Functions.GB)
	    {
	  	  this._removeGridAttribute(relAlias, busAlias, optionName, false, false, false);
	      this._gridSelectables[relAlias] = selectable;
	      this._gridGroupBySelectables[relAlias] = selectable;
	      return;
	    }
	    else if(func === '')
	    {
	      // Use regular selectable (this is just here for clarity).
	  	  this._removeGridAttribute(relAlias, busAlias, optionName, false, true, false);
	      this._gridSelectables[relAlias] = selectable;
	      return;
	    }

	    // aggregate functions
	    var aggFunc = null;
	    if(func === MDSS.QueryXML.Functions.SUM)
	    {
	      aggFunc = new MDSS.QueryXML.SUM(selectable, optionName);
	    }
	    else if(func === MDSS.QueryXML.Functions.MIN)
	    {
	      aggFunc = new MDSS.QueryXML.MIN(selectable, optionName);
	    }
	    else if(func === MDSS.QueryXML.Functions.MAX)
	    {
	      aggFunc = new MDSS.QueryXML.MAX(selectable, optionName);
	    }
	    else if(func === MDSS.QueryXML.Functions.AVG)
	    {
	      aggFunc = new MDSS.QueryXML.AVG(selectable, optionName);
	    }

	  	this._removeGridAttribute(relAlias, busAlias, optionName, false, true, false);

	    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
	    this._gridAggregateSelectables[relAlias] = aggSelectable;
	  },

	  /**
	   * Handler when someone selects an aggregate function
	   * on a visible attribute.
	   */
	  _visibleAggregateHandler : function(e, obj)
	  {
	    var func = obj.func;
	    var attributeName = obj.attribute;


	    var option = e.target;

	    var attribute = new MDSS.QueryXML.Attribute(Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS, attributeName, attributeName);
	    var selectable = new MDSS.QueryXML.Selectable(attribute);

	    // special cases
	    if(func === MDSS.QueryXML.Functions.GB)
	    {
	  	  this._removeVisibleAttribute(attributeName, false, false, false);
	      this._visibleSelectables[attributeName] = selectable;
	      this._visibleGroupBySelectables[attributeName] = selectable;
	      return;
	    }
	    else if(func === '')
	    {
	      // Use regular selectable (this is just here for clarity).
	  	  this._removeVisibleAttribute(attributeName, false, true, false);
	      this._visibleSelectables[attributeName] = selectable;
	      return;
	    }

	    // aggregate functions
	    var aggFunc = null;
	    if(func === MDSS.QueryXML.Functions.SUM)
	    {
	      aggFunc = new MDSS.QueryXML.SUM(selectable, attributeName);
	    }
	    else if(func === MDSS.QueryXML.Functions.MIN)
	    {
	      aggFunc = new MDSS.QueryXML.MIN(selectable, attributeName);
	    }
	    else if(func === MDSS.QueryXML.Functions.MAX)
	    {
	      aggFunc = new MDSS.QueryXML.MAX(selectable, attributeName);
	    }
	    else if(func === MDSS.QueryXML.Functions.AVG)
	    {
	      aggFunc = new MDSS.QueryXML.AVG(selectable, attributeName);
	    }

	  	this._removeVisibleAttribute(attributeName, false, true, false);

	    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
	    this._visibleAggregateSelectables[attributeName] = aggSelectable;
	  },

	  /**
	   * Builds the query items for the left column.
	   */
	  _buildQueryItems : function(ageGroups, visibleAttributes, orderedGrids)
	  {
	  	/*
	  	 * Target
	  	 */
	    // area (geo entity search)
	    var boundSearch = MDSS.util.bind(this, this._displaySearch);
	    this._queryPanel.addQueryItem({
	      html: MDSS.Localized.Target_Search+' <img src="./imgs/icons/world.png"/>',
	      onclick: {handler: boundSearch},
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
	    YAHOO.util.Event.on(groupByCheck, 'click', this._groupByAgeGroupHandler, null, this);

	    groupBySpan.innerHTML = MDSS.QueryXML.Functions.GB;

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
	    YAHOO.util.Event.on(allCheck, 'click', this._ageGroupCheckHandler, null, this);

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
	      YAHOO.util.Event.on(check, 'click', this._ageGroupCheckHandler, group, this);

	      li.appendChild(check);
	      li.appendChild(span);

	      groups.appendChild(li);
	    }

	    ageGroupDiv.appendChild(ageSpan);
	    ageGroupDiv.appendChild(groupBy);
	    ageGroupDiv.appendChild(groups);

	    this._queryPanel.addQueryItem({
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
	      var md = this._aggregatedCase.getAttributeDTO(visible).getAttributeMdDTO();
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
	      YAHOO.util.Event.on(check, 'click', this._visibleAttributeHandler, attributeObj, this);

	      var select = document.createElement('select');
	      var options = [''];
	      options = options.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));
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

	        YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, obj, this);

	        select.appendChild(optionEl);
	      }
	      select.disabled = true; // default (must be checked to enabled)

	      li.appendChild(check);
	      li.appendChild(select);
	      li.appendChild(span);

	      visibleUl.appendChild(li);
	    }

	    visibleDiv.appendChild(visibleUl);

	    this._queryPanel.addQueryItem({
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
	        YAHOO.util.Event.on(check, 'click', this._gridAttributeHandler, attributeObj, this);

	        var select = document.createElement('select');
	        var aggOptions = [''];
	        aggOptions = aggOptions.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));

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

	          YAHOO.util.Event.on(optionEl, 'click', this._gridAggregateHandler, obj, this);

	          select.appendChild(optionEl);
	        }
	        select.disabled = true; // default (must be checked to enabled)

	        li.appendChild(check);
	        li.appendChild(select);
	        li.appendChild(span);

	        ul.appendChild(li);
	      }

	      gridDiv.appendChild(ul);

	      this._queryPanel.addQueryItem({
	        html: gridDiv,
	        id: "gridItem_"+1
	      });
	    }
	  },

	  /**
	   * Builds the column information (pre-render) for the table
	   * in the the QueryPanel.
	   */
	  _buildColumns : function()
	  {
	    // startAge
	    this._preconfiguredColumns.push({
	      key: this._aggregatedCase.getStartAgeMd().getName(),
	      label: this._aggregatedCase.getStartAgeMd().getDisplayLabel()
	    });

	    // endAge
	    this._preconfiguredColumns.push({
	      key: this._aggregatedCase.getEndAgeMd().getName(),
	      label: this._aggregatedCase.getEndAgeMd().getDisplayLabel()
	    });
	  },

	  /**
	   * Renders the QueryPanel to query on AggregatedCases.
	   */
	  render : function()
	  {
	    // render the panel
	    this._queryPanel.render();

	    // add pre-configured columns
	    for(var i=0; i<this._preconfiguredColumns.length; i++)
	    {
	      var column = this._preconfiguredColumns[i];
	      this._addVisibleAttribute(column);
	    }
	  }
	});