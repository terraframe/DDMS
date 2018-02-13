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

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.intervention.monitor.ITNDataController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="batchNumber">
      <mjl:header>
        The ITNs batch number
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="currencyReceived">
      <mjl:header>
        The amount of currency received
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:header>
        End Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberDistributed">
      <mjl:header>
        Number of ITNs distributed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberSold">
      <mjl:header>
        Number of ITNs sold
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedForCommunityResponse">
      <mjl:header>
        Total number ITNs received for community response
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedForTargetGroups">
      <mjl:header>
        The number of ITNs received for Target Groups
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:header>
        Start Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDataController.view.mojo" name="view.link">
          <mdss:localize key="View" />
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDataController.newInstance.mojo" name="ITNDataController.newInstance">
<mdss:localize key="Create_a_new_ITN_distribution_data" />
</mjl:commandLink>
