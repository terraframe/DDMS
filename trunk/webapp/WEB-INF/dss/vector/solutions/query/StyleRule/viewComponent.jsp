<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.StyleRule.form.name" id="dss.vector.solutions.query.StyleRule.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.StyleRuleController.edit.mojo" name="dss.vector.solutions.query.StyleRule.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.query.StyleRuleController.viewAll.mojo" name="dss.vector.solutions.query.StyleRule.viewAll.link" />
