<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.form.id" method="POST">
<div class="fldContainer">
    <div class="fcTop">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
      <dt>
      <label>
        ${item.testDateMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.testDate}
    </dd>
     <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.keyName}" action="csu.mrc.ivcc.mdss.mo.ResistanceMethodologyController.view.mojo" name="csu.mrc.ivcc.mdss.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="csu.mrc.ivcc.mdss.mo.SpecieController.view.mojo" name="csu.mrc.ivcc.mdss.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.colonyNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.colonyName}
    </dd>
     <dt>
      <label>
        ${item.ageRangeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
       <dt>
          <label>
            ${item.ageRange.startPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.startPoint}
        </dd>
        <dt>
          <label>
            ${item.ageRange.endPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.endPoint}
        </dd>
      </dl>
    </dd>
     <dt>
      <label>
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          <li>
            ${item.sexMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.gravidMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.gravid}
    </dd>
    <dt>
      <label>
        ${item.fedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fed}
    </dd>
     <dt>
      <label>
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.keyName}" action="csu.mrc.ivcc.mdss.mo.InsecticideController.view.mojo" name="csu.mrc.ivcc.mdss.mo.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
    </dd>
     <dt>
      <label>
        ${item.expousureTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exposureTime}
    </dd>
     <dt>
      <label>
        ${item.surfacePostionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.surfacePostionEnumNames}">
          <li>
            ${item.surfacePostionMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.holdingTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.holdingTime}
    </dd>
    <dt>
      <label>
        ${item.exposureTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exposureTime}
    </dd>
   <dt>
      <label>
        ${item.quantityTestedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityTested}
    </dd>
    <dt>
      <label>
        ${item.quantityLiveMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityLive}
    </dd>
    <dt>
      <label>
        ${item.quantityDeadMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityDead}
    </dd>
    <dt>
      <label>
        ${item.mortalityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.mortality}
    </dd>  
  </dl>
     <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
  </div>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.viewAll.link" />
