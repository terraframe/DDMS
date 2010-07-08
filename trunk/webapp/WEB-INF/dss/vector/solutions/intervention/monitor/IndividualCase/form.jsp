<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<%@include file="personHeader.jsp" %>
<mjl:component item="${individualCase}" param="dto">
  <mjl:dt type="text" attribute="caseReportDate" classes="DatePick NoFuture" />
  <mjl:dt type="text" attribute="diagnosisDate" classes="DatePick NoFuture" />
  <mjl:dt attribute="residence">
    <mdss:geo param="residence" value="${individualCase.residence}" />  
  </mjl:dt>
  <mjl:dt attribute="residenceText">
    <mjl:textarea param="residenceText" cols="3" rows="3"/>
  </mjl:dt>
  <mjl:dt attribute="probableSource">
    <mdss:geo param="probableSource" value="${individualCase.probableSource}" />  
  </mjl:dt>
  <mjl:dt attribute="probableSourceText">
    <mjl:textarea param="probableSourceText" cols="3" rows="3"/>
  </mjl:dt>
  <mjl:dt attribute="workplace">
    <mdss:geo param="workplace" value="${individualCase.workplace}" />  
  </mjl:dt>
  <mjl:dt attribute="workplaceText">
    <mjl:textarea param="workplaceText" cols="3" rows="3"/>
  </mjl:dt>
  <mjl:dt attribute="otherSettlements">
    <mjl:textarea param="otherSettlements" cols="3" rows="3"/>
  </mjl:dt>
      <mjl:dt type="text" attribute="symptomOnset" classes="DatePick  NoFuture" />
  <mjl:dt attribute="origin">
    <mdss:mo param="origin" value="${origin}" />
  </mjl:dt>
  <mjl:dt attribute="plasmaLeakageOnset">
    <mjl:input type="text" param="plasmaLeakageOnset" id="plasmaLeakageOnset" classes="DatePick NoFuture" />
  </mjl:dt>
  <mjl:dt attribute="hemorrhagicOnset">
    <mjl:input type="text" param="hemorrhagicOnset" id="hemorrhagicOnset" classes="DatePick NoFuture" />
  </mjl:dt>
</mjl:component>
