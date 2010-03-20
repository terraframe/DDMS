<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_GeoUniversal" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.GeoUniversal.form.name" id="dss.vector.solutions.ontology.GeoUniversal.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="obsolete">
        ${item.obsolete ? item.obsoleteMd.positiveDisplayLabel : item.obsoleteMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="ontology">
        ${item.ontology.id}
      </mjl:dt>
      <mjl:dt attribute="termComment">
        ${item.termComment}
      </mjl:dt>
      <mjl:dt attribute="termId">
        ${item.termId}
      </mjl:dt>
      <mjl:dt attribute="termName">
        ${item.termName}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.ontology.GeoUniversalController.edit.mojo" name="dss.vector.solutions.ontology.GeoUniversal.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.GeoUniversalController.viewAll.mojo" name="dss.vector.solutions.ontology.GeoUniversal.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
