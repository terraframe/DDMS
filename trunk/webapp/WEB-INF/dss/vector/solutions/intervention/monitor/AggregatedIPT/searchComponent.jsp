<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<c:set var="page_title" value="Search_Aggregated_IPT"  scope="request"/>

<mjl:form name="search" method="POST" id ="searchAggregatedCase">
  <dl>
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="searchType">
        <mjl:boolean param="searchType" id="searchType"/>
      </mjl:dt>
      <mjl:dt attribute="geoId">
        <mdss:geo param="geoId" concrete="false" value="${entity}" universals="${entityUniversals}">
          <mdss:filter universal="" id="all" checked="true">&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;</mdss:filter>
          <mdss:filter universal="${HealthFacility}" id="health_facility">&nbsp;<fmt:message key="Health_Facility"/> &nbsp;&nbsp;&nbsp; <br /></mdss:filter>              
        </mdss:geo>       
      </mjl:dt>
      <div class="periodType">
        <mjl:dt attribute="periodType">
          <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${periodType}" param="periodType">
            <mjl:radioOption checked="${current.enumName == checkedType ? 'checked' : 'false'}" id="periodType.${current.enumName}" classes="periodTypeOption">
              ${current.displayLabel}
            </mjl:radioOption>
          </mjl:radioGroup>      
        </mjl:dt>
      </div>
      <div class="period">
        <mjl:dt attribute="period">
          <mjl:input param="period" type="text" size="2" maxlength="2" value="${period}" id="period" classes="NumbersOnly"/>
        </mjl:dt>
      </div>
      <div class="periodYear">
        <mjl:dt attribute="periodYear">
          <mjl:input param="periodYear" type="text" size="4" maxlength="4" value="${year}" classes="NoFutureYear" id='periodYear'/>
          <mjl:messages attribute="periodYear">
            <mjl:message/>
          </mjl:messages>
        </mjl:dt>
      </div>
      <div class="startDate">
        <mjl:dt attribute="startDate">
          <mjl:input type="text" param="startDate" classes="DatePick NoFuture" id="startDate"/>
        </mjl:dt>
      </div>
      <div class="endDate">
        <mjl:dt attribute="endDate">
          <mjl:input type="text" param="endDate" classes="DatePick NoFuture"  id="endDate"/>
        </mjl:dt>
      </div>
    </mjl:component>
    
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.searchByView.mojo" name="search.button" id="button.id" value="Search" />
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.AggregatedIPTExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{AggregatedCaseViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    
    MDSS.GenericSearch.createYearSearch('periodYear');
        
    var dateValidator = new MDSS.DateSearchValidator({button:'button.id', geoId:'geoId', startDate:'startDate', endDate:'endDate'});
    var epiValidator = new MDSS.EpiSearchValidator({button:'button.id', geoId:'geoId', year:'periodYear', period:'period', periodType:'periodTypeOption'});

    Mojo.GLOBAL.onValidGeoEntitySelected = function() {
      var searchType = document.getElementById('searchType.positive');

      if(searchType.checked == true) {
        dateValidator.validate();          
      }
      else {
        epiValidator.validate();  
      }
    }
        

    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var periodType = new MDSS.HiddenRadioElement({element:'periodType', option:"periodTypeOption"});
    var period = new MDSS.HiddenInputElement({element:'period'});
    var periodYear = new MDSS.HiddenInputElement({element:'periodYear'});
    var startDate = new MDSS.HiddenInputElement({element:'startDate'});
    var endDate = new MDSS.HiddenInputElement({element:'endDate'});
        
    MDSS.ElementHandler.setupBooleanHandler('searchType.negative', 'searchType.positive', [periodType, period, periodYear]);
    MDSS.ElementHandler.setupBooleanHandler('searchType.positive', 'searchType.negative', [startDate, endDate]);    
  })
})();  
</script>