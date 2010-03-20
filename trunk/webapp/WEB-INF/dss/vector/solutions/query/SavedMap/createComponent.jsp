<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_SavedMap" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.SavedMap.form.name" id="dss.vector.solutions.query.SavedMap.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.query.SavedMapController.create.mojo" name="dss.vector.solutions.query.SavedMap.form.create.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.SavedMapController.cancel.mojo" name="dss.vector.solutions.query.SavedMap.form.cancel.button" />
  </mjl:form>
</dl>
