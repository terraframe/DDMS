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
