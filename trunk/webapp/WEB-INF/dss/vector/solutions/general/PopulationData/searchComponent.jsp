<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
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
      <label><fmt:message key="Geo_Id"/></label>
    </dt>
    <dd>
      <mdss:geo param="geoId" populated="true" political="true" concrete="false" />    
    </dd>
    <dt>
      <label> <fmt:message key="Year"/></label>
    </dt>
    <dd>
      <mjl:input type="text" maxlength="4" size="4" param="yearOfData"/>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.PopulationDataController.searchForPopulationData.mojo" name="search" value="Search" id="search"/>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PopulationDataExcelView" name="excelType"/>
</jsp:include>
