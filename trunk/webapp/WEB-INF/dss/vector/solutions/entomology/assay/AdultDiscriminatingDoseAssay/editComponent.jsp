<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<c:set var="true_label" value='<%=Halp.translateBool(((AdultDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),true)%>' />
<c:set var="false_label" value='<%=Halp.translateBool(((AdultDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),false)%>' />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set var="page_title" value="Edit ADDA data" scope="request" />
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">


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
        <mjl:input type="text" param="testDate" id="testDate" classes="DatePick" />
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
        <mjl:boolean param="isofemale" trueLabel="${true_label}" falseLabel="${false_label}" />
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <c:choose>
            <c:when test="${mjl:contains(item.sexEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
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
      <dt><label> ${item.ageRangeMd.displayLabel} </label></dt>
      <dd>
      <dl>
        <mjl:struct param="ageRange">
          <dt><label> ${item.ageRange.startPointMd.displayLabel} </label></dt>
          <dd><mjl:input type="text" param="startPoint" /> <mjl:messages attribute="startPoint">
            <mjl:message />
          </mjl:messages></dd>
          <dt><label> ${item.ageRange.endPointMd.displayLabel} </label></dt>
          <dd><mjl:input type="text" param="endPoint" /> <mjl:messages attribute="endPoint">
            <mjl:message />
          </mjl:messages></dd>

        </mjl:struct>
      </dl>
      </dd>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>

      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
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

      <mjl:dt attribute="intervalTime">
        <mjl:input type="text" param="intervalTime" />
      </mjl:dt>

    </dl>
  </mjl:component>



  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.update.mojo"
    name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.update.button" classes="submitButton" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.delete.mojo"
    name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.delete.button" classes="submitButton" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.cancel.mojo"
    name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.cancel.button" classes="submitButton" />
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>