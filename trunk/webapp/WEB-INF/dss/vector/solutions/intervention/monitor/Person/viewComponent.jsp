<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">  
      <mjl:dt attribute="personId">
        ${item.personId}
      </mjl:dt>
      <mjl:dt attribute="dob">
        <span id="dob" class="formatDate"> ${item.dob} </span>
      </mjl:dt>
      <mjl:dt attribute="age">
        ${item.age}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <ul>
          <c:forEach var="enumName" items="${item.sexEnumNames}">
            <li>${item.sexMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="pregnant">
        ${item.pregnant}
      </mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        ${item.sleptUnderNet}
      </mjl:dt>
      <mjl:dt attribute="haemoglobinMeasured">
        ${item.haemoglobinMeasured}
      </mjl:dt>
      <mjl:dt attribute="haemoglobin">
        ${item.haemoglobin}
      </mjl:dt>         
      <mjl:dt attribute="anaemiaTreatment">
        ${item.anaemiaTreatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="iron">
        ${item.iron}
      </mjl:dt>
      <mjl:dt attribute="performedRDT">
        <ul>
          <c:forEach var="enumName" items="${item.performedRDTEnumNames}">
            <li>${item.performedRDTMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="rDTResult">
        <ul>
          <c:forEach var="enumName" items="${item.RDTResultEnumNames}">
            <li>${item.RDTResultMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="rdtTreatment">
        ${item.rdtTreatment.displayLabel}
      </mjl:dt>      
      <mjl:dt attribute="bloodslide">
        <ul>
          <c:forEach var="enumName" items="${item.bloodslideEnumNames}">
            <li>${item.bloodslideMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="fever">
        <ul>
          <c:forEach var="enumName" items="${item.feverEnumNames}">
            <li>${item.feverMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="feverTreatment">
        ${item.feverTreatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="malaria">
        <ul>
          <c:forEach var="enumName" items="${item.malariaEnumNames}">
            <li>${item.malariaMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="malariaTreatment">
        ${item.malariaTreatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="payment">
        <ul>
          <c:forEach var="enumName" items="${item.paymentEnumNames}">
            <li>${item.paymentMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.PersonController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink display="Back_To_Household" action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
  <mjl:property name="id" value="${item.household.id}" />
</mjl:commandLink>
<div id="cal1Container" class="yui-skin-sam"></div>
