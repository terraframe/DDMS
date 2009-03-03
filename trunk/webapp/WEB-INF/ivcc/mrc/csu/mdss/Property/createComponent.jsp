<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.Property.form.name" id="ivcc.mrc.csu.mdss.Property.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.descriptionMd.displayLabel}
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
          ${item.propertyNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyName" />
        <mjl:messages attribute="propertyName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.propertyPackageMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyPackage" />
        <mjl:messages attribute="propertyPackage">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.propertyTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyType" />
        <mjl:messages attribute="propertyType">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.propertyValidatorMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyValidator" />
        <mjl:messages attribute="propertyValidator">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.propertyValueMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyValue" />
        <mjl:messages attribute="propertyValue">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="ivcc.mrc.csu.mdss.PropertyController.create.mojo" name="ivcc.mrc.csu.mdss.Property.form.create.button" />
</mjl:form>
