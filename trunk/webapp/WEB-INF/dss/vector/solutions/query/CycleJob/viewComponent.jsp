<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_CycleJob" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.CycleJob.form.id" name="dss.vector.solutions.query.CycleJob.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="jobName">
        ${item.jobName}
      </mjl:dt>
      <mjl:dt attribute="layerId">
        ${item.layerId}
      </mjl:dt>
      <mjl:dt attribute="savedMap">
        ${item.savedMap.keyName}
      </mjl:dt>
      <mjl:dt attribute="scheduleJob">
        ${item.scheduleJob ? item.scheduleJobMd.positiveDisplayLabel : item.scheduleJobMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.CycleJob.form.edit.button" value="Edit" action="dss.vector.solutions.query.CycleJobController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.CycleJob.viewAll.link" action="dss.vector.solutions.query.CycleJobController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
