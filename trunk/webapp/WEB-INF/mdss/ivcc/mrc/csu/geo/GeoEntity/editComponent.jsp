<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.name" id="mdss.ivcc.mrc.csu.geo.GeoEntity.form.id" method="POST">
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
        <mjl:select var="current" valueAttribute="enumName" items="${mdss_test_GeoEntity_terrain}" param="terrain">
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
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.update.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.delete.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.cancel.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.cancel.button" />
</mjl:form>
