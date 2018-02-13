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
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.GeoHierarchy.form.name" id="dss.vector.solutions.geo.GeoHierarchy.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.geoEntityClassMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink action="com.runwaysdk.system.metadata.MdBusinessController.view.mojo" name="com.runwaysdk.system.metadata.MdBusiness.form.view.link">
        <mjl:property value="${item.geoEntityClass.id}" name="id" />
        ${item.geoEntityClass.keyName}        
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.politicalMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.political}
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.geo.GeoHierarchyController.edit.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink action="dss.vector.solutions.geo.AllowedInController.parentQuery.mojo" name="dss.vector.solutions.geo.AllowedIn.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink action="dss.vector.solutions.geo.AllowedInController.childQuery.mojo" name="dss.vector.solutions.geo.AllowedIn.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.viewAll.mojo" name="dss.vector.solutions.geo.GeoHierarchy.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
