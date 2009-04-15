<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.SexMaster.form.name" id="dss.vector.solutions.entomology.SexMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="displayLabel">
<mjl:input type="text" param="displayLabel" />
</mjl:dt>
      <mjl:dt attribute="enumName">
<mjl:input type="text" param="enumName" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.SexMasterController.create.mojo" name="dss.vector.solutions.entomology.SexMaster.form.create.button" />
</mjl:form>
