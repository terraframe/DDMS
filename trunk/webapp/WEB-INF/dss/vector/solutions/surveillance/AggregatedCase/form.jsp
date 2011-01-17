<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<dl>
  <dt> <label> <mdss:localize key="Change_age_group"/> </label> </dt>
  <dd>
    <mjl:form name="search.form.name" id="search.form" method="POST">
      <mjl:component item="${search}" param="view">
        <mjl:input type="hidden" param="searchType" value="${search.searchType}" />
        <mjl:input type="hidden" param="geoEntity" value="${search.geoEntity.id}" />
        <mjl:input type="hidden" param="period" value="${search.period}" />
        <mjl:input type="hidden" param="periodType" value="${periodType}"/>
        <mjl:input type="hidden" param="periodYear" value="${search.periodYear}"/>
        <mjl:input type="hidden" param="startDate" value="${search.startDate}" classes="DatePick"/>
        <mjl:input type="hidden" param="endDate" value="${search.endDate}" classes="DatePick"/>
        <mjl:dt attribute="ageGroup">
          <mjl:select var="current" valueAttribute="id" items="${ageGroups}"  param="ageGroup" id="search.select.id">
            <mjl:option selected="${current.id == search.ageGroup.id ? 'selected' : 'false'}">
              ${current.displayLabel}
            </mjl:option>
          </mjl:select>
        </mjl:dt>
      </mjl:component>
    </mjl:form>
  </dd>
</dl>

<script type='text/javascript'>
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    jumpBoxForm = document.getElementById('search.form');
    selectBox = document.getElementById('search.select.id'); 

    selectBox.onchange = function(){
      jumpBoxForm.action = 'dss.vector.solutions.surveillance.AggregatedCaseController.searchByView.mojo';
      jumpBoxForm.submit();
    }
  })
})();    
</script>