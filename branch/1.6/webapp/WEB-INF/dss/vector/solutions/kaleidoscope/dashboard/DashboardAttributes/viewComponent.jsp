<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_DashboardAttributes" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributes.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributes.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Dashboard Wrapper
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapper.form.view.link" action="dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Attribute Wrapper
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.AttributeWrapper.form.view.link" action="dss.vector.solutions.kaleidoscope.dashboard.AttributeWrapperController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <mjl:dt attribute="listOrder">
        ${item.listOrder}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributes.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributesController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributes.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributesController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
