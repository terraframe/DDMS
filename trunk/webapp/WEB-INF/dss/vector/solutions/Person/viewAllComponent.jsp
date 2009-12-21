<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.business.ComponentQueryDTO"%>
<%@page import="dss.vector.solutions.PersonWithDelegatesViewDTO"%><c:set var="page_title" value="View_All_People" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
  ComponentQueryDTO query = (ComponentQueryDTO) request.getAttribute("query");
  String userLabel = query.getAttributeDTO(PersonWithDelegatesViewDTO.ISMDSSUSER).getAttributeMdDTO().getDisplayLabel();
  String sprayOperatorLabel = query.getAttributeDTO(PersonWithDelegatesViewDTO.ISSPRAYOPERATOR).getAttributeMdDTO().getDisplayLabel();
  String sprayLeaderLabel = query.getAttributeDTO(PersonWithDelegatesViewDTO.ISSPRAYLEADER).getAttributeMdDTO().getDisplayLabel();
  String stockStaffLabel = query.getAttributeDTO(PersonWithDelegatesViewDTO.ISSTOCKSTAFF).getAttributeMdDTO().getDisplayLabel();
  String supervisorLabel = query.getAttributeDTO(PersonWithDelegatesViewDTO.ISSUPERVISOR).getAttributeMdDTO().getDisplayLabel();
  
  request.setAttribute("userLabel", userLabel);
  request.setAttribute("sprayOperatorLabel", sprayOperatorLabel);
  request.setAttribute("sprayLeaderLabel", sprayLeaderLabel);
  request.setAttribute("stockStaffLabel", stockStaffLabel);
  request.setAttribute("supervisorLabel", supervisorLabel);
%>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PersonController.viewPage.mojo" />
  <mjl:columns>
    <%@include file="tableComponent.jsp" %> 
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.PersonController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Person" action="dss.vector.solutions.PersonController.newInstance.mojo" name="PersonController.newInstance" />

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PersonExcelView" name="excelType"/>
</jsp:include>