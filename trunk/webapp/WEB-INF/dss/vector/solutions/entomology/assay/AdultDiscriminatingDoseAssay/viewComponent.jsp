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
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:set var="page_title" value="View_ADDA"  scope="request"/>

<%

DataGrid grid = (DataGrid) request.getAttribute("grid");

%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
          <mjl:property name="id" value="${collection.concreteId}"/>
          ${collection.collectionId}
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="uniqueAssayId">
        ${item.uniqueAssayId}
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <c:if test="${testMethod != null}">
          ${testMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="generation">
        <c:if test="${generation != null}">
          ${generation.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        ${item.isofemale}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <c:if test="${sex != null}">
          ${sex.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <c:if test="${specie != null}">
          ${specie.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <c:if test="${identificationMethod != null}">
          ${identificationMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint">
            ${item.ageRange.startPoint}
          </mjl:dt>
          <mjl:dt attribute="endPoint">
            ${item.ageRange.endPoint}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="fed">
        ${item.fed}
      </mjl:dt>
      <mjl:dt attribute="gravid">
        ${item.gravid}
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        ${item.exposureTime}
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        ${item.holdingTime}
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        ${item.insecticide.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        ${item.quantityTested}
      </mjl:dt>
      <mjl:dt attribute="quantityLive">
        ${item.quantityLive}
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        ${item.quantityDead}
      </mjl:dt>
      
      <mjl:dt attribute="observedMortality">        
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${item.observedMortality}</fmt:formatNumber>              
      </mjl:dt>
      
      <mjl:dt attribute="controlTestMortality">
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${item.controlTestMortality}</fmt:formatNumber>              
      </mjl:dt>
            
      <mjl:dt attribute="correctedQuantityDead">
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${item.correctedQuantityDead}</fmt:formatNumber>              
      </mjl:dt>
      
      <mjl:dt attribute="controlTestNumberDead">
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${item.controlTestNumberDead}</fmt:formatNumber>              
      </mjl:dt>
      
      <mjl:dt attribute="mortality">
        <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${item.mortality}</fmt:formatNumber>              
      </mjl:dt>
      
      <mjl:dt attribute="kd50">
        <fmt:formatNumber minFractionDigits="2">${item.kd50}</fmt:formatNumber>
      </mjl:dt>
      <mjl:dt attribute="kd95">
        <fmt:formatNumber minFractionDigits="2">${item.kd95}</fmt:formatNumber>
      </mjl:dt>      
    </mjl:component>
    <dd>
      <div id="intervals"></div>
    </dd>
    <dd>
      <mdss:localize key="Edit" var="Localized_Edit" />
      <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.edit.button" classes="submitButton" />
      
      <mdss:localize key="Create_replicate" var="Create_replicate" />
      <mjl:command value="${Create_replicate}" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.cloneAssay.mojo" name="clone.button" classes="submitButton" />
    </dd>
    <dd>
    </dd>
  </dl>
</mjl:form>

<ul>
  <li>
    <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
      <mjl:property name="id" value="${collection.concreteId}"/>
      <mdss:localize key="Return_to_Collection"/>
    </mjl:commandLink>
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo" name="newWiththisCollection">
      <mjl:property value="${collection.concreteId}" name="collection_id" />
      <mdss:localize key="Create_Another_Adult_Diagnostic_Assay_With_This_Collection"/>
    </mjl:commandLink>
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo" name="viewAll.link" >
      <mdss:localize key="View_All_ADA"/>
    </mjl:commandLink>
  </li>
</ul>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    // SETUP THE INTERVAL GRID
    <%=grid.getJavascript()%>        
  })
})();       
</script>