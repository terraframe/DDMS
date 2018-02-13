<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
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
      <mjl:dt attribute="entityLabel">
        ${item.entityLabel}
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
    <mjl:command name="dss.vector.solutions.geo.generated.GeoEntity.form.edit.button" value="Edit" action="dss.vector.solutions.geo.generated.GeoEntityController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.geo.generated.GeoEntity.viewAll.link" action="dss.vector.solutions.geo.generated.GeoEntityController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
