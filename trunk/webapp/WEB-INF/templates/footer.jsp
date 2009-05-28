<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.terraframe.mojo.system.UsersDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="dss.vector.solutions.util.ErrorUtility"%>
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
    %> <fmt:message key="logged_in_as" />: <%
    //out.print(user.getUsername());
    out.print(clientRequest.getSessionUser());
}
else
{
 out.print("Not Logged In");
}

%>
<span id="loadTime"></span>
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

<script type="text/javascript">
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

  var prevPagePostTime = readCookie('pagepostTime');
  var loadtime = Date.now() - parseInt(prevPagePostTime);
  loadtime = loadtime / 1000.0;
  document.getElementById('loadTime').innerHTML = loadtime;
  //the server can log this on the next request
  document.cookie = "PrevLoadTime=" + loadtime +"@"+ document.location + "; path=/";
});
</script>
</body>
</html>