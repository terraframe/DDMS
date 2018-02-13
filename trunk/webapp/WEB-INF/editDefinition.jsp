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

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.GeoHierarchy.form.name" id="dss.vector.solutions.geo.generated.GeoHierarchy.form.id" method="POST">
  <mjl:input type="hidden" param="geoHierarchyId" value="${geoHierarchyId}" />
  <dl>
    <mjl:component item="${view}" param="view">
      <mjl:input type="hidden" param="geoHierarchyId" />
      <mjl:input type="hidden" param="referenceId" />
      <dt>
        <label>
          ${definition.typeNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        ${definition.typeName}
      </dd>
      <dt>
        <label>
          ${definition.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.descriptionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="description" />
        <mjl:messages attribute="description">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
        ${definition.termMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mdss:mo param="term" value="${term}" script="false"/>
      </dd>
      <dt>
        <label>
          ${definition.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
        <mjl:messages attribute="political">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.populationAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="populationAllowed" />
      </dd>
      <dt>
        <label>
          ${definition.sprayTargetAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="sprayTargetAllowed" />
        <mjl:messages attribute="sprayTargetAllowed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.urbanMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="urban" />
        <mjl:messages attribute="urban">
          <mjl:message />
        </mjl:messages>
      </dd>
    </mjl:component>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.geo.GeoEntityTypeController.updateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.updateDefinition.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.geo.GeoEntityTypeController.cancelUpdateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.cancelUpdateDefinition.button" />
  </dl>
</mjl:form>