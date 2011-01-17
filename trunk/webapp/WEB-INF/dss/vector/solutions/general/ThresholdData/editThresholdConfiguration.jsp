<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.general.ThresholdDataController"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.general.ThresholdCalculationTypeViewDTO"%><c:set var="page_title" value="Configure_Thresholds"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="ThresholdData.search.mojo" method="POST" id="threshold.form">
  <dl>
    <mjl:component item="${thresholdCalculation}" param="thresholdCalculation">    
      <h2><fmt:message key="current_case_count_parameters" /></h2>
      <mjl:dt attribute="countingMethod">
        <mjl:group type="radio" param="countingMethod" items="${methods}" var="current" valueAttribute="enumName">
          <mjl:groupOption checked="${(thresholdCalculation.countingMethod[0]==current.enumName) ? 'checked' : 'false'}">
             ${current.displayLabel}
          </mjl:groupOption>
        </mjl:group>    
      </mjl:dt> 
      <mjl:dt attribute="epidemicUniversal">
        <mjl:select param="epidemicUniversal" items="${views}" var="current" valueAttribute="geoHierarchyId" >
          <mjl:option selected="${epidemicUniversal != null && epidemicUniversal == current.geoHierarchyId ? 'selected' : 'false'}">
            ${current.displayLabel}
          </mjl:option>    
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="clinicalPositivePercentage">
        <mjl:input type="text" param="clinicalPositivePercentage"/>
      </mjl:dt>
      <hr/>
      <h2><fmt:message key="threshold_calculation_parameters"/></h2>
      <mjl:dt attribute="caseTypes">
        <mjl:select valueAttribute="enumName" param="caseTypes" items="${thresholdCalculationCaseTypes}" var="current">
          <mjl:option>${current.displayLabel}</mjl:option>
        </mjl:select>
      </mjl:dt>
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
      <mjl:dt attribute="notificationMinimum">
        <mjl:input type="text" param="notificationMinimum"/>
      </mjl:dt>
      <mjl:dt attribute="identificationMinimum">
        <mjl:input type="text" param="identificationMinimum"/>
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
            <th><mdss:localize key="Weight"/></th>
          </tr>
          <tr class="oddRow" id="weight0" style="display:none;">
            <td>${thresholdCalculation.weight0Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight0" /><mjl:messages attribute="weight0"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="evenRow" id="weight1" style="display:none;">
            <td>${thresholdCalculation.weight1Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight1" /><mjl:messages attribute="weight1"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="oddRow" id="weight2" style="display:none;">
            <td>${thresholdCalculation.weight2Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight2" /><mjl:messages attribute="weight2"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="evenRow" id="weight3" style="display:none;">
            <td>${thresholdCalculation.weight3Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight3" /><mjl:messages attribute="weight3"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="oddRow" id="weight4" style="display:none;">
            <td>${thresholdCalculation.weight4Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight4" /><mjl:messages attribute="weight4"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="evenRow" id="weight5" style="display:none;">
            <td>${thresholdCalculation.weight5Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight5" /><mjl:messages attribute="weight5"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="oddRow" id="weight6" style="display:none;">
            <td>${thresholdCalculation.weight6Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight6" /><mjl:messages attribute="weight6"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="evenRow" id="weight7" style="display:none;">
            <td>${thresholdCalculation.weight7Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight7" /><mjl:messages attribute="weight7"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="oddRow" id="weight8" style="display:none;">
            <td>${thresholdCalculation.weight8Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight8" /><mjl:messages attribute="weight8"><mjl:message /></mjl:messages></td>
          </tr>
          <tr class="evenRow" id="weight9" style="display:none;">
            <td>${thresholdCalculation.weight9Md.displayLabel}</td>
            <td><mjl:input type="text" param="weight9" /><mjl:messages attribute="weight9"><mjl:message /></mjl:messages></td>
          </tr>
        </table>    
      </div>
    </mjl:component>
    
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration.mojo" name="search" value="Submit"/>
    
    <hr />
    
    <dt>
      <label> ${thresholdCalculation.calculationIntervalMd.displayLabel}</label>
    </dt>
    <dd>
      <input type="radio" name="currentYear" value="true" checked="checked"/> ${thresholdCalculation.calculationIntervalMd.positiveDisplayLabel}    
      <input type="radio" name="currentYear" value="false"/> ${thresholdCalculation.calculationIntervalMd.negativeDisplayLabel}  
    </dd>
    <dd>
      <c:if test="${!active}">
        <input type="button" id="calculatePolitical.button" value=""  />
        <input type="button" id="calculateFacility.button" value="" />
        (<mdss:localize key="Calculations_may_take_time"/>)
      </c:if>
      <c:if test="${active}">
        <input type="button" id="calculatePolitical.button" disabled="disabled" value=""  />
        <input type="button" id="calculateFacility.button" disabled="disabled" value="" />
        (<mdss:localize key="Calculations_already_in_progress"/>: ${percentComplete}% complete) 
      </c:if>
    </dd>
    
    <hr />
    
    <dd>
      <mjl:command name="exportHistory.button" action="dss.vector.solutions.general.ThresholdDataController.exportHistory.mojo" value="Export_Threshold_History" />
    </dd>
  </dl>
</mjl:form>

<%=Halp.loadTypes(Arrays.asList(new String[]{ThresholdCalculationTypeViewDTO.CLASS, ThresholdDataController.CLASS}))%>

<script type="text/javascript">
(function(){
    YAHOO.util.Event.onDOMReady(function(){
      var politicalButton = document.getElementById('calculatePolitical.button');    
      politicalButton.value = MDSS.localize('CalculatePoliticalThresholds');
        
      var calculatePoliticalThresholds = function(e) {
        e.preventDefault();
        YAHOO.util.Event.stopEvent(e);

        var params = Mojo.Util.collectFormValues('threshold.form');
        params["thresholdCalculation.epidemicUniversal"] = params["thresholdCalculation.epidemicUniversal"][0];
		
        var func = function(request)
        {
          Mojo.$.dss.vector.solutions.general.ThresholdDataController.calculatePoliticalThresholdsMap(request, params);                    
        }

        new MDSS.ProgressRequest(func, Mojo.$.dss.vector.solutions.general.ThresholdCalculationTypeView.getPercentComplete, 'Calculating_Thresholds').start();
      };

      var facilityButton = document.getElementById('calculateFacility.button');    
      facilityButton.value = MDSS.localize('CalculateFacilityThresholds');

      var calculateFacilityThresholds = function(e) {
        e.preventDefault();
        YAHOO.util.Event.stopEvent(e);

        var params = Mojo.Util.collectFormValues('threshold.form');
        params["thresholdCalculation.epidemicUniversal"] = params["thresholdCalculation.epidemicUniversal"][0];
        
        var func = function(request)
        {
          Mojo.$.dss.vector.solutions.general.ThresholdDataController.calculateFacilityThresholdsMap(request, params);
        }

        new MDSS.ProgressRequest(func, Mojo.$.dss.vector.solutions.general.ThresholdCalculationTypeView.getPercentComplete, 'Calculating_Thresholds').start();
      };

      
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

      YAHOO.util.Event.addListener('priorYears', "keyup", priorYearsHandler);  
      YAHOO.util.Event.on(politicalButton, 'click', calculatePoliticalThresholds);    
      YAHOO.util.Event.on(facilityButton, 'click', calculateFacilityThresholds);    
      
      priorYearsHandler();
    });
})();
</script>