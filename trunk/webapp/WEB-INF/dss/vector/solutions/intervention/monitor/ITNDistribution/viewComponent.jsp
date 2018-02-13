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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_ITNDistribution" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="person">
        ${item.person}
      </mjl:dt>
      <mjl:dt attribute="distributionDate">
        <span class="formatDate">
          ${item.distributionDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="facility">
        ${facility.displayString}
      </mjl:dt>
      <mjl:dt attribute="service">
        <c:if test="${service != null}">
          ${service.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="batchNumber">
        ${item.batchNumber}
      </mjl:dt>
      
      <c:if test="${item.isTargetGroupsReadable}">    
        <dt>
    
        </dt>
        <dd>
          <table class="displayTable">
            <tr> 
              <th>${item.targetGroupsMd.displayLabel}</th>
              <th><mdss:localize key="Amount"/></th>
            </tr>      
            <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
              <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
                <td>
                  ${current.child.displayLabel}
                </td>
                <td class="integerColumn">
                  ${current.amount}
                  <mjl:messages attribute="amount">
                    <mjl:message />
                  </mjl:messages>
                </td>
              </tr>
            </mjl:components>
          </table>
        </dd>
      </c:if>
      
      <mjl:dt attribute="net">
        <c:if test="${net != null}">
          ${net.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="numberSold">
        ${item.numberSold}
      </mjl:dt>
      <mjl:dt attribute="currencyReceived">
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.currencyReceived}" />      
      </mjl:dt>
      <mjl:dt attribute="distributorName">
        ${item.distributorName}
      </mjl:dt>
      <mjl:dt attribute="distributorSurname">
        ${item.distributorSurname}
      </mjl:dt>
    </mjl:component>
      
    <mdss:localize key="Edit" var="Localized_Edit" />
      
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.edit.mojo" name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.edit.button" />
    <mdss:localize key="Create_New_ITN_Distribution_Button" var="Localized_Create_New_ITN_Distribution_Button" />
    <mjl:command value="${Localized_Create_New_ITN_Distribution_Button}" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.search.mojo" name="ITNDistributionController.search" />
  </mjl:form>
</dl>