<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.name" id="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="active" />
        <mjl:messages attribute="active">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.endAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="endAge" />
        <mjl:messages attribute="endAge">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.startAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="startAge" />
        <mjl:messages attribute="startAge">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.update.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.delete.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.cancel.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.cancel.button" />
</mjl:form>
