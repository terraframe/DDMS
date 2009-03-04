<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.name" id="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollection.viewAll.link" />
