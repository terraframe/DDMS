<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.name" id="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="dateCollected">
<mjl:input type="text" param="dateCollected" />
</mjl:dt>
      <mjl:dt attribute="geoEntity">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_ConcreteMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.ConcreteMosquitoCollectionController.update.mojo" name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.ConcreteMosquitoCollectionController.delete.mojo" name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.ConcreteMosquitoCollectionController.cancel.mojo" name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.cancel.button" />
</mjl:form>
