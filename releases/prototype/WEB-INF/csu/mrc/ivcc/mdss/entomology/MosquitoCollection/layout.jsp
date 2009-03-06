<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
    <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />   
    <title>
      ${page_title}
    </title>
  </head>
  <body class="yui-skin-sam">
    <jsp:include page="/WEB-INF/templates/navMenu.jsp" />
    <jsp:include page="${jsp}" flush="false"  />
  </body>
</html>