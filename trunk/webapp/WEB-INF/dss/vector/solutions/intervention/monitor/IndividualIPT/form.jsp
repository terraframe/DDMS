<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('${healthFacility}*');
</script>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input param="iptCase" type="hidden" value="${item.iptCase.id}" />
  <mjl:dt attribute="facility">
    <input type="hidden" id="typeSearchFilter" value="${healthFacility}" />      
    <mjl:input classes="geoInput" id="geoIdEl" param="facility" type="text" />
  </mjl:dt>
  <mjl:dt attribute="serviceDate" classes="DatePick" id="serviceDate" type="text"/>  
  <mjl:dt attribute="patientType">
    <mjl:radioGroup param="patientType" items="${patientType}" var="current" valueAttribute="id">
      <mjl:radioOption checked="${item.patientType != null && item.patientType.id == current.id ? checked : 'false'}">
        ${current.displayLabel}
      </mjl:radioOption>
    </mjl:radioGroup>
  </mjl:dt>
  <mjl:dt attribute="age">
    <mjl:input param="age" type="text" />
  </mjl:dt>
  <mjl:dt attribute="isANCVisit">
    <mjl:boolean param="isANCVisit" id="isANCVisit" />
  </mjl:dt>
  <mjl:dt attribute="visitNumber" classes="visitNumber">
    <mjl:select param="visitNumber" items="${visitNumber}" var="current" valueAttribute="id" classes="visitNumber" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="doseNumber">
    <mjl:select param="doseNumber" items="${doseNumber}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="doseType">
    <mjl:select param="doseType" items="${doseType}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="recievedSupplement">
    <mjl:boolean param="recievedSupplement" />
  </mjl:dt>
  <mjl:dt attribute="recievedITN">
    <mjl:boolean param="recievedITN" id="recievedITN" />
  </mjl:dt>
  <mjl:dt attribute="numberOfRecievedITNs" classes="numberOfRecievedITNs">
    <mjl:input param="numberOfRecievedITNs" type="text" classes="numberOfRecievedITNs" />
  </mjl:dt>
  <mjl:dt attribute="administratorName">
    <mjl:input param="administratorName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="administratorSurname">
    <mjl:input param="administratorSurname" type="text" />
  </mjl:dt>
</mjl:component>

<script type="text/javascript" defer="defer">
<!--
// Setup the option fields for batch number
MDSS.ElementHandler.setupBooleanHandler('isANCVisit.positive', 'isANCVisit.negative', 'visitNumber');
MDSS.ElementHandler.setupBooleanHandler('recievedITN.positive', 'recievedITN.negative', 'numberOfRecievedITNs');
-->
</script>
