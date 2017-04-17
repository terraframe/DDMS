<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.GeoHierarchy.form.name" id="dss.vector.solutions.geo.GeoHierarchy.form.id" method="POST">
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
  <mdss:localize key="Update" var="Localized_Update" />
  <mjl:command value="${Localized_Update}" action="dss.vector.solutions.geo.GeoHierarchyController.update.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.update.button" />
  <mdss:localize key="Delete" var="Localized_Delete" />
  <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.geo.GeoHierarchyController.delete.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.delete.button" />
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.geo.GeoHierarchyController.cancel.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.cancel.button" />
</mjl:form>
