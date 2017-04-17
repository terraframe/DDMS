<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Create_ITN_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.ITNData.form.name" id="dss.vector.solutions.intervention.monitor.ITNData.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Create" var="Localized_Create" />
  
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.intervention.monitor.ITNDataController.create.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.create.button" />
  </dl>
</mjl:form>
