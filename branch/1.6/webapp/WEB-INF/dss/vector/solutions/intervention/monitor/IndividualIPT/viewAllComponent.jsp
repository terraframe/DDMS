<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_IndividualIPT" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.IndividualIPTController.viewCasePage.mojo" >
    <mjl:property name="facility" value="${facility}"/>
    <mjl:property name="serviceDate" value="${serviceDate}" />
    <mjl:property name="age" value="${age}"/>
    <mjl:property name="patientId" value="${patientId}"/>
  </mjl:context>
  <mjl:columns>
    <mjl:attributeColumn attributeName="facility">
      <mjl:row>
        ${item.facility}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientType">
      <mjl:row>
        ${item.patientType.displayLabel}
      </mjl:row>    
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="age">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isANCVisit">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="visitNumber">
      <mjl:row>
        ${item.visitNumber.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="doseNumber">
      <mjl:row>
        ${item.doseNumber.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="doseType">
      <mjl:row>
        ${item.doseType.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedSupplement">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="receivedITN">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberOfReceivedITNs">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="administratorName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="administratorSurname">
    </mjl:attributeColumn>    
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.concreteId}" />
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
<mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.intervention.monitor.IndividualIPTController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Individual_IPT_Information" />
  <mjl:property name="facility" value="${facility}"/>
  <mjl:property name="serviceDate" value="${serviceDate}" />
  <mjl:property name="age" value="${age}"/>
  <mjl:property name="patientId" value="${patientId}"/>  
</mjl:commandLink>
