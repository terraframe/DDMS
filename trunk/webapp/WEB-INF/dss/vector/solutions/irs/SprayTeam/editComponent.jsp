<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Spray_Team_Edit" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST" >
  <dl>
    <dt><label> ${item.teamIdMd.displayLabel} </label></dt>
    <dd><mjl:component item="${item}" param="team">
      <mjl:input type="text" param="teamId" />
      <mjl:messages attribute="teamId">
        <mjl:message />
      </mjl:messages>
    </mjl:component></dd>
    <dt><label> ${item.sprayZoneMd.displayLabel} </label></dt>
    <dd><mjl:input type="text" param="geoId" value="${item.sprayZone.geoId}" /> <mjl:messages attribute="geoId">
      <mjl:message />
    </mjl:messages></dd>
    <dt><label> <fmt:message key="Spray_Team_Leader" /> </label></dt>
    <dd><mjl:select var="leader" valueAttribute="id" items="${leaders}" param="leaderId" includeBlank="true">
      <mjl:option selected="${leader.id==item.allTeamLeader[0].id ? 'selected' : 'false'}">
          ${leader.person.firstName} ${leader.person.lastName}
        </mjl:option>
    </mjl:select> <mjl:messages attribute="leaderId">
      <mjl:message />
    </mjl:messages></dd>
    <dt><label> <fmt:message key="Spray_Team_Manage_Operators" /> </label></dt>
    <dd>
    <table>
      <tr>
        <th colspan="2" width="33%"><fmt:message key="Spray_Team_Current_Operators" /></th>
        <th colspan="2" width="33%"><fmt:message key="Spray_Team_Available_Operators" /></th>
        <th width="33%"><fmt:message key="Spray_Team_Assigned_Operators" /></th>
      </tr>
      <tr>
        <td colspan="2" style="padding-right:5px"><fmt:message key="Spray_Team_Current_Operators_Instructions" /></td>
        <td colspan="2" style="padding-right:5px"><fmt:message key="Spray_Team_Available_Operators_Instructions" /></td>
        <td><fmt:message key="Spray_Team_Assigned_Operators_Instructions" /></td>
      </tr>
      <tr>
        <td><mjl:select var="operator" valueAttribute="operatorId" items="${current}" param="operatorIds" multiple="true" size="12" id="onTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="operatorId">
          <mjl:message />
        </mjl:messages></td>
        <td align="center" width="15%">
        <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(notOnTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveSelectedOptions(onTeam,notOnTeam,true)"><br>
        <br>
        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(notOnTeam,onTeam,true)"><br><br>
        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,notOnTeam,true)">
        </td>

        <td><mjl:select var="operator" valueAttribute="operatorId" items="${available}" param="removedIds" multiple="true" size="12" id="notOnTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="operatorId">
          <mjl:message />
        </mjl:messages>
        </td>
        <td align="center" width="15%">
                <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(onOtherTeam,notOnTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveSelectedOptions(notOnTeam,onOtherTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(onOtherTeam,notOnTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(notOnTeam,onOtherTeam,true,teamRegex)">

        </td>
        <td><mjl:select var="operator" valueAttribute="operatorId" items="${assigned}" param="onOtherTeam" multiple="true" size="12" id="onOtherTeam" style="width:15em">
          <mjl:option>[${operator.teamId}] ${operator.firstName} ${operator.lastName}</mjl:option>
        </mjl:select> <mjl:messages attribute="operatorId">
          <mjl:message />
        </mjl:messages></td>
      </tr>
    </table>
    </dd>
  </dl>

  <mjl:component item="${item}" param="dto">&nbsp;</mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.SprayTeamController.updateAssignments.mojo" name="dss.vector.solutions.irs.SprayTeam.form.updateAssignments.button" id="updateTeam"/>
  <mjl:command value="Delete" action="dss.vector.solutions.irs.SprayTeamController.delete.mojo" name="dss.vector.solutions.irs.SprayTeam.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.SprayTeamController.cancel.mojo" name="dss.vector.solutions.irs.SprayTeam.form.cancel.button" />
</mjl:form>
<script type="text/javascript">
        onTeam = document.getElementById("onTeam");
        notOnTeam = document.getElementById("notOnTeam");
        onOtherTeam = document.getElementById("onOtherTeam");
        teamRegex = '^ *[^[]';
        teamForm = document.getElementById("dss.vector.solutions.irs.SprayTeam.form.id");
        document.getElementById("updateTeam").onclick = function(){
        var formEl = document.getElementById("dss.vector.solutions.irs.SprayTeam.form.id");
          formEl.action = "dss.vector.solutions.irs.SprayTeamController.updateAssignments.mojo";
          Selectbox.selectAllOptions(onTeam);
          Selectbox.selectAllOptions(notOnTeam,);
          Selectbox.selectAllOptions(onOtherTeam);
          alert("test");
          formEl.submit();
        }

</script>
