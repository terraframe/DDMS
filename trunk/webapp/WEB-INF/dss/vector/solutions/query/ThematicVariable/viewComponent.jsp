<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.ThematicVariable.form.name" id="dss.vector.solutions.query.ThematicVariable.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.attributeNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.attributeName}
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
        ${item.entityAliasMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.entityAlias}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.ThematicVariableController.edit.mojo" name="dss.vector.solutions.query.ThematicVariable.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.ThematicVariableController.viewAll.mojo" name="dss.vector.solutions.query.ThematicVariable.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
