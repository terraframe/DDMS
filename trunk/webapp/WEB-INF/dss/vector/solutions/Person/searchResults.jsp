<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.terraframe.mojo.constants.Constants" %>
<c:set var="page_title" value="Search_Person" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PersonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="firstName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateOfBirth">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="User"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.userDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Patient"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.patientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="ITN_Recipient"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.itnRecipientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="IPT_Recipient"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.iptRecipientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Spray_Operator"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayOperatorDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <%-- 5.13.09 - Marlzie says we don't need Spray Leaders
    --%>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Spray_Leader"/></mjl:header>
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
<fmt:formatDate value="${newPerson.dateOfBirth}" pattern="${dateFormatPattern}" var="formatedBirthday" />
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:component item="${newPerson}" param="person">
    <mjl:input type="hidden" param="personId" value="${newPerson.personId}" />
    <mjl:input type="hidden" param="firstName" value="${newPerson.firstName}"/>
    <mjl:input type="hidden" param="lastName" value="${newPerson.lastName}"/>
    <mjl:input type="hidden" param="dateOfBirth" value="${formatedBirthday}"/>
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
  <mjl:command value="No_Matches__continue_with_new_Person" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="dss.vector.solutions.PersonController.continueNewInstance.button" />
</mjl:form>
