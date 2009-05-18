<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="Edit_Malaria_Season" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.MalariaSeason.form.name" id="dss.vector.solutions.general.MalariaSeason.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="seasonName" type="text" />
      <mjl:dt attribute="startDate" type="text" classes="DatePick" />
      <mjl:dt attribute="endDate" type="text" classes="DatePick" />
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.general.MalariaSeasonController.update.mojo" name="dss.vector.solutions.general.MalariaSeason.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.general.MalariaSeasonController.delete.mojo" name="dss.vector.solutions.general.MalariaSeason.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.general.MalariaSeasonController.cancel.mojo" name="dss.vector.solutions.general.MalariaSeason.form.cancel.button" />
</mjl:form>
