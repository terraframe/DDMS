<form id="<%= request.getParameter("excelType") %>.export" name="<%= request.getParameter("excelType") %>.export" action="excelexport" method="post">
  <input type="hidden" value="<%= request.getParameter("excelType") %>" name="type"/>
  <input type="submit" class="submitButton" name="export.button" value="Export Excel File"/>
</form>
<form id="<%= request.getParameter("excelType") %>.import" name="<%= request.getParameter("excelType") %>.import" action="excelimport" method="post">
  <input type="hidden" value="<%= request.getParameter("excelType") %>" name="type"/>
  <input type="submit" class="submitButton" name="import.button" value="Import Excel File"/>
</form>