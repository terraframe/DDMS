<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page isErrorPage="true"%>

<c:set var="page_title" value="welcome"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
  <div class="pageTitle">
    <fmt:message key="welcome"/>
  </div>

  <jsp:include page="/WEB-INF/inlineError.jsp" />
  
  <div class="alert alertbox">
    <p>
      <%
        if(exception != null) 
        {
          out.print(exception.getLocalizedMessage());
        }
      %>
    </p>
  </div>  

  <fmt:message key="permission_configuration_error" /> <br>
    
  <img alt="Padlock" src="imgs/icons/Padlock.png" width="128" height="128">

</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />