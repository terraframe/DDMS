<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_Person" scope="request" />


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

    <%-- IMPORTANT: Do not change the id of the Create button because it is used javascript defined on personFields.jsp --%>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command value="${Localized_Search}" action="dss.vector.solutions.PersonController.search.mojo" name="dss.vector.solutions.Person.form.search.button" />
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.PersonController.createFromView.mojo" name="dss.vector.solutions.Person.form.create.button" id="submit.button" />
  </dl>
</mjl:form>
