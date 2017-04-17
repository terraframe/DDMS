<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="page_title" value="Create_Aggregated_Case"  scope="request"/>

<%@ include file="form.jsp"%>

<div class="pageTitle"> <mdss:localize key="Enter_data_for_ages"/> ${item.ageGroup.displayLabel} </div> 

<mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="form.id" method="POST">
  <dl>
  </dl>
</mjl:form>
