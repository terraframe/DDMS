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
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set scope="request" var="page_title" value="View_Transaction_Item" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:component item="${item}" param="ignore">
  <mjl:dt attribute="actionLabel">
    ${item.actionLabel}
  </mjl:dt>
  <mjl:dt attribute="componentId">
    ${item.componentId}
  </mjl:dt>
  <mjl:dt attribute="componentSeq">
    ${item.componentSeq}
  </mjl:dt>
  <mjl:dt attribute="componentSiteMaster">
    ${item.componentSiteMaster}
  </mjl:dt>
  <mjl:dt attribute="xmlRecord">
    <pre>${fn:escapeXml(item.xmlRecord)}</pre>
  </mjl:dt>
  </mjl:component>
  <mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.synchronization.TransactionController.viewItemPage.mojo">
    <mdss:localize key="View_Transaction_Record" />
    <mjl:property name="recordId" value="${item.recordId}"/>
  </mjl:commandLink>  
</dl>
