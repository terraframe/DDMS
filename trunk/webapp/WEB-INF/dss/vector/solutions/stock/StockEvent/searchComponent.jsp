<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.stock.StockEventDTO"%>


<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%><c:set var="page_title" value="Stock_Detail"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>


<mjl:form name="StockDetail.search.mojo" method="POST">
  <dl>
    <dt>
      <label> ${view.stockDepotMd.displayLabel} </label>
    </dt>
    <dd>
      <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/>
    </dd>
    <dt>
      <label> ${view.itemMd.displayLabel} </label>
    </dt>
    <dd>
      <span class="clickable browserLauncher" id="itemNameBtn"> <fmt:message key="Browser"/></span>
      <div id="itemNameDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${item != null}">
              ${item.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
      </div>
      <mjl:input type="hidden" param="item.componentId" id="itemName" value="${item != null ? item.id : ''}" />
    </dd>
    <dt>
      <label> ${view.eventDateMd.displayLabel} </label>
    </dt>
    <dd>
      <mjl:input param="date" type="text" classes="DatePick NoFuture" id="date"/>
    </dd>    
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchInStock.mojo" name="inStock" value="In_Stock" id="inStock"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchOutStock.mojo" name="outStock" value="Out_Stock" id="outStock"/>
    <dt>
      <label> <fmt:message key="endDate" /> </label>
    </dt>
    <dd>
      <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
    </dd>    
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchPage.mojo" name="searchPage" value="Search" id="searchPage"/>    
  </dl>
</mjl:form>
<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'itemName'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=StockItemViewDTO.CLASS%>", attributes);
  })
})();
</script>

