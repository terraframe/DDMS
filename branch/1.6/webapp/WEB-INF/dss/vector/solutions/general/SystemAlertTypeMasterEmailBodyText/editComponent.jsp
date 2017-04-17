<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Edit_SystemAlertTypeMasterEmailBodyText" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyText.form.id" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyText.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyText.form.update.button" value="${Update_Localize}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyTextController.update.mojo" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyText.form.delete.button" value="${Delete_Localize}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyTextController.delete.mojo" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyText.form.cancel.button" value="${Cancel_Localize}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyTextController.cancel.mojo" />
  </mjl:form>
</dl>
