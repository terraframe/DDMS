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
<c:set var="page_title" value="View_Styles" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.Styles.form.name" id="dss.vector.solutions.query.Styles.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="fill">
        ${item.fill}
      </mjl:dt>
      <mjl:dt attribute="fontFamily">
        ${item.fontFamily}
      </mjl:dt>
      <mjl:dt attribute="fontSize">
        ${item.fontSize}
      </mjl:dt>
      <mjl:dt attribute="fontStyle">
        ${item.fontStyle}
      </mjl:dt>
      <mjl:dt attribute="pointMarker">
        <ul>
          <c:forEach var="enumName" items="${item.pointMarkerEnumNames}">
            <li>
              ${item.pointMarkerMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="pointStroke">
        ${item.pointStroke}
      </mjl:dt>
      <mjl:dt attribute="pointWidth">
        ${item.pointWidth}
      </mjl:dt>
      <mjl:dt attribute="polygonFill">
        ${item.polygonFill}
      </mjl:dt>
      <mjl:dt attribute="polygonStroke">
        ${item.polygonStroke}
      </mjl:dt>
      <mjl:dt attribute="polygonWidth">
        ${item.polygonWidth}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.StylesController.edit.mojo" name="dss.vector.solutions.query.Styles.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.StylesController.viewAll.mojo" name="dss.vector.solutions.query.Styles.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
