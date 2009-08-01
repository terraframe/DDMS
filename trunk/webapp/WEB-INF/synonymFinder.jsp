<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<c:choose>
  <c:when test="${!(empty errorMessage)}">
    <div class="pageTitle"><fmt:message key="Synonym_Finder_Fail" /></div>
    ${errorMessage}
  </c:when>
  <c:otherwise>
    <div class="pageTitle"><fmt:message key="Synonym_Finder" />
      <c:forEach var="unknownGeoEntity" items="${unknownGeoEntitys}">

      </c:forEach>
    </div>
  </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/templates/footer.jsp" />