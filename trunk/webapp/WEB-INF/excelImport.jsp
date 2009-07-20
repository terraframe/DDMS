<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><fmt:message key="Excel_Import_Header" /></div>
<div class="fldContainer">
    <div class="fcTop">
    
    <form method="post" enctype="multipart/form-data" action="excelimport">
  XLS File: <br />
  <input type="hidden" name="excelType" value="${excelType}" />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
</form>
    
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    </div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />
</div>