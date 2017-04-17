<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_ExcelFieldBinding" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBinding.form.name" id="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBinding.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="columnHeader">
        ${item.columnHeader}
      </mjl:dt>
      <mjl:dt attribute="columnLabel">
        ${item.columnLabel}
      </mjl:dt>
      <mjl:dt attribute="columnType">
        ${item.columnType}
      </mjl:dt>
      <mjl:dt attribute="mdAttribute">
        ${item.mdAttribute.keyName}
      </mjl:dt>
      <mjl:dt attribute="sourceDefinition">
        ${item.sourceDefinition.keyName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBinding.form.edit.button" action="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBindingController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBinding.viewAll.link" action="dss.vector.solutions.kaleidoscope.data.etl.ExcelFieldBindingController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
