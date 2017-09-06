<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_HasLayer" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Map
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.form.view.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
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
      <mjl:dt attribute="layerIndex">
        ${item.layerIndex}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
