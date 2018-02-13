<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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