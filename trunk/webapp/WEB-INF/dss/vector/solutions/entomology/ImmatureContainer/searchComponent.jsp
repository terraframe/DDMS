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
<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Enter_Immatures_by_Container_Type"  scope="request"/>

<mjl:form name="ImmatureCollection.search" method="POST" id="searchImmatureCollections">
  <dl>
    <%@ include file="searchForm.jsp"%>

    <mdss:localize key="Search" var="Localized_Search" />

    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.ImmatureContainerController.searchByDTO.mojo" name="search.button" value="${Localized_Search}" />
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.ImmatureContainerController.forward.mojo" name="create.button" value="${Localized_Create}" />
  </dl>
</mjl:form>

<h2><mdss:localize key="Results" /></h2>
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
	          <mdss:localize key="View" />
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
  	<span class="nomatch"><mdss:localize key="Search_Found_No_Results"/></span>
  </c:otherwise> 
</c:choose>  
<br />

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ImmatureCollectionExcelView" name="excelType"/>
</jsp:include>