<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_WeeklyThreshold" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.general.WeeklyThreshold.form.name" id="dss.vector.solutions.general.WeeklyThreshold.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Population Data
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.general.ThresholdDataController.view.mojo" name="dss.vector.solutions.general.ThresholdData.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Epi Week
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.general.EpiWeekController.view.mojo" name="dss.vector.solutions.general.EpiWeek.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
      <mjl:dt attribute="identification">
        ${item.identification}
      </mjl:dt>
      <mjl:dt attribute="lastIdentification">
        ${item.lastIdentification.id}
      </mjl:dt>
      <mjl:dt attribute="lastNotification">
        ${item.lastNotification.id}
      </mjl:dt>
      <mjl:dt attribute="notification">
        ${item.notification}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.general.WeeklyThresholdController.edit.mojo" name="dss.vector.solutions.general.WeeklyThreshold.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.general.WeeklyThresholdController.viewAll.mojo" name="dss.vector.solutions.general.WeeklyThreshold.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
