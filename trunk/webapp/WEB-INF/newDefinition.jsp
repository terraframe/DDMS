<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.generated.GeoEntity.form.name" id="csu.mrc.ivcc.mdss.geo.generated.GeoEntity.form.id" method="POST">
  <mjl:component item="${definition}" param="definition">
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
          ${definition.parentTypeMdId.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" param="parentType" items="${availableParents}">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${definition.allowedInMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" param="allowedIn" items="${allowedInParents}">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.createDefinition.mojo" name="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.form.createDefinition.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.cancelCreateDefinition.mojo" name="csu.mrc.ivcc.mdss.geo.GeoEntityTypeController.form.cancelCreateDefinition.button" />
</mjl:form>