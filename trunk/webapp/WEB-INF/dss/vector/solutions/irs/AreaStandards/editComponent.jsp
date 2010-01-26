<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_AreaStandards" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.irs.AreaStandards.form.id" name="dss.vector.solutions.irs.AreaStandards.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.irs.AreaStandards.form.update.button" value="Update" action="dss.vector.solutions.irs.AreaStandardsController.update.mojo" />
    <mjl:command name="dss.vector.solutions.irs.AreaStandards.form.delete.button" value="Delete" action="dss.vector.solutions.irs.AreaStandardsController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.irs.AreaStandards.form.cancel.button" value="Cancel" action="dss.vector.solutions.irs.AreaStandardsController.cancel.mojo" />
  </mjl:form>
</dl>
