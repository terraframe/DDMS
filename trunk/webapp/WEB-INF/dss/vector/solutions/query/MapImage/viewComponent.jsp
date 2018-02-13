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
<c:set scope="request" var="page_title" value="View_MapImage" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.MapImage.form.id" name="dss.vector.solutions.query.MapImage.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="imageFilePath">
        ${item.imageFilePath}
      </mjl:dt>
      <mjl:dt attribute="imageName">
        ${item.imageName}
      </mjl:dt>
      <mjl:dt attribute="imageXPosition">
        ${item.imageXPosition}
      </mjl:dt>
      <mjl:dt attribute="imageYPosition">
        ${item.imageYPosition}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.MapImage.form.edit.button" value="Edit" action="dss.vector.solutions.query.MapImageController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.MapImage.viewAll.link" action="dss.vector.solutions.query.MapImageController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
