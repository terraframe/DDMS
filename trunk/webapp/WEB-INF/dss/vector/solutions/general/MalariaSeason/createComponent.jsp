<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Create_Malaria_Season" scope="request" />
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
  <mdss:localize key="Create" var="Localized_Create" />
  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.general.MalariaSeasonController.create.mojo" name="dss.vector.solutions.general.MalariaSeason.form.create.button" />
</mjl:form>
