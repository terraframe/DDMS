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
  <mjl:dt attribute="bccAddresses">
    <mjl:input param="bccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="body">
    <mjl:input param="body" type="text" />
  </mjl:dt>
  <mjl:dt attribute="ccAddresses">
    <mjl:input param="ccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="error">
    <mjl:input param="error" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fromAddress">
    <mjl:input param="fromAddress" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="sentDate" type="text" />
  <mjl:dt attribute="subject">
    <mjl:input param="subject" type="text" />
  </mjl:dt>
  <mjl:dt attribute="toAddresses">
    <mjl:input param="toAddresses" type="text" />
  </mjl:dt>
</mjl:component>
