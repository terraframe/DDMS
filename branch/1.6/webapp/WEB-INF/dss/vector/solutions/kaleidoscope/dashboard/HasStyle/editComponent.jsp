<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="Edit_HasStyle" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.id">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.update.button" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.update.mojo" value="${Update_Localize}" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.delete.button" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.delete.mojo" value="${Delete_Localize}" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.cancel.button" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.cancel.mojo" value="${Cancel_Localize}" />
  </mjl:form>
</dl>
