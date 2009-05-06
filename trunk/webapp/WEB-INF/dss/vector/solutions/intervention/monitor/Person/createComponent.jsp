<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <dl>
    <mjl:component item="${item}" param="dto">
      <mjl:input param="household" type="hidden" value="${item.household.id}" />
      <mjl:dt attribute="personId">
        <mjl:input type="text" param="personId" />        
      </mjl:dt>     
      <mjl:dt attribute="anaemiaTreatment">
        <mjl:select var="current" includeBlank="true" valueAttribute="id" items="${anaemiaTreatment}" param="anaemiaTreatment">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="bloodslide">
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${bloodslide}" param="bloodslide">
          <mjl:option selected="${mjl:contains(item.bloodslideEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.bloodslideMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>        
      </mjl:dt>
      <mjl:dt attribute="dob">
        <mjl:input type="text" param="dob" id="dob" classes="DatePick" />        
      </mjl:dt>
      <mjl:dt attribute="age">
        <mjl:input type="text" param="age" id="age" size="3" maxlength="3"/>        
      </mjl:dt>
      <mjl:dt attribute="fever">
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${fever}" param="fever">
          <mjl:option selected="${mjl:contains(item.feverEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.feverMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="feverTreatment">
        <mjl:select var="current" includeBlank="true" valueAttribute="id" items="${feverTreatment}" param="feverTreatment">
          <mjl:option>
            ${current.displayLabel}
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
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${malaria}" param="malaria">
          <mjl:option selected="${mjl:contains(item.malariaEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.malariaMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="malariaTreatment">
        <mjl:select var="current" includeBlank="true" valueAttribute="id" items="${malariaTreatment}" param="malariaTreatment">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="payment">
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${payment}" param="payment">
          <mjl:option selected="${mjl:contains(item.paymentEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.paymentMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="performedRDT">
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${performedRDT}" param="performedRDT">
          <mjl:option selected="${mjl:contains(item.performedRDTEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.performedRDTMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>       
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
        <mjl:select var="current" includeBlank="true" valueAttribute="id" items="${rdtTreatment}" param="rdtTreatment">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" includeBlank="true" valueAttribute="enumName" items="${sex}" param="sex">
          <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        <mjl:boolean param="sleptUnderNet" />
        
</mjl:dt>
    </mjl:component>
  </dl>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.PersonController.create.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.create.button" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>

<script type="text/javascript">
  var age = document.getElementById('age');
  var dob = document.getElementById('dob');

  age.onblur=function()
  {
	  if(age.value.length > 0) dob.disabled=true;
	  else
		  dob.disabled=false;
  };

  dob.onblur=function()
  {
	  if(dob.value.length > 0) age.disabled=true;
	  else
		  age.disabled=false;
  };

  MojoCal.init();
  
  Event.addListener('cal1Container', 'mouseout', function(){
	  var age = document.getElementById('age');
	  var dob = document.getElementById('dob');
		  
	  if(dob.value.length > 0) age.disabled=true;
	  else
		  age.disabled=false;
	  });  
</script>
