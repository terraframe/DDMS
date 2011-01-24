<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Spray_Team_Edit" scope="request" />
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST" >
<mjl:component item="${item}" param="dto">&nbsp;</mjl:component>
  <dl>
    <%@ include file="form.jsp"%>
    
    <mdss:localize key="Update" var="Localized_Update" />
    
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.irs.SprayTeamController.updateAssignments.mojo" name="dss.vector.solutions.irs.SprayTeam.form.updateAssignments.button" id="updateTeam" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.irs.SprayTeamController.delete.mojo" name="dss.vector.solutions.irs.SprayTeam.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.irs.SprayTeamController.cancel.mojo" name="dss.vector.solutions.irs.SprayTeam.form.cancel.button" />
  </dl>
</mjl:form>

<script type='text/javascript'>
        onTeam = document.getElementById('onTeam');
        notOnTeam = document.getElementById('notOnTeam');
        onOtherTeam = document.getElementById('onOtherTeam');
        teamRegex = '^ *[^[]';
        teamForm = document.getElementById('dss.vector.solutions.irs.SprayTeam.form.id');
        document.getElementById('updateTeam').onclick = function(){
          teamForm.action = 'dss.vector.solutions.irs.SprayTeamController.updateAssignments.mojo';
          Selectbox.selectAllOptions(onTeam);
          Selectbox.selectAllOptions(notOnTeam);
          teamForm.submit();
        }
</script>