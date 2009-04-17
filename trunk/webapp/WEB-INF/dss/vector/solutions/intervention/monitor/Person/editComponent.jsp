<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:input param="household" type="hidden" value="${item.household.id}" />
    <dl>
      <dt>
        <label>
          ${item.anaemiaTreatmentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${anaemiaTreatment}" param="anaemiaTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="anaemiaTreatment">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.bloodslideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${bloodslide}" param="bloodslide">
          <mjl:option selected="${mjl:contains(item.bloodslideEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.bloodslideMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="bloodslide">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.dobMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="dob" />
        <mjl:messages attribute="dob">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.feverMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${fever}" param="fever">
          <mjl:option selected="${mjl:contains(item.feverEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.feverMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="fever">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.feverTreatmentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${feverTreatment}" param="feverTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="feverTreatment">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.haemoglobinMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="haemoglobin" />
        <mjl:messages attribute="haemoglobin">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.haemoglobinMeasuredMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="haemoglobinMeasured" />
        <mjl:messages attribute="haemoglobinMeasured">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.ironMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="iron" />
        <mjl:messages attribute="iron">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.malariaMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${malaria}" param="malaria">
          <mjl:option selected="${mjl:contains(item.malariaEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.malariaMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="malaria">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.malariaTreatmentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${malariaTreatment}" param="malariaTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="malariaTreatment">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.paymentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${payment}" param="payment">
          <mjl:option selected="${mjl:contains(item.paymentEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.paymentMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="payment">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.performedRDTMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${performedRDT}" param="performedRDT">
          <mjl:option selected="${mjl:contains(item.performedRDTEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.performedRDTMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="performedRDT">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.personIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="personId" />
        <mjl:messages attribute="personId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.pregnantMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="pregnant" />
        <mjl:messages attribute="pregnant">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.RDTResultMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:checkboxGroup var="current" valueAttribute="enumName" items="${rDTResult}" param="rDTResult">
          <mjl:checkboxOption checked="${mjl:contains(item.RDTResultEnumNames, current.enumName) ? 'checked' : 'false'}">
            ${item.RDTResultMd.enumItems[current.enumName]}
          </mjl:checkboxOption>
        </mjl:checkboxGroup>
        <mjl:messages attribute="rDTResult">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.rdtTreatmentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${rdtTreatment}" param="rdtTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="rdtTreatment">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sexMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sex">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sleptUnderNetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="sleptUnderNet" />
        <mjl:messages attribute="sleptUnderNet">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.PersonController.update.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.PersonController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.PersonController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.cancel.button" />
</mjl:form>
