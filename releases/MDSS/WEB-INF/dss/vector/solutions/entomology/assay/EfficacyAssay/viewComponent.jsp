<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Efficacy_Bioassay" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span id="testDateSpan" class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        ${item.testMethod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="specie">
        ${item.specie.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="colonyName">
        ${item.colonyName}
      </mjl:dt>
      <mjl:dt attribute="ageRange">      
        <mjl:struct param="ageRange">        
        <dl>
          <mjl:dt attribute="startPoint">
            ${item.ageRange.startPoint}
          </mjl:dt>
          <mjl:dt attribute="endPoint">
            ${item.ageRange.endPoint}
          </mjl:dt>
        </dl>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <ul>
          <c:forEach var="enumName" items="${item.sexEnumNames}">
            <li>${item.sexMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="gravid">
        ${item.gravid}
      </mjl:dt>
      <mjl:dt attribute="fed">
        ${item.fed}
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        ${item.insecticide.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface">
        ${item.timeOnSurface}
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">      
        <ul>
          <c:forEach var="enumName" items="${item.surfacePostionEnumNames}">
            <li>${item.surfacePostionMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        ${item.exposureTime}
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        ${item.holdingTime}
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
      <mjl:dt attribute="mortality">
        ${item.mortality}
      </mjl:dt>
      
      <dt><label> <fmt:message key="Overall_Mortality_for_surface" /> </label></dt>
      <dd><fmt:formatNumber type="number" maxFractionDigits="2" value="${item.overallMortalityRate}" /></dd>
    </mjl:component>

    <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.edit.button" />
    <mjl:command value="Copy_Assay" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.cloneAssay.mojo" name="form.cloneAssay.button" />
  </dl>
</mjl:form>


<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.viewAll.link" />
