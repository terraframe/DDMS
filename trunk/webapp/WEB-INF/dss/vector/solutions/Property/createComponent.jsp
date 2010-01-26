<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mjl:command value="Create" action="dss.vector.solutions.PropertyController.create.mojo" name="dss.vector.solutions.Property.form.create.button" classes="submitButton"/>
  </dl>
</mjl:form>
