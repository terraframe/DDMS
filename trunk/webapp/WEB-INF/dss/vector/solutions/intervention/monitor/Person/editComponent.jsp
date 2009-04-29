<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:input param="concreteId" type="hidden" value="${item.concreteId}" />
    <mjl:input param="household" type="hidden" value="${item.household.id}" />
    <dl>
      <mjl:dt attribute="anaemiaTreatment">
        <mjl:select var="current" valueAttribute="id" items="${anaemiaTreatment}" param="anaemiaTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="bloodslide">
        <mjl:select var="current" valueAttribute="enumName" items="${bloodslide}" param="bloodslide">
          <mjl:option selected="${mjl:contains(item.bloodslideEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.bloodslideMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="dob">
        <mjl:input type="text" param="dob" id="dob" classes="DatePick"/>
        
</mjl:dt>
      <mjl:dt attribute="fever">
        <mjl:select var="current" valueAttribute="enumName" items="${fever}" param="fever">
          <mjl:option selected="${mjl:contains(item.feverEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.feverMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="feverTreatment">
        <mjl:select var="current" valueAttribute="id" items="${feverTreatment}" param="feverTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="haemoglobin">
        <mjl:input type="text" param="haemoglobin" />
        
</mjl:dt>
      <mjl:dt attribute="haemoglobinMeasured">
        <mjl:boolean param="haemoglobinMeasured" />
        
</mjl:dt>
      <mjl:dt attribute="iron">
        <mjl:boolean param="iron" />
        
</mjl:dt>
      <mjl:dt attribute="malaria">
        <mjl:select var="current" valueAttribute="enumName" items="${malaria}" param="malaria">
          <mjl:option selected="${mjl:contains(item.malariaEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.malariaMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="malariaTreatment">
        <mjl:select var="current" valueAttribute="id" items="${malariaTreatment}" param="malariaTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="payment">
        <mjl:select var="current" valueAttribute="enumName" items="${payment}" param="payment">
          <mjl:option selected="${mjl:contains(item.paymentEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.paymentMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="performedRDT">
        <mjl:select var="current" valueAttribute="enumName" items="${performedRDT}" param="performedRDT">
          <mjl:option selected="${mjl:contains(item.performedRDTEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.performedRDTMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="personId">
        <mjl:input type="text" param="personId" />
        
</mjl:dt>
      <mjl:dt attribute="pregnant">
        <mjl:boolean param="pregnant" />
        
</mjl:dt>
      <mjl:dt attribute="RDTResult">
        <mjl:checkboxGroup var="current" valueAttribute="enumName" items="${rDTResult}" param="rDTResult">
          <mjl:checkboxOption checked="${mjl:contains(item.RDTResultEnumNames, current.enumName) ? 'checked' : 'false'}">
            ${item.RDTResultMd.enumItems[current.enumName]}
          </mjl:checkboxOption>
        </mjl:checkboxGroup>
        
</mjl:dt>
      <mjl:dt attribute="rdtTreatment">
        <mjl:select var="current" valueAttribute="id" items="${rdtTreatment}" param="rdtTreatment">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        <mjl:boolean param="sleptUnderNet" />
        
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.PersonController.update.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.PersonController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.PersonController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.cancel.button" />
</mjl:form>
