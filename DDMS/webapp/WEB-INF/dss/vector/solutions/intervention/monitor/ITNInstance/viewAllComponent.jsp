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
<c:set scope="request" var="page_title" value="View_All_ITNInstance" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.ITNInstanceController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="damaged">
      <mjl:row>
        ${item.damaged.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="hanging">
      <mjl:row>
        ${item.hanging.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="household">
      <mjl:row>
        ${item.household.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="monthReceived">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="monthRetreated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="netBrand">
      <mjl:row>
        ${item.netBrand.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="netId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="notUsedForSleeping">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="obtained">
      <mjl:row>
        ${item.obtained.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="price">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="purpose">
      <mjl:row>
        ${item.purpose.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="purposeComments">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="retreated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sleptUnderNet">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="washFrequency">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="washPeriod">
      <mjl:row>
        ${item.washPeriod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="washed">
      <mjl:row>
        <ul>
          <c:forEach items="${item.washedEnumNames}" var="enumName">
            <li>
              ${item.washedMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="yearReceived">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="yearRetreated">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
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
<mjl:commandLink name="ITNInstanceController.newInstance" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.newInstance.mojo">
  <mdss:localize key="Create_a_new_ITN_instance" />
</mjl:commandLink>
