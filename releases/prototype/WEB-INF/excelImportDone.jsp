<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<c:choose>
  <c:when test="${empty problems}">
    <div class="pageTitle"><f:message key="Excel_Import_Success" /></div>
  </c:when>
  <c:otherwise>
    <div class="pageTitle"><f:message key="Excel_Import_Fail" /></div>
    <table class="displayTable">
      <tr>
        <th><f:message key="Excel_Row" /></th>
        <th><f:message key="Excel_Column" /></th>
        <th><f:message key="Excel_Error" /></th>
      </tr>
      <% boolean altRow = true; %>
      <c:forEach var="problem" items="${problems}">
        <% altRow = !altRow; %>
        <tr <% if (altRow) out.print("class=\"altRow\""); %>>
          <td>${problem.rowNumber}</td>
          <td>${problem.column}</td>
          <td>${problem.message}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/templates/footer.jsp" />