<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<c:set var="page_title" value="Search_Aggregated_Case"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:form name="search" method="POST" id ="searchAggregatedCase">
  <dl>
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="searchType">
        <mjl:boolean param="searchType" id="searchType"/>
      </mjl:dt>
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" value="${item.geoEntity}" universals="${entityUniversals}"/>       
      </mjl:dt>
      <div class="periodType">
        <mjl:dt attribute="periodType">
          <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${periodType}" param="periodType">
            <mjl:groupOption checked="${current.enumName == checkedType ? 'checked' : 'false'}" id="periodType.${current.enumName}" classes="periodTypeOption">
              ${current.displayLabel}
            </mjl:groupOption>
          </mjl:group>      
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
      <mjl:dt attribute="ageGroup">
        <mjl:select var="current" valueAttribute="id" includeBlank="true" items="${ageGroups}"  param="ageGroup" id="search.select.id">
          <mjl:option selected="${current.id == search.ageGroup.id ? 'selected' : 'false'}">
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>      
    </mjl:component>
    
    <mdss:localize key="Search" var="Localized_Search" />
    
    <mjl:command classes="submitButton" action="dss.vector.solutions.surveillance.AggregatedCaseController.searchByView.mojo" name="search.button" id="button.id" value="${Localized_Search}" />
  </dl>
</mjl:form>

<br />
<form id="export" name="export" action="dss.vector.solutions.surveillance.AggregatedCaseController.exportExcelTemplate.mojo" method="post" target="messageFrame">
  <mdss:localize key="Excel_Export_Header" var="export_label"/>
  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
</form>
<form id="import" name="import" action="excelimport" method="post">
  <mdss:localize key="Excel_Import_Header" var="import_label"/>
  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
</form>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{AggregatedCaseViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
	YAHOO.util.Event.onDOMReady(function(){
			  		
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

    var validator = new MDSS.SearchValidator('searchType', {button:'button.id', geoId:'geoEntity', startDate:'startDate', endDate:'endDate', year:'periodYear', period:'period', periodType:'periodTypeOption'});

    Mojo.GLOBAL.onValidGeoEntitySelected = function() {
      validator.validate();
    }

    var autocomplete = MDSS.GenericSearch.createYearSearch('periodYear');
    autocomplete.addListener(Mojo.Util.bind(validator, validator.eventHandler));

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);
    
    // Localize the startDate and endDate field values
    MDSS.Calendar.localizeDateElement(document.getElementById('startDate'));
    MDSS.Calendar.localizeDateElement(document.getElementById('endDate'));
    
    validator.validate();    
  })
})();  
</script>

<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>