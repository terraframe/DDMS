<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.DefinesCategories.form.name" id="dss.vector.solutions.query.DefinesCategories.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.query.DefinesCategoriesController.create.mojo" name="dss.vector.solutions.query.DefinesCategories.form.create.button" />
</mjl:form>
