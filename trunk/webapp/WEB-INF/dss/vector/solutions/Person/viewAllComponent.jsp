<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_People" scope="request" />

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
      <mjl:row>
        ${item.sex.displayLabel}
      </mjl:row>      
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateOfBirth">
      <mjl:row>
        <span class="formatDate"> ${item.dateOfBirth} </span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="User"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.userDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
<%--  Removed from screen on 7/28/09 because of ticket #445
    
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
--%>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Spray_Operator"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayOperatorDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Spray_Leader"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayLeaderDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="Stock_Staff"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.stockStaffDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
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

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PersonExcelView" name="excelType"/>
</jsp:include>