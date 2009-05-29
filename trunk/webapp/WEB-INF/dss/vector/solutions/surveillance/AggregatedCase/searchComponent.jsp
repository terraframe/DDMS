<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>


<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
</script>

<mjl:form name="search" method="POST" id ="searchAggregatedCase">
  <dl>
    <dt> <fmt:message key="Geo_Id"/> </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" value="${geoId}" maxlength="16" classes="geoInput"/></dd>
    <dt> <fmt:message key="Period_Type"/> </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${periodType}" param="periodType">
        <mjl:radioOption checked="${current.enumName == checkedType ? 'checked' : 'false'}" >
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt> <fmt:message key="Period"/> </dt>
    <dd>
      <mjl:input param="period" type="text" size="2" maxlength="2" value="${period}" id="period" classes="NumbersOnly"/>
      <mjl:messages attribute="period">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="Year"/> </dt>
    <dd>
      <mjl:input param="year" type="text" size="4" maxlength="4" value="${year}" classes="NoFutureYear" id='year'/>
      <mjl:messages attribute="year">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.surveillance.AggregatedCaseController.selectAgeGroup.mojo" name="search.button" value="Search" />
  </dl>
</mjl:form>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{AggregatedCaseViewDTO.CLASS}))%>

<script type="text/javascript">
  var validate = function(e, obj){
    var year = document.getElementById('year');
    var period = document.getElementById('period');
    var periodType;

    var radios = document.getElementsByName('periodType');
    for(var i=0; i<radios.length; i++)
    {
      var radio = radios[i];

      if(radio.checked)
      {
        periodType = radio.value;
      }
    }

	var re = /^[0-9]+$/;

	if ( !re.test(year.value) || !re.test(period.value))
	{
	  return;
	}

    if(year.value != '' && period.value != '' && periodType != '')
    {
      var request = new MDSS.Request({
          onSend: function(){},
          onComplete: function(){},
          onSuccess : function(){},
          onProblemExceptionDTO : function(e){
              var problems = e.getProblems();
    		  for each (p in problems)
    		  {
        		if(p.getType() == "dss.vector.solutions.FuturePeriodProblem")
            	{
                	MojoCal.addError(year,p.getLocalizedMessage());
        		}
        		else
        		{
                	MojoCal.addError(period,p.getLocalizedMessage());
        		}
    		  }
    		}
  		});

  	  MojoCal.removeError(year);
	  MojoCal.removeError(period);

      Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView.validateEpiDate(request, periodType, parseInt(period.value), parseInt(year.value));
    }
  }

  var form = document.getElementById('searchAggregatedCase');
  var periodType = form.periodType;

  YAHOO.util.Event.on(periodType, 'click', validate);
  YAHOO.util.Event.on('period', 'blur', validate);
  YAHOO.util.Event.on('year', 'blur', validate);
</script>
