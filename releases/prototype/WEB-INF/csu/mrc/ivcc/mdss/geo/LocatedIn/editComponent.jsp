<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.LocatedIn.form.name" id="csu.mrc.ivcc.mdss.geo.LocatedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.geo.LocatedInController.update.mojo" name="csu.mrc.ivcc.mdss.geo.LocatedIn.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.geo.LocatedInController.delete.mojo" name="csu.mrc.ivcc.mdss.geo.LocatedIn.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.LocatedInController.cancel.mojo" name="csu.mrc.ivcc.mdss.geo.LocatedIn.form.cancel.button" />
</mjl:form>
