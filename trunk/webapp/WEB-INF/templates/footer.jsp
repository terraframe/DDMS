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
if(clientRequest != null && clientRequest.isLoggedIn())
{
    %> <mdss:localize key="logged_in_as" />: <%
    //out.print(user.getUsername());
    out.print(clientRequest.getSessionUser()); %> (&lrm;${diseaseLabel})&lrm; <%
}
else
{
 //out.print("Not Logged In");
}
%>

</div>

 ${add_to_footer}

</body>
</html>