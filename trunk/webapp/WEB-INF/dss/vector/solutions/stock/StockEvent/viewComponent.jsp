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
    <mjl:command name="dss.vector.solutions.stock.StockEvent.form.edit.button" value="Edit" action="dss.vector.solutions.stock.StockEventController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.stock.StockEvent.viewAll.link" action="dss.vector.solutions.stock.StockEventController.viewAll..mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
