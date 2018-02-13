<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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