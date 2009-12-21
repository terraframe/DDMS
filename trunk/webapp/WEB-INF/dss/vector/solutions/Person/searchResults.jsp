<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.terraframe.mojo.constants.Constants" %>


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


<c:set var="page_title" value="Search_Person" scope="request" />

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
        <mjl:commandLink action="dss.vector.solutions.PersonController.edit.mojo" name="view.link">
          <fmt:message key="Use_This_Person"/>
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
<fmt:formatDate value="${newPerson.dateOfBirth}" pattern="${dateFormatPattern}" var="formatedBirthday" />
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <mjl:component item="${newPerson}" param="person">
    <mjl:input type="hidden" param="personId" value="${newPerson.personId}" />
    <mjl:input type="hidden" param="firstName" value="${newPerson.firstName}"/>
    <mjl:input type="hidden" param="lastName" value="${newPerson.lastName}"/>
    <mjl:input type="hidden" param="residentialGeoId" value="${newPerson.residentialGeoId}"/>
    <mjl:input type="hidden" param="dateOfBirth" value="${formatedBirthday}"/>
    <mjl:input type="hidden" param="sex" value="${sexEnumName}"/>
    <mjl:input type="hidden" param="isMDSSUser" value="${newPerson.isMDSSUser}"/>
    <mjl:input type="hidden" param="username" value="${newPerson.username}"/>
    <mjl:input type="hidden" param="password" value="${newPerson.password}"/>
    <mjl:input type="hidden" param="isIPTRecipient" value="${newPerson.isIPTRecipient}"/>
    <mjl:input type="hidden" param="isITNRecipient" value="${newPerson.isITNRecipient}"/>
    <mjl:input type="hidden" param="isPatient" value="${newPerson.isPatient}"/>
    <mjl:input type="hidden" param="isSprayLeader" value="${newPerson.isSprayLeader}"/>
    <mjl:input type="hidden" param="leaderId" value="${newPerson.leaderId}"/>
    <mjl:input type="hidden" param="isSprayOperator" value="${newPerson.isSprayOperator}"/>
    <mjl:input type="hidden" param="operatorId" value="${newPerson.operatorId}"/>
  </mjl:component>
  <mjl:command value="No_Matches__continue_with_new_Person" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="dss.vector.solutions.PersonController.continueNewInstance.button" />
</mjl:form>
