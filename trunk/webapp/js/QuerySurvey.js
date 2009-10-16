/**
 * Class to query for SurveyPoints.
 */
Mojo.Meta.newClass('MDSS.QuerySurvey', {

  Extends: MDSS.QueryBase,
  
  Instance : {
  
    initialize : function(queryList, householdMenuItems, personMenuItems, nets, positives)
    {
      MDSS.QueryBase.prototype.initialize.call(this);
  
      if(arguments.length === 1 && arguments[0] == null)
      {
        // FIXME used for inheritance optimization
        return;
      }
      
      this._positives = positives;
      
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
      
      this._netSelectables = {};
      this._netAggregateSelectables = {};
      
      // END: query objects
  
      // Key/value where key is attribute.getKey() + "_li"
      // (which is the id of the relevant LI node,
      // and value is an array of ContextMenuItems.
      this._menus = {};
      
      // Map of criteria ids and associated ContextMenuItems.
      this._menuItems = {};
      
      // List of attributes that can be used to specify numeric single and range criteria
      this._singleAndRangeAttributes = [];
      
  
      for(var i=0; i<queryList.length; i++)
      {
        this._queryPanel.addAvailableQuery(queryList[i]);
      }
      
      var attribute = new MDSS.QueryXML.Attribute(this._SurveyPoint.CLASS, this._SurveyPoint.SURVEYDATE, this._SurveyPoint.SURVEYDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(attribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(attribute);
      
      // Criteria for Person.DOB
      this._config.setProperty('dobCriteria', null);
  
      // Key of Person.RDTRESULT attribute for use with prevalence
      this._rdtResultKey = null;
      this._prevalenceSelectables = {};
      this._prevalenceCheck = null;
  
      this._buildQueryItems(householdMenuItems, personMenuItems, nets);
    },
  
    /**
     * Returns the method to save this SurveyPoint search.
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
    
    _getReportQueryType : function()
    {
      return 'INDICATOR_SURVEY';
    },
    
    _loadNumericCriteria : function(attributeName, userAlias, operator, value)
    {
      if(operator === MDSS.QueryXML.Operator.GE)
      {
        var item = this._menuItems[userAlias+'-range'];
        var obj = item.onclick.obj;
        
        this._setNumberInputValues(obj, value, null);
        this._toggleRange(obj.attribute, true);
      }
      // LastSprayed uses LE, but it is only for the single
      // value criteria, not the range.
      else if(operator === MDSS.QueryXML.Operator.LE
        && attributeName !== this._Household.LASTSPRAYED)
      {
        var item = this._menuItems[userAlias+'-range'];
        var obj = item.onclick.obj;
  
        this._setNumberInputValues(obj, null, value);
        this._toggleRange(obj.attribute, true);
      }
      else
      {
        var item = this._menuItems[userAlias+'-single'];
        var obj = item.onclick.obj;
  
        this._setNumberInputValues(obj, value, null);
        this._toggleSingle(obj.attribute, true);
      }
      
      this._setNumberCriteria(null, obj);
    },
  
    _loadQueryState : function(view)
    {
      var thisRef = this;
  
      var xml = view.getQueryXml();
      var parser = new MDSS.Query.Parser(xml);
  
      var selectStart = this._queryPanel.getStartDateCheck();
      var selectEnd = this._queryPanel.getEndDateCheck();
      
      var addPrevalence = false;
      parser.parseSelectables({
        attribute : function(entityAlias, attributeName, userAlias){
        
          thisRef._checkBox(userAlias);
        },
        sum: function(entityAlias, attributeName, userAlias){
  
          thisRef._checkBox(userAlias);
          thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.SUM);
        },
        min: function(entityAlias, attributeName, userAlias){
  
          thisRef._checkBox(userAlias);
          thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MIN);
        },
        max: function(entityAlias, attributeName, userAlias){
  
          thisRef._checkBox(userAlias);
          thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MAX);
        },
        avg: function(entityAlias, attributeName, userAlias){
  
          thisRef._checkBox(userAlias);
          thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.AVG);
        },
        count : function(entityAlias, attributeName, userAlias){
        
          thisRef._checkBox(userAlias);
        },
        sqlcharacter : function(entityAlias, attributeName, userAlias)
        {
          thisRef._checkBox(attributeName); // Date selection
        },
        sqldouble: function(entityAlias, attributeName, userAlias){
          
          // this checks for prevalence. If it makes it in here at all
          // that means at least one prevalence option was selected, but we
          // cannot add the selectables until the WHERE criteria has been parsed.
          addPrevalence = true;
        },
        sqlinteger: function(entityAlias, attributeName, userAlias){
  
          thisRef._checkBox(userAlias);
        },
        sqldate : function(entityAlias, attributeName, userAlias){
  
          if(userAlias === selectStart.id)
          {
            thisRef._checkBox(selectStart);
          }
          else if(userAlias === selectEnd.id)
          {
            thisRef._checkBox(selectEnd);
          }
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
          else if(userAlias === thisRef._SurveyPoint.SURVEYDATE)
          {
            var formatted = MDSS.Calendar.getLocalizedString(value);
          
            if(operator === MDSS.QueryXML.Operator.GE)
            {
              var start = thisRef._queryPanel.getStartDate();
              start.value = formatted;
            }
            else
            {
              var end = thisRef._queryPanel.getEndDate();
              end.value = formatted;
            }
          }
          else
          {
            // Set all attribute criteria with single or numeric ranges.
            if(attributeName === thisRef._Person.HAEMOGLOBIN ||
               attributeName === thisRef._Household.LASTSPRAYED)
            {
              thisRef._loadNumericCriteria(attributeName, userAlias, operator, value);
            }
            else if(attributeName === thisRef._Person.RDTRESULT)
            {
              var enumIds = value.split(',');
              var attribute = null;
              for(var i=0; i<enumIds.length; i++)
              {
                var item = thisRef._menuItems[userAlias+'-'+enumIds[i]];
                item.checked = true;
                attribute = item.onclick.obj.attribute;
  
                thisRef._queryPanel.addWhereCriteria(attribute.getKey(), enumIds[i], item.onclick.obj.display);              
                
              }
              
              var selectable = attribute.getSelectable(false);  
              var condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.CONTAINS_ANY, value);
              
              thisRef._personCriteria[attribute.getKey()] = condition;
            }
            else
            {
              // Handle all other attribute criteria.
              var item = thisRef._menuItems[userAlias+'-'+value];
              item.checked = true;         
              
              // Re-use the onclick object, which contains all information
              // needed to rebuild the criteria.
              var obj = item.onclick.obj;
  
              var attribute = item.onclick.obj.attribute;
              var display = item.onclick.obj.display;
              
              if(entityAlias === thisRef._Person.CLASS)
              {
                thisRef._setPersonCriteria(attribute, value, display, true);
              }
              else if(entityAlias === thisRef._Household.CLASS)
              {
                thisRef._setHouseholdCriteria(attribute, value, display, true);
              }
            }
          }
        }
      });
      
      if(addPrevalence)
      {
        this._checkBox(this._prevalenceCheck);
      }
      
      // check if DOB has been added as criteria
      var configRaw = view.getConfig();
      var config = new MDSS.Query.Config(configRaw);
      var dobCrit = config.getProperty("dobCriteria");
      if(dobCrit != null)
      {
        if(dobCrit.indexOf('-') != -1)
        {
          var range = dobCrit.split('-');
          if(range.length == 2)
          {
            if(range[0] !== '')
            {
              this._loadNumericCriteria(this._Person.DOB, this._Person.DOB, MDSS.QueryXML.Operator.GE, range[0]);
            }
            
            if(range[1] !== '')
            {
              this._loadNumericCriteria(this._Person.DOB, this._Person.DOB, MDSS.QueryXML.Operator.LE, range[1]);
            }
          }
          else
          {
            this._loadNumericCriteria(this._Person.DOB, this._Person.DOB, MDSS.QueryXML.Operator.GE, range[0]);
          }
        }
        else
        {
          this._loadNumericCriteria(this._Person.DOB, this._Person.DOB, MDSS.QueryXML.Operator.EQ, dobCrit);
        }
      }
      
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
      var queryXML = this._constructQuery(true);
      var xml = queryXML.getXML();
  
      var request = new MDSS.Request({
        thisRef: this,
        onSuccess : function(layers){
          var layersObj = Mojo.Util.getObject(layers);
          this.thisRef._queryPanel.createMap(layersObj);
        }
      });
  
      var layerIds = this._queryPanel.getSelectedLayers();
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");
  
      Mojo.$.dss.vector.solutions.query.MappingController.mapSurveyQuery(request, xml, this._config.getJSON(), layerIds, savedSearchId);
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
  
      // Household selectables
      var addedHousehold = false;
      var householdSel = Mojo.Util.getKeys(this._householdSelectables);
      for(var i=0; i<householdSel.length; i++)
      {
        addedHousehold = true;
      
        var name = householdSel[i];
        var selectable = this._householdSelectables[name];
        queryXML.addSelectable(this._Household.CLASS+'-'+name, selectable);
      }
      
      // Household Aggregates
      var aggNames = Mojo.Util.getKeys(this._householdAggregateSelectables);
      for(var i=0; i<aggNames.length; i++)
      {
        addedHousehold = true;
  
        var name = aggNames[i];
        var selectable = this._householdAggregateSelectables[name];
  
        queryXML.addSelectable(this._Household.CLASS+'_'+name, selectable);
      }
      
      // Household criteria
      var householdCrit = Mojo.Util.getKeys(this._householdCriteria);
      var and = new MDSS.QueryXML.And();
      for(var i=0; i<householdCrit.length; i++)
      {
        addedHousehold = true;
  
        var name = householdCrit[i];
        var condition = this._householdCriteria[name];
        and.addCondition(name, condition);
      }
      
      if(addedHousehold)
      {
        var householdType = this._household.getType();
        var householdQuery = new MDSS.QueryXML.Entity(householdType, householdType);
        queryXML.addEntity(householdQuery);
        
        if(and.getSize() > 0)
        {
          var composite = new MDSS.QueryXML.CompositeCondition(and);
          householdQuery.setCondition(composite);
        }
      }
      
     // Person selectables
      var addedPerson = false;
      var personSel = Mojo.Util.getKeys(this._personSelectables);
      for(var i=0; i<personSel.length; i++)
      {
        addedPerson = true;
      
        var name = personSel[i];
        var selectable = this._personSelectables[name];
        queryXML.addSelectable(this._Person.CLASS+'-'+name, selectable);
      }
      
      // Person Aggregates
      var aggNames = Mojo.Util.getKeys(this._personAggregateSelectables);
      for(var i=0; i<aggNames.length; i++)
      {
        addedPerson = true;
  
        var name = aggNames[i];
        var selectable = this._personAggregateSelectables[name];
  
        queryXML.addSelectable(this._Person.CLASS+'_'+name, selectable);
      }
      
      // Person criteria
      var personCrit = Mojo.Util.getKeys(this._personCriteria);
      and = new MDSS.QueryXML.And();
      for(var i=0; i<personCrit.length; i++)
      {
        addedPerson = true;
  
        var name = personCrit[i];
        var condition = this._personCriteria[name];
        and.addCondition(name, condition);
      }
      
      // prevalence selectables
      var prevalenceKeys = Mojo.Util.getKeys(this._prevalenceSelectables);
      for(var i=0; i<prevalenceKeys.length; i++)
      {
        addedPerson = true;
      
        var name = prevalenceKeys[i];
        var selectable = this._prevalenceSelectables[name];
        queryXML.addSelectable(name, selectable);
      }
  
      if(addedPerson)
      {
        var personType = this._person.getType();
        var personQuery = new MDSS.QueryXML.Entity(personType, personType);
        queryXML.addEntity(personQuery);
        
        if(and.getSize() > 0)
        {
          var composite = new MDSS.QueryXML.CompositeCondition(and);
          personQuery.setCondition(composite);
        }
      }
      
      // net selectables
      var netKeys = Mojo.Util.getKeys(this._netSelectables);
      for(var i=0; i<netKeys.length; i++)
      {
        var name = netKeys[i];
        var selectable = this._netSelectables[name];
        
        var attribute = selectable.getComponent();
        
        queryXML.addSelectable(attribute.getEntityAlias(), selectable);
        var type = "dss.vector.solutions.intervention.monitor.HouseholdNet";
        
        var householdNetQuery = new MDSS.QueryXML.Entity(type, attribute.getEntityAlias());
        queryXML.addEntity(householdNetQuery);
      }
      
      var netAggKeys = Mojo.Util.getKeys(this._netAggregateSelectables);
      for(var i=0; i<netAggKeys.length; i++)
      {
        var name = netAggKeys[i];
        var aggSelectable = this._netAggregateSelectables[name];
        
        var attribute = aggSelectable.getComponent().getSelectable().getComponent();
        
        queryXML.addSelectable(attribute.getEntityAlias(), aggSelectable);
        var type = "dss.vector.solutions.intervention.monitor.HouseholdNet";
        
        var householdNetQuery = new MDSS.QueryXML.Entity(type, attribute.getEntityAlias());
        queryXML.addEntity(householdNetQuery);
      }
      
      // Date selectables/criteria
      var conditions = [];
      if(this._startDate != null)
      {
        conditions.push(this._startDate);
      }
  
      if(this._endDate != null)
      {
        conditions.push(this._endDate);
      }    
      
      var keys = Mojo.Util.getKeys(this._dateGroupSelectables);
      for(var i=0; i < keys.length; i++)
      {
        var selectable = this._dateGroupSelectables[keys[i]];
        if(selectable != null)
        {
          queryXML.addSelectable(keys[i], selectable);
        }
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
      if(dateAndOr != null)
      {
        var composite = new MDSS.QueryXML.CompositeCondition(dateAndOr);
        surveyPointQuery.setCondition(composite);
      }
  
      // count
      if(this._countSelectable != null)
      {
        queryXML.addSelectable(surveyPointQuery.getAlias()+'_globalCount', this._countSelectable);
      }
      
      var keys = Mojo.Util.getKeys(this._dateGroupSelectables);
      for(var i=0; i < keys.length; i++)
      {
        var selectable = this._dateGroupSelectables[keys[i]];
        if(selectable != null)
        {
          queryXML.addSelectable(keys[i], selectable);
        }
      }
      
      return queryXML;
    },
    
    /**
     * Clears (unchecks) all menu items for an attribute.
     */
    _clearAllAttributeItems : function(key)
    {
      var items = this._menus[key+"_li"];
      if(items != null)
      {
        for(var i=0; i<items.length; i++)
        {
          items[i].checked = false;
        }
      }
    },
    
    _setHouseholdCriteria : function(attribute, value, display, addCriteria)
    {
      var key = attribute.getKey();
      var selectable = attribute.getSelectable(false); 
  
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
      else if(attrDTO instanceof AttributeReferenceDTO ||
        attrDTO instanceof AttributeBooleanDTO)
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
    },
    
    _setPersonCriteria : function(attribute, value, display, addCriteria)
    {
      var key = attribute.getKey();
    
      var condition;
      
      var attrDTO = this._person.getAttributeDTO(attribute.getAttributeName());
      if(attrDTO instanceof AttributeEnumerationDTO)
      {
        // force removal of prevalence columns.
        // This is done for simplicity instead of synchronizing
        // the columns.
        if(key === this._rdtResultKey)
        {
          this._setPrevalence(false);
        }
  
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
        
        // add prevalence columns
        if(key === this._rdtResultKey)
        {
          this._setPrevalence(true);
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
      else if(attrDTO instanceof AttributeReferenceDTO ||
        attrDTO instanceof AttributeBooleanDTO)
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
      
        // check for either single or range matches
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
    },
    
    /**
     * Resets all defaults, including clearing all criteria and unchecking
     * all context menu items.
     */
    _resetToDefault : function()
    {
      MDSS.QueryBase.prototype._resetToDefault.call(this); // super
    
      this._setPrevalence(false);
    
      // uncheck all menu items
      var keys = Mojo.Util.getKeys(this._menuItems);
      for(var i=0; i<keys.length; i++)
      {
        var item = this._menuItems[keys[i]];
        item.checked = false;
      }
  
      // clear any single/range input elements
      for(var i=0; i<this._singleAndRangeAttributes.length; i++)
      {
        var attribute = this._singleAndRangeAttributes[i];
        this._toggleSingle(attribute, false);
        this._toggleRange(attribute, false);
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
      if(this._household.getAttributeDTO(attributeName) instanceof AttributeNumberDTO)
      {
        this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
      }
    },
    
    /**
     * Helper method to add Household attributes to selectables and as a column.
     */
    _addNetAttribute : function(attribute)
    {
      var column = new YAHOO.widget.Column(attribute.getColumnObject());
      column = this._queryPanel.insertColumn(column);
  
      var attributeName = attribute.getAttributeName();
      
      var selectable = attribute.getSelectable(true);
  
      this._netSelectables[attribute.getKey()] = selectable;
  
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
        delete this._householdSelectables[key];
      }
  
      // remove all possible query references
      delete this._householdAggregateSelectables[attribute.getKey()];
  
      if(removeColumn)
      {
        var column = this._queryPanel.getColumn(key);
        this._queryPanel.removeColumn(column);
      }
  
      if(removeThematic &&
        this._household.getAttributeDTO(attributeName) instanceof AttributeNumberDTO)
      {
        this._queryPanel.removeThematicVariable(attribute.getKey());
      }
    },
    
    /**
     * Removes an attribute as a selectable and column.
     */
    _removeNetAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
    {
      var attributeName = attribute.getAttributeName();
      var key = attribute.getKey();
  
      if(removeSelectable)
      {
        delete this._netSelectables[key];
      }
  
      // remove all possible query references
      delete this._netAggregateSelectables[attribute.getKey()];
  
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
      if(this._person.getAttributeDTO(attributeName) instanceof AttributeNumberDTO ||
        attributeName === this._Person.DOB)
      {
        this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
      }
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
        delete this._personSelectables[key];
      }
  
      // remove all possible query references
      delete this._personAggregateSelectables[key];
  
      if(removeColumn)
      {
        var column = this._queryPanel.getColumn(key);
        this._queryPanel.removeColumn(column);
      }
  
      if(removeThematic && (
        this._person.getAttributeDTO(attributeName) instanceof AttributeNumberDTO ||
        attributeName === this._Person.DOB))
      {
        this._queryPanel.removeThematicVariable(key);
      }
    },
    
    _setPrevalence : function(doSet, excludeIds)
    {
      // create columns and selectables for each checked RDTResult menu item.
      // Or, if none are checked, create a generic prevalence column
      if(doSet)
      {
        var useDefault = true;
        var menuKeys = Mojo.Util.getKeys(this._menuItems);
        for(var i=0; i<menuKeys.length; i++)
        {
          var menuKey = menuKeys[i];
          if(menuKey.indexOf(this._rdtResultKey) != -1)
          {
            var menuItem = this._menuItems[menuKey];
            if(menuItem.checked)
            {
              var value = menuItem.onclick.obj.value;
              var name = "prevalence_"+value;  // name == key == userAlias == enum id
              
              // the option must be a positive result
              if(!this._contains(this._positives, value))
              {
                continue;
              }
  
              useDefault = false;
              var display = menuItem.onclick.obj.display + " " + MDSS.Localized.Prevalence;
          
              var sqlDouble = new MDSS.QueryXML.Sqldouble(this._Person.CLASS, name, name, display, true);
              var selectable = new MDSS.QueryXML.Selectable(sqlDouble);
          
              this._queryPanel.insertColumn({key:name,label:display});
          
              this._prevalenceSelectables[name] = selectable;
              this._queryPanel.addThematicVariable(this._Person.CLASS, sqlDouble.getName(), sqlDouble.getUserAlias(), display);
            }
          }
        }
        
        if(useDefault)
        {
          var display = MDSS.Localized.Prevalence;
          var name = "prevalence"; //  name == key == userAlias
          
          var sqlDouble = new MDSS.QueryXML.Sqldouble(this._Person.CLASS, name, name, display, true);
          var selectable = new MDSS.QueryXML.Selectable(sqlDouble);
          
          this._queryPanel.insertColumn({key:name,label:display});
          
          this._prevalenceSelectables[name] = selectable;
          this._queryPanel.addThematicVariable(this._Person.CLASS, sqlDouble.getName(), sqlDouble.getUserAlias(), display);
        }
      }
      else
      {
        // remove all prevalence entries
        var keys = Mojo.Util.getKeys(this._prevalenceSelectables);
        for(var i=0; i<keys.length; i++)
        {
          var key = keys[i];
          var column = this._queryPanel.getColumn(key);
          this._queryPanel.removeColumn(column);
          this._queryPanel.removeThematicVariable(key);
        }
        
        this._prevalenceSelectables = {};
      }
    },
    
    _prevalenceHandler : function(e)
    {
      var check = e.target;
      this._setPrevalence(check.checked);
    },
    
    _contains : function(array, value)
    {
      for(var i=0; i<array.length; i++)
      {
        if(array[i] === value)
        {
          return true;
        }
      }
      
      return false;
    },
    
    _netAttributeHandler : function(e, attribute)
    {
      var check = e.target;
      if(check.checked)
      {
        this._addNetAttribute(attribute);
        
        var select = check.nextSibling;
        if(select && select.nodeName == 'SELECT' && select.disabled === true)
        {
          select.disabled = false;
        }
      }
      else
      {
        this._removeNetAttribute(attribute, true, true, true);
  
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
     * on a net attribute.
     */
    _netAggregateHandler : function(e, attribute)
    {
      var func = e.target.value;
  
      var attributeName = attribute.getAttributeName();
      var key = attribute.getKey();
  
      var selectable = attribute.getSelectable();
  
      this._queryPanel.updateColumnLabel(key, func);
  
      // special cases
  
      if(func === '')
      {
        // Use regular selectable (this is just here for clarity).
        this._removeNetAttribute(attribute, false, true, false);
        this._netSelectables[attribute.getKey()] = selectable;
  
  
        return;
      }
  
      // aggregate functions 
      var aggFunc = null;
      var displayLabel = "("+func+") "+ attribute.getDisplayLabel();
      if(func === MDSS.QueryXML.Functions.SUM)
      {
        aggFunc = new MDSS.QueryXML.SUM(selectable, key, displayLabel);
      }
      else if(func === MDSS.QueryXML.Functions.MIN)
      {
        aggFunc = new MDSS.QueryXML.MIN(selectable, key, displayLabel);
      }
      else if(func === MDSS.QueryXML.Functions.MAX)
      {
        aggFunc = new MDSS.QueryXML.MAX(selectable, key, displayLabel);
      }
      else if(func === MDSS.QueryXML.Functions.AVG)
      {
        aggFunc = new MDSS.QueryXML.AVG(selectable, key, displayLabel);
      }
  
      this._removeNetAttribute(attribute, false, true, false);
  
      var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
      this._netAggregateSelectables[attribute.getKey()] = aggSelectable;
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
  
        // Clear any criteria since criteria cannot exist
        // without the attribute as a selectable.
        var key = attribute.getKey();
        delete this._householdCriteria[key];
        this._clearAllAttributeItems(key);
  
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
        
        // Clear any criteria since criteria cannot exist
        // without the attribute as a selectable.
        var key = attribute.getKey();
        this._clearAllAttributeItems(key);
        delete this._personCriteria[key];
  
        var select = check.nextSibling;
        if(select && select.nodeName == 'SELECT' && select.disabled === false)
        {
          select.selectedIndex = 0;
          select.disabled = true;
        }
        
        // reset prevalence
        if(key === this._rdtResultKey && this._prevalenceCheck.checked)
        {
          this._setPrevalence(false);
          this._setPrevalence(true);
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
      var displayLabel = "("+func+") "+ attribute.getDisplayLabel();
      if(func === MDSS.QueryXML.Functions.SUM)
      {
        aggFunc = new MDSS.QueryXML.SUM(selectable, key, displayLabel);
      }
  
      this._removeHouseholdAttribute(attribute, false, true, false);
  
      var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
      this._householdAggregateSelectables[attribute.getKey()] = aggSelectable;
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
  
    _toggleCount : function(e, attribute)
    {
      var check = e.target;
  
      if(check.checked)
      {
        var selectable = attribute.getSelectable();
  
        var count = new MDSS.QueryXML.COUNT(selectable, attribute.getKey());
        var aggSelectable = new MDSS.QueryXML.Selectable(count);
        this._countSelectable = aggSelectable;
  
        this._queryPanel.insertColumn(attribute.getColumnObject());
  
        
        // ADD THEMATIC VARIABLE
        this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
      }
      else
      {
        var column = this._queryPanel.getColumn(attribute.getKey());
        this._queryPanel.removeColumn(column);
  
        this._countSelectable = null;
  
        this._queryPanel.removeThematicVariable(attribute.getKey());
      }
    },   
    
    /**
     * Builds the query items for the left column.
     */
    _buildQueryItems : function(householdMenuItems, personMenuItems, nets)
    {
      /*
       * Target
       */
      // 3. area (geo entity search)
      var boundSearch = Mojo.Util.bind(this, this._displaySearch);
      this._queryPanel.addQueryItem({
        html: MDSS.Localized.Target_Search+' <img src="./imgs/icons/world.png"/>',
        onclick: {handler: boundSearch},
        id: "areaItem"
      });
      
      this._queryPanel.addQueryItem({
          html: this._getCountDiv(this,"Group_By", this._SurveyPoint),
          id: 'globalCount'
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
  
      // 5. Status
      this._createHouseholdMenu(householdUl, this._Household.URBAN, householdMenuItems);
      
      // 6. # People
      attribute = new MDSS.HouseholdAttribute({
        type: this._household.getType(),
        displayLabel: this._household.getPeopleMd().getDisplayLabel(),
        attributeName: this._household.getPeopleMd().getName()
      });
      
      this._createHouseholdInt(householdUl, attribute);
      
      // 7. Wall
      this._createHouseholdMenu(householdUl, this._Household.WALL, householdMenuItems);
      
      // 7. Roof
      this._createHouseholdMenu(householdUl, this._Household.ROOF, householdMenuItems);    
      
      // 9. windows
      this._createHouseholdMenu(householdUl, this._Household.WINDOWTYPE, householdMenuItems);
      
      // 10. # rooms
      attribute = new MDSS.HouseholdAttribute({
        type: this._household.getType(),
        displayLabel: this._household.getRoomsMd().getDisplayLabel(),
        attributeName: this._household.getRoomsMd().getName()
      });
      
      this._createHouseholdInt(householdUl, attribute);
      
      // 11. Last sprayed
      this._createHouseholdMenu(householdUl, this._Household.LASTSPRAYED, householdMenuItems);
      
      // 12. # nets
      attribute = new MDSS.HouseholdAttribute({
        type: this._household.getType(),
        displayLabel: this._household.getNetsMd().getDisplayLabel(),
        attributeName: this._household.getNetsMd().getName()
      });
      
      this._createHouseholdInt(householdUl, attribute);
      
  
  
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
        menuBuilder : Mojo.Util.bind(this, this._menuBuilder)
      });
      
      // nets
      var netDiv = document.createElement('div');
      
      var labelDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
      labelDiv.innerHTML = MDSS.Localized.Nets
      
      var netUl = document.createElement('ul');
      YAHOO.util.Dom.addClass(netUl, 'gridList');
      YAHOO.util.Dom.setStyle(netUl, 'clear', 'both');
  
      netDiv.appendChild(labelDiv);
      netDiv.appendChild(netUl);
      
      for(var i=0; i<nets.length; i++)
      {
        var net = nets[i];
        var netAttribute = new MDSS.BasicAttribute(net);
        
        var li = document.createElement('li');
        var check = document.createElement('input');
        check.id = netAttribute.getKey();
        YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
        this._defaults.push({element: check, checked: false});
        YAHOO.util.Event.on(check, 'click', this._netAttributeHandler, netAttribute, this);
        var span = document.createElement('span');
        span.innerHTML = netAttribute.getDisplayLabel();
        
        var select = document.createElement('select');
        this._defaults.push({element:select, index:0});
        var aggOptions = [''];
        aggOptions = aggOptions.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));
  
        for(var k=0; k<aggOptions.length; k++)
        {
          var aggOption = aggOptions[k];
          var optionEl = document.createElement('option');
          optionEl.id = netAttribute.getKey()+'-'+aggOption;
          optionEl.innerHTML = aggOption;
          YAHOO.util.Dom.setAttribute(optionEl, 'value', aggOption);
  
          YAHOO.util.Event.on(optionEl, 'click', this._netAggregateHandler, netAttribute, this);
  
          select.appendChild(optionEl);
        }
        select.disabled = true; // default (must be checked to enabled)
  
        
        li.appendChild(check);
        li.appendChild(select);
        li.appendChild(span);
        netUl.appendChild(li);
      }
      
      this._queryPanel.addQueryItem({
        html : netDiv,
        id: 'netItems',
        menuBuilder : Mojo.Util.bind(this, this._menuBuilder)
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
      attribute = new MDSS.PersonAttribute({
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
      attribute = new MDSS.PersonAttribute({
        type: this._person.getType(),
        displayLabel: MDSS.Localized.Age,
        attributeName: this._person.getDobMd().getName()
      });
      attribute.setKey(this._Person.DOB);// overwrite key for use with SQL pass-through
      
      this._createPersonMenu(personUl, attribute, personMenuItems);
  
      // 17. Sex
      this._createPersonMenu(personUl, this._Person.SEX, personMenuItems); 
      
      // 18. Pregnant
      this._createPersonMenu(personUl, this._Person.PREGNANT, personMenuItems);   
      
      // 19. slept under net
      this._createPersonMenu(personUl, this._Person.SLEPTUNDERNET, personMenuItems);   
  
      // ??. hemoglobin measured
      this._createPersonMenu(personUl, this._Person.HAEMOGLOBIN, personMenuItems);
       
      // 20. hemoglobin measured
      this._createPersonMenu(personUl, this._Person.HAEMOGLOBINMEASURED, personMenuItems);   
      
      // 21. Anemia Treatment
      this._createPersonMenu(personUl, this._Person.ANAEMIATREATMENT, personMenuItems);    
  
      // 22. Iron given
      this._createPersonMenu(personUl, this._Person.IRON, personMenuItems);    
  
      // 23. RDT Treatment
      this._createPersonMenu(personUl, this._Person.RDTTREATMENT, personMenuItems);   
      
       // 27. Bloodslide
      this._createPersonMenu(personUl, this._Person.BLOODSLIDE, personMenuItems);      
      
      // 24. RDT Result
      /* FIXME broken from ontology refactor
      var rdtResultAttr = this._createPersonMenu(personUl, this._Person.RDTRESULT, personMenuItems);
      this._rdtResultKey = rdtResultAttr.getKey();
      */
       
      // 25. Prevalence
      li = document.createElement('li');
      this._prevalenceCheck = document.createElement('input');
      this._prevalenceCheck.id = "Prevalence";
      YAHOO.util.Dom.setAttribute(this._prevalenceCheck, 'type', 'checkbox');
      this._defaults.push({element:this._prevalenceCheck, checked:false});
      YAHOO.util.Event.on(this._prevalenceCheck, 'click', this._prevalenceHandler, null, this);
      span = document.createElement('span');
      span.innerHTML = MDSS.Localized.Prevalence;
      
      //this._prevalenceCheck.disabled = true; // RDTRESULT must be checked first.
      
      li.appendChild(this._prevalenceCheck);
      li.appendChild(span);
      personUl.appendChild(li);    
      
      // 28. Fever
      this._createPersonMenu(personUl, this._Person.FEVER, personMenuItems);   
      
      // 29. Fever Treatment
      this._createPersonMenu(personUl, this._Person.FEVERTREATMENT, personMenuItems);   
      
      // 30. malaria
      this._createPersonMenu(personUl, this._Person.MALARIA, personMenuItems);   
      
      // 31. Malaria Treatment
      this._createPersonMenu(personUl, this._Person.MALARIATREATMENT, personMenuItems);   
      
      // 32. Payment
      this._createPersonMenu(personUl, this._Person.PAYMENT, personMenuItems);   
      
      this._queryPanel.addQueryItem({
        html : personDiv,
        id: 'personItems',
        menuBuilder : Mojo.Util.bind(this, this._menuBuilder)
      });
    },
  
    _menuBuilder : function(outerLi, targetEl)
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
      
      var items = this._menus[li.id];
      if(items != null)
      {
        return items;
      }
      else
      {
        return [];
      }
    },
    
    _personMenuItemHandler : function(eventType, event, obj)
    {
      var attribute = obj.attribute;
      var value = obj.value;
      var display = obj.display;
      
      var item = this._menuItems[attribute.getKey()+'-'+value];
      item.checked = !item.checked;
      
      this._setPersonCriteria(attribute, value, display, item.checked);
    },
    
    _householdMenuItemHandler : function(eventType, event, obj)
    {
      var attribute = obj.attribute;
      var value = obj.value;
      var display = obj.display;
      
      var item = this._menuItems[attribute.getKey()+'-'+value];
      item.checked = !item.checked;
      
      this._setHouseholdCriteria(attribute, value, display, item.checked);
    },
    
    _createHouseholdMenu : function(householdUl, attributeName, householdMenuItems)
    {
      var attrDTO = this._household.getAttributeDTO(attributeName);
      var dereference = (attrDTO instanceof AttributeEnumerationDTO ||
         attrDTO instanceof AttributeReferenceDTO) ? true : false;
    
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
  
      li.appendChild(check);
  
      var items = [];
      if(attributeName === this._Household.LASTSPRAYED)
      {
        var single = this._createSingleItem(check, li, attribute);
        
        this._menuItems[attribute.getKey()+'-single'] = single;
            
        items.push(single);
      }
      else if(householdMenuItems[attribute.getAttributeName()] != null)
      {
        // Use the JSON object sent from the server to build objects
        // compatible with YUI's context menu constructor.
        var rawItems = householdMenuItems[attribute.getAttributeName()];
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
          
          this._menuItems[attribute.getKey()+'-'+rawItem.value] = item;
          items.push(item);
        }
      }
      
      // map the menu items to the LI node that contains the attribute data
      this._menus[li.id] = items;
      
      var span = document.createElement('span');
      span.innerHTML = attribute.getDisplayLabel();
      
      li.appendChild(span);
      householdUl.appendChild(li);
      
      return attribute;
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
      if(Mojo.Util.isString(attributeInput))
      {
        var attrDTO = this._person.getAttributeDTO(attributeInput);
        var dereference = (attrDTO instanceof AttributeEnumerationDTO ||
          attrDTO instanceof AttributeReferenceDTO) ? true : false;
    
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
        // Add single match and range
        var single = this._createSingleItem(check, li, attribute);
        var range = this._createRangeItem(check, li, attribute);
        
        this._menuItems[attribute.getKey()+'-single'] = single;        
        this._menuItems[attribute.getKey()+'-range'] = range;
            
        items.push(single);
        items.push(range);
      }
      // Hemoglobin requires a menu item to enable range criteria.
      else if(attribute.getAttributeName() === this._Person.HAEMOGLOBIN)
      {
        var range = this._createRangeItem(check, li, attribute);
        
        this._menuItems[attribute.getKey()+'-range'] = range;
            
        items.push(range);
      }
      else if(personMenuItems[attribute.getAttributeName()] != null)
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
        
          this._menuItems[attribute.getKey()+'-'+rawItem.value] = item;
          items.push(item);
        }
      }
      
      
      // map the menu items to the LI node that contains the attribute data
      this._menus[li.id] = items;
      
      var span = document.createElement('span');
      span.innerHTML = attribute.getDisplayLabel();
      
      li.appendChild(span);
      personUl.appendChild(li);
      
      return attribute;
    },
    
    _toggleSingle : function(attribute, toggleOverride)
    {
      var item = this._menuItems[attribute.getKey()+'-single'];
      // The single criteria is optional, so return if null
      if(item == null)
      {
        return;
      }
      
      item.checked = Mojo.Util.isBoolean(toggleOverride) ? toggleOverride : !item.checked; 
    
      var single = document.getElementById(attribute.getKey()+"-single");
      if(item.checked)
      {
        YAHOO.util.Dom.setStyle(single, 'display', 'inline');
      }
      else
      {
        single.value = '';
        YAHOO.util.Dom.setStyle(single, 'display', 'none');
      }
      
      return item.checked;
    },
    
    _toggleRange : function(attribute, toggleOverride)
    {
      var item = this._menuItems[attribute.getKey()+'-range'];
      // The range criteria is optional, so return if null
      if(item == null)
      {
        return;
      }
      
      item.checked = Mojo.Util.isBoolean(toggleOverride) ? toggleOverride : !item.checked;
      
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
     * input fields for single value matching and range.
     */
    _toggleNumberInputs : function(eventType, event, obj)
    {
      var attribute = obj.attribute;
      
      var that = this;
      var checked;
      if(obj.type === 'single')
      {
        this._toggleRange(attribute, false);
        checked = this._toggleSingle(attribute);
      }
      else
      {
        this._toggleSingle(attribute, false);
        checked = this._toggleRange(attribute);
      }
      
      // Always clear the criteria as there's no reason to
      // preserve it when toggling single/range inputs.
      this._queryPanel.clearWhereCriteria(attribute.getKey());
      
      if(!checked && attribute.getAttributeName() === this._Person.DOB)
      {
        this._config.setProperty('dobCriteria', null);
      }
      else if(!checked)
      {
        // no criteria specified, so clear the mapping
        if(attribute.getType() === this._Person.CLASS)
        {
          this._setPersonCriteria(attribute, null, null, true);
        }
        else
        {
          this._setHouseholdCriteria(attribute,  null, null, true);
        }
      }
    },
    
    /**
     * Sets the numeric values on the text inputs for single and range
     * criteria.
     */
    _setNumberInputValues : function(obj, value1, value2)
    {
      var attribute = obj.attribute;
      if(obj.type === 'single')
      {
        if(value1 != null)
        {
          document.getElementById(attribute.getKey()+"-single").value = value1;
        }
      }
      else
      {
        if(value1 != null)
        {
          document.getElementById(attribute.getKey()+"-range1").value = value1;
        }
        
        if(value2 != null)
        {
          document.getElementById(attribute.getKey()+"-range2").value = value2;
        }
      }
      
    },
    
    /**
     * Sets number criteria on an attribute.
     */
    _setNumberCriteria : function(e, obj)
    {
      var attribute = obj.attribute;
      var value;
      if(obj.type === 'single')
      {
        var single = document.getElementById(attribute.getKey()+"-single");
        value = single.value;
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
      else if(attribute.getType() === this._Person.CLASS)
      {
        this._setPersonCriteria(attribute, value, value, true);
      }
      else if(attribute.getType() === this._Household.CLASS)
      {
        this._setHouseholdCriteria(attribute, value, value, true);
      }
    },
    
    /**
     * Creates the JSON necessary to let a user specify an single
     * match on an attribute.
     */
    _createSingleItem : function(check, li, attribute)
    {
      this._singleAndRangeAttributes.push(attribute);
    
      var singleInput = document.createElement('input');
      YAHOO.util.Dom.setAttribute(singleInput, 'type', 'text');
      YAHOO.util.Dom.setStyle(singleInput, 'display', 'none');
      YAHOO.util.Dom.addClass(singleInput, 'queryNumberCriteria');
      singleInput.id = attribute.getKey()+"-single";
      
      var obj = {
        attribute: attribute,
        type: 'single'
      };
      
      YAHOO.util.Event.on(singleInput, 'keyup', this._setNumberCriteria, obj, this);
  
      li.appendChild(singleInput);
  
      // When the check box is toggled, be sure to clear and hide the input
      YAHOO.util.Event.on(check, 'click', function(e, attr){
        if(!e.target.checked)
        {
          this._toggleSingle(attr, false);
        }
      }, attribute, this);
  
      return item = {
        text: MDSS.Localized.Single_Value,
        checked: false,
        onclick : {
          fn: this._toggleNumberInputs,
          obj: obj, 
          scope: this
        }
      };
    },
    
    /**
     * Creates the JSON necessary to let a user specify an single
     * match on an attribute.
     */
    _createRangeItem : function(check, li, attribute)
    {
      this._singleAndRangeAttributes.push(attribute);
    
      var rangeInput1 = document.createElement('input');
      YAHOO.util.Dom.setAttribute(rangeInput1, 'type', 'text');
      YAHOO.util.Dom.setStyle(rangeInput1, 'display', 'none');
      YAHOO.util.Dom.addClass(rangeInput1, 'queryNumberCriteria');
      rangeInput1.id = attribute.getKey()+"-range1";
  
      var obj = {
        attribute: attribute,
        type: 'range'
      };
  
      YAHOO.util.Event.on(rangeInput1, 'keyup', this._setNumberCriteria, obj, this);
  
      var rangeSign = document.createElement('span');
      rangeSign.innerHTML = '-';
      YAHOO.util.Dom.setStyle(rangeSign, 'display', 'none');
      rangeSign.id = attribute.getKey()+"-rangeSign";
  
      var rangeInput2 = document.createElement('input');
      YAHOO.util.Dom.setAttribute(rangeInput2, 'type', 'text');
      YAHOO.util.Dom.setStyle(rangeInput2, 'display', 'none');
      YAHOO.util.Dom.addClass(rangeInput2, 'queryNumberCriteria');
      rangeInput2.id = attribute.getKey()+"-range2";
  
      YAHOO.util.Event.on(rangeInput2, 'keyup', this._setNumberCriteria, obj, this);
      
      li.appendChild(rangeInput1);
      li.appendChild(rangeSign);
      li.appendChild(rangeInput2);  
  
      // When the check box is toggled, be sure to clear and hide the inputs
      YAHOO.util.Event.on(check, 'click', function(e, attr){
        if(!e.target.checked)
        {
          this._toggleRange(attr, false);
        }
      }, attribute, this);
  
      return item = {
        text: MDSS.Localized.Set_Range,
        checked: false,
        onclick : {
          fn: this._toggleNumberInputs,
          obj: obj,
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
     * Renders the QueryPanel to query on SurveyPoints.
     */
    render : function()
    {
      this._queryPanel.render();
    }
  
  }
});

Mojo.Meta.newClass('MDSS.HouseholdAttribute', {

  Extends: MDSS.AbstractAttribute,
  
  Instance : {
    
    initialize : function(obj, dereference)
    {
      this.$initialize(obj, dereference);
    } 
  }
});

Mojo.Meta.newClass('MDSS.PersonAttribute', {

  Extends: MDSS.AbstractAttribute,
  
  Instance : {
    
    initialize : function(obj, dereference)
    {
      this.$initialize(obj, dereference);
    } 
  }
});
