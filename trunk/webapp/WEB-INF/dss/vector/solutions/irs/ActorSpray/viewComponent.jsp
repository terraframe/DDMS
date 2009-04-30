<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ActorSpray.form.name" id="dss.vector.solutions.irs.ActorSpray.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.targetMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.target}
    </dd>
    <dt>
      <label>
        ${item.teamSprayWeekMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.teamSprayWeek}
    </dd>
    <dt>
      <label>
        ${item.sprayDataMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayData.keyName}" action="dss.vector.solutions.irs.SprayDataController.view.mojo" name="dss.vector.solutions.irs.SprayData.form.view.link">
        <mjl:property value="${item.sprayData.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.ActorSprayController.edit.mojo" name="dss.vector.solutions.irs.ActorSpray.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.ActorSprayController.viewAll.mojo" name="dss.vector.solutions.irs.ActorSpray.viewAll.link" />
