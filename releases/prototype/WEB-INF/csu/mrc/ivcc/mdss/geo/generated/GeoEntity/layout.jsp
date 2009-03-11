<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle">${page_title}</div>
<jsp:include page="/WEB-INF/inlineError.jsp" />
<jsp:include page="${jsp}" flush="false" />
</div>


<jsp:include page="/WEB-INF/templates/footer.jsp" />
