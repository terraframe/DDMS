<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<c:choose>
  <c:when test="${empty problems}">
    <div class="pageTitle"><fmt:message key="Excel_Import_Success" /></div>
  </c:when>
  <c:otherwise>
    <div class="pageTitle"><fmt:message key="Excel_Import_Fail" /></div>
    <table class="displayTable">
      <tr>
        <th><fmt:message key="Excel_Row" /></th>
        <th><fmt:message key="Excel_Column" /></th>
        <th><fmt:message key="Excel_Error" /></th>
      </tr>
      <% boolean altRow = true; %>
      <c:forEach var="problem" items="${problems}">
        <% altRow = !altRow; %>
        <tr <% if (altRow) out.print("class=\"evenRow\""); else out.print("class=\"oddRow\""); %>>
          <td>${problem.rowNumber}</td>
          <td>${problem.column}</td>
          <td>${problem.message}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/templates/footer.jsp" />