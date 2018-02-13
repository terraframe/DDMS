<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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
      <label> * <mdss:localize key="Geo_Entity"/></label>
    </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" political="false" populated="false" spray="true" />
    </dd>
    <dt>
      <label> * <mdss:localize key="Season"/></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonLabel.value}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <dd>
      <div class="time">
        <mdss:localize key="Search" var="Localized_Search" />
        <mjl:command classes="submitButton" action="dss.vector.solutions.irs.InterventionPlanningController.searchForTimePlanning.mojo" name="search.time" value="${Localized_Search}" id="time" />
      </div>
      <div class="insecticide">
        <mjl:command classes="submitButton Insecticide" action="dss.vector.solutions.irs.InterventionPlanningController.searchForInsceticidePlanning.mojo" name="search.insecticide" value="${Localized_Search}" id="insecticide" />
      </div>
      <div class="operator">
        <mjl:command classes="submitButton Operator" action="dss.vector.solutions.irs.InterventionPlanningController.searchForOperatorPlanning.mojo" name="search.operator" value="${Localized_Search}" id="operator" />
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