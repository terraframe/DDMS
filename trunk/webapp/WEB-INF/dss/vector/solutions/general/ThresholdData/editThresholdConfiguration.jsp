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
		<mjl:dt attribute="weeksBefore"><mjl:input type="text" param="weeksBefore"/></mjl:dt>
		<mjl:dt attribute="weeksAfter"><mjl:input type="text" param="weeksAfter"/></mjl:dt>
		<mjl:dt attribute="priorYears"><mjl:input type="text" param="priorYears"/></mjl:dt>
		
		<mjl:dt attribute="weight0"><mjl:input type="text" param="weight0"/></mjl:dt>
		<mjl:dt attribute="weight1"><mjl:input type="text" param="weight1"/></mjl:dt>
		<mjl:dt attribute="weight2"><mjl:input type="text" param="weight2"/></mjl:dt>
		<mjl:dt attribute="weight3"><mjl:input type="text" param="weight3"/></mjl:dt>
		<mjl:dt attribute="weight4"><mjl:input type="text" param="weight4"/></mjl:dt>
		<mjl:dt attribute="weight5"><mjl:input type="text" param="weight5"/></mjl:dt>
		<mjl:dt attribute="weight6"><mjl:input type="text" param="weight6"/></mjl:dt>
		<mjl:dt attribute="weight7"><mjl:input type="text" param="weight7"/></mjl:dt>
		<mjl:dt attribute="weight8"><mjl:input type="text" param="weight8"/></mjl:dt>
		<mjl:dt attribute="weight9"><mjl:input type="text" param="weight9"/></mjl:dt>
		
    </mjl:component>
    
    <input type="radio" name="currentYear" value="true" checked="checked"/> <fmt:message key="Current_Year"/>    
    <input type="radio" name="currentYear" value="false"/> <fmt:message key="Next_Year"/>    
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration.mojo" name="search" value="Submit"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.calculateThresholds.mojo" name="calculate" value="Calculate"/>
  </dl>
</mjl:form>