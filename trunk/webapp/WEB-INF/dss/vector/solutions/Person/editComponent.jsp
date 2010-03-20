<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
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
    <mjl:command value="Update" action="dss.vector.solutions.PersonController.updateFromView.mojo" name="dss.vector.solutions.Person.form.updateFromView.button" id="submit.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.PersonController.deleteFromView.mojo" name="dss.vector.solutions.Person.form.deleteFromView.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.PersonController.cancel.mojo" name="dss.vector.solutions.Person.form.cancel.button" />
  </dl>
</mjl:form>
