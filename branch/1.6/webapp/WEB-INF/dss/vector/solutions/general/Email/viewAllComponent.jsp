<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_Email" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.EmailController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="toAddresses">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="subject">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="createDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.createDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sentDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.sentDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="error">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.general.EmailController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
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

