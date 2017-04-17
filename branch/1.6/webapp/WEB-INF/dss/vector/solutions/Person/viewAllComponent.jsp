<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_All_People" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PersonController.viewPage.mojo" />
  <mjl:columns>
    <%@include file="tableComponent.jsp" %> 
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.PersonController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.personId}" name="id" />
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
<br />
<mjl:commandLink action="dss.vector.solutions.PersonController.newInstance.mojo" name="PersonController.newInstance">
<mdss:localize key="Create_a_new_Person" />
</mjl:commandLink>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PersonExcelView" name="excelType"/>
</jsp:include>