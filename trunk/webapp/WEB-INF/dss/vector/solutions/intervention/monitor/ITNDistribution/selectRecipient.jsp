<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.terraframe.mojo.constants.Constants" %>
<c:set var="page_title" value="Search_Person" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PersonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="firstName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${item.sexEnumNames}">
            <li>
              ${item.sexMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>      
      </mjl:row>          
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateOfBirth">
      <mjl:row>
        <span class="formatDate"> ${item.dateOfBirth} </span>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header><fmt:message key="ITN_Recipient"/></mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.itnRecipientDelegate != null}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </mjl:row>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.PersonController.edit.mojo" name="view.link">
          <fmt:message key="Use_This_Person"/>
          <mjl:property value="${item.id}" name="id" />
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
  <mjl:component item="${itn}" param="itn">
      <mjl:input type="hidden" param="concreteId" value="${itn.concreteId}" />  
      <mjl:input type="hidden" param="batchNumber"/>
      <mjl:input type="hidden" param="currencyReceived" value="${itn.currencyReceived}"/>
      <fmt:formatDate value="${itn.distributionDate}" pattern="${dateFormatPattern}" var="formattedDistributionDate"/>
      <mjl:input type="hidden" param="distributionDate" value="${formattedDistributionDate}"/>
      <mjl:input type="hidden" param="distributorName" value="${itn.distributorName}"/>
      <mjl:input type="hidden" param="distributorSurname" value="${itn.distributorSurname}"/>
      <mjl:input type="hidden" param="facility" value="${itn.facility != null ? itn.facility.id : ''}"/>
      <mjl:input type="hidden" param="net" value="${itn.net != null ? itn.net.id : ''}"/>
      <mjl:input type="hidden" param="numberSold" value="${itn.numberSold}"/>
      <mjl:input type="hidden" param="recipient" value="${itn.recipient != null ? itn.recipient.id : ''}"/>
      <mjl:input type="hidden" param="service" value="${itn.service != null ? itn.service.id : ''}"/>
    </mjl:component>
  <mjl:command value="No_Matches__continue_with_new_Person" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="dss.vector.solutions.PersonController.continueNewInstance.button" />
</mjl:form>
