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
<c:set scope="request" var="page_title" value="View_All_Exported_Types" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.ExportController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="qualifiedType"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description"></mjl:attributeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />

<mjl:form name="form.name" id="form.id" method="GET">
  <dl>
    <dt> 
      <mdss:localize key="Search_for_type_to_export" />
    </dt>
    <dd>
      <mjl:input type="text" param="query"/>
    </dd>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command name="ExportController.search" action="dss.vector.solutions.synchronization.ExportController.search.mojo" value="${Localized_Search}" />
  </dl>
  
</mjl:form>
