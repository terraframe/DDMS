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
    LE: 'LE',
    CONTAINS_ANY: 'containsAny'
  },

  Functions : {
  	SUM: 'SUM',
  	MIN: 'MIN',
  	MAX: 'MAX',
  	AVG: 'AVG'
  },
  
  DateGroupOpts: {
  	DATEGROUP_EPIWEEK:MDSS.localize('DATEGROUP_EPIWEEK'),
  	DATEGROUP_EPIYEAR:MDSS.localize('DATEGROUP_EPIYEAR'),
  	DATEGROUP_MONTH:MDSS.localize('DATEGROUP_MONTH'),
  	DATEGROUP_QUARTER:MDSS.localize('DATEGROUP_QUARTER'),
  	DATEGROUP_YEAR:MDSS.localize('DATEGROUP_YEAR'),
  	DATEGROUP_SEASON:MDSS.localize('DATEGROUP_SEASON')
  },

  GROUP_BY_FUNCTION : 'AG',
  COUNT_FUNCTION : 'COUNT',
  RATIO_FUNCTION : 'ratio_of_this_row_to_total_count'
};

/**
 * Converts an object literal into XML.
 */
MDSS.QueryXML.objectToXML = function(obj)
{
  function toXML(name, content)
  {
    if(name === 'userDisplayLabel' && Mojo.Util.isString(content))
    {
      content = content.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/&/g, "&amp;").replace(/'/g, "&apos;").replace(/"/g, "&quot;");
    }
    
    return '<'+name+'>'+content+'</'+name+'>' + (MDSS.QueryXML.DEBUG ? '\n' : '');
  }

  var xml = '';
  for(var key in obj)
  {
    var value = obj[key];
    if(Mojo.Util.isObject(value))
    {
      var fragment = MDSS.QueryXML.objectToXML(value);
      xml += toXML(key, fragment);
    }
    else if(Mojo.Util.isArray(value))
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
 * Set of functions useful for certain calculations where
 * SUM isn't meaningful.
 */
MDSS.QueryXML.F_SET1 = [
                       MDSS.QueryXML.Functions.MIN,
                       MDSS.QueryXML.Functions.MAX,
                       MDSS.QueryXML.Functions.AVG
                     ];

/**
 * Set of functions useful for certain calculations where
 * only MIN and MAX are meaningful.
 */
MDSS.QueryXML.F_SET2 = [
                        MDSS.QueryXML.Functions.MIN,
                        MDSS.QueryXML.Functions.MAX
                        ];

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
    var entities = Mojo.Util.getValues(this._entityMap);
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

  getValue : function() { return this._value; },

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

  getConditions : function() { return this._conditions; },

  getSize : function()
  {
    return Mojo.Util.getKeys(this._conditions).length;
  },

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
    var conditions = Mojo.Util.getValues(this._conditions);
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

  getSize : function()
  {
    return Mojo.Util.getKeys(this._conditions).length;
  },

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
    var conditions = Mojo.Util.getValues(this._conditions);
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
    var selectables = Mojo.Util.getValues(this._selectableMap);
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

MDSS.QueryXML.Attribute = function(entityAlias, name, userAlias, userDisplayLabel)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
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
        'userDisplayLabel': this._userDisplayLabel
      }
    };

    return obj;
  }
}

MDSS.QueryXML.Sqlinteger = function(entityAlias, name, userAlias, userDisplayLabel, isAggregate)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
  this._isAggregate = isAggregate || false;
}
MDSS.QueryXML.Sqlinteger.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },
  
  build : function()
  {
    var obj = {
      'sqlinteger': {
        'name': this._name,
        'userAlias': this._userAlias,
        'userDisplayLabel': this._userDisplayLabel,
        'isaggregate': this._isAggregate
      }
    };

    return obj;
  }
}


MDSS.QueryXML.Sqlfloat = function(entityAlias, name, userAlias, userDisplayLabel, isAggregate)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
  this._isAggregate = isAggregate || false;
}
MDSS.QueryXML.Sqlfloat.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },
  
  build : function()
  {
    var obj = {
      'sqlfloat': {
        'name': this._name,
        'userAlias': this._userAlias,
        'userDisplayLabel': this._userDisplayLabel,
        'isaggregate': this._isAggregate
      }
    };

    return obj;
  }
}

MDSS.QueryXML.Sqldouble = function(entityAlias, name, userAlias, userDisplayLabel, isAggregate)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
  this._isAggregate = isAggregate || false;
}
MDSS.QueryXML.Sqldouble.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },
  
  build : function()
  {
    var obj = {
      'sqldouble': {
        'name': this._name,
        'userAlias': this._userAlias,
        'userDisplayLabel': this._userDisplayLabel,
        'isaggregate': this._isAggregate
      }
    };

    return obj;
  }
}

MDSS.QueryXML.Sqlcharacter = function(entityAlias, name, userAlias, userDisplayLabel, isAggregate)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
  this._isAggregate = isAggregate || false;
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
        'userDisplayLabel': this._userDisplayLabel,
        'isaggregate': this._isAggregate

      }
    };

    return obj;
  }
}

MDSS.QueryXML.Sqldate = function(entityAlias, name, userAlias, userDisplayLabel, isAggregate)
{
  this._entityAlias = entityAlias;
  this._name = name;
  this._userAlias = userAlias || '';
  this._userDisplayLabel = userDisplayLabel || '';
  this._isAggregate = isAggregate || false;
}
MDSS.QueryXML.Sqldate.prototype = {

  getName : function() { return this._name; },

  getEntityAlias : function() { return this._entityAlias; },

  getUserAlias : function() { return this._userAlias; },

  build : function()
  {
    var obj = {
      'sqldate': {
        'name': this._name,
        'userAlias': this._userAlias,
        'userDisplayLabel': this._userDisplayLabel,
        'isaggregate': this._isAggregate
      }
    };

    return obj;
  }
}



MDSS.QueryXML.SUM = function(selectable, userAlias, userDisplayLabel)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
  this._userDisplayLabel = userDisplayLabel || '';
}
MDSS.QueryXML.SUM.prototype = {

  getSelectable : function() { return this._selectable; },

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'sum': [selectableObj,
  	  {
  	    'userAlias': alias,
  	    'userDisplayLabel': this._userDisplayLabel}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.MIN = function(selectable, userAlias , userDisplayLabel)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
  this._userDisplayLabel = userDisplayLabel || '';
}
MDSS.QueryXML.MIN.prototype = {

  getSelectable : function() { return this._selectable; },

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'min': [selectableObj,
  	  {'userAlias': alias,
  	   'userDisplayLabel': this._userDisplayLabel}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.MAX = function(selectable, userAlias , userDisplayLabel)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
  this._userDisplayLabel = userDisplayLabel || '';
}
MDSS.QueryXML.MAX.prototype = {

  getSelectable : function() { return this._selectable; },

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'max': [selectableObj,
  	  {'userAlias': alias,
  	   'userDisplayLabel': this._userDisplayLabel}]
  	};
  	return obj;
  }
}

MDSS.QueryXML.AVG = function(selectable, userAlias , userDisplayLabel)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
  this._userDisplayLabel = userDisplayLabel || '';
}
MDSS.QueryXML.AVG.prototype = {

  getSelectable : function() { return this._selectable; },

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'avg': [selectableObj,
  	  {'userAlias': alias,
  	   'userDisplayLabel': this._userDisplayLabel}]
  	};

  	return obj;
  }
}

MDSS.QueryXML.COUNT = function(selectable, userAlias , userDisplayLabel)
{
  this._selectable = selectable;
  this._userAlias = userAlias;
  this._userDisplayLabel = userDisplayLabel || '';
}
MDSS.QueryXML.COUNT.prototype = {

  getSelectable : function() { return this._selectable; },

  build : function()
  {
  	var selectableObj = this._selectable.build();
    var alias = this._userAlias != null ? this._userAlias : '';

  	var obj = {
  	  'count': [selectableObj,
  	  {'userAlias': alias,
  	   'userDisplayLabel': this._userDisplayLabel}]
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
    var selectables = Mojo.Util.getValues(this._selectableMap);
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

MDSS.Query = {};
MDSS.Query.Config = function(configJSON)
{
  this.CRITERIA = 'Criteria';
  
  this._config = {
    selectedUniversals : {},
    criteriaEntities : {},
    terms: {},
    date_attribute: {}
  };

  if(configJSON != null)
  {
    var config = Mojo.Util.getObject(configJSON);
    Mojo.Util.copy(config, this._config);
  }
};

MDSS.Query.Config.prototype = {

  setGeoAttributes : function(attributeKeys)
  {
    Mojo.Iter.forEach(attributeKeys, function(key){
      this._config.selectedUniversals[key] = [];
      this._config.criteriaEntities[key] = [];
    }, this);
  },
 
  setCriteriaEntities : function(attributeKey, entities)
  {
    var ids = Mojo.Iter.map(entities, function(entity){
      return entity.getGeoEntityId();
    });
    
    this._config.criteriaEntities[attributeKey] = ids;
  },
  
  getCriteriaEntities : function(attributeKey)
  {
    return this._config.criteriaEntities[attributeKey];
  },

  addSelectedUniversal : function(attributeKey, universal)
  {
    this._config.selectedUniversals[attributeKey].push(universal);
  },

  getSelectedUniversals : function(attributeKey)
  {
    return this._config.selectedUniversals[attributeKey];
  },

  clearSelectedUniversals : function(attributeKey)
  {
    if(attributeKey)
    {
      this._config.selectedUniversals[attributeKey] = [];
    }
    else
    {
      for(var key in this._config.selectedUniversals)
      {
        this._config.selectedUniversals[key] = [];
      }
    }
  },
  setDateAttribute : function(value)
  {
    this._config.date_attribute = value;
  },

  getDateAttribute : function(value)
  {
    return(this._config.date_attribute);
  },
  
  setProperty : function(key, value)
  {
    this._config[key] = value;
  },
  
  removeProperty : function(key)
  {
    delete this._config[key];
  },
  
  setNumberCriteria : function(key, value)
  {
    this.setProperty(key+this.CRITERIA, value);
  },
  
  getNumberCriteria : function(key)
  {
    return this._getProperty(key+this.CRITERIA);
  },
  
  removeNumberCriteria : function(key)
  {
    this.removeProperty(key+this.CRITERIA);
  },

  getProperty : function(key)
  {
    return this._config[key];
  },
  
  addTerms : function(key, termIds)
  {
    this.removeTerms(key);
    for(var i=0; i<termIds.length; i++)
    {
      this._config.terms[key][termIds[i]] = '';
    }
  },
  
  getTermDisplay : function(key, termId)
  {
    return this._config.terms[key][termId];
  },
  
  removeTerms : function(key)
  {
    this._config.terms[key] = {};
  },

  getJSON : function()
  {
    return Mojo.Util.getJSON(this._config);
  }
};


MDSS.Query.Parser = function(xml)
{
  this._xmlDoc;
  /*
  try //Internet Explorer
  {
    this._xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    this._xmlDoc.async="false";
    this._xmlDoc.loadXML(xml);
  }
  catch(e)
  {
  */
    var p =new DOMParser();
    this._xmlDoc = p.parseFromString(xml,"text/xml");
  /*
  }
  */
};

MDSS.Query.Parser.prototype = {

  _getValue : function(parent, tagName)
  {
    var nodes = parent.getElementsByTagName(tagName)
    var value = (nodes != null && nodes.length > 0 && nodes[0].firstChild) ? nodes[0].firstChild.nodeValue : '';
    return value;
  },

  parseSelectables : function(handlers)
  {
    var select = this._xmlDoc.getElementsByTagName('select')[0];
    var children = select.childNodes;

    for(var i=0; i<children.length; i++)
    {
      var child = children[i];
      if(child.nodeName !== 'selectable')
      {
        continue;
      }

      var first = child.firstChild;
      if(Mojo.Util.isFunction(handlers[first.nodeName]))
      {
        var entityAlias = this._getValue(first, 'entityAlias');
        var attributeName = this._getValue(first, 'name');
        var userAlias = this._getValue(first, 'userAlias');
        handlers[first.nodeName](entityAlias, attributeName, userAlias);
      }
    }
  },

  parseCriteria : function(handlers)
  {
    var criteria = this._xmlDoc.getElementsByTagName('criteria');
    if(criteria == null || criteria.length == 0)
    {
      return;
    }

    for(var i=0; i<criteria.length; i++)
    {
      var criteriaNode = criteria[i];
      var selectables = criteriaNode.getElementsByTagName('selectable');

      for(var j=0; j<selectables.length; j++)
      {
        var selectable = selectables[j];
        var attribute = selectable.firstChild;
        if(Mojo.Util.isFunction(handlers[attribute.nodeName]))
        {
          var entityAlias = this._getValue(selectable, 'entityAlias');
          var attributeName = this._getValue(selectable, 'name');
          var userAlias = this._getValue(selectable, 'userAlias');

          // Don't parse GeoEntity criteria (QueryBase) takes care of that.
          if(attributeName === 'parentGeoEntity')
          {
            continue;
          }

          var parent = selectable.parentNode;
          var operator = this._getValue(parent, 'operator');
          var value = this._getValue(parent, 'value');
          handlers[attribute.nodeName](entityAlias, attributeName, userAlias, operator, value);
        }
      }
    }

  }

};
