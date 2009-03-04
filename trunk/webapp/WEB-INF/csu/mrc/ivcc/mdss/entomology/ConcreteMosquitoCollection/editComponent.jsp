<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.form.name" id="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.dateCollectedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="dateCollected" />
        <mjl:messages attribute="dateCollected">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_ConcreteMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.form.cancel.button" />
</mjl:form>
