<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>
      MDSS/trunk
    </title>
  </head>
  <body>
    <h1>Welcome to MDSS/trunk!</h1>
    <mjl:commandLink
      name="LoginController.logout"
      action="com.terraframe.mojo.defaults.LoginController.logout.mojo"
      display="Logout"/>
  </body>
</html>
