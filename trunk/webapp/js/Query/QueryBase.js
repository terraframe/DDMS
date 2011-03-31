/**
 * Base class to support functionality for all query use cases.
 */
Mojo.Meta.newClass('MDSS.QueryBase', {

  IsAbstract : true,

  Constants : {
    GEO_ATTRIBUTES : 'geoAttributes',
    DATE_ATTRIBUTES : 'dateAttributes',
    TARGET : 'target'
  },

  Instance : {

    initialize : function(queryList)
    {
      this._queryPanel = new MDSS.QueryPanel(this, 'queryPanel', 'mapPanel', {
        executeQuery: this.executeQuery,
        saveQuery: this.saveQuery,
        saveQueryAs: this.saveQueryAs,
        loadQuery: this.loadQuery,
        exportXLS : this.exportXLS,
        exportCSV : this.exportCSV,
        exportReport : this.exportReport,
        paginationHandler : this.paginationHandler,
        postRender : this.postRender
      });
      
      this._dm = new MDSS.DependencyManager();
      
      // Map of attribute key to display label
      this._geoAttributes = {};
      
      this._allPathQueries = {};
  
      this._geoEntityTypes = {};
      this._geoEntitySelectables = {};
      
      // list of geo attributes to their GeoEntityView criteria objects
      this._criteriaEntities = {};
  
      this._currentPage = 1;
  
      this._startDate = null;
      this._endDate = null;
      this._dateGroupSelectables = {};
  
      this._config = new MDSS.Query.Config();
  
      this.PAGE_SIZE = 100;
  
      this.ALL_PATHS = "dss.vector.solutions.geo.AllPaths";
  
      var hideBound = Mojo.Util.bind(this, this._hideHandler);
  
      this._selectSearch = new MDSS.MultipleSelectSearch();
      this._selectSearch.setHideHandler(hideBound);
      this._selectSearch.setFilter('');
  
      // list of all elements and default settings
      this._defaults = [];
      
      this._browsers = {};
      
      this._namespacedQueryType = queryList.namespacedType;
      
      var queries = queryList.queries;
      for(var i=0; i<queries.length; i++)
      {
        this._queryPanel.addAvailableQuery(queries[i]);
      }
      
      // A counter used by refreshColumnsWhenIdle() to know when
      // the QB is ready to display all the columns after loading 
      // a saved query.
      this._refreshIdleCounter = 0;
      
      // bound refreshColumnsWhenIdle() function to enforce *this* context.
      this._refreshIdleBound = Mojo.Util.bind(this, this._refreshColumnsWhenIdle);
    },
    
    _refreshColumnsWhenIdle : function()
    {
      if(this._refreshIdleCounter === 0)
      {
        // The query state is loaded, so sort the columns before finally
        // displaying them
        var ordered = this._config.getProperty('sortOrder');
        if(Mojo.Util.isArray(ordered) && ordered.length > 0)
        {
          this._queryPanel.orderColumns(ordered);
          this._queryPanel.refreshBatch();
        }
      }
      else
      {
        // not ready to refresh so wait a little longer
        setTimeout(this._refreshIdleBound, 50);
      }
    },
    
    getDateGroupIds : function()
    {
      return Mojo.Iter.map(Mojo.Util.getKeys(MDSS.QueryXML.DateGroupOpts), function(opt){ return opt.toLowerCase(); });
    },
    
    getDependencyManager : function()
    {
      return this._dm;
    },
    
    getGeoPicker : function ()
    {
      return this._selectSearch;
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
     * Returns the MDSS.QueryBrowser instance based on the AbstractAttribute or key parameters.
     * Null is returned if no match is found.
     */
    getBrowser : function(attribute)
    {
      return this._browsers[(Mojo.Util.isString(attribute) ? attribute : attribute.getKey())+'_li'];
    },
    
    /**
     * Clears all selected Terms on the ontology browser for the given attribute.
     * This is useful to remove criteria when a selectable for a Term attribute
     * has been removed from the query.
     */
    clearBrowserTerms : function(attribute)
    {
      var browser = this.getBrowser(attribute);
      if(browser)
      {
        browser.clearTerms();
      }
    },
    
    /**
     * Maps a QueryBrowser object to a specific
     * attribute element (typically an LI).
     */
    _attachBrowser : function(elementId, handler, attribute, fieldClass, fieldAttribute)
    {
    	
    	var bound = null;
    	
    	if(Mojo.Util.isFunction(handler))
    	{
    		bound = Mojo.Util.bind(this, handler);
    	}
    	else
    	{
    		bound = Mojo.emptyFunction;
    	}
    		      
      var browser = new MDSS.QueryBrowser(this, bound, attribute, fieldClass, fieldAttribute);
      this._browsers[elementId] = browser;
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
  
    _dateGroupHandler : function(e,group)
    {
      var check = e.target;
      //var group = check.value;
      if(check.checked)
      {
        var attribute = new MDSS.QueryXML.Sqlcharacter('', group , group.toLowerCase(),MDSS.QueryXML.DateGroupOpts[group]);
        var selectable = new MDSS.QueryXML.Selectable(attribute);
        this._dateGroupSelectables[group] = selectable;
        this._queryPanel.insertColumn(new YAHOO.widget.Column({
          key: group.toLowerCase(),
          label: MDSS.QueryXML.DateGroupOpts[group]
        }));
      }
      else
      {
        var column = this._queryPanel.getColumn(group.toLowerCase());
        this._queryPanel.removeColumn(column);
        delete this._dateGroupSelectables[group];
      }
      
      this._dm.notifyAll(e);
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
      case 'DATEGROUP_EPIYEAR':
        Mojo.$.dss.vector.solutions.general.EpiDate.snapToEpiYear(request,date,snapToFirstDay);
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
//      queryTypeInput.value = this._getReportQueryType();
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
           if(dto instanceof AttributeDateDTO){
             value = MDSS.Calendar.getLocalizedString(value);
           }
           else if(dto instanceof AttributeDecDTO){
             if(value != null){
               value = value.toFixed(2);
             }
           }
           else if(dto instanceof AttributeBooleanDTO){
  
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
      YAHOO.util.Event.on(MDSS.QueryBase.TARGET, 'click', this._displaySearch, null, this);
    
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
      this.executeQuery(false);
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
        this._queryPanel.insertColumn(new YAHOO.widget.Column({
          key: range.toLowerCase(),
          label: MDSS.localize(range)
        }));
      }
      else
      {
        var column = this._queryPanel.getColumn(range.toLowerCase());
        this._queryPanel.removeColumn(column);
        delete this._dateGroupSelectables[range];
      }
  
    },
  
    _getExportXLSAction: {
      IsAbstract : true
    },
  
    _getExportCSVAction: {
      IsAbstract : true
    },
  
    _getExportReportAction : function()
    {
      return 'dss.vector.solutions.report.ReportController.generateReport.mojo';
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
        displayLabel: MDSS.localize('COUNT'),
        attributeName: 'id'
      }, this);
  
      var countCheck = document.createElement('input');
      YAHOO.util.Dom.setAttribute(countCheck, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(countCheck, 'id', countAttribute.getKey());
      YAHOO.util.Dom.addClass(countCheck,'uncheckMeOnQueryTypeSwitch');
      YAHOO.util.Event.on(countCheck, 'click', that._toggleCount, countAttribute, that);
      this._defaults.push({element: countCheck, checked:false});
  
      var countSpan = document.createElement('span');
      countSpan.innerHTML = countAttribute.getDisplayLabel() + ' ' + MDSS.localize('selectable_is_aggreated');
  
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
          type: 'sqlfloat',
          key: MDSS.QueryXML.RATIO_FUNCTION,
          displayLabel: MDSS.localize('RATIO'),
          attributeName: MDSS.QueryXML.RATIO_FUNCTION,
          isAggregate:true
        }, this);
  
        var ratioCheck = document.createElement('input');
        YAHOO.util.Dom.setAttribute(ratioCheck, 'type', 'checkbox');
        YAHOO.util.Dom.setAttribute(ratioCheck, 'id', ratioAttribute.getKey());
          YAHOO.util.Dom.addClass(ratioCheck,'uncheckMeOnQueryTypeSwitch');
        YAHOO.util.Event.on(ratioCheck, 'click', that._toggleRatio, ratioAttribute, that);
        this._defaults.push({element: ratioCheck, checked:false});
  
        var ratioSpan = document.createElement('span');
        ratioSpan.innerHTML = ratioAttribute.getDisplayLabel()  + ' ' + MDSS.localize('selectable_is_aggreated');
  
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
        YAHOO.util.Dom.setAttribute(countCheck, 'id', keys[j].toLowerCase());
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
     * Method called to render to set up the QueryPanel
     * this QueryBase uses.
     */
    render: {
      IsAbstract : true
    },
  
    /**
     * Called when the user tries to execute the query.
     */
    executeQuery: {
      IsAbstract : true
    },
  
    /**
     * Called when the user tries to map a query.
     */
    mapQuery: {
      IsAbstract : true
    },
  
    _reconstructSearch : function(view)
    {
      // check all selected universals for every geo attribute
      var attributeKeys = Mojo.Util.getKeys(this._geoAttributes);
      var loaded = {};
      for(var i=0; i<attributeKeys.length; i++)
      {
        var attributeKey = attributeKeys[i];
        
        // check if any entities need to be loaded
        var entityCriteria = this._config.getCriteriaEntities(attributeKey);
        if(entityCriteria)
        {
          for(var j=0; j<entityCriteria.length; j++)
          {
            var id = entityCriteria[j];
            loaded[id] = null;
          } 
        }
      }
      
      
      // Load the GeoEntities as WHERE criteria
      var toLoad = Mojo.Util.getKeys(loaded);
      if(toLoad.length > 0)
      {
        this._refreshIdleCounter++;
        
        var request = new MDSS.Request({
          thisRef : this,
          onSuccess : function(query)
          {
            var results = query.getResultSet();
            for(var i=0; i<results.length; i++)
            {
              var view = results[i];
              var id = view.getGeoEntityId();
              loaded[id] = view;
            }
            
            // Only set the found geo entities (in case we were referencing a deleted entity)
            for(var i=0; i<attributeKeys.length; i++)
            {
              var attributeKey = attributeKeys[i];
              var criteriaEntities = this.thisRef._config.getCriteriaEntities(attributeKey)
              var views = [];
              for(var j=0; j<criteriaEntities.length; j++)
              {
                var view = loaded[criteriaEntities[j]];
                views.push(view);
              } 
                
              var selectedUniversals = this.thisRef._config.getSelectedUniversals(attributeKey);
              
              this.thisRef._hideHandler(views, selectedUniversals, attributeKey);
            }
            
            this.thisRef._refreshIdleCounter--;
          }
        });
  
        // TODO replace this hack with a better error handler/extension system
        var oldOnFailure = request.onFailure;
        request.onFailure = function(e)
        {
          this.thisRef._refreshIdleCounter--;
          oldOnFailure.call(this, e);
        };
        
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getAsViews(request, toLoad);
      }
      else
      {
        for(var i=0; i<attributeKeys.length; i++)
        {
          var attributeKey = attributeKeys[i];
          
          var selectedUniversals = this._config.getSelectedUniversals(attributeKey);
          this._hideHandler([], selectedUniversals, attributeKey);
        }
      
        MDSS.util.wait_for_ajax.hide();
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
        return true;
      }
      else
      {
        return false;
      }
    },
  
    _checkBox : function(check)
    {
      var checkEl = Mojo.Util.isString(check) ? document.getElementById(check) : check;
      if(checkEl != null && !checkEl.checked)
      {
        checkEl.click();
        return true;
      }
      else
      {
        return false;
      }
    },
  
    _chooseOption : function(option)
    {
      var optionEl = Mojo.Util.isString(option) ? document.getElementById(option) : option;
      if(optionEl == null)
      {
        return false;
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
      return true;
    },
  
    /**
     * Loops through all the default settings in this._defaults
     * and applies the necessary logic depending on the input type.
     */
    _resetToDefault : function()
    {
      var attributeKeys = Mojo.Util.getKeys(this._geoAttributes);
      for(var i=0; i<attributeKeys.length; i++)
      {
        this._hideHandler([], [], attributeKeys[i]);
      }
    
      // clear all criteria on any browsers
      var browsers = Mojo.Util.getValues(this.browsers);
      for(var i=0; i<browsers.length; i++)
      {
        browsers[i].clearTerms();
      }
    
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
      
      // disable the date criteria checkboxes
      this._queryPanel.disableDates(true, true);
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
  
      // look for menu items
      var items = this._menus[li.id];
      if(items != null && items.length > 0)
      {
        var itemSorter = function(a,b){
          x = a.text.toUpperCase();
          y = b.text.toUpperCase();
            return x>y?1:x<y?-1:0;
        };
  
        return items.sort(itemSorter);
      }
      
      // look for a browser match
      var browser = this._browsers[li.id];
      if(browser)
      {
        browser.openBrowser();
      
        // return empty set of menu items as the browser will be launched instead.
        return [];
      }
      
      // default case, no menu items to render
      return [];
    },
  
    /**
     * Called when a user tries to load a query.
     */
    loadQuery : function(savedSearchId)
    {
      var request = new MDSS.Request({
        thisRef : this,
        hadError : false,
        savedSearchId : savedSearchId,
        onComplete : function(){
        
          // Batched Ajax isn't supported yet, so check for an error
          // and manually remove the overlay if an error occured.
          if(this.getTransport().status === 500)
          {
            MDSS.util.wait_for_ajax.hide();
          }
        },
        onSuccess: function(savedSearchView){
  
          this.thisRef._dm.disable();
          
          try
          {
            var that = this.thisRef;
            that._resetToDefault();
            that._config = new MDSS.Query.Config(savedSearchView.getConfig()); 
          
            that._queryPanel.setCurrentSavedSearch(savedSearchView);

            that._queryPanel.waitForRefresh = true;
            that._reconstructSearch(savedSearchView);
  
            // set the XML and config
            that._loadQueryState(savedSearchView);
            
            that._refreshColumnsWhenIdle();
          }
          catch(e)
          {
            // not all of the query could be loaded. There are too many
            // possibilities that could cause this, but at least load the
            // best we can.
            this.thisRef._queryPanel.waitForRefresh = false;
          }
          finally
          {
            this.thisRef._dm.enable();
          }
        }
      });
  
      Mojo.$.dss.vector.solutions.query.SavedSearch.loadSearch(request, savedSearchId);
    },
  
    _loadQueryState : {
      IsAbstract : true
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
      view.setConfig(this._config.getJSON());
      view.setQueryType(queryType);
    },
    
    /**
     * Unique type of this query which is the string: "[query class:query type]".
     * For example, "dss.vector.solutions.entomology.MosquitoCollection:QUERYIRS"
     */
    _getQueryType : function()
    {
      return this._namespacedQueryType;
    },
  
    /**
     * Subclasses must override this to return the controller method
     * that will be executed to save a search.
    _getQueryType: {
      IsAbstract : true
    },
     */
    
    /**
     * Creates and returns an MDSS.QueryXML.Query object.
     * Subclasses must override this method and use the returned
     * object according their specific use case.
     */
    _constructQuery : function()
    {
      var queryXML = new MDSS.QueryXML.Query();
  
      // get the order of the selectables for sorting
      var columnSet = this._queryPanel.getColumnSet();
      var columns = columnSet.keys;
      var order = Mojo.Iter.map(columns, function(col){ return col.getKey(); });
      this._config.setProperty('sortOrder', order);
      
      // Add GeoEntity criteria on any geo attribute
      var attributeKeys = Mojo.Util.getKeys(this._allPathQueries);
      for(var i=0; i<attributeKeys.length; i++)
      {
        var attributeKey = attributeKeys[i];
        var allPaths = this._allPathQueries[attributeKey];
        
        var or = new MDSS.QueryXML.Or();
        var criteriaEntities = this._criteriaEntities[attributeKey];
        for(var i=0; i<criteriaEntities.length; i++)
        {
          var geoEntityView = criteriaEntities[i];
  
          var attribute = new MDSS.QueryXML.Attribute(allPaths.getAlias(), 'parentGeoEntity');
          var selectable = new MDSS.QueryXML.Selectable(attribute);
          var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, geoEntityView.getGeoEntityId());
  
          or.addCondition(attributeKey+'__'+geoEntityView.getGeoEntityId()+'_'+i, geoIdCondition);
        }
        
        var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);
        allPaths.setCondition(compositeCondition);
      
        queryXML.addEntity(allPaths);
      }
      
      var geoSelectables = Mojo.Util.getKeys(this._geoEntitySelectables);
      for(var i=0; i<geoSelectables.length; i++)
      {
        var namespacedType = geoSelectables[i];
        var geoSels = this._geoEntitySelectables[namespacedType];
 
        queryXML.addSelectable(namespacedType+'-entityName', geoSels.entityName);
        queryXML.addSelectable(namespacedType+'-geoId', geoSels.geoId);
      }
  
      // calculate the date criteria
      var startDateEl = this._queryPanel.getStartDate();
      var startDate = MDSS.util.stripWhitespace(startDateEl.value);
      var endDateEl = this._queryPanel.getEndDate();
      var endDate = MDSS.util.stripWhitespace(endDateEl.value);
      this._endDate = null;
      this._startDate = null;
      startDate = MDSS.Calendar.getMojoDateString(startDate);
      endDate = MDSS.Calendar.getMojoDateString(endDate);
      

      
      if(this._dateAttributes){
      	var dateAttrib =  this._dateAttributes[this._dateAttributeSelect.value];
      	var dateObj = {
      		'date_attribute':dateAttrib.attribute,
      		'klass':dateAttrib.klass,
      		'start':startDate,
      	  'end':endDate
      	}; 
      	this._config.setDateAttribute(dateObj);
      }
      else
      {
        if(startDate)
        {
          var startDateCondition = new MDSS.QueryXML.BasicCondition(this._startDateSelectable, MDSS.QueryXML.Operator.GE, startDate);
          this._startDate = startDateCondition;
        }

        if(endDate)
        {
          var endDateCondition = new MDSS.QueryXML.BasicCondition(this._endDateSelectable, MDSS.QueryXML.Operator.LE, endDate);
          this._endDate = endDateCondition;
        }
      }
    
      return queryXML;
    },
  
    /**
     * Adds a universal type as a selectable to the query results. The universal
     * is represented as a GeoEntityView, which is a view instance of that geo entity
     * type. This is done to grab any necessary metadata/display labels.
     * 
     * Returns the key
     */
    _addUniversalEntity: function(attributeKey, geoEntityView)
    {
      // Earth is not allowed in the Select
      if(geoEntityView.getEntityType() === 'dss.vector.solutions.geo.generated.Earth')
      {
        return;
      }
      
      var type = geoEntityView.getEntityType();
      var entityAlias = attributeKey+"__"+type;
  
      // add the GeoEntity as a query entity
      var namespacedType = attributeKey+'_'+type;
      
      var geoEntityQuery = this._geoEntityTypes[namespacedType];
      if(!Mojo.Util.isObject(geoEntityQuery))
      {
        this._geoEntityTypes[namespacedType] = new MDSS.QueryXML.Entity(type, entityAlias);
      }
  
      // Add the entity name and geo id selectables
      var geoSelectables = this._geoEntitySelectables[namespacedType];
      if(!Mojo.Util.isObject(geoSelectables))
      {
        // use the type name and lowercase it so it adheres to attribute naming conventions
        var typeName = type.substring(type.lastIndexOf('.')+1).toLowerCase();

        var entityName = this._addUniversalColumn(attributeKey, geoEntityView, typeName, entityAlias, geoEntityView.getEntityNameMd().getName());
        var geoId = this._addUniversalColumn(attributeKey, geoEntityView, typeName, entityAlias, geoEntityView.getGeoIdMd().getName());
        
        this._geoEntitySelectables[namespacedType] = {
            entityName : entityName,
          geoId : geoId
        };
      }
    
      return namespacedType;
    },
    
    /**
     * Adds the actual YUI column that represents the given universal attribute. Returns
     * the selectable representing the column.
     */
    _addUniversalColumn : function(attributeKey, geoEntityView, typeName, entityAlias, geoAttr)
    {
      // namespace the column using the attributeKey because different attributes
      // can add the same universal columns (e.g., Country [work], Country [home]).
      var columnKey = attributeKey.replace(/\./g, '_')+'__'+typeName+'_'+geoAttr;
      
      var obj = {
        key: columnKey,
        label: (geoEntityView.getTypeDisplayLabel() + " " 
          + geoEntityView.getAttributeDTO(geoAttr).getAttributeMdDTO().getDisplayLabel()
          + " (" + this._geoAttributes[attributeKey]+")")
      };
 
      var column = new YAHOO.widget.Column(obj);
      this._queryPanel.insertColumn(column);
 
      var attr = new MDSS.QueryXML.Attribute(entityAlias, geoAttr, columnKey, obj.label);
      var sel = new MDSS.QueryXML.Selectable(attr);
      
      return sel;
    },
    
    _removeUniversalColumns : function(attributeKey, ignoreSet)
    {
      // remove all universal entities from the query
      var keys = Mojo.Util.getKeys(this._geoEntityTypes);
      for(var i=0; i<keys.length; i++)
      {
        var key = keys[i];
        // only delete GeoEntity queries for this attribute
        if(!ignoreSet.contains(key) && key.indexOf(attributeKey) !== -1)
        {
          delete this._geoEntityTypes[key];
        }
      }
      
      // remove all geo entity name and geo id columns
      keys = Mojo.Util.getKeys(this._geoEntitySelectables);
      for(var i=0; i<keys.length; i++)
      {
        var key = keys[i];
        // The column may not exist because the selectable
        // was used for criteria and not selection
        // only delete GeoEntity queries for this attribute
        if(!ignoreSet.contains(key) && key.indexOf(attributeKey) !== -1)
        {
          var geoSelectables = this._geoEntitySelectables[key];
          
          var entityAliasCol = this._queryPanel.getColumn(geoSelectables.entityName.getComponent().getUserAlias());
          if(entityAliasCol)
          {
            this._queryPanel.removeColumn(entityAliasCol);
          }

          var geoIdCol = this._queryPanel.getColumn(geoSelectables.geoId.getComponent().getUserAlias());
          if(geoIdCol)
          {
            this._queryPanel.removeColumn(geoIdCol);
          }
          
          delete this._geoEntitySelectables[key];
        }
      }
    },
  
    /**
     * Uses the given GeoEntityView objects to add
     * restrictions to the GeoEntity query.
     */
    _hideHandler : function(criteriaEntities, selectedUniversals, currentAttribute)
    {
      // This override is required for loading saved queries
      if(!currentAttribute)
      {
        currentAttribute = this._getCurrentGeoAttribute();
      }
    
      this._queryPanel.clearAllRecords();
  
      // clear any prior selected universals
      this._config.clearSelectedUniversals(currentAttribute);
  
      // add universal columns. The criteria for pre-existing columns will be recreated but
      // the columns will not be deleted.
      var ignoreSet = new MDSS.Set(); // namespaced key in geo type/attr maps to not remove
      for(var i=0; i<selectedUniversals.length; i++)
      {
        var universal = selectedUniversals[i];
  
        var construct = Mojo.Meta.findClass(universal);
        var geoEntity = new construct();
        var geoEntityView = this._selectSearch._copyEntityToView(geoEntity);
  
        var namespacedType = this._addUniversalEntity(currentAttribute, geoEntityView);
        ignoreSet.set(namespacedType);
        
        this._config.addSelectedUniversal(currentAttribute, geoEntityView.getEntityType());
      }

      // remove existing columns
      this._removeUniversalColumns(currentAttribute, ignoreSet);
  
      this._criteriaEntities[currentAttribute] = criteriaEntities;
      
      if(criteriaEntities.length > 0)
      {
        var entityAlias = this.ALL_PATHS+'_'+currentAttribute; // Unique namespace per attribute 
        var allPaths = new MDSS.QueryXML.Entity(this.ALL_PATHS, entityAlias);
  
        this._allPathQueries[currentAttribute] = allPaths;
      }
      else
      {
        delete this._allPathQueries[currentAttribute];
      }
      
      this._config.setCriteriaEntities(currentAttribute, criteriaEntities);
      
      // update the query panel with the attribute's restricting geo entities
      var display = this._geoAttributes[currentAttribute];
      this._queryPanel.addSelectedGeoEntities(currentAttribute, display, criteriaEntities);
    },
    
    _getCurrentGeoAttribute : function()
    {
      var target = document.getElementById(MDSS.QueryBase.GEO_ATTRIBUTES);
      if(target.nodeName === 'INPUT')
      {
        return target.value;
      }
      else
      {
        return target.options[target.selectedIndex].value;
      }
    },
    
    _addGeoAttributes : function(div)
    {
    	var attributes = this._geoEntityAttribs
      var html;
      var attributeKeys = [];
      if(attributes.length > 1)
      {
        var html = '<select id="'+MDSS.QueryBase.GEO_ATTRIBUTES+'">';
        for(var i=0; i<attributes.length; i++)
        {
          var attribute = attributes[i];
          
          attributeKeys.push(attribute.keyName);
          this._geoAttributes[attribute.keyName] = attribute.display;
          this._criteriaEntities[attribute.keyName] = [];
          
          html += '<option value="'+attribute.keyName+'">'+attribute.display+'</option>';
        }
        html += '</select>';
      }
      else
      {
        var attribute = attributes[0];
        
        attributeKeys.push(attribute.keyName);
        this._geoAttributes[attribute.keyName] = attribute.display;
        this._criteriaEntities[attribute.keyName] = [];
      
        html = '<input type="hidden" value="'+attribute.keyName+'" id="'+MDSS.QueryBase.GEO_ATTRIBUTES+'" />';
        html += attribute.display;
      }
    
      var boundSearch = Mojo.Util.bind(this, this._displaySearch);
      
      var geospan = document.createElement('span');
      geospan.id = MDSS.QueryBase.GEO_ATTRIBUTES + 'Span';
      
      geospan.innerHTML = html + ' <img id="'+MDSS.QueryBase.TARGET+'" class="clickable" src="./imgs/icons/world.png"/>';
      
      div.appendChild(geospan);
//      this._queryPanel.addQueryItem({
//        html: 
//        id: "areaItem"
//      });
      
      
      this._config.setGeoAttributes(attributeKeys);
      return html;
    },
  
    /**
     * Displays the SelectSearch (handler for click event on Area/Target menu).
     */
    _displaySearch : function()
    {
      // Set the selected universals and criteria entities
      var currentAttribute = this._getCurrentGeoAttribute();
      var selectedUniversals = this._config.getSelectedUniversals(currentAttribute);
      var criteria = this._criteriaEntities[currentAttribute];
      
      this._selectSearch.setSelectedUniversals(selectedUniversals);
      this._selectSearch.setCriteria(criteria);
    
      if(this._selectSearch != null && this._selectSearch.isInitialized())
      {
        this._selectSearch.show();
      }
      else
      {
        this._selectSearch.render();
        this._setTreeValidator();
      }
      
    },
    
    _setTreeValidator : function()
    {
      MDSS.GeoEntityTree.setValidator(Mojo.Util.bind(this, this._validator));          
    },
    
    _validator : function(request, geoId)
    {
      var type = this._selectSearch.getFilter();
        
      if(Mojo.Util.isString(type) && type != '')
      {
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByType(request, geoId, type);
      }
      else 
      {
        var political = this._selectSearch.getPolitical();
        var populated = this._selectSearch.getPopulated();
        var sprayTarget = this._selectSearch.getSprayTargetAllowed();        
        var urban = this._selectSearch.getUrban();
        
        var parameters = [political, populated, sprayTarget, urban].concat(this._selectSearch.getExtraUniversals());
           
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByParameters(request, geoId, parameters);
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
    }
  
    
  },
  
  Static :
  {
    aliasTerm : function(termId)
    {
      return termId.substring(0,16);
    },
    
    filterFunctions : function(columns, toFilter, funcs)
    {
      var set = new MDSS.Set(Mojo.Util.isArray(toFilter) ? toFilter : [toFilter]);
      var len = columns.length;
      for(var i=0; i<len; i++)
      {
        var col = columns[i];
        if(set.contains(col.key))
        {
          col.includes = funcs;
        }
      }
    }
  }
});


Mojo.Meta.newClass('MDSS.AbstractAttribute', {

  IsAbstract : true,
  
  Constants : {
    DESCRIPTION_SUFFIX : '_desc'
  },
  
  Instance : {
  
    initialize :function(obj)
    {
      this._type = obj.type;
      this._dtoType = obj.dtoType;
      
      this._displayLabel = obj.displayLabel || MDSS.localize(obj.key);
      
      this._description = this._displayLabel; // default
      // first look for the description using the convention key+'_desc', used mainly for calculations
      if(Mojo.Util.isString(obj.key) && obj.key+MDSS.AbstractAttribute.DESCRIPTION_SUFFIX in MDSS.Localized)
      {
        this._description = MDSS.localize(obj.key+MDSS.AbstractAttribute.DESCRIPTION_SUFFIX);
      }
      // The description defaults to the display label if it is null or a length of 0
      else if(Mojo.Util.isString(obj.description) && obj.description.length > 0)
      {
        this._description = obj.description;
      }
      
      this._attributeName = obj.attributeName;
      this._entityAlias = obj.entityAlias || this._type;
      this._whereValues = [];
      this._isAggregate = obj.isAggregate || false;
      this._isTerm = false;
      this._isEnum = false;
      this._customDereference = obj.customDereference || null;
      
      this._searchType = obj.searchType || null;
      this._searchAttribute = obj.searchAttribute || null;
      
      if(obj.isTerm){
      	this._isTerm = obj.isTerm;
      }
  
      if(obj.isEnum){
      	this._isEnum = obj.isEnum;
      }
      
      if(obj.key)
      {
        this._key=obj.key;
      }
      else
      {
        this._genKey();
      }
    },
    
    hasCustomSearch : function()
    {
      return this._searchType != null && this._searchAttribute != null;
    },
    
    getSearchType : function()
    {
      return this._searchType;
    },
    
    getSearchAttribute : function()
    {
      return this._searchAttribute;
    },
    
    setTerm : function(isTerm)
    {
      this._isTerm = isTerm;
    },
    
    getTerm : function()
    {
      return this._isTerm;
    },
    
    _genKey : function()
    {
      this._key = this._type.replace(/\./g, '_')+'__'+this._attributeName;
    },
    
    /**
     * Unique key used with YUI Column
     * and as the user alias for the attribute
     * in a ValueObject.
     */
    getKey : function()
    {
      return this._key;
    },
  
    setKey : function(key)
    {
      this._key = key;
    },
  
    getType : function()
    {
      return this._type;
    },
  
    setType : function(type)
    {
      this._type = type;
    },
  
    getDtoType : function()
    {
      return this._dtoType;
    },
  
    getWhereValues : function()
    {
      return this._type;
    },
  
    getAttributeName : function()
    {
      return this._attributeName;
    },
    
    setAttributeName : function(attributeName)
    {
      this._attributeName = attributeName;
    },
  
    getDisplayLabel : function()
    {
      return this._displayLabel;
    },
    
    getDescription : function()
    {
      return this._description;
    },
  
    getEntityAlias : function()
    {
      return this._entityAlias;
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
    },
    
    /**
     * Returns a basic selectable object that represents this
     * attribute.
     */
    getSelectable : function(dereference, asClass)
    {
      var attrName = this._attributeName;
      if(this._isTerm && dereference)
      {
        var attribute = new MDSS.QueryXML.Sqlcharacter(this._entityAlias, attrName+'_displayLabel', this._key, this._displayLabel);
        return new MDSS.QueryXML.Selectable(attribute);
      }
      else if(this._isEnum && dereference)
      {
        var attribute = new MDSS.QueryXML.Sqlcharacter(this._entityAlias, attrName+'_displayLabel', this._key, this._displayLabel);
        return new MDSS.QueryXML.Selectable(attribute);
      }
      else if(this._customDereference && dereference)
      {
        attrName = attrName + this._customDereference;
      }
      else if(dereference)
      {
        attrName = attrName + '.displayLabel.currentValue';
        //attrName = attrName + '_displayLabel';
      }
  
      var attribute;
      if(asClass != null)
      {
        attribute = new asClass(this._entityAlias, attrName, this._key);
      }
      else
      {
        attribute = new MDSS.QueryXML.Attribute(this._entityAlias, attrName, this._key, this._displayLabel);
      }
      var selectable = new MDSS.QueryXML.Selectable(attribute);
      return selectable;
    }
  }
});

Mojo.Meta.newClass('MDSS.QueryBrowser', {

  Instance : {
  
    initialize : function(query, handler, attribute, fieldClass, fieldAttribute)
    {
      this._query = query;
      this._fieldClass = fieldClass;
      this._fieldAttribute = fieldAttribute;
      this._multiSelect = true;
      this._attribute = attribute;
      this._handler = handler;
      this._browser = new MDSS.OntologyBrowser(this._multiSelect, this._fieldClass, this._fieldAttribute);
      this._browser.setHandler(this.setTermsHandler, this);
      
      // Set of ids used for setting criteria before
      // the browser is opened (when loading a query).
      this._terms = new MDSS.Set();
    },
    
    addTerm : function(termId)
    {
      this._terms.set(termId);
    },
    
    getAttribute : function()
    {
      return this._attribute;
    },
    
    openBrowser : function()
    {
      if(this._browser.isRendered())
      {
        this._browser.reset();
        this._browser.show();
      }
      else
      {
        this._browser.render();
      }
     
      this._browser.setSelection(this._terms.values());
    },
    
    clearTerms : function()
    {
      // Remove all terms on the Query Config because they
      // will no longer need to be dereferenced.
      this._query._config.removeTerms(this._attribute.getKey());
      
      this._terms.clear();
    },
    
    getTerms : function()
    {
      return this._terms.values();
    },
    
    getDisplay : function(termId)
    {
      var display = this._browser.getDisplay(termId);
      if(display)
      {
        return display;
      }
      else
      {
        return this._query._config.getTermDisplay(this._attribute.getKey(), termId);
      }
    },
    
    /**
     * Sets the Term criteria on the query instance.
     */
    setTermsHandler: function(terms)
    {
      this._terms.clear(); // reset the saved terms

      // The terms could be a mix of TermViews and ValueObjects
      // so be careful to call the correct id and display methods.
      var idAttr = Mojo.$.dss.vector.solutions.ontology.Term.ID;
      var TermView = Mojo.$.dss.vector.solutions.ontology.TermView;
      var entryMap = new Mojo.Map();
      for(var i=0, len=terms.length; i<len; i++)
      {
        var id;
        var display;
        var term = terms[i];
        if(term instanceof TermView)
        {
          id = term.getTermId();
          display = MDSS.OntologyBrowser.formatLabelFromView(term);
        }
        else
        {
          id = term.getValue(idAttr);
          display = MDSS.OntologyBrowser.formatLabelFromValueObject(term);
        }
        
        this._terms.set(id);
        entryMap.put(id, {id:id, display:display});
      }
      
      
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(idsToRemove)
        {
          var that = this.that;

          var warnings = this.getWarnings();
          if(warnings.length === 1 && warnings[0] instanceof Mojo.$.dss.vector.solutions.ontology.NestedTermsWarning)
          {

            // remove all nested terms from criteria
            for(var i=0, len=idsToRemove.length; i<len; i++)
            {
              var id = idsToRemove[i];
              that._terms.remove(id);
              entryMap.remove(id);
            }
         
            var nestedTermWarning = warnings[0];
            var removed = Mojo.Util.toObject(nestedTermWarning.getNestedTerms()).join('<br />');
            new MDSS.ErrorModal(nestedTermWarning.getMessage()+'<br />'+removed);
          }
          
          that._query._config.addTerms(that._attribute.getKey(), that._terms.values());
        
          if(that._handler)
          {
            that._handler(that, entryMap.values());
          }
        }
        
      });
      Mojo.$.dss.vector.solutions.ontology.Term.checkForNestedTerms(request, this._terms.values());
    }
    
    
  }

});

Mojo.Meta.newClass('MDSS.BasicAttribute', {
  
  Extends: MDSS.AbstractAttribute,

  Instance : {
 
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  }
});

Mojo.Meta.newClass('MDSS.DependencyManager', {

  Instance :
  {
    initialize : function(){
      this._dependencies = {};
      this._enabled = true;
      this._transactions = 0;
      this._allTransactionsStartListeners = [];
      this._allTransactionsFinishListeners = [];
    },
    
    addAllTransactionsStartListener : function(handler)
    {
      this._allTransactionsStartListeners.push(handler);
    },

    addAllTransactionsFinishListener : function(handler)
    {
      this._allTransactionsFinishListeners.push(handler);
    },
    
    _fireAllListeners : function(listeners, triggerId, independents)
    {
      for(var i=0; i<listeners.length; i++)
      {
        listeners[i](triggerId, independents);
      }
    },
    
    enable : function() { this._enabled = true; },
    
    disable : function() { this._enabled = false; },
    
    notifyAll : function(e)
    {
      var id = e.target.id;
      var dArr = this._dependencies[id];
      if(!dArr || !this._enabled)
      {
        return;
      }
      
      if(this._transactions === 0)
      {
        this._fireAllListeners(this._allTransactionsStartListeners, id, dArr);
      }
      
      if(Mojo.Util.isArray(dArr))
      {
        for(var i=0; i<dArr.length; i++)
        {
          var d = dArr[i];
          var propagate = d.doesPropagate();
          if(!propagate && this._transactions > 1)
          {
            return;
          }
          
          this._transactions++;
          d.notify(e);
          this._transactions--;
        }
      }
      
      if(this._transactions === 0)
      {
        // uncheck any Select All boxes that don't contain checked siblings
        var checked =YAHOO.util.Selector.query('input[type="checkbox"].selectAllCheck:checked');
        for(var i=0; i<checked.length; i++)
        {
          var check = checked[i];
          var ul = check.parentNode.parentNode;
          var count = 0;
          var cSiblings = YAHOO.util.Selector.query('input[type="checkbox"]:checked:not(.selectAllCheck)', ul);
          if(cSiblings.length === 0)
          {
            check.checked = false;
          }
        }
        
        // We are completely done with all transactions!
        this._fireAllListeners(this._allTransactionsFinishListeners, id, dArr);
      }
   },
   
   _addEntry : function(ind, dep, type, excludes, propagate, name)
   {
     if(Mojo.Util.isArray(ind))
     {
       for(var i=0; i<ind.length; i++)
       {
         this._addEntry(ind[i], dep, type, excludes, propagate, name);
       }
       
       return;
     }     
     
     var indO = MDSS.Independent.factory(ind, excludes, propagate, name);
     var depO = MDSS.Dependent.factory(dep, type, name);
     
     indO.setDependent(depO);
     
     if(this._dependencies[ind])
     {
       this._dependencies[ind].push(indO);
     }
     else
     {
       this._dependencies[ind] = [indO];
     }
   },
   
   _getIds : function(input)
   {
     if(Mojo.Util.isArray(input))
     {
       return Mojo.Iter.map(input, function(i){ return Mojo.Util.isObject(i) ? i.key : i; });
     }
     else
     {
       return Mojo.Util.isObject(input) ? input.key : input;
     }
   },
   
   _processEntry : function(config, excludes)
   {
     var ind = config.independent;
     var dep = config.dependent;
     var type = config.type;
     var bidirectional = config.bidirectional;
     var propagate = config.propagate || false;
     var name = config.name || Mojo.Util.generateId(6);
     
     var indIds = this._getIds(ind);
     var depIds = this._getIds(dep);
     
     this._addEntry(indIds, depIds, type, excludes, propagate, name);
     
     if(bidirectional)
     {
       this._addEntry(depIds, indIds, type, excludes, propagate, name);
     }
   },
   
   includes : function(config)
   {
     this._processEntry(config, false);
   },
   
   excludes : function(config)
   {
     this._processEntry(config, true);
   }
   
  }
});

Mojo.Meta.newClass('MDSS.Dependent', {
  
  IsAbstract : true,

  Constants : {
    CHECKED : 'checked',
    UNCHECKED : 'unchecked',
    BOTH : 'both'
  },

  Instance : {
    initialize : function(type, name)
    {
      this._type = type;
      this._name = name;
    },
    
    getType : function()
    {
      return this._type;
    },

    doCheck : {
      IsAbstract: true
    },

    doUncheck : {
      IsAbstract: true
    }

  },

  Static : {
    factory : function(attribute, type, name)
    {
      return Mojo.Util.isArray(attribute) ? 
        new MDSS.GroupDependent(attribute, type, name) : new MDSS.SingleDependent(attribute, type, null, name);
    }  
  }

});

Mojo.Meta.newClass('MDSS.GroupDependent', {

  Extends : MDSS.Dependent,

  Instance : {
    initialize : function(attributes, type, name)
    {
      this.$initialize(type, name);

      this._group = [];
      for(var i=0; i<attributes.length; i++)
      {
        this._group.push(new MDSS.SingleDependent(attributes[i], type, this, name));
      }
    },

    doCheck : function(dependsOn)
    {
      for(var i=0; i<this._group.length; i++)
      {
        this._group[i].doCheck(dependsOn);
      }
    },

    doUncheck : function(dependsOn)
    {
      for(var i=0; i<this._group.length; i++)
      {
        this._group[i].doUncheck(dependsOn);
      }
    }
  }
});

Mojo.Meta.newClass('MDSS.SingleDependent', {

  Extends : MDSS.Dependent,

  Instance : {
    initialize : function(attribute, type, group, name)
    {
      this.$initialize(type);
      this._group = group || null;
      this._single = Mojo.Util.isString(attribute) ? document.getElementById(attribute) : attribute;
    },

    getGroup : function()
    {
      return this._group;
    },
    
    doCheck : function(dependsOn)
    {
      if(!this._single.checked)
      {
        this._single.click();
      }
    },

    doUncheck : function(dependsOn)
    {
      if(this._single.checked)
      {
        this._single.click();
      }
    }
  }
});

Mojo.Meta.newClass('MDSS.Independent',{
  
  IsAbstract : true,
  
  Instance : {
    initialize : function(excludes, propagate, name)
    {
      this._dependent = null;
      this._excludes = excludes;
      this._propagate = propagate;
      this._name = name;
    },
    
    getName : function()
    {
      return this._name;
    },
    
    doesPropagate : function()
    {
      return this._propagate;
    },
    
    doesExclude : function()
    {
      return this._excludes;
    },
    
    setDependent : function(d)
    {
      this._dependent = d;
    },

    getDependent : function()
    {
      return this._dependent;
    },
    
    notify : function(e)
    {
      var type = this._dependent.getType();
      var checked = this.isChecked();
      var excludes = this.doesExclude();
      if(checked && (type === MDSS.Dependent.CHECKED
        || type === MDSS.Dependent.BOTH))
      {
        if(excludes)
        {
          this._dependent.doUncheck(this);
        }
        else
        {
          this._dependent.doCheck(this);
        }
      }
      if(!checked && (type === MDSS.Dependent.UNCHECKED
        || type === MDSS.Dependent.BOTH))
      {
        if(excludes)
        {
          this._dependent.doCheck(this);
        }
        else
        {
          this._dependent.doUncheck(this);
        }
      }
    }
  },
  
  Static : {
    factory : function(attribute, excludes, propagate, name)
    {
      return Mojo.Util.isArray(attribute) ?
        new MDSS.GroupIndependent(attribute, excludes, propagate, name) : new MDSS.SingleIndependent(attribute, excludes, propagate, null, name);
    }
  }
});

Mojo.Meta.newClass('MDSS.SingleIndependent', {
  
  Extends : MDSS.Independent,
  
  Instance : {
    initialize : function(attribute, excludes, propagates, group, name)
    {
      this.$initialize(excludes, propagates, name);
      this._group = group || null;
      this._attribute = attribute;
    },
    
    isChecked : function()
    {
      return document.getElementById(this._attribute).checked;
    },
    
    toString : function()
    {
      return "I: "+this._attribute;
    }
  }

  
});

Mojo.Meta.newClass('MDSS.GroupIndependent', {
  
  Extends : MDSS.Independent,
  
  Instance : {
  initialize : function(attributes, excludes, propagates, name)
  {
    this.$initialize(excludes, propagates, name);
    
    this._group = [];
    for(var i=0; i<attributes.length; i++)
    {
      this._group.push(new MDSS.SingleIndependent(attributes[i], excludes, propagates, this, name));
    }
  }
}

});