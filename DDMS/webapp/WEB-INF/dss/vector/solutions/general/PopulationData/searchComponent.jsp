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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Population_Data"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="PopulationData.search.mojo" method="POST">
  <dl>
    <dt>
      <label>${item.populationTypeMd.displayLabel}</label>
    </dt>
    <dd>      
      <mjl:boolean param="populationType" id="populationType" trueLabel="${item.populationTypeMd.positiveDisplayLabel}" falseLabel="${item.populationTypeMd.negativeDisplayLabel}" value="${item.populationType}"/>
    </dd>
  
    <dt>
      <label>${item.geoEntityMd.displayLabel}</label>
    </dt>
    <dd>
      <mdss:geo param="geoId" populated="true" political="true" concrete="false" actions="${actions}" />    
    </dd>
    <dt>
      <label>${item.yearOfDataMd.displayLabel}</label>
    </dt>
    <dd>
      <mjl:input type="text" maxlength="4" size="4" param="yearOfData" id="year"/>
    </dd>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.PopulationDataController.searchForPopulationData.mojo" name="search" value="${Localized_Search}" id="search" />
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PopulationDataExcelView" name="excelType"/>
</jsp:include>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    MDSS.GenericSearch.createYearSearch('year');
  })
})();  
</script>
