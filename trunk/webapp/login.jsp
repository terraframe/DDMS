<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" type="image/png" href="/favicon.png" />
<script type="text/javascript" charset="utf-8">

//Thanks for doing your part to liber(IE)t the world from IE6!
//Original copy written by Jonathan Howard (jon@StaringIsPolite.com)
//
//GNU LGPL License v3
//SevenUp is released into the wild under a GNU LGPL v3
//
//Browser sniffing technique lovingly adapted from http://www.thefutureoftheweb.com/
//Simple CSS Lightbox technique adapted equally lovingly from http://www.emanueleferonato.com/
//Go read their blogs :)

//Constructor technique advocated by Doug Crockford (of LSlint, JSON) in his recent Google tech talk.
var sevenUp = function () {
// Define private vars here.
	var downloadLink = "http://www.getfirefox.com";
	var is_firefox = /Firefox.3/i.test(navigator.userAgent); // is IE6??
	var overlayColor  = "#000000";  // Change these to fit your color scheme.
	var lightboxColor = "#ffffff";  // " "
	var borderColor   = "#ff0000";  // " "
// Hate to define CSS this way, but trying to keep to one file.
// I'll keep it as pretty as possible.
var overlayCSS =
 "display: block; position: absolute; top: 0%; left: 0%;" +
 "width: 100%; height: 100%; background-color: " + overlayColor + "; " +
 "z-index:1001; -moz-opacity: 0.8; opacity:.80; filter: alpha(opacity=80);";
var lightboxCSS =
 "display: block; position: absolute; top: 25%; left: 25%; width: 50%; " +
 "height: 50%; padding: 16px; border: 8px solid " + borderColor + "; " +
 "background-color:" + lightboxColor + "; " +
 "z-index:1002; overflow: auto;";
var lightboxContents =
 "<div style='width: 100%; height: 95%'>" +
   "<div style='text-align: center;'>" +
   "<div class='pageTitle'>Your Browser is Unsupported</div>" +
   "<br><br><br>" +
   "Firefox 3 is required for proper function of MDSS " +
   "<br><br><br>" +
   "Please <a style='color: #0000EE' href='" + downloadLink + "'>install Firefox 3</a>" +
   "</div>" +
 "</div>";
function isCookieSet() {
 if (document.cookie.length > 0) {
   var i = document.cookie.indexOf("sevenup=");
   return (i != -1);
 }
 return false;
}

return {  // Return object literal and public methods here.
	test: function(allowSkip) {
	  if (! is_firefox) {
	    // Write layer into the document.
	    var layerHTML =
	      "<div id='sevenUpOverlay' style='" + overlayCSS + "'>" +
	        "<div style='" + lightboxCSS + "'>" +
           lightboxContents +
         "</div>" +
		    "</div>";
     var layer = document.createElement('div');
     layer.innerHTML = layerHTML;
	    document.body.appendChild(layer);
	  }
	},
 setLightboxContents: function(newContents) {
   lightboxContents = newContents;
 },
 quitBuggingMe: function() {
   var exp = new Date();
   exp.setTime(exp.getTime()+(7*24*3600000));
   document.cookie = "sevenup=dontbugme; expires="+exp.toUTCString();
   this.close();
 },
 close: function() {
   var overlay = document.getElementById('sevenUpOverlay');
   if (overlay !== undefined) {
     overlay.style.display = 'none';
   }
 }
};
}();

  </script>
<title><fmt:message key="login" /></title>

<link rel="icon" type="image/png" href="./imgs/favicon.png">
<jwr:style src="/bundles/yuiStyle.css" />
<jwr:style src="/bundles/mdssScreen.css" />

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

<body onload="sevenUp.test()" class="yui-skin-sam">
<div id="freeText"><strong>National Malaria Control Program</strong><br />
MALAWI</div>
<div id="uploadLogo"><img src="./imgs/design/sample_logo.gif" width="85" height="57" /></div>
<div class="header">
<div class="hTitle"></div>
</div>
<div class="pageContent">
<div class="pageTitle"><fmt:message key="login" /></div>
<form method="post" action="com.terraframe.mojo.defaults.LoginController.login.mojo" name="mform" id="mform"><c:if test="${bad_password}">
  <div class="alert alertbox">
  <p>${exception.localizedMessage}</p>
  </div>
</c:if>


<dl>
  <dt><label> <fmt:message key="username" />: </label></dt>
  <dd><mjl:input param="username" type="text" value="SYSTEM" /></dd>
  <dt><label> <fmt:message key="password" />: </label></dt>
  <dd><mjl:input param="password" type="password" value="" /></dd>
</dl>

<div class="submitButton_bl"></div>
<input type="submit" value="Login" name="LoginController" id="submitLogin" class="submitButton" /></form>
<script type="text/javascript">

</script></div>
</body>
</html>