<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.stock.StockEventDTO"%>
<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%>
<%@page import="dss.vector.solutions.geo.generated.StockDepotDTO"%>

<c:set var="page_title" value="Stock_Detail"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="StockDepot" scope="request"><%=StockDepotDTO.CLASS%></c:set>
<c:set var="StockItemView" scope="request"><%=StockItemViewDTO.CLASS%></c:set>

<mjl:form name="StockDetail.search.mojo" method="POST">
  <dl>
    <dt>
      <label> ${view.stockDepotMd.displayLabel} </label>
    </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" value="${geoId}" filter="${StockDepot}" />      
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
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchInStock.mojo" name="inStock" value="In_Stock" id="inStock"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchOutStock.mojo" name="outStock" value="Out_Stock" id="outStock"/>
    <dt>
      <label> <fmt:message key="endDate" /> </label>
    </dt>
    <dd>
      <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate" value="${endDate}"/>
    </dd>    
    <mjl:command classes="submitButton" action="dss.vector.solutions.stock.StockEventController.searchPage.mojo" name="searchPage" value="Search" id="searchPage"/>    
  </dl>
</mjl:form>