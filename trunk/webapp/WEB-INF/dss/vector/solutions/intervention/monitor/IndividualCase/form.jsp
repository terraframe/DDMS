<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component item="${individualCase}" param="dto">
  <mjl:dt type="text" attribute="caseReportDate" classes="DatePick" />
  <mjl:dt type="text" attribute="diagnosisDate" classes="DatePick" />
  <mjl:dt attribute="residence">
    <mjl:input value="${individualCase.residence != null ? individualCase.residence.geoId : ''}" type="text" param="residenceId" classes="geoInput" id="residenceGeoId" />
    <mjl:input type="hidden" param="residence" id="residenceGeoId_geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="residenceText">
    <mjl:textarea param="residenceText" cols="3" rows="3"/>
  </mjl:dt>
  <mjl:dt attribute="probableSource">
    <mjl:input value="${individualCase.probableSource != null ? individualCase.probableSource.geoId : ''}" type="text" param="probableSourceId" classes="geoInput" id="probableSourceGeoId" />
    <mjl:input type="hidden" param="probableSource" id="probableSourceGeoId_geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="probableSourceText">
    <mjl:textarea param="probableSourceText" cols="3" rows="3"/>
  </mjl:dt>
  <mjl:dt attribute="workplace">
    <mjl:input value="${individualCase.workplace != null ? individualCase.workplace.geoId : ''}" type="text" param="workplaceId" classes="geoInput" id="workplaceGeoId" />
    <mjl:input type="hidden" param="workplace" id="workplaceGeoId_geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="workplaceText">
    <mjl:textarea param="workplaceText" cols="3" rows="3"/>
  </mjl:dt>
</mjl:component>
