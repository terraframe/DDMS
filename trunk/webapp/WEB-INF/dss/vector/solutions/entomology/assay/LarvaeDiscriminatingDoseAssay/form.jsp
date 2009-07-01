<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO"%><mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:input id="collectionInput" param="collectionInput" type="text" value="${item.collection != null ? item.collection.collectionId : ''}"/>
        <mjl:input id="collectionId" param="collection" type="hidden" value="${item.collection != null ? item.collection.id : ''}" />        
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie" includeBlank="true">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <mjl:select var="current" valueAttribute="id" items="${identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="generation">
        <mjl:select var="current" valueAttribute="id" items="${generation}" param="generation">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        <mjl:boolean param="isofemale" />
      </mjl:dt>
      <mjl:dt attribute="startPoint">
        <mjl:select var="current" valueAttribute="id" items="${ageRange}" param="startPoint">
          <mjl:option>
              ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="endPoint">
        <mjl:select var="current" valueAttribute="id" items="${ageRange}" param="endPoint">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
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
    </mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{AbstractMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{ConcreteMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionDTO.CLASS}))%>

<script type="text/javascript" defer="defer">  
  MDSS.collectionSearch('<%=MosquitoCollectionDTO.CLASS%>');
</script>    