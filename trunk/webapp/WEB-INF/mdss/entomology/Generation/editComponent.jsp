<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.Generation.form.name" id="mdss.entomology.Generation.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.termNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="termName" />
        <mjl:messages attribute="termName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.entomology.GenerationController.update.mojo" name="mdss.entomology.Generation.form.update.button" />
  <mjl:command value="Delete" action="mdss.entomology.GenerationController.delete.mojo" name="mdss.entomology.Generation.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.entomology.GenerationController.cancel.mojo" name="mdss.entomology.Generation.form.cancel.button" />
</mjl:form>
