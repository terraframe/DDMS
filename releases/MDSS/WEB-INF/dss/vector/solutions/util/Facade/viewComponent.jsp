<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.util.Facade.form.name" id="dss.vector.solutions.util.Facade.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  
  <mjl:command value="Edit" action="dss.vector.solutions.util.FacadeController.edit.mojo" name="dss.vector.solutions.util.Facade.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.util.FacadeController.viewAll.mojo" name="dss.vector.solutions.util.Facade.viewAll.link" />
