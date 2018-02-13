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
<c:set var="page_title" scope="request" value="Select Has Layer Participants"/>
<mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.id">
  <dl>
    <dt>
      <label>
        Map
      </label>
    </dt>
    <dd>
      <mjl:select valueAttribute="id" param="parentId" var="current" items="${parentList}">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label>
        Layer
      </label>
    </dt>
    <dd>
      <mjl:select valueAttribute="id" param="childId" var="current" items="${childList}">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.newInstance.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerController.newInstance.mojo" value="New Instance" />
  </dl>
</mjl:form>
