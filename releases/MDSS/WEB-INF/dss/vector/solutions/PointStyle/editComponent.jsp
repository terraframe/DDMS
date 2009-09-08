<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.PointStyle.form.name" id="dss.vector.solutions.PointStyle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.wellKnownNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_PointStyle_wellKnownName}" param="wellKnownName">
          <mjl:option selected="${mjl:contains(item.wellKnownNameEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.wellKnownNameMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="wellKnownName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.strokeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="stroke" />
        <mjl:messages attribute="stroke">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.strokeWidthMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="strokeWidth" />
        <mjl:messages attribute="strokeWidth">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.PointStyleController.update.mojo" name="dss.vector.solutions.PointStyle.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.PointStyleController.delete.mojo" name="dss.vector.solutions.PointStyle.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.PointStyleController.cancel.mojo" name="dss.vector.solutions.PointStyle.form.cancel.button" />
</mjl:form>
