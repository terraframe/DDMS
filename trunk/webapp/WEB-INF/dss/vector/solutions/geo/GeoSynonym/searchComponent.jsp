<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Search_GeoSynonym"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="GeoSynonym.search" method="POST" id ="searchGeoSynonyms">
  <dl>
    <%@ include file="searchForm.jsp"%>

    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.geo.GeoSynonymController.searchByDTO.mojo" name="search.button" value="${Localized_Search}" />

    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.geo.GeoSynonymController.newInstance.mojo" name="create.button" value="${Localized_Create}" />
  </dl>
</mjl:form>


<h2><mdss:localize key="Results" /></h2>
<c:choose> 
  <c:when test="${query.count > 0}" > 
	<mjl:table var="current" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
	  <mjl:context action="dss.vector.solutions.geo.GeoSynonymController.searchByParameters.mojo">
	    <mjl:property name="geoEntityName" value="${geoEntityName}"/>
	    <mjl:property name="geoId" value="${geoId}"/>
	    <mjl:property name="geoTypeDisplayLabel" value="${geoTypeDisplayLabel}"/>
	    <mjl:property name="synonymNames" value="${synonymNames}"/>
	  </mjl:context>
	  <mjl:columns>
	    <mjl:attributeColumn attributeName="geoEntityName">
	      <mjl:row>
	        ${current.geoEntityName}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="geoEntity">
        <mjl:row>
          ${current.geoEntity.geoId}
        </mjl:row>
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="geoTypeDisplayLabel">
        <mjl:row>
          ${current.geoTypeDisplayLabel}
        </mjl:row>
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="synonymNames">
        <mjl:row>
          ${current.synonymNames}
        </mjl:row>
      </mjl:attributeColumn>
	    <mjl:freeColumn>
	      <mjl:header>
	
	      </mjl:header>
	      <mjl:row>
	        <mjl:commandLink action="dss.vector.solutions.geo.GeoSynonymController.view.mojo" name="view.link">
	          <mdss:localize key="View" />
	          <mjl:property value="${current.geoEntity.id}" name="id" />
	        </mjl:commandLink>
	      </mjl:row>
	      <mjl:footer>
	      </mjl:footer>
	    </mjl:freeColumn>
	  </mjl:columns>
	  <mjl:pagination>
	    <mjl:page />
	  </mjl:pagination>  
	</mjl:table>
  </c:when> 
  <c:otherwise>
  	<span class="nomatch"><mdss:localize key="Search_Found_No_Results"/></span>
  </c:otherwise> 
</c:choose>  
<br />


<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.geo.GeoSynonymArrayExcelView" name="excelType"/>
</jsp:include>
