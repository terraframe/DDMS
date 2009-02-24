<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.name" id="mdss.ivcc.mrc.csu.geo.GeoEntity.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.entityNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.entityName}
    </dd>
    <dt>
      <label>
        ${item.geoIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.geoId}
    </dd>
    <dt>
      <label>
        ${item.terrainMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.terrainEnumNames}">
          <li>
            ${item.terrainMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.edit.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.viewAll.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.viewAll.link" />
