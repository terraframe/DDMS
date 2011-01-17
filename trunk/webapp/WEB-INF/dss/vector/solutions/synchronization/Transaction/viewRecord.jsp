<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_Transaction_Record" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:component item="${record}" param="ingore">
  <mjl:dt attribute="createDate">
    <span class="formatDate"> ${record.createDate} </span>
  </mjl:dt>
  <mjl:dt attribute="exportSequence">
    ${record.exportSequence}
  </mjl:dt>
  <mjl:dt attribute="siteMaster">
    ${record.siteMaster}
  </mjl:dt>
  </mjl:component>
  <dt> <label><mdss:localize key="Transaction_items" /> </label></dt>
  <dd>
<mjl:table classes="displayTable" var="current" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.TransactionController.viewItemPage.mojo" >
    <mjl:property name="recordId" value="${record.id}"/>
  </mjl:context>
  <mjl:columns>
    <mjl:attributeColumn attributeName="actionLabel"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentSeq"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentId"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="componentSiteMaster"></mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.synchronization.TransactionController.viewItem.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${current.itemId}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
</dd>
  <mjl:commandLink name="IndividualIPTController.newInstance" action="dss.vector.solutions.synchronization.TransactionController.viewRecordPage.mojo">
    <mdss:localize key="View_All_Transaction_Records" />
  </mjl:commandLink>  
</dl>
