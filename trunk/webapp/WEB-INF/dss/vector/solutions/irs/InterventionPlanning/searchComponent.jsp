<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Intervention_Planning"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>

<mjl:form name="InterventionPlanning.search.mojo" method="POST">
  <dl>
    <dt>
      <label><fmt:message key="Planning_Type"/></label>
    </dt>
    <dd>
      <input type="radio" name="planningType" class="planningOption" id="planningType.time" ${time}/> <fmt:message key="Time"/>
      <input type="radio" name="planningType" class="planningOption" id="planningType.insecticide" ${insecticide} /> <fmt:message key="Insecticide"/>
      <input type="radio" name="planningType" class="planningOption" id="planningType.operator" ${operator}/> <fmt:message key="Operator_Planning"/>
    </dd>
    <dt>
      <label><fmt:message key="Geo_Id"/></label>
    </dt>
    <dd>
      <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/>
    </dd>
    <dt>
      <label> <fmt:message key="Season"/></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonName}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton Time" action="dss.vector.solutions.irs.InterventionPlanningController.searchForTimePlanning.mojo" name="search.time" value="Search" id="search.time"/>
    <mjl:command classes="submitButton Insecticide" action="dss.vector.solutions.irs.InterventionPlanningController.searchForInsceticidePlanning.mojo" name="search.insecticide" value="Search" id="search.insecticide"/>
    <mjl:command classes="submitButton Operator" action="dss.vector.solutions.irs.InterventionPlanningController.searchForOperatorPlanning.mojo" name="search.operator" value="Search" id="search.operator"/>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
	  var options = YAHOO.util.Selector.query('.planningOption');

    MDSS.ElementHandler.setupBooleanHandler('planningType.time', options, 'Time', false);
    MDSS.ElementHandler.setupBooleanHandler('planningType.insecticide', options, 'Insecticide', false);
    MDSS.ElementHandler.setupBooleanHandler('planningType.operator', options, 'Operator', false);
  });
})();

</script>