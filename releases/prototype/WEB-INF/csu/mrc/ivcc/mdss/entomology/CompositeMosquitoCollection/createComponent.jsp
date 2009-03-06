<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.name" id="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionController.create.mojo" name="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.create.button" />
</mjl:form>
