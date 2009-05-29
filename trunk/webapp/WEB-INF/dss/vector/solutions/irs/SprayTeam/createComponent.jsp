<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
</script>

<c:set var="page_title" value="Spray_Team_Create" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <%@ include file="form.jsp"%>
  <mjl:command value="Create" id="updateTeam" action="dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo" name="dss.vector.solutions.irs.SprayTeam.form.createAndAssign.button" />
</mjl:form>

<script type='text/javascript'>
        onTeam = document.getElementById('onTeam');
        notOnTeam = document.getElementById('notOnTeam');
        onOtherTeam = document.getElementById('onOtherTeam');
        teamRegex = '^ *[^[]';
        teamForm = document.getElementById('dss.vector.solutions.irs.SprayTeam.form.id');
        document.getElementById('updateTeam').onclick = function(){
          teamForm.action = 'dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo';
          Selectbox.selectAllOptions(onTeam);
          Selectbox.selectAllOptions(notOnTeam);
          teamForm.submit();
        }
</script>