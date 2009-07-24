MDSS.QueryResistance = Mojo.Class.create();
MDSS.QueryResistance.prototype = Mojo.Class.extend(MDSS.QueryBase, {

	  initialize : function(selectableGroups, queryList)
	  {
	  	MDSS.QueryBase.prototype.initialize.call(this);

        if(arguments.length === 1 && arguments[0] == null)
        {
          // FIXME used for inheritance optimization
          return;
        }

	    // list of columns that have been added before a call to render()
	    this._preconfiguredColumns = [];

	    // START: query objects that dictate state of the query.

	    this._countSelectable = null;
	    this._ratioSelectable = null;

	    this._specieGroupSelectables = {};
	    this._visibleSelectables = {};
	    this._whereOptions = {};
	    this._visibleAggregateSelectables = {};
	    this._visibleGroupBySelectables = {};

	    this._concerteMosquitoCollection = Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection;
	    var mosquitoCollection = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
      var attribute = new MDSS.QueryXML.Attribute(mosquitoCollection.CLASS, this._concerteMosquitoCollection.DATECOLLECTED, this._concerteMosquitoCollection.DATECOLLECTED);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(attribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(attribute);

	    //this screen can query two diffrent classes, so we have a place to store the selected class
	    this._mainQueryClass = Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;

	    this._commonQueryClasses = [Mojo.$.dss.vector.solutions.entomology.MosquitoCollection.CLASS,
	                                Mojo.$.dss.vector.solutions.general.Insecticide];

	    this._exclusionClasses = ['common_selectables'];

	    this._dataQueryFunction = Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.queryResistance;
	    this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.MappingController.mapResistanceQuery;
	    // END: query objects

	    // Key/value where key is attribute.getKey() + "_li"
	    // (which is the id of the relevant LI node,
	    // and value is an array of ContextMenuItems.
	    this._menus = {};

	    // Map of criteria ids and associated ContextMenuItems.
	    this._menuItems = {};

	    for(var i=0; i<queryList.length; i++)
	    {
	      this._queryPanel.addAvailableQuery(queryList[i]);
	    }

	    this._buildQueryItems(selectableGroups);

	    this._buildColumns();
	  },

	  /**
	   * Returns the type of query.
	   */
	  _getQueryType: function()
	  {
	  	return 'QueryResistance';
	  },

	  /**
	   * Returns the controller action to invoke when exporting the query to XML.
	   */
	  _getExportXLSAction : function()
	  {
	  	return 'dss.vector.solutions.query.QueryController.exportResistanceQueryToExcel.mojo';
	  },

	  _getExportCSVAction : function()
	  {
	  	return 'dss.vector.solutions.query.QueryController.exportResistacneQueryToCSV.mojo';
	  },

	  _getExportReportAction : function()
	  {
	  	return 'dss.vector.solutions.report.ReportController.generateReport.mojo';
	  },

	  _getReportQueryType : function()
	  {
		return 'ENTOMOLOGY';
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
	    this._dataQueryFunction(request, xml, this._config.getJSON(), '', true, page, this.PAGE_SIZE);
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
	        var layersObj = Mojo.util.getObject(layers);
	        this.thisRef._queryPanel.createMap(layersObj);
	      }
	    });

	    var layerIds = this._queryPanel.getSelectedLayers();
	    var savedSearchView = this._queryPanel.getCurrentSavedSearch();
	    var savedSearchId = (savedSearchView != null ? savedSearchView.getSavedQueryId() : "");

        // FIXME json conversion below is temporary
	    this._mapQueryFunction(request, xml, this._config.getJSON(), layerIds, savedSearchId);
	  },

	  /**
	   * Constructs the query with all the subcomponents.
	   */
	  _constructQuery : function(formapping)
	  {
	  	var queryXML = MDSS.QueryBase.prototype._constructQuery.call(this,formapping); // super

	    var mainQuery = new MDSS.QueryXML.Entity(this._mainQueryClass, this._mainQueryClass);
	    queryXML.addEntity(mainQuery);


	    //add the common alaies
	    this._commonQueryClasses.map(function(klass){
	    	var query = new MDSS.QueryXML.Entity(klass, klass);
		    queryXML.addEntity(query);
	   	},this);

	    var mosquitoCollection = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection.CLASS;
	    var collectionQuery = new MDSS.QueryXML.Entity(mosquitoCollection, mosquitoCollection);
	    queryXML.addEntity(collectionQuery);

	    var conditions = [];
	    var groupBy = queryXML.getGroupBy();

	    // Visible Attributes
	    var selNames = Mojo.util.getKeys(this._visibleSelectables);
	    for(var i=0; i<selNames.length; i++)
	    {
	      var name = selNames[i];
	      var selectable = this._visibleSelectables[name];

	      queryXML.addSelectable(mainQuery.getAlias()+'_'+name, selectable);

	      if(selectable.attribute)
	      {

	      	var t =  selectable.attribute.getType();
	      	var n = selectable.attribute.getAttributeName().replace(/.displayLabel.currentValue/,'');
	      	var k = selectable.attribute.getKey().replace(/.displayLabel.currentValue/,'');
	      	if(t == 'sqlcharacter')
	      	{
		      	n = selectable.attribute.getAttributeName().replace(/_defaultLocale/,'');
		      	k = selectable.attribute.getKey().replace(/_defaultLocale/,'');
	      		var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', n, k));
	      	}
	      	else
	      	{
	      		var whereSelectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Attribute(t,n,k));
	      	}
	        //add entity for assay if this selectable is an assay
	        if(t.indexOf('.assay.') > 0 )
	        {
	          queryXML.addEntity(new MDSS.QueryXML.Entity(t,t));
	        }

	        var items = this._menus[selectable.attribute.getKey()+'_li'];

	        if(items)
	        {
	      	if(selectable.attribute.getDtoType() == 'AttributeEnumerationDTO')
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
	      				function(a){return a.checked;}).map(
	      						function(a){return a.uuid;});
	      		if(whereIds.length == 1)
	      		{
	      		  var condition = new MDSS.QueryXML.BasicCondition(whereSelectable, MDSS.QueryXML.Operator.EQ, whereIds[0]);
	    	      conditions.push(condition);
	      		}
	      		if(whereIds.length > 1)
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
	    }

	    var aggNames = Mojo.util.getKeys(this._visibleAggregateSelectables);
	    for(var i=0; i<aggNames.length; i++)
	    {
	      var name = aggNames[i];
	      var selectable = this._visibleAggregateSelectables[name];

	      queryXML.addSelectable(mosquitoQuery.getAlias()+'_'+name, selectable);
	    }

	    var gbNames = Mojo.util.getKeys(this._visibleGroupBySelectables);
	    for(var i=0; i<gbNames.length; i++)
	    {
	      var name = gbNames[i];
	      var selectable = this._visibleGroupBySelectables[name];

	      groupBy.addSelectable(name, selectable);
	    }

	    // start and end dates

		  if(this._startDate != null || this._endDate != null)
		  {
		    where = new MDSS.QueryXML.And();
		    if(this._startDate != null)
		    {
			      where.addCondition(('StartDateRange'), this._startDate );
		    }

		    if(this._endDate != null)
		    {
		    	where.addCondition(('EndDateRange'), this._endDate );
		    }
		    var composite = new MDSS.QueryXML.CompositeCondition(where);
		    collectionQuery.setCondition(composite);
	    }

	    //date groups

	    var keys = Mojo.util.getKeys(this._dateGroupSelectables);
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
	      queryXML.addSelectable(mosquitoQuery.getAlias()+'_globalCount', this._countSelectable);
	    }
	    if(this._ratioSelectable != null)
	    {
	      queryXML.addSelectable(MDSS.QueryXML.RATIO_FUNCTION, this._ratioSelectable);
	    }

	    var where = null;
	    if(conditions.length > 0 )
	    {
	      where = new MDSS.QueryXML.And();
	      for(var i=0; i<conditions.length; i++)
		    {
		      where.addCondition(('cond' + i), conditions[i]);
		    }

	      var composite = new MDSS.QueryXML.CompositeCondition(where);
	      mosquitoQuery.setCondition(composite);
	    }

	    return queryXML;
	  },




	  /**
	   * Helper method to add Entomology attributes to selectables and as a column.
	   */
	  _addVisibleAttribute : function(attribute)
	  {
	    var attributeName = attribute.getAttributeName();

	    if(attribute.getType() == 'sqlcharacter'){
	    	var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', attributeName, attributeName,attribute.getDisplayLabel(),attribute._isAggregate));
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
	    if(attribute._dtoType === 'AttributeIntegerDTO')
	    {
	    	this._queryPanel.addThematicVariable(attribute.getType(), attribute.getAttributeName(), attribute.getKey(), attribute.getDisplayLabel());
	    }
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
	    delete this._visibleGroupBySelectables[attribute.getKey()];

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

	    	this.uncheckAllNotInGroup(check);

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
	    for each (removeClass in  this._exclusionClasses)
	    {
	    	if(! YAHOO.util.Dom.hasClass(check, removeClass))
	    	{
		  	for each (check in YAHOO.util.Dom.getElementsByClassName(removeClass))
	      {
		  		if(check.checked && )
		      {
		  			if(YAHOO.util.Dom.hasClass(check, 'selectAllCheck')){
		  				check.checked = false;
		  			}else{
		  				check.click();
		  			}
		      }
	      }
	    }
	   }
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
	      sqldate : function(entityAlias, attributeName, userAlias){

	        thisRef._checkBox(userAlias);
	      },
	    });

	    var entities = [];

	    parser.parseCriteria({
	      attribute : function(entityAlias, attributeName, userAlias, operator, value){

	        // restricting geo entities
	        if(entityAlias === thisRef.ALL_PATHS)
	        {
	          entities.push(value);
	        }else
	        if(userAlias === thisRef._concerteMosquitoCollection.DATECOLLECTED)
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
	    var keys = Mojo.util.getKeys(this._menuItems);
	    for(var i=0; i<keys.length; i++)
	    {
	      var item = this._menuItems[keys[i]];
	      item.checked = false;
	    }


	  },


    /*
     * Visible Attributes
     */
    _getVizDiv : function(that,visibleAttributes,divName,type,checkClass)
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
	      var attribute = new MDSS.VisibleAttribute(visibleObj);

	      var li = document.createElement('li');

	      var check = document.createElement('input');
	      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
	      //this is the marker for the mutual exculsion group
	      YAHOO.util.Dom.addClass(check,checkClass);

	      YAHOO.util.Event.on(check, 'click', that._visibleAttributeHandler, attribute, that);
	      check.id = attribute.getKey();
	      li.appendChild(check);
	      this._defaults.push({element:check, checked:false});
	      if(visibleObj.dtoType === 'AttributeIntegerDTO')
	      {
	      	var select = document.createElement('select');


		      var options = [''];
		      options = options.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));


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
	      }

	      var span = document.createElement('span');
	      span.innerHTML = attribute.getDisplayLabel();
	      li.appendChild(span);

	      if (visibleObj.dropDownMap)
	      {

	      	li.id = attribute.getKey()+'_li';

		      var options = Mojo.util.getKeys(visibleObj.dropDownMap);
		      var displayLabels = Mojo.util.getValues(visibleObj.dropDownMap);
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

	      visibleUl.appendChild(li);
	    }

	    visibleDiv.appendChild(visibleUl);

	    return visibleDiv;
    },

	  /**
	   * Builds the query items for the left column.
	   */
	  _buildQueryItems : function(selectableGroups)
	  {
	  	/*
	  	 * Target
	  	 */
	    // area (geo entity search)
	    var boundSearch = MDSS.util.bind(this, this._displaySearch);
	    this._queryPanel.addQueryItem({
	      html: MDSS.Localized.Target_Search + ' <img src="./imgs/icons/world.png"/>',
	      onclick: {handler: boundSearch},
	      id: "areaItem"
	    });


	    this._queryPanel.addQueryItem({
	      html: this._getCountDiv(this,"Group_By",Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay,true),
	      id: 'globalCount'
	    });


	    var setupDiv =  function(group,idx){
	    	this._queryPanel.addQueryItem({
		      html: this._getVizDiv(this, group.values, group.title, group.klass, group.group),
		      id: group.group + '_checkbox_group',
		      menuBuilder : MDSS.util.bind(this, this._menuBuilder)
		    });
	    	this._exclusionClasses.push(group.group);
	    };

	    selectableGroups.map(setupDiv,this);

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

	  }
	});
