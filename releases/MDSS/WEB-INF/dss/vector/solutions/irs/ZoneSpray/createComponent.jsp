<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="page_title" value="Create_Zone_Spray_Title"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mjl:command value="Create" action="dss.vector.solutions.irs.ZoneSprayController.create.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.create.button" />
  </dl>
</mjl:form>
