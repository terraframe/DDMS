<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Search_Mosquito_Collection"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <dl>
    <%@ include file="searchForm.jsp"%>

    <mdss:localize key="Search" var="Localized_Search" />

    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.searchByDTO.mojo" name="search.button" value="${Localized_Search}" />
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.forward.mojo" name="create.button" value="${Localized_Create}" />
  </dl>
</mjl:form>

<h2><mdss:localize key="Results" /></h2>
<c:choose> 
  <c:when test="${query.count > 0}" > 
	<mjl:table var="current" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
	  <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.searchByParameters.mojo">  
	    <mjl:property name="startDate" value="${startDate}"/>
	    <mjl:property name="endDate" value="${endDate}"/>
	    <mjl:property name="collectionMethod" value="${item.collectionMethod.id}"/>
	    <mjl:property name="geoEntity" value="${item.geoEntity.id}"/>
	    <mjl:property name="collectionId" value="${item.collectionId}"/>
	    <mjl:property name="abundance" value="${item.abundance}"/>
	    <mjl:property name="lifeStage" value="${currentLifeStage}"/>
	  </mjl:context>
	  <mjl:columns>
	    <mjl:attributeColumn attributeName="collectionMethod">
	      <mjl:row>
	        ${current.collectionMethod.displayLabel}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="collectionDate">
	      <mjl:row>
	        <fmt:formatDate value="${current.collectionDate}" pattern="${dateFormatPattern}"  />
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="geoEntity">
	      <mjl:row>
	        ${current.geoEntity.displayString}
	      </mjl:row>
	    </mjl:attributeColumn>    
	    <mjl:attributeColumn attributeName="collectionId">
	      <mjl:row>
	        ${current.collectionId}
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="abundance">
	      <mjl:row>
	        ${current.abundance ? current.abundanceMd.positiveDisplayLabel : current.abundanceMd.negativeDisplayLabel}
	      </mjl:row>    
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="lifeStage">
	      <mjl:row>
	        <ul>
	          <c:forEach items="${current.lifeStageEnumNames}" var="enumName">
	            <li>
	              ${current.lifeStageMd.enumItems[enumName]}
	            </li>
	          </c:forEach>
	        </ul>
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:freeColumn>
	      <mjl:header>
	
	      </mjl:header>
	      <mjl:row>
	        <mjl:commandLink action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="view.link">
	          <mdss:localize key="View" />
	          <mjl:property value="${current.concreteId}" name="id" />
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
  <jsp:param value="dss.vector.solutions.export.MosquitoCollectionExcelView" name="excelType"/>
</jsp:include>