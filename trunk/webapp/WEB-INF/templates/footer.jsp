<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.runwaysdk.system.UsersDTO"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="dss.vector.solutions.util.ErrorUtility"%>
<%@page import="java.util.Date"%>
<div class="pageFooter">

<!--  <a href="#">Administration</a>
| <a href="#">Intervention Planning</a>
|<a href="#"> Entomology Surveillance</a>
| <a href="#">Case Surveillance</a>
| <a href="#">Intervention Monitoring</a>
| <a href="#">Malaria Indicators</a>
| <a href="#">GIS</a> -->

<%
//UsersDTO user =(UsersDTO) session.getAttribute("MOJO_CurrentUser");
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
if(clientRequest.isLoggedIn())
{
    %> <mdss:localize key="logged_in_as" />: <%
    //out.print(user.getUsername());
    out.print(clientRequest.getSessionUser());
}
else
{
 out.print("Not Logged In");
}
//load time stats
Long startTime = (Long) request.getAttribute("startTime");
Double serverTime = (new Date().getTime() - startTime) / 1000.0;
request.setAttribute("serverTime",serverTime);
%>
(${diseaseName})
<br>
ST=<fmt:formatNumber maxFractionDigits="2" value="${serverTime}"/>
CT=<span id="clientTime"></span>
TT=<span id="loadTime"></span>
</div>

<c:choose>
  <c:when test="${fn:contains(pageContext.request.requestURL,'localhost') or fn:contains(pageContext.request.requestURL,'127.0.0.1')}">
     <pre>
      ${developerMessage}
    </pre>
  </c:when>
  <c:otherwise>
    <pre style="color:#FFFFFF">
      ${developerMessage}
    </pre>
  </c:otherwise>
</c:choose>

 ${add_to_footer}

<script type="text/javascript" defer="defer">
YAHOO.util.Event.addListener(window, 'unload', function()
{
  document.cookie = "pagepostTime=" + Date.now() + "; path=/";
});
YAHOO.util.Event.addListener(window, 'load', function()
{
  function readCookie(name)
  {
    var ca = document.cookie.split(';');
    var nameEQ = name + "=";
    for(var i=0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0)===' ') { c = c.substring(1, c.length); }
      if (c.indexOf(nameEQ) === 0) { return c.substring(nameEQ.length, c.length); }
    }
    return null;
  }
  var serverTime = <fmt:formatNumber maxFractionDigits="2" value="${serverTime}"/>;
  var prevPagePostTime = readCookie('pagepostTime');
  var loadtime = (Date.now() - parseInt(prevPagePostTime,10)) / 1000.0;
  var clientTime = loadtime - serverTime;
  document.getElementById('loadTime').innerHTML = loadtime.toFixed(2);
  document.getElementById('clientTime').innerHTML = clientTime.toFixed(2);
  //the server can log this on the next request
  document.cookie = "PrevLoadTime=CT" + clientTime.toFixed(2) +"ST" + serverTime + escape(document.location) + "; path=/";
});
</script>
</body>
</html>