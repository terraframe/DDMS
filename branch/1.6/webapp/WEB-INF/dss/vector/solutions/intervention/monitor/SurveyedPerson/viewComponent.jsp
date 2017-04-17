<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page_title" value="View_Person"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.id" name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="personId">
        ${item.personId}
      </mjl:dt>    
      <mjl:dt attribute="headOfHousehold">
        ${item.headOfHousehold.displayLabel}
      </mjl:dt>      
      <mjl:dt attribute="dob">
        <span class="formatDate">
          ${item.dob}
        </span>
      </mjl:dt>
      <mjl:dt attribute="age">
        ${item.age}
      </mjl:dt>    
      <mjl:dt attribute="sex">
        ${item.sex.displayLabel}
      </mjl:dt>        
      <mjl:dt attribute="pregnant">
        ${item.pregnant ? item.pregnantMd.positiveDisplayLabel : item.pregnantMd.negativeDisplayLabel}
      </mjl:dt>      
      <mjl:dt attribute="immuneCompromised">
        ${item.immuneCompromised.displayLabel}
      </mjl:dt>      
      <mjl:dt attribute="sleptUnderNet">       
        ${net != null ? net.netId : '-'}
      </mjl:dt>      
      <mjl:dt attribute="haemoglobinMeasured">
        <ul>
          <c:forEach items="${item.haemoglobinMeasuredEnumNames}" var="enumName">
            <li>
              ${item.haemoglobinMeasuredMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="haemoglobin">
        ${item.haemoglobin}
      </mjl:dt>
      <mjl:dt attribute="anaemiaTreatment">
        ${item.anaemiaTreatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="iron">
        ${item.iron ? item.ironMd.positiveDisplayLabel : item.ironMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="performedRDT">
        <ul>
          <c:forEach items="${item.performedRDTEnumNames}" var="enumName">
            <li>
              ${item.performedRDTMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="rdtResult">
        ${item.rdtResult != null ? (item.rdtResult ? item.rdtResultMd.positiveDisplayLabel : item.rdtResultMd.negativeDisplayLabel) : '-'}
      </mjl:dt>      
      <mjl:dt attribute="rdtDetail">
        ${item.rdtDetail.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="rdtTreatment">
        ${item.rdtTreatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="performedBloodslide">
        ${item.performedBloodslide ? item.performedBloodslideMd.positiveDisplayLabel : item.performedBloodslideMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="bloodslideReason">
        ${item.bloodslideReason.displayLabel}
      </mjl:dt>      
      <mjl:dt attribute="bloodslideResult">
        ${item.bloodslideResult != null  ? (item.bloodslideResult ? item.bloodslideResultMd.positiveDisplayLabel : item.bloodslideResultMd.negativeDisplayLabel) : '-'}
      </mjl:dt>
      <mjl:dt attribute="bloodslideDetail">
        ${item.bloodslideDetail.displayLabel}
      </mjl:dt>      
      <mjl:dt attribute="fever">
        <ul>
          <c:forEach items="${item.feverEnumNames}" var="enumName">
            <li>
              ${item.feverMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="malaria">
        <ul>
          <c:forEach items="${item.malariaEnumNames}" var="enumName">
            <li>
              ${item.malariaMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="malariaConformationTechnique">
        ${item.malariaConformationTechnique.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="displayLocations">
        <ul>
          <c:forEach items="${locations}" var="current"> 
            <li> ${current.displayLabel} </li> 
          </c:forEach>
        </ul>      
      </mjl:dt>
      <mjl:dt attribute="displayTreatments">
        <ul>
          <c:forEach items="${treatments}" var="current"> 
            <li> ${current.displayLabel} </li> 
          </c:forEach>
        </ul>      
      </mjl:dt>      
      <mjl:dt attribute="payment">
        ${item.payment.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
  <mdss:localize key="Back_To_Household"/>
  <mjl:property name="id" value="${item.household.id}" />
</mjl:commandLink>
