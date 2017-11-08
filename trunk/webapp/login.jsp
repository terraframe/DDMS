<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>

<c:set var="page_title" value=""  scope="request"/>
<c:set var="window_title" value="login"  scope="request"/>

<!-- Tell Runway what the application context path is. -->
<script>
window.com = window.com || {};
window.com.runwaysdk = window.com.runwaysdk || {};
window.com.runwaysdk.__applicationContextPath = "<%=request.getContextPath()%>";
</script>

<jsp:include page="/WEB-INF/templates/banner.jsp" />

<link rel="stylesheet" href="css/login.css">

<div class="pageContent">

  <div class="sectionLeft">
    <div class="titleDiv">
      <span class="title1"><mdss:localize key="login_ddms" /></span>
      <span class="title2"><mdss:localize key="login_versionName" /></span>
    </div>
    
    <jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
    
    <form class="loginForm" method="post" action="${pageContext.request.contextPath}/com.runwaysdk.defaults.LoginController.login.mojo" name="mform" id="mform">
      <c:if test="${bad_password}">
        <div class="alert alertbox">
          <p>${exception.localizedMessage}</p>
        </div>
      </c:if>
      
      
      <dl class="login">
        <dt><label> <mdss:localize key="username" />: </label></dt>
        <dd><mjl:input param="username" type="text" /></dd>
        <dt><label> <mdss:localize key="password" />: </label></dt>
        <dd><mjl:input param="password" type="password" value="" /></dd>
      </dl>
      
      <div class="submitButton_bl"></div>
      <input type="submit" value="<mdss:localize key="Login" />" name="LoginController" id="submitLogin" class="submitButton" />
    </form>
  </div>

  <img class="loginMosquito" src="imgs/data-mozzie.png"/>

</div>

<mdss:localize key="unsupported_browser_header" var="unsupported_browser_header" />
<mdss:localize key="unsupported_browser_contents" var="unsupported_browser_contents" />

<script type="text/javascript" charset="utf-8">
var checkForFF = function () {
// Define private vars here.
  var downloadLink = "http://www.getfirefox.com";
  var agent = navigator.userAgent;
  var is_firefox = /Firefox.3\.(5|6)/i.test(agent) || /Shiretoko.3/i.test(agent); // is IE6??
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
   "<div class='pageTitle'>${unsupported_browser_header}</div>" +
   "<br><br><br>" +
   "${unsupported_browser_contents}" +
   "<br><br><br>" +
   "<a style='color: #0000EE' href='" + downloadLink + "'>" + downloadLink + "</a>" +
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
 }
};
}();

YAHOO.util.Event.onDOMReady(checkForFF);
</script>

<jsp:include page="/WEB-INF/templates/footer.jsp" />