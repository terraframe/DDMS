<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_NonSentinelSite" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.NonSentinelSite.form.name" id="dss.vector.solutions.geo.generated.NonSentinelSite.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.geo.generated.NonSentinelSiteController.create.mojo" name="dss.vector.solutions.geo.generated.NonSentinelSite.form.create.button" />
  </mjl:form>
</dl>
