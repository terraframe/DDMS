Mojo.Meta.newClass('MDSS.QueryBaseNew', {

  Extends: MDSS.QueryBase,

  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {
      this.$initialize(queryList);

      // list of columns that have bee_visibleAttributeHandlern added before a call to render()
      this._preconfiguredColumns = [];

      // START: query objects that dictate state of the query.

      this._countSelectable = null;
      this._ratioSelectable = null;

      this._visibleSelectables = {};
      this._whereOptions = {};
      this._visibleAggregateSelectables = {};
      this._exclusionClasses = [];
      
      this._showRatioSelectable = true;

      this._singleAndRangeAttributes = [];
  
      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      this._xmlToValueQueryClass = this._mainQueryClass;
      // END: query objects

      // Key/value where key is attribute.getKey() + "_li"
      // (which is the id of the relevant LI node,
      // and value is an array of ContextMenuItems.
      this._menus = {};

      // Map of criteria ids and associated ContextMenuItems.
      this._menuItems = {};

      this._buildQueryItems(selectableGroups);

      this._buildColumns();
    },


    /**
     * Returns the type of query.
     * // FIXME NO LONGER NEEDED
    _getQueryType: function()
    {
      return this._queryType;
    },
     */

    /**
     * Returns the controller action to invoke when exporting the query to XML.
     */
    _getExportXLSAction : function()
    {
      return 'dss.vector.solutions.query.QueryController.exportQueryToExcel.mojo';
    },
    
    /**
     * Handler to export the current query to an Excel file as a download.
     */
    exportXLS : function(form, xmlInput, config, searchIdInput, queryClassInput)
    {
      var queryXML = this._constructQuery();
      var xml = queryXML.getXML();
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");
  
      var action = this._getExportXLSAction();
      form.action = action;
  
      xmlInput.innerHTML = xml;
      config.value = this._config.getJSON();
      searchIdInput.value = savedSearchId;
      queryClassInput.value = this._xmlToValueQueryClass;
      form.submit();
    },

    _getExportCSVAction : function()
    {
      return 'dss.vector.solutions.query.QueryController.exportQueryToCSV.mojo';
    },
    
    exportCSV : function(form, xmlInput, config, searchIdInput, queryClassInput)
    {
      var queryXML = this._constructQuery();
      var xml = queryXML.getXML();
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");
  
      var action = this._getExportCSVAction();
      form.action = action;
  
      xmlInput.innerHTML = xml;
      config.value = this._config.getJSON();
      searchIdInput.value = savedSearchId;
      queryClassInput.value = this._xmlToValueQueryClass;
      form.submit();
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

      var debug = document.getElementById('debug_xml');
      if(debug){
        	debug.value = xml;
        	//xml = debug.value;
      }
      
      var page = this.getCurrentPage();

      this._dataQueryFunction(request,this._xmlToValueQueryClass, xml, this._config.getJSON(), '', true, page, this.PAGE_SIZE);
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

        // FIXME json conversion below is temporary
      this._mapQueryFunction(request,this._mainQueryClass, xml, this._config.getJSON(), layerIds, savedSearchId);
    },

    /**
     * Constructs the query with all the subcomponents.
     */
    _constructQuery : function(formapping)
    {
      var queryXML = MDSS.QueryBase.prototype._constructQuery.call(this,formapping); // super

      var mainQuery = new MDSS.QueryXML.Entity(this._mainQueryClass, this._mainQueryClass);

      var addedEntities = [this._mainQueryClass];
  
      addedEntities = addedEntities.concat(this._commonQueryClasses);

      var conditions = [];

      // Visible Attributes
      var selNames = Mojo.Util.getKeys(this._visibleSelectables);
      for(var i=0; i<selNames.length; i++)
      {
        var name = selNames[i];
        var selectable = this._visibleSelectables[name];

        queryXML.addSelectable(mainQuery.getAlias()+'_'+name, selectable);

        if(selectable.attribute)
        {
          var t =  selectable.attribute.getType();
          var n = selectable.attribute.getAttributeName().replace(/.displayLabel.currentValue/,'').replace(/.name/,'');
          var k = selectable.attribute.getKey().replace(/.displayLabel.currentValue/,'').replace(/.name/,'');
          if(t == 'sqlcharacter')
          {
            n = selectable.attribute.getAttributeName().replace(/_defaultLocale/,'');
            k = selectable.attribute.getKey().replace(/_defaultLocale/,'');
            var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', n, k));
          }else if(t == 'sqlfloat')
          {
            var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('', n, k));
          }else if(t == 'sqldouble')
          {
            var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('', n, k));
          }else if(t == 'sqlinteger')
          {
            var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('', n, k));
          }else
          {
            var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Attribute(t,n,k));
            if (addedEntities.indexOf(t) < 0) {
              var query = new MDSS.QueryXML.Entity(t, t);
              queryXML.addEntity(query);
            	addedEntities.concat(t);
						}
          }


          var items = this._menus[selectable.attribute.getKey()+'_li'];

          if(items)
          {
          if(selectable.attribute.getDtoType() && selectable.attribute.getDtoType().indexOf('AttributeEnumeration') != -1)
          {
            var enumIds = items.filter(
                function(a){return a.checked;}).map(
                    function(a){return a.uuid;}
                      ).join(',');
            if  (enumIds.length > 0)
            {
              var condition = new MDSS.QueryXML.BasicCondition(whereSelectable, MDSS.QueryXML.Operator.CONTAINS_ANY, enumIds);
              conditions.push(condition);
            }
          }else{
            var whereIds = items.filter(
                function(a){return a.checked && a.uuid;}).map(
                    function(a){
                    	  if(a.uuid === 'false')return 0;
                    	  if(a.uuid === 'true' )return 1;
                    		return a.uuid;
                    	});
            
            if(whereIds.length == 1)
            {
            	//this is for multi mo restriction
            	if(t == 'sqlinteger')
            	{
            		this._config.setProperty(k+'Criteria',  whereIds[0]);
            	}
            	else
            	{
            		var condition = new MDSS.QueryXML.BasicCondition(whereSelectable, MDSS.QueryXML.Operator.EQ, whereIds[0]);
            		conditions.push(condition);
            	}
            }
            if(whereIds.length > 1)
            {

            	//this is for multi mo restriction
            	if(t == 'sqlinteger')
            	{
            		this._config.setProperty(k+'Criteria',  whereIds[0] + ' - ' + whereIds[1]);
            	}
            	else
            	{
	            	//We OR the selected filter values together inside a CompositeCondition because nothing can be 'true' AND 'false'
	              
	            	
	            	var orConds = new MDSS.QueryXML.Or();
	              
	              
	              
	              for(var idNum=0; idNum<whereIds.length; idNum++)
	              {
	                var condition = new MDSS.QueryXML.BasicCondition(whereSelectable, MDSS.QueryXML.Operator.EQ, whereIds[idNum]);
	                orConds.addCondition(('orCond' + i + idNum), condition);
	              }
	              var composite = new MDSS.QueryXML.CompositeCondition(orConds);
	              conditions.push(composite);
            	}
            }
          }
        }
          //this is for mo terms
          var queryBrowser = this.getBrowser(selectable.attribute);
          if(queryBrowser)
          {
          	var terms = queryBrowser.getTerms();
          	
          	if(terms.length > 0)
          	{
          		
          		if(this._typeOverride && this._typeOverride[selectable.attribute.getKey()])
          		{
          			t = this._typeOverride[selectable.attribute.getKey()];
          		}
          		//create a new where clause for allpaths
	          	var termClass = 'dss.vector.solutions.ontology.AllPaths';
	          	var termAlias = n +'__'+ t.replace(/[.]/g,'_') +'__'+ selectable.attribute.getKey();
	          	var termQuery = new MDSS.QueryXML.Entity(termClass, termAlias);
	          	queryXML.addEntity(termQuery);
	          	
	          	var termParent = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Attribute(termAlias, "parentTerm"));
	          	//now restrict to attributes having the parent id of the restrictor term
	          	var or = new MDSS.QueryXML.Or();
	          	Mojo.Iter.forEach(terms, function(restrictorID){
	
		          	var restrictCondition = new MDSS.QueryXML.BasicCondition(termParent, MDSS.QueryXML.Operator.EQ, restrictorID);
		          	or.addCondition(restrictorID, restrictCondition);
		            
          	  });
	            //add the restrictions to the query
          	  var composite = new MDSS.QueryXML.CompositeCondition(or);
          	  termQuery.setCondition(composite);
          	}
          	
          }
       }
      }

      var aggNames = Mojo.Util.getKeys(this._visibleAggregateSelectables);
      for(var i=0; i<aggNames.length; i++)
      {
        var name = aggNames[i];
        var selectable = this._visibleAggregateSelectables[name];
        var t = selectable.getComponent().getSelectable().getComponent().getEntityAlias();
        if (addedEntities.indexOf(t) < 0  && t.indexOf('dss.vector.solutions') == 0) {
          var query = new MDSS.QueryXML.Entity(t, t);
          queryXML.addEntity(query);
        	addedEntities.concat(t);
				}

        queryXML.addSelectable(mainQuery.getAlias()+'_'+name, selectable);
      }

      // start and end dates

      if(this._startDate != null || this._endDate != null)
      {
        var dateAnd = new MDSS.QueryXML.And();
        if(this._startDate != null)
        {
        	dateAnd.addCondition(('StartDateRange'), this._startDate );
        }

        if(this._endDate != null)
        {
        	dateAnd.addCondition(('EndDateRange'), this._endDate );
        }
        var composite = new MDSS.QueryXML.CompositeCondition(dateAnd);
        conditions.push(composite);
      }

      //date groups

      var keys = Mojo.Util.getKeys(this._dateGroupSelectables);
      for(var i=0; i < keys.length; i++)
      {
        var selectable = this._dateGroupSelectables[keys[i]];
        if(selectable != null)
        {
          queryXML.addSelectable(keys[i], selectable);
        }
      }

      if(this._countSelectable != null)
      {
        queryXML.addSelectable(mainQuery.getAlias()+'_globalCount', this._countSelectable);
      }
      if(this._ratioSelectable != null)
      {
        queryXML.addSelectable(MDSS.QueryXML.RATIO_FUNCTION, this._ratioSelectable);
      }

      var where = null;
      if(conditions.length > 0 )
      {
        var where = new MDSS.QueryXML.And();
        for(var i=0; i<conditions.length; i++)
        {
          where.addCondition(('cond' + i), conditions[i]);
        }

        var composite = new MDSS.QueryXML.CompositeCondition(where);
        mainQuery.setCondition(composite);
      }
      
      queryXML.addEntity(mainQuery);
      addedEntities.map(function(klass){
      	if(queryXML.getEntity(klass) == null)
      	{
      		var query = new MDSS.QueryXML.Entity(klass, klass);
      		queryXML.addEntity(query);
      	}
      },this);
      
      return queryXML;
    },



    /**
     * Helper method to add Entomology attributes to selectables and as a column.
     */
    _addVisibleAttribute : function(attribute)
    {
      var attributeName = attribute.getAttributeName();

      if(attribute.mainQueryClass)
      {
        //this._mainQueryClass = attribute.mainQueryClass;
      }

      if(attribute.getType() == 'sqlcharacter'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }else
      if(attribute.getType() == 'sqlinteger'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
    	}else
      if(attribute.getType() == 'sqlfloat'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }else
      if(attribute.getType() == 'sqldouble'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }
      else
      {
        var selectable = attribute.getSelectable(true);
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column(attribute.getColumnObject());
         column.attribute = attribute;
      }

      column = this._queryPanel.insertColumn(column);

      this._visibleSelectables[attribute.getKey()] = selectable;
    },



    /**
     * Removes an attribute as a selectable and column.
     */
    _removeVisibleAttribute : function(attribute, removeColumn, removeSelectable)
    {
      var attributeName = attribute.getAttributeName();
      var key = attribute.getKey();

      if(removeSelectable)
      {
        delete this._visibleSelectables[attribute.getKey()];
      }

      // remove all possible query references
      delete this._visibleAggregateSelectables[attribute.getKey()];

      if(removeColumn)
      {
        var column = this._queryPanel.getColumn(key);
        this._queryPanel.removeColumn(column);
      }
    },



    /**
     * Handler to toggle visible attributes as selectables
     */
    _visibleAttributeHandler : function(e, attribute)
    {
      var check = e.target;
      var liTarget = YAHOO.util.Dom.getAncestorByTagName(check, "LI");
      if(check.checked)
      {
        this._uncheckAllNotInGroup(check);
        this._addVisibleAttribute(attribute);
        var select = check.nextSibling;
        select.selectedIndex = 0;
        select.disabled = false;
      }
      else
      {
        this._removeVisibleAttribute(attribute, true, true, true);
        var select = check.nextSibling;
        select.selectedIndex = 0;
        select.disabled = true;
        var menus = this._menus[liTarget.id];
        if(menus)
        {
          Mojo.Iter.forEach(menus, function(ck){
            //for display
          	if(ck.checked) ck.checked = false;
          }, this); 
        }
        
        this.clearBrowserTerms(attribute);
      }
    },

    _uncheckAllNotInGroup : function(target)
    {
      //find all the exclusion classes the target is not a member of
      var uncheckClasses = this._exclusionClasses.filter(function(klass){return !YAHOO.util.Dom.hasClass(target, klass);});

      var queryTypeSwitched = uncheckClasses.filter(function(uncheckClass){
        return this._uncheckAllByClass(uncheckClass).length > 0;
      },this);

      if(queryTypeSwitched.length > 0)
      {
        this._uncheckAllByClass('uncheckMeOnQueryTypeSwitch');
      }
    },

    _uncheckAllByClass : function(klass,root)
    {
      return YAHOO.util.Dom.getElementsByClassName(klass,'input',root).filter(function(check){
        if(check.checked)
        {
          //do not fire click event on select all checkbox
          if(YAHOO.util.Dom.hasClass(check, 'selectAllCheck')){
            check.checked = false;
          }else{
            check.click();
            return true;
          }
        }
        return false;
      });
    },

    _toggleCount : function(e, attribute)
    {
      var check = e.target;

      attribute.setType(this._mainQueryClass);

      if(check.checked)
      {
        var selectable = attribute.getSelectable();

        var count = new MDSS.QueryXML.COUNT(selectable, attribute.getKey());
        var aggSelectable = new MDSS.QueryXML.Selectable(count);
        this._countSelectable = aggSelectable;

        this._queryPanel.insertColumn(attribute.getColumnObject());
      }
      else
      {
        var column = this._queryPanel.getColumn(attribute.getKey());
        this._queryPanel.removeColumn(column);

        this._countSelectable = null;
      }
    },

    _toggleRatio : function(e, attribute)
    {
      var check = e.target;

      if(check.checked)
      {
        //if(attribute.getType() == 'sqlcharacter'){
          var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel(),attribute._isAggregate));
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
           column.attribute = attribute;
        //}

        this._ratioSelectable = selectable;

        this._queryPanel.insertColumn(column);
      }
      else
      {
        var column = this._queryPanel.getColumn(attribute.getKey());
        this._queryPanel.removeColumn(column);

        this._ratioSelectable = null;
      }
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
      
      var selectable = attribute.getSelectable();
      
      
      if(attribute.getType() == 'sqlinteger'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),false));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }
      
      if(attribute.getType() == 'sqlfloat'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),false));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }
      
      if(attribute.getType() == 'sqldouble'){
        var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),false));
        selectable.attribute = attribute;
        var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
         column.attribute = attribute;
      }
      

      this._queryPanel.updateColumnLabel(key, func);

      // special cases
      if(func === '')
      {
        // Use regular selectable (this is just here for clarity).
        this._removeVisibleAttribute(attribute, false, true, false);
        this._visibleSelectables[attribute.getKey()] = selectable;

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

      this._removeVisibleAttribute(attribute, false, true, false);

      var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
      this._visibleAggregateSelectables[attribute.getKey()] = aggSelectable;
    },


    _whereValueHandler: function(eventType, event, obj)
    {

       var attribute = obj.attribute;
       var value = obj.value;
       var display = obj.display;

       var item = this._menuItems[attribute.getKey()+'-'+value];
       item.checked = !item.checked;

       if(item.checked)
       {
         this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
       }
       else
       {
         this._queryPanel.removeWhereCriteria(attribute.getKey(), value);
       }
    },


    _loadQueryState : function(view)
    {
      var thisRef = this;

      
      var dateRestrictions = thisRef._config.getDateAttribute();
      if(dateRestrictions)
      {
        if(dateRestrictions.start)
        {
          var start = thisRef._queryPanel.getStartDate();
          var formatted = MDSS.Calendar.getLocalizedString(dateRestrictions.start);
          start.value = formatted;
        }
        if(dateRestrictions.end)
        {
          var end = thisRef._queryPanel.getEndDate();
          var formatted = MDSS.Calendar.getLocalizedString(dateRestrictions.end);
          end.value = formatted;
        }
      }
      
      
      var xml = view.getQueryXml();
      var parser = new MDSS.Query.Parser(xml);

      parser.parseSelectables({
        attribute : function(entityAlias, attributeName, userAlias){
            var checked = thisRef._checkBox(userAlias);
           
           	var key = userAlias + 'Criteria';
           	var crit = thisRef._config.getProperty(key);
	          if(checked && crit){
	          	thisRef._queryPanel.addWhereCriteria(userAlias, crit, crit);
	          	if(crit.indexOf(' - ')>0)
	          	{
	          		crit = crit.split(' - ');	          		
      			  	thisRef._toggleRange(userAlias, true, crit[0],crit[1]);
	          	}
      			  else
      			  {
      			  	thisRef._toggleSingle(userAlias, true, crit);
      			  }
          	}
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
        count: function(entityAlias, attributeName, userAlias){

          thisRef._checkBox(userAlias);
        },
        sqlcharacter : function(entityAlias, attributeName, userAlias){

          var key = userAlias + '_li';
        	var browser = thisRef._browsers[key];
          if(browser){
          	thisRef._checkBox(userAlias);
          	var termList = thisRef._config._config.terms[userAlias];
            for(var termId in termList){
                browser.addTerm(termId);
                attribute = browser.getAttribute();
                display = browser.getDisplay(termId);
                thisRef._queryPanel.addWhereCriteria(attribute.getKey(), termId, display);
            }
        	}
          else
          {
          	thisRef._checkBox(userAlias);
          }
          
        },
        sqlinteger: function(entityAlias, attributeName, userAlias){
          
          thisRef._checkBox(userAlias);
        },
        sqldouble: function(entityAlias, attributeName, userAlias){
          
          thisRef._checkBox(userAlias);
        },
        sqlfloat: function(entityAlias, attributeName, userAlias){
          
          thisRef._checkBox(userAlias);
        },
        sqldate : function(entityAlias, attributeName, userAlias){

          thisRef._checkBox(userAlias);
        }
      });

      parser.parseCriteria({
        attribute : function(entityAlias, attributeName, userAlias, operator, value){

          if(!!thisRef._dateAttribute && userAlias === thisRef._dateAttribute.getUserAlias())
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
            var item = thisRef._menuItems[userAlias+'-'+value];
            if(!item)
            {	
            	item = (value === '1') ? thisRef._menuItems[userAlias+'-true'] : item;
              item = (value === '0') ? thisRef._menuItems[userAlias+'-false'] : item;
            }
            if(item)
            {
              item.checked = true;
              var attribute = item.onclick.obj.attribute;
              var display = item.onclick.obj.display;
              thisRef._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
            }
          }


        }
      });
    },

    /**
     * Resets all defaults, including clearing all criteria and unchecking
     * all context menu items.
     */
    _resetToDefault : function()
    {
      MDSS.QueryBase.prototype._resetToDefault.call(this); // super

      // uncheck all menu items
      var keys = Mojo.Util.getKeys(this._menuItems);
      for(var i=0; i<keys.length; i++)
      {
        var item = this._menuItems[keys[i]];
        item.checked = false;
      }
      
      this._queryPanel.clearPagination();


    },


    /*
     * Visible Attributes
     */
    _getVizDiv : function(that,visibleAttributes,divName,mainQueryClass,checkClass)
    {
      var visibleDiv = document.createElement('div');
      // YAHOO.util.Dom.addClass(visibleDiv, 'scrollable');

      var labelDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
      labelDiv.innerHTML = MDSS.localize(divName);

      var toggleDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(toggleDiv, 'clickable');
      YAHOO.util.Dom.addClass(toggleDiv, 'queryItemLabel');
      toggleDiv.innerHTML = MDSS.Localized.Toggle_Show;

      visibleDiv.appendChild(labelDiv);
      visibleDiv.appendChild(toggleDiv);

      var visibleUl = document.createElement('ul');
      visibleUl.id = divName + "Li";
      YAHOO.util.Dom.addClass(visibleUl, 'gridList');
      YAHOO.util.Dom.setStyle(visibleUl, 'clear', 'both');
      YAHOO.util.Dom.setStyle(visibleUl, 'display', 'none');

      that._toggleVisibility(toggleDiv, visibleUl);

      that._attachSelectAll(visibleUl,checkClass);

      for(var i=0; i<visibleAttributes.length; i++)
      {
        var visibleObj = visibleAttributes[i];
        
        if(visibleObj.isAggregate){
        	visibleObj.displayLabel = MDSS.localize(visibleObj.key) +  MDSS.localize("selectable_is_aggreated");
        }
        
        var attribute = new MDSS.BasicAttribute(visibleObj);
        attribute.mainQueryClass = mainQueryClass;

        var li = document.createElement('li');

        var check = document.createElement('input');
        YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
        //this is the marker for the mutual exculsion group
        YAHOO.util.Dom.addClass(check,checkClass);

        YAHOO.util.Event.on(check, 'click', that._visibleAttributeHandler, attribute, that);
        check.id = attribute.getKey();
        li.appendChild(check);
        this._defaults.push({element:check, checked:false});
        
        if(visibleObj.dtoType && visibleObj.dtoType.indexOf('AttributeCharacterDTO') != -1  && ! visibleObj.isGeoEntity)
        {
        	li.id = attribute.getKey()+'_li';

          // Add single match and range
        	var items = [];
          var single = this._createSingleItem(check, li, attribute,'queryTextCriteria');
          
          this._menuItems[attribute.getKey()+'-single'] = single;        
              
          items.push(single);
          
          this._menus[li.id] = items;
          
        }
        
        if(visibleObj.dtoType && (visibleObj.dtoType.indexOf('AttributeIntegerDTO') != -1  || visibleObj.dtoType.indexOf('AttributeFloatDTO') != -1 ))
        {
        	li.id = attribute.getKey()+'_li';
        	 	
          var select = document.createElement('select');

          var options = [''];
          options = options.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));

          for(var j=0; j<options.length; j++)
          {
            var option = options[j];
            var optionEl = document.createElement('option');
            optionEl.id = attribute.getKey()+'-'+option;
            optionEl.innerHTML = option;
            YAHOO.util.Dom.setAttribute(optionEl, 'value', option);

            YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, attribute, this);

            select.appendChild(optionEl);
          }
          select.disabled = true; // default (must be checked to enabled)
          this._defaults.push({element:select, index:0});
          li.appendChild(select);

            // Add single match and range
        	var items = [];
          var single = this._createSingleItem(check, li, attribute,'queryNumberCriteria');
          var range = this._createRangeItem(check, li, attribute);
          
          this._menuItems[attribute.getKey()+'-single'] = single;        
          this._menuItems[attribute.getKey()+'-range'] = range;
              
          items.push(single);
          items.push(range);
        	this._menus[li.id] = items;
       
        }

        var span = document.createElement('span');
        span.innerHTML = attribute.getDisplayLabel();
        li.appendChild(span);

        if (visibleObj.dropDownMap)
        {

          li.id = attribute.getKey()+'_li';

          var options = Mojo.Util.getKeys(visibleObj.dropDownMap);
          var displayLabels = Mojo.Util.getValues(visibleObj.dropDownMap);
          var items = [];
          for(var j=0; j<options.length; j++)
          {
            var item = {
                checked : false,
                text : displayLabels[j],
                uuid:options[j],
                myIndex:j,
                onclick: {
                  fn: that._whereValueHandler,
                  obj: {attribute: attribute, value: options[j], display: displayLabels[j]},
                  scope: this
              }
            }
            items.push(item);
            this._menuItems[attribute.getKey()+'-'+options[j]] = item;
          }
          this._menus[li.id] = items;
        }
        else //Mo terms
        if(attribute.getTerm())
        {
        	li.id = attribute.getKey()+'_li';
        	var n =  attribute.getAttributeName().replace(/.name/,'');
        	if(this._moUsesView !== false)
        	{
        			this._attachBrowser(li.id, this._genericBrowserHandler, attribute, visibleObj.type + "View", n, true);
        	}
        	else
        	{
        			this._attachBrowser(li.id, this._genericBrowserHandler, attribute, visibleObj.type , n, true);
        	}
        }

        visibleUl.appendChild(li);
      }

      visibleDiv.appendChild(visibleUl);

      return visibleDiv;
    },

    _genericBrowserHandler : function(browser, selected)
    {
      // clear all previous criteria on this attribute
      var attribute = browser.getAttribute();
      
      var key = attribute.getKey();
      this._queryPanel.clearWhereCriteria(key);      

      Mojo.Iter.forEach(selected, function(sel){
        //for display
        var display = MDSS.OntologyBrowser.formatLabelFromView(sel);
        this._queryPanel.addWhereCriteria(attribute.getKey(), sel.getTermId(), display);
      }, this); 
    },
    
    /**
     * Builds the query items for the left column.
     */
    _buildQueryItems : function(selectableGroups)
    {
     // this.addGeoAttributes(this._geoEntityAttribs);

      this._queryPanel.addQueryItem({
        html: this._getCountDiv(this,"Dates_And_Count",this._groupByClass,!!this._showRatioSelectable),
        id: 'globalCount'
      });


      var setupDiv =  function(group,idx){
        this._queryPanel.addQueryItem({
          html: this._getVizDiv(this, group.values, group.title, group.klass, group.group),
          id: group.group + '_checkbox_group',
          menuBuilder : Mojo.Util.bind(this, this._menuBuilder)
        });
        this._exclusionClasses.push(group.group);
      };

      Mojo.Iter.forEach(selectableGroups, setupDiv, this);
    },


    /**
     * Attaches an option to select all items in the given list.
     */
    _attachSelectAll : function(ul,klass)
    {
      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Dom.addClass(check,'selectAllCheck');
      YAHOO.util.Dom.addClass(check,klass);
      YAHOO.util.Event.on(check, 'click', this._toggleSelectAll, ul, this);
      this._defaults.push({element:check, checked:false, bypass:true});      

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
    },

    /**
     * Renders the QueryPanel to query on Entomology.
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

    },
    /**
     * Creates the JSON necessary to let a user specify an single
     * match on an attribute.
     */
    _createSingleItem : function(check, li, attribute, klass)
    {
      this._singleAndRangeAttributes.push(attribute);
    
      var singleInput = document.createElement('input');
      YAHOO.util.Dom.setAttribute(singleInput, 'type', 'text');
      YAHOO.util.Dom.setStyle(singleInput, 'display', 'none');
      YAHOO.util.Dom.addClass(singleInput, klass);
      singleInput.id = attribute.getKey()+"-single";
      
      var obj = {
        attribute: attribute,
        type: 'single'
      };
      
      YAHOO.util.Event.on(singleInput, 'keyup', this._setNumberCriteria, obj, this);
  
      li.appendChild(singleInput);
      
      if(klass == 'queryTextCriteria')
      {
      	this._buildTextAttributeAutoSuggest(singleInput,attribute,obj,this);
      }
      
  
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
    
    _buildTextAttributeAutoSuggest: function(searchEl,attribute,obj,queryObject)
    {

    	  var listFunction = function(valueObject) {

    	    return  valueObject.getValue('attributeCount') +') ' + valueObject.getValue('attribute') ;
    	  };

    	  var displayFunction = function(valueObject) {

    	    return valueObject.getValue('attribute');
    	  };
    	  
    	  var idFunction = function(valueObject) {

    	    return null;
    	  };

    	  var searchFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getTextAttributeSugestions;
    	  
    	  var selectEventHandler = function(selected) {
    	  	queryObject._setNumberCriteria(null,obj);	
    	  };
    	   
    	  var search = new MDSS.GenericSearch(searchEl, null, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler, {minLength:0});
    	  
    	  search.addParameter([attribute.getType(),attribute.getAttributeName()]);
    	  
    },
    
    _buildDateAttributesSelect : function(div)
    {   	
    	attributes = this._dateAttribs.map( 
         	function(d){
   	      	var tmp = new d.klass();
   	      	var attrib = tmp.attributeMap[d.accessor];
   	      	return {
   	          keyName :  d.klass.CLASS+'.'+d.accessor,
   	          klass:d.klass.CLASS,
   	          attribute:d.accessor,
   	          display : attrib.attributeMdDTO.displayLabel
   	      	};
         });
    	
    	var sel;
    	this._dateAttributes = [];
     
    	sel = document.createElement('select');
      sel.id = MDSS.QueryBase.DATE_ATTRIBUTES;
      for(var i=0; i<attributes.length; i++)
      {
      	var attribute = attributes[i];
        var optionEl = document.createElement('option');
        optionEl.innerHTML = attribute.display;
        optionEl.value = i;
        this._dateAttributes[i] = attribute;
        sel.appendChild(optionEl);
      }
      div.appendChild(sel);

      var label = document.createElement('span');
      label.innerHTML = ' ';
      div.appendChild(label);
      
      this._dateAttributeSelect = sel;
      
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
    _toggleSingle : function(attribute, toggleOverride, value)
    {
    	var key = (Mojo.Util.isString(attribute) ? attribute: attribute.getKey())+'-single';
    	
    	var item = this._menuItems[key];
      // The single criteria is optional, so return if null
      if(item == null)
      {
        return;
      }
      
      item.checked = Mojo.Util.isBoolean(toggleOverride) ? toggleOverride : !item.checked; 
    
      var single = document.getElementById(key);
      if(item.checked)
      {
        YAHOO.util.Dom.setStyle(single, 'display', 'inline');
        if(value)
        {
        	single.value = value;
        	
        }
      }
      else
      {
        single.value = '';
        YAHOO.util.Dom.setStyle(single, 'display', 'none');
      }
      
      return item.checked;
    },
    
    _toggleRange : function(attribute, toggleOverride, value1, value2)
    {
    	var key = Mojo.Util.isString(attribute) ? attribute: attribute.getKey();
    	
      var item = this._menuItems[key+'-range'];
      // The range criteria is optional, so return if null
      if(item == null)
      {
        return;
      }
      
      item.checked = Mojo.Util.isBoolean(toggleOverride) ? toggleOverride : !item.checked;
      
      var range1 = document.getElementById(key+"-range1");
      var rangeSign = document.getElementById(key+"-rangeSign");
      var range2 = document.getElementById(key+"-range2");
      
      if(item.checked)
      {
        YAHOO.util.Dom.setStyle(range1, 'display', 'inline');
        YAHOO.util.Dom.setStyle(rangeSign, 'display', 'inline');
        YAHOO.util.Dom.setStyle(range2, 'display', 'inline');
        if(value1)
        {
        	range1.value = value1;
        }
        if(value2)
        {
        	range2.value = value2;
        }
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
      
      if(!checked)
      {
        this._config.setProperty(attribute.getKey()+'Criteria', null);
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
        value = range1.value+' - '+range2.value; // Will be split up later
      }
      
      // DOB uses the json config because WHERE criteria is not easily passed
      // between a date and sql integer.

      if(value === '' || value === ' - ')
      {
        value = null;
      }
    
      this._config.setProperty(attribute.getKey()+'Criteria', value);
      this._queryPanel.clearWhereCriteria(attribute.getKey());
      
      if(value != null)
      {
        this._queryPanel.addWhereCriteria(attribute.getKey(), value, value);
      }
     
    }
  },
  Static : {
      mapAttribs : function(attribName,index){
       var attrib = this.obj.attributeMap[attribName];
       var row = {};
       if(attrib){
         row.attributeName = attrib.attributeName;
         if(attrib.dtoType.indexOf('AttributeReferenceDTO') != -1)
         {
           if(attrib.getAttributeMdDTO().getReferencedMdBusiness().indexOf('Term') != -1)
           {
             row.isTerm = true;
           }
           
           if(attrib.getAttributeMdDTO().getReferencedMdBusiness().indexOf('dss.vector.solutions.geo.generated') != -1)
           {
             row.attributeName = attribName + '_displayLabel';
             row.type = 'sqlcharacter';
             row.displayLabel = attrib.attributeMdDTO.displayLabel;
             row.key = attribName;
             row.dtoType = "AttributeCharacterDTO";
             row.isGeoEntity = true;
             return row;
           }

         }
         if(attrib.dtoType.indexOf('AttributeEnumerationDTO') != -1)
         {
        	 /* row.attributeName = attribName + '_displayLabel';
            row.type = 'sqlcharacter';
            row.displayLabel = attrib.attributeMdDTO.displayLabel;
            row.key = attribName;
            row.dtoType = "AttributeCharacterDTO";
            return row;
        	 */
           //row.attributeName += '.displayLabel.currentValue';
        	 row.isEnum = true;
         }
        
         row.key = attrib.attributeName + this.suffix;
         //sometimes we need to override the type, like if we want labels from the view but data from the concrete
         if(this.type)
         {
        	 row.type = this.type;
         }else
         {
        	 row.type = this.obj.getType();
         }
         row.dtoType = attrib.dtoType;
         row.displayLabel = attrib.attributeMdDTO.displayLabel;
         var uppFirst = attrib.attributeName.slice(0,1).toUpperCase() + attrib.attributeName.slice(1);
         if(this.dropDownMaps[uppFirst]){
           row.dropDownMap = this.dropDownMaps[uppFirst];
         }
       }else{
         row.attributeName = attribName;
         row.type = 'sqlinteger';
         row.displayLabel = MDSS.localize(attribName);
         row.key = attribName;
         row.dtoType = "AttributeIntegerDTO";
         
         if(this.obj.row)
         {
        	 row.type = this.obj.row.type;
        	 row.dtoType = this.obj.row.dtoType;
         }

       }
       return row;
     },
     mapInts : function(attribName,index){
       var attrib = this.obj.attributeMap[attribName];
       var row = {};
         row.attributeName = attrib.attributeName;
         row.type = 'sqlinteger';
         row.displayLabel = attrib.attributeMdDTO.displayLabel;
         row.key = attribName;
         row.dtoType = "AttributeIntegerDTO";
       return row;
     },
     mapMo : function(term,index){
     	 var row = {};
         //row.attributeName = this.relAttribute;
         //row.key = 'term' + term.MOID.replace(':','') +'_'+ term.id;
         //row.type = this.relType;
         row.dtoType = "AttributeIntegerDTO";
         row.displayLabel = term.displayLabel;
         
         row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
         row.type = 'sqlinteger';
         row.attributeName = 'term' + term.MOID.replace(':','');
         
        return row;
      },
     
     mapBooleanMo : function(term,index){
      	 var row = {};
          row.dtoType = "AttributeBooleanDTO";
          row.displayLabel = term.displayLabel;
          
          row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
          row.type = 'sqlinteger';
          row.attributeName = 'term' + term.MOID.replace(':','');
          row.dropDownMap = {'0':'0','1':'1'};
         return row;
       }

   }
});
