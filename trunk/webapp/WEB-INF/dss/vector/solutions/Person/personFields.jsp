<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.PersonViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

  <mjl:messages>
    <mjl:message />
  </mjl:messages>
  <mjl:component item="${item}" param="person">
    <mjl:input type="hidden" param="personId" id="personId" value="${item.personId}" />
    
    <mjl:input type="hidden" param="isIPTRecipient" value="${item.isIPTRecipient}" />
    <mjl:input type="hidden" param="isITNRecipient" value="${item.isITNRecipient}" />
    <mjl:input type="hidden" param="isPatient" value="${item.isPatient}" />
      <mjl:dt attribute="identifier">
        <mjl:input type="text" param="identifier"/>
      </mjl:dt>    
      <mjl:dt attribute="firstName" type="text" />
      <mjl:dt attribute="lastName" type="text"/>      
      <mjl:dt attribute="residentialGeoId" >
        <mdss:geo param="residentialGeoId" concrete="false" value="${item.residentialGeoId}" />      
      </mjl:dt>
      <mjl:dt attribute="dateOfBirth" >
        <mjl:input type="text" param="dateOfBirth" id="dateOfBirth" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mdss:mo param="sex" value="${sex}"/>
      </mjl:dt>            
      <dt>
        <label>
          ${item.isMDSSUserMd.displayLabel}?
          <span id="userSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isMDSSUser}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
              <mjl:input id="username" type="text" param="username" />
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
                <mdss:localize key="password_confirmation"/>                
              </label>
            </dt>
            <dd>
              <mjl:input type="password" param="repassword" id="repassword"  value="${repassword}"/>
              <mjl:messages attribute="repassword">
                <mjl:message />
              </mjl:messages>
            </dd>
            <mjl:dt attribute="disease">
              <mjl:select param="disease" items="${allDiseases}" var="current" valueAttribute="id" id="disease">
                <mjl:option selected="${(current==item.disease) ? 'selected' :  'false'}" id="disease.${current}">
                  ${current}
                </mjl:option>
              </mjl:select>
            </mjl:dt>
            
          </fieldset>
        </div>
      </dd>
<%--  Removed from screen on 7/28/09 because of ticket #445

      <dt>
        <label>
          ${item.isIPTRecipientMd.displayLabel}
          <span id="iptSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isIPTRecipient}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
            <c:when test="${item.isITNRecipient}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
            <c:when test="${item.isPatient}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
            <c:when test="${item.isSprayLeader}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
                ${item.memberIdMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="memberId" id="memberId" />
              <mjl:messages attribute="memberId">
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
            <c:when test="${item.isSprayOperator}"><mdss:localize key="Click_to_Remove" /></c:when>
            <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
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
                ${item.memberIdMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="operatorId" id="operatorId" value="${item.memberId}" />
              <mjl:messages attribute="memberId">
                <mjl:message />
              </mjl:messages>
            </dd>
          </fieldset>
        </div>
      </dd>
      <c:if test="${item.isStockStaffReadable}">
        <dt>
          <label>
            ${item.isStockStaffMd.displayLabel}
            <span id="stockStaffSwitch" class="clickable">
            <c:choose>
              <c:when test="${item.isStockStaff}"><mdss:localize key="Click_to_Remove" /></c:when>
              <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
            </c:choose>
            </span>
          </label>
        </dt>
        <dd>
          <mjl:input id="stockStaffInput" type="hidden" value="${item.isStockStaff}" param="isStockStaff" />
          <div id="stockStaffDiv" style="display: ${item.isStockStaff ? 'block' : 'none'}">
            <!-- Patient Fieldset -->
          </div>
        </dd>      
      </c:if>
      <c:if test="${item.isStockStaffReadable}">      
        <dt>
          <label>
            ${item.isSupervisorMd.displayLabel}
            <span id="supervisorSwitch" class="clickable">
            <c:choose>
              <c:when test="${item.isSupervisor}"><mdss:localize key="Click_to_Remove" /></c:when>
              <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
            </c:choose>
            </span>
          </label>
        </dt>
        <dd>
          <mjl:input id="supervisorInput" type="hidden" value="${item.isSupervisor}" param="isSupervisor" />
          <div id="supervisorDiv" style="display: ${item.isSupervisor ? 'block' : 'none'}">
            <!-- Patient Fieldset -->
          </div>
        </dd>
      </c:if>      
      <c:if test="${item.isPhysicianReadable}">      
        <dt>
          <label>
            ${item.isPhysicianMd.displayLabel}
            <span id="physicianSwitch" class="clickable">
              <c:choose>
                <c:when test="${item.isPhysician}"><mdss:localize key="Click_to_Remove" /></c:when>
                <c:otherwise><mdss:localize key="Click_to_Add" /></c:otherwise>
              </c:choose>
            </span>
          </label>
        </dt>
        <dd>
          <mjl:input id="physicianInput" type="hidden" value="${item.isPhysician}" param="isPhysician" />
          <div id="physicianDiv" style="display: ${item.isPhysician ? 'block' : 'none'}">
          </div>
        </dd>
      </c:if>      
  </mjl:component>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var password = document.getElementById('password');
    var repassword = document.getElementById('repassword');
    var button = document.getElementById('submit.button');
    var userInput = document.getElementById('userInput');
    var userSwitch = document.getElementById('userSwitch');

    var validatePassword = function () {
        MDSS.Calendar.removeError(password);

       if(password.value !== '' && repassword.value !== '' && password.value !== repassword.value) {
         MDSS.Calendar.addError(password,MDSS.localize("Password_Mismatch"));

         // Empty the values of password and repassword to ensure that they re
         password.value = '';
         repassword.value = '';
       }
    }

    var validatePasswordButton = function() {
      button.disabled = false;

      var visible = userInput.value;
      var validPassword = (password.value === repassword.value && password.value.length > 0);
      
      if(visible == 'true' && !validPassword) {
        button.disabled = true;
      }
    }

   var memberId = document.getElementById('memberId');
   var operatorId = document.getElementById('operatorId');   

   var copyValue = function (to, from)
   {
     to.value = from.value;
   }

   YAHOO.util.Event.on(memberId, 'keyup', Mojo.Util.curry(copyValue, operatorId, memberId));
   YAHOO.util.Event.on(operatorId, 'keyup', Mojo.Util.curry(copyValue, memberId, operatorId));

   YAHOO.util.Event.on(memberId, 'blur', Mojo.Util.curry(copyValue, operatorId, memberId));
   YAHOO.util.Event.on(operatorId, 'blur', Mojo.Util.curry(copyValue, memberId, operatorId));
   
   YAHOO.util.Event.on(password, 'blur', validatePassword);
   YAHOO.util.Event.on(repassword, 'blur', validatePassword);

   YAHOO.util.Event.on(password, 'keyup', validatePasswordButton);
   YAHOO.util.Event.on(repassword, 'keyup', validatePasswordButton);
   YAHOO.util.Event.on(userSwitch, 'click', validatePasswordButton);

   if(document.getElementById('personId').value == '')
   {	   
     validatePasswordButton();
   }
  })
})();
</script>