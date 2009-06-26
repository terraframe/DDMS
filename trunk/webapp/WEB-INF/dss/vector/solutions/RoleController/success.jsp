<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Roles_Success" scope="request"/>
<fmt:message key="Roles_Manage_Another_User" var="manage"/>
<mjl:commandLink display="${manage}" name="dss.vector.solutions.RoleController.viewAll.mojo" action="dss.vector.solutions.RoleController.viewAll.mojo"/>