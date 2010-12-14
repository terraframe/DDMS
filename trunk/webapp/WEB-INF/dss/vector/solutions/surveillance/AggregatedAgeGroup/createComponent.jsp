<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Create_Age_Group"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.name" id="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Create" id="create_button" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.create.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.create.button" />
  </dl>
</mjl:form>
