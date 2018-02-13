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
<jsp:include page="/WEB-INF/inlineError.jsp" />

<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.GeoHierarchy.form.name" id="dss.vector.solutions.geo.generated.GeoHierarchy.form.id" method="POST">
  <mjl:input type="hidden" param="geoHierarchyId" value="${geoHierarchyId}" />
  <mjl:component item="${view}" param="view">
    <mjl:input type="hidden" param="geoHierarchyId" />
    <mjl:input type="hidden" param="referenceId" />
    <dl>
      <dt>
        ${view.displayLabelMd.displayLabel}
      </dt>
      <dd>
        ${view.displayLabel}
      </dd>
      <dt>
        ${view.descriptionMd.displayLabel}
      </dt>
      <dd>
        ${view.description}
      </dd>
      <dt>
        ${view.politicalMd.displayLabel}
      </dt>
      <dd>
        ${view.political}
      </dd>
  </mjl:component>
    </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.geo.GeoEntityTypeController.editDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.editDefinition.button" />
</mjl:form>