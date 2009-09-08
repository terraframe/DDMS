<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_Seepage" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Seepage.form.name" id="dss.vector.solutions.geo.generated.Seepage.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="multiPolygon">
        ${item.multiPolygon}
      </mjl:dt>
      <mjl:dt attribute="activated">
        ${item.activated ? item.activatedMd.positiveDisplayLabel : item.activatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="entityName">
        ${item.entityName}
      </mjl:dt>
      <mjl:dt attribute="gazId">
        ${item.gazId}
      </mjl:dt>
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.geo.generated.SeepageController.edit.mojo" name="dss.vector.solutions.geo.generated.Seepage.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.geo.generated.SeepageController.viewAll.mojo" name="dss.vector.solutions.geo.generated.Seepage.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
