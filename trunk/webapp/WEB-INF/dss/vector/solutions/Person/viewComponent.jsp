<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:input value="${item.personId}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.firstNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.firstName}
    </dd>
    <dt>
      <label>
        ${item.lastNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lastName}
    </dd>
    <dt>
      <label>
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          <li>
            ${item.sexMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.dateOfBirthMd.displayLabel}
      </label>
    </dt>
    <dd class="formatDate">
      ${item.dateOfBirth}
    </dd>
    <c:if test="${item.isMDSSUser}">
      <dt>
        <label>
          ${item.isMDSSUserMd.displayLabel}
        </label>
      </dt>
      <dd>
        <fieldset>
          <dt>
            <label>
              ${item.usernameMd.displayLabel}
            </label>
          </dt>
          <dd>
            ${item.username}
          </dd>
        </fieldset>
      </dd>
    </c:if>
    <c:if test="${item.isPatient}">
      <dt>
        <label>
          ${item.isPatientMd.displayLabel}
        </label>
      </dt>
    </c:if>
    <c:if test="${item.isITNRecipient}">
      <dt>
        <label>
          ${item.isITNRecipientMd.displayLabel}
        </label>
      </dt>
    </c:if>
    <c:if test="${item.isIPTRecipient}">
      <dt>
        <label>
          ${item.isIPTRecipientMd.displayLabel}
        </label>
      </dt>
    </c:if>
    <c:if test="${item.isSprayOperator}">
      <dt>
        <label>
          ${item.isSprayOperatorMd.displayLabel}
        </label>
      </dt>
      <dd>
        <fieldset>
          <dt>
            <label>
              ${item.operatorIdMd.displayLabel}
            </label>
          </dt>
          <dd>
            ${item.operatorId}
          </dd>
        </fieldset>
      </dd>
    </c:if>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders
    <c:if test="${item.isSprayLeader}">
      <dt>
        <label>
          ${item.isSprayLeaderMd.displayLabel}
        </label>
      </dt>
    </c:if>
    --%>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.PersonController.edit.mojo" name="dss.vector.solutions.Person.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.PersonController.viewAll.mojo" name="dss.vector.solutions.Person.viewAll.link" />
