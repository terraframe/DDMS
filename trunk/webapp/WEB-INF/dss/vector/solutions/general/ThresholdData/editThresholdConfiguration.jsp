<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Configure_Thresholds"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="PopulationData.search.mojo" method="POST">
  <dl>
    <dt>
      <label><fmt:message key="Epidemic_Universal"/></label>
    </dt>
    <dd>    
      <mjl:select var="current" valueAttribute="geoHierarchyId" param="universal" items="${views}">
        <mjl:option selected="${hierarchy != null && hierarchy == current.geoHierarchyId ? 'selected' : ''}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label> <fmt:message key="Counting_Method"/></label>
    </dt>
    <dd>
      <mjl:radioGroup var="current" valueAttribute="enumName" items="${methods}" param="calulationMethod">
       <mjl:radioOption  checked="${isEpiWeek != null && isEpiWeek == current.enumName ? 'checked' : ''}">
          ${current.displayLabel}
       </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <mjl:component item="${thresholdCalculation}" param="thresholdCalculation">
		  <mjl:dt attribute="t1Method">
			  <mjl:select valueAttribute="enumName" param="t1Method" items="${thresholdCalculationMethods}" var="current">
				  <mjl:option>${current.displayLabel}</mjl:option>
			  </mjl:select>
		  </mjl:dt>
		  <mjl:dt attribute="t2Method">
			  <mjl:select valueAttribute="enumName" param="t2Method" items="${thresholdCalculationMethods}" var="current">
				  <mjl:option>${current.displayLabel}</mjl:option>
			  </mjl:select>
		  </mjl:dt>
		  <mjl:dt attribute="weeksBefore">
		    <mjl:input type="text" param="weeksBefore"/>
		  </mjl:dt>
		  <mjl:dt attribute="weeksAfter">
		    <mjl:input type="text" param="weeksAfter"/>
		  </mjl:dt>
		  <mjl:dt attribute="priorYears">
		    <mjl:input type="text" param="priorYears" id="priorYears" size="1" maxlength="1"/>
		  </mjl:dt>
		
		  <div id="weightsDiv" style="display:none;">
  		  <table class="displayTable">
          <tr>
            <th></th>
            <th><fmt:message key="Weight"/></th>
          </tr>
          <tr class="oddRow" id="weight0" style="display:none;">
            <td>${thresholdCalculation.weight0Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight0" /></td>
          </tr>
          <tr class="evenRow" id="weight1" style="display:none;">
            <td>${thresholdCalculation.weight1Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight1" /></td>
          </tr>
          <tr class="oddRow" id="weight2" style="display:none;">
            <td>${thresholdCalculation.weight2Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight2" /></td>
          </tr>
          <tr class="evenRow" id="weight3" style="display:none;">
            <td>${thresholdCalculation.weight3Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight3" /></td>
          </tr>
          <tr class="oddRow" id="weight4" style="display:none;">
            <td>${thresholdCalculation.weight4Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight4" /></td>
          </tr>
          <tr class="evenRow" id="weight5" style="display:none;">
            <td>${thresholdCalculation.weight5Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight5" /></td>
          </tr>
          <tr class="oddRow" id="weight6" style="display:none;">
            <td>${thresholdCalculation.weight6Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight6" /></td>
          </tr>
          <tr class="evenRow" id="weight7" style="display:none;">
            <td>${thresholdCalculation.weight7Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight7" /></td>
          </tr>
          <tr class="oddRow" id="weight8" style="display:none;">
            <td>${thresholdCalculation.weight8Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight8" /></td>
          </tr>
          <tr class="evenRow" id="weight9" style="display:none;">
            <td>${thresholdCalculation.weight9Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight9" /></td>
          </tr>
        </table>		
      </div>
    </mjl:component>
    
    
    <dt>
      <label> <fmt:message key="Calculation_Interval"/> </label>
    </dt>
    <dd>
      <input type="radio" name="currentYear" value="true" checked="checked"/> <fmt:message key="Current_Year"/>    
      <input type="radio" name="currentYear" value="false"/> <fmt:message key="Next_Year"/>    
    </dd>
    
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration.mojo" name="search" value="Submit"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.calculateThresholds.mojo" name="calculate" value="Calculate"/>
  </dl>
</mjl:form>

<script type="text/javascript">
(function(){
	  YAHOO.util.Event.onDOMReady(function(){
		  var priorYearsHandler = function(){
		    var value = document.getElementById('priorYears').value;
        var weightsDiv = document.getElementById('weightsDiv');
        
		    if(value !== "" && value * 1 > 0) {
			    var priorYears = value * 1;
			    weightsDiv.style.display = "block";

			    for(var i = 0; i < priorYears; i++) {
            var weightEl = document.getElementById('weight' + i);
            weightEl.style.display = "table-row";            
			    }

			    for(var i = priorYears; i < 10; i++) {
				    var weightEl = document.getElementById('weight' + i);
				    weightEl.style.display = "none";            
		      }		 			    
		    }
		    else {
			    weightsDiv.style.display = "none";
			    for(var i = 0; i < 10; i++) {
            var weightEl = document.getElementById('weight' + i);
			      weightEl.style.display = "none";            
			    }
		    }
		  }

		  YAHOO.util.Event.addListener('priorYears', "blur", priorYearsHandler);  

		  priorYearsHandler();
	  });
})();
</script>