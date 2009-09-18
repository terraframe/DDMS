<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Search_ITNDistribution" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <mjl:component item="${item}" param="itn">
      <mjl:dt attribute="facility">
        <mjl:input value="${item.facility != null ? item.facility.geoId : ''}" type="text" param="facilityId" classes="geoInput" id="geoIdEl" />
        <mjl:input type="hidden" param="facility" id="geoIdEl_geoEntityId" />
      </mjl:dt>
      <mjl:dt attribute="distributionDate" >
        <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="batchNumber">
        <mjl:input type="text" param="batchNumber" />
      </mjl:dt>
    </mjl:component>

    <mjl:component item="${recipient}" param="recipient">
      <mjl:dt attribute="firstName">
        <mjl:input type="text" param="firstName" />
      </mjl:dt>
      <mjl:dt attribute="lastName">
        <mjl:input type="text" param="lastName" />
      </mjl:dt>
    </mjl:component>
        
    <mjl:command value="Search" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.searchRecipient.mojo" name="searchRecipient" />
  </mjl:form>
</dl>
