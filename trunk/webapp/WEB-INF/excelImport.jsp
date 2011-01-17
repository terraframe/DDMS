<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Excel_Import_Header"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><mdss:localize key="Excel_Import_Header" /></div>
<jsp:include page="/WEB-INF/inlineError.jsp" />
<div class="fldContainer">
    <div class="fcTop">
    
    <form method="post" enctype="multipart/form-data" action="excelimport">
  <mdss:localize key="xls_file" />:: <br />
  <input type="hidden" name="excelType" value="${excelType}" />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
</form>
    
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    </div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />
</div>