/**
 * Base class to support functionality for all query use cases.
 */
Mojo.Meta.newClass('MDSS.QueryBaseNew', {

  IsAbstract : true,

  Instance : {

    initialize : function()
    {
      this._queryPanel = new MDSS.QueryPanel(this, 'queryPanel', 'mapPanel', {
        executeQuery: this.executeQuery,
        mapQuery: this.mapQuery,
        saveQuery: this.saveQuery,
        saveQueryAs: this.saveQueryAs,
        loadQuery: this.loadQuery,
        addLayer: this.addLayer,
        editLayer: this.editLayer,
        deleteLayer: this.deleteLayer,
        editVariableStyles: this.editVariableStyles,
        exportXLS : this.exportXLS,
        exportCSV : this.exportCSV,
        exportReport : this.exportReport,
        paginationHandler : this.paginationHandler,
        postRender : this.postRender,
        thematicLayerSelected :this.thematicLayerSelected
      });
  
      this._allPathsQuery = null;
  
      this._geoEntityTypes = {};
      this._geoEntitySelectables = {};
      this._geoIdConditions = {};
  
      this._currentPage = 1;
  
      this._startDate = null;
      this._endDate = null;
      this._dateGroupSelectables = [];
  
      this._config = new MDSS.Query.Config();
  
      this.PAGE_SIZE = 15;
  
      this.ALL_PATHS = "dss.vector.solutions.geo.AllPaths";
  
      var hideBound = Mojo.Util.bind(this, this._hideHandler);
  
      this._selectSearch = new MDSS.MultipleSelectSearch();
      this._selectSearch.setHideHandler(hideBound);
      this._selectSearch.setFilter('');
  
      // list of all elements and default settings
      this._defaults = [];
      
      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      
    },
  
    getCurrentPage : function()
    {
      return this._currentPage;
    },
  
    setCurrentPage : function(page)
    {
      this._currentPage = page;
    },
  
    exportCSV : function(form, xmlInput, config, searchIdInput)
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
      form.submit();
    },
  
    /**
     * Returns the type of query.
     */
    _getQueryType: function()
    {
      return this._queryType;
    },
    
    /*
    * Returns the controller action to invoke when exporting the query to XML.
    */
	  _getExportXLSAction : function()
	  {
	    return 'dss.vector.solutions.query.QueryController.exportResistanceQueryToExcel.mojo';
	  },
	
	  _getExportCSVAction : function()
	  {
	    return 'dss.vector.solutions.query.QueryController.exportResistanceQueryToCSV.mojo';
	  },
	
	  _getExportReportAction : function()
	  {
	    return 'dss.vector.solutions.report.ReportController.generateReport.mojo';
	  },
	
	   _getReportQueryType : function()
	   {
	   return this._queryReportType;
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


    $('debug_xml').value = xml;
     xml = $('debug_xml').value;
     var page = this.getCurrentPage();

       // FIXME json conversion below is temporary
     this._dataQueryFunction(request,this._mainQueryClass, xml, this._config.getJSON(), '', true, page, this.PAGE_SIZE);
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
     }
     if(attribute.getType() == 'sqlinteger'){
       var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
       selectable.attribute = attribute;
       var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
        column.attribute = attribute;
     }
     else
     {
       var selectable = attribute.getSelectable();
       selectable.attribute = attribute;
       var column = new YAHOO.widget.Column(attribute.getColumnObject());
        column.attribute = attribute;
     }

     column = this._queryPanel.insertColumn(column);

     this._visibleSelectables[attribute.getKey()] = selectable;

     // ADD THEMATIC VARIABLE
    // if(attribute.getDtoType != 'undefined' && attribute.getDtoType().contains('AttributeIntegerDTO'))
    // {
      // this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
     //}
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
    * Handler to toggle visible attributes as selectables
    * to the Entomology query.
    */
   _visibleAttributeHandler : function(e, attribute)
   {
     var check = e.target;
     var liTarget = YAHOO.util.Dom.getAncestorByTagName(check, "LI");
     if(check.checked)
     {
       this._uncheckAllNotInGroup(check);
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

         thisRef._checkBox(attributeName);
       },
       sqlinteger: function(entityAlias, attributeName, userAlias){
         
         thisRef._checkBox(userAlias);
       },
       sqldate : function(entityAlias, attributeName, userAlias){

         thisRef._checkBox(userAlias);
       }
     });

     var entities = [];

     parser.parseCriteria({
       attribute : function(entityAlias, attributeName, userAlias, operator, value){

         // restricting geo entities
         if(entityAlias === thisRef.ALL_PATHS)
         {
           entities.push(value);
         }else
         if(userAlias === thisRef._dateAttribute.getUserAlias())
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

     this._reconstructSearch(entities, view);
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
   
   

   
   
    _dateGroupHandler : function(e,group)
    {
      var check = e.target;
      //var group = check.value;
      if(check.checked)
      {
        var attribute = new MDSS.QueryXML.Sqlcharacter('', group , group.toLowerCase(),MDSS.QueryXML.DateGroupOpts[group]);
        var selectable = new MDSS.QueryXML.Selectable(attribute);
        this._dateGroupSelectables[group] = selectable;
        this._queryPanel.insertColumn({
          key: group.toLowerCase(),
          label: MDSS.QueryXML.DateGroupOpts[group]
        });
      }
      else
      {
        var column = this._queryPanel.getColumn(group.toLowerCase());
        this._queryPanel.removeColumn(column);
        this._dateGroupSelectables[group] = null;
      }
    },
  
  
    _dateSnapHandler : function(e)
    {
      var option = e.target;
      var group = option.value;
  
  
      if(group !== '')
      {
        var dateEl = this._queryPanel.getStartDate();
        this._snapDate(dateEl.value, dateEl,group,true);
  
        dateEl = this._queryPanel.getEndDate();
        this._snapDate(dateEl.value, dateEl,group,false);
      }
  
      option.parentNode.selectedIndex = 0;
  
    },
  
    _snapDate : function(date,targetEl,dateGroupPeriod,snapToFirstDay){
  
      date = MDSS.util.stripWhitespace(date);
  
      if(date.length == 0){return;}
  
      date = MDSS.Calendar.parseDate(date);
  
      var request = new MDSS.Request({
        el: targetEl,
        onSend: function(){},
        onComplete: function(){},
        onSuccess : function(result){
        this.el.value = MDSS.Calendar.getLocalizedString(result);
      }
      });
  
      switch(dateGroupPeriod)
      {
      case 'DATEGROUP_EPIWEEK':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToEpiWeek(request,date,snapToFirstDay);
          break;
      case 'DATEGROUP_MONTH':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToMonth(request,date,snapToFirstDay);
          break;
      case 'DATEGROUP_QUARTER':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToQuarter(request,date,snapToFirstDay);
        break;
      case 'DATEGROUP_YEAR':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToYear(request,date,snapToFirstDay);
          break;
      case 'DATEGROUP_SEASON':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToSeason(request,date,snapToFirstDay);
          break;
      default:
        targetEl.set('value',MDSS.Calendar.getLocalizedString(result));
      }
  
  
    },
  
    exportReport : function(form, xmlInput, config, searchIdInput, queryTypeInput)
    {
      var queryXML = this._constructQuery();
      var xml = queryXML.getXML();
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");
  
      var action = this._getExportReportAction();
  
      form.action = action;
  
      xmlInput.innerHTML = xml;
      config.value = this._config.getJSON();
      searchIdInput.value = savedSearchId;
      queryTypeInput.value = this._getReportQueryType();
      form.submit();
    },
  
    /**
     * Handler to export the current query to an Excel file as a download.
     */
    exportXLS : function(form, xmlInput, config, searchIdInput)
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
           var dto = result.getAttributeDTO(attr);
           var value = dto.getValue();
           if(dto.dtoType.contains('AttributeDateDTO')){
             value = MDSS.Calendar.getLocalizedString(value);
           }
           if(dto instanceof AttributeDecDTO){
             if(value != null){
               value = value.toFixed(2);
             }
           }
           if(dto.dtoType.contains('AttributeBooleanDTO')){
  
             var displayValue = null;
             if(value === true)
             {
               displayValue = query.getAttributeDTO(attr).getAttributeMdDTO().getPositiveDisplayLabel();
             }
             else if(value === false) // must be boolean false to avoid matching against an empty string
             {
               displayValue = query.getAttributeDTO(attr).getAttributeMdDTO().getNegativeDisplayLabel();
             }
  
             // make sure a meaningful label was given (otherwise, the default true/false/"" will be used)
             if(Mojo.Util.isString(displayValue) && displayValue !== '')
             {
               value = displayValue;
             }
           }
  
           entry[attr] = value;
         }
  
         jsonData.push(entry);
       }
  
       this._queryPanel.clearAllRecords();
       this._queryPanel.setRowData(jsonData);
       this._queryPanel.setPagination(query.getCount(), this.getCurrentPage(), this.PAGE_SIZE);
    },
  
    _loadDefaultSearch : function()
    {
      var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
      this._populateSearch(null, view);
  
      var request = new MDSS.Request({
        thisRef : this,
        onSuccess: function(savedSearchView){
  
          // the default will be the current saved search but
          // it will not be visible in the query list
          this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);
        }
      });
  
      Mojo.$.dss.vector.solutions.query.SavedSearch.loadDefaultSearch(request, view);
    },
  
    /**
     * Called after the QueryPanel has performed all of its rendering operations.
     */
    postRender : function()
    {
       var options = this._queryPanel._dateGroupBy.options;
       for(var i=0; i<options.length; i++)
       {
         YAHOO.util.Event.on(options[i], 'click', this._dateSnapHandler, '',this);
       }
  
      var startCheck = this._queryPanel.getStartDateCheck();
      var endCheck = this._queryPanel.getEndDateCheck();
  
      YAHOO.util.Event.on(startCheck, 'click', this.toggleDates, 'START_DATE_RANGE', this);
      YAHOO.util.Event.on(endCheck, 'click', this.toggleDates, 'END_DATE_RANGE', this);
  
      // set the default for the date searching
      var startDate = this._queryPanel.getStartDate();
      var endDate = this._queryPanel.getEndDate();
  
      this._defaults.push({element: startCheck, checked:false});
      this._defaults.push({element: endCheck, checked:false});
      this._defaults.push({element: startDate, value: ''});
      this._defaults.push({element: endDate, value: ''});
  
      this._customPostRender();
  
      this._loadDefaultSearch();
    },
  
    _customPostRender : function()
    {
      // A subclass should override this method to perform
      // any post render cleanup that may be required before
      // loading the default search.
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
    toggleDates : function(e,range)
    {
      var check = e.target;
      if(check.checked)
      {
        var attribute = new MDSS.QueryXML.Sqldate('', range, range.toLowerCase(), MDSS.localize(range));
        var selectable = new MDSS.QueryXML.Selectable(attribute);
        this._dateGroupSelectables[range] = selectable;
        this._queryPanel.insertColumn({
          key: range.toLowerCase(),
          label: MDSS.localize(range)
        });
      }
      else
      {
        var column = this._queryPanel.getColumn(range.toLowerCase());
        this._queryPanel.removeColumn(column);
        this._dateGroupSelectables[range] = null;
      }
  
    },

  
    _getCountDiv : function(that,divName,klass,useRatio){
  
  
      var visibleDiv = document.createElement('div');
  
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
      visibleUl.id =  "countDivLi";
      YAHOO.util.Dom.addClass(visibleUl, 'gridList');
      YAHOO.util.Dom.setStyle(visibleUl, 'clear', 'both');
      YAHOO.util.Dom.setStyle(visibleUl, 'display', 'none');
  
      that._toggleVisibility(toggleDiv, visibleUl);
  
      visibleDiv.appendChild(visibleUl);
  
      /*
       * Global COUNT, causes implicit group by on all selected attributes
       */
  
      var countAttribute = new MDSS.BasicAttribute({
        type: klass.CLASS,
        displayLabel: MDSS.QueryXML.COUNT_FUNCTION,
        attributeName: 'id'
      });
  
      var countCheck = document.createElement('input');
      YAHOO.util.Dom.setAttribute(countCheck, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(countCheck, 'id', countAttribute.getKey());
      YAHOO.util.Dom.addClass(countCheck,'uncheckMeOnQueryTypeSwitch');
      YAHOO.util.Event.on(countCheck, 'click', that._toggleCount, countAttribute, that);
      this._defaults.push({element: countCheck, checked:false});
  
      var countSpan = document.createElement('span');
      countSpan.innerHTML = countAttribute.getDisplayLabel() + ' (GB)';
  
      var li = document.createElement('li');
  
      li.appendChild(countCheck);
      li.appendChild(countSpan);
  
      visibleUl.appendChild(li);
  
      /*
       * Global RATIO, causes implicit group by on all selected attributes
       */
      if(useRatio)
      {
        var ratioAttribute = new MDSS.BasicAttribute({
          type: 'sqlcharacter',
          key: MDSS.QueryXML.RATIO_FUNCTION,
          displayLabel: 'RATIO',
          attributeName: MDSS.QueryXML.RATIO_FUNCTION,
          isAggregate:true
        });
  
        var ratioCheck = document.createElement('input');
        YAHOO.util.Dom.setAttribute(ratioCheck, 'type', 'checkbox');
        YAHOO.util.Dom.setAttribute(ratioCheck, 'id', ratioAttribute.getKey());
          YAHOO.util.Dom.addClass(ratioCheck,'uncheckMeOnQueryTypeSwitch');
        YAHOO.util.Event.on(ratioCheck, 'click', that._toggleRatio, ratioAttribute, that);
        this._defaults.push({element: ratioCheck, checked:false});
  
        var ratioSpan = document.createElement('span');
        ratioSpan.innerHTML = ratioAttribute.getDisplayLabel() + ' (GB)';
  
        var li = document.createElement('li');
  
        li.appendChild(ratioCheck);
        li.appendChild(ratioSpan);
  
        visibleUl.appendChild(li);
      }
  
  
      var options = Mojo.Util.getValues(MDSS.QueryXML.DateGroupOpts);
      var keys = Mojo.Util.getKeys(MDSS.QueryXML.DateGroupOpts);
      for(var j=0; j<options.length; j++)
      {
        var countCheck = document.createElement('input');
        YAHOO.util.Dom.setAttribute(countCheck, 'type', 'checkbox');
        YAHOO.util.Dom.setAttribute(countCheck, 'value', keys[j]);
        YAHOO.util.Dom.setAttribute(countCheck, 'id', keys[j]);
        YAHOO.util.Dom.addClass(countCheck,'uncheckMeOnQueryTypeSwitch');
        YAHOO.util.Event.on(countCheck, 'click', that._dateGroupHandler, keys[j],that);
        this._defaults.push({element: countCheck, checked: false});
  
        var countSpan = document.createElement('span');
        countSpan.innerHTML = options[j];
  
        var li = document.createElement('li');
  
        li.appendChild(countCheck);
        li.appendChild(countSpan);
  
        visibleUl.appendChild(li);
      }
  
  
      return visibleDiv;
  
    },
  
  
  
    /**
     * Handler for when a new thematic layer type is selected.
     */
    thematicLayerSelected : function(layerType)
    {
      // Update the thematic layer for mapping. This is the only forced
      // save due to required artifact dependencies.
      var request = new MDSS.Request({
        thisRef : this,
        layerType : layerType,
        onSuccess : function()
        {
          var enable = this.layerType != null && this.layerType !== '';
          this.thisRef._queryPanel.toggleThematicSettings(enable);
        }
      });
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var thematicLayerId = (savedSearchView != null ? savedSearchView.getThematicLayerId() : "");
  
      Mojo.$.dss.vector.solutions.query.ThematicLayer.changeLayerType(request, thematicLayerId, layerType);
    },
  
    _reconstructSearch : function(entities, view)
    {
      // check all selected universals
      var configRaw = view.getConfig();
      var config = new MDSS.Query.Config(configRaw);
      var selectedUniversals = config.getSelectedUniversals();
      this._selectSearch.setSelectedUniversals(selectedUniversals);
  
      // Load the GeoEntities as WHERE criteria
      if(entities.length > 0)
      {
        var request = new MDSS.Request({
          thisRef : this,
          onSuccess : function(query)
          {
            var results = query.getResultSet();
  
            this.thisRef._selectSearch.setCriteria(results);
  
            this.thisRef._hideHandler(results, selectedUniversals);
          }
        });
  
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getAsViews(request, entities);
      }
      else
      {
        this._hideHandler([], selectedUniversals);
      }
    },
  
    _delegateToOption : function(e, attribute)
    {
      var select = e.target;
      var option = select.options[select.selectedIndex];
      this._fireClickOnOption(option);
    },
  
    _uncheckBox : function(check)
    {
      var checkEl = Mojo.Util.isString(check) ? document.getElementById(check) : check;
      if(checkEl != null && checkEl.checked)
      {
        checkEl.click();
      }
    },
  
    _checkBox : function(check)
    {
      var checkEl = Mojo.Util.isString(check) ? document.getElementById(check) : check;
      if(checkEl != null && !checkEl.checked)
      {
        checkEl.click();
      }
    },
  
    _chooseOption : function(option)
    {
      var optionEl = Mojo.Util.isString(option) ? document.getElementById(option) : option;
      if(optionEl == null)
      {
        return;
      }
  
      var select = optionEl.parentNode;
      var options = select.options;
      for(var i=0; i<options.length; i++)
      {
        if(options[i].id === optionEl.id)
        {
          select.selectedIndex = i;
          break;
        }
      }
  
      this._fireClickOnOption(optionEl);
    },
  
    /**
     * Loops through all the default settings in this._defaults
     * and applies the necessary logic depending on the input type.
     */
    _resetToDefault : function()
    {
      for(var i=0; i<this._defaults.length; i++)
      {
        var obj = this._defaults[i];
        var element = obj.element;
        if(element.nodeName === 'INPUT' && element.type === 'checkbox')
        {
          var checked = obj.checked;
          if(element.checked !== checked)
          {
            if(obj.bypass)
            {
              element.checked = checked;
            }
            else
            {
              element.click();
            }
          }
        }
        else if(element.nodeName === 'INPUT' && element.type === 'text')
        {
          var value = obj.value;
  
          element.value = value;
        }
        else if(element.nodeName === 'SELECT')
        {
          var index = obj.index;
          if(!element.disabled)
          {
            element.selectedIndex = index;
            this._fireClickOnOption(element.options[index]);
          }
  
          if(!obj.active && index === 0)
          {
            element.disabled = true;
          }
        }
  
  
        if(obj.disabled)
        {
          element.disabled = true;
        }
      }
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
  
      var itemSorter = function(a,b){
        x = a.text.toUpperCase();
        y = b.text.toUpperCase();
          return x>y?1:x<y?-1:0;
        }
  
      if(items != null)
      {
        return items.sort(itemSorter);
      }
      else
      {
        return [];
      }
    },
  
    /**
     * Called when a user tries to load a query.
     */
    loadQuery : function(savedSearchId)
    {
      var request = new MDSS.Request({
        thisRef : this,
        savedSearchId : savedSearchId,
        onComplete : function(){},
        onSuccess: function(savedSearchView){
  
          this.thisRef._resetToDefault();
          this.thisRef._queryPanel.clearAllDefinedLayers();
  
          this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);
  
          // set the XML and config
          this.thisRef._loadQueryState(savedSearchView);
  
          // set the layers
          var request2 = new MDSS.Request({
            thisRef : this.thisRef,
            onSend : function(){},
            onSuccess : function(layerViews){
  
              for(var i=0; i<layerViews.length; i++)
              {
                var layerView = layerViews[i];
  
                if(layerView.getIsThematic())
                {
                  var type = layerView.getThematicType();
                  var valid = type != null && type !== '';
                  if(valid)
                  {
                    // set the selected thematic layer (the list will be populatd by now)
                    this.thisRef._queryPanel.setSelectedThematicLayer(type);
                  }
  
                  this.thisRef._queryPanel.toggleThematicSettings(valid);
                }
                else
                {
                  var layerId = layerView.getLayerId();
                  var type = layerView.getUniversalType();
  
                  this.thisRef._queryPanel.addDefinedLayer(layerId, type);
                }
              }
            }
          });
  
          Mojo.$.dss.vector.solutions.query.SavedSearch.getAllLayers(request2, this.savedSearchId);
        }
      });
  
      Mojo.$.dss.vector.solutions.query.SavedSearch.loadSearch(request, savedSearchId);
    },

    _fireClickOnOption : function(option)
    {
      // FIXME add IE version of this
      var evObj = document.createEvent('UIEvents');
      evObj.initUIEvent('click', true, true, window, 1);
      option.dispatchEvent(evObj);
    },
  
    /**
     * Cancels saving a new query.
     */
    _cancelQueryListener : function(modal, params, action)
    {
      modal.destroy();
    },
  
   /**
     * Saves the current state of the QueryXML.
     */
    saveQuery : function()
    {
      var view = this._queryPanel.getCurrentSavedSearch();
  
      if(view != null)
      {
        this._populateSearch(null, view);
      }
  
      var request = new MDSS.Request({
        onSuccess : function()
        {
          // nothing to do
        }
      });
  
      Mojo.$.dss.vector.solutions.query.SavedSearch.updateSearch(request, view);
    },
  
    /**
     * Saves the current state of the QueryXML.
     */
    saveQueryAs : function()
    {
      var controller = Mojo.$.dss.vector.solutions.query.QueryController;
      var request = new MDSS.Request({
        thisRef: this,
        controller: controller,
        onSuccess: function(html)
        {
          var modal = this.thisRef._createModal(html, MDSS.Localized.Query.Save);
  
          var saved = Mojo.Util.bind(this.thisRef, this.thisRef._saveQueryListener, modal);
          var canceled = Mojo.Util.bind(this.thisRef, this.thisRef._cancelQueryListener, modal);
  
          this.controller.setSaveQueryListener(saved);
          this.controller.setCancelQueryListener(canceled);
        }
      });
  
      controller.newQuery(request);
    },
  
    /**
     * Saves the current state of the query.
     */
    _saveQueryListener : function(modal, params, action)
    {
      var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
      this._populateSearch(params != null ? params['savedQueryView.queryName'] : null, view);
  
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
  
      Mojo.$.dss.vector.solutions.query.SavedSearch.saveSearch(request, view);
    },
  
    _populateSearch : function(queryName, view)
    {
      var queryXML = this._constructQuery();
      var xml = queryXML.getXML();
      var queryType = this._getQueryType();
  
      if(queryName != null)
      {
        view.setQueryName(queryName);
      }
  
      view.setQueryXml(xml);
      view.setConfig(Mojo.Util.getJSON(this._config));
      view.setThematicLayer(this._queryPanel.getCurrentThematicLayer());
      view.setQueryType(queryType);
    },

  
    /**
     * Creates and returns an MDSS.QueryXML.Query object.
     * Subclasses must override this method and use the returned
     * object according their specific use case.
     */
    _constructQuery : function(forMapping)
    {
      forMapping = forMapping || false;
  
      var queryXML = new MDSS.QueryXML.Query();
  
      if(this._allPathsQuery != null)
      {
        queryXML.addEntity(this._allPathsQuery);
      }
  
      if(forMapping)
      {
        // only include the thematic layer as an entity in the query.
        // The selectables (data column and entity name) will be provided
        // by a ValueQuery in the business layer
  
        var layer = this._queryPanel.getCurrentThematicLayer();
        if(layer != null && layer != '')
        {
          var entity = new MDSS.QueryXML.Entity(layer, layer);
          queryXML.addEntity(entity);
        }
      }
      else
      {
        var geoSelectables = Mojo.Util.getKeys(this._geoEntitySelectables);
        for(var i=0; i<geoSelectables.length; i++)
        {
          var name = geoSelectables[i];
          var selectable = this._geoEntitySelectables[name];
  
          queryXML.addSelectable(name, selectable);
        }
      }
  
      // geo id restrictions (WHERE clause)
      var conditions = Mojo.Util.getValues(this._geoIdConditions);
      if(conditions.length > 0)
      {
        var or = new MDSS.QueryXML.Or();
        for(var i=0; i<conditions.length; i++)
        {
          var condition = conditions[i];
  
          or.addCondition('geoIdCondition_'+i, condition);
        }
  
        var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);
  
        this._allPathsQuery.setCondition(compositeCondition);
      }
  
      // calculate the date criteria
      var startDateEl = this._queryPanel.getStartDate();
      var startDate = MDSS.util.stripWhitespace(startDateEl.value);
      if(startDate.length > 0)
      {
        var formatted = MDSS.Calendar.getMojoDateString(startDate);
        var startDateCondition = new MDSS.QueryXML.BasicCondition(this._startDateSelectable, MDSS.QueryXML.Operator.GE, formatted);
        this._startDate = startDateCondition;
      }
      else
      {
        this._startDate = null;
      }
  
      var endDateEl = this._queryPanel.getEndDate();
      var endDate = MDSS.util.stripWhitespace(endDateEl.value);
      if(endDate.length > 0)
      {
        var formatted = MDSS.Calendar.getMojoDateString(endDate);
        var endDateCondition = new MDSS.QueryXML.BasicCondition(this._endDateSelectable, MDSS.QueryXML.Operator.LE, formatted);
        this._endDate = endDateCondition;
      }
      else
      {
        this._endDate = null;
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
      if(!Mojo.Util.isObject(geoEntityQuery))
      {
        geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
        this._geoEntityTypes[type] = geoEntityQuery;
      }
  
      if(addColumn)
      {
  
        // use the type name and lowercase it so it adheres to attribute naming conventions
        var typeName = type.substring(type.lastIndexOf('.')+1).toLowerCase();
  
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
  
          var entityNameAttr = new MDSS.QueryXML.Attribute(type, geoEntityView.getEntityNameMd().getName(), entityNameColumn, obj.label);
          //var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getEntityNameMd().getName(), entityNameColumn);
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
  
          var geoIdAttr = new MDSS.QueryXML.Attribute(type, geoEntityView.getGeoIdMd().getName(), geoIdColumn, obj.label);
          //var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), geoEntityView.getGeoIdMd().getName(), geoIdColumn);
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
      var typeName = type.substring(type.lastIndexOf('.')+1).toLowerCase(); // lowercase to force attribute naming convention
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
    _hideHandler : function(criteriaEntities, selectedUniversals)
    {
      this._queryPanel.clearAllRecords();
  
      // clear any prior selected universals
      this._config.clearSelectedUniversals();
  
      // remove existing columns
      var types = Mojo.Util.getKeys(this._geoEntityTypes);
      for(var i=0; i<types.length; i++)
      {
        this._removeUniversalEntity(types[i]);
      }
  
      // add new columns
      for(var i=0; i<selectedUniversals.length; i++)
      {
        var universal = selectedUniversals[i];
  
        var construct = Mojo.Meta.findClass(universal);
        var geoEntity = new construct();
        var geoEntityView = this._selectSearch._copyEntityToView(geoEntity);
  
        this._addUniversalEntity(geoEntityView, true);
  
        this._config.addSelectedUniversal(geoEntityView.getEntityType());
      }
  
      this._queryPanel.setAvailableThematicLayers(selectedUniversals);
  
      // remove all prior conditions
      this._geoIdConditions = {};
      this._allPathsQuery = null;
  
      if(criteriaEntities.length > 0)
      {
        this._allPathsQuery = new MDSS.QueryXML.Entity(this.ALL_PATHS, this.ALL_PATHS);
      }
  
      for(var i=0; i<criteriaEntities.length; i++)
      {
        var geoEntityView = criteriaEntities[i];
  
        var attribute = new MDSS.QueryXML.Attribute(this._allPathsQuery.getAlias(), 'parentGeoEntity');
        var selectable = new MDSS.QueryXML.Selectable(attribute);
        var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, geoEntityView.getGeoEntityId());
  
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
        this._selectSearch.render();
      }
    },
  
    /**
     * Creates a modal with the given HTML as its body and the given title
     * as the modal title, wrapped in an H3.
     */
    _createModal : function(html, title, useLarge)
    {
      var executable = MDSS.util.extractScripts(html);
      var html = MDSS.util.removeScripts(html);
  
      var modal = new YAHOO.widget.Panel("editQuery", {
        width:"400px",
        height: useLarge ? "530px" : "400px",
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
      YAHOO.util.Dom.addClass(contentDiv, (useLarge ? 'innerContentModalLarge' : 'innerContentModal'));
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);
  
      modal.setBody(outer);
      modal.render(document.body);
  
      eval(executable);
  
      modal.bringToTop();
  
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
  
          var modal = this.thisRef._createModal(html, MDSS.Localized.Update, true);
  
          var update = Mojo.Util.bind(this.thisRef, this.thisRef._updateLayerListener, modal);
          var canceled = Mojo.Util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, layerId, true);
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
    deleteLayer : function(layerId, type)
    {
      var request = new MDSS.Request({
        thisRef : this,
        layerId : layerId,
        type : type,
        onSuccess : function()
        {
          this.thisRef._queryPanel.removeDefinedLayer(this.layerId, this.type);
        }
      });
  
      Mojo.$.dss.vector.solutions.query.MappingController.deleteLayer(request, layerId);
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
        // The string values are set in editVariableStyles.jsp
        thematicVar = new Mojo.$.dss.vector.solutions.query.ThematicVariable();
        var pieces = thematicVarStr.split('_-_');
        thematicVar.setEntityAlias(pieces[0]);
        thematicVar.setAttributeName(pieces[1]);
        thematicVar.setUserAlias(pieces[2]);
  
        var display = this._queryPanel.getSelectedDisplayLabel(pieces[2]);
        thematicVar.setDisplayLabel(display);
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
  
          var update = Mojo.Util.bind(this.thisRef, this.thisRef._editThematicVariable, modal, this.thematicLayerId);
          var canceled = Mojo.Util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, this.thematicLayerId,true);
          var newCategory = Mojo.Util.bind(this.thisRef, this.thisRef._newCategoryListener);
  
          this.controller.setUpdateThematicVariableListener(update);
          this.controller.setCancelLayerListener(canceled);
  
          Mojo.$.dss.vector.solutions.query.RangeCategoryController.setNewInstanceListener(newCategory);
          Mojo.$.dss.vector.solutions.query.NonRangeCategoryController.setNewInstanceListener(newCategory);
        }
      });
  
      var thematicVars = this._queryPanel.getThematicVariables();
      for(var i=0; i<thematicVars.length; i++)
      {
        // grab the most recent version of the display
        // label (taking aggregate functions into account)
        var thematic = thematicVars[i];
  
        var display = this._queryPanel.getSelectedDisplayLabel(thematic.getUserAlias());
        thematic.setDisplayLabel(display);
      }
  
      controller.editThematicLayer(request, thematicLayerId, thematicVars);
    }
  
  }
});




