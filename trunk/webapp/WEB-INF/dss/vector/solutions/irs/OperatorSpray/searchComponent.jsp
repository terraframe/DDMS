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
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.TeamMemberViewDTO"%>


<%@page import="dss.vector.solutions.PersonViewDTO"%><c:set var="page_title" value="Search_for_an_Operator_Spray" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:form name="search" method="POST" id ="searchOperatorSpray">
  <dl>
    <dt>
      <label><fmt:message key="Geo_Entity"/></label>
    </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" value="${geoId}" political="false" populated="false" spray="true" />
    </dd>
    <dt>
      <label><fmt:message key="Insecticide"/></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.productName.termDisplayLabel.value}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label><fmt:message key="Spray_Date"/></label>
    </dt>
    <dd>
      <mjl:input param="date" type="text" classes="DatePick NoFuture" id="sprayDate" value="${date}" />
    </dd>
    <dt>
      <label><fmt:message key="Spray_Method"/></label>
    </dt>
    <dd>
      <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:groupOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:groupOption>
      </mjl:group>
    </dd>
    <dt>
      <label><fmt:message key="Team" /></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${teams}" param="teamId" id="teamSelect" includeBlank="true">
        <mjl:option selected="${teamId != null && teamId == current.id ? 'selected' : 'false'}">
          ${current}
         </mjl:option>
      </mjl:select>
    </dd> 
    
    <mjl:component item="${item}" param="#none">
      <mjl:dt attribute="teamOperator">
        <mjl:select var="current" valueAttribute="actorId" items="${operators}" id="#teamOperator" param="#teamOperator" includeBlank="true" >
          <mjl:option selected="${operator != null && current.actorId == operator.id ? 'selected' : 'false'}">
            ${current.memberId} - ${current.lastName}, ${current.firstName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="floatingOperator">
        <mjl:input type="text" param="#floatingOperator" id="#floatingOperator"/>
      </mjl:dt>
      <mjl:dt attribute="assignedOperator">
        <mjl:input type="text" param="#assignedOperator" id="#assignedOperator"/>
      </mjl:dt>
    </mjl:component>
    
    <dt>
      <label><fmt:message key="Spray_Operator"/></label>
    </dt>
    <dd>
      <mjl:input type="text" disabled="true" param="#operatorLabel" id="#operatorLabel" value="${operator != null ? operator.label : ''}"/>
      <mjl:input type="hidden" param="operator.componentId" id="operator.componentId" value="${operator != null ? operator.actorId : ''}"/>    
    </dd>    
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.OperatorSprayController.searchByParameters.mojo" name="search.button" value="Search" />
  </dl>
</mjl:form>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.OperatorSprayExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS, TeamMemberViewDTO.CLASS, PersonViewDTO.CLASS}))%>


<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){       
    var teamSelect = document.getElementById('teamSelect');
    var operatorSelect = document.getElementById('#teamOperator');
    var geoId = document.getElementById('geoId');
    
    var operatorLabelEl = document.getElementById('#operatorLabel');
    var operatorIdEl = document.getElementById('operator.componentId');

    Mojo.GLOBAL.onValidGeoEntitySelected = function() {
      search.populateSprayTeams();
    }

    var teamOperatorHandler = function()
    {
      var index = operatorSelect.selectedIndex;
      var operatorId = operatorSelect.children[index].value;
      var operatorLabel = operatorSelect.children[index].text;

      operatorIdEl.value = operatorId;
      operatorLabelEl.value = operatorLabel;

      document.getElementById('#assignedOperator').value = '';
      document.getElementById('#floatingOperator').value = '';      
    }
    
    new MDSS.UnassignedOperatorsSearch({search:'#floatingOperator', concrete:'operator.componentId', label:'#operatorLabel', teamOps:'#teamOperator', unassigned:'#assignedOperator'});
    new MDSS.AssignedOperatorsSearch({search:'#assignedOperator', concrete:'operator.componentId', label:'#operatorLabel',  team:'teamSelect', teamOps:'#teamOperator', unassigned:'#floatingOperator'});

    var search = new MDSS.TeamSearch(geoId, teamSelect, operatorSelect, null);

    YAHOO.util.Event.addListener(operatorSelect, "change", teamOperatorHandler);          
  });
})();               
</script>


<div id="cal1Container" class="yui-skin-sam"></div>