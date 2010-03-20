<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.SpatialMaster.form.name" id="dss.vector.solutions.geo.SpatialMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
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
          ${item.enumNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="enumName" />
        <mjl:messages attribute="enumName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.geo.SpatialMasterController.update.mojo" name="dss.vector.solutions.geo.SpatialMaster.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.geo.SpatialMasterController.delete.mojo" name="dss.vector.solutions.geo.SpatialMaster.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.SpatialMasterController.cancel.mojo" name="dss.vector.solutions.geo.SpatialMaster.form.cancel.button" />
</mjl:form>
