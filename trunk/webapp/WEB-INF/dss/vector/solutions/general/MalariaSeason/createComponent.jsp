<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.MalariaSeason.form.name" id="dss.vector.solutions.general.MalariaSeason.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.endDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="endDate" />
        <mjl:messages attribute="endDate">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.seasonNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="seasonName" />
        <mjl:messages attribute="seasonName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.startDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="startDate" />
        <mjl:messages attribute="startDate">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.general.MalariaSeasonController.create.mojo" name="dss.vector.solutions.general.MalariaSeason.form.create.button" />
</mjl:form>
