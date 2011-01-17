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