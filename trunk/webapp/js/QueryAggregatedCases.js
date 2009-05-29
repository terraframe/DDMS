/**
 * Class to query for AggregatedCases.
 */
MDSS.QueryAggregatedCases = Mojo.Class.create();
MDSS.QueryAggregatedCases.prototype = Mojo.Class.extend(MDSS.QueryBase, {

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

  _containsGroupBy : function()
  {
    return this._startAgeGroupBySel != null || this._endAgeGroupBySel != null ||
      Mojo.util.getValues(this._visibleGroupBySelectables).length > 0 ||
      Mojo.util.getValues(this._gridGroupBySelectables).length > 0;
  },

  /**
   * Returns the method to save this AggregatedCase search.
   */
  _getSaveQueryMethod : function()
  {
  	return Mojo.$.dss.vector.solutions.query.AggregatedCasesSearch.saveSearch;
  },

  /**
   * Returns the controller action to invoke when exporting the query to XML.
   */
  _getExportXLSAction : function()
  {
  	return 'dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToExcel.mojo';
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
      and.addCondition('ageGroup', new MDSS.QueryXML.CompositeCondition(ageGroupOr));
      and.addCondition('dateRange', new MDSS.QueryXML.CompositeCondition(dateAndOr));

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
  _addVisibleAttribute : function(attribute)
  {
    var column = new YAHOO.widget.Column(attribute.getColumnObject());
    column = this._queryPanel.insertColumn(column);

    var attributeName = attribute.getAttributeName();
    var selectable = attribute.getSelectable();

    this._visibleSelectables[attribute.getKey()] = selectable;

    // ADD THEMATIC VARIABLE
    this._queryPanel.addThematicVariable(attribute.getType(), attribute.getKey(), attribute.getDisplayLabel());
  },

  /**
   * Adds a grid attribute as a selectable.
   */
  _addGridAttribute : function(attribute)
  {
    var column = new YAHOO.widget.Column(attribute.getColumnObject());
    column = this._queryPanel.insertColumn(column);

    var optionName = attribute.getOptionName();

    /*
     * Relationship
     */
    var relationshipType = attribute.getRelationshipType();
    var relationshipAlias = attribute.getRelationshipAlias();

    var relEntity = new MDSS.QueryXML.Entity(relationshipType, relationshipAlias);

    this._gridEntities[relationshipAlias] = relEntity;

    // selectable
    var rSelectable = attribute.getRelationshipSelectable();
    this._gridSelectables[attribute.getKey()] = rSelectable;

    /*
     * Business
     */
    var type = attribute.getType();
    var businessAlias = attribute.getBusinessAlias();

    // entity and criteria
    var entity = new MDSS.QueryXML.Entity(type, businessAlias);
    var bSelectable = attribute.getBusinessSelectable();

    var condition = new MDSS.QueryXML.BasicCondition(bSelectable, MDSS.QueryXML.Operator.EQ, optionName);
    entity.setCondition(condition);

    this._gridEntities[businessAlias] = entity;

    // ADD THEMATIC VARIABLE
    this._queryPanel.addThematicVariable(relationshipAlias, attribute.getKey(), attribute.getDisplayLabel());
  },


  /**
   * Removes an attribute as a selectable and column.
   */
  _removeVisibleAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
  {
  	var attributeName = attribute.getAttributeName();
  	var key = attribute.getKey();

  	if(removeSelectable)
  	{
      delete this._visibleSelectables[attribute.getKey()];
  	}

  	// remove all possible query references
    delete this._visibleAggregateSelectables[attribute.getKey()];
    delete this._visibleGroupBySelectables[attribute.getKey()];

    if(removeColumn)
    {
      var column = this._queryPanel.getColumn(key);
      this._queryPanel.removeColumn(column);
    }

   if(removeThematic)
   {
      this._queryPanel.removeThematicVariable(attribute.getKey());
   }
  },

  /**
   * Removes a grid attribute from the selectables and column.
   */
  _removeGridAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
  {
  	var relAlias = attribute.getRelationshipAlias();

  	if(removeSelectable)
  	{
      delete this._gridSelectables[attribute.getKey()];
  	}

  	// remove all possible query references
    delete this._gridAggregateSelectables[attribute.getKey()];
    delete this._gridGroupBySelectables[attribute.getKey()];

    if(removeColumn)
    {
      var column = this._queryPanel.getColumn(attribute.getKey());
      this._queryPanel.removeColumn(column);

      delete this._gridEntities[attribute.getBusinessAlias()];
    }

    if(removeThematic)
    {
      this._queryPanel.removeThematicVariable(attribute.getKey());
    }
  },

  /**
   * Handler to toggle visible attributes as selectables
   * to the AggregatedCase query.
   */
  _visibleAttributeHandler : function(e, attribute)
  {
    var check = e.target;
    if(check.checked)
    {
      this._addVisibleAttribute(attribute);
      check.nextSibling.disabled = false;
    }
    else
    {
      this._removeVisibleAttribute(attribute, true, true, true);

      var select = check.nextSibling;
      select.selectedIndex = 0;
      select.disabled = true;
    }
  },

  /**
   * Handler when a new grid attribute is checked/unchecked.
   */
  _gridAttributeHandler : function(e, attribute)
  {
    var check = e.target;
    if(check.checked)
    {
      this._addGridAttribute(attribute);
      check.nextSibling.disabled = false;
    }
    else
    {
      this._removeGridAttribute(attribute, true, true, true);

      var select = check.nextSibling;
      select.selectedIndex = 0;
      select.disabled = true;
    }
  },

  _gridAggregateHandler : function(e, attribute)
  {
    var func = e.target.value;

    var optionName = attribute.getOptionName();
    var attributeName = attribute.getAttributeName();
    var relationshipAlias = attribute.getRelationshipAlias();
    var key = attribute.getKey();

    var selectable = attribute.getRelationshipSelectable();

    // special cases
    if(func === MDSS.QueryXML.Functions.GB)
    {
  	  this._removeGridAttribute(attribute, false, false, false);
      this._gridSelectables[key] = selectable;
      this._gridGroupBySelectables[key] = selectable;
      return;
    }
    else if(func === '')
    {
      // Use regular selectable (this is just here for clarity).
  	  this._removeGridAttribute(attribute, false, true, false);
      this._gridSelectables[key] = selectable;
      return;
    }

    // aggregate functions
    var aggFunc = null;
    if(func === MDSS.QueryXML.Functions.SUM)
    {
      aggFunc = new MDSS.QueryXML.SUM(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.MIN)
    {
      aggFunc = new MDSS.QueryXML.MIN(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.MAX)
    {
      aggFunc = new MDSS.QueryXML.MAX(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.AVG)
    {
      aggFunc = new MDSS.QueryXML.AVG(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.COUNT)
    {
      aggFunc = new MDSS.QueryXML.COUNT(selectable, key);
    }

  	this._removeGridAttribute(attribute, false, true, false);

    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
    this._gridAggregateSelectables[key] = aggSelectable;
  },

  /**
   * Handler when someone selects an aggregate function
   * on a visible attribute.
   */
  _visibleAggregateHandler : function(e, attribute)
  {
    var func = e.target.value;
    var attributeName = attribute.getAttributeName();
    var key = attribute.getKey();


    var option = e.target;

    var selectable = attribute.getSelectable();

    // special cases
    if(func === MDSS.QueryXML.Functions.GB)
    {
  	  this._removeVisibleAttribute(attribute, false, false, false);
      this._visibleSelectables[attribute.getKey()] = selectable;
      this._visibleGroupBySelectables[attribute.getKey()] = selectable;
      return;
    }
    else if(func === '')
    {
      // Use regular selectable (this is just here for clarity).
  	  this._removeVisibleAttribute(attribute, false, true, false);
      this._visibleSelectables[attribute.getKey()] = selectable;
      return;
    }

    // aggregate functions
    var aggFunc = null;
    if(func === MDSS.QueryXML.Functions.SUM)
    {
      aggFunc = new MDSS.QueryXML.SUM(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.MIN)
    {
      aggFunc = new MDSS.QueryXML.MIN(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.MAX)
    {
      aggFunc = new MDSS.QueryXML.MAX(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.AVG)
    {
      aggFunc = new MDSS.QueryXML.AVG(selectable, key);
    }
    else if(func === MDSS.QueryXML.Functions.COUNT)
    {
      aggFunc = new MDSS.QueryXML.COUNT(selectable, key);
    }

  	this._removeVisibleAttribute(attribute, false, true, false);

    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
    this._visibleAggregateSelectables[attribute.getKey()] = aggSelectable;
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

    var ageDiv = document.createElement('div');
    ageDiv	.innerHTML = MDSS.Localized.Age_Group;

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

    ageGroupDiv.appendChild(ageDiv);
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
    YAHOO.util.Dom.addClass(visibleDiv, 'scrollable');

    var labelDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
    labelDiv.innerHTML = MDSS.Localized.Aggregated_Case;

    var toggleDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(toggleDiv, 'clickable');
    YAHOO.util.Dom.addClass(toggleDiv, 'queryItemLabel');
    toggleDiv.innerHTML = MDSS.Localized.Toggle_Show;

    visibleDiv.appendChild(labelDiv);
    visibleDiv.appendChild(toggleDiv);

    var visibleUl = document.createElement('ul');
    YAHOO.util.Dom.setStyle(visibleUl, 'clear', 'both');
    YAHOO.util.Dom.setStyle(visibleUl, 'display', 'none');

    this._toggleVisibility(toggleDiv, visibleUl);

    this._attachSelectAll(visibleUl);

    var type = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    for(var i=0; i<visibleAttributes.length; i++)
    {
      var visibleObj = visibleAttributes[i];
      var attribute = new MDSS.VisibleAttribute(visibleObj);

      var li = document.createElement('li');

      var span = document.createElement('span');
      span.innerHTML = attribute.getDisplayLabel();


      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Event.on(check, 'click', this._visibleAttributeHandler, attribute, this);

      var select = document.createElement('select');
      var options = [''];
      options = options.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));


      for(var j=0; j<options.length; j++)
      {
      	var option = options[j];
        var optionEl = document.createElement('option');
        optionEl.innerHTML = option;
        YAHOO.util.Dom.setAttribute(optionEl, 'value', option);

        YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, attribute, this);

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
      var grid = orderedGrids[i];

      var gridDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(gridDiv, 'scrollable');

      var labelDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
      labelDiv.innerHTML = grid.label;

      var toggleDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(toggleDiv, 'clickable');
      YAHOO.util.Dom.addClass(toggleDiv, 'queryItemLabel');
      toggleDiv.innerHTML = MDSS.Localized.Toggle_Show;

      gridDiv.appendChild(labelDiv);
      gridDiv.appendChild(toggleDiv);

      var ul = document.createElement('ul');
      YAHOO.util.Dom.addClass(ul, 'gridList');
      YAHOO.util.Dom.setStyle(ul, 'clear', 'both');
      YAHOO.util.Dom.setStyle(ul, 'display', 'none');

      this._toggleVisibility(toggleDiv, ul);

      this._attachSelectAll(ul);

      var options = grid.options;
      for(var j=0; j<options.length; j++)
      {
      	var option = options[j];
        var attribute = new MDSS.GridAttribute(option, grid);

        var li = document.createElement('li');

        var span = document.createElement('span');
        span.innerHTML = attribute.getDisplayLabel();


        var check = document.createElement('input');
        YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');

        // Diagnostic Method adds two columns
        if(attribute.getType() === 'dss.vector.solutions.surveillance.DiagnosticGrid')
        {
          attribute._displayLabel += ' ('+MDSS.Localized.Total_Tests+')';
          YAHOO.util.Event.on(check, 'click', this._gridAttributeHandler, attribute, this);

          // copy the object and set the attribute name to that of the second column
          var optionCopy = {};
          var gridCopy = {};
          Mojo.util.copy(option, optionCopy);
          Mojo.util.copy(grid, gridCopy);

          gridCopy.relAttribute = gridCopy.relAttributeTwo;

          var second = new MDSS.GridAttribute(optionCopy, gridCopy);
          second._displayLabel += ' ('+MDSS.Localized.Positive+')';
          YAHOO.util.Event.on(check, 'click', this._gridAttributeHandler, second, this);
        }
        else
        {
          YAHOO.util.Event.on(check, 'click', this._gridAttributeHandler, attribute, this);
        }

        var select = document.createElement('select');
        var aggOptions = [''];
        aggOptions = aggOptions.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));

        for(var k=0; k<aggOptions.length; k++)
        {
          var aggOption = aggOptions[k];
          var optionEl = document.createElement('option');
          optionEl.innerHTML = aggOption;
          YAHOO.util.Dom.setAttribute(optionEl, 'value', aggOption);

          // Diagnostic Method adds two columns
          YAHOO.util.Event.on(optionEl, 'click', this._gridAggregateHandler, attribute, this);

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
   * Attaches an option to select all items in the given list.
   */
  _attachSelectAll : function(ul)
  {
    var check = document.createElement('input');
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    YAHOO.util.Event.on(check, 'click', this._toggleSelectAll, ul, this);

    var span = document.createElement('span');
    span.innerHTML = MDSS.Localized.Select_All;

    var li = document.createElement('li');
    li.appendChild(check);
    li.appendChild(span);

    ul.appendChild(li);
  },

  /**
   *
   */
  _toggleSelectAll : function(e, ul)
  {
  	var check = e.target;
  	var checks = YAHOO.util.Selector.query('input[type="checkbox"]', ul);
  	var doCheck = check.checked;

    for(var i=0; i<checks.length; i++)
    {
      var check = checks[i];
      if(doCheck !== check.checked)
      {
        check.click();
      }
    }
  },

  /**
   * Handler to toggle the visibility of a list.
   */
  _toggleVisibility : function(toggle, element)
  {
    YAHOO.util.Event.on(toggle, 'click', function(e, obj){
      var el = obj.element;
      var toggle = obj.toggle;

      if(YAHOO.util.Dom.getStyle(el, 'display') === 'block')
      {
        YAHOO.util.Dom.setStyle(el, 'display', 'none');
        toggle.innerHTML = MDSS.Localized.Toggle_Show;
      }
      else
      {
        YAHOO.util.Dom.setStyle(el, 'display', 'block');
        toggle.innerHTML = MDSS.Localized.Toggle_Hide;
      }

    }, {toggle: toggle, element: element}, this);

  },

  /**
   * Builds the column information (pre-render) for the table
   * in the the QueryPanel.
   */
  _buildColumns : function()
  {
  	var type = this._aggregatedCase.getType();

    // startAge
    var startAge = this._aggregatedCase.getStartAgeMd().getName();
    var startAgeObj = new MDSS.VisibleAttribute({
      type: type,
      attributeName : startAge,
      displayLabel: this._aggregatedCase.getStartAgeMd().getDisplayLabel(),
    });

    this._preconfiguredColumns.push(startAgeObj);

    // endAge
    var endAge = this._aggregatedCase.getEndAgeMd().getName();
    var endAgeObj = new MDSS.VisibleAttribute({
      type: type,
      displayLabel: this._aggregatedCase.getEndAgeMd().getDisplayLabel(),
      attributeName: endAge
    });

    this._preconfiguredColumns.push(endAgeObj);
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

MDSS.AbstractAttribute = function(obj)
{
  	this._type = obj.type;
  	this._displayLabel = obj.displayLabel;
  	this._attributeName = obj.attributeName;

    var s = new String(Math.random());
    var r = s.substring(s.length-6);
  	this._key = this._type.substring(this._type.lastIndexOf('.')+1) +'_'+this._attributeName+'_'+r;
};
MDSS.AbstractAttribute.prototype = {

  /**
   * Unique key used with YUI Column
   * and as the user alias for the attribute
   * in a ValueObject.
   */
  getKey : function()
  {
  	return this._key;
  },

  getType : function()
  {
  	return this._type;
  },

  getAttributeName : function()
  {
  	return this._attributeName;
  },

  getDisplayLabel : function()
  {
  	return this._displayLabel;
  },

  /**
   * Returns an object compatible with YUI Column's
   * constructor.
   */
  getColumnObject : function()
  {
  	return {
  	  key: this._key,
  	  label: this._displayLabel
  	};
  }
};

MDSS.VisibleAttribute = function(obj)
{
  Mojo.util.copy(new MDSS.AbstractAttribute(obj), this);
};
MDSS.VisibleAttribute.prototype = {

  /**
   * Returns a basic selectable object that represents this
   * attribute.
   */
  getSelectable : function()
  {
    var attribute = new MDSS.QueryXML.Attribute(this._type, this._attributeName, this._key);
    var selectable = new MDSS.QueryXML.Selectable(attribute);
    return selectable;
  }
};

MDSS.GridAttribute = function(obj, meta)
{
  Mojo.util.copy(new MDSS.AbstractAttribute(obj), this);

  this._optionName = obj.optionName;
  this._meta = meta;

  var busType = this._type;
  var relType = this._meta.relType;

  this._relAlias = this._getGridRelAlias(relType, this._optionName);
  this._busAlias = this._getGridBusAlias(relType, this._optionName, busType);
};
MDSS.GridAttribute.prototype = {

  getOptionName : function()
  {
  	return this._optionName;
  },

  /**
   * Returns the namespaced business alias for this attribute.
   */
  getBusinessAlias : function()
  {
    return this._busAlias;
  },

  /**
   * Returns the namespaced relationship alias for this attribute.
   */
  getRelationshipAlias : function()
  {
    return this._relAlias;
  },

  getRelationshipType : function()
  {
  	return this._meta.relType;
  },

  getRelationshipSelectable : function()
  {
  	var attribute = new MDSS.QueryXML.Attribute(this._relAlias, this._meta.relAttribute, this._key);
    var selectable = new MDSS.QueryXML.Selectable(attribute);
    return selectable;
  },

  getBusinessSelectable : function()
  {
    var attribute = new MDSS.QueryXML.Attribute(this._busAlias, this._attributeName);
    var selectable = new MDSS.QueryXML.Selectable(attribute);
    return selectable;
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
};