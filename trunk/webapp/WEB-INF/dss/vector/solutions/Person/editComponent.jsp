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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Person" scope="request" />


<script type="text/javascript">
(function(){

  YAHOO.util.Event.onDOMReady(function(){

    MDSS.Effect.toggleVisibility('userDiv', 'userSwitch', 'userInput');
    MDSS.Effect.toggleVisibility('iptDiv', 'iptSwitch', 'iptInput');
    MDSS.Effect.toggleVisibility('itnDiv', 'itnSwitch', 'itnInput');
    MDSS.Effect.toggleVisibility('patientDiv', 'patientSwitch', 'patientInput');
    MDSS.Effect.toggleVisibility('sprayLeaderDiv', 'sprayLeaderSwitch', 'sprayLeaderInput');
    MDSS.Effect.toggleVisibility('sprayOperatorDiv', 'sprayOperatorSwitch', 'sprayOperatorInput');
    MDSS.Effect.toggleVisibility('stockStaffDiv', 'stockStaffSwitch', 'stockStaffInput');
    MDSS.Effect.toggleVisibility('supervisorDiv', 'supervisorSwitch', 'supervisorInput');
    MDSS.Effect.toggleVisibility('physicianDiv', 'physicianSwitch', 'physicianInput');
  });

})();
</script>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Person.form.name" id="dss.vector.solutions.Person.form.id" method="POST">
  <dl>
    <jsp:include page="personFields.jsp" />

    <mjl:input type="hidden" param="dto.componentId" value="${item.personId}"/>
    
    <%-- IMPORTANT: Do not change the id of the Update button because it is used javascript defined on personFields.jsp --%>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.PersonController.updateFromView.mojo" name="dss.vector.solutions.Person.form.updateFromView.button" id="submit.button" />
    
    <c:if test="${!isOdkUser}">
      <mdss:localize key="Delete" var="Localized_Delete" />
      <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.PersonController.deleteFromView.mojo" name="dss.vector.solutions.Person.form.deleteFromView.button" />
    </c:if>
    
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.PersonController.cancel.mojo" name="dss.vector.solutions.Person.form.cancel.button" />
  </dl>
</mjl:form>
