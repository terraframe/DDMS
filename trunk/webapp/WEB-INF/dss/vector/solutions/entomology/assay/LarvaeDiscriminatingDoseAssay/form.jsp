<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay"%>
<%@page import="dss.vector.solutions.entomology.assay.CollectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="collection">
    <mjl:input id="collectionInput" param="collectionInput" type="text" value="${collection != null ? collection.collectionId : ''}"/>
    <mjl:input id="collectionId" param="collection" type="hidden" value="${collection != null ? collection.concreteId : ''}" />    
  </mjl:dt>
  <mjl:dt attribute="uniqueAssayId">
    <mjl:input type="text" param="uniqueAssayId" id="uniqueAssayId" />
  </mjl:dt>
  <mjl:dt attribute="testDate">
    <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
  </mjl:dt>
  <mjl:dt attribute="specie">
    <mdss:mo param="specie" value="${specie}"/>
  </mjl:dt>
  <mjl:dt attribute="identificationMethod">
    <mdss:mo param="identificationMethod" value="${identificationMethod}"/>
  </mjl:dt>  
  <mjl:dt attribute="testMethod">
    <mdss:mo param="testMethod" value="${testMethod}"/>
  </mjl:dt>  
  <mjl:dt attribute="generation">
    <mdss:mo param="generation" value="${generation}"/>
  </mjl:dt>  
  <mjl:dt attribute="isofemale">
    <mjl:boolean param="isofemale" />
  </mjl:dt>
  <mjl:dt attribute="startPoint">
    <mdss:mo param="startPoint" value="${startPoint}"/>
  </mjl:dt>  
  <mjl:dt attribute="endPoint">
    <mdss:mo param="endPoint" value="${endPoint}"/>
  </mjl:dt>  
  <mjl:dt attribute="insecticide">
    <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
    <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><mdss:localize key="Manage_Insecticides" /></a>
  </mjl:dt>
  <mjl:dt attribute="exposureTime">
    <mjl:input type="text" param="exposureTime" />
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
    <fmt:formatNumber minFractionDigits="2" var="formatControlTestMortality" value="${item.controlTestMortality}" />
    <mjl:input type="text" param="controlTestMortality" value="${formatControlTestMortality}" />
  </mjl:dt>
  <mjl:dt attribute="lt50">
    <fmt:formatNumber minFractionDigits="2" var="formatLt50" value="${item.lt50}" />
    <mjl:input type="text" param="lt50" value="${formatLt50}" />
  </mjl:dt>
  <mjl:dt attribute="lt95">
    <fmt:formatNumber minFractionDigits="2" var="formatLt95" value="${item.lt95}" />
    <mjl:input type="text" param="lt95" value="${formatLt95}" />
  </mjl:dt>
</mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>    
