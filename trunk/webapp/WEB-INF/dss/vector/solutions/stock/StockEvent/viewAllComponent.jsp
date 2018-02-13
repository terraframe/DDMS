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
<c:set scope="request" var="page_title" value="View_All_Stock_Transactions" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="current" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.stock.StockEventController.getPage.mojo" >
    <mjl:property name="item" value="${item}"/>
    <mjl:property name="geoId" value="${geoId}"/>
    <mjl:property name="startDate" value="${startDate}"/>
    <mjl:property name="endDate" value="${endDate}"/>
  </mjl:context>  
  <mjl:columns>
    <mjl:attributeColumn attributeName="eventDate">
      <mjl:row>
        <span class="formatDate">${current.eventDate}</span>
      </mjl:row>
    </mjl:attributeColumn>  
    <mjl:attributeColumn attributeName="itemLabel" >
      <mjl:row>
        ${current.itemLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="staffLabel" >
      <mjl:row>
        ${current.staffLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="otherParty" >
      <mjl:row>
        ${current.otherParty}
      </mjl:row>
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="transactionType">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${current.transactionTypeEnumNames}">
            ${current.transactionTypeMd.enumItems[enumName]}
          </c:forEach>        
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantity" />
    <mjl:attributeColumn attributeName="cost">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${current.cost}" maxFractionDigits="2" />
      </mjl:row>
    </mjl:attributeColumn>        
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.stock.StockEventController.search.mojo" name="search.link" >
    <mjl:property name="geoId" value="${geoId}"/>
    <mjl:property name="itemId" value="${item}"/>
    <mjl:property name="date" value="${startDate}"/>    
    <mjl:property name="endDate" value="${endDate}"/>    
    <mdss:localize key="Back_To_Search"/>
  </mjl:commandLink>
</span>
