<jsp:include page="/WEB-INF/inlineError.jsp" />

<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.generated.GeoEntity.form.name" id="csu.mrc.ivcc.mdss.geo.generated.GeoEntity.form.id" method="POST">
  <mjl:component item="${definition}" param="definition">
  <mjl:input type="hidden" param="parentGeoHierarchyId" />
    <dl>
      <dt>
        <label>
          ${definition.typeNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="typeName" />
        <mjl:messages attribute="typeName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.descriptionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="description" />
        <mjl:messages attribute="description">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.parentTypeIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="referenceId" param="parentTypeId" items="${availableParents}">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${definition.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
      </dd>
      <dt>
        <label>
          ${definition.spatialTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" param="spatialType" items="${types}">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
  </mjl:component>
    </dl>
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.createDefinition.mojo" name="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.form.createDefinition.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.cancelCreateDefinition.mojo" name="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.form.cancelCreateDefinition.button" />
</mjl:form>