<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Threshold_Data"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="PopulationData.search.mojo" method="POST">
  <dl>
    
    <dt>
      <label>${item.thresholdTypeMd.displayLabel}</label>
    </dt>
    <dd>      
      <mjl:boolean param="thresholdType" trueLabel="${item.thresholdTypeMd.positiveDisplayLabel}" falseLabel="${item.thresholdTypeMd.negativeDisplayLabel}" value="${item.thresholdType}"/>
    </dd>
    <dt>
      <label>${item.geoEntityMd.displayLabel}</label>
    </dt>
    <dd>
      <mdss:geo param="geoId" populated="true" political="true" concrete="false" value="${item.geoEntity}" />    
    </dd>
    <dt>
      <label>${item.seasonMd.displayLabel}</label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonName}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.searchForThresholdData.mojo" name="search" value="Search" id="search"/>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ThresholdDataExcelView" name="excelType"/>
</jsp:include>