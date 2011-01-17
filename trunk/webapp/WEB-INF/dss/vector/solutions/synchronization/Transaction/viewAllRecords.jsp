<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_Transaction_Records" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.TransactionController.viewRecordPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="createDate">
      <mjl:row>
        <span class="formatDate"> ${item.createDate} </span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="exportSequence"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="siteMaster"></mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header></mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.synchronization.TransactionController.viewItemPage.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.id}" name="recordId" />
          <mjl:property value="componentSeq" name="sortAttribute" />
          <mjl:property value="true" name="isAscending" />
          <mjl:property value="20" name="pageSize" />
          <mjl:property value="1" name="pageNumber" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer></mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
