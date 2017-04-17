<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Property"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mdss:localize key="Update" var="Localized_Update" />
    
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.PropertyController.update.mojo" name="dss.vector.solutions.Property.form.update.button" classes="submitButton" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.PropertyController.cancel.mojo" name="dss.vector.solutions.Property.form.cancel.button" classes="submitButton" />
  </dl>
</mjl:form>
