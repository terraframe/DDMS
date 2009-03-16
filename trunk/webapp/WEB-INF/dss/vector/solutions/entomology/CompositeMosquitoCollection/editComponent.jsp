<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.CompositeMosquitoCollection.form.name" id="dss.vector.solutions.entomology.CompositeMosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.CompositeMosquitoCollectionController.update.mojo" name="dss.vector.solutions.entomology.CompositeMosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.CompositeMosquitoCollectionController.delete.mojo" name="dss.vector.solutions.entomology.CompositeMosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.CompositeMosquitoCollectionController.cancel.mojo" name="dss.vector.solutions.entomology.CompositeMosquitoCollection.form.cancel.button" />
</mjl:form>
