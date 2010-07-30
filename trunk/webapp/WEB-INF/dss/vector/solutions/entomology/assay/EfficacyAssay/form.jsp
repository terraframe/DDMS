<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO"%>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:dt attribute="geoId">
        <mdss:geo param="geoId" value="${geoId}" concrete="false" filter="${surface}" />      
      </mjl:dt>
      <mjl:dt attribute="surfaceType">
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>   
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <mdss:mo param="testMethod" value="${testMethod}"/>
      </mjl:dt>      
      <mjl:dt attribute="specie">
        <mdss:mo param="specie" value="${specie}"/>
      </mjl:dt>      
      <mjl:dt attribute="colonyName">
        <mjl:input type="text" param="colonyName" />
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint" type="text"  />
          <mjl:dt attribute="endPoint" type="text"  />
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mdss:mo param="sex" value="${sex}"/>
      </mjl:dt>      
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="insecticideBrand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="insecticideBrand" >
         <mjl:option>
           ${current.productName.termDisplayLabel.value}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface" >
        <mjl:input type="text" param="timeOnSurface" />
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">
        <mdss:mo param="surfacePostion" value="${surfacePostion}"/>
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
    </mjl:component>
