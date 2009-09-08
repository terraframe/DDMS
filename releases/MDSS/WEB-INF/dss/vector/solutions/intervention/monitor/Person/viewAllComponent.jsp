<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.intervention.monitor.PersonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="anaemiaTreatment">
      <mjl:header>
        Anaemia Treatment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bloodslide">
      <mjl:header>
        Bloodslide
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dob">
      <mjl:header>
        Date of Birth
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fever">
      <mjl:header>
        fever
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="feverTreatment">
      <mjl:header>
        Fever Treatment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="haemoglobin">
      <mjl:header>
        haemoglobin
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="haemoglobinMeasured">
      <mjl:header>
        Haemoglobin Measured
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="household">
      <mjl:header>
        Household
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="iron">
      <mjl:header>
        Iron Given
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="malaria">
      <mjl:header>
        Malaria
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="malariaTreatment">
      <mjl:header>
        Malaria Treatment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="payment">
      <mjl:header>
        Payment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="performedRDT">
      <mjl:header>
        RDT
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="personId">
      <mjl:header>
        Person Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnant">
      <mjl:header>
        Pregnant
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rDTResult">
      <mjl:header>
        RDT Result
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rdtTreatment">
      <mjl:header>
        RDT Treatment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
      <mjl:header>
        Sex
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sleptUnderNet">
      <mjl:header>
        Slept Under Net
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.PersonController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Person" action="dss.vector.solutions.intervention.monitor.PersonController.newInstance.mojo" name="PersonController.newInstance" />
