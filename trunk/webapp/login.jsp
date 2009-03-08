<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="/favicon.png"/>
<title><f:message key="login"/></title>
<jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />
</head>
<body class="yui-skin-sam">

<div id="freeText"><strong>National Malaria Control Program</strong><br /> MALAWI</div>
<div id="uploadLogo"><img src="./imgs/design/sample_logo.gif" width="85" height="57" /></div>
<div class="header">
<div class="hTitle"></div>
</div>
<div class="pageContent">
<div class="pageTitle"><f:message key="login"/></div>


<mjl:form method="post" id="mform" name="mform">  
  <f:message key="username"/>: <mjl:input param="username" type="text" value="SYSTEM" />
  <f:message key="password"/>: <mjl:input param="password" type="password" value="SYSTEM" />
  <br/>
  <mjl:command action="com.terraframe.mojo.defaults.LoginController.login.mojo" name="LoginController" value="Login"/>
</mjl:form>
</div>

</body>
</html>
