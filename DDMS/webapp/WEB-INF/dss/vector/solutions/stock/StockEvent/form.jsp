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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="cost">
    <mjl:input param="cost" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="eventDate" type="text" />
  <mjl:dt attribute="item">
    <mjl:select param="item" items="${item}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="otherParty">
    <mjl:input param="otherParty" type="text" />
  </mjl:dt>
  <mjl:dt attribute="quantity">
    <mjl:input param="quantity" type="text" />
  </mjl:dt>
  <mjl:dt attribute="staff">
    <mjl:select param="staff" items="${staff}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="stockDepot">
    <mdss:geo param="stockDepot" value="${item.stockDepot}" />
  </mjl:dt>
  <mjl:dt attribute="transactionType">
    <mjl:select param="transactionType" items="${transactionType}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.transactionTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.transactionTypeMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
