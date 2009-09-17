<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_MO" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.MO.form.id" name="dss.vector.solutions.ontology.MO.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
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
    <mjl:command name="dss.vector.solutions.ontology.MO.form.edit.button" value="Edit" action="dss.vector.solutions.ontology.MOController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.ontology.MO.viewAll.link" action="dss.vector.solutions.ontology.MOController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
