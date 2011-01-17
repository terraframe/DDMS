<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_WeeklyThreshold" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.WeeklyThreshold.form.id" name="dss.vector.solutions.general.WeeklyThreshold.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          Population Data
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.general.ThresholdData.form.view.link" action="dss.vector.solutions.general.ThresholdDataController.view.mojo" >
          <mjl:property name="id" value="${item.parentId}" />
          ${item.parent.keyName}
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Epi Week
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.general.EpiWeek.form.view.link" action="dss.vector.solutions.general.EpiWeekController.view.mojo">
          <mjl:property name="id" value="${item.parentId}" />
          ${item.parent.keyName}
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
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.edit.button" value="Edit" action="dss.vector.solutions.general.WeeklyThresholdController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.WeeklyThreshold.viewAll.link" action="dss.vector.solutions.general.WeeklyThresholdController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
