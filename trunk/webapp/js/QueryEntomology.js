MDSS.QueryEntomology = Mojo.Class.create();
MDSS.QueryEntomology.prototype = Mojo.Class.extend(MDSS.QueryBase, {

	  initialize : function(visibleAttributes, orderedGrids, queryList)
	  {
	  	MDSS.QueryBase.prototype.initialize.call(this);

	    // list of columns that have been added before a call to render()
	    this._preconfiguredColumns = [];

	    // Ref to instance of Entomology (used as template for display labels)
	    this._mosquitoView = new Mojo.$.dss.vector.solutions.entomology.MosquitoView();

	    // START: query objects that dictate state of the query.

	    this._startDate = null;
	    this._endDate = null;

	    this._visibleSelectables = {};
	    this._whereOptions = {};
	    this._visibleAggregateSelectables = {};
	    this._visibleGroupBySelectables = {};

	    this._thematicSearchList = [];  //list of map to search for thematic variable.
	    //this._thematicSearchList.push(this._gridEntities);
	    //this._thematicSearchList.push(this._gridSelectables);
	    //this._thematicSearchList.push(this._gridAggregateSelectables);
	    //this._thematicSearchList.push(this._gridGroupBySelectables);

	    // END: query objects

	    for(var i=0; i<queryList.length; i++)
	    {
	      this._queryPanel.addAvailableQuery(queryList[i]);
	    }

	    this._buildQueryItems(visibleAttributes, orderedGrids);

	    this._buildColumns();
	  },

	  _containsGroupBy : function()
	  {
	    return  Mojo.util.getValues(this._visibleGroupBySelectables).length > 0 ||
	      Mojo.util.getValues(this._gridGroupBySelectables).length > 0;
	  },

	  /**
	   * Returns the method to save this Entomology search.
	   */
	  _getSaveQueryMethod : function()
	  {
	  	return Mojo.$.dss.vector.solutions.query.EntomologySearch.saveSearch;
	  },

	  /**
	   * Returns the controller action to invoke when exporting the query to XML.
	   */
	  _getExportXLSAction : function()
	  {
	  	return 'dss.vector.solutions.query.QueryController.exportEntomologyQueryToExcel.mojo';
	  },

	  _getExportCSVAction : function()
	  {
	  	return 'dss.vector.solutions.query.QueryController.exportQueryToCSV.mojo';
	  },

	  /**
	   * Saves the current state of the QueryXML.
	   */
	  saveQuery : function()
	  {
	    var controller = Mojo.$.dss.vector.solutions.query.QueryController;
	    var request = new MDSS.Request({
	      thisRef: this,
	      controller: controller,
	      onSuccess: function(html)
	      {
	        var modal = this.thisRef._createModal(html, MDSS.Localized.Query.Save);

	        var saved = MDSS.util.bind(this.thisRef, this.thisRef._saveQueryListener, modal);
	        var canceled = MDSS.util.bind(this.thisRef, this.thisRef._cancelQueryListener, modal);

	        this.controller.setSaveEntomologyQueryListener(saved);
	        this.controller.setCancelQueryListener(canceled);
	      }
	    });

	    controller.newEntomologyQuery(request);
	  },

	  /**
	   * Final function called before query is executed.
	   * Any last minute cleanup is done here. The this
	   * reference is that of the QueryPanel.
	   */
	  executeQuery : function()
	  {
	  	var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito;

	    // calculate the date criteria
	    var startDateEl = this._queryPanel.getStartDate();
	    var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
	    if(startDate.length > 0)
	    {
	      var formatted = MDSS.Calendar.getMojoDateString(startDate);

	      var attribute = new MDSS.QueryXML.Attribute(mosquito.CLASS, mosquito.TESTDATE);
	      var selectable = new MDSS.QueryXML.Selectable(attribute);
	      var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
	      this._startDate = startDateCondition;
	    }
	    else
	    {
	      this._startDate = null;
	    }

	    var endDateEl = this._queryPanel.getEndDate();
	    var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
	    if(endDate.length > 0)
	    {
	      var formatted = MDSS.Calendar.getMojoDateString(endDate);

	      var attribute = new MDSS.QueryXML.Attribute(mosquito.CLASS, mosquito.TESTDATE);
	      var selectable = new MDSS.QueryXML.Selectable(attribute);
	      var endDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, formatted);

	      this._endDate = endDateCondition;
	    }
	    else
	    {
	      this._endDate = null;
	    }

	    // execute the query
	    var queryXML = this._constructQuery();
	    var xml = queryXML.getXML();

	    var request = new MDSS.Request({
	      thisRef : this,
	      onSuccess : function(query)
	      {
	        // column key is selectable alias name
	        var columnSet = this.thisRef._queryPanel.getColumnSet();
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
	            entry[attr] = result.getAttributeDTO(attr).getValue();
	          }

	          jsonData.push(entry);
	        }

	        // clear previous records
	        this.thisRef._queryPanel.clearAllRecords();

	        this.thisRef._queryPanel.setRowData(jsonData);
	      }
	    });

	    $('debug_xml').value = xml;
	    xml = $('debug_xml').value;




	    xml = xml + '<!--GROUP_BY_MONTH-->';


	    //$('xml_go').onclick = function(){
	    	//var xml = $('debug_xml').value;
	    	//Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml, this._geoEntityQueryType);
	    //};
	    Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml, this._geoEntityQueryType);
	  },

	  /**
	   * Handler called to generate a map with a thematic variable.
	   */
	  mapQuery : function()
	  {
	    var queryXML = this._constructQuery();
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

	    Mojo.$.dss.vector.solutions.query.MappingController.mapEntomologyQuery(request, xml, this._geoEntityQueryType, layerIds, savedSearchId);
	  },

	  /**
	   * Constructs the query with all the subcomponents.
	   */
	  _constructQuery : function()
	  {
	  	var queryXML = MDSS.QueryBase.prototype._constructQuery.call(this); // super

	    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
	    var mosquitoQuery = new MDSS.QueryXML.Entity(mosquito, mosquito);
	    queryXML.addEntity(mosquitoQuery);
	    var conditions = [];
	    var groupBy = queryXML.getGroupBy();

	    // Visible Attributes
	    var selNames = Mojo.util.getKeys(this._visibleSelectables);
	    for(var i=0; i<selNames.length; i++)
	    {
	      var name = selNames[i];
	      var selectable = this._visibleSelectables[name];
	      queryXML.addSelectable(mosquitoQuery.getAlias()+'_'+name, selectable);

	      //darrell
	      if(selectable.attribute)
	      {
	      	var whereConds = selectable.attribute._whereValues.filter(
	      			function(a){
	      				return a.checked;
	      			}).map(
	      					function(a){
	      						var condition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, a.uuid);
	      		        conditions.push(condition);
	      						return a.id;
	      						});
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

	    if(this._startDate != null)
	    {
	      conditions.push(this._startDate);
	    }

	    if(this._endDate != null)
	    {
	      conditions.push(this._endDate);
	    }

	    var where = null;
	    if(conditions.length > 0 )
	    {
	      where = new MDSS.QueryXML.And();
	      for(var i=0; i<conditions.length; i++)
		    {
		      where.addCondition('date2', conditions[i]);
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
	    var column = new YAHOO.widget.Column(attribute.getColumnObject());
	    column.attribute = attribute;
	    column = this._queryPanel.insertColumn(column);

	    var attributeName = attribute.getAttributeName();
	    var selectable = attribute.getSelectable();
	    selectable.attribute = attribute;

	    this._visibleSelectables[attribute.getKey()] = selectable;

	    // ADD THEMATIC VARIABLE
	    this._queryPanel.addThematicVariable(attribute.getType(), attribute.getKey(), attribute.getDisplayLabel());
	  },

	  /**
	   * Adds a grid attribute as a selectable.
	   */
	  _addGridAttribute : function(attribute)
	  {
	    var column = new YAHOO.widget.Column(attribute.getColumnObject());
	    column.attribute = attribute;
	    column = this._queryPanel.insertColumn(column);

	    var optionName = attribute.getOptionName();

	    /*
	     * Relationship
	     */
	    var relationshipType = attribute.getRelationshipType();
	    var relationshipAlias = attribute.getRelationshipAlias();

	    var relEntity = new MDSS.QueryXML.Entity(relationshipType, relationshipAlias);

	    this._gridEntities[relationshipAlias] = relEntity;

	    // selectable
	    var rSelectable = attribute.getRelationshipSelectable();
	    this._gridSelectables[attribute.getKey()] = rSelectable;

	    /*
	     * Business
	     */
	    var type = attribute.getType();
	    var businessAlias = attribute.getBusinessAlias();

	    // entity and criteria
	    var entity = new MDSS.QueryXML.Entity(type, businessAlias);
	    var bSelectable = attribute.getBusinessSelectable();

	    var condition = new MDSS.QueryXML.BasicCondition(bSelectable, MDSS.QueryXML.Operator.EQ, optionName);
	    entity.setCondition(condition);

	    this._gridEntities[businessAlias] = entity;

	    // ADD THEMATIC VARIABLE
	    this._queryPanel.addThematicVariable(relationshipAlias, attribute.getKey(), attribute.getDisplayLabel());
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
	   * Removes a grid attribute from the selectables and column.
	   */
	  _removeGridAttribute : function(attribute, removeColumn, removeSelectable, removeThematic)
	  {
	  	var relAlias = attribute.getRelationshipAlias();

	  	if(removeSelectable)
	  	{
	      delete this._gridSelectables[attribute.getKey()];
	  	}

	  	// remove all possible query references
	    delete this._gridAggregateSelectables[attribute.getKey()];
	    delete this._gridGroupBySelectables[attribute.getKey()];

	    if(removeColumn)
	    {
	      var column = this._queryPanel.getColumn(attribute.getKey());
	      this._queryPanel.removeColumn(column);

	      delete this._gridEntities[attribute.getBusinessAlias()];
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
	      this._addVisibleAttribute(attribute);

	      check.nextSibling.disabled = false;
	      check.contextMenu = new YAHOO.widget.ContextMenu(
	      			                                "contextmenu"+attribute.getAttributeName(),
	      			                                {
	      			                                    trigger: liTarget,
	      			                                    itemdata: attribute._whereValues,
	      			                                    lazyload: true
	      			                                }
	      			                            );
	      //check.contextMenu.suscribe('beforeHideEvent',_addSelectedColumn)


	    }
	    else
	    {
	      this._removeVisibleAttribute(attribute, true, true, true);
	      check.contextMenu.destroy();
	      var select = check.nextSibling;
	      select.selectedIndex = 0;
	      select.disabled = true;
	    }
	  },

	  /**
	   * Handler when a new grid attribute is checked/unchecked.
	   */
	  _gridAttributeHandler : function(e, attribute)
	  {
	    var check = e.target;
	    if(check.checked)
	    {
	      this._addGridAttribute(attribute);
	      check.nextSibling.disabled = false;
	    }
	    else
	    {
	      this._removeGridAttribute(attribute, true, true, true);

	      var select = check.nextSibling;
	      select.selectedIndex = 0;
	      select.disabled = true;
	    }
	  },

	  _gridAggregateHandler : function(e, attribute)
	  {
	    var func = e.target.value;

	    var optionName = attribute.getOptionName();
	    var attributeName = attribute.getAttributeName();
	    var relationshipAlias = attribute.getRelationshipAlias();
	    var key = attribute.getKey();

	    var selectable = attribute.getRelationshipSelectable();

	    // special cases
	    if(func === MDSS.QueryXML.Functions.GB)
	    {
	  	  this._removeGridAttribute(attribute, false, false, false);
	      this._gridSelectables[key] = selectable;
	      this._gridGroupBySelectables[key] = selectable;
	      return;
	    }
	    else if(func === '')
	    {
	      // Use regular selectable (this is just here for clarity).
	  	  this._removeGridAttribute(attribute, false, true, false);
	      this._gridSelectables[key] = selectable;
	      return;
	    }

	    // aggregate functions
	    var aggFunc = null;
	    if(func === MDSS.QueryXML.Functions.SUM)
	    {
	      aggFunc = new MDSS.QueryXML.SUM(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.MIN)
	    {
	      aggFunc = new MDSS.QueryXML.MIN(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.MAX)
	    {
	      aggFunc = new MDSS.QueryXML.MAX(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.AVG)
	    {
	      aggFunc = new MDSS.QueryXML.AVG(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.COUNT)
	    {
	      aggFunc = new MDSS.QueryXML.COUNT(selectable, key);
	    }

	  	this._removeGridAttribute(attribute, false, true, false);

	    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
	    this._gridAggregateSelectables[key] = aggSelectable;
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


	    var option = e.target;

	    var selectable = attribute.getSelectable();

	    // special cases
	    if(func === MDSS.QueryXML.Functions.GB)
	    {
	  	  this._removeVisibleAttribute(attribute, false, false, false);
	      this._visibleSelectables[attribute.getKey()] = selectable;
	      this._visibleGroupBySelectables[attribute.getKey()] = selectable;
	      return;
	    }
	    else if(func === '')
	    {
	      // Use regular selectable (this is just here for clarity).
	  	  this._removeVisibleAttribute(attribute, false, true, false);
	      this._visibleSelectables[attribute.getKey()] = selectable;
	      return;
	    }

	    // aggregate functions
	    var aggFunc = null;
	    if(func === MDSS.QueryXML.Functions.SUM)
	    {
	      aggFunc = new MDSS.QueryXML.SUM(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.MIN)
	    {
	      aggFunc = new MDSS.QueryXML.MIN(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.MAX)
	    {
	      aggFunc = new MDSS.QueryXML.MAX(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.AVG)
	    {
	      aggFunc = new MDSS.QueryXML.AVG(selectable, key);
	    }
	    else if(func === MDSS.QueryXML.Functions.COUNT)
	    {
	      aggFunc = new MDSS.QueryXML.COUNT(selectable, key);
	    }

	  	this._removeVisibleAttribute(attribute, false, true, false);

	    var aggSelectable = new MDSS.QueryXML.Selectable(aggFunc);
	    this._visibleAggregateSelectables[attribute.getKey()] = aggSelectable;


	  },

	  _whereValueHandler: function(e,ee,obj){
	    obj.checked = ! obj.checked;
	    this.cfg.setProperty('checked', ! this.cfg.getProperty('checked'));
	    var listId = obj.attribute.getKey() + '_whereValues';
	    var list = document.getElementById(listId);
	    var liId = obj.uuid + "_summary";
	    if(obj.checked)
	    {
		    var li = document.createElement('li');
		    li.id = liId;
		    li.innerHTML = '&nbsp;●&nbsp;' + obj.text;
		    list.appendChild(li);
	    }
	    else
	    {
	    	var li = document.getElementById(liId);
	    	list.removeChild(li);
	    }
	  },


	  /**
	   * Builds the query items for the left column.
	   */
	  _buildQueryItems : function(visibleAttributes, assays)
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


	    /*
	     * Visible Attributes
	     */
	    function getVizDiv(that,visibleAttributes,divName,type)
	    {
		    var visibleDiv = document.createElement('div');
		    //YAHOO.util.Dom.addClass(visibleDiv, 'scrollable');

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

		    that._attachSelectAll(visibleUl);

		    for(var i=0; i<visibleAttributes.length; i++)
		    {
		      var visibleObj = visibleAttributes[i];
		      var attribute = new MDSS.VisibleAttribute(visibleObj);

		      var li = document.createElement('li');

		      var check = document.createElement('input');
		      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
		      YAHOO.util.Event.on(check, 'click', that._visibleAttributeHandler, attribute, that);
		      li.appendChild(check);

		      var select = document.createElement('select');
		      var options = [''];
		      options = options.concat(Mojo.util.getValues(MDSS.QueryXML.Functions));

		      for(var j=0; j<options.length; j++)
		      {
		      	var option = options[j];
		        var optionEl = document.createElement('option');
		        optionEl.innerHTML = option;
		        YAHOO.util.Dom.setAttribute(optionEl, 'value', option);

		        YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, attribute, this);

		        select.appendChild(optionEl);
		      }
		      select.disabled = true; // default (must be checked to enabled)

		      li.appendChild(select);
		      var span = document.createElement('span');
		      span.innerHTML = attribute.getDisplayLabel();
		      li.appendChild(span);

		      if (visibleObj.dropDownMap)
		      {
		      	var options = [''];
		      	var displayLabels = [''];
			      options = options.concat(Mojo.util.getKeys(visibleObj.dropDownMap));
			      displayLabels = displayLabels.concat(Mojo.util.getValues(visibleObj.dropDownMap));
			      for(var j=0; j<options.length; j++)
			      {
			      	attribute._whereValues.push({
			        		checked : false,
			        		text : displayLabels[j],
			        		id:options[j],
			        		uuid:options[j],
			        		myIndex:j,
			        		attribute:attribute,
			        		onclick: {
			      				fn: that._whereValueHandler
			      	  }
			        });
			      	//give menu onclick handler reference back to the original attribute
			        attribute._whereValues[attribute._whereValues.length-1].onclick.obj = attribute._whereValues[attribute._whereValues.length-1];
			      }
		      }

		      visibleUl.appendChild(li);
		    }

		    visibleDiv.appendChild(visibleUl);

		    return visibleDiv;
	    }

	    this._queryPanel.addQueryItem({
	      html: getVizDiv(this,visibleAttributes,"Individuals",Mojo.$.dss.vector.solutions.entomology.MosquitoView.CLASS),
	      id: 'visibleAttributesItem'
	    });

	    this._queryPanel.addQueryItem({
	      html: getVizDiv(this,assays[0],"Infectivity_Assays",Mojo.$.dss.vector.solutions.entomology.MosquitoView.CLASS),
	      id: 'Infectivity_Assays'
	    });

	    this._queryPanel.addQueryItem({
	      html: getVizDiv(this,assays[1],"Molecular_Assays",Mojo.$.dss.vector.solutions.entomology.MosquitoView.CLASS),
	      id: 'Molecular_Assays'
	    });

	    this._queryPanel.addQueryItem({
	      html: getVizDiv(this,assays[2],"Biochemical_Assays",Mojo.$.dss.vector.solutions.entomology.MosquitoView.CLASS),
	      id: 'Biochemical_Assays'
	    });

	  },
	  /**
	   * Attaches an option to select all items in the given list.
	   */
	  _attachSelectAll : function(ul)
	  {
	    var check = document.createElement('input');
	    YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
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
	  	var type = this._mosquitoView.getType();
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
