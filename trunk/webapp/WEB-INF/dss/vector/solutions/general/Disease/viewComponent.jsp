<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_Disease" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.Disease.form.id" name="dss.vector.solutions.general.Disease.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="dimension">
        ${item.dimension.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="menuRoot">
        ${item.menuRoot.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command localize="false" name="dss.vector.solutions.general.Disease.form.edit.button" value="Edit" action="dss.vector.solutions.general.DiseaseController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.Disease.viewAll.link" action="dss.vector.solutions.general.DiseaseController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
