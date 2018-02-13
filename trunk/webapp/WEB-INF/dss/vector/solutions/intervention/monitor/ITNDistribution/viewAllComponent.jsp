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
<c:set var="page_title" value="View_All_ITNDistribution" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.ITNDistributionController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="distributionDate">
      <mjl:row>
        <fmt:formatDate value="${item.distributionDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="facility">
      <mjl:row>
        ${item.facility}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="person">
      <mjl:row>
        ${item.person}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="batchNumber">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="service">
      <mjl:row>
        ${item.service.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="net">
      <mjl:row>
        ${item.net.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberSold">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="currencyReceived">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.currencyReceived}" />
      </mjl:row>    
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="distributorName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="distributorSurname">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDistributionController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.concreteId}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDistributionController.newInstance.mojo" name="ITNDistributionController.newInstance">
  <mjl:property name="person" value="${item.person.id}"/>
  <mjl:property name="distributionDate" value="${distributionDate}"/>
  <mjl:property name="facility" value="${facility}"/>
  <mjl:property name="batchNumber" value="${batchNumber}"/>
  <mdss:localize key="Create_a_new_ITN_Distribution" />
</mjl:commandLink>
