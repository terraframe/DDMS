<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.mo.InfectivityMethodology.form.name" id="dss.vector.solutions.mo.InfectivityMethodology.form.id" method="POST">
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
      ${item.displayLabel}
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
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.mo.InfectivityMethodologyController.edit.mojo" name="dss.vector.solutions.mo.InfectivityMethodology.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.mo.InfectivityMethodologyController.viewAll.mojo" name="dss.vector.solutions.mo.InfectivityMethodology.viewAll.link" />
