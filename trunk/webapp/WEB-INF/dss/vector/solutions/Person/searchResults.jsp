<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
          <mdss:localize key="Use_This_Person"/>
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
    <mjl:input type="hidden" param="identifier" value="${newPerson.identifier}"/>
    <mjl:input type="hidden" param="firstName" value="${newPerson.firstName}"/>
    <mjl:input type="hidden" param="lastName" value="${newPerson.lastName}"/>
    <mjl:input type="hidden" param="residentialGeoId" value="${newPerson.residentialGeoId}"/>
    <mjl:input type="hidden" param="dateOfBirth" value="${formatedBirthday}"/>
    <mjl:input type="hidden" param="sex" value="${sex}"/>
    <mjl:input type="hidden" param="isMDSSUser" value="${newPerson.isMDSSUser}"/>
    <mjl:input type="hidden" param="username" value="${newPerson.username}"/>
    <mjl:input type="hidden" param="password" value="${newPerson.password}"/>
    <mjl:input type="hidden" param="repassword" value="${repassword}"/>
    <mjl:input type="hidden" param="isIPTRecipient" value="${newPerson.isIPTRecipient}"/>
    <mjl:input type="hidden" param="isITNRecipient" value="${newPerson.isITNRecipient}"/>
    <mjl:input type="hidden" param="isPatient" value="${newPerson.isPatient}"/>
    <mjl:input type="hidden" param="isSprayLeader" value="${newPerson.isSprayLeader}"/>
    <mjl:input type="hidden" param="memberId" value="${newPerson.memberId}"/>
    <mjl:input type="hidden" param="isSprayOperator" value="${newPerson.isSprayOperator}"/>
  </mjl:component>
  <mdss:localize key="No_Matches__continue_with_new_Person" var="Localized_No_Matches__continue_with_new_Person" />
  <mjl:command value="${Localized_No_Matches__continue_with_new_Person}" action="dss.vector.solutions.PersonController.continueNewInstance.mojo" name="continueNewInstance.button" />
</mjl:form>
