<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.PersonController.create.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.create.button" />
  </dl>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>