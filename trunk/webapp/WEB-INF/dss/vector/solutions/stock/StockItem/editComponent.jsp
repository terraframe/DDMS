<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_StockItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.stock.StockItem.form.id" name="dss.vector.solutions.stock.StockItem.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.stock.StockItem.form.update.button" value="Update" action="dss.vector.solutions.stock.StockItemController.update.mojo" />
    <mjl:command name="dss.vector.solutions.stock.StockItem.form.delete.button" value="Delete" action="dss.vector.solutions.stock.StockItemController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.stock.StockItem.form.cancel.button" value="Cancel" action="dss.vector.solutions.stock.StockItemController.cancel.mojo" />
  </mjl:form>
</dl>
