<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.UrbanHealthCentre.form.name" id="dss.vector.solutions.geo.generated.UrbanHealthCentre.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activatedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="activated" />
        <mjl:messages attribute="activated">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.entityNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="entityName" />
        <mjl:messages attribute="entityName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.gazIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="gazId" />
        <mjl:messages attribute="gazId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="geoId" />
        <mjl:messages attribute="geoId">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.geo.generated.UrbanHealthCentreController.update.mojo" name="dss.vector.solutions.geo.generated.UrbanHealthCentre.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.UrbanHealthCentreController.delete.mojo" name="dss.vector.solutions.geo.generated.UrbanHealthCentre.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.UrbanHealthCentreController.cancel.mojo" name="dss.vector.solutions.geo.generated.UrbanHealthCentre.form.cancel.button" />
</mjl:form>
