<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<!-- Tell Runway what the application context path is. -->
<script>
window.com = window.com || {};
window.com.runwaysdk = window.com.runwaysdk || {};
window.com.runwaysdk.__applicationContextPath = "<%=request.getContextPath()%>";
</script>

<%@ include file="/WEB-INF/templates/banner.jsp"%>

<jsp:include page="/WEB-INF/templates/navMenu.jsp" />
