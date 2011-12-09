<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Larvae_Diagnostic_Dose_Assay"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
          <mjl:property name="id" value="${collection.concreteId}"/>
          ${collection.collectionId}
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        ${item.controlTestMortality}
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
      <mjl:dt attribute="startPoint">
        <c:if test="${startPoint != null}">
          ${startPoint.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="endPoint">
        <c:if test="${endPoint != null}">
          ${endPoint.displayLabel}
        </c:if>
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
      <mjl:dt attribute="mortality">
        <fmt:formatNumber minFractionDigits="2">${item.mortality}</fmt:formatNumber>
      </mjl:dt>
      <mjl:dt attribute="lt50">
        <fmt:formatNumber minFractionDigits="2">${item.lt50}</fmt:formatNumber>
      </mjl:dt>
      <mjl:dt attribute="lt95">
        <fmt:formatNumber minFractionDigits="2">${item.lt95}</fmt:formatNumber>
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.edit.mojo"
      name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.edit.button" classes="submitButton" />
  </dl>
</mjl:form>

<ul>
  <li> 
    <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
      <mjl:property name="id" value="${item.collection.id}"/>
      <mdss:localize key="Return_to_Collection"/>
    </mjl:commandLink>
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" name="newWiththisCollection">
      <mjl:property value="${item.collection.id}" name="collection_id" />
      <mdss:localize key="Create_Another_Larvae_Diagnostic_Assay_With_This_Collection"/>
    </mjl:commandLink>   
  </li>
  <li> 
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo" name="viewAll.link" >
      <mdss:localize key="View_All_LDA"/>
    </mjl:commandLink>
  </li>
</ul>