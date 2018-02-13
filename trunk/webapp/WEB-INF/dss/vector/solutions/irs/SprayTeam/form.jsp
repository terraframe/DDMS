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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.irs.TeamMemberDTO"%>
<%@page import="dss.vector.solutions.irs.TeamMemberViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<dt><label> ${view.teamIdMd.displayLabel} </label></dt>
    <dd>
    <mjl:component item="${item}" param="team">
      <mjl:input type="text" param="teamId" id="teamId" />
      <mjl:messages attribute="teamId">
        <mjl:message />
      </mjl:messages>
    </mjl:component>
    </dd>
    <dt><label> ${view.sprayZoneMd.displayLabel} </label></dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" value="${item.sprayZone}" political="false" spray="true" filter="${SprayZone}" />
    </dd>
    <c:if test="${view.isTeamLeaderReadable}">
      <dt><label> <mdss:localize key="Spray_Team_Leader" /> </label></dt>
      <dd>
        <mjl:input id="leaderInput" param="leaderInput" type="text" value="${leaderLabel}"/>
        <mjl:input id="leaderId" param="leaderId" type="hidden" value="${leaderId}"/>        
        <mjl:messages attribute="leaderId">
          <mjl:message />
        </mjl:messages>
      </dd>
    </c:if>
    <dt></dt>
    <dd>
    <table>
      <tr>
        <th colspan="2" width="33%" ><label>${view.currentOperatorsMd.displayLabel}</label></th>
        <th colspan="2" width="33%" ><label>${view.availableOperatorsMd.displayLabel}</label></th>
        <th width="33%"><label>${view.assignedOperatorsMd.displayLabel}</label></th>
      </tr>
      <tr>
        <td colspan="1" style="padding-bottom:5px;padding-top:5px;font-size:smaller;"><mdss:localize key="Spray_Team_Current_Operators_Instructions" /></td>
        <td></td>
        <td colspan="1" style="padding-bottom:5px;padding-top:5px;font-size:smaller;"><mdss:localize key="Spray_Team_Available_Operators_Instructions" /></td>
        <td></td>
        <td style="padding-bottom:5px;padding-top:5px;font-size:smaller;"><mdss:localize key="Spray_Team_Assigned_Operators_Instructions" /></td>
      </tr>
      <tr>
        <td><mjl:select var="operator" valueAttribute="actorId" items="${current}" param="operatorIds" multiple="true" size="12" id="onTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName} - ${operator.memberId}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="actorId">
          <mjl:message />
        </mjl:messages></td>
        <td align="center" width="15%">
        <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(notOnTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveSelectedOptions(onTeam,notOnTeam,true);Selectbox.moveSelectedOptions(notOnTeam,onOtherTeam,true,teamRegex);"><br>
        <br>
        <input type="button" name="left" value="&lt;&lt; <mdss:localize key="All"/>" onClick="Selectbox.moveAllOptions(notOnTeam,onTeam,true)"><br><br>
        <input type="button" name="right" value="<mdss:localize key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex);Selectbox.moveAllOptions(onTeam,notOnTeam,true)">
        </td>

        <td><mjl:select var="operator" valueAttribute="actorId" items="${available}" param="removedIds" multiple="true" size="12" id="notOnTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName} - ${operator.memberId}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="actorId">
          <mjl:message />
        </mjl:messages>
        </td>
        <td align="center" width="15%">
                <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(onOtherTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" style="display:none;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="left" value="&lt;&lt; <mdss:localize key="All"/>" onClick="Selectbox.moveAllOptions(onOtherTeam,onTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="right" value="<mdss:localize key="All"/> &gt;&gt;" style="display:none;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)">

        </td>
        <td>
          <mjl:select var="operator" valueAttribute="actorId" items="${assigned}" param="onOtherTeam" multiple="true" size="12" id="onOtherTeam" style="width:15em">
            <mjl:option>[${operator.teamId}] ${operator.firstName} ${operator.lastName} - ${operator.memberId}</mjl:option>
          </mjl:select>
        </td>
      </tr>
      <tr>
        <td > </td>
        <td align="center" width="15%">
          <input type="button" name="left" value="&lt;&lt;" id="available.button.id">
        </td>
        <td>
          <mjl:input id="availableInput" param="availableId" type="text" size="11" style="width:15em"/>
          <mjl:input id="availableId" param="id" type="hidden" />        
        </td>
        <td align="center" width="15%">
          <input type="button" name="left" value="&lt;&lt;" id="assigned.button.id">
        </td>
        <td>
          <mjl:input id="assignedInput" param="assignedId" type="text" size="11" style="width:15em"/>
          <mjl:input id="assignedId" param="id" type="hidden" />        
        </td>
      </tr>
    </table>
    </dd>
    
    <%=Halp.loadTypes(Arrays.asList(new String[]{TeamMemberViewDTO.CLASS, TeamMemberDTO.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){       
    var teamId = document.getElementById('teamId').value;   

    MDSS.operatorSearch({to:'onTeam', from:'notOnTeam', button:'available.button.id', display:'availableInput', id:'availableId'});       
    MDSS.operatorSearch({to:'onTeam', from:'onOtherTeam', button:'assigned.button.id', display:'assignedInput', id:'assignedId'});       
      
    new MDSS.SprayLeaderSearch({search:'leaderInput', concrete:'leaderId'});       

    var loadAssignedOperators = function(geoId) {
      var request = new MDSS.Request({
        onSend: function(){},
        onComplete: function(){},
        onSuccess: function(operators) {
          // Remove all options
          Selectbox.removeAllOptions(onOtherTeam);
          Selectbox.removeAllQualifiedOptions(onTeam, teamRegex);

          // Add new options to the select list
          for(var i = 0; i < operators.length; i++) {
            var operator = operators[i];
            var team = operator.getTeamId();
            var value = operator.getActorId();
            var text = '[' + team + '] ' + operator.getFirstName() + ' ' + operator.getLastName() + ' - ' + operator.getMemberId();

            if(teamId !== team) {
              Selectbox.addOption(onOtherTeam,text,value,false);
            }
          }
        }
      });

      Mojo.$.dss.vector.solutions.irs.TeamMemberView.getAllOperatorsForLocation(request, geoId);
    }        

    Mojo.GLOBAL.onValidGeoEntitySelected = function() {
      var geoId = document.getElementById('geoId');

      if(geoId.value != null) {
        loadAssignedOperators(geoId.value);
      }
    }  
  });
})();               
</script>    
