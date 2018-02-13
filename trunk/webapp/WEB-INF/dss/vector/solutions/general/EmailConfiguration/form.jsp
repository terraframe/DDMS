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
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="emailServer">
    <mjl:input param="emailServer" type="text" />
  </mjl:dt>
  <mjl:dt attribute="protocol">
    <mjl:select param="protocol" items="${protocol}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.protocolEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.protocolMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="emailUserid">
    <mjl:input param="emailUserid" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailPassword">
    <mjl:input param="emailPassword" type="text" />
  </mjl:dt>
  <mjl:dt attribute="retry">
    <mjl:input param="retry" type="text" />
  </mjl:dt>
  <mjl:dt attribute="timeout">
    <mjl:input param="timeout" type="text" />
  </mjl:dt>
</mjl:component>
