<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_SentinelSite" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.geo.generated.SentinelSite.form.id" name="dss.vector.solutions.geo.generated.SentinelSite.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command name="dss.vector.solutions.geo.generated.SentinelSite.form.update.button" value="${Localized_Update}" action="dss.vector.solutions.geo.generated.SentinelSiteController.update.mojo" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command name="dss.vector.solutions.geo.generated.SentinelSite.form.delete.button" value="${Localized_Delete}" action="dss.vector.solutions.geo.generated.SentinelSiteController.delete.mojo" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command name="dss.vector.solutions.geo.generated.SentinelSite.form.cancel.button" value="${Localized_Cancel}" action="dss.vector.solutions.geo.generated.SentinelSiteController.cancel.mojo" />
  </mjl:form>
</dl>
