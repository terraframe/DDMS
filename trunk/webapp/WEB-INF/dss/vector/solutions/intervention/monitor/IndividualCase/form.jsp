<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component item="${individualCase}" param="dto">
  <mjl:dt type="text" attribute="caseEntryDate" classes="DatePick" />
  <mjl:dt type="text" attribute="caseReportDate" classes="DatePick" />
  <mjl:dt type="text" attribute="diagnosisDate" classes="DatePick" />
  <mjl:dt attribute="probableSource">
    <mjl:input value="${individualCase.probableSource != null ? individualCase.probableSource.geoId : ''}" type="text" param="probableSourceId" classes="geoInput" id="geoIdEl" />
    <mjl:input type="hidden" param="probableSource" id="geoIdEl_geoEntityId" />
  </mjl:dt>
</mjl:component>
