<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.MalariaSeason.form.name" id="dss.vector.solutions.general.MalariaSeason.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.endDateMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.endDate}
    </dd>
    <dt>
      <label>
        ${item.seasonLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.seasonLabel.value}
    </dd>
    <dt>
      <label>
        ${item.startDateMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.startDate}
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.general.MalariaSeasonController.edit.mojo" name="dss.vector.solutions.general.MalariaSeason.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink action="dss.vector.solutions.general.MalariaSeasonController.viewAll.mojo" name="dss.vector.solutions.general.MalariaSeason.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
