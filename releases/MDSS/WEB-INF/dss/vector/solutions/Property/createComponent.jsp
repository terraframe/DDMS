<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
<div class="fldContainer">
    <div class="fcTop"><div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    <div style="position:absolute; left:20px; top:25px;">

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
  </div>
</div>


  <mjl:command value="Create" action="dss.vector.solutions.PropertyController.create.mojo" name="dss.vector.solutions.Property.form.create.button" classes="submitButton"/>
</mjl:form>
