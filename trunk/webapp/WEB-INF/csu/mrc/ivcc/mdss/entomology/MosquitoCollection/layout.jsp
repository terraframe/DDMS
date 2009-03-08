<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle">${page_title}</div>
<jsp:include page="/WEB-INF/inlineError.jsp" />
<jsp:include page="${jsp}" flush="false" />
</div>


<jsp:include page="/WEB-INF/templates/footer.jsp" />