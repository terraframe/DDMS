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
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
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
        <mjl:commandLink display="View" action="dss.vector.solutions.PersonController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Person" action="dss.vector.solutions.PersonController.newInstance.mojo" name="PersonController.newInstance" />
