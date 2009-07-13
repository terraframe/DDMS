/**
 * Class to create a Query Panel.
 */
MDSS.QueryPanel = function(queryClass, queryPanelId, mapPanelId, config)
{
  
  this._queryClass = queryClass;

  var minWidth = 1270;
  var minHeight = 500;

  var pWidth =  (window.innerWidth - 30) > minWidth ? (window.innerWidth - 30) : minWidth;
  var pHeight = (window.innerHeight * 0.6) > minHeight ? (window.innerHeight * 0.6) : minHeight;


  this._queryLayout = new YAHOO.widget.Layout(queryPanelId, {
    height: pHeight,
    width: pWidth,
    units: [
        { position: 'top', height: 40, resize: false, body: '', gutter: '2' },
        { position: 'left', width: 220 , resize: true, body: '', gutter: '0 5 0 2', scroll: true },
        { position: 'bottom', height: 40, body: '', gutter: '2' },
        { position: 'center', body: '<div id="'+this.QUERY_DATA_TABLE+'"></div><div id="'+this.PAGINATION_SECTION+'"></div>', gutter: '0 2 0 0', scroll: true },
        { position: 'right', width: 150, body: '<div style="margin-left: 10px" id="'+this.QUERY_SUMMARY+'"></div>', resize: true, scroll: true, gutter: '0 5 0 2'}
    ]
  });

  this._mapLayout = new YAHOO.widget.Layout(mapPanelId, {
    height: pHeight,
    width: pWidth,
    units: [
        { position: 'left', width: 300, resize: false, body: '', gutter: '0 5 0 2', scroll: true },
        { position: 'bottom', height: 40, body: '', gutter: '2' },
        { position: 'center', body: '<div id="'+this.MAP_CONTAINER+'"></div>', gutter: '0 2 0 0', scroll: true }
    ]
  });

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

  this._mLeftUnit = null;
  this._mBottomUnit = null;
  this._mCenterUnit = null;

  this._querySummary = null;

  // reference to the reusable modal for file uploading
  this._uploadModal = null;

  // array of objects with each element representing a
  // key/value pair of SavedSearch.ID/SavedSearch.QUERYNAME
  this._availableQueries = [];
  this._queryList = null;

  // Obj representing a SavedSearchView
  this._currentSavedSearch = null;

  // The current ThematicVarible object that is used for mapping.
  this._currentThematicVariable = null;

  // the current layers in the map. If this._currentSavedSearch
  // is not null, then these layers belong to that SavedSearch.
  this._layers = [];

  this._map = null;

  // map between header ids (TH tags) and context menu builder functions
  this._headerMenuBuilders = {};

  // map between query list entries (LI tags) and context menu builder functions
  this._queryMenuBuilders = {};

  // The button that adds a new layer when clicked
  this._addLayerButton = null;

  // a map of ThematicVariable objects
  this._thematicVariables = {};
  this._thematicLayers = [];
};

MDSS.QueryPanel.prototype = {

  CATEGORY_LIST : "categoryList",

  MAP_CONTAINER : "mapContainer",

  QUERY_ITEMS : "queryItemsList",

  THEMATIC_VARIABLES_LIST : "thematicVariablesList",

  DEFINED_LAYERS_LIST : "definedLayersList",

  AVAILABLE_QUERY_LIST : "availableQueryList",

  AVAILABLE_LAYERS_LIST : "availableLayersList",

  QUERY_DATA_TABLE : "queryDataTable",

  PAGINATION_SECTION : "paginationSection",

  DATE_RANGE_DIV : "dateRange",

  DATE_GROUP_ID : "dateGroupSelection",

  START_DATE_RANGE : "startDateRange",

  END_DATE_RANGE : "endDateRange",

  GEO_ENTITY_PANEL_LIST : "geoEntityPanelList",

  COLUMNS_LIST : "columnsList",

  QUERY_SUMMARY : "querySummary",

  THEMATIC_LAYERS_SELECT : "thematicLayersSelect",
  
  EDIT_VARIABLE_STYLE : "editVariableStyle",
  
  EDIT_DEFAULT_STYLE : "editDefaultStyle",

  /**
   *
   */
  setCurrentSavedSearch : function(savedSearch)
  {
    this._currentSavedSearch = savedSearch;
    this._resetThematicOptions();
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
   * Sets the current ThematicVariable instance.
   */
  setCurrentThematicVariable : function(thematicVar)
  {
    this._currentThematicVariable = thematicVar;
  },

  /**
   * Returns the curren ThematicVariable instance.
   */
  getCurrentThematicVariable : function(thematicVar)
  {
    return this._currentThematicVariable;
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
   * Adds the list of GeoEntit objects to the list
   * of selected GeoEntities.
   */
  addSelectedGeoEntities : function(geoEntities)
  {
    var ul = document.getElementById(this.GEO_ENTITY_PANEL_LIST);

    var frag = document.createDocumentFragment();
    for(var i=0; i<geoEntities.length; i++)
    {
      var geoEntityView = geoEntities[i];

      var li = document.createElement('li');
      li.innerHTML = geoEntityView.getEntityName() + ' ('+geoEntityView.getGeoId()+')';

      frag.appendChild(li);
    }

    ul.innerHTML = '';
    ul.appendChild(frag);
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

    this._startDateRangeCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._startDateRangeCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(this._startDateRangeCheck, 'id', 'START_DATE_RANGE');

    var endLabel = document.createElement('span');
    endLabel.innerHTML = MDSS.Localized.Query.End_Date;

    this._endDate = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._endDate, 'type', 'text');
    this._endDate.id = this.END_DATE_RANGE;
    YAHOO.util.Dom.addClass(this._endDate, 'DatePick');

    this._endDateRangeCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(this._endDateRangeCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(this._endDateRangeCheck, 'id', 'END_DATE_RANGE');

    var toggleDatesSpan = document.createElement('span');
    toggleDatesSpan.innerHTML = MDSS.Localized.Toggle_Show;

    // add the date fields

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
    options = options.concat(Mojo.util.getValues(MDSS.QueryXML.DateGroupOpts));
    keys = keys.concat(Mojo.util.getKeys(MDSS.QueryXML.DateGroupOpts));

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

    var body = new YAHOO.util.Element(this._qTopUnit.body);
    body.appendChild(dateRange);

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

      if(Mojo.util.isString(queryItem.html))
      {
        li.innerHTML = queryItem.html;
      }
      else
      {
      	li.appendChild(queryItem.html);
      }

      // add click event handler
      if(Mojo.util.isObject(queryItem.onclick))
      {
        liE.on('click', queryItem.onclick.handler, queryItem.onclick.obj);
      }

      liE.set('id', queryItem.id);

      ul.appendChild(li);

      // add the builder function to create an entry
      // specific context menu
      if(Mojo.util.isFunction(queryItem.menuBuilder))
      {
        this._queryMenuBuilders[queryItem.id] = queryItem.menuBuilder;
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

  getQueryTopUnit : function() { return this._qTopUnit; },

  getQueryLeftUnit : function() { return this._qLeftUnit; },

  getQueryBottomUnit : function() { return this._qBottomUnit; },

  getQueryCenterUnit : function() { return this._qCenterUnit; },

  getQueryRightUnit : function() { return this._qRightUnit; },

  getMapLeftUnit : function() { return this._mLeftUnit; },

  getMapBottomUnit : function() { return this._mBottomUnit; },

  getMapCenterUnit : function() { return this._mCenterUnit; },

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

    this._mLeftUnit = this._mapLayout.getUnitByPosition('left');
    this._mBottomUnit = this._mapLayout.getUnitByPosition('bottom');
    this._mCenterUnit = this._mapLayout.getUnitByPosition('center');

    this._buildButtons();

    this._buildDateRange();

    this._buildQueryItems();

    this._buildContentGrid();

    this._buildQuerySummary();

    YAHOO.util.Event.on(this.PAGINATION_SECTION, 'click', this._paginationHandler, null, this);
    
    this._buildUniversalList();

    // let the query panels perform their own post-render logic
    if(Mojo.util.isFunction(this._config.postRender))
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
    html += '<h3>'+MDSS.Localized.Selected_Entities + '</h3><ul id="'+this.GEO_ENTITY_PANEL_LIST+'"></ul>';

    var querySummary = document.getElementById(this.QUERY_SUMMARY);
    querySummary.innerHTML = html;
  },
  
  /**
   * Sets the selected thematic layer. Note that this should be called
   * after this.setAvailableThematicLayers().
   */
  setSelectedThematicLayer : function(layer)
  {
    var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
    var options = select.options;
    
    for(var i=0; i<options.length; i++)
    {
    
      var option = options[i];
      if(option.value === layer)
      {
        select.selectedIndex = i;
        break;
      }
    }
  },

  setAvailableThematicLayers : function(layers)
  {
    this._thematicLayers = layers;
    this._resetThematicOptions();
  },

  _resetThematicOptions : function()
  {
    var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
    if(select)
    {
      var oldSelected = select.selectedIndex != -1 ? select.options[select.selectedIndex].value : null;
      var selectIndex = 0;
      select.innerHTML = '<option value="">&nbsp;</value>';
      for(var i=0; i<this._thematicLayers.length; i++)
      {
        var layer = this._thematicLayers[i];

        var option = document.createElement('option');
        option.value = layer;
        option.innerHTML = MDSS.GeoTreeSelectables.types[layer].label;

        select.appendChild(option);

        if(oldSelected != null && oldSelected === layer)
        {
          selectIndex = i;
        }
      }
      
      select.selectedIndex = selectIndex;
    }
  },

  _thematicLayerSelected : function(e)
  {
    if(Mojo.util.isFunction(this._config.thematicLayerSelected))
    {
      var select = e.target;
      var option = select.options[select.selectedIndex];
      
      this._config.thematicLayerSelected.call(this._queryClass, option.value);
    } 
  },
  
  toggleThematicSettings : function(enabled)
  {
    document.getElementById(this.EDIT_DEFAULT_STYLE).disabled = !enabled;
    document.getElementById(this.EDIT_VARIABLE_STYLE).disabled = !enabled;
  },
  
  /**
   * Returns the currently selected thematic layer in the drop down list
   * of available thematic layers.
   */
  getCurrentThematicLayer : function()
  {
    var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
    return select.options[select.selectedIndex].value;
  },

  /**
   * Builds a list of possible universal layers
   * that can be selected.
   */
  _buildUniversalList : function()
  {
    // list thematic variables
    var thematicDiv = new YAHOO.util.Element(document.createElement('div'));

    var thematicLayerDiv = document.createElement('div');

    var html = MDSS.Localized.Thematic.Layer+"<br />";
    html += "<select style='margin: 3px 0px; min-width: 220px;' id='"+this.THEMATIC_LAYERS_SELECT+"'>";
    html += "<option value=''></option>";
    html += "</select>";
    thematicLayerDiv.innerHTML = html;

    // edit default style
    var editDefaultStyle = new YAHOO.util.Element(document.createElement('input'));
    editDefaultStyle.set('type', 'button');
    editDefaultStyle.set('id', this.EDIT_DEFAULT_STYLE);
    editDefaultStyle.set('disabled', true);
    editDefaultStyle.set('value', MDSS.Localized.Thematic.Edit_Default_Style);
    editDefaultStyle.on('click', function(e){
      var search = this._currentSavedSearch;
      var layerId = search != null ? search.getThematicLayerId() : '';
      this._editDefinedLayer(e, {layerId:layerId});
    }, null, this);

    var editVariableStyles = new YAHOO.util.Element(document.createElement('input'));
    editVariableStyles.set('type', 'button');
    editVariableStyles.set('id', this.EDIT_VARIABLE_STYLE);
    editVariableStyles.set('disabled', true);
    editVariableStyles.set('value', MDSS.Localized.Thematic.Edit_Variable_Styles);
    editVariableStyles.on('click', this._editVariableStyles, null, this);

    thematicDiv.appendChild(thematicLayerDiv);
    thematicDiv.appendChild(editDefaultStyle);
    thematicDiv.appendChild(editVariableStyles);

    var availableSpan = document.createElement('span');
    YAHOO.util.Dom.setStyle(availableSpan, 'display', 'block');
    availableSpan.innerHTML = MDSS.Localized.Available_Layers;

    // this data structure is defined by
    // the GeoEntity tree for use case 111.
    // (We're just stealing it for our own use here.)
    var types = MDSS.GeoTreeSelectables.types;
    var typeNames = Mojo.util.getKeys(types);
    typeNames.sort();

    var universalListDiv = new YAHOO.util.Element(document.createElement('div'));
    YAHOO.util.Dom.addClass(universalListDiv, 'universalList');
    var layers = document.createElement('select');
    YAHOO.util.Dom.setAttribute(layers, 'id', this.AVAILABLE_LAYERS_LIST);

    for(var i=0; i<typeNames.length; i++)
    {
      var type = typeNames[i];
      var displayLabel = types[type].label;

      var option = document.createElement('option');
      YAHOO.util.Dom.setAttribute(option, 'value', type);
      YAHOO.util.Dom.setAttribute(option, 'id', type+"_available");
      option.innerHTML = displayLabel;

      layers.appendChild(option);
    }

    universalListDiv.appendChild(availableSpan);
    universalListDiv.appendChild(layers);

    // add container for user defined layers
    var layersListDiv = document.createElement('div');
    YAHOO.util.Dom.addClass(layersListDiv, 'definedLayers');

    var definedSpan = document.createElement('span');
    definedSpan.innerHTML = MDSS.Localized.Defined_Layers;

    var ul = document.createElement('ul');
    YAHOO.util.Dom.setAttribute(ul, 'id', this.DEFINED_LAYERS_LIST);

    var ulDiv = document.createElement('div');
    ulDiv.appendChild(ul);

    layersListDiv.appendChild(definedSpan);
    layersListDiv.appendChild(ulDiv);

    // add button for new layer
    this._addLayerButton = new YAHOO.util.Element(document.createElement('input'));
    this._addLayerButton.set('type', 'button');
    this._addLayerButton.set('value', MDSS.Localized.Add);
    this._addLayerButton.on('click', this._addLayer, null, this);

    universalListDiv.appendChild(this._addLayerButton);

    var wrapper = new YAHOO.util.Element(document.createElement('div'));
    YAHOO.util.Dom.addClass(wrapper, 'layersWrapper');
    wrapper.appendChild(thematicDiv);
    wrapper.appendChild(universalListDiv);
    wrapper.appendChild(layersListDiv);

    // clear any prior HTML
    this._mLeftUnit.body.innerHTML = '';
    var body = new YAHOO.util.Element(this._mLeftUnit.body);
    body.appendChild(wrapper);

    var thematicSelect = document.getElementById(this.THEMATIC_LAYERS_SELECT);
    thematicSelect.selectedIndex = 0;
    
    YAHOO.util.Event.on(thematicSelect, 'change', this._thematicLayerSelected, null, this);
  },

  /**
   * Adds a thematic variable to the map.
   */
  _editVariableStyles : function(e)
  {
    if(Mojo.util.isFunction(this._config.editVariableStyles))
    {
      this._config.editVariableStyles.call(this._queryClass);
    }
  },

  /**
   * Called when a user clicks the checkbox next to the date ranges.
   */
  _toggleDates : function(e)
  {
    if(Mojo.util.isFunction(this._config.toggleDates))
    {
      this._config.toggleDates.call(this._queryClass, e);
    }
  },

  /**
   * Called when a user makes a request to edit a layer.
   */
  _editDefinedLayer : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.editLayer))
    {
      this._config.editLayer.call(this._queryClass, obj.layerId);
    }
  },

  _deleteDefinedLayer : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.deleteLayer))
    {
      this._config.deleteLayer.call(this._queryClass, obj.layerId, obj.type);
    }
  },
  
  /**
   * Removes all currently defined layers (from the DOM, it doesn't
   * delete them), and also re-enables all disabled options in the
   * available layers list.
   */
  clearAllDefinedLayers : function()
  {
    var ul = document.getElementById(this.DEFINED_LAYERS_LIST);
    ul.innerHTML = '';
    
    var select = document.getElementById(this.AVAILABLE_LAYERS_LIST);
    var options = select.options;
    for(var i=0; i<options.length; i++)
    {
      options[i].disabled = false;
    }
  },
  
  removeDefinedLayer : function(layerId, type)
  {
    var li = document.getElementById(layerId+'_defined');
    li.parentNode.removeChild(li);    
    
    // enable the option in the available list
    document.getElementById(type+"_available").disabled = false;
  },

  /**
   * Adds a user defined layer to the right panel
   * of the map screen. The entry for the universal
   * with the given type is removed as an "available"
   * layer to add.
   */
  addDefinedLayer : function(layerId, type)
  {
    var li = document.createElement('li');
    YAHOO.util.Dom.setAttribute(li, 'id', layerId+"_defined")

    var layerObj = {
      layerId: layerId,
      type: type
    }

    var del = document.createElement('img');
    YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
    YAHOO.util.Event.on(del, 'click', this._deleteDefinedLayer, layerObj, this);

    var edit = document.createElement('img');
    YAHOO.util.Dom.setAttribute(edit, 'src', 'imgs/icons/wand.png');
    YAHOO.util.Event.on(edit, 'click', this._editDefinedLayer, layerObj, this);

    var check = document.createElement('input');
    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(check, 'value', layerId);

    var span = document.createElement('span');
    var types = MDSS.GeoTreeSelectables.types; // defined by 111
    span.innerHTML = types[type].label;

    li.appendChild(del);
    li.appendChild(edit);
    li.appendChild(check);
    li.appendChild(span);

    var ul = document.getElementById(this.DEFINED_LAYERS_LIST);
    ul.appendChild(li);

    // disable the option in the available list (can't have duplicate layers)
    document.getElementById(type+"_available").disabled = true;
  },

  /**
   * Returns all selected layers to include in the map.
   */
  getSelectedLayers : function()
  {
    var layers = [];

    var ul = document.getElementById(this.DEFINED_LAYERS_LIST);
    var checks = YAHOO.util.Selector.query('input', ul);
    for(var i=0; i<checks.length; i++)
    {
      var check = checks[i];
      if(check.checked)
      {
        layers.push(check.value);
      }
    }

    return layers;
  },

  /**
   * Handler for when a new layer is added.
   */
  _addLayer : function()
  {
    if(Mojo.util.isFunction(this._config.addLayer))
    {
      var layersList = document.getElementById(this.AVAILABLE_LAYERS_LIST);
      var options = layersList.options;
      var selected = options[layersList.selectedIndex];

      if(selected && selected.value)
      {
        var type = selected.value;
        this._config.addLayer.call(this._queryClass, type);
      }
    }
  },

  _exportXLS : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.exportXLS))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportXLS.apply(this._queryClass, Mojo.util.getValues(obj));
    }
  },

  _exportCSV : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.exportCSV))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportCSV.apply(this._queryClass, Mojo.util.getValues(obj));
    }
  },

  _exportReport : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.exportReport))
    {
      // pass in the form element so the calling process
      // can modify its action.
      this._config.exportReport.apply(this._queryClass, Mojo.util.getValues(obj));
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

    var searchIdInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'name', 'savedSearchId');

    var obj = {
      form: form,
      xmlInput: xmlInput,
      config : config,
      searchIdInput : searchIdInput
    };

    var exportCSVButton = document.createElement('input');
    YAHOO.util.Dom.setAttribute(exportCSVButton, 'type', 'button');
    YAHOO.util.Dom.setAttribute(exportCSVButton, 'value', MDSS.Localized.Export_CSV);
    YAHOO.util.Dom.addClass(exportCSVButton, 'queryButton');
    YAHOO.util.Event.on(exportCSVButton, 'click', this._exportCSV, obj, this);

    form.appendChild(xmlInput);
    form.appendChild(config);
    form.appendChild(searchIdInput);

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

   var queryTypeInput = document.createElement('input');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'name', 'queryType');
   
   var queryTypeInput = document.createElement('input');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'type', 'hidden');
   YAHOO.util.Dom.setAttribute(queryTypeInput, 'name', 'type');   

   var obj = {
     form: form,
     xmlInput: xmlInput,
     config : config,
     searchIdInput : searchIdInput,
     queryTypeInput : queryTypeInput
   };

   var exportReportButton = document.createElement('input');
   YAHOO.util.Dom.setAttribute(exportReportButton, 'type', 'button');
   YAHOO.util.Dom.setAttribute(exportReportButton, 'value', MDSS.Localized.Export_Report);
   YAHOO.util.Dom.addClass(exportReportButton, 'queryButton');
   YAHOO.util.Event.on(exportReportButton, 'click', this._exportReport, obj, this);

   form.appendChild(xmlInput);
   form.appendChild(config);
   form.appendChild(searchIdInput);
   form.appendChild(queryTypeInput);

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

    var searchIdInput = document.createElement('input');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'type', 'hidden');
    YAHOO.util.Dom.setAttribute(searchIdInput, 'name', 'savedSearchId');

    var obj = {
      form: form,
      xmlInput: xmlInput,
      config : config,
      searchIdInput : searchIdInput
    };

    var exportXLSButton = document.createElement('input');
    YAHOO.util.Dom.setAttribute(exportXLSButton, 'type', 'button');
    YAHOO.util.Dom.setAttribute(exportXLSButton, 'value', MDSS.Localized.Excel_Export_Nav);
    YAHOO.util.Dom.addClass(exportXLSButton, 'queryButton');
    YAHOO.util.Event.on(exportXLSButton, 'click', this._exportXLS, obj, this);

    form.appendChild(xmlInput);
    form.appendChild(config);
    form.appendChild(searchIdInput);

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

    var loadButton = new YAHOO.util.Element(document.createElement('input'));
    loadButton.set('type', 'button');
    loadButton.set('value', MDSS.Localized.Query.Load);
    loadButton.set('id', this.LOAD_QUERY_BUTTON);
    loadButton.addClass('queryButton');
    loadButton.on('click', this._loadQuery, {}, this);

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
    leftDiv.appendChild(loadButton);
    leftDiv.appendChild(saveButton);
    leftDiv.appendChild(saveAsButton);

    var qBottom = new YAHOO.util.Element(this._qBottomUnit.body);
    qBottom.appendChild(leftDiv);
    qBottom.appendChild(rightDiv);

    // map panel buttons
    var mapButtonDiv = new YAHOO.util.Element(document.createElement('div'));
    mapButtonDiv.setStyle('float', 'right');


    var refreshMapButton = new YAHOO.util.Element(document.createElement('input'));
    refreshMapButton.set('type', 'button');
    refreshMapButton.set('value', MDSS.Localized.Query.Refresh);
    refreshMapButton.set('id', this.REFRESH_MAP_BUTTON);
    refreshMapButton.addClass('queryButton');
    refreshMapButton.on('click', this._mapQuery, {}, this);
    mapButtonDiv.appendChild(refreshMapButton);

    var mBottom = new YAHOO.util.Element(this._mBottomUnit.body);
    mBottom.appendChild(mapButtonDiv);
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

    if(nodeName === 'LI')
    {
      return oTarget;
    }
    else
    {
      // check he nodes parents for a TH
      var parent = YAHOO.util.Dom.getAncestorByTagName(oTarget, "LI");
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
        var menuItems = builder != null ? builder(liEntry) : [];
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
  _buildContentGrid : function()
  {
    // build the DataSource (required)
    var dataSource = new YAHOO.util.DataSource([]);
    dataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;

    dataSource.responseSchema = {
      fields: []
    };

    this._dataTable = new YAHOO.widget.DataTable(this.QUERY_DATA_TABLE, [], dataSource,{draggableColumns:true});

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
  	column = this._dataTable.insertColumn(column);
  	column.attribute = attrib;

    if(Mojo.util.isFunction(menuBuilder))
    {
      // add mapping between column and menuItems
      this._headerMenuBuilders[column.getKey()] = menuBuilder;
    }

    this._addSelectedColumn(column);

    return column;
  },

  /**
   * Adds to the list of possible thematic variables a user can
   * choose from.
   */
  addThematicVariable : function(entityAlias, attributeName, userAlias, displayLabel)
  {
    var thematicVar = new Mojo.$.dss.vector.solutions.query.ThematicVariable();
    thematicVar.setEntityAlias(entityAlias);
    thematicVar.setAttributeName(attributeName);
    thematicVar.setUserAlias(userAlias);
    thematicVar.setDisplayLabel(displayLabel);

  	this._thematicVariables[attributeName] = thematicVar;
  },

  /**
   * Removes the given thematic variable.
   */
  removeThematicVariable : function(attributeName)
  {
    delete this._thematicVariables[attributeName];
  },

  getThematicVariables : function()
  {
  	return Mojo.util.getValues(this._thematicVariables);
  },

  /**
   * Scraps the category list HTML and creates new instances
   * of AbstractCategor.
   */
  scrapeCategories : function()
  {
  	var categories = [];

    var categoryList = document.getElementById(this.CATEGORY_LIST);
    var dds = YAHOO.util.Selector.query('dd', categoryList);
    for(var i=0; i<dds.length; i++)
    {
      var dd = dds[i];
      var inputs = YAHOO.util.Selector.query('input', dd);

      var type = inputs[0].value;
      var thematicColor = inputs[1].value;

      var construct = Mojo.util.getType(type);
      var category = new construct();
      category.setThematicColor(thematicColor);

      if(type === Mojo.$.dss.vector.solutions.query.RangeCategory.CLASS)
      {
        var lowerBound = inputs[2].value;
        var upperBound = inputs[3].value;

        category.setLowerBound(lowerBound);
        category.setUpperBound(upperBound);
      }
      else
      {
      	var exactValue = inputs[2].value;

      	category.setExactValue(exactValue);
      }

      categories.push(category);
    }

    return categories;
  },

  addCategoryHTML : function(html)
  {
    var categoryList = document.getElementById(this.CATEGORY_LIST);
    var li = document.createElement('li');
    li.innerHTML = html;

    categoryList.appendChild(li);
  },

  /**
   * Removes the specified column from the table.
   */
  removeColumn : function(column)
  {
    this._dataTable.removeColumn(column);
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
   * Adds a map with the given configuration.
   */
  createMap : function(layers)
  {
    var baseLayer = layers[0];
    var geoServerPath = baseLayer.geoserverURL;

    // clear any previous map
    //document.getElementById(this.MAP_CONTAINER).innerHTML = '';
    if(this._map != null)
    {
      this._map.destroy();
    }

    // pink tile avoidance
    OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
    // make OL compute scale according to WMS spec
    OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

    var bounds = new OpenLayers.Bounds(
        36.718452, -17.700377000000003,
        36.938452, -17.480376999999997
    );
    var options = {
        controls: [],
        projection: "EPSG:4326",
        units: 'degrees'
    };


    this._map = new OpenLayers.Map(this.MAP_CONTAINER, options);

    // setup base tiled layer
    var mapLayers = [];
    var tiled = new OpenLayers.Layer.WMS(
        "", geoServerPath+"/wms",
        {
            width: '430',
            srs: 'EPSG:4326',
            layers: baseLayer.view,
            height: '430',
            styles: '',
            format: 'image/png',
            tiled: 'true',
            sld: Mojo.ClientSession.getBaseEndpoint()+baseLayer.sld
        },
        {
          buffer: 0,
          isBaseLayer: true
        }
    );
    mapLayers.push(tiled);

    for(var i=1; i<layers.length; i++)
    {
      var layerName = layers[i];
        var extraLayer = new OpenLayers.Layer.WMS(
        "", geoServerPath+"/wms",
        {
            width: '430',
            srs: 'EPSG:4326',
            layers: layerName.view,
            height: '430',
            styles: '',
            format: 'image/png',
            tiled: 'true',
            sld: Mojo.ClientSession.getBaseEndpoint()+layerName.sld,
            transparent: true
        },
        {
          buffer: 0,
          opacity: 0.3
        }
    );

         mapLayers.push(extraLayer);
    }


    this._map.addLayers(mapLayers);

    // build up all controls
    this._map.addControl(new OpenLayers.Control.PanZoomBar({
        position: new OpenLayers.Pixel(2, 15)
    }));
    this._map.addControl(new OpenLayers.Control.Navigation());
    this._map.addControl(new OpenLayers.Control.Scale($('scale')));
    this._map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
    this._map.zoomToExtent(bounds);
  },

  /**
   * Loads all saved queries for this QueryPanel.
   */
  _loadQuery : function()
  {
    if(Mojo.util.isFunction(this._config.loadQuery))
    {
      var queries = document.getElementById(this.AVAILABLE_QUERY_LIST);

      // ignore the default, empty option
      var savedSearchId = queries.options[queries.selectedIndex].value;
      this._config.loadQuery.call(this._queryClass, savedSearchId);
    }
  },

  /**
   * Saves a query.
   */
  _saveQuery : function()
  {
    if(Mojo.util.isFunction(this._config.saveQuery))
    {
      this._config.saveQuery.call(this._queryClass);
    }
  },

  /**
   * Saves a new query.
   */
  _saveQueryAs : function()
  {
    if(Mojo.util.isFunction(this._config.saveQueryAs))
    {
      this._config.saveQueryAs.call(this._queryClass);
    }
  },

  /**
   * Creates the map
   */
  _mapQuery : function()
  {
    if(Mojo.util.isFunction(this._config.mapQuery))
    {
      this._config.mapQuery.call(this._queryClass);
    }
  },

  /**
   * Executes the Query by calling the user-defined
   * handler.
   */
  _executeQuery : function()
  {
    if(Mojo.util.isFunction(this._config.executeQuery))
    {
      this._config.executeQuery.call(this._queryClass);
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

    section.appendChild(frag);
  },

  _paginationHandler : function(e)
  {
    if(e.target.nodeName === 'SPAN' && Mojo.util.isFunction(this._config.paginationHandler))
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
    this._mapLayout.render();

    this._postRender();
  }
};

/**
 * Class to manage a color picker inside of a
 * dialog.
 */
MDSS.ColorPicker = function(baseId, openerId, inputId, colorHex)
{
  this._baseId = baseId;
  this._openerId = openerId;
  this._inputId = inputId;
  this._colorHex = colorHex;
  this._dialog = null;
  this._picker = null;

  YAHOO.util.Event.on(openerId, 'click', this._renderDialog, null, this);
};

MDSS.ColorPicker.prototype = {

  /**
   * Handles the submit click after
   * selecting a color.
   */
  _handleSubmit: function()
  {
    var sColor = "#" + this._picker.get("hex");
    document.getElementById(this._inputId).value = sColor;
    YAHOO.util.Dom.setStyle(this._openerId, 'background-color', sColor);

    this._dialog.hide();
  },

  /**
   * Cancels selecting a color and
   * closes the dialog.
   */
  _handleCancel: function()
  {
  	this._dialog.hide();
  },

  _renderPicker : function()
  {
  	var val = this._colorHex.substring(1);

    this._picker = new YAHOO.widget.ColorPicker(this._baseId+"_picker", {
      container: this._dialog,
      showcontrols: false,
      images: {
        PICKER_THUMB: "js/yui/build/colorpicker/assets/picker_thumb.png"
      },
    });

    this._picker.set('hex', val);
  },

  _renderDialog : function()
  {
    if(this._dialog == null)
    {
      var sBound = MDSS.util.bind(this, this._handleSubmit);
      var cBound = MDSS.util.bind(this, this._handleCancel);

      this._dialog = new YAHOO.widget.Dialog(this._baseId+"_pickerPanel", {
        width : "400px",
        height: "250px",
        fixedcenter : true,
        visible : true,
        constraintoviewport : true,
        draggable: false,
        close: false,
        zindex: 200,
        buttons : [ { text: MDSS.Localized.Submit, handler:sBound, isDefault:true },
            { text: MDSS.Localized.Cancel, handler:cBound } ]
      });

      var rBound = MDSS.util.bind(this, this._renderPicker);
      //this._dialog.renderEvent.subscribe(rBound);
      this._dialog.setBody('<div class="yui-picker" id="'+this._baseId+'_picker"></div>');
      this._dialog.render(document.body);
      this._dialog.bringToTop();

      this._renderPicker();
    }
    else
    {
      this._dialog.show();
    }
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
  this._isCurrent = Mojo.util.isBoolean(isCurrent) ? isCurrent : false;
  this._pageNumber = Mojo.util.isNumber(pageNumber) ? pageNumber : 0;
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