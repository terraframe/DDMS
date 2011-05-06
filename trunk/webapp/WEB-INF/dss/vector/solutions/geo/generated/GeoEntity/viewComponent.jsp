<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_GeoEntity" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.geo.generated.GeoEntity.form.id" name="dss.vector.solutions.geo.generated.GeoEntity.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="activated">
        ${item.activated ? item.activatedMd.positiveDisplayLabel : item.activatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="entityName">
        ${item.entityName}
      </mjl:dt>
      <mjl:dt attribute="geoData">
        ${item.geoData}
      </mjl:dt>
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
      <mjl:dt attribute="geoMultiPolygon">
        ${item.geoMultiPolygon}
      </mjl:dt>
      <mjl:dt attribute="geoPoint">
        ${item.geoPoint}
      </mjl:dt>
      <mjl:dt attribute="term">
        ${item.term.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command localize="false" name="dss.vector.solutions.geo.generated.GeoEntity.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.geo.generated.GeoEntityController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.geo.generated.GeoEntity.viewAll.link" action="dss.vector.solutions.geo.generated.GeoEntityController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
