<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
      <mjl:dt attribute="collection">
        <mjl:select var="current" valueAttribute="id" items="${collection}" param="collection">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie">
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
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.update.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.delete.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.cancel.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.cancel.button" />
</mjl:form>