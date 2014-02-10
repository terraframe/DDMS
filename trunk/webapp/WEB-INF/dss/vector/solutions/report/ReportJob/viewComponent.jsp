<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_ReportJob" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.report.ReportJob.form.id" name="dss.vector.solutions.report.ReportJob.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="reportItem">
        ${item.reportItem.keyName}
      </mjl:dt>
      <mjl:dt attribute="cancelable">
        ${item.cancelable ? item.cancelableMd.positiveDisplayLabel : item.cancelableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="canceled">
        ${item.canceled ? item.canceledMd.positiveDisplayLabel : item.canceledMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="completed">
        ${item.completed ? item.completedMd.positiveDisplayLabel : item.completedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="cronExpression">
        ${item.cronExpression}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="endTime">
        <span class="formatDate">
          ${item.endTime}
        </span>
      </mjl:dt>
      <mjl:dt attribute="jobId">
        ${item.jobId}
      </mjl:dt>
      <mjl:dt attribute="lastRun">
        <span class="formatDate">
          ${item.lastRun}
        </span>
      </mjl:dt>
      <mjl:dt attribute="maxRetries">
        ${item.maxRetries}
      </mjl:dt>
      <mjl:dt attribute="pauseable">
        ${item.pauseable ? item.pauseableMd.positiveDisplayLabel : item.pauseableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="paused">
        ${item.paused ? item.pausedMd.positiveDisplayLabel : item.pausedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="removeOnComplete">
        ${item.removeOnComplete ? item.removeOnCompleteMd.positiveDisplayLabel : item.removeOnCompleteMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="repeated">
        ${item.repeated ? item.repeatedMd.positiveDisplayLabel : item.repeatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="retries">
        ${item.retries}
      </mjl:dt>
      <mjl:dt attribute="running">
        ${item.running ? item.runningMd.positiveDisplayLabel : item.runningMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="startTime">
        <span class="formatDate">
          ${item.startTime}
        </span>
      </mjl:dt>
      <mjl:dt attribute="timeout">
        ${item.timeout}
      </mjl:dt>
      <mjl:dt attribute="workProgress">
        ${item.workProgress}
      </mjl:dt>
      <mjl:dt attribute="workTotal">
        ${item.workTotal}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.report.ReportJob.form.edit.button" value="Edit" action="dss.vector.solutions.report.ReportJobController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.report.ReportJob.viewAll.link" action="dss.vector.solutions.report.ReportJobController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
