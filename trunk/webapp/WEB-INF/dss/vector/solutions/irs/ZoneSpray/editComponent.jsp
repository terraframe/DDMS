<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="page_title" value="Edit_Zone_Spray"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Update" action="dss.vector.solutions.irs.ZoneSprayController.update.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.irs.ZoneSprayController.delete.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.irs.ZoneSprayController.cancel.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.cancel.button" />
  </dl>
</mjl:form>
