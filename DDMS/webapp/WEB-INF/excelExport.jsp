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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageTitle"><mdss:localize key="Excel_Export_Header" /></div>
<table class="displayTable">
  <tr>
    <th><mdss:localize key="Excel_Class_Label" /></th>
    <th><mdss:localize key="Excel_Qualified_Classname" /></th>
    <th/>
  <tr>
  
  <% boolean altRow = true; %>
  <c:forEach var="class" items="${classes}">
    <% altRow = !altRow; %>
    <tr <% if (altRow) out.print("class=\"evenRow\""); else out.print("class=\"oddRow\""); %>>
    <td>${class.displayLabel}</td>
    <td>${class.packageName}.${class.typeName}</td>
    <td>
      <form method="post" action="excelexport">
        <input type="hidden" name="excelType" value="${class.packageName}.${class.typeName}"/>
        <input class="submitButton" type="submit" value="Export" style="margin-left: 0px; top: 0px;" />
      </form>
    </td>
  </tr>
  </c:forEach>
  
  </tr>
</table>
<jsp:include page="/WEB-INF/templates/footer.jsp" />