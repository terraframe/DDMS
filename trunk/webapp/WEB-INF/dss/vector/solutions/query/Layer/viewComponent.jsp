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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_Layer" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.Layer.form.name" id="dss.vector.solutions.query.Layer.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="defaultStyles">
        ${item.defaultStyles.id}
      </mjl:dt>
      <mjl:dt attribute="geoHierarchy">
        ${item.geoHierarchy.id}
      </mjl:dt>
      <mjl:dt attribute="layerName">
        ${item.layerName}
      </mjl:dt>
      <mjl:dt attribute="savedSearch">
        ${item.savedSearch.id}
      </mjl:dt>
      <mjl:dt attribute="sldFile">
        ${item.sldFile}
      </mjl:dt>
      <mjl:dt attribute="thematicVariable">
        ${item.thematicVariable.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="viewCreated">
        ${item.viewCreated ? item.viewCreatedMd.positiveDisplayLabel : item.viewCreatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="viewName">
        ${item.viewName}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.LayerController.edit.mojo" name="dss.vector.solutions.query.Layer.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.LayerController.viewAll.mojo" name="dss.vector.solutions.query.Layer.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
