<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_IndividualIPTCase" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.id" name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:input param="serviceDate" value="${serviceDate}" type="hidden"/>    
    <%@include file="personHeader.jsp" %>    
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="residentialLocation">
        ${residentialLocation.displayString}
      </mjl:dt>      
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.edit.mojo" />
  </mjl:form>
</dl>
<dl>
  <dt> <label><mdss:localize key="Case_instances" /> </label></dt>
  <dd>
<mjl:table classes="displayTable" var="current" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.IndividualIPTController.view.mojo" >
    <mjl:property name="id" value="${item.concreteId}"/>
  </mjl:context>
  <mjl:columns>
    <mjl:attributeColumn attributeName="facility">
      <mjl:row>
        ${current.facilityName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="serviceDate">
      <mjl:row>
        <span class="formatDate">${current.serviceDate}</span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientType">
      <mjl:row>
        ${current.patientType.displayLabel}
      </mjl:row>    
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="age">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isANCVisit">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="visitNumber">
      <mjl:row>
        ${current.visitNumber.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="doseNumber">
      <mjl:row>
        ${current.doseNumber.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="doseType">
      <mjl:row>
        ${current.doseType.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedSupplement">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedITN">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberOfReceivedITNs">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${current.concreteId}" />
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
</dd>
  <mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.intervention.monitor.IndividualIPTController.newInstance.mojo">
    <mdss:localize key="Create_a_new_Individual_IPT_Instance" />
    <mjl:property name="caseId" value="${item.concreteId}"/>
    <mjl:property name="serviceDate" value="${serviceDate}"/>    
</mjl:commandLink>  
</dl>

<mjl:commandLink name="search.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.search.mojo">
  <mdss:localize key="Search_another_individual_IPT_Case" />
</mjl:commandLink>
