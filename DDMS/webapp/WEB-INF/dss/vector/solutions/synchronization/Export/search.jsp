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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Add_New_Export_Types" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="ExportController.name" id="ExportController.form.id" method="POST">
  <dl>
<mjl:table classes="displayTable" var="item" query="${results}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.ExportController.search.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="qualifiedType"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description"></mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        <mdss:localize key="Export" />
      </mjl:header>
      <mjl:row>
        <mjl:input type="checkbox" param="types" value="${item.mdTypeId}"/>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
    <mdss:localize key="checkDependencies" var="Localized_checkDependencies" />
    <mjl:command value="${Localized_checkDependencies}" action="dss.vector.solutions.synchronization.ExportController.checkDependencies.mojo" name="ExportController.form.checkDependencies.button" />
  </dl>
</mjl:form>
