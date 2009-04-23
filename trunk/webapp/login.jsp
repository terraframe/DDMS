<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib
  prefix="c"
  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
  uri="http://java.sun.com/jstl/fmt"
  prefix="fmt"%>
<%@ taglib
  uri="/WEB-INF/tlds/mojoLib.tld"
  prefix="mjl"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta
  http-equiv="Content-Type"
  content="text/html; charset=UTF-8" />
<link
  rel="icon"
  type="image/png"
  href="/favicon.png" />
<script
  type="text/javascript"
  src="js/check_for_firefox.js"
  charset="utf-8"></script>
<title><fmt:message key="login" /></title>

<link rel="icon" type="image/png" href="./imgs/favicon.png" >
<link rel="stylesheet" type="text/css" href="js/yui/build/reset-fonts-grids/reset-fonts-grids.css">
<link href="css/style.css" rel="stylesheet" type="text/css">


<style type="text/css">
input[type="button"],input[type="submit"] {
	display: inline;
	background: F00;
	height: 39px;
	background-position: right top;
	font-size: 18px;
	font-weight: bold;
	color: #FFFFFF;
	line-height: 30px;
	text-decoration: none;
	margin-left: -13px;
    margin-top: 13px;
	text-shadow: 0 0 0 #000;
	border-width: 0px;
	width: 130px;
	padding: 0px;
	z-index: 3;
}

.submitButton:hover {
	background-image: url(/MDSS/imgs/submitButtonBackOver.gif);
}

.submitButton {
	display: inline;
	background-image: url(/MDSS/imgs/submitButtonBack.gif);
	background-repeat: repeat-x;
	height: 39px;
	background-position: right top;
	font-size: 18px;
	font-weight: bold;
	color: #FFFFFF;
	line-height: 60px;
	text-decoration: none;
	margin-left: -13px;
	text-shadow: 0 0 0 #000;
    margin-top: 13px;
}

input[type="button"]:hover {
	border: 0px
}

input[type="button"]:active {
	border: 0px
}

.submitButton:hover {
	background-image: url(/MDSS/imgs/submitButtonBackOver.gif);
}

.submitButton_bl {
	width: 24px;
	background-image: url(/MDSS/imgs/submitButtonLeft.gif);
	height: 39px;
	float: left;
	z-index: 4;
	position: relative;
	left: 11px;
   margin-top: 13px;
}
</style>
</head>

<body
  onload="sevenUp.test()"
  class="yui-skin-sam">
<div id="freeText"><strong>National Malaria Control Program</strong><br />MALAWI</div>
<div id="uploadLogo"><img
  src="./imgs/design/sample_logo.gif"
  width="85"
  height="57" /></div>
<div class="header">
<div class="hTitle"></div>
</div>
<div class="pageContent">
<div class="pageTitle"><fmt:message key="login" /></div>
<form method="post" action="com.terraframe.mojo.defaults.LoginController.login.mojo" name="mform" id="mform" >


  <c:if test="${bad_password}">
    <div class="alert alertbox">
    <p>
    ${exception.localizedMessage}
    </p>
    </div>
  </c:if>


  <dl>
    <dt><label> <fmt:message key="username" />: </label></dt>
    <dd><mjl:input
      param="username"
      type="text"
      value="SYSTEM" /></dd>
    <dt><label> <fmt:message key="password" />: </label></dt>
    <dd><mjl:input
      param="password"
      type="password"
      value="" /></dd>
  </dl>

  <div class="submitButton_bl"></div>
  <input type="submit" value="Login" name="LoginController" id="submitLogin" class="submitButton"/>



</form>
<script type="text/javascript">

</script>
</div>
</body>
</html>


