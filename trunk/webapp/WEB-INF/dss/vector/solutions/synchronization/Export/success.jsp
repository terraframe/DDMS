<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Exported_Type_Success" />

<mjl:commandLink name="ExportController.viewAll" action="dss.vector.solutions.synchronization.ExportController.viewAll.mojo">
  <fmt:message key="View_All_Exported_Types" />
</mjl:commandLink>