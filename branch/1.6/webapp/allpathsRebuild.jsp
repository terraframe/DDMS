<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="allpaths_rebuild_title"  scope="request"/>

<mdss:localize key="allpaths_rebuild_message" var="allpaths_rebuild_message" />
<mdss:localize key="allpaths_rebuild_title" var="allpaths_rebuild_title" />

<jsp:include page="/WEB-INF/templates/banner.jsp" />
<div class="pageContent">
<div class="pageTitle">${allpaths_rebuild_title}</div>
  <div class="alert alertbox">
  <p>
  ${allpaths_rebuild_message}
  </p>
  </div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />