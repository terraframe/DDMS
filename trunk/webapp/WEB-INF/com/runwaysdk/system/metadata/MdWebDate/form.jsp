<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<%@include file="../MdWebPrimitive/form.jsp" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
  <mjl:dt attribute="showOnSearch">
    <mjl:boolean param="showOnSearch" />
  </mjl:dt>  
  
  
  <dt><label><mdss:localize key="Date_Restriction"/></label></dt>
  <dd>
    <input type="radio" name="dateValidation" value="none" id="dateN" /> <mdss:localize key="None"/> <br />
    <input type="radio" name="dateValidation" value="beforeTodayExclusive" id="dateBTE" /> ${item.beforeTodayExclusiveMd.displayLabel}<br />
    <input type="radio" name="dateValidation" value="beforeTodayInclusive" id="dateBTI" /> ${item.beforeTodayInclusiveMd.displayLabel}<br />
    <input type="radio" name="dateValidation" value="afterTodayExclusive" id="dateATE"/> ${item.afterTodayExclusiveMd.displayLabel}<br />
    <input type="radio" name="dateValidation" value="afterTodayInclusive" id=dateATI /> ${item.afterTodayInclusiveMd.displayLabel}<br />
    <input type="radio" name="dateValidation" value="range" id="dateR"/> <mdss:localize key="Range"/> <br />
  </dd>

  <mjl:input type="hidden" param="beforeTodayExclusive" id="beforeTodayExclusive" />
  <mjl:input type="hidden" param="beforeTodayInclusive" id="beforeTodayInclusive" />
  <mjl:input type="hidden" param="afterTodayExclusive" id="afterTodayExclusive" />
  <mjl:input type="hidden" param="afterTodayInclusive" id="afterTodayInclusive" />
  
  <div id="dateRange" style="display: none;">
    <mjl:dt attribute="startDate">
      <mjl:input id="${item.id}_startDate" param="startDate" type="text" />
    </mjl:dt>
    <mjl:dt attribute="endDate">
      <mjl:input id="${item.id}_endDate" param="endDate" type="text" />
    </mjl:dt>
  </div>
</mjl:component>
<script type="text/javascript">
(function(){
  var sd = document.getElementById('${item.id}_startDate');
  var ed = document.getElementById('${item.id}_endDate');

  MDSS.Calendar.addCalendarListeners(sd);
  MDSS.Calendar.addCalendarListeners(ed);
  
  sd.value = MDSS.Calendar.getLocalizedString(sd.value);
  ed.value = MDSS.Calendar.getLocalizedString(ed.value);

  // Get the hidden inputs
  var bte = document.getElementById('beforeTodayExclusive');
  var bti = document.getElementById('beforeTodayInclusive');
  var ate = document.getElementById('afterTodayExclusive');
  var ati = document.getElementById('afterTodayInclusive');
  var dateRange = document.getElementById('dateRange');

  // Get the radio inputs
  var dateN = document.getElementById('dateN');
  var dateBTE = document.getElementById('dateBTE');
  var dateBTI = document.getElementById('dateBTI');
  var dateATE = document.getElementById('dateATE');
  var dateATI = document.getElementById('dateATI');
  var dateR = document.getElementById('dateR');


  var none = true;  // Variable which indicates if any restrictions have been placed
  
  var setChecked = function(element) {
    none = false;
    element.checked = true;
  }
  
  // Set checked value
  if(bte.value == 'true') {
	setChecked(dateBTE);
  }
  else if(bti.value == 'true') {
    setChecked(dateBTI);
  }
  else if(ate.value == 'true') {
    setChecked(dateATE);
  }
  else if(ati.value == 'true') {
    setChecked(dateATI);
  }
  else if(sd.value != '' || ed.value != '') {
    setChecked(dateR);
    dateRange.style.display='block';
  }
  else if(none) {
    dateN.checked = true;
  }
  
  // Function to clear all of the hidden inputs
  var clearValues = function() 
  {
    bte.value=false;
    bti.value=false;    
    ate.value=false;
    ati.value=false;    
    dateRange.style.display='none';
    sd.value='';
    ed.value='';
  }; 

  // Set the event handler for the validation radio button
  dateN.addEventListener('change', clearValues, false);
  dateBTE.addEventListener('change', function() {clearValues(); bte.value=true;}, false);  
  dateBTI.addEventListener('change', function() {clearValues(); bti.value=true;}, false);  
  dateATE.addEventListener('change', function() {clearValues(); ate.value=true;}, false);  
  dateATI.addEventListener('change', function() {clearValues(); ati.value=true;}, false);  
  dateR.addEventListener('change', function() {clearValues(); dateRange.style.display='block';}, false);  
})();
</script>
