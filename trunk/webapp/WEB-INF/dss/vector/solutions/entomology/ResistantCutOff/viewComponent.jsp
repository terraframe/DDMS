<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.ResistantCutOff.form.name" id="dss.vector.solutions.entomology.ResistantCutOff.form.id" method="POST">

 <mjl:component item="${item}" param="dto">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="aAKnockDownPR">
      ${item.aAKnockDownPR}
</mjl:dt>
    <mjl:dt attribute="aAKnockDownR">
      ${item.aAKnockDownR}
</mjl:dt>
    <mjl:dt attribute="aDDAR">
      ${item.aDDAR}
</mjl:dt>
    <mjl:dt attribute="aDDAS">
      ${item.aDDAS}
</mjl:dt>
    <mjl:dt attribute="lAKnockDownPR">
      ${item.lAKnockDownPR}
</mjl:dt>
    <mjl:dt attribute="lAKnockDownR">
      ${item.lAKnockDownR}
</mjl:dt>
    <mjl:dt attribute="lDDAR">
      ${item.lDDAR}
</mjl:dt>
    <mjl:dt attribute="lDDAS">
      ${item.lDDAS}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.ResistantCutOffController.edit.mojo" name="dss.vector.solutions.entomology.ResistantCutOff.form.edit.button" />
  <br />
  </mjl:component>
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.ResistantCutOffController.viewAll.mojo" name="dss.vector.solutions.entomology.ResistantCutOff.viewAll.link" />
