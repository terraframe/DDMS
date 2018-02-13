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

<c:set var="page_title" value="View_Aggregated_IPT"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.name" id="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoId">
        ${entity.displayString}
      </mjl:dt>
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>      
      </mjl:dt>      
<!--       
      <mjl:dt attribute="periodType">
        <ul>
          <c:forEach var="enumName" items="${item.periodTypeEnumNames}">
            <li>${item.periodTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>      
      </mjl:dt>
      <mjl:dt attribute="period">
        ${item.period}
      </mjl:dt>
      <mjl:dt attribute="periodYear">
        ${item.periodYear}
      </mjl:dt>
 -->      
      <mjl:dt attribute="numberPregnant">
        ${item.numberPregnant}
      </mjl:dt>    
      <mjl:dt attribute="numberNatalCare" >
        ${item.numberNatalCare}
      </mjl:dt>
      <mjl:dt attribute="numberPregnantIron">
        ${item.numberPregnantIron}
      </mjl:dt>    
      <mjl:dt attribute="numberPregnantITN" >
        ${item.numberPregnantITN}
      </mjl:dt>    
      <mjl:dt attribute="totalITN">
        ${item.totalITN}
      </mjl:dt>    
    </mjl:component>
  
    <c:if test="${item.isDisplayPatientsReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><mdss:localize key="Number_per_person_category"/></th>
            <th><mdss:localize key="Amount"/></th>
          </tr>      
          <mjl:components items="${patients}" param="patients" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayVisitsReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><mdss:localize key="ANC_Visit"/></th>
            <th><mdss:localize key="Amount"/></th>
          </tr>      
          <mjl:components items="${visits}" param="visits" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayDoseReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><mdss:localize key="IPT_Dose"/></th>
            <th><mdss:localize key="Amount"/></th>
          </tr>      
          <mjl:components items="${doses}" param="doses" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayTreatmentsReadable}">
      <dt>  
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><mdss:localize key="IPT_Type"/></th>
            <th><mdss:localize key="Amount"/></th>
          </tr>      
          <mjl:components items="${treatments}" param="treatments" var="current" varStatus="status">
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
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.edit.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.edit.button" />
    <mdss:localize key="Create_New_Aggregated_IPT_Button" var="Localized_Create_New_Aggregated_IPT_Button" />
    <mjl:command value="${Localized_Create_New_Aggregated_IPT_Button}" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.search.mojo" name="AggregatedIPTController.search" />
 </dl>
</mjl:form>