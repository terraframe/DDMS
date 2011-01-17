<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ResistancePropertyDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.form.id" name="dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.form.edit.button" value="Edit" action="dss.vector.solutions.entomology.ResistancePropertyDisplayLabelController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.viewAll.link" action="dss.vector.solutions.entomology.ResistancePropertyDisplayLabelController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
