<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.Property.form.name" id="csu.mrc.ivcc.mdss.Property.form.id" method="POST">
  <div class="fldContainer">
    <div class="fcTop"><div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    <div style="position:absolute; left:20px; top:25px;">

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
</div>
</div>
<div class="submitButton_bl"></div>      
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.PropertyController.edit.mojo" name="csu.mrc.ivcc.mdss.Property.form.edit.button" classes="submitButton"/>
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.PropertyController.viewAll.mojo" name="csu.mrc.ivcc.mdss.Property.viewAll.link" />
