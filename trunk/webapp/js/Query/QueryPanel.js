/**
 * Class to create a Query Panel.
 */
MDSS.QueryPanel = function(queryClass, queryPanelId, mapPanelId, config)
{

  this._queryClass = queryClass;

  var minWidth = 1250;
  var minHeight = 500;

  var pWidth =  (window.innerWidth - 30) > minWidth ? (window.innerWidth - 30) : minWidth;
  var pHeight = (window.innerHeight - 160) > minHeight ? (window.innerHeight -160) : minHeight;

  this._queryLayout = new YAHOO.widget.Layout(queryPanelId, {
    height: pHeight,
    width: pWidth,
    units: [
        { position: 'top', height: 40, resize: false, body: '', gutter: '2' },
        { position: 'left', width: 220 , resize: true, body: '', gutter: '0 5 0 2', scroll: true },
        { position: 'bottom', height: 40, body: '', gutter: '2' },
        { position: 'center', body: '<div id="'+this.QUERY_DATA_TABLE+'"></div><div id="'+this.PAGINATION_SECTION+'"></div>', gutter: '0 2 0 0', scroll: false},
        { position: 'right', width: 150, body: '<div style="margin-left: 10px" id="'+this.QUERY_SUMMARY+'"></div>', resize: true, scroll: true, gutter: '0 5 0 2'}
    ]
  });

  this._pWidth = pWidth;
  this._pHeight = pHeight;

  this._config = config;

  this._queryItems = [];

  this._dataTable = null;

  // references to date range DOM elements
  this._startDate = null;
  this._endDate = null;

  // references to the panel units
  this._qTopUnit = null;
  this._qLeftUnit = null;
  this._qBottomUnit = null;
  this._qCenterUnit = null;
  this._qRightUnit = null;

  this._querySummary = null;

  // reference to the reusable modal for file uploading
  this._uploadModal = null;

  // array of objects with each element representing a
  // key/value pair of SavedSearch.ID/SavedSearch.QUERYNAME
  this._availableQueries = [];
  this._queryList = null;

  // Obj representing a SavedSearchView
  this._currentSavedSearch = null;

  this._map = null;

  // map between header ids (TH tags) and context menu builder functions
  this._headerMenuBuilders = {};

  // map between query list entries (LI tags) and context menu builder functions
  this._queryMenuBuilders = {};
  
  this.waitForRefresh = false;
  this._columnBatch = [];
  this._deleteBatch = [];
};

MDSS.QueryPanel.prototype = {
    
  RUN_QUERY_BUTTON : 'runQueryBtn',

  QUERY_ITEMS : "queryItemsList",

  AVAILABLE_QUERY_LIST : "availableQueryList",

  QUERY_DATA_TABLE : "queryDataTable",

  PAGINATION_SECTION : "paginationSection",

  DATE_RANGE_DIV : "dateRange",

  DATE_GROUP_ID : "dateGroupSelection",

  START_DATE_RANGE : "startDateRange",
  
  START_DATE_RANGE_CHECK : "start_date_range",

  END_DATE_RANGE : "endDateRange",
  
  END_DATE_RANGE_CHECK : "end_date_range",

  GEO_ENTITY_PANEL_LIST : "geoEntityPanelList",

  COLUMNS_LIST : "columnsList",

  QUERY_SUMMARY : "querySummary",
  

  EDIT_VARIABLE_STYLE : "editVariableStyle",

  EDIT_DEFAULT_STYLE : "editDefaultStyle",

  /**
   *
   */
  setCurrentSavedSearch : function(savedSearch)
  {
    this._currentSavedSearch = savedSearch;
  },

  /**
   * Returns the current saved search. The
   * value will be null if there isn't a saved
   * search.
   */
  getCurrentSavedSearch : function()
  {
    return this._currentSavedSearch;
  },

  /**
   * Updates the column label on both the YUI column object
   * and the listing in the right panel query summary.
   */
  updateColumnLabel : function(key, prepend)
  {
    var li = document.getElementById(key+"_summary");
    var prependEl = li.firstChild;
    prependEl.innerHTML = (prepend === '') ? '' : '('+prepend+') ';
  },

  getSelectedDisplayLabel : function(key)
  {
    // grab the first element which holes an possible aggregate function info
    // and append its contents to that of its sibling that contains the selectable
    // display.
    var li = document.getElementById(key+"_summary");
    var display = li.firstChild.textContent+li.firstChild.nextSibling.textContent;
    return display;
  },

  /**
   * Adds a column to the query summary in
   * the right panel.
   */
  _addSelectedColumn : function(column)
  {
    var ul = document.getElementById(this.COLUMNS_LIST);

    var li = document.createElement('li');
    li.id = column.getKey()+"_summary";

    if(column.attribute){
    	var whereFilters = column.attribute._whereValues.filter(function(a){return a.checked;}).map(
    			function(a){return('<li id= "'+a.uuid+'_summary" >'+a.text+'</li>');
    			});
    	li.innerHTML = "<span></span>"+ column.label + '<ul id="'+column.getKey()+'_whereValues">'+whereFilters.join('')+'</ul>';
  	}else{
  		li.innerHTML = "<span></span>"+ column.label + '<ul id="'+column.getKey()+'_whereValues"></ul>';
  	}

    ul.appendChild(li);
  },

  clearWhereCriteria : function(key)
  {
    var whereValues = document.getElementById(key+"_whereValues");
    if(whereValues)
    {
      whereValues.innerHTML = '';
    }
  },

  /**
   * Adds a WHERE criteria label to the attribute with
   * the given key.
   */
  addWhereCriteria : function(key, value, display)
  {
    var id = key+'-'+value+'-where';
    var li = document.getElementById(id);
    if(li)
    {
      return;
    }

    var whereValues = document.getElementById(key+"_whereValues");
    li = document.createElement('li');
    li.id = id;
    li.innerHTML = display;

    whereValues.appendChild(li);
  },

  /**
   * Removes a single entry in the WHERE criteria for
   * an attribute with the given key.
   */
  removeWhereCriteria : function(key, value)
  {
    var id = key+'-'+value+'-where';
    var li = document.getElementById(id);
    if(li)
    {
      li.parentNode.removeChild(li);
    }
  },

  /**
   * Removes a column from the query summary in
   * the right panel.
   */
  _removeSelectedColumn : function(column)
  {
    var ul = document.getElementById(this.COLUMNS_LIST);
    var li = document.getElementById(column.getKey()+"_summary");
    ul.removeChild(li);
  },

  /**
   * Adds the list of GeoEntity objects to the list
   * of criteria entities for the given attribute.
   */
  addSelectedGeoEntities : function(attributeKey, displayLabel, geoEntities)
  {
    var parent = document.getElementById(this.GEO_ENTITY_PANEL_LIST);
        
    var ulId = attributeKey+'_criteriaEntitiesUl';
    var spanId = attributeKey+'_criteriaEntitiesSpan';
    
    var ul = document.getElementById(ulId);
    var span = document.getElementById(spanId);
    if(geoEntities.length > 0)
    {
      // To avoid an ugly diff procedure, just wipe the previous node clean
      // if it already exists.
      if(ul)
      {
        ul.innerHTML = '';
      }
      else
      {
        ul = document.createElement('ul');
        ul.id = ulId; 
        
        span = document.createElement('span');
        span.id = spanId;
        span.innerHTML = displayLabel;
        
        parent.appendChild(span);
        parent.appendChild(ul);
      }
  
      var frag = document.createDocumentFragment();
      for(var i=0; i<geoEntities.length; i++)
      {
        var geoEntityView = geoEntities[i];
  
        var li = document.createElement('li');
        li.innerHTML = MDSS.AbstractSelectSearch.formatDisplay(geoEntityView);
  
        frag.appendChild(li);
      }
  
      ul.appendChild(frag);
    }
    else if(ul)
    {
      parent.removeChild(ul);
      parent.removeChild(span);
    }
  },

  /**
   * Adds an available query id and name
   * as an option to the select list.
   */
  addAvailableQuery : function(obj)
  {
    this._availableQueries.push(obj);

    // update the live list
    if(this._queryList != null)
    {
      var option = document.createElement('option');
      YAHOO.util.Dom.setAttribute(option, 'value', obj.id);
      option.innerHTML = obj.name;

      this._queryList.appendChild(option);

      var el = document.getElementById(this._queryList.get('id'));

      el.selectedIndex = el.options.length-1;
    }
  },

  /**
   * Returns the start date element wrapped
   * in a YUI Element object.
   */
  getStartDate : function()
  {
    return this._startDate;
  },

  getStartDateCheck : function()
  {
    return this._startDateRangeCheck;
  },

  /**
   * Returns the end date element wrapped
   * in a YUI Element object.
   */
  getEndDate : function()
  {
    return this._endDate;
  },

  getEndDateCheck : function()
  {
    return this._endDateRangeCheck;
  },

  /**
   * Adds the date range div to the top panel.
   */
  _buildDateRange : function()
  {
    var dateRange = new YAHOO.util.Element(document.createElement('div'));
    dateRange.set('id', this.DATE_RANGE_DIV);

    var startLabel = document.createElement('span');
    startLabel.innerHTML = MDSS.Localized.Query.Start_Date;

    this._startDate = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._startDate, 'type', 'text');
    this._startDate.id = this.START_DATE_RANGE;
    YAHOO.util.Dom.addClass(this._startDate, 'DatePick');
    YAHOO.util.Event.addListener(this._startDate, "blur", this.disableDateCheck, null, this);

    this._startDateRangeCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._startDateRangeCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(this._startDateRangeCheck, 'id', this.START_DATE_RANGE_CHECK);
    YAHOO.util.Dom.setAttribute(this._startDateRangeCheck, 'disabled', true);

    var endLabel = document.createElement('span');
    endLabel.innerHTML = MDSS.Localized.Query.End_Date;

    this._endDate = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._endDate, 'type', 'text');
    this._endDate.id = this.END_DATE_RANGE;
    YAHOO.util.Dom.addClass(this._endDate, 'DatePick');
    YAHOO.util.Event.addListener(this._endDate, "blur", this.disableDateCheck, null, this);

    this._endDateRangeCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._endDateRangeCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(this._endDateRangeCheck, 'id', this.END_DATE_RANGE_CHECK);
    YAHOO.util.Dom.setAttribute(this._endDateRangeCheck, 'disabled', true);

    var toggleDatesSpan = document.createElement('span');
    toggleDatesSpan.innerHTML = MDSS.Localized.Toggle_Show;


  
    // add the date fields
    if(this._queryClass._dateAttribs){
    	this._queryClass._buildDateAttributesSelect(dateRange);
    }
    
    dateRange.appendChild(startLabel);
    dateRange.appendChild(this._startDateRangeCheck);
    dateRange.appendChild(this._startDate);
    dateRange.appendChild(endLabel);
    dateRange.appendChild(this._endDateRangeCheck);
    dateRange.appendChild(this._endDate);

    var dateGroupLabel = document.createElement('span');
    dateGroupLabel.innerHTML = MDSS.localize("Snap_To_Nearest");

    this._dateGroupBy = document.createElement('select');
    this._dateGroupBy.id = this.DATE_GROUP_ID;
    var options = [''];
    var keys = [''];
    options = options.concat(Mojo.Util.getValues(MDSS.QueryXML.DateGroupOpts));
    keys = keys.concat(Mojo.Util.getKeys(MDSS.QueryXML.DateGroupOpts));

    for(var j=0; j<options.length; j++)
    {
      var optionEl = document.createElement('option');
      optionEl.innerHTML = options[j];
      YAHOO.util.Dom.setAttribute(optionEl, 'value', keys[j]);
      //YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, attribute, this);
      this._dateGroupBy.appendChild(optionEl);
    }
    dateRange.appendChild(dateGroupLabel);
    dateRange.appendChild(this._dateGroupBy);
    
    
    //add geo entity chooser    
    if(this._queryClass._geoEntityAttribs)
    {
    	this._queryClass._addGeoAttributes(dateRange);
    }
    

    var body = new YAHOO.util.Element(this._qTopUnit.body);
    body.appendChild(dateRange);

  },
  
  disableDates : function(disableStart, disableEnd)
  {
    if(disableStart !== null)
    {
      this._startDateRangeCheck.disabled = disableStart;
    }
    
    if(disableEnd !== null)
    {
      this._endDateRangeCheck.disabled = disableEnd;
    }
  },
  
  getDateGroupBy : function()
  {
    return this._dateGroupBy;
  },

  /**
   */
  disableDateCheck : function()
  {
  	if(this._startDate.value.length == 0)
  	{
      if(this._startDateRangeCheck.checked)
      {
      	this._startDateRangeCheck.click();
      }
      this._startDateRangeCheck.disabled = true;
  	}
  	else
  	{
      this._startDateRangeCheck.disabled = false;
  	}

  	if(this._endDate.value.length == 0)
  	{
      if(this._endDateRangeCheck.checked)
      {
      	this._endDateRangeCheck.click();
      }
      this._endDateRangeCheck.disabled = true;
  	}
  	else
  	{
      this._endDateRangeCheck.disabled = false;
  	}
  },


  /**
   * Builds the query items/attributes and adds them
   * to the left panel.
   */
  _buildQueryItems : function()
  {
    var ul = new YAHOO.util.Element(document.createElement('ul'));
    ul.set('id', this.QUERY_ITEMS);

    for(var i=0; i<this._queryItems.length; i++)
    {
      var queryItem = this._queryItems[i];

      // create the item
      var li = document.createElement('li');
      var liE = new YAHOO.util.Element(li);

      if(Mojo.Util.isString(queryItem.html))
      {
        li.innerHTML = queryItem.html;
      }
      else
      {
      	li.appendChild(queryItem.html);
      }

      // add click event handler
      if(Mojo.Util.isObject(queryItem.onclick))
      {
        liE.on('click', queryItem.onclick.handler, queryItem.onclick.obj);
      }

      liE.set('id', queryItem.id);

      ul.appendChild(li);

      // add the builder function to create an entry
      // specific context menu
      if(Mojo.Util.isFunction(queryItem.menuBuilder))
      {
        this._queryMenuBuilders[queryItem.id] = queryItem.menuBuilder;
        liE.addClass('contextMenuContainer');
      }
    }

    var body = new YAHOO.util.Element(this._qLeftUnit.body);
    body.appendChild(ul);

    // add context menu for the query item list
    var menu = new YAHOO.widget.ContextMenu(this.QUERY_ITEMS+"_menu", {
      trigger:this.QUERY_ITEMS,
      lazyload:true,
      zindex:9999
    });

    menu.subscribe("beforeShow", this._queryMenuBeforeShow, {thisRef:this});
    menu.subscribe("triggerContextMenu", this._queryMenuTrigger, {thisRef:this});
  },

  /**
   * Should be called after QueryPanel has been rendered.
   */
  _postRender : function()
  {
    this._qTopUnit = this._queryLayout.getUnitByPosition('top');
    this._qLeftUnit = this._queryLayout.getUnitByPosition('left');
    this._qBottomUnit = this._queryLayout.getUnitByPosition('bottom');
    this._qCenterUnit = this._queryLayout.getUnitByPosition('center');
    this._qRightUnit = this._queryLayout.getUnitByPosition('right');

    this._buildButtons();

    this._buildDateRange();

    this._buildQueryItems();

    this._buildContentGrid([]);

    this._buildQuerySummary();

    YAHOO.util.Event.on(this.PAGINATION_SECTION, 'click', this._paginationHandler, null, this);

    // let the query panels perform their own post-render logic
    if(Mojo.Util.isFunction(this._config.postRender))
    {
      this._config.postRender.call(this._queryClass);
    }
  },

  /**
   * Builds the right side of the query panel with information
   * about the query, including the selected columns and restricting
   * geo entities.
   */
  _buildQuerySummary : function()
  {
    var html = '<h3>'+MDSS.Localized.Columns+'</h3><ul id="'+this.COLUMNS_LIST+'"></ul>';
    html += '<h3>'+MDSS.Localized.Selected_Entities + '</h3><div id="'+this.GEO_ENTITY_PANEL_LIST+'"></div>';

    var querySummary = document.getElementById(this.QUERY_SUMMARY);
    querySummary.innerHTML = html;
  },

  _exportXLS : function(e, obj)
  {
    if(Mojo.Util.isFunction(this._config.exportXLS))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportXLS.apply(this._queryClass, Mojo.Util.getValues(obj));
    }
  },

  _exportCSV : function(e, obj)
  {
    if(Mojo.Util.isFunction(this._config.exportCSV))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportCSV.apply(this._queryClass, Mojo.Util.getValues(obj));
    }
  },

  _exportReport : function(e, obj)
  {
    if(Mojo.Util.isFunction(this._config.exportReport))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportReport.apply(this._queryClass, Mojo.Util.getValues(obj));
    }
  },

  /**
   * Builds the form to request to download a CSV list
   * of the current saved query.
   */
  _buildCSVForm : function()
  {
    var form = document.createElement('form');
    YAHOO.util.Dom.setAttribute(form, 'method', 'POST');
    YAHOO.util.Dom.setAttribute(form, 'target', 'messageFrame');

    var xmlInput = document.createElement('textarea');
    YAHOO.util.Dom.setAttribute(xmlInput, 'name', 'queryXML');

    var config = document.createElement('input');
    YAHOO.util.Dom.setAttribute(config, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(config, 'name', 'config');
    
    var queryClassInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(queryClassInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(queryClassInput, 'name', 'queryClass');
    
    var searchIdInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'name', 'savedSearchId');

    var obj = {
      form: form,
      xmlInput: xmlInput,
      config : config,
      searchIdInput : searchIdInput,
      queryClassInput: queryClassInput
    };

    var exportCSVButton = document.createElement('input');
    YAHOO.util.Dom.setAttribute(exportCSVButton, 'type', 'button');
    YAHOO.util.Dom.setAttribute(exportCSVButton, 'value', MDSS.Localized.Export_CSV);
    YAHOO.util.Dom.addClass(exportCSVButton, 'queryButton');
    YAHOO.util.Event.on(exportCSVButton, 'click', this._exportCSV, obj, this);

    form.appendChild(xmlInput);
    form.appendChild(config);
    form.appendChild(searchIdInput);
    form.appendChild(queryClassInput);

    document.getElementById('CSVFormContainer').appendChild(form);

    return exportCSVButton;
  },

  /**
  * Builds the form to request to download a pdf of the saved report
  */
 _buildReportForm : function()
 {
   var form = document.createElement('form');
   YAHOO.util.Dom.setAttribute(form, 'method', 'POST');
   YAHOO.util.Dom.setAttribute(form, 'target', 'messageFrame');

   var xmlInput = document.createElement('textarea');
   YAHOO.util.Dom.setAttribute(xmlInput, 'name', 'queryXML');

   var config = document.createElement('input');
   YAHOO.util.Dom.setAttribute(config, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(config, 'name', 'config');

   var searchIdInput = document.createElement('input');
   YAHOO.util.Dom.setAttribute(searchIdInput, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(searchIdInput, 'name', 'savedSearchId');

/* Obsolete: query type is grabbed from SavedSearch given by savedSearchId param
   var queryTypeInput = document.createElement('input');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'name', 'queryType');

   var queryTypeInput = document.createElement('input');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'name', 'type');
*/
   
   var obj = {
     form: form,
     xmlInput: xmlInput,
     config : config,
     searchIdInput : searchIdInput
//     queryTypeInput : queryTypeInput
   };

   var exportReportButton = document.createElement('input');
   YAHOO.util.Dom.setAttribute(exportReportButton, 'type', 'button');
   YAHOO.util.Dom.setAttribute(exportReportButton, 'value', MDSS.Localized.Export_Report);
   YAHOO.util.Dom.addClass(exportReportButton, 'queryButton');
   YAHOO.util.Event.on(exportReportButton, 'click', this._exportReport, obj, this);

   form.appendChild(xmlInput);
   form.appendChild(config);
   form.appendChild(searchIdInput);
//   form.appendChild(queryTypeInput);

   document.getElementById('ReportFormContainer').appendChild(form);

   return exportReportButton;
 },


  /**
   * Builds the form to do a synchronous post to the server to
   * download a Excel file.
   */
  _buildXLSForm : function()
  {
    var form = document.createElement('form');
    YAHOO.util.Dom.setAttribute(form, 'method', 'POST');
    YAHOO.util.Dom.setAttribute(form, 'target', 'messageFrame');

    var xmlInput = document.createElement('textarea');
    YAHOO.util.Dom.setAttribute(xmlInput, 'name', 'queryXML');

    var config = document.createElement('input');
    YAHOO.util.Dom.setAttribute(config, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(config, 'name', 'config');
    
    var queryClassInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(queryClassInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(queryClassInput, 'name', 'queryClass');

    var searchIdInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'name', 'savedSearchId');

    var obj = {
      form: form,
      xmlInput: xmlInput,
      config : config,
      searchIdInput : searchIdInput,
      queryClassInput: queryClassInput
    };

    var exportXLSButton = document.createElement('input');
    YAHOO.util.Dom.setAttribute(exportXLSButton, 'type', 'button');
    YAHOO.util.Dom.setAttribute(exportXLSButton, 'value', MDSS.Localized.Excel_Export_Nav);
    YAHOO.util.Dom.addClass(exportXLSButton, 'queryButton');
    YAHOO.util.Event.on(exportXLSButton, 'click', this._exportXLS, obj, this);

    form.appendChild(xmlInput);
    form.appendChild(config);
    form.appendChild(searchIdInput);
    form.appendChild(queryClassInput);

    document.getElementById('XLSFormContainer').appendChild(form);

    return exportXLSButton;
  },

  _uploadTemplateOnSubmit : function()
  {
    var input = document.getElementById('savedSearchIdInput');
    input.value = this._currentSavedSearch != null ? this._currentSavedSearch.getSavedQueryId() : '';

    return true;
  },

  /**
   * Action to upload a template file.
   */
  _uploadTemplate : function()
  {
    if(this._uploadModal == null)
    {
      var formId = 'templateUploadForm';
      var action = 'dss.vector.solutions.query.QueryController.uploadTemplate.mojo';

      var html = MDSS.Localized.File_Upload_Status+":<br />";
      html += "<iframe name='templateIframe' id='templateIframe' style='height:65px; width:350px; margin-bottom: 15px'></iframe>";
      html += "<form action='"+action+"' enctype='multipart/form-data' target='templateIframe' id='"+formId+"' method='post'>";
      html += "<input type='hidden' name='savedSearchId' id='savedSearchIdInput' value='' />";
      html += "<input type='file' name='templateFile' /><br />";
      html += "<input type='submit' name='import' value='"+MDSS.Localized.Submit+"' />"
      html += "</form>";

      this._uploadModal = new YAHOO.widget.Panel("uploadTemplateModal", {
        width:"400px",
        height: "400px",
        fixedcenter:true,
        close: true,
        draggable:false,
        zindex:8,
        modal:true,
        visible:true
      });

      // wrap content in divs
      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>'+MDSS.Localized.Upload_Template+'</h3><hr />';
      outer.appendChild(header);

      var contentDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);

      this._uploadModal.setBody(outer);
      this._uploadModal.render(document.body);

      YAHOO.util.Event.on(formId, 'submit', this._uploadTemplateOnSubmit, null, this);
    }
    else
    {
      this._uploadModal.show();
    }
  },

  /**
   * Builds the buttons to perform acions in the QueryPanel.
   */
  _buildButtons : function()
  {
    var uploadTemplate = new YAHOO.util.Element(document.createElement('input'));
    uploadTemplate.set('type', 'button');
    uploadTemplate.set('value', MDSS.Localized.Upload_Template);
    uploadTemplate.set('id', "uploadTemplateButton");
    uploadTemplate.addClass('queryButton');
    uploadTemplate.on('click', this._uploadTemplate, {}, this);

    var saveButton = new YAHOO.util.Element(document.createElement('input'));
    saveButton.set('type', 'button');
    saveButton.set('value', MDSS.Localized.Query.Save);
    saveButton.set('id', "saveQueryButton");
    saveButton.addClass('queryButton');
    saveButton.on('click', this._saveQuery, {}, this);

    var saveAsButton = new YAHOO.util.Element(document.createElement('input'));
    saveAsButton.set('type', 'button');
    saveAsButton.set('value', MDSS.Localized.Query_Save_As);
    saveAsButton.set('id', "saveAsQueryButton");
    saveAsButton.addClass('queryButton');
    saveAsButton.on('click', this._saveQueryAs, {}, this);

    var deleteButton = new YAHOO.util.Element(document.createElement('input'));
    deleteButton.set('type', 'button');
    deleteButton.set('value', MDSS.localize("Delete_Query"));
    deleteButton.set('id', this.LOAD_QUERY_BUTTON);
    deleteButton.addClass('queryButton');
    deleteButton.on('click', this._deleteQuery, {}, this);

    this._queryList = new YAHOO.util.Element(document.createElement('select'));
    this._queryList.set('id', this.AVAILABLE_QUERY_LIST);
    this._queryList.addClass('queryList');
    var defaultOption = document.createElement('option');
    this._queryList.appendChild(defaultOption);
    for(var i=0; i<this._availableQueries.length; i++)
    {
      var obj = this._availableQueries[i];

      var option = document.createElement('option');
      YAHOO.util.Dom.setAttribute(option, 'value', obj.id);
      option.innerHTML = obj.name;

      this._queryList.appendChild(option);
    }
    this._queryList.on('change', this._loadQuery, {}, this);
    
    var exportReportButton = this._buildReportForm();

    var exportCSVButton = this._buildCSVForm();

    var exportXLSButton = this._buildXLSForm();

    var runButton = new YAHOO.util.Element(document.createElement('input'));
    runButton.set('type', 'button');
    runButton.set('value', MDSS.Localized.Query.Run);
    runButton.set('id', this.RUN_QUERY_BUTTON);
    runButton.addClass('queryButton');
    runButton.on('click', this._executeQuery, {}, this);

    var rightDiv = new YAHOO.util.Element(document.createElement('div'));
    rightDiv.setStyle('float', 'right');
    rightDiv.appendChild(uploadTemplate);
    rightDiv.appendChild(exportReportButton);
    rightDiv.appendChild(exportCSVButton);
    rightDiv.appendChild(exportXLSButton);
    rightDiv.appendChild(runButton);

    var leftDiv = new YAHOO.util.Element(document.createElement('div'));
    leftDiv.setStyle('float', 'left');
    leftDiv.appendChild(this._queryList);
    
    leftDiv.appendChild(saveButton);
    leftDiv.appendChild(saveAsButton);
    leftDiv.appendChild(deleteButton);

    var qBottom = new YAHOO.util.Element(this._qBottomUnit.body);
    qBottom.appendChild(leftDiv);
    qBottom.appendChild(rightDiv);
  },

  /**
   * Checks if the context menu has been triggered for
   * a TH tag.
   */
  _tableMenuTrigger : function(a, b, c)
  {
    var oTarget = this.contextEventTarget;

    if(c.thisRef._getHeader(oTarget) == null)
    {
      this.cancel();
    }
  },

  /**
   * Checks if the context menu has been trigged for
   * an LI tag.
   */
  _queryMenuTrigger : function(a, b, c)
  {
    var oTarget = this.contextEventTarget;

    if(c.thisRef._getListEntry(oTarget) == null)
    {
      this.cancel();
    }
  },

  /**
   * Gets the header element from the given event target.
   * Null is returned if the header element is not found.
   */
  _getHeader : function(oTarget)
  {
    var nodeName = oTarget.nodeName.toUpperCase();

    if(nodeName === 'TH')
    {
      return oTarget;
    }
    else
    {
      // check he nodes parents for a TH
      var parent = YAHOO.util.Dom.getAncestorByTagName(oTarget, "TH");
      if(parent != null)
      {
        return parent;
      }
    }

    return null; // nothing found
  },

  /**
   * Gets the list element from the given event target.
   * Null is returned if the header element is not found.
   */
  _getListEntry : function(oTarget)
  {
    var nodeName = oTarget.nodeName.toUpperCase();

    if(YAHOO.util.Dom.hasClass(nodeName, 'contextMenuContainer'))
    {
      return oTarget;
    }
    else
    {
      // check the node's parents for a TH
      var parent = YAHOO.util.Dom.getAncestorByClassName(oTarget, "contextMenuContainer");
      if(parent != null)
      {
        return parent;
      }
    }

    return null; // nothing found
  },

  /**
   * Modifies the table context menu
   * depending on the state of the QueryPanel.
   */
  _tableMenuBeforeShow : function(a, b, c)
  {
    this.clearContent();

    // get the header id
    var header = c.thisRef._getHeader(this.contextEventTarget);

    // add items specific to the header
    if(header != null)
    {
      var column = c.thisRef._dataTable.getColumn(header);
      var builder = c.thisRef._headerMenuBuilders[column != null ? column.getKey() : ''];
      var menuItems = builder != null ? builder(column) : [];
      this.addItems(menuItems);
    }
    else
    {
      this.addItems([]);
    }

    this.render();
  },

  /**
   * Modifies the query items context menu
   * depending on the state of the QueryPanel.
   */
  _queryMenuBeforeShow : function(a, b, c)
  {
    // this.contextEventTarget will be null for menu
    // dimensions > 1. Let render as normal.
    var cet = this.contextEventTarget
    if(cet != null)
    {
      // get the li
      var liEntry = c.thisRef._getListEntry(cet);

      this.clearContent();
      // add items specific to the list entry

      if(liEntry != null)
      {
        var builder = c.thisRef._queryMenuBuilders[liEntry.id];
        var menuItems = builder != null ? builder(liEntry, cet) : [];
        this.addItems(menuItems);
      }
      else
      {
        this.addItems([]);
      }

      this.render();
    }
  },

  /**
   * Builds the content grid to contain the query criteria.
   */
  _buildContentGrid : function(columns)
  {
    // build the DataSource (required)
    var dataSource;
    if(this._dataTable)
    {
      dataSource = this._dataTable.getDataSource();
      this._dataTable.destroy();
      this._dataTable = null;
    }
    else
    {
      dataSource = new YAHOO.util.DataSource([]);
      dataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;

      dataSource.responseSchema = {
        fields: []
      };
    }

    this._dataTable = new YAHOO.widget.DataTable(this.QUERY_DATA_TABLE, columns, dataSource,{draggableColumns:true,resizeableColumns:true});

    this._dataTable.render();

    this._dataTable.subscribe("columnReorderEvent", function(){
    //can handle column order here
    }, this, true);


    // add context menu to table
    var menu = new YAHOO.widget.ContextMenu(this.QUERY_DATA_TABLE+"_menu", {
      trigger:this.QUERY_DATA_TABLE,
      lazyload:true,
      zindex:9999
    });

    menu.subscribe("beforeShow", this._tableMenuBeforeShow, {thisRef:this});
    menu.subscribe("triggerContextMenu", this._tableMenuTrigger, {thisRef:this});
  },

  /**
   * Adds a new Query Item to the left column of the panel. The object
   * must be in the following format (R == required and O == optional):
   * {
   *   id (R): [string],
   *   html (R): [string] or [Element],
   *   onclick (O): {
   *     handler (R): [Function],
   *     obj (O): [Object]
   *   },
   *   menuBuilder (O): [Function]
   * }
   */
  addQueryItem : function(menuObj)
  {
    this._queryItems.push(menuObj);
  },

  /**
   * Inserts a new column into the query pane.
   * Returns an updated column object (the column
   * argument will be stale).
   */
  insertColumn : function(column, menuBuilder)
  {
    var attrib = column.attribute;
  	column.resizeable = true;
  	
  	if(this.waitForRefresh)
  	{
  	  this._columnBatch.push(column.getDefinition());
  	}
  	else
  	{
  	  column = this._dataTable.insertColumn(column);
  	}
  	
  	column.attribute = attrib;

    if(Mojo.Util.isFunction(menuBuilder))
    {
      // add mapping between column and menuItems
      this._headerMenuBuilders[column.getKey()] = menuBuilder;
    }

    this._addSelectedColumn(column);

    return column;
  },
  
  /**
   * Sorts the column batch in the order given.
   */
  orderColumns : function(order)
  {
    var columns = {};
    for(var i=0; i<this._columnBatch.length; i++)
    {
      var col = this._columnBatch[i];
      columns[col.key] = col;
    }
    
    this._columnBatch = [];
    for(var i=0; i<order.length; i++)
    {
      var c = columns[order[i]];
      if(c)
      {
        this._columnBatch.push(c);
      }
    }
  },
  
  refreshBatch : function()
  {
    if(this._columnBatch.length > 0 || this._deleteBatch.length > 0)
    {
      var all = this.getColumnSet().getDefinitions().concat(this._columnBatch);
      if(this._deleteBatch.length > 0)
      {
        var toExclude = new MDSS.Set(this._deleteBatch);
        var temp = [];
        for(var i=0; i<all.length; i++)
        {
          var c = all[i];
          if(!toExclude.contains(c.key))
          {
            temp.push(c);
          }
        }
        
        all = temp;
      }
      
      this._buildContentGrid(all);
    }
    
    this._columnBatch = [];
    this._deleteBatch = [];
    this.waitForRefresh = false;
  },

  /**
   * Removes the specified column from the table.
   */
  removeColumn : function(column)
  {
    if(this.waitForRefresh)
    {
      this._deleteBatch.push(column.getKey());
    }
    else
    {
      this._dataTable.removeColumn(column);
    }

    this._removeSelectedColumn(column);
  },

  /**
   * Gets the column with given column reference or key or id.
   * Returns null if the column doesn't exist.
   */
  getColumn : function(column)
  {
    return this._dataTable.getColumn(column);
  },

  /**
   *
   */
  getColumnSet : function()
  {
    return this._dataTable.getColumnSet();
  },

  /**
   * Sets the row data on the data table.
   */
  setRowData : function(rowData)
  {
    this._dataTable.addRows(rowData);
  },

  /**
   * Clears all records in the table.
   */
  clearAllRecords : function()
  {
    this._dataTable.deleteRows(0, this._dataTable.getRecordSet().getLength());
  },

  /**
   * Loads all saved queries for this QueryPanel.
   */
  _loadQuery : function()
  {
    if(Mojo.Util.isFunction(this._config.loadQuery))
    {
      var queries = document.getElementById(this.AVAILABLE_QUERY_LIST);

      // ignore the default, empty option
      var savedSearchId = queries.options[queries.selectedIndex].value;
      if(savedSearchId)
      {
      	this._config.loadQuery.call(this._queryClass, savedSearchId);
      }
      else
      {
        this._queryClass._dm.disable();
      	this._queryClass._resetToDefault();
      	this._queryClass._dm.enable();
        this._queryClass._loadDefaultSearch();
      }
    }
  },
  
  _doDeleteQuery : function(savedSearchId, queries)
  {
    var request = new MDSS.Request( {
      queries : queries,
      thisRef : this,
      selectedIndex : queries.selectedIndex,
      onSuccess : function(deletedRow) {                
        this.queries.options[this.selectedIndex].selected = false;
        this.queries.options[0].selected = true;
        this.queries.options[this.selectedIndex] = null;
        
        this.thisRef._queryClass._resetToDefault();
        this.thisRef._queryClass._loadDefaultSearch();
      }
    });
    Mojo.Facade.deleteEntity(request, savedSearchId);
  },

  /**
   * Deletes a query.
   */
  _deleteQuery : function()
  {
    var queries = document.getElementById(this.AVAILABLE_QUERY_LIST);
    // ignore the default, empty option
    var savedSearchId = queries.options[queries.selectedIndex].value;
    if(savedSearchId)
    {
      var doDel = Mojo.Util.bind(this, this._doDeleteQuery, savedSearchId, queries);
      MDSS.confirmModal(MDSS.Localized.Confirm_Delete_Query, doDel, function(){});
    }
  },
  
  /**
   * Saves a query.
   */
  _saveQuery : function()
  {
  	 var queries = document.getElementById(this.AVAILABLE_QUERY_LIST);
     // ignore the default, empty option
     var savedSearchId = queries.options[queries.selectedIndex].value;
     // if this query has not been saved yet then open save as
     if(savedSearchId)
     {
    	 if(Mojo.Util.isFunction(this._config.saveQuery))
    	 {
    		 this._config.saveQuery.call(this._queryClass);
    	 }
     }
     else
     {
    	 this._saveQueryAs();
     }
  },

  /**
   * Saves a new query.
   */
  _saveQueryAs : function()
  {
    if(Mojo.Util.isFunction(this._config.saveQueryAs))
    {
      this._config.saveQueryAs.call(this._queryClass);
    }
  },

  /**
   * Executes the Query by calling the user-defined
   * handler.
   */
  _executeQuery : function()
  {
    if(Mojo.Util.isFunction(this._config.executeQuery))
    {
      this._config.executeQuery.call(this._queryClass, true);
    }
  },

  /**
   * Creates new pagination settings with the given configuration.
   */
  setPagination : function(count, pageNumber, pageSize)
  {
    var pagination = new MDSS.Pagination(pageNumber, pageSize, count);
    var pages = pagination.getPages();

    var section = document.getElementById(this.PAGINATION_SECTION);
    section.innerHTML = '';


    var frag = document.createDocumentFragment();

    for(var i=0; i<pages.length; i++)
    {

      var page = pages[i];

      var span = document.createElement('span');
      YAHOO.util.Dom.addClass(span, 'page');

      if(page.isLeft())
      {
        span.innerHTML = '...';
      }
      else if(page.isRight())
      {
        span.innerHTML = '...';
      }
      else if(page.isCurrentPage())
      {
        span.innerHTML = page.getPageNumber();
        YAHOO.util.Dom.addClass(span, 'currentPage');
      }
      else
      {
        span.innerHTML = page.getPageNumber();
      }

      frag.appendChild(span);
      
    }
    var countSpan = document.createElement('span');
    YAHOO.util.Dom.addClass(countSpan, 'resultCount');
    var max = (pageNumber * pageSize);
    if(max > count) max = count;
    countSpan.innerHTML = " " + (((pageNumber-1) * pageSize)+1)+ "-" +max+" "+MDSS.localize('Of')+" "+ count;
    frag.appendChild(countSpan);

    section.appendChild(frag);
  },

  clearPagination : function()
  {
    var section = document.getElementById(this.PAGINATION_SECTION);
    section.innerHTML = '';
  },
  
  _paginationHandler : function(e)
  {
    if(e.target.nodeName === 'SPAN' && Mojo.Util.isFunction(this._config.paginationHandler))
    {
      var pageNumber = e.target.innerHTML;
      this._config.paginationHandler.call(this._queryClass, pageNumber);
    }
  },

  /**
   * Renders the QueryPanel and its sub-components.
   */
  render : function()
  {
    this._queryLayout.render();
    
    this._postRender();
  }
};

MDSS.Pagination = function(pageNumber, pageSize, count)
{
  this._pageNumber = pageNumber;
  this._pageSize = pageSize;
  this._count = count;
  this._pages = [];
  this.MAX_DISPLAY_PAGES = 10;
  this.calculate();
};

MDSS.Pagination.prototype = {
  calculate : function()
  {
    // can't paginate an empty result set
    if (this._count === 0)
    {
      return;
    }

    // Calculate the number of links to display
    if(this._pageSize == 0 || this._pageNumber == 0)
    {
      this._pageSize = this._count;
      this._pageNumber = 1;
    }

    var totalPages = parseInt(Math.ceil(this._count / this._pageSize ));

    var l = Math.max(this._pageNumber - 4, 1);
    var u = Math.min(this._pageNumber + 4, totalPages);
    var lowerBound = Math.max(1, Math.min(this._pageNumber-4, u-this.MAX_DISPLAY_PAGES));
    var upperBound = Math.min(Math.max(this._pageNumber+4, l+this.MAX_DISPLAY_PAGES), totalPages);

    if (lowerBound != 1)
    {
      // Generate the first page
      this._pages.push(new MDSS.Pagination.Page(false, 1));

      // Generate the marker page
      if (lowerBound != 2)
      {
        var page = new MDSS.Pagination.Page();
        page.markLeft();
        this._pages.push(page);
      }
    }

    for (var i = lowerBound; i <= upperBound; i++)
    {
      this._pages.push(new MDSS.Pagination.Page( ( this._pageNumber == i ), i));
    }

    if (upperBound != totalPages)
    {
      // Generate marker page
      if (upperBound != totalPages - 1)
      {
        var page = new MDSS.Pagination.Page();
        page.markRight();
        this._pages.push(page);
      }

      // Generate last page
      this._pages.push(new MDSS.Pagination.Page(false, totalPages));
    }
  },

  getPages : function()
  {
    return this._pages;
  }
};

MDSS.Pagination.Page = function(isCurrent, pageNumber)
{
  this._isCurrent = Mojo.Util.isBoolean(isCurrent) ? isCurrent : false;
  this._pageNumber = Mojo.Util.isNumber(pageNumber) ? pageNumber : 0;
  this._isLeft = false;
  this._isRight = false;
};

MDSS.Pagination.Page.prototype = {
  markLeft : function() { this._isLeft = true; },

  markRight : function() { this._isRight = true; },

  isLeft : function() { return this._isLeft; },

  isRight : function() { return this._isRight; },

  isCurrentPage : function() { return this._isCurrent; },

  getPageNumber : function() { return this._pageNumber; },

};