<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="true_label" value='<%=Halp.translateBool(((LarvaeDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),true)%>'/>
<c:set var="false_label" value='<%=Halp.translateBool(((LarvaeDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),false)%>'/>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt><label> ${item.collectionMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${collection}" param="collection">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.testDateMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="testDate" classes="DatePick" id="testDate" /> <mjl:messages attribute="testDate">
        <mjl:message />
      </mjl:messages></dd>
      <dt><label> ${item.specieMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${specie}" param="specie">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.identificationMethodMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${identificationMethod}" param="identificationMethod">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.testMethodMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.generationMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${generation}" param="generation">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.isofemaleMd.displayLabel} </label></dt>
      <dd><mjl:boolean param="isofemale" trueLabel="${true_label}" falseLabel="${false_label}" /></dd>
      <dt><label> ${item.startPointMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${ageRange}" param="startPoint">
        <mjl:option>
              ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.endPointMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${ageRange}" param="endPoint">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select></dd>
      <dt><label> ${item.insecticideMd.displayLabel} </label></dt>
      <dd><mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
        <mjl:option>
            ${current.displayLabel}
          </mjl:option>
      </mjl:select> <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><f:message key="Manage_Insecticides"/></a></dd>
      <dt><label> ${item.exposureTimeMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="exposureTime" /> <mjl:messages attribute="exposureTime">
        <mjl:message />
      </mjl:messages>
      <dt><label> ${item.holdingTimeMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="holdingTime" /> <mjl:messages attribute="holdingTime">
        <mjl:message />
      </mjl:messages></dd>
      <dt><label> ${item.quantityTestedMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="quantityTested" /> <mjl:messages attribute="quantityTested">
        <mjl:message />
      </mjl:messages></dd>
      <dt><label> ${item.quantityDeadMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="quantityDead" /> <mjl:messages attribute="quantityDead">
        <mjl:message />
      </mjl:messages></dd>
      <dt><label> ${item.controlTestMortalityMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="controlTestMortality" /> <mjl:messages attribute="controlTestMortality">
        <mjl:message />
      </mjl:messages></dd>
      <dt><label> ${item.intervalTimeMd.displayLabel} </label></dt>
      <dd><mjl:input type="text" param="intervalTime" /> <mjl:messages attribute="intervalTime">
        <mjl:message />
      </mjl:messages></dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.create.mojo"
    name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.create.button" />
</mjl:form>
