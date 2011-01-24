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
  <mdss:localize key="Create" var="Localized_Create" />
  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.geo.GeoHierarchyController.create.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.create.button" />
</mjl:form>
