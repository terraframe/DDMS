<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.mo.AbstractTerm.form.name" id="ivcc.mrc.csu.mdss.mo.AbstractTerm.form.id" method="POST">
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
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.mo.AbstractTermController.update.mojo" name="ivcc.mrc.csu.mdss.mo.AbstractTerm.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.mo.AbstractTermController.delete.mojo" name="ivcc.mrc.csu.mdss.mo.AbstractTerm.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.mo.AbstractTermController.cancel.mojo" name="ivcc.mrc.csu.mdss.mo.AbstractTerm.form.cancel.button" />
</mjl:form>
