<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.name" id="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.active}
    </dd>
    <dt>
      <label>
        ${item.endAgeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.endAge}
    </dd>
    <dt>
      <label>
        ${item.startAgeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.startAge}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.edit.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.viewAll.link" />
