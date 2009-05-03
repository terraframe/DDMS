<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.Drug.form.name" id="dss.vector.solutions.intervention.Drug.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="displayLabel">
      ${item.displayLabel}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.DrugController.edit.mojo" name="dss.vector.solutions.intervention.Drug.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.DrugController.viewAll.mojo" name="dss.vector.solutions.intervention.Drug.viewAll.link" />
