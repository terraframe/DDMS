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
<c:set var="page_title" value="View_All_Styles" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.query.StylesController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="fill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontFamily">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontStyle">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointMarker">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${item.pointMarkerEnumNames}">
            <li>
              ${item.pointMarkerMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonWidth">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.query.StylesController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink action="dss.vector.solutions.query.StylesController.newInstance.mojo" name="StylesController.newInstance">
  <mdss:localize key="Create_a_new_Styles" />
</mjl:commandLink>
