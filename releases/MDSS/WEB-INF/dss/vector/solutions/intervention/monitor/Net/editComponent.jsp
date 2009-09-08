<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Edit_Net"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%> 
    
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.NetController.update.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.NetController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.NetController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.cancel.button" />
  </dl>
</mjl:form>
