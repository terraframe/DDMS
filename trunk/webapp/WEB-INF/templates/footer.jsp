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
</body>




</html>

