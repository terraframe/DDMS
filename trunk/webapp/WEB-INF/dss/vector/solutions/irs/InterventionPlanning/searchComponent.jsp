<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Intervention_Planning"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>

<mjl:form name="InterventionPlanning.search.mojo" method="POST">
  <dl>
    <dt>
      <label><mdss:localize key="Planning_Type"/></label>
    </dt>
    <dd>
      <input type="radio" name="planningType" class="planningOption" id="planningType.time" ${time}/> <mdss:localize key="Time"/>
      <input type="radio" name="planningType" class="planningOption" id="planningType.insecticide" ${insecticide} /> <mdss:localize key="Insecticide"/>
      <input type="radio" name="planningType" class="planningOption" id="planningType.operator" ${operator}/> <mdss:localize key="Operator_Planning"/>
    </dd>
    <dt>
      <label><mdss:localize key="Geo_Entity"/></label>
    </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" political="false" populated="false" spray="true" />
    </dd>
    <dt>
      <label> <mdss:localize key="Season"/></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonName}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <dd>
      <div class="time">
        <mjl:command classes="submitButton" action="dss.vector.solutions.irs.InterventionPlanningController.searchForTimePlanning.mojo" name="search.time" value="Search" id="time"/>
      </div>
      <div class="insecticide">
        <mjl:command classes="submitButton Insecticide" action="dss.vector.solutions.irs.InterventionPlanningController.searchForInsceticidePlanning.mojo" name="search.insecticide" value="Search" id="insecticide"/>
      </div>
      <div class="operator">
        <mjl:command classes="submitButton Operator" action="dss.vector.solutions.irs.InterventionPlanningController.searchForOperatorPlanning.mojo" name="search.operator" value="Search" id="operator"/>
      </div>
    </dd>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    var options = YAHOO.util.Selector.query('.planningOption');

    var time = new MDSS.HiddenInputElement({element:'time', clearValue:false});
    var insecticide = new MDSS.HiddenInputElement({element:'insecticide', clearValue:false});
    var operator = new MDSS.HiddenInputElement({element:'operator', clearValue:false});

    MDSS.ElementHandler.setupBooleanHandler('planningType.time', options, [time]);
    MDSS.ElementHandler.setupBooleanHandler('planningType.insecticide', options, [insecticide]);
    MDSS.ElementHandler.setupBooleanHandler('planningType.operator', options, [operator]);
  });
})();

</script>