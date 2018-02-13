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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="config">
    <mjl:input param="config" type="text" />
  </mjl:dt>
  <mjl:dt attribute="csvFile">
    <mjl:input param="csvFile" type="text" />
  </mjl:dt>
  <mjl:dt attribute="disease">
    <mjl:select param="disease" items="${_disease}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="mappable">
    <mjl:boolean param="mappable" />
  </mjl:dt>
  <mjl:dt attribute="queryName">
    <mjl:input param="queryName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryType">
    <mjl:input param="queryType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryXml">
    <mjl:input param="queryXml" type="text" />
  </mjl:dt>
  <mjl:dt attribute="templateFile">
    <mjl:input param="templateFile" type="text" />
  </mjl:dt>
</mjl:component>
