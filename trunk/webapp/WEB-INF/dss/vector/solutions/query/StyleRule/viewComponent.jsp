<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.StyleRule.form.name" id="dss.vector.solutions.query.StyleRule.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  
  <mdss:localize key="Edit" var="Localized_Edit" />
  
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.StyleRuleController.edit.mojo" name="dss.vector.solutions.query.StyleRule.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.query.StyleRuleController.viewAll.mojo" name="dss.vector.solutions.query.StyleRule.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
