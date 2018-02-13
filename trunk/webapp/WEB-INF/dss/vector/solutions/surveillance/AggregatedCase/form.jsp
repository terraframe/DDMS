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