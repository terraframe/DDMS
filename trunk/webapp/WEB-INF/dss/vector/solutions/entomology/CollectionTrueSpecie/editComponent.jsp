<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.name" id="dss.vector.solutions.entomology.CollectionTrueSpecie.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.update.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.delete.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.cancel.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.cancel.button" />
</mjl:form>
