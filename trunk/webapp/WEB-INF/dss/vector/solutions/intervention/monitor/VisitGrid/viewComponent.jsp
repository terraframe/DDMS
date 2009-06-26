<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Visit_Grid"  scope="request"/>


<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.VisitGrid.form.name" id="dss.vector.solutions.intervention.monitor.VisitGrid.form.id" method="POST">
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
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.optionNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.optionName}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.VisitGridController.edit.mojo" name="dss.vector.solutions.intervention.monitor.VisitGrid.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.VisitGridController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.VisitGrid.viewAll.link">
  <fmt:message key="View_All"/>
</mjl:commandLink>
