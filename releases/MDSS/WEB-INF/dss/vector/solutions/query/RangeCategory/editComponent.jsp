<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.RangeCategory.form.name" id="dss.vector.solutions.query.RangeCategory.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.lowerBoundMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lowerBound" />
        <mjl:messages attribute="lowerBound">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.upperBoundMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="upperBound" />
        <mjl:messages attribute="upperBound">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.RangeCategoryController.update.mojo" name="dss.vector.solutions.query.RangeCategory.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.query.RangeCategoryController.delete.mojo" name="dss.vector.solutions.query.RangeCategory.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.RangeCategoryController.cancel.mojo" name="dss.vector.solutions.query.RangeCategory.form.cancel.button" />
</mjl:form>
