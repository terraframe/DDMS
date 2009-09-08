<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AbstractGridDisplayLabel.form.name" id="dss.vector.solutions.surveillance.AbstractGridDisplayLabel.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.defaultLocaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.defaultLocale}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AbstractGridDisplayLabelController.edit.mojo" name="dss.vector.solutions.surveillance.AbstractGridDisplayLabel.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.AbstractGridDisplayLabelController.viewAll.mojo" name="dss.vector.solutions.surveillance.AbstractGridDisplayLabel.viewAll.link" />
