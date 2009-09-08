<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Edit_Age_Group"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.name" id="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.update.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.update.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.cancel.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.cancel.button" />
  </dl>
</mjl:form>
