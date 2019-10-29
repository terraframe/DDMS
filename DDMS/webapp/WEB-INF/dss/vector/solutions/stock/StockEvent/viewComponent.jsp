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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_StockEvent" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.stock.StockEvent.form.id" name="dss.vector.solutions.stock.StockEvent.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="cost">
        ${item.cost}
      </mjl:dt>
      <mjl:dt attribute="eventDate">
        <span class="formatDate">
          ${item.eventDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="item">
        ${item.item.id}
      </mjl:dt>
      <mjl:dt attribute="otherParty">
        ${item.otherParty}
      </mjl:dt>
      <mjl:dt attribute="quantity">
        ${item.quantity}
      </mjl:dt>
      <mjl:dt attribute="staff">
        ${item.staff.id}
      </mjl:dt>
      <mjl:dt attribute="stockDepot">
        ${item.stockDepot.geoId}
      </mjl:dt>
      <mjl:dt attribute="transactionType">
        <ul>
          <c:forEach items="${item.transactionTypeEnumNames}" var="enumName">
            <li>
              ${item.transactionTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.stock.StockEvent.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.stock.StockEventController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.stock.StockEvent.viewAll.link" action="dss.vector.solutions.stock.StockEventController.viewAll..mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
