<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="itemId">
    <mjl:input type="text" param="itemId"/>
  </mjl:dt>
  <mjl:dt attribute="itemName">
    <mdss:mo param="itemName" value="${itemName}"/>
  </mjl:dt>
  <mjl:dt attribute="quantity">
    <fmt:formatNumber minFractionDigits="2" var="formatQuantity" value="${item.quantity}" />
    <mjl:input type="text" param="quantity" value="${formatQuantity}" />  
  </mjl:dt>
  <mjl:dt attribute="unit">
    <mdss:mo param="unit" value="${unit}"/>
  </mjl:dt>
</mjl:component>