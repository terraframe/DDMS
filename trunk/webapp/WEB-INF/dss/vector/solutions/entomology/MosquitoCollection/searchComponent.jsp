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
    
    <c:if test="${canDeleteAll}">
      <button class="submitButton" id="delete.all.button">
        <mdss:localize key="Delete_All"/>
      </button>      
    </c:if>
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

<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function(){
  var buttonEl = document.getElementById("delete.all.button");
  
  if(buttonEl != null)
  {
    var onclick = function(e){
      
      if(e != null)
      {
        e.preventDefault();        
      }
      
      // Ensure the user wants to delete all of the objects
      if(confirm(MDSS.localize('confirm_delete_all')))
      {
        var evt = document.createEvent("HTMLEvents");
        evt.initEvent("submit", false, true);
        
        var formEl = document.getElementById("searchMosquitoCollections");
        formEl.action = "dss.vector.solutions.entomology.MosquitoCollectionController.deleteAllCollections.mojo";
        formEl.dispatchEvent(evt);
      }
    };
    
    YAHOO.util.Event.on(buttonEl, 'click', onclick);
  }
});
</script>