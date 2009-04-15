<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.IdentificationMethod.form.name" id="dss.vector.solutions.entomology.IdentificationMethod.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="termName">
      ${item.termName}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.IdentificationMethodController.edit.mojo" name="dss.vector.solutions.entomology.IdentificationMethod.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.IdentificationMethodController.viewAll.mojo" name="dss.vector.solutions.entomology.IdentificationMethod.viewAll.link" />
