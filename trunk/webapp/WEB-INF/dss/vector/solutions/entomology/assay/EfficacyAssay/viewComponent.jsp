<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Efficacy_bioassay" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="uniqueAssayId">
        ${item.uniqueAssayId}
      </mjl:dt>
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
      <mjl:dt attribute="surfaceType">
        <c:if test="${item.surfaceType != null}">
          ${item.surfaceType.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span id="testDateSpan" class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        ${item.controlTestMortality}
      </mjl:dt>      
      <mjl:dt attribute="testMethod">
        <c:if test="${testMethod != null}">
          ${testMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <c:if test="${specie != null}">
          ${specie.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="colonyName">
        ${item.colonyName}
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
      <mjl:dt attribute="sex">
        <c:if test="${sex != null}">
          ${sex.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="gravid">
        ${item.gravid}
      </mjl:dt>
      <mjl:dt attribute="fed">
        ${item.fed}
      </mjl:dt>
      <mjl:dt attribute="insecticideBrand">
        ${item.insecticideBrand.productName.termDisplayLabel.value}
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface">
        ${item.timeOnSurface}
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">      
        <c:if test="${surfacePostion != null}">
          ${surfacePostion.displayLabel}
        </c:if>
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
        <fmt:formatNumber minFractionDigits="2">${item.mortality}</fmt:formatNumber>
      </mjl:dt>
      
      <dt><label> <mdss:localize key="Overall_Mortality_for_surface" /> </label></dt>
      <dd><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.overallMortalityRate}" /></dd>
    </mjl:component>

    <mdss:localize key="Edit" var="Localized_Edit" />

    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.edit.button" />
    <mdss:localize key="Copy_Assay" var="Localized_Copy_Assay" />
    <mjl:command value="${Localized_Copy_Assay}" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.cloneAssay.mojo" name="form.cloneAssay.button" />
  </dl>
</mjl:form>


<mjl:commandLink action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.viewAll.link" >
  <mdss:localize key="View_All" />
</mjl:commandLink>
