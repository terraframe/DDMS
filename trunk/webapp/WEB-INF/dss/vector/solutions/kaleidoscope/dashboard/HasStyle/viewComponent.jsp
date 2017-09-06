<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_HasStyle" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Layer
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer.form.view.link" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Style
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle.form.view.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
