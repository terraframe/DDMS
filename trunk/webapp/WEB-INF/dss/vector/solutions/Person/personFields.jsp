<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
</script>
  <mjl:messages>
    <mjl:message />
  </mjl:messages>
  <mjl:component item="${item}" param="person">
    <mjl:input type="hidden" param="personId" value="${item.personId}" />
    
    <mjl:input type="hidden" param="isIPTRecipient" value="${item.isIPTRecipient}" />
    <mjl:input type="hidden" param="isITNRecipient" value="${item.isITNRecipient}" />
    <mjl:input type="hidden" param="isPatient" value="${item.isPatient}" />
    
      <mjl:dt attribute="firstName" type="text" />
      <mjl:dt attribute="lastName" type="text"/>      
      <mjl:dt attribute="residentialGeoId" >
        <mjl:input id="geoIdEl" param="residentialGeoId" type="text" value="${item.residentialGeoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
      <mjl:dt attribute="dateOfBirth" >
        <mjl:input type="text" param="dateOfBirth" id="dateOfBirth" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sexes}" param="sex">
          <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
      </mjl:dt>      
      <dt>
        <label>
          ${item.isMDSSUserMd.displayLabel}?
          <span id="userSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isMDSSUser}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="userInput" type="hidden" value="${item.isMDSSUser}" param="isMDSSUser" />
        <div id="userDiv" style="display: ${item.isMDSSUser ? 'block' : 'none'}">
          <fieldset>
            <dt>
              <label>
                ${item.usernameMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="username" />
              <mjl:messages attribute="username">
                <mjl:message />
              </mjl:messages>
            </dd>
            <dt>
              <label>
                ${item.passwordMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="password" param="password" id="password" />
              <mjl:messages attribute="password">
                <mjl:message />
              </mjl:messages>
            </dd>
            <dt>
              <label>
                <fmt:message key="password_confirmation"/>                
              </label>
            </dt>
            <dd>
              <mjl:input type="password" param="repassword" id="repassword" />
              <mjl:messages attribute="repassword">
                <mjl:message />
              </mjl:messages>
            </dd>
          </fieldset>
        </div>
      </dd>
<%--  Removed from screen on 7/28/09 because of ticket #445

      <dt>
        <label>
          ${item.isIPTRecipientMd.displayLabel}
          <span id="iptSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isIPTRecipient}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="iptInput" type="hidden" value="${item.isIPTRecipient}" param="isIPTRecipient" />
        <div id="iptDiv" style="display: ${item.isIPTRecipient ? 'block' : 'none'}">
          <!-- IPT Recipient Fieldset -->
        </div>
      </dd>
      <dt>
        <label>
          ${item.isITNRecipientMd.displayLabel}
          <span id="itnSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isITNRecipient}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="itnInput" type="hidden" value="${item.isITNRecipient}" param="isITNRecipient" />
        <div id="itnDiv" style="display: ${item.isIPTRecipient ? 'block' : 'none'}">
          <!-- ITN Recipient Fieldset -->
        </div>
      </dd>
      <dt>
        <label>
          ${item.isPatientMd.displayLabel}
          <span id="patientSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isPatient}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="patientInput" type="hidden" value="${item.isPatient}" param="isPatient" />
        <div id="patientDiv" style="display: ${item.isPatient ? 'block' : 'none'}">
          <!-- Patient Fieldset -->
        </div>
      </dd>
--%>       
      <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
      <dt>
        <label>
          ${item.isSprayLeaderMd.displayLabel}
          <span id="sprayLeaderSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isSprayLeader}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="sprayLeaderInput" type="hidden" value="${item.isSprayLeader}" param="isSprayLeader" />
        <div id="sprayLeaderDiv" style="display: ${item.isSprayLeader ? 'block' : 'none'}">
          <fieldset>
            <dt>
              <label>
                ${item.leaderIdMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="leaderId" />
              <mjl:messages attribute="leaderId">
                <mjl:message />
              </mjl:messages>
            </dd>
          </fieldset>
        </div>
      </dd>
      <dt>
        <label>
          ${item.isSprayOperatorMd.displayLabel}
          <span id="sprayOperatorSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isSprayOperator}"><fmt:message key="Click_to_Remove" /></c:when>
            <c:otherwise><fmt:message key="Click_to_Add" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="sprayOperatorInput" type="hidden" value="${item.isSprayOperator}" param="isSprayOperator" />
        <div id="sprayOperatorDiv" style="display: ${item.isSprayOperator ? 'block' : 'none'}">
          <fieldset>
            <dt>
              <label>
                ${item.operatorIdMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="operatorId" />
              <mjl:messages attribute="operatorId">
                <mjl:message />
              </mjl:messages>
            </dd>
          </fieldset>
        </div>
      </dd>
  </mjl:component>
  
<script type="text/javascript" defer="defer">
<!--
(function(){
var password = document.getElementById('password');
var repassword = document.getElementById('repassword');
var button = document.getElementById('submit.button');

var validatePassword = function () {
  button.disabled = true;
  MDSS.Calendar.removeError(password);
  
  if(password.value !== '' && repassword.value !== '') {
    if(password.value !== repassword.value) {
      MDSS.Calendar.addError(password,MDSS.localize("Password_Mismatch"));

      // Empty the values of password and repassword to ensure that they re
      password.value = '';
      repassword.value = '';
    }
    else {
      button.disabled = false;        
    }
  }    
}

YAHOO.util.Event.on(password, 'blur', validatePassword);
YAHOO.util.Event.on(repassword, 'blur', validatePassword);
})();
//-->
</script>