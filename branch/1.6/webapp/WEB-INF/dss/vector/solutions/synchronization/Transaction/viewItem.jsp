<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set scope="request" var="page_title" value="View_Transaction_Item" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:component item="${item}" param="ignore">
  <mjl:dt attribute="actionLabel">
    ${item.actionLabel}
  </mjl:dt>
  <mjl:dt attribute="componentId">
    ${item.componentId}
  </mjl:dt>
  <mjl:dt attribute="componentSeq">
    ${item.componentSeq}
  </mjl:dt>
  <mjl:dt attribute="componentSiteMaster">
    ${item.componentSiteMaster}
  </mjl:dt>
  <mjl:dt attribute="xmlRecord">
    <pre>${fn:escapeXml(item.xmlRecord)}</pre>
  </mjl:dt>
  </mjl:component>
  <mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.synchronization.TransactionController.viewItemPage.mojo">
    <mdss:localize key="View_Transaction_Record" />
    <mjl:property name="recordId" value="${item.recordId}"/>
  </mjl:commandLink>  
</dl>
