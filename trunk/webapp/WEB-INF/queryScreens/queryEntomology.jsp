<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
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
      
      var request = new Mojo.ClientRequest({
        onSuccess : function(query)
        {
          // add query results to table
          var names = query.getAttributeNames();
          
          var resultSet = query.getResultSet();
          var jsonData = [];
          for(var i=0; i<resultSet.length; i++)
          {
            var result = resultSet[i];
            
            var entry = {};
            for(var j=0; j<names.length; j++)
            {
              var name = names[j];
              entry[name] = result.getAttributeDTO(name).getValue();
            }
            
            jsonData.push(entry);
          }
          
          queryPanel.setRowData(jsonData);
          queryPanel.enableMapButton();       
        },
        onFailure : function(e)
        {
          alert(e.getDeveloperMessage());
        }
      });
      
      Mojo.$.dss.vector.solutions.entomology.Mosquito.queryEntomology(request, xml);
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
        
       function selectHandler(selected, allSelected)
       {
         var bestFit = allSelected[allSelected.length-1];
         
         var obj = {
           key: bestFit.getId(),
           label: bestFit.getTypeMd().getDisplayLabel()
         };
         var column = new YAHOO.widget.Column(obj);
         column = queryPanel.insertColumn(column);

         /*
         var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), attributeObj.key);
         var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
         query.addSelectable(mosquitoEntity.getAlias()+'_'+attributeObj.key, selectable);
         */
       }
          
       MDSS.SelectSearch.initialize(selectHandler, selectHandler, '');
      }
    }
    
    var queryPanel = new MDSS.QueryPanel('queryPanel', {
      startDateLabel: "Start Date",
      endDateLabel: "End Date",
      mapButtonLabel: "Map",
      runButtonLabel: "Run",
      executeQuery: executeQuery
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
    
    function removeAttribute(eventType, event, obj)
    {
      queryPanel.removeColumn(obj.column);
    }

    /**
     * Helper method to add attributes to selectables and as a column.
     */
    function addSelectable(attributeObj)
    {
      var column = new YAHOO.widget.Column(attributeObj);
      column = queryPanel.insertColumn(column);

      var attribute = new MDSS.QueryXML.Attribute(mosquitoEntity.getAlias(), attributeObj.key, attributeObj.key);
      var selectable = new MDSS.QueryXML.SimpleSelectable(attribute);
      query.addSelectable(mosquitoEntity.getAlias()+'_'+attributeObj.key, selectable);
    }
    
    // area (geo entity search)
    queryPanel.addQueryItem({
      displayLabel: "Area",
      menuData: [
        {text:"Search", onclick: {fn: displaySearch}}
      ]
    });
    
    // true species
    var tsObj = {key: "trueSpecies", label: "True Species"};
    queryPanel.addQueryItem({
      displayLabel: tsObj.label,
      onclick: {handler: clickTrueSpecies, obj: tsObj},
      menuData: [
        {text:"Add", onclick: {fn: addTrueSpecies, obj: tsObj}}
      ]
    });
    
    // species ratio
    var srObj = {key: "speciesRatio", label: "Species Ratio"};
    queryPanel.addQueryItem({
      displayLabel: srObj.label,
      onclick: {handler: clickSpeciesRatio, obj: srObj},
      menuData: [
        {text:"Add", onclick: {fn: addSpeciesRatio, obj: srObj}}
      ]
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
      menuData: assayMenuData
    });
    
    // render the panel    
    queryPanel.render();

    /*
     * Mosquito entity and select attributes (will automatically be added to the query select)
     */
    <% ClassQueryDTO query = (ClassQueryDTO) request.getAttribute("query"); %>

    // entity definition
    var mosquitoEntity = new MDSS.QueryXML.Entity('<%= query.getType() %>', 'mosquito');
    query.addEntity('mosquito', mosquitoEntity);
    
    // generation
    <%
      JSONObject generation = new JSONObject();
      generation.put("key", query.getAttributeDTO(MosquitoDTO.GENERATION).getAttributeMdDTO().getName());
      generation.put("label", query.getAttributeDTO(MosquitoDTO.GENERATION).getAttributeMdDTO().getDisplayLabel());
    %>
    addSelectable(<%= generation.toString() %>);
    
    // isofemale
    <%
      JSONObject isofemale = new JSONObject();
      isofemale.put("key", query.getAttributeDTO(MosquitoDTO.ISOFEMALE).getAttributeMdDTO().getName());
      isofemale.put("label", query.getAttributeDTO(MosquitoDTO.ISOFEMALE).getAttributeMdDTO().getDisplayLabel());
    %>
    addSelectable(<%= isofemale.toString() %>);
    
    // sex
    <%
      JSONObject sex = new JSONObject();
      sex.put("key", query.getAttributeDTO(MosquitoDTO.SEX).getAttributeMdDTO().getName());
      sex.put("label", query.getAttributeDTO(MosquitoDTO.SEX).getAttributeMdDTO().getDisplayLabel());
    %>
    addSelectable(<%= sex.toString() %>);
    
    // test date
    <%
      JSONObject testDate = new JSONObject();
      testDate.put("key", query.getAttributeDTO(MosquitoDTO.TESTDATE).getAttributeMdDTO().getName());
      testDate.put("label", query.getAttributeDTO(MosquitoDTO.TESTDATE).getAttributeMdDTO().getDisplayLabel());
    %>
    addSelectable(<%= testDate.toString() %>);
    
    
    
  });
  
})();
</script>

<div class="yui-skin-sam">
  <div id="queryPanel" class="queryPanel"></div>
</div>
<div id="cal1Container" class="yui-skin-sam"></div>
<jsp:include page="../templates/footer.jsp"></jsp:include>