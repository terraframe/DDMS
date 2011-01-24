<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mdss:localize key="Create" var="Localized_Create" />
    
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.PropertyController.create.mojo" name="dss.vector.solutions.Property.form.create.button" classes="submitButton" />
  </dl>
</mjl:form>
