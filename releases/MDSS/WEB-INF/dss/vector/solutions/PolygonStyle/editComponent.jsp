<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.PolygonStyle.form.name" id="dss.vector.solutions.PolygonStyle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.fillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fill" />
        <mjl:messages attribute="fill">
          <mjl:message />
        </mjl:messages>
      </dd>
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
  <mjl:command value="Update" action="dss.vector.solutions.PolygonStyleController.update.mojo" name="dss.vector.solutions.PolygonStyle.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.PolygonStyleController.delete.mojo" name="dss.vector.solutions.PolygonStyle.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.PolygonStyleController.cancel.mojo" name="dss.vector.solutions.PolygonStyle.form.cancel.button" />
</mjl:form>
