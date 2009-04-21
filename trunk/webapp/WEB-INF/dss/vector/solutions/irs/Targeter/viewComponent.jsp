<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.Targeter.form.name" id="dss.vector.solutions.irs.Targeter.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.TargeterController.edit.mojo" name="dss.vector.solutions.irs.Targeter.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.TargeterController.viewAll.mojo" name="dss.vector.solutions.irs.Targeter.viewAll.link" />
