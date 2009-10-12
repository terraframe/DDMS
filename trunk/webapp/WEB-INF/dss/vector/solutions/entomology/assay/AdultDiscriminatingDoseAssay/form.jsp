<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
  
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionPointDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>


<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay"%>
<%@page import="dss.vector.solutions.entomology.assay.CollectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%><jsp:include page="/WEB-INF/MOSearch.jsp" />


<mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:input id="collectionInput" param="collectionInput" type="text" value="${item.collection != null ? item.collection.collectionId : ''}"/>
        <mjl:input id="collectionId" param="collection" type="hidden" value="${item.collection != null ? item.collection.id : ''}" />        
      </mjl:dt>
      <mjl:dt attribute="testDate" type="text" classes="DatePick NoFuture" />
      <mjl:dt attribute="testMethod">
        <span class="clickable" id="testMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="testMethodDisplay" class="ontologyDisplay">
          <c:if test="${testMethod != null}">
            ${testMethod.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="testMethod" id="testMethod" value="${testMethod != null ? testMethod.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="generation">
        <span class="clickable" id="generationBtn"> <fmt:message key="Browser"/></span>
        <div id="generationDisplay" class="ontologyDisplay">
          <c:if test="${generation != null}">
            ${generation.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="generation" id="generation" value="${generation != null ? generation.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="isofemale">
        <mjl:boolean param="isofemale"  />
      </mjl:dt>
      <mjl:dt attribute="sex">
        <span class="clickable" id="sexBtn"> <fmt:message key="Browser"/></span>
        <div id="sexDisplay" class="ontologyDisplay">
          <c:if test="${sex != null}">
            ${sex.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="sex" id="sex" value="${sex != null ? sex.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="specie">
        <span class="clickable" id="specieBtn"> <fmt:message key="Browser"/></span>
        <div id="specieDisplay" class="ontologyDisplay">
          <c:if test="${specie != null}">
            ${specie.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="specie" id="specie" value="${specie != null ? specie.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="identificationMethod">
        <span class="clickable" id="identificationMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="identificationMethodDisplay" class="ontologyDisplay">
          <c:if test="${identificationMethod != null}">
            ${identificationMethod.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="identificationMethod" id="identificationMethod" value="${identificationMethod != null ? identificationMethod.id : ''}" />
      </mjl:dt>      
     <mjl:dt attribute="ageRange">
      <dl>
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint" type="text"  />
          <mjl:dt attribute="endPoint" type="text"  />
        </mjl:struct>
      </dl>
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
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

      <mjl:dt attribute="intervalTime">
        <mjl:input type="text" param="intervalTime" />
      </mjl:dt>

      <mjl:dt attribute="kd50">
        <mjl:input type="text" param="kd50" />
      </mjl:dt>

      <mjl:dt attribute="kd95">
        <mjl:input type="text" param="kd95" />
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
	       {attributeName:'sex', className:'<%=AdultAssayDTO.CLASS%>'},
	       {attributeName:'specie', className:'<%=AbstractAssayDTO.CLASS%>'},
	       {attributeName:'identificationMethod', className:'<%=CollectionAssayDTO.CLASS%>'}
	  ];
	  
	  new MDSS.GenericOntologyBrowser("<%=AdultDiscriminatingDoseAssay.CLASS%>", attributes);

	  MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
	})
})();

</script>    