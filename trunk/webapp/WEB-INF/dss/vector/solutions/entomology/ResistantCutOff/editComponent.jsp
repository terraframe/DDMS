<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.ResistantCutOff.form.name" id="dss.vector.solutions.entomology.ResistantCutOff.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="aAKnockDownPR">
<mjl:input type="text" param="aAKnockDownPR" />
</mjl:dt>
      <mjl:dt attribute="aAKnockDownR">
<mjl:input type="text" param="aAKnockDownR" />
</mjl:dt>
      <mjl:dt attribute="aDDAR">
<mjl:input type="text" param="aDDAR" />
</mjl:dt>
      <mjl:dt attribute="aDDAS">
<mjl:input type="text" param="aDDAS" />
</mjl:dt>
      <mjl:dt attribute="lAKnockDownPR">
<mjl:input type="text" param="lAKnockDownPR" />
</mjl:dt>
      <mjl:dt attribute="lAKnockDownR">
<mjl:input type="text" param="lAKnockDownR" />
</mjl:dt>
      <mjl:dt attribute="lDDAR">
<mjl:input type="text" param="lDDAR" />
</mjl:dt>
      <mjl:dt attribute="lDDAS">
<mjl:input type="text" param="lDDAS" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.ResistantCutOffController.update.mojo" name="dss.vector.solutions.entomology.ResistantCutOff.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.ResistantCutOffController.delete.mojo" name="dss.vector.solutions.entomology.ResistantCutOff.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.ResistantCutOffController.cancel.mojo" name="dss.vector.solutions.entomology.ResistantCutOff.form.cancel.button" />
</mjl:form>
