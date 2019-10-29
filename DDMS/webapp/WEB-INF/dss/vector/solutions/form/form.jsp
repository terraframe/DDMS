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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%
  
%>

<mjl:component param="form" item="${form}">
    <c:if test="${form.formNameReadable}">
      <li>
        <label>${form.formNameMd.displayLabel}</label>
        <mjl:input value="${form.formName}" type="text" param="formName" />
      </li>
    </c:if>
    <c:if test="${form.displayLabelReadable}">
      <li>
        <label>${form.displayLabelMd.displayLabel}</label>
        <mjl:input value="${form.displayLabel}" type="text" param="displayLabel" />
      </li>
    </c:if>
</mjl:component>