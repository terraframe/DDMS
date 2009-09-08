<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.name" id="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.id" method="POST">
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
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.NetDisplayLabelController.edit.mojo" name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.NetDisplayLabelController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.viewAll.link" />
