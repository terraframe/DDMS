<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Excel_Import_Header" scope="request" />

<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.generator.ExcelController.excelImport.mojo">
  <mdss:localize key="xls_file" />:: <br />
  <input type="hidden" name="type" value="${type}" />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
</form>
