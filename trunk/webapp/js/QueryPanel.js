/**
 * Namespace for all XML related query functionality.
 */
MDSS.QueryXML = {
  DEBUG: true,
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
  addEntity : function(key, entity)
  {
    this._entities.addEntity(key, entity);
  },
  
  /**
   * Returns the Entity with the given
   * key or null if it does not exist.
   */
  getEntity : function(key)
  {
  	return this._entities.getEntity(key);
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
MDSS.QueryPanel = function(panelId, config)
{
  this._layout = new YAHOO.widget.Layout(panelId, {
    height: 480,
    width: 800,
    units: [
        { position: 'top', height: 50, resize: false, body: '', gutter: '2' },
        { position: 'left', width: 150, resize: false, body: '', gutter: '0 5 0 2', scroll: true },
        { position: 'bottom', height: 50, body: '', gutter: '2' },
        { position: 'center', body: '<div id="'+this.QUERY_DATA_TABLE+'"></div>', gutter: '0 2 0 0', scroll: true },
        { position: 'right', width: 150, body: '', resize: false, scroll: true, gutter: '0 5 0 2'}
    ]
  });
  
  this._config = config;
  
  this._queryItems = [];
  
  this._dataTable = null;
  
  this._query = new MDSS.QueryXML.Query();
  
  // references to date range DOM elements
  this._startDate = null;
  this._endDate = null;
  
  // references to the panel units
  this._topUnit = null;
  this._leftUnit = null;
  this._bottomUnit = null;
  this._centerUnit = null;
  this._rightUnit = null;
  
    
  // map between header ids (TH tags) and context menu builder functions
  this._headerMenuBuilders = {};
  
  // map between query list entries (LI tags) and context menu builder functions
  this._queryMenuBuilders = {};
};

MDSS.QueryPanel.prototype = {

  QUERY_ITEMS : "queryItemsList",

  QUERY_DATA_TABLE : "queryDataTable",

  DATE_RANGE_DIV : "dateRange",
  
  START_DATE_RANGE : "startDateRange",
  
  END_DATE_RANGE : "endDateRange",
  
  MAP_QUERY_BUTTON : "mapQueryButton",
  
  RUN_QUERY_BUTTON : "runQueryButton",
  
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
   * Returns the MDSS.QueryXML.Query object.
   */
  getQuery : function()
  {
    return this._query;
  },

  /**
   * Adds the date range div to the top panel.
   */
  _buildDateRange : function()
  {
  	var dateRange = new YAHOO.util.Element(document.createElement('div'));
    dateRange.set('id', this.DATE_RANGE_DIV);

    var startLabel = document.createElement('span');
    startLabel.innerHTML = this._config.startDateLabel;

    this._startDate = new YAHOO.util.Element(document.createElement('input'));
    this._startDate.set('type', 'text');
    this._startDate.set('id', this.START_DATE_RANGE);
    this._startDate.addClass('DatePick');

    var endLabel = document.createElement('span');
    endLabel.innerHTML = this._config.endDateLabel;

    this._endDate = new YAHOO.util.Element(document.createElement('input'));
    this._endDate.set('type', 'text');
    this._endDate.set('id', this.END_DATE_RANGE);
    this._endDate.addClass('DatePick');
    
    // add the date fields
    dateRange.appendChild(startLabel);
    dateRange.appendChild(this._startDate);
    dateRange.appendChild(endLabel);
    dateRange.appendChild(this._endDate);
    
    var body = new YAHOO.util.Element(this._topUnit.body);
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
    
    var body = new YAHOO.util.Element(this._leftUnit.body);
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
  
  getTopUnit : function() { return this._topUnit; },
  
  getLeftUnit : function() { return this._leftUnit; },
  
  getBottomUnit : function() { return this._bottomUnit; },
  
  getCenterUnit : function() { return this._centerUnit; },
  
  getRightUnit : function() { return this._rightUnit; },
  
  /**
   * Should be called after QueryPanel has been rendered.
   */
  _postRender : function()
  {
    this._topUnit = this._layout.getUnitByPosition('top');
    this._leftUnit = this._layout.getUnitByPosition('left');
    this._bottomUnit = this._layout.getUnitByPosition('bottom');
    this._centerUnit = this._layout.getUnitByPosition('center');
    this._rightUnit = this._layout.getUnitByPosition('right');
    
    // action buttons
    this._buildButtons();
    
    // date range
    this._buildDateRange();
    
    // query items
    this._buildQueryItems();
    
    // content grid
    this._buildContentGrid();
  },
  
  /**
   * Builds the buttons to perform actions in the QueryPanel.
   */
  _buildButtons : function()
  {
  	this._mapButton = new YAHOO.util.Element(document.createElement('input'));
  	this._mapButton.set('type', 'button');
  	this._mapButton.set('value', this._config.mapButtonLabel);
  	this._mapButton.set('id', this.MAP_QUERY_BUTTON);
  	this._mapButton.addClass('queryButton');
  	this._mapButton.set('disabled', 'disabled');
  	this._mapButton.on('click', this._mapQuery, {}, this);

  	runButton = new YAHOO.util.Element(document.createElement('input'));
  	runButton.set('type', 'button');
  	runButton.set('value', this._config.runButtonLabel);
  	runButton.set('id', this.RUN_QUERY_BUTTON);
  	runButton.addClass('queryButton');
  	runButton.on('click', this._executeQuery, {}, this);
  	
    var body = new YAHOO.util.Element(this._bottomUnit.body);
    body.appendChild(runButton);
    body.appendChild(this._mapButton);
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
  
  _mapQuery : function()
  {
    
  },
  
  /**
   * Executes the Query as 
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
  	this._layout.render();
  	
  	this._postRender(); // FIXME have this be delayed or executed upon element load
  }
};