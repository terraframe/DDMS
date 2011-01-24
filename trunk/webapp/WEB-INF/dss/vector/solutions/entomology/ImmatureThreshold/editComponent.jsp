<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Configure_immature_thresholds" />
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThreshold.form.id" name="dss.vector.solutions.entomology.ImmatureThreshold.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThreshold.form.update.button" value="${Localized_Update}" action="dss.vector.solutions.entomology.ImmatureThresholdController.update.mojo" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThreshold.form.cancel.button" value="${Localized_Cancel}" action="dss.vector.solutions.entomology.ImmatureThresholdController.cancel.mojo" />
  </mjl:form>
</dl>
