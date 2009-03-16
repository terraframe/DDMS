<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.UnitMaster.form.name" id="dss.vector.solutions.entomology.assay.UnitMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enumNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="enumName" />
        <mjl:messages attribute="enumName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.UnitMasterController.update.mojo" name="dss.vector.solutions.entomology.assay.UnitMaster.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.UnitMasterController.delete.mojo" name="dss.vector.solutions.entomology.assay.UnitMaster.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.UnitMasterController.cancel.mojo" name="dss.vector.solutions.entomology.assay.UnitMaster.form.cancel.button" />
</mjl:form>
