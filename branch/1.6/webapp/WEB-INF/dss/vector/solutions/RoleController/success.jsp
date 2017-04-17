<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Roles_Success" scope="request"/>
<mjl:commandLink name="dss.vector.solutions.RoleController.viewAll.mojo" action="dss.vector.solutions.RoleController.viewAll.mojo">
  <mdss:localize key="Roles_Manage_Another_User"/>
</mjl:commandLink>