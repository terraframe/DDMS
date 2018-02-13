<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page isErrorPage="true"%>

<c:set var="page_title" value="welcome"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
  <div class="pageTitle">
    <mdss:localize key="welcome"/>
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

  <mdss:localize key="permission_configuration_error" /> <br>
    
  <img alt="Padlock" src="imgs/icons/Padlock.png" width="128" height="128">

</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />