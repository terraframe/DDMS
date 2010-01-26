<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Property"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mjl:command value="Update" action="dss.vector.solutions.PropertyController.update.mojo" name="dss.vector.solutions.Property.form.update.button" classes="submitButton"/>
    <mjl:command value="Cancel" action="dss.vector.solutions.PropertyController.cancel.mojo" name="dss.vector.solutions.Property.form.cancel.button" classes="submitButton"/>
  </dl>
</mjl:form>
