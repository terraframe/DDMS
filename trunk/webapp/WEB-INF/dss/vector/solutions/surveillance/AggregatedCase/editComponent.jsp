<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%@ include file="form.jsp"%>

<div class="pageTitle"> <fmt:message key="Enter_data_for_ages"/> ${item.ageGroup.startAge} &lt; ${item.ageGroup.endAge} </div> 

<mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="dss.vector.solutions.surveillance.AggregatedCase.form.id" method="POST">
  
  <%@ include file="writeForm.jsp"%>
  
  <mjl:command value="Update" action="dss.vector.solutions.surveillance.AggregatedCaseController.update.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.surveillance.AggregatedCaseController.delete.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.AggregatedCaseController.cancel.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.cancel.button" />
</mjl:form>
