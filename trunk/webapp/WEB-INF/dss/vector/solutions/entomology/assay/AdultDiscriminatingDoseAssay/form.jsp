<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay"%>
<%@page import="dss.vector.solutions.entomology.assay.CollectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>

<mjl:input type="hidden" param="id" id="id" value="${item.id}" />
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="collection">
    <mjl:input id="collectionInput" type="text" value="${collection != null ? collection.collectionId : ''}"/>
    <mjl:input id="collection" param="collection" type="hidden" value="${collection != null ? collection.concreteId : ''}"  classes="component" />    
  </mjl:dt>
  <mjl:dt attribute="testDate">
    <mjl:input type="text" param="testDate" id="testDate" classes="DatePick NoFuture component" />
  </mjl:dt>
  <mjl:dt attribute="testMethod">
    <mdss:mo param="testMethod" value="${testMethod}"  classes="component" id="testMethod"/>
  </mjl:dt>  
  <mjl:dt attribute="generation">
    <mdss:mo param="generation" value="${generation}" classes="component" id="generation"/>
  </mjl:dt>  
  <mjl:dt attribute="isofemale">
    <mjl:boolean param="isofemale" id="isofemale" />
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mdss:mo param="sex" value="${sex}" classes="component" id="sex"/>
  </mjl:dt>  
  <mjl:dt attribute="specie">
    <mdss:mo param="specie" value="${specie}" classes="component" id="specie"/>
  </mjl:dt>  
  <mjl:dt attribute="identificationMethod">
    <mdss:mo param="identificationMethod" value="${identificationMethod}" classes="component" id="identificationMethod"/>
  </mjl:dt>  
  <mjl:dt attribute="ageRange">
    <mjl:struct param="ageRange">
      <mjl:dt attribute="startPoint" type="text" id="startPoint"  />
      <mjl:dt attribute="endPoint" type="text" id="endPoint"  />
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="fed">
    <mjl:input type="text" param="fed"  classes="component" id="fed"/>
  </mjl:dt>
  <mjl:dt attribute="gravid">
    <mjl:input type="text" param="gravid" classes="component" id="gravid" />
  </mjl:dt>
  <mjl:dt attribute="exposureTime">
    <mjl:input type="text" param="exposureTime" classes="component" id="exposureTime" />
  </mjl:dt>
  <mjl:dt attribute="holdingTime">
    <mjl:input type="text" param="holdingTime" classes="component" id="holdingTime" />
  </mjl:dt>
  <mjl:dt attribute="insecticide">
    <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide" classes="component" id="insecticide">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
    <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><mdss:localize key="Manage_Insecticides" /></a>
  </mjl:dt>
  <mjl:dt attribute="quantityTested">
    <mjl:input type="text" param="quantityTested" classes="component" id="quantityTested" />
  </mjl:dt>

  <mjl:dt attribute="quantityDead">
    <mjl:input type="text" param="quantityDead" classes="component" id="quantityDead" />
  </mjl:dt>
  <mjl:dt attribute="controlTestMortality">
    <fmt:formatNumber minFractionDigits="2" var="formatControlTestMortality" value="${item.controlTestMortality}" />
    <mjl:input type="text" param="controlTestMortality" value="${formatControlTestMortality}" classes="component" id="controlTestMortality" />
  </mjl:dt>

  <mjl:dt attribute="kd50">
    <fmt:formatNumber minFractionDigits="2" var="formatKd50" value="${item.kd50}" />
    <mjl:input type="text" param="kd50" value="${formatKd50}" id="kd50" classes="component"/>
  </mjl:dt>

  <mjl:dt attribute="kd95">
    <fmt:formatNumber minFractionDigits="2" var="formatKd95" value="${item.kd95}" />
    <mjl:input type="text" param="kd95" value="${formatKd95}" id="kd95" classes="component" />
  </mjl:dt>
</mjl:component>
<dd>
  <div id="intervals"></div>
</dd>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    MDSS.collectionSearch({search:'collectionInput', concrete:'collection', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>    