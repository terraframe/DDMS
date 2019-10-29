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
  <mjl:dt attribute="reportLabel">
    <mjl:input param="reportLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="outputFormat">
    <mjl:select param="outputFormat" items="${outputFormat}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.outputFormatEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.outputFormatMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="cacheDocument">
    <mjl:boolean param="cacheDocument"/>
  </mjl:dt>
  <mjl:dt attribute="reportName">
    ${item.reportName}
  </mjl:dt>  
</mjl:component>
<dt>
  <label>* ${item.designMd.displayLabel}</label>
</dt>
<dd>
  <mjl:input param="design" type="file" />
  <mjl:messages attribute="design">
    <mjl:message />
  </mjl:messages>  
</dd>
