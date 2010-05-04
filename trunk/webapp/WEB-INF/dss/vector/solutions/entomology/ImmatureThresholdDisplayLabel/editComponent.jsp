<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ImmatureThresholdDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.id" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelController.update.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelController.delete.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
