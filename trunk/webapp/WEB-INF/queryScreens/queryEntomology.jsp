<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<jsp:include page="../templates/header.jsp"></jsp:include>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

// mosquito definition
  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);   
    
    String[] types = new String[]{MosquitoDTO.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

(function(){
  
  YAHOO.util.Event.onDOMReady(function(){

    var thematicVariableColumnKey = null;

    /**
     * Final function called before query is executed.
     * Any last minute cleanup is done here. The this
     * reference is that of the QueryPanel.
     */
    function executeQuery()
    {
      // always clear any prior conditions
      mosquitoEntity.clearCondition();
    
      var conditions = [];
    
      // Add start and end dates WHERE criteria
      // if values exist
      var startDateEl = this.getStartDate();
      var startDate = MDSS.util.stripWhitespace(startDateEl.get('value'));
      if(startDate.length > 0)
      {
        var formatted = MojoCal.getMojoDateString(startDate);

        var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), '<%= MosquitoDTO.TESTDATE %>');
        var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
        var startDateCondition = new MDSS.QueryXML.BasicCondition(selectable, MDSS.QueryXML.Operator.GE, formatted);
        conditions.push(startDateCondition);
      }

      var endDateEl = this.getEndDate();
      var endDate = MDSS.util.stripWhitespace(endDateEl.get('value'));
      if(endDate.length > 0)
      {
        var formatted = MojoCal.getMojoDateString(endDate);
      
        var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), '<%= MosquitoDTO.TESTDATE %>');
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
      var xml = this.getQuery().getXML();
      
      var request = new MDSS.Request({
        onSuccess : function(query)
        {
          // column key is selectable alias name
          var columnSet = queryPanel.getColumnSet();
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
          queryPanel.clearAllRecords();
          
          queryPanel.setRowData(jsonData);
          queryPanel.enableMapping();       
        }
      });
      
      Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml);
    }
  
    /**
     * Handler called to generate a map with a thematic variable.
     */  
    function mapQuery()
    {
      var xml = this.getQuery().getXML();
      alert(xml);
    }
    
    /**
     * Displays the SelectSearch (handler for click event on Area menu).
     */
    function displaySearch()
    {
      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
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
       
       /**
        * Handler for a selected GeoEntity. The selected GeoEntity
        * is added as restricting criteria and the type is added
        * as a column for the query output.
        */
       function selectHandler(selected, allSelected)
       {
         var listIdSuffix = '_entry';
       
         var bestFit = allSelected[allSelected.length-1];
         
         // Earth is not allowed in the Select
         if(bestFit.getType() === '<%= EarthDTO.CLASS %>')
         {
           return;
         }
         
         // do nothing if the GeoEntith has already been added
         if(YAHOO.util.Dom.inDocument(bestFit.getId()+listIdSuffix))
         {
           return;
         }
         
         var type = bestFit.getType();
         var typeName = type.substring(type.lastIndexOf('.')+1);
         
         var entityNameColumn = typeName+'_'+bestFit.getEntityNameMd().getName();
         var geoIdColumn = typeName+'_'+bestFit.getGeoIdMd().getName();
         
         // instantiate new DTO to get Metadata display label
         // (not ideal, but it gets the job done)
         var _constructor = Mojo.util.getType(type);
         var temp = new _constructor();
         
         // Add entity as restricting criteria to right column
         var li = document.createElement('li');
         YAHOO.util.Dom.setAttribute(li, 'id', bestFit.getId()+listIdSuffix);
         li.innerHTML = bestFit.getEntityName() + " " + bestFit.getGeoId();
         
         var rightUnit = queryPanel.getRightUnit();
         var ul = rightUnit.body.firstChild;
         ul.appendChild(li);
         
         // only add the column if it does not exist
         if(queryPanel.getColumn(entityNameColumn) == null)
         {
           var obj = {
             key: entityNameColumn,
             label: (temp.getTypeMd().getDisplayLabel() + " " + bestFit.getEntityNameMd().getDisplayLabel())
           };
         
           var column = new YAHOO.widget.Column(obj);
           queryPanel.insertColumn(column);
         }
         
         if(queryPanel.getColumn(geoIdColumn) == null)
         {
           var obj = {
             key: geoIdColumn,
             label: (temp.getTypeMd().getDisplayLabel() + " " + bestFit.getGeoIdMd().getDisplayLabel())
           };
         
           var column = new YAHOO.widget.Column(obj);
           queryPanel.insertColumn(column);
         }

         // add the GeoEntity as restricting criteria
         // FIXME not compatible w/ two entities of the same type
         var geoEntityQuery = query.getEntity(type);
         if(geoEntityQuery == null)
         {
           var geoEntityQuery = new MDSS.QueryXML.Entity(type, type);
           query.addEntity(bestFit.getType(), geoEntityQuery); 

           // selectables (entityName, geoId and spatial attribute)
           var entityNameAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getEntityNameMd().getName(), entityNameColumn);
           var entityNameSel = new MDSS.QueryXML.SimpleSelectable(entityNameAttr);
         
           query.addSelectable(type+'_'+entityNameAttr.getName(), entityNameSel);

           var geoIdAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), bestFit.getGeoIdMd().getName(), geoIdColumn);
           var geoIdSel = new MDSS.QueryXML.SimpleSelectable(geoIdAttr);
         
           query.addSelectable(type+'_'+geoIdAttr.getName(), geoIdSel, geoIdAttr.getName());

           // geo entities either have getPoint or getPolygon
           var attributeName = '';
           if('getPoint' in bestFit)
           {
             attributeName = 'point';
           }
           else if('getLineString' in bestFit)
           {
             attributeName = 'lineString';
           }
           else if('getPolygon' in bestFit)
           {
             attributeName = 'polygon';
           }
           else if('getMultiPoint' in bestFit)
           {
             attributeName = 'multiPoint';
           }
           else if('getMultiLineString' in bestFit)
           {
             attributeName = 'multiLineString';
           }
           else if('getMultiPolygon' in bestFit)
           {
             attributeName = 'multiPolygon';
           }
           
           var geoAttr = new MDSS.QueryXML.Attribute(geoEntityQuery.getAlias(), attributeName, typeName+'_'+attributeName);
           var geoSel = new MDSS.QueryXML.SimpleSelectable(geoAttr);
         
           //query.addSelectable(type+'_'+geoAttr.getName(), geoSel);
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
          
       MDSS.SelectSearch.initialize(selectHandler, selectHandler, '');
      }
    }
    
    /**
     * Execute when the user requests that the given
     * column is to be removed from the table.
     */
    function removeSelectableColumn(columnId)
    {
      var column = queryPanel.getColumn(columnId);
    }
    
    var queryPanel = new MDSS.QueryPanel('queryPanel', {
      startDateLabel: "Start Date",
      endDateLabel: "End Date",
      mapButtonLabel: "Map",
      runButtonLabel: "Run",
      executeQuery: executeQuery,
      mapQuery: mapQuery
    });
    var query = queryPanel.getQuery();

    function clickTrueSpecies(e, obj)
    {
      trueSpeciesHandler(obj);
    }

    function addTrueSpecies(eventType, event, obj)
    {
      trueSpeciesHandler(obj);
    }
    
    function trueSpeciesHandler(obj)
    {
      // do nothing if the column already exists
      if(queryPanel.getColumn(obj.key) != null)
      {
        return;
      }
    
      var column = new YAHOO.widget.Column(obj);
      queryPanel.insertColumn(column);
      
      // add Specie entity to the query
      var specieEntity = new MDSS.QueryXML.Entity('<%= SpecieDTO.CLASS %>', 'specie');
      query.addEntity('specie', specieEntity);
      
      var dlAttribute = new MDSS.QueryXML.Attribute(specieEntity.getAlias(), '<%= SpecieDTO.DISPLAYLABEL %>');
      var dlSelectable = new MDSS.QueryXML.SimpleSelectable(dlAttribute);
      query.addSelectable(specieEntity.getAlias()+'_<%= SpecieDTO.DISPLAYLABEL %>', dlSelectable);
    }
  
    function clickSpeciesRatio(e, obj)
    {
      speciesRatioHandler(obj);
    }
  
    function addSpeciesRatio(eventType, event, obj)
    {
      speciesRatioHandler(obj);
    }
    
    function speciesRatioHandler(obj)
    {
      var column = new YAHOO.widget.Column(obj);
      queryPanel.insertColumn(column);
    }
  
    function addAssayHandler(eventType, event, obj)
    {
      var column = new YAHOO.widget.Column(obj);
      queryPanel.insertColumn(column);
    }

    /**
     * Builds menu items for attributes native to Mosquito
     */
    function buildMenuForAttributes(column)
    {
      var items = [];
      
      // all attributes are removable
      items.push({
        text: "Remove", onclick: {fn: removeAttribute, obj:{column: column}}
      });
      
      // enable thematic selection if mapping is enabled
      if(queryPanel.isMappingEnabled())
      {
        items.push({
          text: "Mark Thematic", onclick: {fn: markThematic, obj:{column: column}}
        });
      }
      
      return items;
    }
    
    /**
     * Handler to remove Mosquito attributes from the table
     * and query.
     */
    function removeAttribute(eventType, event, obj)
    {
      var column = obj.column;
      query.removeSelectable(mosquitoEntity.getAlias()+'_'+column.getKey());
      queryPanel.removeColumn(column);
    }
    
    function markThematic(eventType, event, obj)
    {
      var thematicClass = "thematicVariable";
      
      // remove current thematic variable
      var currentColumn = queryPanel.getColumn(thematicVariableColumnKey);
      if(currentColumn != null)
      {
        YAHOO.util.Dom.removeClass(currentColumn.getThEl().firstChild, thematicClass);
      }
      
      // add new thematic var
      var column = obj.column;
      YAHOO.util.Dom.addClass(column.getThEl().firstChild, thematicClass);
      
      thematicVariableColumnKey = column.getKey();
    }

    /**
     * Helper method to add mosquito attributes to selectables and as a column.
     */
    function addAttribute(attributeObj)
    {
      var column = new YAHOO.widget.Column(attributeObj);
      column = queryPanel.insertColumn(column, buildMenuForAttributes);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), attributeObj.key, attributeObj.key);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      query.addSelectable(mosquitoEntity.getAlias()+'_'+column.getKey(), selectable);
    }
    
    // area (geo entity search)
    queryPanel.addQueryItem({
      displayLabel: 'Area <img src="./imgs/icons/world.png"/>',
      onclick: {handler: displaySearch},
      id: "areaItem"
    });
    
    // true species
    var tsObj = {key: "trueSpecies", label: "True Species"};
    queryPanel.addQueryItem({
      displayLabel: tsObj.label,
      onclick: {handler: clickTrueSpecies, obj: tsObj},
      id:"trueSpeciesItem",
      menuBuilder: (function(onClickHandler, obj){
        return function(){
          return [
            {text:"Add", onclick: {fn: onClickHandler, obj: obj}}
          ];
        };
      })(addTrueSpecies, tsObj)
    });
    
    // species ratio
    var srObj = {key: "speciesRatio", label: "Species Ratio"};
    queryPanel.addQueryItem({
      displayLabel: srObj.label,
      id:"speciesRatioItem",
      onclick: {handler: clickSpeciesRatio, obj: srObj},
      menuBuilder: (function(onClickHandler, obj){
        return function(){
          return [
            {text:"Add", onclick: {fn: onClickHandler, obj: obj}}
          ];
        }
      })(addSpeciesRatio, srObj)
    });
    
    // assays
    var assays = <%= (String) request.getAttribute("treeJSON") %>;
    
    // recurses through the assay hierarchy and builds menu items
    function recurseAssays(parent)
    {
      var parentItem = {};

      parentItem.text = parent.displayLabel;
      
      // add click handler
      if(!parent.isAbstract)
      {
        parentItem.onclick = {
          fn: addAssayHandler,
          obj: {
            key: parent.type,
            label: parent.displayLabel
          }
        };
      }
      
      var children = parent.children;
      if(children.length > 0)
      {
        var submenu = {};
        submenu.id = parent.id;
        submenu.itemdata = [];
              
        for(var i=0; i<children.length; i++)
        {
          var child = children[i];
          var childItem = recurseAssays(child);
          submenu.itemdata.push(childItem);
        }
        
        parentItem.submenu = submenu;
      }
      
      return parentItem;
    }
    
    var assayMenuData = [];
    assayMenuData.push(recurseAssays(assays[0]));
    
    queryPanel.addQueryItem({
      displayLabel: "Assay",
      id: "assayItem",
      menuBuilder: (function(menuData){
        return function(){
          return menuData;
        };
      })(assayMenuData)
    });
    
    // render the panel    
    queryPanel.render();
    
    // modify the right panel to accept GeoEntity data as a list
    var rightUnit = queryPanel.getRightUnit();
    var body = rightUnit.body;
    var ul = document.createElement('ul');
    YAHOO.util.Dom.addClass(ul, 'geoEntityPanelList');
    body.appendChild(ul);

    /*
     * Mosquito entity and select attributes (will automatically be added to the query select)
     */
    <% ClassQueryDTO query = (ClassQueryDTO) request.getAttribute("query"); %>

    // entity definition
    var mosquitoEntity = new MDSS.QueryXML.Entity('<%= query.getType() %>', '<%= query.getType() %>');
    query.addEntity('mosquito', mosquitoEntity);
    
    // generation
    <%
      JSONObject generation = new JSONObject();
      generation.put("key", query.getAttributeDTO(MosquitoDTO.GENERATION).getAttributeMdDTO().getName());
      generation.put("label", query.getAttributeDTO(MosquitoDTO.GENERATION).getAttributeMdDTO().getDisplayLabel());
    %>
    addAttribute(<%= generation.toString() %>);
    
    // isofemale
    <%
      JSONObject isofemale = new JSONObject();
      isofemale.put("key", query.getAttributeDTO(MosquitoDTO.ISOFEMALE).getAttributeMdDTO().getName());
      isofemale.put("label", query.getAttributeDTO(MosquitoDTO.ISOFEMALE).getAttributeMdDTO().getDisplayLabel());
    %>
    addAttribute(<%= isofemale.toString() %>);
    
    // sex
    <%
      JSONObject sex = new JSONObject();
      sex.put("key", query.getAttributeDTO(MosquitoDTO.SEX).getAttributeMdDTO().getName());
      sex.put("label", query.getAttributeDTO(MosquitoDTO.SEX).getAttributeMdDTO().getDisplayLabel());
    %>
    addAttribute(<%= sex.toString() %>);
    
    // test date
    <%
      JSONObject testDate = new JSONObject();
      testDate.put("key", query.getAttributeDTO(MosquitoDTO.TESTDATE).getAttributeMdDTO().getName());
      testDate.put("label", query.getAttributeDTO(MosquitoDTO.TESTDATE).getAttributeMdDTO().getDisplayLabel());
    %>
    addAttribute(<%= testDate.toString() %>);
    
    
    
  });
  
})();
</script>

<div class="yui-skin-sam">
  <div id="queryPanel" class="queryPanel"></div>
</div>
<div id="cal1Container" class="yui-skin-sam"></div>
<jsp:include page="../templates/footer.jsp"></jsp:include>