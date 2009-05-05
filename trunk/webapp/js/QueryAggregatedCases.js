MDSS.QueryAggregatedCases = (function(){

  var _selectSearch = null;

  var _preconfiguredColumns = [];

  var _queryPanel = null;

  var _queryXML = null;

  /**
   * Loads available queries.
   */
  function _loadQuery(savedSearchId)
  {
    var request = new MDSS.Request({
      onSuccess: function(savedSearchJSON){
        var savedSearchObj = Mojo.util.getObject(savedSearchJSON);

        _queryPanel.setCurrentSavedSearch(savedSearchObj);

        // set the XML

        // set the layers
      }
    });

    Mojo.$.dss.vector.solutions.query.EntomologySearch.get(request, savedSearchId);
  }

  /**
   * Saves the query.
   */
  function _saveQueryListener(modal, params, action)
  {
    // FIXME hardcoded for testing
    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;

    var xml = _queryXML.getXML();

    var view = new Mojo.$.dss.vector.solutions.query.SavedSearchView();
    view.setQueryName(params['savedQueryView.queryName']);
    view.setQueryXml(xml);
    view.setThematicLayer(sentinelSite);

    var request = new MDSS.Request({
      modal:modal,
      onSuccess: function(savedSearchJSON){

        var savedSearchObj = Mojo.util.getObject(savedSearchJSON);

        var obj = {id: savedSearchObj.id, name: savedSearchObj.queryName };
        _queryPanel.addAvailableQuery(obj);
        _queryPanel.setCurrentSavedSearch(savedSearchObj);
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.QueryController.saveQuery(request, view);
  }

  function _cancelQueryListener(modal, params, action)
  {
    modal.destroy();
  }

  /**
   * Saves the current state of the QueryXML.
   */
  function _saveQuery()
  {
    var controller = Mojo.$.dss.vector.solutions.query.QueryController;
    var request = new MDSS.Request({
      controller: controller,
      onSuccess: function(html)
      {

        var modal = _createModal(html, MDSS.Localized.Query.Save);

        var saved = MDSS.util.curry(_saveQueryListener, modal);
        var canceled = MDSS.util.curry(_cancelQueryListener, modal);
        this.controller.setSaveQueryListener(saved);
        this.controller.setCancelQueryListener(canceled);
      }
    });

    controller.newQuery(request);
  }

  /**
   * Final function called before query is executed.
   * Any last minute cleanup is done here. The this
   * reference is that of the QueryPanel.
   */
  function _executeQuery()
  {
    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    var mosquitoEntity = _queryXML.getEntity(mosquito);

    // always clear any prior conditions
    mosquitoEntity.clearCondition();

    var conditions = [];

    // Add start and end dates WHERE criteria
    // if values exist
    var startDateEl = this.getStartDate();
    var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
    var testDate = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;
    if(startDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(startDate);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), testDate);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
      conditions.push(startDateCondition);
    }

    var endDateEl = this.getEndDate();
    var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
    if(endDate.length > 0)
    {
      var formatted = MojoCal.getMojoDateString(endDate);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), testDate);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      var endDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.LE, formatted);
      conditions.push(endDateCondition);
    }

    if(conditions.length == 2)
    {
      var and = new MDSS.QueryXML.And();
      and.addCondition('date1', conditions[0]);
      and.addCondition('date2', conditions[1]);
      var compositeCondition = new MDSS.QueryXML.CompositeCondition(and);
      mosquitoEntity.setCondition(compositeCondition);
    }
    else if(conditions.length == 1)
    {
      var or = new MDSS.QueryXML.Or();
      or.addCondition('date1', conditions[0]);
      var compositeCondition = new MDSS.QueryXML.CompositeCondition(or);
      mosquitoEntity.setCondition(compositeCondition);
    }

    // execute the query
    var xml = _queryXML.getXML();

    var request = new MDSS.Request({
      onSuccess : function(query)
      {
        // column key is selectable alias name
        var columnSet = _queryPanel.getColumnSet();
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
        _queryPanel.clearAllRecords();

        _queryPanel.setRowData(jsonData);
      }
    });

    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;
    Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml, sentinelSite);
  }

  /**
   * Handler called to generate a map with a thematic variable.
   */
  function _mapQuery()
  {

    // FIXME hardcoded for testing
    var sentinelSite = Mojo.$.dss.vector.solutions.geo.generated.SentinelSite.CLASS;
    var mosquito = Mojo.$.dss.vector.solutions.entomology.Mosquito.CLASS;

    var types = [];
    types.push(sentinelSite);
    types.push(mosquito);

    var selectables = [];
    selectables.push(types[0]+'_geoId');
    var xml = _queryXML.getXMLForMap(types, selectables);

    var request = new MDSS.Request({
      queryPanelRef: this,
      onSuccess : function(layers){
        var layersObj = Mojo.util.getObject(layers);
        this.queryPanelRef.createMap(layersObj);
      }
    });

    var layerIds = this.getSelectedLayers();
    var savedSearch = this.getCurrentSavedSearch();
    var savedSearchId = savedSearch.id;
    Mojo.$.dss.vector.solutions.entomology.Mosquito.mapQuery(request, xml, sentinelSite, layerIds, savedSearchId);
  }

   /**
    * Handler for a selected GeoEntity. The selected GeoEntity
    * is added as restricting criteria and the type is added
    * as a column for the query output.
    */
   function _selectHandler(selected)
   {
     var listIdSuffix = '_entry';

     bestFit = selected;

     // Earth is not allowed in the Select
     var earth = Mojo.$.dss.vector.solutions.geo.generated.Earth.CLASS;
     if(bestFit.getEntityType() === earth)
     {
       return;
     }

     // do nothing if the GeoEntity has already been added
     if(YAHOO.util.Dom.inDocument(bestFit.getId()+listIdSuffix))
     {
       return;
     }

     var type = bestFit.getEntityType();
     var typeName = type.substring(type.lastIndexOf('.')+1);

     var entityNameColumn = typeName+'_'+bestFit.getEntityNameMd().getName();
     var geoIdColumn = typeName+'_'+bestFit.getGeoIdMd().getName();


     // only add the column if it does not exist
     if(_queryPanel.getColumn(entityNameColumn) == null)
     {
       var obj = {
         key: entityNameColumn,
         label: (bestFit.getTypeDisplayLabel() + " " + bestFit.getEntityNameMd().getDisplayLabel())
       };

       var column = new YAHOO.widget.Column(obj);
       _queryPanel.insertColumn(column);
     }

     if(_queryPanel.getColumn(geoIdColumn) == null)
     {
       var obj = {
         key: geoIdColumn,
         label: (bestFit.getTypeDisplayLabel() + " " + bestFit.getGeoIdMd().getDisplayLabel())
       };

       var column = new YAHOO.widget.Column(obj);
       _queryPanel.insertColumn(column);
     }

    // add the GeoEntity as restricting criteria
    // FIXME not compatible w/ two entities of the same type
    var geoEntityQuery = _queryXML.getEntity(type);
    if(geoEntityQuery == null)
    {
      var geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
      _queryXML.addEntity(geoEntityQuery);

      // selectables (entityName, geoId and spatial attribute)
      var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getEntityNameMd().getName(), entityNameColumn);
      var entityNameSel = new MDSS.QueryXML.SimpleSelectable(entityNameAttr);

      _queryXML.addSelectable(type+'_'+entityNameAttr.getName(), entityNameSel);

      var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName(), geoIdColumn);
      var geoIdSel = new MDSS.QueryXML.SimpleSelectable(geoIdAttr);

      _queryXML.addSelectable(type+'_'+geoIdAttr.getName(), geoIdSel, geoIdAttr.getName());
    }

    // add restriction based on geoId
    var attribute = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName());
    var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
    var geoIdCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.EQ, bestFit.getGeoId());

    var and = new MDSS.QueryXML.And();
    and.addCondition('geoIdCondition', geoIdCondition);
    var compositeCondition = new MDSS.QueryXML.CompositeCondition(and);
    geoEntityQuery.setCondition(compositeCondition);
  }

  /**
   * Displays the SelectSearch (handler for click event on Area menu).
   */
  function _displaySearch()
  {
    if(_selectSearch != null && _selectSearch.isInitialized())
    {
      _selectSearch.show();
    }
    else
    {
      var radios = YAHOO.util.Selector.query('input[type="radio"]', 'searchMosquitoCollections');
      var filterType = '';
      for(var i=0; i<radios.length; i++)
      {
        var radio = radios[i];
        if(radio.checked)
        {
          filterType = radio.value;
        }
      }

      _selectSearch = new MDSS.SingleSelectSearch();
      _selectSearch.setSelectHandler(_selectHandler);
      _selectSearch.setTreeSelectHandler(_selectHandler);
      _selectSearch.setFilter(filterType);
      _selectSearch.render();
    }
  }

  /**
   * Execute when the user requests that the given
   * column is to be removed from the table.
   */
  function _removeSelectableColumn(columnId)
  {
    var column = _queryPanel.getColumn(columnId);
  }

  /**
   * Handler to toggle grouping by startAge/endAge.
   */
  function _groupByAgeGroupHandler(e)
  {
    var check = e.target;

    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    var aggregatedCaseQuery = _queryXML.getEntity(aggregatedCase);
    var groupBy = _queryXML.getGroupBy();

    if(check.checked)
    {
      var startAge = new MDSS.QueryXML.Attribute(aggregatedCaseQuery.getAlias(), Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.STARTAGE);
      var startAgeSel = new MDSS.QueryXML.SimpleSelectable(startAge);

      var endAge = new MDSS.QueryXML.Attribute(aggregatedCaseQuery.getAlias(), Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.ENDAGE);
      var endAgeSel = new MDSS.QueryXML.SimpleSelectable(endAge);

      groupBy.addSelectable('startAge', startAgeSel);
      groupBy.addSelectable('endAge', endAgeSel);
    }
    else
    {
      groupBy.clearGroupBy();
    }
  }

  /**
   * Handler for when an age group checkbox is selected as WHERE criteria.
   */
  function _ageGroupCheckHandler(e, ageGroup)
  {
    var check = e.target;

    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    var aggregatedCaseQuery = _queryXML.getEntity(aggregatedCase);

    if(check.checked)
    {
      // All Ages was selected so simply clear the criteria
      // and other checkboxes
      if(ageGroup == null)
      {
        var checks = YAHOO.util.Selector.query('input[type="checkbox"]', 'ageGroupsList');
        for(var i=0; i<checks.length; i++)
        {
          var check = checks[i];
          if(check.checked && check.id !== 'allAgesCheck')
          {
            check.checked = false;
          }
        }

        aggregatedCaseQuery.clearCondition();

        return;
      }
      else
      {
        // uncheck the All Ages option
        document.getElementById('allAgesCheck').checked = false;
      }

      // check if OR conditions already exist for the age groups
      var orComposite = aggregatedCaseQuery.getCondition();
      var orCondition = null;
      if(orComposite == null)
      {
        var orCondition = new MDSS.QueryXML.Or();
        orComposite = new MDSS.QueryXML.CompositeCondition(orCondition);
        aggregatedCaseQuery.setCondition(orComposite);
      }
      else
      {
        orCondition = orComposite.getComponent();
      }

      var leftSide = new MDSS.QueryXML.Attribute(aggregatedCaseQuery.getAlias(), Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.STARTAGE);
      var leftSelectable = new MDSS.QueryXML.SimpleSelectable(leftSide);
      var leftCondition = new MDSS.QueryXML.BasicCondition(leftSelectable, MDSS.QueryXML.Operator.GE, ageGroup.startAge);

      var rightSide = new MDSS.QueryXML.Attribute(aggregatedCaseQuery.getAlias(), Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.ENDAGE);
      var rightSelectable = new MDSS.QueryXML.SimpleSelectable(rightSide);
      var rightCondition = new MDSS.QueryXML.BasicCondition(rightSelectable, MDSS.QueryXML.Operator.LT, ageGroup.endAge);

      var and = new MDSS.QueryXML.And();
      and.addCondition('startAge', leftCondition);
      and.addCondition('endAge', rightCondition);
      var andCondition = new MDSS.QueryXML.CompositeCondition(and);

      orCondition.addCondition(ageGroup.id, andCondition);
    }
    else if(ageGroup != null)
    {
      // removes the query criteria
      var orComposite = aggregatedCaseQuery.getCondition();
      var orCondition = orComposite.getComponent();
      orCondition.removeCondition(ageGroup.id);
    }
  }

  /**
   * Helper method to add AggregatedCase attributes to selectables and as a column.
   */
  function _addAttribute(attributeObj)
  {
    var column = new YAHOO.widget.Column(attributeObj);
    //column = _queryPanel.insertColumn(column, _buildMenuForAttributes);
    column = _queryPanel.insertColumn(column);

    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    var aggregatedCaseQuery = _queryXML.getEntity(aggregatedCase);

    var attribute = new MDSS.QueryXML.Attribute(aggregatedCaseQuery.getAlias(), attributeObj.key, attributeObj.key);
    var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
    _queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+column.getKey(), selectable);
  }

  /**
   * Builds the query items for the left column.
   */
  function _buildQueryItems(ageGroups, visibleAttributes)
  {
  	/*
  	 * Target
  	 */
    // area (geo entity search)
    _queryPanel.addQueryItem({
      html: MDSS.Localized.Target_Search+' <img src="./imgs/icons/world.png"/>',
      onclick: {handler: _displaySearch},
      id: "areaItem"
    });

    /*
     * Age Group
     */
    var ageGroupDiv = document.createElement('div');

    var ageSpan = document.createElement('span');
    ageSpan.innerHTML = MDSS.Localized.Age_Group;

    // group by
    var groupBy = document.createElement('ul');
    var groupByLi = document.createElement('li');
    var groupBySpan = document.createElement('span');

    var groupByCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(groupByCheck, 'type', 'checkbox');
    // send null to signal this is for All Ages
    YAHOO.util.Event.on(groupByCheck, 'click', _groupByAgeGroupHandler, null, this);

    groupBySpan.innerHTML = 'GB';

    groupByLi.appendChild(groupByCheck);
    groupByLi.appendChild(groupBySpan);
    groupBy.appendChild(groupByLi);


    var groups = document.createElement('ul');
    YAHOO.util.Dom.setAttribute(groups, 'id', 'ageGroupsList');

    // default All Ages option
    var allLi = document.createElement('li');
    var allSpan = document.createElement('span');
    allSpan.innerHTML = MDSS.Localized.All_Ages;

    var allCheck = document.createElement('input');
    YAHOO.util.Dom.setAttribute(allCheck, 'type', 'checkbox');
    YAHOO.util.Dom.setAttribute(allCheck, 'id', 'allAgesCheck');
    // send null to signal this is for All Ages
    YAHOO.util.Event.on(allCheck, 'click', _ageGroupCheckHandler, null, this);

    allLi.appendChild(allCheck);
    allLi.appendChild(allSpan);

    groups.appendChild(allLi);

    for(var i=0; i<ageGroups.length; i++)
    {
      var group = ageGroups[i];

      var li = document.createElement('li');
      var span = document.createElement('span');
      span.innerHTML = group.startAge + " - " + group.endAge;

      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(check, 'value', group.id);
      YAHOO.util.Event.on(check, 'click', _ageGroupCheckHandler, group, this);

      li.appendChild(check);
      li.appendChild(span);

      groups.appendChild(li);
    }

    ageGroupDiv.appendChild(ageSpan);
    ageGroupDiv.appendChild(groupBy);
    ageGroupDiv.appendChild(groups);

    _queryPanel.addQueryItem({
      html: ageGroupDiv,
      id:"ageGroupItem"
    });

    /*
     * Visible Attributes
     */
    var visibleDiv = document.createElement('div');
  }

  /**
   * Builds the column information (pre-render) for the table
   * in the the QueryPanel.
   */
  function _buildColumns()
  {
    // Define the columns for the AggregatedCase attributes
    // (uses a new instance as a template)
    var aggregatedCase = new Mojo.$.dss.vector.solutions.surveillance.AggregatedCase();

    // startAge
    _preconfiguredColumns.push({
      key: aggregatedCase.getStartAgeMd().getName(),
      label: aggregatedCase.getStartAgeMd().getDisplayLabel()
    });

    // endAge
    _preconfiguredColumns.push({
      key: aggregatedCase.getEndAgeMd().getName(),
      label: aggregatedCase.getEndAgeMd().getDisplayLabel()
    });
  }

  /**
   * Adds a layer of the given type to the map.
   * The map is not refreshed.
   */
  function _addLayer(type)
  {
    var request = new MDSS.Request({
      type: type,
      onSuccess: function(layerId){

        layerId = MDSS.util.stripWhitespace(layerId);
        _queryPanel.addDefinedLayer(layerId, this.type);
      }
    });

    var savedSearch = this.getCurrentSavedSearch();
    var savedSearchId = savedSearch.id;
    Mojo.$.dss.vector.solutions.query.MappingController.createLayer(request, savedSearchId, type);
  }

  /**
   * Locks a layer and its components to put them
   * in edit mode.
   */
  function _editLayer(layerId)
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;

    var request = new MDSS.Request({
      layerId: layerId,
      controller: controller,
      onSuccess: function(html){

        var modal = _createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.curry(_updateLayerListener, modal);
        var canceled = MDSS.util.curry(_cancelLayerListener, modal, layerId, true);
        this.controller.setUpdateLayerListener(update);
        this.controller.setCancelLayerListener(canceled);
      }
    });

    controller.editLayer(request, layerId);
  }

  function _updateLayerListener(modal, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){

        this.modal.destroy();
      }
    });

    return request;
  }

  function _cancelLayerListener(modal, layerId, unlock)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function(){
        this.modal.destroy();
      }
    });

    Mojo.$.dss.vector.solutions.query.MappingController.cancelLayer(request, layerId, unlock);
  }

  /**
   *
   */
  function _deleteLayer()
  {

  }

  /**
   * Listener for when a use requesets a new AbstractCategory.
   * The returned HTML form is appended to the list of editable
   * categories.
   */
  function _newCategoryListener(params, action)
  {
    var request = new MDSS.Request({
      onSuccess: function(html)
      {
        _queryPanel.addCategoryHTML(html);
      }
    });

    return request;
  }

  function _editThematicVariable(modal, layerId, params, action)
  {
    var request = new MDSS.Request({
      modal: modal,
      onSuccess: function()
      {
        this.modal.destroy();
      }
    });


    var thematicVar = params['variable'][0];
    var categories = _queryPanel.scrapeCategories();
    Mojo.$.dss.vector.solutions.query.MappingController.updateThematicVariable(request, layerId, thematicVar, categories);
  }

  /**
   * Handler to edit the styles associated with a thematic variable.
   */
  function _editVariableStyles()
  {
    var controller = Mojo.$.dss.vector.solutions.query.MappingController;
    var savedSearch = this.getCurrentSavedSearch();
    var thematicLayerId = savedSearch.thematicLayerId;

    var request = new MDSS.Request({
      controller: controller,
      thematicLayerId: thematicLayerId,
      onSuccess: function(html){

        var modal = _createModal(html, MDSS.Localized.Update);

        var update = MDSS.util.curry(_editThematicVariable, modal, this.thematicLayerId);
        var canceled = MDSS.util.curry(_cancelLayerListener, modal, this.thematicLayerId,true);
        this.controller.setUpdateThematicVariableListener(update);
        this.controller.setCancelLayerListener(canceled);

        Mojo.$.dss.vector.solutions.query.RangeCategoryController.setNewInstanceListener(_newCategoryListener);
        Mojo.$.dss.vector.solutions.query.NonRangeCategoryController.setNewInstanceListener(_newCategoryListener);
      }
    });

    controller.editThematicLayer(request, thematicLayerId, ['dss.vector.solutions.entomology.Mosquito[seq]']);
  }

  /**
   * Creates a modal with the given HTML as its body and the given title
   * as the modal title, wrapped in an H3.
   */
  function _createModal(html, title)
  {
    var executable = MDSS.util.extractScripts(html);
    var html = MDSS.util.removeScripts(html);

    var modal = new YAHOO.widget.Panel("editQuery", {
      width:"400px",
      height: "400px",
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
    YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
    contentDiv.innerHTML = html;
    outer.appendChild(contentDiv);

    modal.setBody(outer);
    modal.render(document.body);

    eval(executable);

    return modal;
  }

  /**
   * Initializes the configuration for the QueryPanel to
   * Query on Aggregated Cases.
   */
  function _initialize(ageGroups, visibleAttributes, queryList)
  {
    _queryPanel = new MDSS.QueryPanel('queryPanel', 'mapPanel', {
      executeQuery: _executeQuery,
      mapQuery: _mapQuery,
      saveQuery: _saveQuery,
      loadQuery: _loadQuery,
      addLayer: _addLayer,
      editLayer: _editLayer,
      deleteLayer: _deleteLayer,
      editVariableStyles: _editVariableStyles
    });

    for(var i=0; i<queryList.length; i++)
    {
      _queryPanel.addAvailableQuery(queryList[i]);
    }

    _queryXML = new MDSS.QueryXML.Query();

    // We will always be querying on AggregatedCase, so include that by default
    var aggregatedCase = Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;
    var aggregatedCaseQuery = new MDSS.QueryXML.Entity(aggregatedCase, aggregatedCase);
    _queryXML.addEntity(aggregatedCaseQuery);

    _buildQueryItems(ageGroups, visibleAttributes);

    _buildColumns();

  }

  /**
   * Renders the QueryPanel to query on Entomology.
   */
  function _render()
  {
    // render the panel
    _queryPanel.render();

    // modify the right panel to accept GeoEntity data as a list
    var rightUnit = _queryPanel.getQueryRightUnit();
    var body = rightUnit.body;
    var ul = document.createElement('ul');
    YAHOO.util.Dom.addClass(ul, 'geoEntityPanelList');
    body.appendChild(ul);

    // add pre-configured columns
    for(var i=0; i<_preconfiguredColumns.length; i++)
    {
      var column = _preconfiguredColumns[i];
      _addAttribute(column);
    }
  }

  // public methods/proeprties
  return {
    initialize : _initialize,
    render: _render
  };

})();