<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<c:set var="true_label" value='<%=Halp.translateBool(((AdultDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),true)%>'/>
<c:set var="false_label" value='<%=Halp.translateBool(((AdultDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),false)%>'/>


<c:set var="window_title" value="Enter new adult diagnostic assay data"  scope="request"/>
<c:set var="page_title" value="Enter new data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">

  <mjl:component item="${item}" param="dto">
    <dl>
    <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${collection}" param="collection">
          <c:choose>
            <c:when test="${current.id == item.collection.id}">
             <mjl:option selected="selected">
               ${current.displayLabel}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${current.displayLabel}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.testDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="testDate" id="testDate" classes="DatePick"/>
        <mjl:messages attribute="testDate">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.testMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
        <dt>
        <label>
          ${item.generationMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${generation}" param="generation">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="generation">
          <mjl:message />
        </mjl:messages>
      </dd>
            <dt>
        <label>
          ${item.isofemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="isofemale" trueLabel="${true_label}" falseLabel="${false_label}" />
        <mjl:messages attribute="isofemale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sexMd.displayLabel}
        </label>
      </dt>
      <dd>
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
      </dd>
      <dt>
        <label>
          ${item.specieMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.identificationMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.ageRangeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <dl>
          <mjl:struct param="ageRange">
           <dt>
              <label>
                ${item.ageRange.startPointMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="startPoint" />
              <mjl:messages attribute="startPoint">
                <mjl:message />
              </mjl:messages>
            </dd>
            <dt>
              <label>
                ${item.ageRange.endPointMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="endPoint" />
              <mjl:messages attribute="endPoint">
                <mjl:message />
              </mjl:messages>
            </dd>

          </mjl:struct>
        </dl>
      </dd>
      <dt>
        <label>
          ${item.fedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fed" />
        <mjl:messages attribute="fed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.gravidMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="gravid" />
        <mjl:messages attribute="gravid">
          <mjl:message />
        </mjl:messages>
      </dd>
           <dt>
        <label>
          ${item.exposureTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="exposureTime" />
        <mjl:messages attribute="exposureTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.holdingTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="holdingTime" />
        <mjl:messages attribute="holdingTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.insecticideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
         <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><f:message key="Manage_Insecticides"/></a>
      </dd>
       <dt>
        <label>
          ${item.quantityTestedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantityTested" />
        <mjl:messages attribute="quantityTested">
          <mjl:message />
        </mjl:messages>

      </dd>

      <dt>
        <label>
          ${item.quantityDeadMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantityDead" />
        <mjl:messages attribute="quantityDead">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.controlTestMortalityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="controlTestMortality" />
        <mjl:messages attribute="controlTestMortality">
          <mjl:message />
        </mjl:messages>
      </dd>

        <dt>
        <label>
          ${item.intervalTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="intervalTime" />
        <mjl:messages attribute="intervalTime">
          <mjl:message />
        </mjl:messages>
      </dd>

    </dl>
  </mjl:component>



  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.create.button" classes="submitButton" />
</mjl:form>