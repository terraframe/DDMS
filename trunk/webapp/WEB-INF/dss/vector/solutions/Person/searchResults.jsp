<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PersonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="firstName">
      <mjl:header>
        First Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastName">
      <mjl:header>
        Last Name / Surname
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
      <mjl:header>
        Sex
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateOfBirth">
      <mjl:header>
        Date of Birth
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>User</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.userDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>Patient</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.patientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>ITN Recipient</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.itnRecipientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>IPT Recipient</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.iptRecipientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>Spray Operator</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayOperatorDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>Spray Leader</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayLeaderDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="Use This Person" action="dss.vector.solutions.PersonController.edit.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:component item="${newPerson}" param="person">
    <mjl:input type="hidden" param="personId" value="${item.personId}" />
    <mjl:input type="hidden" param="firstName" value="${newPerson.firstName}"/>
    <mjl:input type="hidden" param="lastName" value="${newPerson.lastName}"/>
    <mjl:input type="hidden" param="dateOfBirth" value="${newPerson.dateOfBirth}"/>
    <mjl:input type="hidden" param="sex" value="${sexEnumName}"/>
    <mjl:input type="hidden" param="isMDSSUser" value="${newPerson.isMDSSUser}"/>
    <mjl:input type="hidden" param="username" value="${newPerson.username}"/>
    <mjl:input type="hidden" param="password" value="${newPerson.password}"/>
    <mjl:input type="hidden" param="isIPTRecipient" value="${newPerson.isIPTRecipient}"/>
    <mjl:input type="hidden" param="isITNRecipient" value="${newPerson.isITNRecipient}"/>
    <mjl:input type="hidden" param="isPatient" value="${newPerson.isPatient}"/>
    <mjl:input type="hidden" param="isSprayLeader" value="${newPerson.isSprayLeader}"/>
    <mjl:input type="hidden" param="isSprayOperator" value="${newPerson.isSprayOperator}"/>
  </mjl:component>
  <mjl:command value="No Matches, continue with new Person" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="dss.vector.solutions.PersonController.continueNewInstance.button" />
</mjl:form>

<mjl:commandLink display="No Matches, continue with new Person" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="PersonController.continueNewInstance">
  <mjl:property name="firstName" value="${person.firstName}"/>
  <mjl:property name="lastName" value="${person.lastName}"/>
  <mjl:property name="dateOfBirth" value="${person.dateOfBirth}"/>
  <mjl:property name="sex" value="${sexEnumName}"/>
  <mjl:property name="isMDSSUser" value="${person.isMDSSUser}"/>
  <mjl:property name="username" value="${person.username}"/>
  <mjl:property name="password" value="${person.password}"/>
  <mjl:property name="isIPTRecipient" value="${person.isIPTRecipient}"/>
  <mjl:property name="isITNRecipient" value="${person.isITNRecipient}"/>
  <mjl:property name="isPatient" value="${person.isPatient}"/>
  <mjl:property name="isSprayLeader" value="${person.isSprayLeader}"/>
  <mjl:property name="isSprayOperator" value="${person.isSprayOperator}"/>
</mjl:commandLink>
