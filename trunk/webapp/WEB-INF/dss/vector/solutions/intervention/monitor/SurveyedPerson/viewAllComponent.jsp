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
<c:set scope="request" var="page_title" value="View_All_SurveyedPerson" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="anaemiaTreatment">
      <mjl:row>
        ${item.anaemiaTreatment.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bloodslideDetail">
      <mjl:row>
        ${item.bloodslideDetail.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bloodslideReason">
      <mjl:row>
        ${item.bloodslideReason.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bloodslideResult">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dob">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.dob}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fever">
      <mjl:row>
        <ul>
          <c:forEach items="${item.feverEnumNames}" var="enumName">
            <li>
              ${item.feverMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="haemoglobin">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="haemoglobinMeasured">
      <mjl:row>
        <ul>
          <c:forEach items="${item.haemoglobinMeasuredEnumNames}" var="enumName">
            <li>
              ${item.haemoglobinMeasuredMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="headOfHousehold">
      <mjl:row>
        ${item.headOfHousehold.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="household">
      <mjl:row>
        ${item.household.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="immuneCompromised">
      <mjl:row>
        ${item.immuneCompromised.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="iron">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="malaria">
      <mjl:row>
        <ul>
          <c:forEach items="${item.malariaEnumNames}" var="enumName">
            <li>
              ${item.malariaMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="malariaConformationTechnique">
      <mjl:row>
        ${item.malariaConformationTechnique.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="payment">
      <mjl:row>
        ${item.payment.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="performedBloodslide">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="performedRDT">
      <mjl:row>
        <ul>
          <c:forEach items="${item.performedRDTEnumNames}" var="enumName">
            <li>
              ${item.performedRDTMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="personId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnant">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rdtDetail">
      <mjl:row>
        ${item.rdtDetail.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rdtResult">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rdtTreatment">
      <mjl:row>
        ${item.rdtTreatment.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
      <mjl:row>
        ${item.sex.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sleptUnderNet">
      <mjl:row>
        ${item.sleptUnderNet.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.view.mojo">
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
<mjl:commandLink name="SurveyedPersonController.newInstance" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Person" />
</mjl:commandLink>
