<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.ThematicVariable.form.name" id="dss.vector.solutions.query.ThematicVariable.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.attributeNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="attributeName" />
        <mjl:messages attribute="attributeName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
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
          ${item.entityAliasMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="entityAlias" />
        <mjl:messages attribute="entityAlias">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.ThematicVariableController.update.mojo" name="dss.vector.solutions.query.ThematicVariable.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.query.ThematicVariableController.delete.mojo" name="dss.vector.solutions.query.ThematicVariable.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.ThematicVariableController.cancel.mojo" name="dss.vector.solutions.query.ThematicVariable.form.cancel.button" />
</mjl:form>
