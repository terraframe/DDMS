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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<br /><br />
<form id="<%= request.getParameter("excelType") %>.export" name="<%= request.getParameter("excelType") %>.export" action="excelexport" method="post">
  <input type="hidden" value="<%= request.getParameter("excelType") %>" name="excelType"/>
  <mdss:localize key="Excel_Export_Header" var="export_label"/>
  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
</form>
<form id="<%= request.getParameter("excelType") %>.import" name="<%= request.getParameter("excelType") %>.import" action="excelimport" method="post">
  <mdss:localize key="Excel_Import_Header" var="import_label"/>
  <input type="hidden" value="<%= request.getParameter("excelType") %>" name="excelType"/>
  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
</form>
<form id="<%= request.getParameter("excelType") %>.mobile" name="<%= request.getParameter("excelType") %>.mobile" action="mobile/export" method="get">
  <input type="hidden" value="<%= request.getParameter("excelType") %>" name="type"/>
  <mdss:localize key="Mobile_Export_Button" var="mobile_label"/>
  <input type="submit" class="mobileButton" name="mobile.button" value="${mobile_label}"/>
</form>