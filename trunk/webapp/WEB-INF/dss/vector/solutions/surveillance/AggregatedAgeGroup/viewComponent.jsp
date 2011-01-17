<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Age_Group"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.name" id="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.displayLabel}
    </dd>
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
        ${item.startAgeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.startAge}
    </dd>    
    <dt>
      <label>
        ${item.endAgeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.endAge}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.edit.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.form.edit.button" />
  </dl>
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo" name="dss.vector.solutions.surveillance.AggregatedAgeGroup.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
