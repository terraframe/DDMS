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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.PersonViewDTO"%>

<c:set var="page_title" value="Confirm_IPT_Recipient" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="IndividualIPT.form.name" id="form.id" method="POST">
    <mjl:component item="${item}" param="patient">
      <mjl:input type="hidden" param="personId" id="personId" classes="component"/>
      <mjl:dt attribute="identifier">
        <mjl:input type="text" param="identifier" id="identifier" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="firstName">
        <mjl:input type="text" param="firstName" id="firstName" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="lastName">
        <mjl:input type="text" param="lastName" id="lastName" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="dateOfBirth" >
        <mjl:input type="text" param="dateOfBirth" id="dateOfBirth" classes="DatePick NoFuture component"/>
      </mjl:dt>
      <mjl:dt attribute="age" >
        <mjl:input type="text" param="age" size="3" maxlength="3" id="age" classes="component" />
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mdss:mo param="sex" value="${sex}" id="sex" classes="component" />
      </mjl:dt> 
      <mjl:dt attribute="residentialGeoId" >
        <mdss:geo param="residentialGeoId" id="residentialGeoId" classes="component" concrete="false" value="${item.residentialGeoId}" />
      </mjl:dt>
      <mjl:dt attribute="residentialInformation">
        <mjl:textarea param="residentialInformation" id="residentialInformation" classes="component" />
      </mjl:dt>
      <mjl:dt attribute="workGeoId" >
        <mdss:geo param="workGeoId" id="workGeoId" classes="component" concrete="false" value="${item.workGeoId}" />      
      </mjl:dt>
      <mjl:dt attribute="workInformation">
        <mjl:textarea param="workInformation" id="workInformation" classes="component" />
      </mjl:dt>
      <mjl:dt attribute="birthEntity" >
        <mdss:geo param="birthEntity" id="birthEntity" classes="component" concrete="false" value="${item.birthEntity}" />      
      </mjl:dt>
      <mjl:dt attribute="birthLocation">
        <mjl:textarea param="birthLocation" id="birthLocation" classes="component" />
      </mjl:dt>
    </mjl:component>

    <button type="button" id="button.confirm"><mdss:localize key="Confirm" /></button>
    <button type="button" id="button.cancel"><mdss:localize key="Cancel" /></button>
  </mjl:form>
</dl>