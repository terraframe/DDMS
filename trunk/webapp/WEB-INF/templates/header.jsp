<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/templates/banner.jsp"%>

<c:set var="disease" value="dengue"></c:set>
<jsp:include page="/WEB-INF/disease/${disease}/navMenu.jsp" />
