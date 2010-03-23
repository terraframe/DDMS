<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Active_Ingredient_View" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.mo.ActiveIngredient.form.name" id="dss.vector.solutions.mo.ActiveIngredient.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.definitionMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.definition}
    </dd>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
    <dt>
      <label>
        ${item.inheritsTermMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inheritsTerm}
    </dd>
    <dt>
      <label>
        ${item.inheritsTermNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inheritsTermName}
    </dd>
    <dt>
      <label>
        ${item.oboNamespaceMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.oboNamespace}
    </dd>
    <dt>
      <label>
        ${item.termIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.termId}
    </dd>
    <dt>
      <label>
        ${item.termNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.termName}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.mo.ActiveIngredientController.edit.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.mo.ActiveIngredientController.viewAll.mojo" name="dss.vector.solutions.mo.ActiveIngredient.viewAll.link">
<fmt:message key="View_All" />
</mjl:commandLink>

