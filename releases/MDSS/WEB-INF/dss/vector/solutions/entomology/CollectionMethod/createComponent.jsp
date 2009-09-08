<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.CollectionMethod.form.name" id="dss.vector.solutions.entomology.CollectionMethod.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="termName">
<mjl:input type="text" param="termName" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.CollectionMethodController.create.mojo" name="dss.vector.solutions.entomology.CollectionMethod.form.create.button" />
</mjl:form>
