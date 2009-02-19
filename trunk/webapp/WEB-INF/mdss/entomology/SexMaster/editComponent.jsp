<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.SexMaster.form.name" id="mdss.entomology.SexMaster.form.id" method="POST">
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
  <mjl:command value="Update" action="mdss.entomology.SexMasterController.update.mojo" name="mdss.entomology.SexMaster.form.update.button" />
  <mjl:command value="Delete" action="mdss.entomology.SexMasterController.delete.mojo" name="mdss.entomology.SexMaster.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.entomology.SexMasterController.cancel.mojo" name="mdss.entomology.SexMaster.form.cancel.button" />
</mjl:form>
