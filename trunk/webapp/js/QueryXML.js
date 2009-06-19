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
  },

  Functions : {
  	SUM: 'SUM',
  	MIN: 'MIN',
  	MAX: 'MAX',
  	AVG: 'AVG',
  },

  DateGroupOpts: {
  	DATEGROUP_EPIWEEK:'Epi Week',
  	DATEGROUP_MONTH:'Month',
  	DATEGROUP_QUARTER:'Quarter',
  	DATEGROUP_SEASON:'Season'
  },

  GROUP_BY_FUNCTION : 'GB',
  COUNT_FUNCTION : 'COUNT'
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

  getGroupBy : function()
  {
  	return this._groupBy;
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

  getCondition : function() { return this._condition; },

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

  getComponent : function()
  {
    return this._component;
  },

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

  getSelectable : function() { return this._selectable; },

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

  getCondition : function(key)
  {
    return this._conditions[key];
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

  getCondition : function(key)
  {
    return this._conditions[key];
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
MDSS.QueryXML.Selectable = function(component)
{
  this._component = component;
}
MDSS.QueryXML.Selectable.prototype = {

  getComponent: function() { return this._component; },

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

MDSS.QueryXML.Sqlcharacter = function(entityAlias, name, userAlias)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = arguments.length == 3 ? userAlias : '';
}
MDSS.QueryXML.Sqlcharacter.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },

  build : function()
  {
    var obj = {
      'sqlcharacter': {
        'name': this._name,
        'userAlias': this._userAlias,
      }
    };

    return obj;
  }
}



MDSS.QueryXML.SUM = function(selectable, userAlias)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
}
MDSS.QueryXML.SUM.prototype = {

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'sum': [selectableObj,
  	  {'userAlias': alias}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.MIN = function(selectable, userAlias)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
}
MDSS.QueryXML.MIN.prototype = {

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'min': [selectableObj,
  	  {'userAlias': alias}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.MAX = function(selectable, userAlias)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
}
MDSS.QueryXML.MAX.prototype = {

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'max': [selectableObj,
  	  {'userAlias': alias}]
  	};
  	return obj;
  }
}

MDSS.QueryXML.AVG = function(selectable, userAlias)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
}
MDSS.QueryXML.AVG.prototype = {

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'avg': [selectableObj,
  	  {'userAlias': alias}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.COUNT = function(selectable, userAlias)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
}
MDSS.QueryXML.COUNT.prototype = {

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'count': [selectableObj,
  	  {'userAlias': alias}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.GroupBy = function()
{
  this._selectableMap = [];
}
MDSS.QueryXML.GroupBy.prototype = {

  clearGroupBy : function() { this._selectableMap = []; },

  addSelectable : function(key, selectable)
  {
    this._selectableMap[key] = selectable;
  },

  removeSelectable : function(key)
  {
    delete this._selectableMap[key];
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
      'groupby': selectablesArray
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
