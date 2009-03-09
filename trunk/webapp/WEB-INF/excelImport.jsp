<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageTitle"><f:message key="Excel_Import_Header" /></div>
<form method="post" enctype="multipart/form-data" action="excelimport">
  XLS File: <br />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
</form>
<jsp:include page="/WEB-INF/templates/footer.jsp" />