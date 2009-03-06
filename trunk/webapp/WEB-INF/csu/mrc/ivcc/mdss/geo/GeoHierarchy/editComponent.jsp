<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.name" id="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.geoEntityClassMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_geo_GeoHierarchy_geoEntityClass}" param="geoEntityClass">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.update.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.delete.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.cancel.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.cancel.button" />
</mjl:form>
