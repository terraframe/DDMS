/**
 * Namespace for all XML related query functionality.
 */
MDSS.QueryXML = {
  DEBUG: false,
  Operator : {
    EQ: 'EQ',
    GT: 'GT',
    GE: 'GE',
    LT: 'LT',
    LE: 'LE'

  }
};

/**
 * Converts an object literal into XML.
 */
MDSS.QueryXML.objectToXML = function(obj)
{
  function toXML(name, content)
  {
    return '<'+name+'>'+content+'</'+name+'>' + (MDSS.QueryXML.DEBUG ? '\n' : '');
  }

  var xml = '';
  for(var key in obj)
  {
    var value = obj[key];
    if(Mojo.util.isObject(value))
    {
      var fragment = MDSS.QueryXML.objectToXML(value);
      xml += toXML(key, fragment);
    }
    else if(Mojo.util.isArray(value))
    {
      // collect all elements under current name
      var subXML = '';
      for(var i=0; i<value.length; i++)
      {
        var entry = value[i];
        subXML += MDSS.QueryXML.objectToXML(entry);
      }

      xml += toXML(key, subXML);
    }
    else
    {
      xml += toXML(key, obj[key]);
    }
  }

  return xml;
}

/**
 * Root class for a query.
 */
MDSS.QueryXML.Query = function()
{
  this._entities = new MDSS.QueryXML.Entities();
  this._select = new MDSS.QueryXML.Select();
  this._groupBy = new MDSS.QueryXML.GroupBy();
  this._having = new MDSS.QueryXML.Having();
  this._orderBy = new MDSS.QueryXML.OrderBy();
}
MDSS.QueryXML.Query.prototype = {

  /**
   * Adds an Entity to the query.
   */
  addEntity : function(entity)
  {
    this._entities.addEntity(entity.getAlias(), entity);
  },

  /**
   * Returns the Entity with the given
   * key or null if it does not exist.
   */
  getEntity : function(alias)
  {
    return this._entities.getEntity(alias);
  },

  /**
   * Adds a selectable to the query.
   */
  addSelectable : function(key, selectable)
  {
    this._select.addSelectable(key, selectable);
  },

  /**
   * Removes the Selectable with the given key.
   */
  removeSelectable : function(key)
  {
    this._select.removeSelectable(key);
  },

  /**
   * Returns the XML for this query.
   */
  getXML : function()
  {
    var entitiesArray = this._entities.build();
    var selectArray = this._select.build();
    var groupByArray = this._groupBy.build();
    var havingArray = this._having.build();
    var orderByArray = this._orderBy.build();

    var obj = {
      'query': [entitiesArray, selectArray, groupByArray, havingArray, orderByArray]
    };

    var xml = '<?xml version=\"1.0\" encoding=\"UTF-8\"?>';
    xml += MDSS.QueryXML.objectToXML(obj);
    return xml;
  },

  /**
   * Returns the XML to display a map, which is a
   * subset of the original XML as restricted by the
   * given parameter.
   */
  getXMLForMap : function(entityAliases, selectableAliases)
  {
    // include only the entity of the given alias
    // and its selectable attributes
    var entities = new MDSS.QueryXML.Entities();
    for(var i=0; i<entityAliases.length; i++)
    {
      var entityAlias = entityAliases[i];
      var entity = this.getEntity(entityAlias);
      entities.addEntity(entityAlias, entity);
    }


    // TODO filter out by entityAlias instead of selectable aliases
    var select = new MDSS.QueryXML.Select();
    for(var i=0; i<selectableAliases.length; i++)
    {
      var key = selectableAliases[i];
      var selectable = this._select.getSelectable(key);
      select.addSelectable(key, selectable);
    }

    var entitiesArray = entities.build();
    var selectArray = select.build();
    var groupByArray = this._groupBy.build();
    var havingArray = this._having.build();
    var orderByArray = this._orderBy.build();

    var obj = {
      'query' : [entitiesArray, selectArray, groupByArray, havingArray, orderByArray]
    };

    var xml = '<?xml version=\"1.0\" encoding=\"UTF-8\"?>';
    xml += MDSS.QueryXML.objectToXML(obj);
    return xml;
  }
};

/**
 * Class to manage a list of Entities.
 */
MDSS.QueryXML.Entities = function()
{
  this._entityMap = {};
}
MDSS.QueryXML.Entities.prototype = {

  /**
   * Adds a new Entity to the query.
   */
  addEntity : function(key, entity)
  {
    this._entityMap[key] = entity;
  },

  getEntity : function(key, entity)
  {
    return this._entityMap[key];
  },

  /**
   * Removes an entity from the query.
   */
  removeEntity : function(key)
  {
    delete this._entityMap[key];
  },

  build : function()
  {
    var entities = Mojo.util.getValues(this._entityMap);
    var entitiesArray = [];
    for(var i=0; i<entities.length; i++)
    {
      entitiesArray.push(entities[i].build());
    }

    var obj = {
      'entities' : entitiesArray
    };

    return obj;
  }
};

/**
 * Class to manage a single Entity.
 */
MDSS.QueryXML.Entity = function(type, alias)
{
  this._type = type;
  this._alias = alias;
  this._condition = null; // optional
}
MDSS.QueryXML.Entity.prototype = {

  getType : function() { return this._type; },

  getAlias : function() { return this._alias; },

  setCondition : function(condition) { this._condition = condition; },

  clearCondition : function() { this._condition = null; },

  build : function()
  {
    var conditionObj = this._condition != null ? this._condition.build() : '';

    var obj = {
      'entity': {
        'type': this._type,
        'alias': this._alias,
        'criteria': conditionObj
      }
    };

    return obj;
  }
};

/**
 * Class to represent composite criteria on
 * a type.
 */
MDSS.QueryXML.CompositeCondition = function(component)
{
  this._component = component;
}
MDSS.QueryXML.CompositeCondition.prototype = {

  build : function()
  {
    var componentObj = this._component.build();

    var obj = {
      'compositeCondition' : componentObj
    };

    return obj;
  }
}

/**
 * Class to represent basic criteria on a
 * type.
 */
MDSS.QueryXML.BasicCondition = function(selectable, operator, value)
{
  this._selectable = selectable;
  this._operator = operator;
  this._value = value;
}
MDSS.QueryXML.BasicCondition.prototype = {

  build : function()
  {
    var selectableObj = this._selectable.build();

    var obj = {
      'basicCondition' : [
        selectableObj,
        {'operator' : this._operator},
        {'value': this._value}
      ]
    };

    return obj;
  }
}

/**
 * Class to wrap conditions with the Or operator.
 */
MDSS.QueryXML.Or = function()
{
  this._conditions = {};
}
MDSS.QueryXML.Or.prototype = {

  addCondition : function(key, condition)
  {
    this._conditions[key] = condition;
  },

  removeCondition : function(key)
  {
    delete this._conditions[key];
  },

  build : function()
  {
    var conditions = Mojo.util.getValues(this._conditions);
    var conditionsArray = [];
    for(var i=0; i<conditions.length; i++)
    {
      conditionsArray.push(conditions[i].build());
    }

    var obj = {
      'or' : conditionsArray
    };

    return obj;
  }
}

/**
 * Class to wrap conditions with the And operator.
 */
MDSS.QueryXML.And = function()
{
  this._conditions = {};
}
MDSS.QueryXML.And.prototype = {

  addCondition : function(key, condition)
  {
    this._conditions[key] = condition;
  },

  removeCondition : function(key)
  {
    delete this._conditions[key];
  },

  build : function()
  {
    var conditions = Mojo.util.getValues(this._conditions);
    var conditionsArray = [];
    for(var i=0; i<conditions.length; i++)
    {
      conditionsArray.push(conditions[i].build());
    }

    var obj = {
      'and' : conditionsArray
    };

    return obj;
  }
}

/**
 * Class to contain selectables.
 */
MDSS.QueryXML.Select = function()
{
  // key/value is alias_name/MDSS.QueryXML.Selectable
  this._selectableMap = {};
}
MDSS.QueryXML.Select.prototype = {

  addSelectable : function(key, selectable)
  {
    this._selectableMap[key] = selectable;
  },

  removeSelectable : function(key)
  {
    delete this._selectableMap[key];
  },

  getSelectable : function(key)
  {
    return this._selectableMap[key];
  },

  getSelectableMap : function()
  {
    return this._selectableMap();
  },

  build : function()
  {
    var selectables = Mojo.util.getValues(this._selectableMap);
    var selectablesArray = [];

    for(var i=0; i<selectables.length; i++)
    {
      selectablesArray.push(selectables[i].build());
    }

    var obj = {
      'select': selectablesArray
    };

    return obj;
  }
}

/**
 * Class to represent a selectable.
 */
MDSS.QueryXML.SimpleSelectable = function(component)
{
  this._component = component;
}
MDSS.QueryXML.SimpleSelectable.prototype = {

  build : function()
  {
    var componentObj = this._component.build();

    var obj = {
      'selectable': componentObj
    };

    return obj;
  }
}

MDSS.QueryXML.Attribute = function(entityAlias, name, userAlias)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = arguments.length == 3 ? userAlias : '';
}
MDSS.QueryXML.Attribute.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },

  build : function()
  {
    var obj = {
      'attribute': {
        'entityAlias': this._entityAlias,
        'name': this._name,
        'userAlias': this._userAlias,
      }
    };

    return obj;
  }
}

MDSS.QueryXML.GroupBy = function()
{

}
MDSS.QueryXML.GroupBy.prototype = {

  build : function()
  {
    var obj = {
      'groupby': ''
    };

    return obj;
  }
};

MDSS.QueryXML.Having = function()
{

}
MDSS.QueryXML.Having.prototype = {

  build : function()
  {
    var obj = {
      'having': ''
    };

    return obj;
  }
}

MDSS.QueryXML.OrderBy = function()
{

}
MDSS.QueryXML.OrderBy.prototype = {

  build : function()
  {
    var obj = {
      'orderby': ''
    };

    return obj;
  }
}

/**
 * Class to create a Query Panel.
 */
MDSS.QueryPanel = function(queryPanelId, mapPanelId, config)
{
  this._queryLayout = new YAHOO.widget.Layout(queryPanelId, {
    height: 480,
    width: 800,
    units: [
        { position: 'top', height: 40, resize: false, body: '', gutter: '2' },
        { position: 'left', width: 150, resize: false, body: '', gutter: '0 5 0 2', scroll: true },
        { position: 'bottom', height: 40, body: '', gutter: '2' },
        { position: 'center', body: '<div id="'+this.QUERY_DATA_TABLE+'"></div>', gutter: '0 2 0 0', scroll: true },
        { position: 'right', width: 150, body: '', resize: false, scroll: true, gutter: '0 5 0 2'}
    ]
  });

  this._mapLayout = new YAHOO.widget.Layout(mapPanelId, {
    height: 480,
    width: 800,
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

  // array of objects with each element representing a
  // key/value pair of SavedSearch.ID/SavedSearch.QUERYNAME
  this._availableQueries = [];
  this._queryList = null;

  // reference to the SavedSearch (if available) that
  // is responsible for rendering the query.
  this._currentSavedSearch = null;

  // the current layers in the map. If this._currentSavedSearch
  // is not null, then these layers belong to that SavedSearch.
  this._layers = [];

  this._map = null;

  // map between header ids (TH tags) and context menu builder functions
  this._headerMenuBuilders = {};

  // map between query list entries (LI tags) and context menu builder functions
  this._queryMenuBuilders = {};

  this._mapButton = null;
  this._runButton = null;
  this._loadButton = null;
  this._saveButton = null;
  this._refreshMapButton = null;

  // The button that adds a new layer when clicked
  this._addLayerButton = null;
};

MDSS.QueryPanel.prototype = {

  MAP_CONTAINER : "mapContainer",

  QUERY_ITEMS : "queryItemsList",

  DEFINED_LAYERS_LIST : "definedLayersList",

  AVAILABLE_QUERY_LIST : "availableQueryList",

  AVAILABLE_LAYERS_LIST : "availableLayersList",

  QUERY_DATA_TABLE : "queryDataTable",

  DATE_RANGE_DIV : "dateRange",

  START_DATE_RANGE : "startDateRange",

  END_DATE_RANGE : "endDateRange",

  MAP_QUERY_BUTTON : "mapQueryButton",

  REFRESH_MAP_BUTTON : "refreshMapButton",

  RUN_QUERY_BUTTON : "runQueryButton",

  LOAD_QUERY_BUTTON : "loadQueryButton",

  SAVE_QUERY_BUTTON : "saveQueryButton",

  /**
   *
   */
  setCurrentSavedSearch : function(savedSearch)
  {
    this._currentSavedSearch = savedSearch;
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

  /**
   * Returns the end date element wrapped
   * in a YUI Element object.
   */
  getEndDate : function()
  {
    return this._endDate;
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

    this._startDate = new YAHOO.util.Element(document.createElement('input'));
    this._startDate.set('type', 'text');
    this._startDate.set('id', this.START_DATE_RANGE);
    this._startDate.addClass('DatePick');

    var endLabel = document.createElement('span');
    endLabel.innerHTML = MDSS.Localized.Query.End_Date;

    this._endDate = new YAHOO.util.Element(document.createElement('input'));
    this._endDate.set('type', 'text');
    this._endDate.set('id', this.END_DATE_RANGE);
    this._endDate.addClass('DatePick');

    // add the date fields
    dateRange.appendChild(startLabel);
    dateRange.appendChild(this._startDate);
    dateRange.appendChild(endLabel);
    dateRange.appendChild(this._endDate);

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
      li.innerHTML = queryItem.displayLabel;

      // add click event handler
      if(queryItem.onclick)
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

    // action buttons
    this._buildButtons();

    // date range
    this._buildDateRange();

    // query items
    this._buildQueryItems();

    // content grid
    this._buildContentGrid();

    // universal list
    this._buildUniversalList();
  },

  /**
   * Builds a list of possible universal layers
   * that can be selected.
   */
  _buildUniversalList : function()
  {
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

  	// this data structure is defined by
  	// the GeoEntity tree for use case 111.
  	// (We're just stealing it for our own use here.)
  	var availableSpan = document.createElement('span');
  	YAHOO.util.Dom.setStyle(availableSpan, 'display', 'block');
    availableSpan.innerHTML = MDSS.Localized.Available_Layers;

  	var types = MDSS.GeoTreeSelectables.types;
    var typeNames = Mojo.util.getKeys(types);
    typeNames.sort();

    var universalListDiv = new YAHOO.util.Element(document.createElement('div'));
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

    // add button for new layer
    this._addLayerButton = new YAHOO.util.Element(document.createElement('input'));
    this._addLayerButton.set('type', 'button');
    this._addLayerButton.set('value', MDSS.Localized.Add);
    this._addLayerButton.on('click', this._addLayer, null, this);

    universalListDiv.appendChild(this._addLayerButton);

    var wrapper = new YAHOO.util.Element(document.createElement('div'));
    YAHOO.util.Dom.addClass(wrapper, 'layersWrapper');
    wrapper.appendChild(universalListDiv);
    wrapper.appendChild(layersListDiv);

    var body = new YAHOO.util.Element(this._mLeftUnit.body);
    body.appendChild(wrapper);
  },

  /**
   *
   */
  _editDefinedLayer : function(e, obj)
  {
    if(Mojo.util.isFunction(this._config.editLayer))
    {
      this._config.editLayer.call(this, obj.layerId, obj.type);
    }
  },

  _deleteDefinedLayer : function(e, obj)
  {

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

    var actionObj = {
      layerId: layerId,
      type: type
    }

    var del = document.createElement('img');
    YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
    YAHOO.util.Event.on(del, 'click', this._deleteDefinedLayer, actionObj, this);

    var edit = document.createElement('img');
    YAHOO.util.Dom.setAttribute(edit, 'src', 'imgs/icons/wand.png');
    YAHOO.util.Event.on(edit, 'click', this._editDefinedLayer, actionObj, this);

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
    var option = document.getElementById(type+"_available");
    YAHOO.util.Dom.setAttribute(option, 'disabled', true);
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
        this._config.addLayer.call(this, this._currentSavedSearch, type);
      }
    }
  },

  /**
   * Builds the buttons to perform actions in the QueryPanel.
   */
  _buildButtons : function()
  {
  	// query panel buttons
    this._mapButton = new YAHOO.util.Element(document.createElement('input'));
    this._mapButton.set('type', 'button');
    this._mapButton.set('value', MDSS.Localized.Query.Map);
    this._mapButton.set('id', this.MAP_QUERY_BUTTON);
    this._mapButton.addClass('queryButton');
    this._mapButton.set('disabled', 'disabled');
    this._mapButton.on('click', this._mapQuery, {}, this);

    this._runButton = new YAHOO.util.Element(document.createElement('input'));
    this._runButton.set('type', 'button');
    this._runButton.set('value', MDSS.Localized.Query.Run);
    this._runButton.set('id', this.RUN_QUERY_BUTTON);
    this._runButton.addClass('queryButton');
    this._runButton.on('click', this._executeQuery, {}, this);

    this._saveButton = new YAHOO.util.Element(document.createElement('input'));
    this._saveButton.set('type', 'button');
    this._saveButton.set('value', MDSS.Localized.Query.Save);
    this._saveButton.set('id', this.SAVE_QUERY_BUTTON);
    this._saveButton.addClass('queryButton');
    this._saveButton.on('click', this._saveQuery, {}, this);

    this._loadButton = new YAHOO.util.Element(document.createElement('input'));
    this._loadButton.set('type', 'button');
    this._loadButton.set('value', MDSS.Localized.Query.Load);
    this._loadButton.set('id', this.LOAD_QUERY_BUTTON);
    this._loadButton.addClass('queryButton');
    this._loadButton.on('click', this._loadQuery, {}, this);

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

    var qBottom = new YAHOO.util.Element(this._qBottomUnit.body);
    qBottom.appendChild(this._runButton);
    qBottom.appendChild(this._mapButton);

    var qRight = new YAHOO.util.Element(this._qRightUnit.body);
    qRight.appendChild(this._queryList);
    qRight.appendChild(this._loadButton);
    qRight.appendChild(this._saveButton);

    // map panel buttons
    this._refreshMapButton = new YAHOO.util.Element(document.createElement('input'));
    this._refreshMapButton.set('type', 'button');
    this._refreshMapButton.set('value', MDSS.Localized.Query.Refresh);
    this._refreshMapButton.set('id', this.REFRESH_MAP_BUTTON);
    this._refreshMapButton.addClass('queryButton');
    this._refreshMapButton.on('click', this._mapQuery, {}, this);

    var mBottom = new YAHOO.util.Element(this._mBottomUnit.body);
    mBottom.appendChild(this._refreshMapButton);
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

    this._dataTable = new YAHOO.widget.DataTable(this.QUERY_DATA_TABLE, [], dataSource);

    this._dataTable.render();

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
   * Adds a new Query Item to the left column of the panel.
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
    column = this._dataTable.insertColumn(column);

    if(Mojo.util.isFunction(menuBuilder))
    {
      // add mapping between column and menuItems
      this._headerMenuBuilders[column.getKey()] = menuBuilder;
    }

    return column;
  },

  /**
   * Removes the specified column from the table.
   */
  removeColumn : function(column)
  {
    this._dataTable.removeColumn(column);
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

  enableMapping : function()
  {
    var mapButton = new YAHOO.util.Element(this.MAP_QUERY_BUTTON);
    mapButton.set('disabled', '');
  },

  isMappingEnabled : function()
  {
    var mapButton = new YAHOO.util.Element(this.MAP_QUERY_BUTTON);
    var disabled = mapButton.get('disabled');
    return disabled != true && disabled !== 'disabled';
  },

  /**
   * Adds a map with the given configuration.
   */
  createMap : function(layers)
  {
  	var baseLayer = layers[0];

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
        //maxExtent: bounds,
        //maxResolution: 0.000859375,
        projection: "EPSG:4326",
        units: 'degrees'
    };


    this._map = new OpenLayers.Map(this.MAP_CONTAINER, options);

    // setup base tiled layer
    var mapLayers = [];
    var tiled = new OpenLayers.Layer.WMS(
        "", "http://127.0.0.1:8080/geoserver/wms",
        {
            width: '430',
            srs: 'EPSG:4326',
            layers: baseLayer.view,
            height: '430',
            styles: '',
            format: 'image/png',
            tiled: 'true',
            //sld: Mojo.ClientSession.getBaseEndpoint(),
            //tilesOrigin : "36.718452,-17.700377000000003"
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
        "", "http://127.0.0.1:8080/geoserver/wms",
        {
            width: '430',
            srs: 'EPSG:4326',
            layers: layerName.view,
            height: '430',
            styles: '',
            format: 'image/png',
            tiled: 'true',
            sld: Mojo.ClientSession.getBaseEndpoint()+layerName.sld,
            //tilesOrigin : "36.718452,-17.700377000000003",
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
      var index = queries.selectedIndex;
      if(index > 0)
      {
        var savedSearchId = queries.options[index].value;
        this._config.loadQuery.call(this, savedSearchId);
      }
    }
  },

  /**
   * Saves a query.
   */
  _saveQuery : function()
  {
    if(Mojo.util.isFunction(this._config.saveQuery))
    {
      this._config.saveQuery.call(this);
    }
  },

  /**
   * Creates the map
   */
  _mapQuery : function()
  {
    if(Mojo.util.isFunction(this._config.mapQuery))
    {
      this._config.mapQuery.call(this);
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
      this._config.executeQuery.call(this);
    }
  },

  /**
   * Renders the QueryPanel and its sub-components.
   */
  render : function()
  {
    this._queryLayout.render();
    this._mapLayout.render();

    this._postRender(); // FIXME have this be delayed or executed upon element load
  }
};