<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
<div class="fldContainer">
    <div class="fcTop">
  <mjl:component item="${item}" param="dto">
    <dl>
    <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
         <mjl:select var="current" valueAttribute="id" items="${geoEntintys}" param="geoEntity" >
          <c:choose>
            <c:when test="${current.geoId == item.geoEntity.geoId}">
             <mjl:option selected="selected">
               ${current.geoId}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${current.geoId}
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
        <mjl:input type="text" param="testDate" classes="DatePick" id="testDate"/>
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
            ${current.termName}
          </mjl:option>
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
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.colonyNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="colonyName" />
        <mjl:messages attribute="colonyName">
          <mjl:message />
        </mjl:messages>
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
          ${item.insecticideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
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
          ${item.surfacePostionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${surfacePostion}" param="surfacePostion">
          <c:choose>
            <c:when test="${mjl:contains(item.surfacePostionEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.surfacePostionMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.surfacePostionMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
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
    </dl>
  </mjl:component>
   <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
  </div>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.create.button" />
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div> 