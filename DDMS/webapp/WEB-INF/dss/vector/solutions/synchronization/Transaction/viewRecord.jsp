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
<c:set scope="request" var="page_title" value="View_Transaction_Record" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:component item="${record}" param="ingore">
  <mjl:dt attribute="createDate">
    <span class="formatDate"> ${record.createDate} </span>
  </mjl:dt>
  <mjl:dt attribute="exportSequence">
    ${record.exportSequence}
  </mjl:dt>
  <mjl:dt attribute="siteMaster">
    ${record.siteMaster}
  </mjl:dt>
  </mjl:component>
  <dt> <label><mdss:localize key="Transaction_items" /> </label></dt>
  <dd>
<mjl:table classes="displayTable" var="current" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.TransactionController.viewItemPage.mojo" >
    <mjl:property name="recordId" value="${record.id}"/>
  </mjl:context>
  <mjl:columns>
    <mjl:attributeColumn attributeName="actionLabel"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentSeq"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentId"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentSiteMaster"></mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.synchronization.TransactionController.viewItem.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${current.itemId}" />
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
</dd>
  <mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.synchronization.TransactionController.viewRecordPage.mojo">
    <mdss:localize key="View_All_Transaction_Records" />
  </mjl:commandLink>  
</dl>
