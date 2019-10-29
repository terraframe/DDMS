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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_AreaStandards" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.irs.AreaStandardsController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <span class="formatDate"> ${item.startDate} </span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:row>
        <span class="formatDate"> ${item.endDate} </span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="targetUnit">
      <mjl:row>
        <ul>
          <c:forEach items="${item.targetUnitEnumNames}" var="enumName">
            <li>
              ${item.targetUnitMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="unitNozzleAreaCoverage">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${item.unitNozzleAreaCoverage}" />
      </mjl:row>
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="room">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${item.room}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="structureArea">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${item.structureArea}" />        
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="household">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${item.household}" />        
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.irs.AreaStandardsController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.areaStandardsId}" />
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
<mjl:commandLink name="AreaStandardsController.newInstance" action="dss.vector.solutions.irs.ApplicationRateController.view.mojo">
  <mdss:localize key="Configure_Application_Rate" />
</mjl:commandLink>
