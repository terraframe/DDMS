<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login to MDSS</title>
<body>
<h3>Log in to MDSS</h3>
<mjl:form method="POST" id="mform" name="mform">  
  Username: <mjl:input param="username" type="text" value="SYSTEM" />
  Password: <mjl:input param="password" type="password" value="SYSTEM" />
  <br />
  <mjl:command action="com.terraframe.mojo.defaults.LoginController.login.mojo" name="LoginController" value="Login"/>
</mjl:form>
</body>
</html>
