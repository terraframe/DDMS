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
<%@page import="dss.vector.solutions.entomology.assay.KnockDownIntervalDTO"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
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


<mjl:input type="hidden" param="id" id="id" value="${item.id}" />
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="collection">
    <mjl:input id="collectionInput" param="collectionInput" type="text" value="${collection != null ? collection.collectionId : ''}"/>
    <mjl:input id="collection" param="collection" type="hidden" value="${collection != null ? collection.concreteId : ''}" classes="component" />    
  </mjl:dt>
  <mjl:dt attribute="uniqueAssayId">
    <mjl:input type="text" param="uniqueAssayId" classes="component" id="uniqueAssayId" />
  </mjl:dt>
  <mjl:dt attribute="testDate">
    <mjl:input type="text" param="testDate" id="testDate" classes="DatePick NoFuture component" />
  </mjl:dt>
  <mjl:dt attribute="testMethod">
    <mdss:mo param="testMethod" id="testMethod" value="${testMethod}" classes="component"/>
  </mjl:dt>  
  <mjl:dt attribute="generation">
    <mdss:mo param="generation" value="${generation}" id="generation" classes="component"/>
  </mjl:dt>  
  <mjl:dt attribute="isofemale">
    <mjl:boolean param="isofemale" id="isofemale" />
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mdss:mo param="sex" value="${sex}" id="sex"  classes="component"/>
  </mjl:dt>  
  <mjl:dt attribute="specie">
    <mdss:mo param="specie" value="${specie}" id="specie"  classes="component"/>
  </mjl:dt>  
  <mjl:dt attribute="identificationMethod">
    <mdss:mo param="identificationMethod" id="identificationMethod" value="${identificationMethod}"  classes="component"/>
  </mjl:dt>  
  <mjl:dt attribute="ageRange">
    <mjl:struct param="ageRange">
      <mjl:dt attribute="startPoint" type="text" id="startPoint" classes="ageRange"  />
      <mjl:dt attribute="endPoint" type="text" id="endPoint" classes="ageRange" />
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="fed">
    <mjl:input type="text" param="fed" id="fed" classes="component"/>
  </mjl:dt>
  <mjl:dt attribute="gravid">
    <mjl:input type="text" param="gravid" id="gravid" classes="component"/>
  </mjl:dt>
  <mjl:dt attribute="exposureTime">
    <mjl:input type="text" param="exposureTime" id="exposureTime"  classes="component" />
  </mjl:dt>

  <mjl:dt attribute="insecticide">
    <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide"  classes="component" id="insecticide">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
    <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><mdss:localize key="Manage_Insecticides" /></a>
  </mjl:dt>
  <mjl:dt attribute="quantityTested">
    <mjl:input type="text" param="quantityTested" classes="component" id="quantityTested" />
  </mjl:dt>

  <mjl:dt attribute="kd50">
    <fmt:formatNumber minFractionDigits="2" var="formatKd50" value="${item.kd50}" />
    <mjl:input type="text" param="kd50" value="${formatKd50}" classes="component" id="kd50"/>
  </mjl:dt>

  <mjl:dt attribute="kd95">
    <fmt:formatNumber minFractionDigits="2" var="formatKd95" value="${item.kd95}" />
    <mjl:input type="text" param="kd95" value="${formatKd95}" classes="component" id="kd95" />
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