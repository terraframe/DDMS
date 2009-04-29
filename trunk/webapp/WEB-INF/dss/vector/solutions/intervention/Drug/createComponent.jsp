<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.Drug.form.name" id="dss.vector.solutions.intervention.Drug.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="displayLabel">
        <mjl:input type="text" param="displayLabel" />
        
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.DrugController.create.mojo" name="dss.vector.solutions.intervention.Drug.form.create.button" />
</mjl:form>
