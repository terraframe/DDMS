<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <mjl:input value="${item.concreteId}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.anaemiaTreatmentMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.anaemiaTreatment.keyName}" action="dss.vector.solutions.intervention.DrugController.view.mojo" name="dss.vector.solutions.intervention.Drug.form.view.link">
        <mjl:property value="${item.anaemiaTreatment.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.bloodslideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.bloodslideEnumNames}">
          <li>
            ${item.bloodslideMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.dobMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.dob}
    </dd>
    <dt>
      <label>
        ${item.feverMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.feverEnumNames}">
          <li>
            ${item.feverMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.feverTreatmentMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.feverTreatment.keyName}" action="dss.vector.solutions.intervention.DrugController.view.mojo" name="dss.vector.solutions.intervention.Drug.form.view.link">
        <mjl:property value="${item.feverTreatment.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.haemoglobinMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.haemoglobin}
    </dd>
    <dt>
      <label>
        ${item.haemoglobinMeasuredMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.haemoglobinMeasured}
    </dd>
    <dt>
      <label>
        ${item.householdMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.household.householdName}" action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.view.link">
        <mjl:property value="${item.household.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.ironMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.iron}
    </dd>
    <dt>
      <label>
        ${item.malariaMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.malariaEnumNames}">
          <li>
            ${item.malariaMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.malariaTreatmentMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.malariaTreatment.keyName}" action="dss.vector.solutions.intervention.DrugController.view.mojo" name="dss.vector.solutions.intervention.Drug.form.view.link">
        <mjl:property value="${item.malariaTreatment.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.paymentMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.paymentEnumNames}">
          <li>
            ${item.paymentMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.performedRDTMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.performedRDTEnumNames}">
          <li>
            ${item.performedRDTMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.personIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.personId}
    </dd>
    <dt>
      <label>
        ${item.pregnantMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.pregnant}
    </dd>
    <dt>
      <label>
        ${item.RDTResultMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.RDTResultEnumNames}">
          <li>
            ${item.RDTResultMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.rdtTreatmentMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.rdtTreatment.keyName}" action="dss.vector.solutions.intervention.DrugController.view.mojo" name="dss.vector.solutions.intervention.Drug.form.view.link">
        <mjl:property value="${item.rdtTreatment.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          <li>
            ${item.sexMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.sleptUnderNetMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sleptUnderNet}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.PersonController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.edit.button" />
  <br />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>
