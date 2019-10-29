<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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