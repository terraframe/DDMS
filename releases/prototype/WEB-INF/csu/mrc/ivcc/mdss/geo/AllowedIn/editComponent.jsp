<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.name" id="csu.mrc.ivcc.mdss.geo.AllowedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.geo.AllowedInController.update.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.geo.AllowedInController.delete.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.AllowedInController.cancel.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.cancel.button" />
</mjl:form>
