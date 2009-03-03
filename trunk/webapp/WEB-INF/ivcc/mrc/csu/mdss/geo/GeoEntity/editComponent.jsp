<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.geo.GeoEntity.form.name" id="ivcc.mrc.csu.mdss.geo.GeoEntity.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.entityNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="entityName" />
        <mjl:messages attribute="entityName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="geoId" />
        <mjl:messages attribute="geoId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.terrainMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${mdss_ivcc_mrc_csu_geo_GeoEntity_terrain}" param="terrain">
          <c:choose>
            <c:when test="${mjl:contains(item.terrainEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.terrainMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.terrainMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.geo.GeoEntityController.update.mojo" name="ivcc.mrc.csu.mdss.geo.GeoEntity.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.geo.GeoEntityController.delete.mojo" name="ivcc.mrc.csu.mdss.geo.GeoEntity.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.geo.GeoEntityController.cancel.mojo" name="ivcc.mrc.csu.mdss.geo.GeoEntity.form.cancel.button" />
</mjl:form>
