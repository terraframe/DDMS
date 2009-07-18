/**
 * Class to query for AggregatedCases.
 */
MDSS.QuerySurvey= Mojo.Class.create();
MDSS.QuerySurvey.prototype = Mojo.Class.extend(MDSS.QueryBase, {

  initialize : function(queryList, householdMenuItems, personMenuItems)
  {
    MDSS.QueryBase.prototype.initialize.call(this);

    if(arguments.length === 1 && arguments[0] == null)
    {
      // FIXME used for inheritance optimization
      return;
    }
    
    // Ref to instances (used as template for display labels/metadata)
    this._SurveyPoint = Mojo.$.dss.vector.solutions.intervention.monitor.SurveyPoint;
    this._surveyPoint= new this._SurveyPoint();
    
    this._Household = Mojo.$.dss.vector.solutions.intervention.monitor.Household;
    this._household = new this._Household();
    
    this._Person = Mojo.$.dss.vector.solutions.intervention.monitor.Person
    this._person = new this._Person();

    // START: query objects that dictate state of the query.

    this._householdSelectables = {};
    this._householdCriteria = {};
    this._householdAggregateSelectables = {};
    
    this._personSelectables = {};
    this._personCriteria = {};
    this._personAggregateSelectables = {};
    
    // END: query objects

    // Key/value where key is attribute.getKey() + "_li"
    // (which is the id of the relevant LI node,
    // and value is an array of ContextMenuItems.
    this._personMenus = {};
    this._householdMenus = {};
    
    // Map of criteria ids and associated ContextMenuItems.
    this._personItems = {};
    this._householdItems = {};

    for(var i=0; i<queryList.length; i++)
    {
      this._queryPanel.addAvailableQuery(queryList[i]);
    }
    
    // Criteria for Person.DOB
    this._config.setProperty('dobCriteria', null);

    this._buildQueryItems(householdMenuItems, personMenuItems);
  },

  /**
   * Returns the method to save this AggregatedCase search.
   */
  _getQueryType: function()
  {
    return 'QueryIndicatorSurvey';
  },

  /**
   * Returns the controller action to invoke when exporting the query to XML.
   */
  _getExportXLSAction : function()
  {
    return 'dss.vector.solutions.query.QueryController.exportSurveyQueryToExcel.mojo';
  },

  _getExportCSVAction : function()
  {
    return 'dss.vector.solutions.query.QueryController.exportSurveyQueryToCSV.mojo';
  },

  _getExportReportAction : function()
  {
    return 'dss.vector.solutions.report.ReportController.generateReport.mojo';
  },

  _loadQueryState : function(view)
  {
    var thisRef = this;

    var xml = view.getQueryXml();
    var parser = new MDSS.Query.Parser(xml);

    parser.parseSelectables({
      attribute : function(entityAlias, attributeName, userAlias){
      
        thisRef._checkBox(userAlias);
      },
      sum: function(entityAlias, attributeName, userAlias){

        thisRef._checkBox(userAlias);
        thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.SUM);
      },
      sqlinteger: function(entityAlias, attributeName, userAlias){

        thisRef._checkBox(userAlias);
      },
      sqldate : function(entityAlias, attributeName, userAlias){

        thisRef._checkBox(userAlias);
      },
    });

    var entities = [];

    parser.parseCriteria({
      attribute : function(entityAlias, attributeName, userAlias, operator, value){

        // restricting geo entities
        if(entityAlias === thisRef.ALL_PATHS)
        {
          entities.push(value);
        }
        // FIXME restore date
      }
    });
    
    this._reconstructSearch(entities, view);
  },



  /**
   * Final function called before query is executed.
   * Any last minute cleanup is done here. The this
   * reference is that of the QueryPanel.
   */
  executeQuery : function()
  {
    // execute the query
    var queryXML = this._constructQuery();
    var xml = queryXML.getXML();

    var request = new MDSS.Request({
      thisRef : this,
      onSuccess : function(query)
      {
        this.thisRef.resetQueryResults(query);
      }
    });

    var page = this.getCurrentPage();
    Mojo.$.dss.vector.solutions.intervention.monitor.SurveyPoint.querySurvey(request, xml, this._config.getJSON(), page, this.PAGE_SIZE);
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

    Mojo.$.dss.vector.solutions.query.MappingController.mapAggregatedCaseQuery(request, xml, this._config.getJSON(), layerIds, savedSearchId);
  },

  /**
   * Constructs the query with all the subcomponents.
   */
  _constructQuery : function(forMapping)
  {
    var queryXML = MDSS.QueryBase.prototype._constructQuery.call(this, forMapping); // super
    
    // Entity queries
    var surveyPointType = this._surveyPoint.getType();
    var surveyPointQuery = new MDSS.QueryXML.Entity(surveyPointType, surveyPointType);
    queryXML.addEntity(surveyPointQuery);

    var householdType = this._household.getType();
    var householdQuery = new MDSS.QueryXML.Entity(householdType, householdType);
    queryXML.addEntity(householdQuery);

    var personType = this._person.getType();
    var personQuery = new MDSS.QueryXML.Entity(personType, personType);
    queryXML.addEntity(personQuery);
    
    // Household selectables
    var householdSel = Mojo.util.getKeys(this._householdSelectables);
    for(var i=0; i<householdSel.length; i++)
    {
      var name = householdSel[i];
      var selectable = this._householdSelectables[name];
      queryXML.addSelectable(householdQuery.getAlias()+'-'+name, selectable);
    }
    
    // Household Aggregates
    var aggNames = Mojo.util.getKeys(this._householdAggregateSelectables);
    for(var i=0; i<aggNames.length; i++)
    {
      var name = aggNames[i];
      var selectable = this._householdAggregateSelectables[name];

      queryXML.addSelectable(householdQuery.getAlias()+'_'+name, selectable);
    }
    
    // Household criteria
    var householdCrit = Mojo.util.getKeys(this._householdCriteria);
    var and = new MDSS.QueryXML.And();
    for(var i=0; i<householdCrit.length; i++)
    {
      var name = householdCrit[i];
      var condition = this._householdCriteria[name];
      and.addCondition(name, condition);
    }
    
    if(and.getSize() > 0)
    {
      var composite = new MDSS.QueryXML.CompositeCondition(and);
      householdQuery.setCondition(composite);
    }
    
   // Person selectables
    var personSel = Mojo.util.getKeys(this._personSelectables);
    for(var i=0; i<personSel.length; i++)
    {
      var name = personSel[i];
      var selectable = this._personSelectables[name];
      queryXML.addSelectable(personQuery.getAlias()+'-'+name, selectable);
    }
    
    // Person Aggregates
    var aggNames = Mojo.util.getKeys(this._personAggregateSelectables);
    for(var i=0; i<aggNames.length; i++)
    {
      var name = aggNames[i];
      var selectable = this._personAggregateSelectables[name];

      queryXML.addSelectable(personQuery.getAlias()+'_'+name, selectable);
    }
    
    // Person criteria
    var personCrit = Mojo.util.getKeys(this._personCriteria);
    var and = new MDSS.QueryXML.And();
    for(var i=0; i<personCrit.length; i++)
    {
      var name = personCrit[i];
      var condition = this._personCriteria[name];
      and.addCondition(name, condition);
    }
    
    if(and.getSize() > 0)
    {
      var composite = new MDSS.QueryXML.CompositeCondition(and);
      personQuery.setCondition(composite);
    }
    
    return queryXML;
  },
  
  /**
   * Sets criteria on household
   */
  _setHouseholdCriteria : function(e, attribute)
  {
    var value = e.target.value;
    var display = e.target.innerHTML;

    this._setHouseholdCriteriaConcrete(attribute, value, display, true);
  },
  
  _setHouseholdCriteriaConcrete : function(attribute, value, display, addCriteria)
  {
    var key = attribute.getKey();
    var selectable = attribute.getSelectable(false); 
  
    if(value === '')
    {
      delete this._householdCriteria[key];
      
      // Criterion of an empty string means the criteria should be cleared.
      // This is generally used with mutually exclusive select list options.
      this._queryPanel.clearWhereCriteria(key);
    }
    else
    {
      var attrDTO = this._household.getAttributeDTO(attribute.getAttributeName());
      var condition;
      if(attribute.getAttributeName() === this._Household.LASTSPRAYED)
      {
        condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, value);
        
        // There can only be one LTE condition at a time for Household.LASTSPRAYED
        this._queryPanel.clearWhereCriteria(key);
        this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
      }
      else if(attribute.getAttributeName() === this._Household.WINDOWTYPE)
      {
        var existing = this._householdCriteria[key];
        var enumIds = existing != null ? existing.getValue().split(',') : [];
        var set = new MDSS.Set();
        set.addAll(enumIds);
      
        if(addCriteria)
        {
          set.set(value);
          this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
        }
        else
        {
          set.remove(value);
          this._queryPanel.removeWhereCriteria(key, value);
        }
        
        var finalEnumIds = set.values();
        if(finalEnumIds.length == 0)
        {
          // No conditions left.
          delete this._householdCriteria[key];
          this._queryPanel.clearWhereCriteria(key);
          return;
        }

        condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.CONTAINS_ANY, finalEnumIds.join(','));
      }
      else if(attrDTO instanceof Mojo.dto.AttributeReferenceDTO ||
        attrDTO instanceof Mojo.dto.AttributeBooleanDTO)
      {
        var existing = this._householdCriteria[key];
        
        var or;
        if(existing != null)
        {
          // Grab the old Or criteria.
          or = existing.getComponent();
        }
        else
        {
          or = new MDSS.QueryXML.Or(); 
        }
  
        if(addCriteria)
        {
          var basicCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, value);
          or.addCondition(value, basicCondition);

          this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
        }
        else
        {
          or.removeCondition(value);
          this._queryPanel.removeWhereCriteria(key, value);
        }
        
        if(or.getSize() === 0)
        {
          // No conditions left.
          delete this._householdCriteria[key];
          this._queryPanel.clearWhereCriteria(key);
          return;
        }
        
        condition = new MDSS.QueryXML.CompositeCondition(or);
      }
      else
      {
        condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, value);
      }
      
      this._householdCriteria[attribute.getKey()] = condition;
    }
  },
  
  _setPersonCriteria : function(attribute, value, display, addCriteria)
  {
    var key = attribute.getKey();
  
    if(value === '')
    {
      delete this._personCriteria[key];
      
      // Criterion of an empty string means the criteria should be cleared.
      // This is generally used with mutually exclusive select list options.
      this._queryPanel.clearWhereCriteria(key);
    }
    else
    {
      var condition;
      
      var attrDTO = this._person.getAttributeDTO(attribute.getAttributeName());
      if(attrDTO instanceof Mojo.dto.AttributeEnumerationDTO)
      {
        var existing = this._personCriteria[key];
        var enumIds = existing != null ? existing.getValue().split(',') : [];
        var set = new MDSS.Set();
        set.addAll(enumIds);
      
        if(addCriteria)
        {
          set.set(value);
          this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
        }
        else
        {
          set.remove(value);
          this._queryPanel.removeWhereCriteria(key, value);
        }
        
        var finalEnumIds = set.values();
        if(finalEnumIds.length == 0)
        {
          // No conditions left.
          delete this._personCriteria[key];
          this._queryPanel.clearWhereCriteria(key);
          return;
        }

        var selectable = attribute.getSelectable(false);  
        condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.CONTAINS_ANY, finalEnumIds.join(','));
      }
      else if(attrDTO instanceof Mojo.dto.AttributeReferenceDTO ||
        attrDTO instanceof Mojo.dto.AttributeBooleanDTO)
      {
        var existing = this._personCriteria[key];
        
        var or;
        if(existing != null)
        {
          // Grab the old Or criteria.
          or = existing.getComponent();
        }
        else
        {
          or = new MDSS.QueryXML.Or(); 
        }
  
        if(addCriteria)
        {
          var selectable = attribute.getSelectable(false);  
          var basicCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, value);
        
          or.addCondition(value, basicCondition);
          
          this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
        }
        else
        {
          or.removeCondition(value);
          this._queryPanel.removeWhereCriteria(key, value);
        }
        
        if(or.getSize() === 0)
        {
          // No conditions left.
          delete this._personCriteria[key];
          this._queryPanel.clearWhereCriteria(key);
          return;
        }
        
        condition = new MDSS.QueryXML.CompositeCondition(or);
      }
      else if(attrDTO.getAttributeMdDTO().getName() === this._Person.HAEMOGLOBIN)
      {
        // always clear the range criteria to avoid having to juggle different ids
        // for different ranges.
        this._queryPanel.clearWhereCriteria(key);
        
        if(!addCriteria || value === '-' || value === '')
        {
          // No conditions left.
          delete this._personCriteria[key];
          return;
        }
        else
        {
          this._queryPanel.addWhereCriteria(attribute.getKey(), value, display); // value == display 
        }
      
        // check for either exact or range matches
        var selectable = attribute.getSelectable(false);  
        if(value.indexOf('-') != -1)
        {
          var range = value.split('-');
          var range1 = range[0];
          var range2 = range[1];
          
          var cond1 = null;
          if(range1 !== '')
          {
            cond1 = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, range1);
          }
          
          var cond2 = null;
          if(range2 !== '')
          {
            cond2 = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, range2);
          }
          
          if(cond1 != null && cond2 != null)
          {
            var and = new MDSS.QueryXML.And();
            and.addCondition(attribute.getAttributeName()+'_range1', cond1);
            and.addCondition(attribute.getAttributeName()+'_range2', cond2);
            
            condition = new MDSS.QueryXML.CompositeCondition(and);
          }
          else if(cond1 != null)
          {
            condition = cond1;
          }
          else if(cond2 != null)
          {
            condition = cond2;
          }
        }
        else
        {
          condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, value);
        }
      }
      else
      {
        if(addCriteria)
        {
          var selectable = attribute.getSelectable(false);  
          condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, value);
        }
        else
        {
          delete this._personCriteria[key];
          this._queryPanel.clearWhereCriteria(key);
          return;
        }
      }
      
      this._personCriteria[key] = condition;
    }
  },
  
  /**
   * Resets all defaults, including clearing all criteria and unchecking
   * all context menu items.
   */
  _resetToDefault : function()
  {
    MDSS.QueryBase.prototype._resetToDefault.call(this); // super
  
    // uncheck all menu items
    var keys = Mojo.util.getKeys(this._personItems);
    for(var i=0; i<keys.length; i++)
    {
      var item = this._personItems[keys[i]];
      item.checked = false;
      
      // clear any exact/range input fields
      if(item.onclick)
      {
        this._toggleExact(item.onclick.obj.attribute, true);
        this._toggleRange(item.onclick.obj.attribute, true);
      }
    }
    
    keys = Mojo.util.getKeys(this._householdItems);
    for(var i=0; i<keys.length; i++)
    {
      var item = this._householdItems[keys[i]];
      item.checked = false;
      
      // clear any exact/range input fields
      if(item.onclick)
      {
        this._toggleExact(item.onclick.obj.attribute, true);
        this._toggleRange(item.onclick.obj.attribute, true);
      }
    }
    
    // criteria maps
    this._personCriteria = {};
    this._householdCriteria = {};
    
    // json config
    this._config.setProperty('dobCriteria', null);
    this._queryPanel.clearWhereCriteria(this._Person.DOB);
  },

  /**
   * Helper method to add Household attributes to selectables and as a column.
   */
  _addHouseholdAttribute : function(attribute)
  {
    var column = new YAHOO.widget.Column(attribute.getColumnObject());
    column = this._queryPanel.insertColumn(column);

    var attributeName = attribute.getAttributeName();
    
    var selectable = attribute.getSelectable(true);

    this._householdSelectables[attribute.getKey()] = selectable;

    // ADD THEMATIC VARIABLE
    this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
  },

  /**
   * Removes an attribute as a selectable and column.
   */
  _removeHouseholdAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
  {
    var attributeName = attribute.getAttributeName();
    var key = attribute.getKey();

    if(removeSelectable)
    {
      delete this._householdSelectables[attribute.getKey()];
      
      // Clear any criteria since criteria cannot exist
      // without the attribute as a selectable.
      delete this._householdCriteria[attribute.getKey()];
    }

    // remove all possible query references
    delete this._householdAggregateSelectables[attribute.getKey()];

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
   * Helper method to add Person attributes to selectables and as a column.
   */
  _addPersonAttribute : function(attribute)
  {
    var column = new YAHOO.widget.Column(attribute.getColumnObject());
    column = this._queryPanel.insertColumn(column);

    var attributeName = attribute.getAttributeName();
    
    // Date of birth requires SQL pass through to convert a date into an int
    if(attributeName === this._Person.DOB)
    {
      selectable = attribute.getSelectable(true, MDSS.QueryXML.Sqlinteger);
    }
    else
    {
      selectable = attribute.getSelectable(true);
    }
    

    this._personSelectables[attribute.getKey()] = selectable;

    // ADD THEMATIC VARIABLE
    this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
  },

  /**
   * Removes an attribute as a selectable and column.
   */
  _removePersonAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
  {
    var attributeName = attribute.getAttributeName();
    var key = attribute.getKey();

    if(removeSelectable)
    {
      delete this._personSelectables[attribute.getKey()];
    }

    // remove all possible query references
    delete this._personAggregateSelectables[attribute.getKey()];

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
   * Handler to toggle household attributes as selectables.
   */
  _householdAttributeHandler : function(e, attribute)
  {
    var check = e.target;
    if(check.checked)
    {
      this._addHouseholdAttribute(attribute);
      
      var select = check.nextSibling;
      if(select && select.nodeName == 'SELECT' && select.disabled === true)
      {
        select.disabled = false;
      }
    }
    else
    {
      this._removeHouseholdAttribute(attribute, true, true, true);

      var select = check.nextSibling;
      if(select && select.nodeName == 'SELECT' && select.disabled === false)
      {
        select.selectedIndex = 0;
        select.disabled = true;
      }
    }
  },
  
  /**
   * Handler to toggle person attributes as selectables.
   */
  _personAttributeHandler : function(e, attribute)
  {
    var check = e.target;
    if(check.checked)
    {
      this._addPersonAttribute(attribute);
      
      var select = check.nextSibling;
      if(select && select.nodeName == 'SELECT' && select.disabled === true)
      {
        select.disabled = false;
      }
    }
    else
    {
      this._removePersonAttribute(attribute, true, true, true);

      var select = check.nextSibling;
      if(select && select.nodeName == 'SELECT' && select.disabled === false)
      {
        select.selectedIndex = 0;
        select.disabled = true;
      }
    }
  },

  /**
   * Handler when someone selects an aggregate function
   * on a household attribute.
   */
  _householdAggregateHandler : function(e, attribute)
  {
    var func = e.target.value;

    var attributeName = attribute.getAttributeName();
    var key = attribute.getKey();

    var selectable = attribute.getSelectable(true);

    this._queryPanel.updateColumnLabel(key, func);

    // special cases

    if(func === '')
    {
      // Use regular selectable (this is just here for clarity).
      this._removeHouseholdAttribute(attribute, false, true, false);
      this._householdSelectables[attribute.getKey()] = selectable;


      return;
    }

    // aggregate functions
    var aggFunc = null;
    if(func === MDSS.QueryXML.Functions.SUM)
    {
      aggFunc = new MDSS.QueryXML.SUM(selectable, key);
    }

    this._removeHouseholdAttribute(attribute, false, true, false);

    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
    this._householdAggregateSelectables[attribute.getKey()] = aggSelectable;
  },

  /**
   * Builds the query items for the left column.
   */
  _buildQueryItems : function(householdMenuItems, personMenuItems)
  {
    /*
     * Target
     */
    // 3. area (geo entity search)
    var boundSearch = MDSS.util.bind(this, this._displaySearch);
    this._queryPanel.addQueryItem({
      html: MDSS.Localized.Target_Search+' <img src="./imgs/icons/world.png"/>',
      onclick: {handler: boundSearch},
      id: "areaItem"
    });
    
    /*
     * Household attributes
     */
    var householdDiv = document.createElement('div');
     
    var labelDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
    labelDiv.innerHTML = this._household.getTypeMd().getDisplayLabel();
    
    var householdUl = document.createElement('ul');
    YAHOO.util.Dom.addClass(householdUl, 'gridList');
    YAHOO.util.Dom.setStyle(householdUl, 'clear', 'both');
    //YAHOO.util.Dom.setStyle(householdUl, 'display', 'none');

    householdDiv.appendChild(labelDiv);
    householdDiv.appendChild(householdUl);
    
    // 4. Household
    var attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getHouseholdNameMd().getDisplayLabel(),
      attributeName: this._household.getHouseholdNameMd().getName()
    });

    var li = document.createElement('li');
    var check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element: check, checked: false});
    YAHOO.util.Event.on(check, 'click', this._householdAttributeHandler, attribute, this);
    var span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(check);
    li.appendChild(span);
    householdUl.appendChild(li);

    var Household = Mojo.$.dss.vector.solutions.intervention.monitor.Household;
    
    // 5. Status
    this._createHouseholdMenu(householdUl, Household.URBAN, householdMenuItems);
    
    // 6. # People
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getPeopleMd().getDisplayLabel(),
      attributeName: this._household.getPeopleMd().getName()
    });
    
    this._createHouseholdInt(householdUl, attribute);
    
    // 7. Wall
    this._createHouseholdMenu(householdUl, Household.WALL, householdMenuItems);
    
    // 7. Roof
    this._createHouseholdMenu(householdUl, Household.ROOF, householdMenuItems);    
    
    // 9. windows
    this._createHouseholdMenu(householdUl, Household.WINDOWTYPE, householdMenuItems);
    
    // 10. # rooms
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getRoomsMd().getDisplayLabel(),
      attributeName: this._household.getRoomsMd().getName()
    });
    
    this._createHouseholdInt(householdUl, attribute);
    
    // 11. Last sprayed
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getLastSprayedMd().getDisplayLabel(),
      attributeName: this._household.getLastSprayedMd().getName()
    });
    
    li = document.createElement('li');
    check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element: check, checked: false});
    YAHOO.util.Event.on(check, 'click', this._householdAttributeHandler, attribute, this);
    
    var select = document.createElement('select');
    select.disabled = true; // default (must be checked to enabled)
    this._defaults.push({element:select, disabled:true, index:0});
    
    // default value (no criteria)
    var noLTE = document.createElement('option');
    noLTE.value = '';
    select.appendChild(noLTE);
    YAHOO.util.Event.on(noLTE, 'click', this._setHouseholdCriteria, attribute, this);
    
    // provide LTE criteria for # months since last spray
    for(var i=1; i<=12; i++)
    {
      var option = document.createElement('option');
      option.value = i;
      option.innerHTML = '<= '+ i;
      select.appendChild(option);
      
      YAHOO.util.Event.on(option, 'click', this._setHouseholdCriteria, attribute, this);
    }
    
    
    var span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(check);
    li.appendChild(select);
    li.appendChild(span);
    householdUl.appendChild(li);    
    
    
    // 12. # nets
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getNetsMd().getDisplayLabel(),
      attributeName: this._household.getNetsMd().getName()
    });
    
    this._createHouseholdInt(householdUl, attribute);
    
    // ?? Household nets (grabs HouseholdNet.AMOUNT, an attr on rel between Household and Net.
    

    // 13. # people slept under a net
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getSleptUnderNetsMd().getDisplayLabel(),
      attributeName: this._household.getSleptUnderNetsMd().getName()
    });
    
    this._createHouseholdInt(householdUl, attribute);
    
    
    // 14. # nets slept under
    attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: this._household.getNetsUsedMd().getDisplayLabel(),
      attributeName: this._household.getNetsUsedMd().getName()
    });
    
    this._createHouseholdInt(householdUl, attribute);
    
    this._queryPanel.addQueryItem({
      html : householdDiv,
      id: 'householdItems',
      menuBuilder : MDSS.util.bind(this, this._householdMenuBuilder)
    });
    
    
    /*
     * Person attributes
     */
    
    var personDiv = document.createElement('div');
     
    labelDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
    labelDiv.innerHTML = this._person.getTypeMd().getDisplayLabel();
    
    var personUl = document.createElement('ul');
    YAHOO.util.Dom.addClass(personUl, 'gridList');
    YAHOO.util.Dom.setStyle(personUl, 'clear', 'both');
    //YAHOO.util.Dom.setStyle(householdUl, 'display', 'none');

    personDiv.appendChild(labelDiv);
    personDiv.appendChild(personUl);

    // 15. Person Id
    attribute = new MDSS.HouseholdAttribute({
      type: this._person.getType(),
      displayLabel: this._person.getPersonIdMd().getDisplayLabel(),
      attributeName: this._person.getPersonIdMd().getName()
    });
    
    li = document.createElement('li');
    check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element:check, checked:false});
    YAHOO.util.Event.on(check, 'click', this._personAttributeHandler, attribute, this);
    span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(check);
    li.appendChild(span);
    personUl.appendChild(li);
    
    // 16. Age
    // FIXME reperesent as age and add WHERE
    attribute = new MDSS.HouseholdAttribute({
      type: this._person.getType(),
      displayLabel: this._person.getDobMd().getDisplayLabel(),
      attributeName: this._person.getDobMd().getName()
    });
    attribute.setKey(this._Person.DOB);// overwrite key for use with SQL pass-through
    
    this._createPersonMenu(personUl, attribute, personMenuItems);

    var Person = Mojo.$.dss.vector.solutions.intervention.monitor.Person;

    // 17. Sex
    this._createPersonMenu(personUl, Person.SEX, personMenuItems); 
    
    // 18. Pregnant
    this._createPersonMenu(personUl, Person.PREGNANT, personMenuItems);   
    
    // 19. slept under net
    this._createPersonMenu(personUl, Person.SLEPTUNDERNET, personMenuItems);   

    // ??. hemoglobin measured
    this._createPersonMenu(personUl, Person.HAEMOGLOBIN, personMenuItems);
     
    // 20. hemoglobin measured
    this._createPersonMenu(personUl, Person.HAEMOGLOBINMEASURED, personMenuItems);   
    
    // 21. Anemia Treatment
    this._createPersonMenu(personUl, Person.ANAEMIATREATMENT, personMenuItems);    

    // 22. Iron given
    this._createPersonMenu(personUl, Person.IRON, personMenuItems);    

    // 23. RDT Treatment
    this._createPersonMenu(personUl, Person.RDTTREATMENT, personMenuItems);   
    
    // 24. RDT Result
    this._createPersonMenu(personUl, Person.RDTRESULT, personMenuItems);

    // 27. Bloodslide
    this._createPersonMenu(personUl, Person.BLOODSLIDE, personMenuItems);   
    
    // 28. Fever
    this._createPersonMenu(personUl, Person.FEVER, personMenuItems);   
    
    // 29. Fever Treatment
    this._createPersonMenu(personUl, Person.FEVERTREATMENT, personMenuItems);   
    
    // 30. malaria
    this._createPersonMenu(personUl, Person.MALARIA, personMenuItems);   
    
    // 31. Malaria Treatment
    this._createPersonMenu(personUl, Person.MALARIATREATMENT, personMenuItems);   
    
    // 32. Payment
    this._createPersonMenu(personUl, Person.PAYMENT, personMenuItems);   
    
    this._queryPanel.addQueryItem({
      html : personDiv,
      id: 'personItems',
      menuBuilder : MDSS.util.bind(this, this._personMenuBuilder)
    });
  },
  
  _menuBuilder : function(outerLi, targetEl, menuMap)
  {
    var li = null;
    if(targetEl.nodeName === 'LI')
    {
      li = targetEl;
    }
    else
    {
      var parent = YAHOO.util.Dom.getAncestorByTagName(targetEl, "LI");
      if(parent != null)
      {
        li = parent;
      }
    }
    
    // make sure the attribute is selected before
    // showing the context menu
    if(li == null || !li.firstChild.checked)
    {
      return [];
    }
    
    var items = menuMap[li.id];
    if(items != null)
    {
      return items;
    }
    else
    {
      return [];
    }
  },
  
  /**
   * Menu builder function to dynamically populate a menu for 
   * specific Person attributes.
   */
  _personMenuBuilder : function(outerLi, targetEl)
  {
    return this._menuBuilder(outerLi, targetEl, this._personMenus);
  },
  
  /**
   * Menu builder function to dynamically populate a menu for 
   * specific Household attributes.
   */
  _householdMenuBuilder : function(outerLi, targetEl)
  {
    return this._menuBuilder(outerLi, targetEl, this._householdMenus);
  },
  
  _personMenuItemHandler : function(eventType, event, obj)
  {
    var attribute = obj.attribute;
    var value = obj.value;
    var display = obj.display;
    
    var item = this._personItems[attribute.getAttributeName()+'-'+value];
    item.checked = !item.checked;
    
    this._setPersonCriteria(attribute, value, display, item.checked);
  },
  
  _householdMenuItemHandler : function(eventType, event, obj)
  {
    var attribute = obj.attribute;
    var value = obj.value;
    var display = obj.display;
    
    var item = this._householdItems[attribute.getAttributeName()+'-'+value];
    item.checked = !item.checked;
    
    this._setHouseholdCriteriaConcrete(attribute, value, display, item.checked);
  },
  
  _createHouseholdMenu : function(householdUl, attributeName, householdMenuItems)
  {
    var attrDTO = this._household.getAttributeDTO(attributeName);
    var dereference = (attrDTO instanceof Mojo.dto.AttributeEnumerationDTO ||
       attrDTO instanceof Mojo.dto.AttributeReferenceDTO) ? true : false;
  
    var attrDTOMd = attrDTO.getAttributeMdDTO();
  
    var attribute = new MDSS.HouseholdAttribute({
      type: this._household.getType(),
      displayLabel: attrDTOMd.getDisplayLabel(),
      attributeName: attrDTOMd.getName()
    }, dereference);  
  
    var li = document.createElement('li');
    li.id = attribute.getKey()+"_li";
    
    var check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element:check, checked:false});
    YAHOO.util.Event.on(check, 'click', this._householdAttributeHandler, attribute, this);

    // Use the JSON object sent from the server to build objects
    // compatible with YUI's context menu constructor.    
    var rawItems = householdMenuItems[attribute.getAttributeName()];
    var items = [];
    for(var i=0; i<rawItems.length; i++)
    {
      var rawItem = rawItems[i];

      var item = {
        text: rawItem.displayLabel,
        checked: false,
      };
      
      if(!rawItem.isAbstract)
      {
        item.onclick = {
          fn: this._householdMenuItemHandler,
          obj: {attribute: attribute, value: rawItem.value, display: rawItem.displayLabel}, 
          scope: this
        };
      }
      
      if(rawItem.isAbstract)
      {
        item.text = "<strong>"+rawItem.displayLabel+"</strong>";
      }
      else if(rawItem.isAbstract === false)
      {
        item.text = "&nbsp;&nbsp;&nbsp;&nbsp;"+rawItem.displayLabel;
      }
      else
      {
        // isAbstract doesn't even exist on the item, meaning it
        // is not part of a Grid or Configurable List
        item.text = rawItem.displayLabel;
      }
      
      this._householdItems[attribute.getAttributeName()+'-'+rawItem.value] = item;
      items.push(item);
    }
    
    // map the menu items to the LI node that contains the attribute data
    this._householdMenus[li.id] = items;
    
    var span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(check);
    li.appendChild(span);
    householdUl.appendChild(li);
  },
  
  /**
   * Creates an entry for an attribute on person that can
   * be added as a selectable and with a context menu for 
   * criteria restriction.
   */
  _createPersonMenu : function(personUl, attributeInput, personMenuItems)
  {
    // The attribute param can either be a string, used to create an
    // PersonAttribute, or it can be an already created PersonAttribute.
    var attribute;
    if(Mojo.util.isString(attributeInput))
    {
      var attrDTO = this._person.getAttributeDTO(attributeInput);
      var dereference = (attrDTO instanceof Mojo.dto.AttributeEnumerationDTO ||
        attrDTO instanceof Mojo.dto.AttributeReferenceDTO) ? true : false;
  
      var attrDTOMd = attrDTO.getAttributeMdDTO();
  
      var attribute = new MDSS.PersonAttribute({
        type: this._person.getType(),
        displayLabel: attrDTOMd.getDisplayLabel(),
        attributeName: attrDTOMd.getName()
      }, dereference);  
    }
    else
    {
      attribute = attributeInput;
    }
  
    var li = document.createElement('li');
    li.id = attribute.getKey()+"_li";
    
    var check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element:check, checked:false});
    YAHOO.util.Event.on(check, 'click', this._personAttributeHandler, attribute, this);
    li.appendChild(check);

    var items = [];
    if(attribute.getAttributeName() === this._Person.DOB)
    {
      // Add exact match and range
      var exact = this._createExactItem(li, attribute);
      var range = this._createRangeItem(li, attribute);
      
      this._personItems[attribute.getAttributeName()+'-exact'] = exact;        
      this._personItems[attribute.getAttributeName()+'-range'] = range;
          
      items.push(exact);
      items.push(range);
    }
    // Hemoglobin requires a menu item to enable range criteria.
    else if(attribute.getAttributeName() === this._Person.HAEMOGLOBIN)
    {
      // Add exact match and range
      var range = this._createRangeItem(li, attribute);
      
      this._personItems[attribute.getAttributeName()+'-range'] = range;
          
      items.push(range);
    }
    else
    {
      // Use the JSON object sent from the server to build objects
      // compatible with YUI's context menu constructor.    
      var rawItems = personMenuItems[attribute.getAttributeName()];
      for(var i=0; i<rawItems.length; i++)
      {
        var rawItem = rawItems[i];
  
        var item = {
          text: rawItem.displayLabel,
          checked: false,
          onclick : {
            fn: this._personMenuItemHandler,
            obj: {attribute: attribute, value: rawItem.value, display: rawItem.displayLabel}, 
            scope: this
          }
        };
        
        this._personItems[attribute.getAttributeName()+'-'+rawItem.value] = item;
        items.push(item);
      }
    }
    
    
    // map the menu items to the LI node that contains the attribute data
    this._personMenus[li.id] = items;
    
    var span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(span);
    personUl.appendChild(li);
  },
  
  _toggleExact : function(attribute, clear)
  {
    var item = this._personItems[attribute.getAttributeName()+'-exact'];
    // The exact criteria is optional, so return if null
    if(item == null)
    {
      return;
    }
    
    item.checked = clear ? false : !item.checked;   
  
    var exact = document.getElementById(attribute.getKey()+"-exact");
    if(item.checked)
    {
      YAHOO.util.Dom.setStyle(exact, 'display', 'inline');
    }
    else
    {
      exact.value = '';
      YAHOO.util.Dom.setStyle(exact, 'display', 'none');
    }
    
    return item.checked;
  },
  
  _toggleRange : function(attribute, clear)
  {
    var item = this._personItems[attribute.getAttributeName()+'-range'];
    // The range criteria is optional, so return if null
    if(item == null)
    {
      return;
    }
    
    item.checked = clear ? false : !item.checked;
    
    var range1 = document.getElementById(attribute.getKey()+"-range1");
    var rangeSign = document.getElementById(attribute.getKey()+"-rangeSign");
    var range2 = document.getElementById(attribute.getKey()+"-range2");
    
    if(item.checked)
    {
      YAHOO.util.Dom.setStyle(range1, 'display', 'inline');
      YAHOO.util.Dom.setStyle(rangeSign, 'display', 'inline');
      YAHOO.util.Dom.setStyle(range2, 'display', 'inline');
    }
    else
    {
      range1.value = '';
      range2.value = '';

      YAHOO.util.Dom.setStyle(range1, 'display', 'none');
      YAHOO.util.Dom.setStyle(rangeSign, 'display', 'none');
      YAHOO.util.Dom.setStyle(range2, 'display', 'none');
    }
    
    return item.checked;
  },
  
  /**
   * Sets number restriction criteria by showing/hiding
   * input fields for exact value matching and range.
   */
  _toggleNumberInputs : function(eventType, event, obj)
  {
    var attribute = obj.attribute;
    
    var that = this;
    var checked;
    if(obj.type === 'exact')
    {
      this._toggleRange(attribute, true);
      checked = this._toggleExact(attribute);
    }
    else
    {
      this._toggleExact(attribute, true);
      checked = this._toggleRange(attribute);
    }
    
    // Always clear the criteria as there's no reason to
    // preserve it when toggling exact/range inputs.
    this._queryPanel.clearWhereCriteria(attribute.getKey());
    
    if(!checked && attribute.getAttributeName() === this._Person.DOB)
    {
      this._config.setProperty('dobCriteria', null);
    }
    else if(!checked)
    {
      // no criteria specified, so clear the mapping
      this._setPersonCriteria(attribute, null, null, false);
    }  
  },
  
  /**
   * Sets number criteria on a Person attribute.
   */
  _setPersonNumberCriteria : function(e, obj)
  {
    var attribute = obj.attribute;
    var value;
    if(obj.type === 'exact')
    {
      var exact = document.getElementById(attribute.getKey()+"-exact");
      value = exact.value;
    }
    else
    {
      var range1 = document.getElementById(attribute.getKey()+"-range1");
      var range2 = document.getElementById(attribute.getKey()+"-range2");
      value = range1.value+'-'+range2.value; // Will be split up in this._setPersonCriteria
    }
    
    // DOB uses the json config because WHERE criteria is not easily passed
    // between a date and sql integer.
    if(attribute.getAttributeName() === this._Person.DOB)
    {
      if(value === '' || value === '-')
      {
        value = null;
      }
    
      this._config.setProperty('dobCriteria', value);
      this._queryPanel.clearWhereCriteria(attribute.getKey());
      
      if(value != null)
      {
        this._queryPanel.addWhereCriteria(attribute.getKey(), value, value);
      }
    }
    else
    {
      this._setPersonCriteria(attribute, value, value, true);
    }    
  },
  
  /**
   * Creates the JSON necessary to let a user specify an exact
   * match on an attribute.
   */
  _createExactItem : function(li, attribute)
  {
    var exactInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(exactInput, 'type', 'text');
    YAHOO.util.Dom.setStyle(exactInput, 'display', 'none');
    YAHOO.util.Dom.addClass(exactInput, 'queryNumberCriteria');
    exactInput.id = attribute.getKey()+"-exact";
    
    var obj = {
      attribute: attribute,
      type: 'exact'
    };
    
    YAHOO.util.Event.on(exactInput, 'keyup', this._setPersonNumberCriteria, obj, this);

    li.appendChild(exactInput);

    return item = {
      text: MDSS.Localized.Exact_Value,
      checked: false,
      onclick : {
        fn: this._toggleNumberInputs,
        obj: {attribute: attribute, type:'exact'}, 
        scope: this
      }
    };
  },
  
  /**
   * Creates the JSON necessary to let a user specify an exact
   * match on an attribute.
   */
  _createRangeItem : function(li, attribute)
  {
    var rangeInput1 = document.createElement('input');
    YAHOO.util.Dom.setAttribute(rangeInput1, 'type', 'text');
    YAHOO.util.Dom.setStyle(rangeInput1, 'display', 'none');
    YAHOO.util.Dom.addClass(rangeInput1, 'queryNumberCriteria');
    rangeInput1.id = attribute.getKey()+"-range1";

    var obj = {
      attribute: attribute,
      type: 'range'
    };

    YAHOO.util.Event.on(rangeInput1, 'keyup', this._setPersonNumberCriteria, obj, this);

    var rangeSign = document.createElement('span');
    rangeSign.innerHTML = '-';
    YAHOO.util.Dom.setStyle(rangeSign, 'display', 'none');
    rangeSign.id = attribute.getKey()+"-rangeSign";

    var rangeInput2 = document.createElement('input');
    YAHOO.util.Dom.setAttribute(rangeInput2, 'type', 'text');
    YAHOO.util.Dom.setStyle(rangeInput2, 'display', 'none');
    YAHOO.util.Dom.addClass(rangeInput2, 'queryNumberCriteria');
    rangeInput2.id = attribute.getKey()+"-range2";

    YAHOO.util.Event.on(rangeInput2, 'keyup', this._setPersonNumberCriteria, obj, this);
    
    li.appendChild(rangeInput1);
    li.appendChild(rangeSign);
    li.appendChild(rangeInput2);  

    return item = {
      text: MDSS.Localized.Set_Range,
      checked: false,
      onclick : {
        fn: this._toggleNumberInputs,
        obj: {attribute: attribute, type:'range'}, 
        scope: this
      }
    };
  },
  
  /**
   * Creates an attribute entry for an integer
   * on household, along with a SUM option.
   */
  _createHouseholdInt : function(householdUl, attribute)
  {
    var li = document.createElement('li');
    var check = document.createElement('input');
    check.id = attribute.getKey();
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    this._defaults.push({element:check, checked:false});
    YAHOO.util.Event.on(check, 'click', this._householdAttributeHandler, attribute, this);
    
    var select = document.createElement('select');
    select.disabled = true; // default (must be checked to enabled)
    this._defaults.push({element:select, disabled:true, index:0});
    
    var noAgg = document.createElement('option');
    noAgg.value = '';
    YAHOO.util.Event.on(noAgg, 'click', this._householdAggregateHandler, attribute, this);
    select.appendChild(noAgg);
    
    var sum = document.createElement('option');
    sum.value = MDSS.QueryXML.Functions.SUM;
    sum.innerHTML = MDSS.QueryXML.Functions.SUM;
    sum.id = attribute.getKey() + '-' + MDSS.QueryXML.Functions.SUM;
    YAHOO.util.Event.on(sum, 'click', this._householdAggregateHandler, attribute, this);
    select.appendChild(sum);

    
    var span = document.createElement('span');
    span.innerHTML = attribute.getDisplayLabel();
    
    li.appendChild(check);
    li.appendChild(select);
    li.appendChild(span);
    householdUl.appendChild(li);
  },

  /**
   * Renders the QueryPanel to query on AggregatedCases.
   */
  render : function()
  {
    // render the panel
    this._queryPanel.render();
  }
});

MDSS.HouseholdAttribute = function(obj, dereference)
{
  Mojo.util.copy(new MDSS.AbstractAttribute(obj, dereference), this);
};
MDSS.HouseholdAttribute.prototype = {

};

MDSS.PersonAttribute = function(obj, dereference)
{
  Mojo.util.copy(new MDSS.AbstractAttribute(obj, dereference), this);
};
MDSS.PersonAttribute.prototype = {

};
