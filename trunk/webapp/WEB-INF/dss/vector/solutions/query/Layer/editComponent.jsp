<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Layer" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
  <mjl:form name="dss.vector.solutions.query.Layer.form.name" id="dss.vector.solutions.query.Layer.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.query.LayerController.saveLayer.mojo" name="dss.vector.solutions.query.Layer.form.saveLayer.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.query.LayerController.cancel.mojo" name="dss.vector.solutions.query.Layer.form.cancel.button" />
  </mjl:form>