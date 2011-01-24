<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_StockItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.stock.StockItem.form.id" name="dss.vector.solutions.stock.StockItem.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="itemName">
        ${item.itemName.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="quantity">
        ${item.quantity}
      </mjl:dt>
      <mjl:dt attribute="unit">
        ${item.unit.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.stock.StockItem.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.stock.StockItemController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.stock.StockItem.viewAll.link" action="dss.vector.solutions.stock.StockItemController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
