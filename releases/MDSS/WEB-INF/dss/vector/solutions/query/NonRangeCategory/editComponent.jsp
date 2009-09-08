<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.NonRangeCategory.form.name" id="dss.vector.solutions.query.NonRangeCategory.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.exactValueMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="exactValue" />
        <mjl:messages attribute="exactValue">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.NonRangeCategoryController.update.mojo" name="dss.vector.solutions.query.NonRangeCategory.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.query.NonRangeCategoryController.delete.mojo" name="dss.vector.solutions.query.NonRangeCategory.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.NonRangeCategoryController.cancel.mojo" name="dss.vector.solutions.query.NonRangeCategory.form.cancel.button" />
</mjl:form>
