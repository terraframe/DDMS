<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Person" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:input value="${item.personId}" type="hidden" param="id" />
  <dl>
    <mjl:component item="${item}" param="item">
    <mjl:dt attribute="identifier">
      ${item.identifier}
    </mjl:dt>    
    <mjl:dt attribute="firstName">
      ${item.firstName}
    </mjl:dt>
    <mjl:dt attribute="lastName">
      ${item.lastName}
    </mjl:dt>
    <mjl:dt attribute="residentialGeoId">
      ${residential.displayString}
    </mjl:dt>
    <mjl:dt attribute="sex">
      <c:if test="${sex != null}">
        ${sex.displayLabel}
      </c:if>
    </mjl:dt>
    <mjl:dt attribute="dateOfBirth">
      <span class="formatDate">${item.dateOfBirth}</span>
    </mjl:dt>
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
          <mjl:dt attribute="disease">
            <ul>
				${item.disease}
            </ul>
          </mjl:dt>
        </fieldset>
      </dd>
    </c:if>
<!--  Removed from screen on 7/28/09 because of ticket #445
    
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
-->    
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
              ${item.memberIdMd.displayLabel}
            </label>
          </dt>
          <dd>
            ${item.memberId}
          </dd>
        </fieldset>
      </dd>
    </c:if>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
    <c:if test="${item.isSprayLeader}">
      <dt>
        <label>
          ${item.isSprayLeaderMd.displayLabel}
        </label>
      </dt>
      <dd>
        <fieldset>
          <dt>
            <label>
              ${item.memberIdMd.displayLabel}
            </label>
          </dt>
          <dd>
            ${item.memberId}
          </dd>
        </fieldset>
      </dd>
    </c:if>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.PersonController.edit.mojo" name="dss.vector.solutions.Person.form.edit.button" />
    <mdss:localize key="Create_new_person" var="Localized_Create_new_person" />
    <mjl:command value="${Localized_Create_new_person}" action="dss.vector.solutions.PersonController.newInstance.mojo" name="newInstance.button" />
    </mjl:component>    
  </dl>
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.PersonController.viewAll.mojo" name="dss.vector.solutions.Person.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
