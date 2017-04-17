<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
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
  <mdss:localize key="Create" var="Localized_Create" />
  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.query.ThematicVariableController.create.mojo" name="dss.vector.solutions.query.ThematicVariable.form.create.button" />
</mjl:form>
