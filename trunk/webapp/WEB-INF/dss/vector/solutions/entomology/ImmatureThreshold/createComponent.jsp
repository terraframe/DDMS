<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Configure_immature_thresholds" />
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThreshold.form.id" name="dss.vector.solutions.entomology.ImmatureThreshold.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command name="dss.vector.solutions.entomology.ImmatureThreshold.form.create.button" value="${Localized_Create}" action="dss.vector.solutions.entomology.ImmatureThresholdController.create.mojo" />
  </mjl:form>
</dl>
