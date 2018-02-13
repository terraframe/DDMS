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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Excel_Import_Fail" scope="request" />

<table class="displayTable">
  <tr>
    <th><mdss:localize key="Excel_Row" /></th>
    <th><mdss:localize key="Excel_Column" /></th>
    <th><mdss:localize key="Excel_Error" /></th>
  </tr>
  <% boolean altRow = true; %>
  <c:forEach var="problem" items="${problems}">
    <% altRow = !altRow; %>
    <tr <% if (altRow) out.print("class=\"evenRow\""); else out.print("class=\"oddRow\""); %>>
      <td>${problem.row}</td>
      <td>${problem.column}</td>
      <td>${problem.message}</td>
    </tr>
  </c:forEach>
</table>
