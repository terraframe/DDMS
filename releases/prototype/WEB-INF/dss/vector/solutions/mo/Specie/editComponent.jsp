<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.mo.Specie.form.name" id="dss.vector.solutions.mo.Specie.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.definitionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="definition" />
        <mjl:messages attribute="definition">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inheritsTermMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inheritsTerm" />
        <mjl:messages attribute="inheritsTerm">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inheritsTermNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inheritsTermName" />
        <mjl:messages attribute="inheritsTermName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.oboNamespaceMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="oboNamespace" />
        <mjl:messages attribute="oboNamespace">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.termIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="termId" />
        <mjl:messages attribute="termId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.termNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="termName" />
        <mjl:messages attribute="termName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.mo.SpecieController.update.mojo" name="dss.vector.solutions.mo.Specie.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.mo.SpecieController.delete.mojo" name="dss.vector.solutions.mo.Specie.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.mo.SpecieController.cancel.mojo" name="dss.vector.solutions.mo.Specie.form.cancel.button" />
</mjl:form>
