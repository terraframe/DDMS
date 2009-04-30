<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
(function(){

  YAHOO.util.Event.onDOMReady(function(){

    MDSS.Effect.toggleVisibility('userDiv', 'userSwitch', 'userInput');

  });

})();
</script>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:component item="${item}" param="person">
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
        <mjl:input type="text" param="dateOfBirth" />
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
        <input id="userInput" type="hidden" value="${item.isMDSSUser}" />
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
              <mjl:input type="text" param="password" />
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
        </label>
      </dt>
      <dd>
        <!-- IPT Recipient Attributes -->
      </dd>
      <dt>
        <label>
          ${item.isITNRecipientMd.displayLabel}
        </label>
      </dt>
      <dd>
        <!-- ITN Recipient Attributes -->
      </dd>
      <dt>
        <label>
          ${item.isPatientMd.displayLabel}
        </label>
      </dt>
      <dd>
        <!-- Patient Attributes -->
      </dd>
      <dt>
        <label>
          ${item.isSprayLeaderMd.displayLabel}
        </label>
      </dt>
      <dd>
        <!-- Spray Leader Attributes -->
      </dd>
      <dt>
        <label>
          ${item.isSprayOperatorMd.displayLabel}
        </label>
      </dt>
      <dd>
        <!-- Spray Operator Attributes -->
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.PersonController.createFromView.mojo" name="dss.vector.solutions.Person.form.create.button" />
</mjl:form>
