<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>


<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay"%>
<%@page import="dss.vector.solutions.entomology.assay.CollectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeAssayDTO"%>



<mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:input id="collectionInput" param="collectionInput" type="text" value="${item.collection != null ? item.collection.collectionId : ''}"/>
        <mjl:input id="collectionId" param="collection" type="hidden" value="${item.collection != null ? item.collection.id : ''}" />        
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="specie">
        <span class="clickable browserLauncher" id="specieBtn"> <fmt:message key="Browser"/></span>
        <div id="specieDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${specie != null}">
              ${specie.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="specie" id="specie" value="${specie != null ? specie.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <span class="clickable browserLauncher" id="identificationMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="identificationMethodDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${identificationMethod != null}">
              ${identificationMethod.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="identificationMethod" id="identificationMethod" value="${identificationMethod != null ? identificationMethod.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="testMethod">
        <span class="clickable browserLauncher" id="testMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="testMethodDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${testMethod != null}">
              ${testMethod.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="testMethod" id="testMethod" value="${testMethod != null ? testMethod.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="generation">
        <span class="clickable browserLauncher" id="generationBtn"> <fmt:message key="Browser"/></span>
        <div id="generationDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${generation != null}">
              ${generation.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="generation" id="generation" value="${generation != null ? generation.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="isofemale">
        <mjl:boolean param="isofemale" />
      </mjl:dt>
      <mjl:dt attribute="startPoint">
        <span class="clickable browserLauncher" id="startPointBtn"> <fmt:message key="Browser"/></span>
        <div id="startPointDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${startPoint != null}">
              ${startPoint.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="startPoint" id="startPoint" value="${startPoint != null ? startPoint.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="endPoint">
        <span class="clickable browserLauncher" id="endPointBtn"> <fmt:message key="Browser"/></span>
        <div id="endPointDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${endPoint != null}">
              ${endPoint.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="endPoint" id="endPoint" value="${endPoint != null ? endPoint.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="intervalTime">
        <mjl:input type="text" param="intervalTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        <mjl:input type="text" param="controlTestMortality" />
      </mjl:dt>
      <mjl:dt attribute="lt50">
        <mjl:input type="text" param="lt50" />
      </mjl:dt>
      <mjl:dt attribute="lt95">
        <mjl:input type="text" param="lt95" />
      </mjl:dt>      
    </mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{AbstractMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{ConcreteMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{GeoEntityViewDTO.CLASS}))%>


<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'testMethod', className:'<%=CollectionAssayDTO.CLASS%>'},
         {attributeName:'generation', className:'<%=CollectionAssayDTO.CLASS%>'},
         {attributeName:'specie', className:'<%=AbstractAssayDTO.CLASS%>'},
         {attributeName:'identificationMethod', className:'<%=CollectionAssayDTO.CLASS%>'},
         {attributeName:'startPoint', className:'<%=LarvaeAssayDTO.CLASS%>'},
         {attributeName:'endPoint', className:'<%=LarvaeAssayDTO.CLASS%>'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=LarvaeDiscriminatingDoseAssay.CLASS%>", attributes);

    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>    
