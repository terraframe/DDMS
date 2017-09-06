<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@page import="com.runwaysdk.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.runwaysdk.transport.attributes.AttributeDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.stock.StockItemDTO"%>
<%@page import="dss.vector.solutions.stock.StockEventDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>




<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.stock.StockEventViewDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Stock"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ StockItemDTO.CLASS, StockEventDTO.CLASS, PersonDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
// Setting both values to false will select *all* univerals


YAHOO.util.Event.onDOMReady(function(){

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);


    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var stockMaps = {<%=(String) request.getAttribute("stockMap")%>};
    
    var personMaps = {};

    var stockEvent = new Mojo.$.dss.vector.solutions.stock.StockEvent();

    var stockEventAttribs = ['stockDepot', 'eventDate','transactionType','quantity', 'cost','otherParty'];
    <%
    Halp.setReadableAttributes(request, "stockEventAttribs", StockEventViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("stockEventAttribs") %>);
    stockEventAttribs = Mojo.Iter.filter(stockEventAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var stockEventColumns =   stockEventAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:stockEvent, suffix:'_stockEvent', dropDownMaps:stockMaps});
    MDSS.QueryBase.filterFunctions(stockEventColumns, ['cost_stockEvent'], MDSS.QueryXML.F_SET1);
    
    var stockItem = new Mojo.$.dss.vector.solutions.stock.StockItem();

    var stockItemAttribs = ['itemId','itemName','quantity','unit'];
    <%
    Halp.setReadableAttributes(request, "stockItemAttribs", StockItemViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("stockItemAttribs") %>);
    stockItemAttribs = Mojo.Iter.filter(stockItemAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var stockItemColumns =   stockItemAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:stockItem, suffix:'_stockItem', dropDownMaps:stockMaps});

    // remove the functions from the quantity selectable
    MDSS.QueryBase.filterFunctions(stockItemColumns, 'quantity_stockItem', []);
    
    var quantityInStock = "quanity_instock";
    stockItemColumns = stockItemColumns.concat([

                                          {
                                          	 isAggregate:true,
                                             key:quantityInStock,
                                             type:"sqlinteger",
                                             attributeName:quantityInStock
                                           }]);

    var person = new Mojo.$.dss.vector.solutions.Person();
    
    var personAttribs = ["firstName","lastName"];
    <%
    Halp.setReadableAttributes(request, "personAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("personAttribs") %>);
    personAttribs = Mojo.Iter.filter(personAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var personColumns =  personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_per', dropDownMaps:personMaps});

    var selectableGroups = [
              {title:"StockItems", values:stockItemColumns, group:"e", klass:stockItem.CLASS},
              {title:"StockEvents", values:stockEventColumns, group:"e", klass:stockEvent.CLASS},
              {title:"Staff", values:personColumns, group:"e", klass:person.CLASS}
    ];

    var query = new MDSS.QueryStock(selectableGroups, queryList);
        
    query.render();

    var dm = query.getDependencyManager();

    var dateIds = query.getDateGroupIds();
    var nonItems = stockEventColumns.concat(personColumns, dateIds);
    dm.excludes({
      independent: quantityInStock,
      dependent: nonItems,
      type: MDSS.Dependent.CHECKED,
      bidirectional: true
    });


});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>
<jsp:include page="../templates/footer.jsp"></jsp:include>
