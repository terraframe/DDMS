<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.name" id="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.cancel.button" />
</mjl:form>
