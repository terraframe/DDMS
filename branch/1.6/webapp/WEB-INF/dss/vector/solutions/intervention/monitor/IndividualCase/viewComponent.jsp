<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_IndividualCase" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <%@include file="personHeader.jsp" %>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="residence">
        ${item.residence}  
      </mjl:dt>
      <mjl:dt attribute="caseReportDate">
        <span class="formatDate">
          ${item.caseReportDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="caseEntryDate">
        <span class="formatDate">
          ${item.createDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="diagnosisDate">
        <span class="formatDate">
          ${item.diagnosisDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="patient">
        ${item.patient.person}
      </mjl:dt>
      <mjl:dt attribute="probableSource">
        ${item.probableSource.displayString}
      </mjl:dt>
      <mjl:dt attribute="otherSettlements">
        ${item.otherSettlements}
      </mjl:dt>
      <mjl:dt attribute="symptomOnset">
        <span class="formatDate">
          ${item.symptomOnset}
        </span>
      </mjl:dt>
      <mjl:dt attribute="origin">
        ${item.origin.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="plasmaLeakageOnset">
        <span class="formatDate">
          ${item.plasmaLeakageOnset}
        </span>
      </mjl:dt>
      <mjl:dt attribute="hemorrhagicOnset">
        <span class="formatDate">
          ${item.hemorrhagicOnset}
        </span>
      </mjl:dt>      
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.edit.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.edit.button" />
  </mjl:form>
</dl>
<dl>
  <dt> <label><mdss:localize key="Individual_Case_instances" /> </label></dt>
  <dd>
<mjl:table var="row" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="facilityVisit">
      <mjl:row>
        <fmt:formatDate value="${row.facilityVisit}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="diagnosisType">
      <mjl:row>
        <c:forEach items="${row.diagnosisTypeEnumNames}" var="enumName">
          <li>
            ${row.diagnosisTypeMd.enumItems[enumName]}
          </li>
        </c:forEach>        
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${row.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
</dd>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.newInstance.mojo" name="IndividualInstanceController.newInstance">
  <mdss:localize key="Create_a_new_Individual_Instance" />
  <mjl:property name="caseId" value="${item.id}"/>
</mjl:commandLink>
</dl>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualCaseController.newInstance.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.viewAll.link">
  <mdss:localize key="Search" />
</mjl:commandLink>
