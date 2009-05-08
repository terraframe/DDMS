<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
  <mjl:component item="${item}" param="person">
    <mjl:input type="hidden" param="personId" value="${item.personId}" />
    <dl>
      <dt>
        <label>
          ${item.firstNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="firstName" />
        <mjl:messages attribute="firstName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lastNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lastName" />
        <mjl:messages attribute="lastName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.dateOfBirthMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="dateOfBirth" id="dateOfBirth" classes="DatePick"/>
        <mjl:messages attribute="dateOfBirth">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sexMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${sexes}" param="sex">
          <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sex">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.isMDSSUserMd.displayLabel}?
          <span id="userSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isMDSSUser}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
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
              <mjl:input type="password" param="password" />
              <mjl:messages attribute="password">
                <mjl:message />
              </mjl:messages>
            </dd>
          </fieldset>
        </div>
      </dd>
      <dt>
        <label>
          ${item.isIPTRecipientMd.displayLabel}
          <span id="iptSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isIPTRecipient}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
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
            <c:when test="${item.isITNRecipient}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
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
            <c:when test="${item.isPatient}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
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
      <dt>
        <label>
          ${item.isSprayLeaderMd.displayLabel}
          <span id="sprayLeaderSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isSprayLeader}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="sprayLeaderInput" type="hidden" value="${item.isSprayLeader}" param="isSprayLeader" />
        <div id="sprayLeaderDiv" style="display: ${item.isSprayLeader ? 'block' : 'none'}">
          <!-- Spray Leader Fieldset -->
        </div>
      </dd>
      <dt>
        <label>
          ${item.isSprayOperatorMd.displayLabel}
          <span id="sprayOperatorSwitch" class="clickable">
          <c:choose>
            <c:when test="${item.isSprayOperator}"><fmt:message key="Toggle_Hide" /></c:when>
            <c:otherwise><fmt:message key="Toggle_Show" /></c:otherwise>
          </c:choose>
          </span>
        </label>
      </dt>
      <dd>
        <mjl:input id="sprayOperatorInput" type="hidden" value="${item.isSprayOperator}" param="isSprayOperator" />
        <div id="sprayOperatorDiv" style="display: ${item.isSprayOperator ? 'block' : 'none'}">
          <!-- Spray Operator Fieldset -->
        </div>
      </dd>
    </dl>
  </mjl:component>