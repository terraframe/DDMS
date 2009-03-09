<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.util.Facade.form.name" id="csu.mrc.ivcc.mdss.util.Facade.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.util.FacadeController.update.mojo" name="csu.mrc.ivcc.mdss.util.Facade.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.util.FacadeController.delete.mojo" name="csu.mrc.ivcc.mdss.util.Facade.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.util.FacadeController.cancel.mojo" name="csu.mrc.ivcc.mdss.util.Facade.form.cancel.button" />
</mjl:form>
