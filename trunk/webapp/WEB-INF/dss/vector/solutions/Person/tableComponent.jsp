<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.business.ComponentQueryDTO"%>
<%@page import="dss.vector.solutions.PersonWithDelegatesViewDTO"%>

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
      <mjl:header>${userLabel}</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.userDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>${sprayOperatorLabel}</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayOperatorDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="Yes" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>${sprayLeaderLabel}</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.sprayLeaderDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>${stockStaffLabel}</mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.stockStaffDelegate != null}"><fmt:message key="Yes" /></c:when>
          <c:otherwise><fmt:message key="No" /></c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>    