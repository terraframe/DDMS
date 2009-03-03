<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.Property.form.name" id="ivcc.mrc.csu.mdss.Property.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.descriptionMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.description}
    </dd>
    <dt>
      <label>
        ${item.propertyNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyName}
    </dd>
    <dt>
      <label>
        ${item.propertyPackageMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyPackage}
    </dd>
    <dt>
      <label>
        ${item.propertyTypeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyType}
    </dd>
    <dt>
      <label>
        ${item.propertyValidatorMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyValidator}
    </dd>
    <dt>
      <label>
        ${item.propertyValueMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyValue}
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.PropertyController.edit.mojo" name="ivcc.mrc.csu.mdss.Property.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.PropertyController.viewAll.mojo" name="ivcc.mrc.csu.mdss.Property.viewAll.link" />
