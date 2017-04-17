<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="Edit_DashboardLayerNameLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabel.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabel.form.id">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabel.form.update.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabelController.update.mojo" value="${Update_Localize}" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabel.form.delete.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabelController.delete.mojo" value="${Delete_Localize}" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabel.form.cancel.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabelController.cancel.mojo" value="${Cancel_Localize}" />
  </mjl:form>
</dl>
