<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Create_Net"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.NetController.create.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.create.button" />
  </dl>
</mjl:form>
