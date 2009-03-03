<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollection.form.name" id="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollection.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollectionController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollection.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollectionController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.CompositeMosquitoCollection.viewAll.link" />
