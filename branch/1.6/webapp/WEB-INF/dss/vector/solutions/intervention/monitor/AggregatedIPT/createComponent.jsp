<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set var="page_title" value="Create_Aggregated_IPT"  scope="request"/>

<mjl:form name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.name" id="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Create" var="Localized_Create" />
  
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.create.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.create.button" />
  </dl>
</mjl:form>
