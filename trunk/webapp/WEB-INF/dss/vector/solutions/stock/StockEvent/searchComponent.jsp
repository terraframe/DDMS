<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.stock.StockEventDTO"%>
<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.StockDepotDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<c:set var="page_title" value="Stock_Detail"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="StockItemView" scope="request"><%=StockItemViewDTO.CLASS%></c:set>

<mjl:form name="StockDetail.search.mojo" method="POST" id="searchStockEventForm">
  <dl>
    <dt>
      <label> ${view.stockDepotMd.displayLabel} </label>
    </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" universals="${entityUniversals}" value="${geoId}" filter="${StockDepot}" />      
    </dd>
    <dt>
      <label> ${view.itemMd.displayLabel} </label>
    </dt>
    <dd>
      <mdss:mo param="item.componentId" id="itemName" browserClass="${StockItemView}" browserAttribute="itemName" value="${item}"/>
    </dd>
    <dt>
      <label> ${view.eventDateMd.displayLabel} </label>
    </dt>
    <dd>
      <mjl:input param="date" type="text" classes="DatePick NoFuture" id="date" value="${date}"/>
    </dd>    
    <mdss:localize key="In_Stock" var="Localized_In_Stock" />    
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchInStock.mojo" name="inStock" value="${Localized_In_Stock}" id="inStock" />
    <mdss:localize key="Out_Stock" var="Localized_Out_Stock" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchOutStock.mojo" name="outStock" value="${Localized_Out_Stock}" id="outStock" />
    <dt>
      <label> <mdss:localize key="endDate_stock" /> </label>
    </dt>
    <dd>
      <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate" value="${endDate}"/>
    </dd>    
    <mdss:localize key="Search" var="Localized_Search" />    
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchPage.mojo" name="searchPage" value="${Localized_Search}" id="searchPage" />    
    
    <c:if test="${canDeleteAll}">
      <button class="submitButton" id="delete.all.button">
        <mdss:localize key="Delete_All"/>
      </button>      
    </c:if>    
  </dl>
</mjl:form>

<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function(){
  var buttonEl = document.getElementById("delete.all.button");
  
  if(buttonEl != null)
  {
    var onclick = function(e)
    {      
      if(e != null)
      {
        e.preventDefault();        
      }
      
      // Ensure the user wants to delete all of the objects
      if(confirm(MDSS.localize('confirm_delete_all')))
      {
        var evt = document.createEvent("HTMLEvents");
        evt.initEvent("submit", false, true);
        
        var formEl = document.getElementById("searchStockEventForm");
        formEl.action = "dss.vector.solutions.stock.StockEventController.deleteAll.mojo";
        formEl.dispatchEvent(evt);
      }
    };
    
    YAHOO.util.Event.on(buttonEl, 'click', onclick);
  }
});


</script>