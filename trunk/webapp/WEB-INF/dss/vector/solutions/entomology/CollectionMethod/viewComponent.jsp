<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.CollectionMethod.form.name" id="dss.vector.solutions.entomology.CollectionMethod.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="termName">
      ${item.termName}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.CollectionMethodController.edit.mojo" name="dss.vector.solutions.entomology.CollectionMethod.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.CollectionMethodController.viewAll.mojo" name="dss.vector.solutions.entomology.CollectionMethod.viewAll.link" />
