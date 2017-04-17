<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
  <c:when test="${!(empty problems)}">
    <c:set var="page_title" value="Excel_Import_Fail"  scope="request"/>
  </c:when>
  <c:when test="${!(empty errorMessage)}">
    <c:set var="page_title" value="Excel_Import_Fail"  scope="request"/>
  </c:when>
  <c:otherwise>
    <c:set var="page_title" value="Excel_Import_Success"  scope="request"/>
  </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<c:choose>
  <c:when test="${!(empty problems)}">
    <div class="pageTitle"><mdss:localize key="Excel_Import_Fail" /></div>
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
  </c:when>
  <c:when test="${not empty errorMessage}">
    <div class="pageTitle"><mdss:localize key="Excel_Import_Fail" /></div>
    <jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
  </c:when>
  <c:otherwise>
    <div class="pageTitle"><mdss:localize key="Excel_Import_Success" /></div>
  </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/templates/footer.jsp" />