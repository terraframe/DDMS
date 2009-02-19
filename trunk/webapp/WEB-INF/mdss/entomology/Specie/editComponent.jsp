<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.Specie.form.name" id="mdss.entomology.Specie.form.id" method="POST">
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
  <mjl:command value="Update" action="mdss.entomology.SpecieController.update.mojo" name="mdss.entomology.Specie.form.update.button" />
  <mjl:command value="Delete" action="mdss.entomology.SpecieController.delete.mojo" name="mdss.entomology.Specie.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.entomology.SpecieController.cancel.mojo" name="mdss.entomology.Specie.form.cancel.button" />
</mjl:form>
