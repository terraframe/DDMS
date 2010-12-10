<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.CollectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="collection">
    <mjl:input id="collectionInput" param="collectionInput" type="text" value="${collection != null ? collection.collectionId : ''}"/>
    <mjl:input id="collectionId" param="collection" type="hidden" value="${collection != null ? collection.concreteId : ''}" />    
  </mjl:dt>
  <mjl:dt attribute="testDate">
    <mjl:input type="text" param="testDate" id="testDate" classes="DatePick NoFuture" />
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
  <mjl:dt attribute="sex">
    <mdss:mo param="sex" value="${sex}"/>
  </mjl:dt>  
  <mjl:dt attribute="specie">
    <mdss:mo param="specie" value="${specie}"/>
  </mjl:dt>  
  <mjl:dt attribute="identificationMethod">
    <mdss:mo param="identificationMethod" value="${identificationMethod}"/>
  </mjl:dt>  
  <mjl:dt attribute="ageRange">
    <mjl:struct param="ageRange">
      <mjl:dt attribute="startPoint" type="text"  />
      <mjl:dt attribute="endPoint" type="text"  />
    </mjl:struct>
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

  <mjl:dt attribute="kd50">
    <mjl:input type="text" param="kd50" />
  </mjl:dt>

  <mjl:dt attribute="kd95">
    <mjl:input type="text" param="kd95" />
  </mjl:dt>

  <div id="intervalsDiv">
    <table class="displayTable">
      <tr>
        <th><fmt:message key="interval_time"/></th>
        <th><fmt:message key="knock_down"/></th>
      </tr>
      <c:if test="${item.interval10Readable}">
        <tr class="oddRow" id="interval10">
          <td>${item.interval10Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval10" /></td>
        </tr>
      </c:if>
      <c:if test="${item.interval20Readable}">
        <tr class="oddRow" id="interval20">
          <td>${item.interval20Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval20" /></td>
        </tr>
      </c:if>
      <c:if test="${item.interval30Readable}">
        <tr class="oddRow" id="interval30">
          <td>${item.interval30Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval30" /></td>
        </tr>
      </c:if>
      <c:if test="${item.interval40Readable}">
        <tr class="oddRow" id="interval40">
          <td>${item.interval40Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval40" /></td>
        </tr>
      </c:if>
      <c:if test="${item.interval50Readable}">
        <tr class="oddRow" id="interval50">
          <td>${item.interval50Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval50" /></td>
        </tr>
      </c:if>
      <c:if test="${item.interval60Readable}">
        <tr class="oddRow" id="interval60">
          <td>${item.interval60Md.displayLabel}</td>
          <td><mjl:input type="text" param="interval60" /></td>
        </tr>
      </c:if>
    </table>    
  </div>  
</mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>
