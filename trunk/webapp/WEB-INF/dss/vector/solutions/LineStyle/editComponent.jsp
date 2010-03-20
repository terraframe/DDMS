<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.LineStyle.form.name" id="dss.vector.solutions.LineStyle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.strokeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="stroke" />
        <mjl:messages attribute="stroke">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.strokeWidthMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="strokeWidth" />
        <mjl:messages attribute="strokeWidth">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.LineStyleController.update.mojo" name="dss.vector.solutions.LineStyle.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.LineStyleController.delete.mojo" name="dss.vector.solutions.LineStyle.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.LineStyleController.cancel.mojo" name="dss.vector.solutions.LineStyle.form.cancel.button" />
</mjl:form>
