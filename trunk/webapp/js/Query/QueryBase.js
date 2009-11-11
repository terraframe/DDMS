/**
 * Base class to support functionality for all query use cases.
 */
Mojo.Meta.newClass('MDSS.QueryBase', {

  IsAbstract : true,

  Constants : {
    GEO_ATTRIBUTES : 'geoAttributes',
    TARGET : 'target'
  },

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
      
      this._browsers = {};
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
    _attachBrowser : function(elementId, handler, attribute, fieldClass, fieldAttribute, multipleSelect)
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
    		      
      var browser = new MDSS.QueryBrowser(this, bound, attribute, fieldClass, fieldAttribute, multipleSelect);
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
          label: MDSS.localize(range),
        });
      }
      else
      {
        var column = this._queryPanel.getColumn(range.toLowerCase());
        this._queryPanel.removeColumn(column);
        this._dateGroupSelectables[range] = null;
      }
  
    },
  
    _getExportXLSAction: {
      IsAbstract : true
    },
  
    _getExportCSVAction: {
      IsAbstract : true
    },
  
    _getExportReportAction: {
      IsAbstract : true
    },
  
    _getReportQueryType: {
      IsAbstract : true
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
      }, this);
  
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
          isAggregate:true,
        }, this);
  
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
      var selectedUniversals = this._config.getSelectedUniversals(); // FIXME GEO
      this._selectSearch.setSelectedUniversals(selectedUniversals); // FIXME GEO
  
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
        savedSearchId : savedSearchId,
        onComplete : function(){},
        onSuccess: function(savedSearchView){
  
          this.thisRef._resetToDefault();
          this.thisRef._config = new MDSS.Query.Config(savedSearchView.getConfig()); 
          
          
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
      view.setThematicLayer('');
      /* FIXME MAP
      view.setThematicLayer(this._queryPanel.getCurrentThematicLayer());
      */ 
      view.setQueryType(queryType);
    },
  
    /**
     * Subclasses must override this to return the controller method
     * that will be executed to save a search.
     */
    _getQueryType: {
      IsAbstract : true
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
  
      var allPaths = Mojo.Util.getValues(this._allPathQueries);
      for(var i=0; i<allPaths.length; i++)
      {
        queryXML.addEntity(allPaths[i]);
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
      var namespace = attributeKey+'_';
      
      var geoEntityQuery = this._geoEntityTypes[namespace+type];
      if(!Mojo.Util.isObject(geoEntityQuery))
      {
        this._geoEntityTypes[namespace+type] = new MDSS.QueryXML.Entity(type, entityAlias);
      }
  
      // use the type name and lowercase it so it adheres to attribute naming conventions
      var typeName = type.substring(type.lastIndexOf('.')+1).toLowerCase();
      
      var addColumn = Mojo.Util.bind(this, function(geoAttr, selectableMap) {
      
        // namespace the column using the attributeKey because different attributes
        // can add the same universal columns (e.g., Country [work], Country [home]).
        var columnKey = attributeKey.replace(/\./g, '_')+'__'+typeName+'_'+geoAttr;
        
        if(this._geoEntitySelectables[columnKey] == null)
        {
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
          this._geoEntitySelectables[columnKey] = sel;
        }
      });
      
      addColumn(geoEntityView.getEntityNameMd().getName());
      addColumn(geoEntityView.getGeoIdMd().getName());
      
    },
    
    _removeUniversalColumns : function(attributeKey)
    {
      // remove all universal entities from the query
      var keys = Mojo.Util.getKeys(this._geoEntityTypes);
      Mojo.Iter.forEach(keys, function(key){
        
        // only delete GeoEntity queries for this attribute
        if(key.indexOf(attributeKey) !== -1)
        {
          delete this._geoEntityTypes[key];
        }
      }, this);
      
      // reme all geo entity name and geo id columns
      keys = Mojo.Util.getKeys(this._geoEntitySelectables);
      Mojo.Iter.forEach(keys, function(key){
      
        // The column may not exist because the selectable
        // was used for criteria and not selection
        // only delete GeoEntity queries for this attribute
        if(key.indexOf(attributeKey.replace(/\./g, '_')) !== -1)
        {
          var column = this._queryPanel.getColumn(key);
          if(column)
          {
            this._queryPanel.removeColumn(column);
          }
          
          delete this._geoEntitySelectables[key];
        }
      }, this);
    },
  
    /**
     * Uses the given GeoEntityView objects to add
     * restrictions to the GeoEntity query.
     */
    _hideHandler : function(criteriaEntities, selectedUniversals)
    {
      var currentAttribute = this._getCurrentGeoAttribute();
    
      this._queryPanel.clearAllRecords();
  
      // clear any prior selected universals
      this._config.clearSelectedUniversals(currentAttribute);
  
      // remove existing columns
      this._removeUniversalColumns(currentAttribute);
  
      // add new columns
      for(var i=0; i<selectedUniversals.length; i++)
      {
        var universal = selectedUniversals[i];
  
        var construct = Mojo.Meta.findClass(universal);
        var geoEntity = new construct();
        var geoEntityView = this._selectSearch._copyEntityToView(geoEntity);
  
        this._addUniversalEntity(currentAttribute, geoEntityView);
  
        this._config.addSelectedUniversal(currentAttribute, geoEntityView.getEntityType());
      }
  
      this._queryPanel.setAvailableThematicLayers(selectedUniversals);
  
      this._criteriaEntities[currentAttribute] = criteriaEntities;
      if(criteriaEntities.length > 0)
      {
        var entityAlias = this.ALL_PATHS+'_'+currentAttribute; // Unique namespace per attribute 
      
        var allPaths = new MDSS.QueryXML.Entity(this.ALL_PATHS, entityAlias);
  
        var or = new MDSS.QueryXML.Or();
        for(var i=0; i<criteriaEntities.length; i++)
        {
          var geoEntityView = criteriaEntities[i];
  
          var attribute = new MDSS.QueryXML.Attribute(entityAlias, 'parentGeoEntity');
          var selectable = new MDSS.QueryXML.Selectable(attribute);
          var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, geoEntityView.getGeoEntityId());
  
          or.addCondition(currentAttribute+'__'+geoEntityView.getGeoEntityId()+'_'+i, geoIdCondition);
        }
        
        var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);
        allPaths.setCondition(compositeCondition);

        this._allPathQueries[currentAttribute] = allPaths;
      }
      else
      {
        delete this._allPathQueries[currentAttribute];
      }
      
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
    
    addGeoAttributes : function(attributes)
    {
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
      this._queryPanel.addQueryItem({
        html: html + ' <img id="'+MDSS.QueryBase.TARGET+'" class="clickable" src="./imgs/icons/world.png"/>',
        id: "areaItem"
      });
      
      this._config.setGeoAttributes(attributeKeys);
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


Mojo.Meta.newClass('MDSS.AbstractAttribute', {

  IsAbstract : true,
  
  Instance : {
  
    initialize :function(obj)
    {
      this._type = obj.type;
      this._dtoType = obj.dtoType;
      this._displayLabel = obj.displayLabel;
      this._attributeName = obj.attributeName;
      this._entityAlias = obj.entityAlias || this._type;
      this._whereValues = [];
      this._isAggregate = obj.isAggregate || false;
      this._isTerm = false;
  
      if(obj.key)
      {
        this._key=obj.key;
      }
      else
      {
        this._genKey();
      }
    },
    
    setTerm : function(isTerm)
    {
      this._isTerm = isTerm;
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
  
    getDisplayLabel : function()
    {
      return this._displayLabel;
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
      else if(dereference)
      {
        attrName = attrName + '.displayLabel.currentValue';
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
  
    initialize : function(query, handler, attribute, fieldClass, fieldAttribute, multiSelect)
    {
      this._query = query;
      this._fieldClass = fieldClass;
      this._fieldAttribute = fieldAttribute;
      this._multiSelect = multiSelect || false;
      this._attribute = attribute;
      this._handler = handler;
      
      this._browser = new MDSS.OntologyBrowser(this._multiSelect, this._fieldClass, this._fieldAttribute);
      this._browser.setHandler(this.setTermsHandler, this);
      
      // Array of ids used for setting criteria before
      // the browser is opened (when loading a query).
      this._terms = [];
    },
    
    addTerm : function(termId)
    {
      this._terms.push(termId);
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
     
      this._browser.setSelection(this._terms);
    },
    
    clearTerms : function()
    {
      // Remove all terms on the Query Config because they
      // will no longer need to be dereferenced.
      this._query._config.removeTerms(this._attribute.getKey());
      
      this._terms = [];
    },
    
    getTerms : function()
    {
      return this._terms;
    },
    
    getDisplay : function(termId)
    {
      return this._query._config.getTermDisplay(this._attribute.getKey(), termId);
    },
    
    /**
     * Sets the Term criteria on the query instance.
     */
    setTermsHandler: function(terms)
    {
      this._terms = Mojo.Iter.map(terms, function(term){
        return term.getTermId(); 
      });
      this._query._config.addTerms(this._attribute.getKey(), this._terms);
    
      if(this._handler)
      {
        this._handler(this, terms);
      }
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