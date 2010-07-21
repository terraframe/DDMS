<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

<c:set var="page_title" value="Immatures_by_Container_Type"  scope="request"/>

<mjl:form name="ImmatureCollection.search" method="POST" id="searchImmatureCollections">
  <dl>
    <%@ include file="searchForm.jsp"%>

    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.ImmatureContainerController.searchByDTO.mojo" name="search.button" value="Search"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.ImmatureContainerController.forward.mojo" name="create.button" value="Create"/>
  </dl>
</mjl:form>

<h2><fmt:message key="Results"/></h2>
<c:choose> 
  <c:when test="${query.count > 0}" > 
	<mjl:table var="current" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
	  <mjl:context action="dss.vector.solutions.entomology.ImmatureContainerController.searchByParameters.mojo">  
	    <mjl:property name="geoId" value="${item.geoEntity.id}"/>
	    <mjl:property name="startDate" value="${startDate}"/>
	    <mjl:property name="endDate" value="${endDate}"/>
	    <mjl:property name="collectionId" value="${item.collectionId}"/>
	    <mjl:property name="premiseType" value="${item.premiseType.id}"/>
	    <mjl:property name="taxon" value="${item.taxon.id}"/>
	  </mjl:context>
	  <mjl:columns>
	    <mjl:attributeColumn attributeName="geoEntity">
	      <mjl:row>
	        ${current.geoEntity.displayString}
	      </mjl:row>
	    </mjl:attributeColumn>    
	    <mjl:attributeColumn attributeName="startDate">
	      <mjl:row>
	        <fmt:formatDate value="${current.startDate}" pattern="${dateFormatPattern}"  />
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="endDate">
	      <mjl:row>
	        <fmt:formatDate value="${current.endDate}" pattern="${dateFormatPattern}"  />
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="collectionId">
	      <mjl:row>
	        ${current.collectionId}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="premiseType">
	      <mjl:row>
	        ${current.premiseType.displayLabel}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="taxon">
	      <mjl:row>
	        ${current.taxon.displayLabel}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:freeColumn>
	      <mjl:header>
	
	      </mjl:header>
	      <mjl:row>
	        <mjl:commandLink action="dss.vector.solutions.entomology.ImmatureContainerController.view.mojo" name="view.link">
	          <fmt:message key="View" />
	          <mjl:property value="${current.taxonId}" name="id" />
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
  	<span class="nomatch"><fmt:message key="Search_Found_No_Results"/></span>
  </c:otherwise> 
</c:choose>  
<br />

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ImmatureCollectionExcelView" name="excelType"/>
</jsp:include>