<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ThresholdData" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.ThresholdData.form.id" name="dss.vector.solutions.general.ThresholdData.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.ThresholdData.form.update.button" value="Update" action="dss.vector.solutions.general.ThresholdDataController.update.mojo" />
    <mjl:command name="dss.vector.solutions.general.ThresholdData.form.delete.button" value="Delete" action="dss.vector.solutions.general.ThresholdDataController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.general.ThresholdData.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.ThresholdDataController.cancel.mojo" />
  </mjl:form>
</dl>
