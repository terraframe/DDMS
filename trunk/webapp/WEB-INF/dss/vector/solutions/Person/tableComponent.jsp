<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.runwaysdk.business.ComponentQueryDTO"%>
<%@page import="dss.vector.solutions.PersonWithDelegatesViewDTO"%>
    
    <mjl:attributeColumn attributeName="identifier">
    </mjl:attributeColumn>
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
    <c:if test="${userLabel != null}">    
      <mjl:freeColumn>
        <mjl:header>${userLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.userDelegate != null}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>
    </c:if>
    <c:if test="${sprayOperatorLabel != null}">    
      <mjl:freeColumn>
        <mjl:header>${sprayOperatorLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.teamMemberDelegate != null && item.teamMemberDelegate.isSprayOperator}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>
    </c:if>    
    <c:if test="${sprayLeaderLabel != null}">    
      <mjl:freeColumn>
        <mjl:header>${sprayLeaderLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.teamMemberDelegate != null && item.teamMemberDelegate.isSprayLeader}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>
    </c:if>
    <c:if test="${stockStaffLabel != null}">
      <mjl:freeColumn>
        <mjl:header>${stockStaffLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.stockStaffDelegate != null}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>    
    </c:if>
    <c:if test="${supervisorLabel != null}">
      <mjl:freeColumn>
        <mjl:header>${supervisorLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.supervisorDelegate != null}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>
    </c:if>      
    <c:if test="${physicianLabel != null}">
      <mjl:freeColumn>
        <mjl:header>${physicianLabel}</mjl:header>
        <mjl:row>
          <c:choose>
            <c:when test="${item.physicianDelegate != null}"><mdss:localize key="Yes" /></c:when>
            <c:otherwise><mdss:localize key="No" /></c:otherwise>
          </c:choose>
        </mjl:row>
      </mjl:freeColumn>
    </c:if>      