<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.AssayMethod.form.name" id="csu.mrc.ivcc.mdss.entomology.AssayMethod.form.id" method="POST">
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
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.AssayMethodController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.AssayMethod.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.AssayMethodController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.AssayMethod.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.AssayMethodController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.AssayMethod.form.cancel.button" />
</mjl:form>
