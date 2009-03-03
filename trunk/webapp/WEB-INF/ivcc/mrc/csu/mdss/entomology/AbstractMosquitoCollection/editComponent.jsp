<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.name" id="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.id" method="POST">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollectionController.update.mojo" name="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollectionController.delete.mojo" name="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollectionController.cancel.mojo" name="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.cancel.button" />
</mjl:form>
