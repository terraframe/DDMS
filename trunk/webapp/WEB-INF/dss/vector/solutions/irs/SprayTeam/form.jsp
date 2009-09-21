<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%>


    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.irs.SprayOperatorDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="dss.vector.solutions.irs.SprayOperatorViewDTO"%>
<%@page import="dss.vector.solutions.irs.SprayLeaderDTO"%>

<dt><label> ${item.teamIdMd.displayLabel} </label></dt>
    <dd>
    <mjl:component item="${item}" param="team">
      <mjl:input type="text" param="teamId" id="teamId" />
      <mjl:messages attribute="teamId">
        <mjl:message />
      </mjl:messages>
    </mjl:component>
    </dd>
    <dt><label> ${item.sprayZoneMd.displayLabel} </label></dt>
    <dd>
      <mjl:input type="hidden" param="typeSearchFilter" id="typeSearchFilter" value="dss.vector.solutions.geo.generated.SprayZone" />
      <mjl:input type="text" param="geoId" value="${item.sprayZone.geoId}" id="geoIdEl" classes="geoInput"/>
    </dd>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
    <dt><label> <fmt:message key="Spray_Team_Leader" /> </label></dt>
    <dd>
      <mjl:input id="leaderInput" param="leaderInput" type="text" value="${leaderLabel}"/>
      <mjl:input id="leaderId" param="leaderId" type="hidden" value="${leaderId}"/>        
      <mjl:messages attribute="leaderId">
        <mjl:message />
      </mjl:messages>
    </dd>
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
        <td><mjl:select var="operator" valueAttribute="actorId" items="${current}" param="operatorIds" multiple="true" size="12" id="onTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName} - ${operator.operatorId}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="actorId">
          <mjl:message />
        </mjl:messages></td>
        <td align="center" width="15%">
        <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(notOnTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveSelectedOptions(onTeam,notOnTeam,true);Selectbox.moveSelectedOptions(notOnTeam,onOtherTeam,true,teamRegex);"><br>
        <br>
        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(notOnTeam,onTeam,true)"><br><br>
        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex);Selectbox.moveAllOptions(onTeam,notOnTeam,true)">
        </td>

        <td><mjl:select var="operator" valueAttribute="actorId" items="${available}" param="removedIds" multiple="true" size="12" id="notOnTeam" style="width:15em">
          <mjl:option>
                ${operator.firstName} ${operator.lastName} - ${operator.operatorId}
              </mjl:option>
        </mjl:select> <mjl:messages attribute="actorId">
          <mjl:message />
        </mjl:messages>
        </td>
        <td align="center" width="15%">
                <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(onOtherTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(onOtherTeam,onTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)">

        </td>
        <td>
          <mjl:select var="operator" valueAttribute="actorId" items="${assigned}" param="onOtherTeam" multiple="true" size="12" id="onOtherTeam" style="width:15em">
            <mjl:option>[${operator.teamId}] ${operator.firstName} ${operator.lastName} - ${operator.operatorId}</mjl:option>
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
    
    <%=Halp.loadTypes(Arrays.asList(new String[]{SprayOperatorViewDTO.CLASS}))%>
    <%=Halp.loadTypes(Arrays.asList(new String[]{SprayLeaderDTO.CLASS}))%>

    <script type="text/javascript" defer="defer">  
      onTeam = document.getElementById('onTeam');      
      notOnTeam = document.getElementById('notOnTeam');   
      onOtherTeam = document.getElementById('onOtherTeam');   

      leaderInput = document.getElementById('leaderInput');
      leaderInput.setAttribute("autocomplete","off");   
      leaderId = document.getElementById('leaderId');   

      availableButton = document.getElementById('available.button.id');
      availableInput = document.getElementById('availableInput');   
      availableId = document.getElementById('availableId');   
      
      assignButton = document.getElementById('assigned.button.id');
      assignInput = document.getElementById('assignedInput');   
      assignId = document.getElementById('assignedId');   
      
      var teamId = document.getElementById('teamId').value;   

      MDSS.operatorSearch(onTeam, notOnTeam, availableButton, availableInput, availableId);       
      MDSS.operatorSearch(onTeam, onOtherTeam, assignButton, assignInput, assignId);       
      MDSS.leaderSearch(leaderInput, leaderId);       

      var loadAssignedOperators = function(geoId)
      {
        var request = new MDSS.Request({
            onSend: function(){},
            onComplete: function(){},
            onSuccess: function(operators)
            {
                // Remove all options
            	Selectbox.removeAllOptions(onOtherTeam);
            	Selectbox.removeAllQualifiedOptions(onTeam, teamRegex);

            	// Add new options to the select list
            	for(var i = 0; i < operators.length; i++)
            	{
                    var operator = operators[i];
                	var team = operator.getTeamId();
                	var value = operator.getActorId();
                	var text = '[' + team + '] ' + operator.getFirstName() + ' ' + operator.getLastName() + ' - ' + operator.getOperatorId();

                	if(teamId !== team)
                	{
                	  Selectbox.addOption(onOtherTeam,text,value,false);
                	}
            	}
            }
          });

        Mojo.$.dss.vector.solutions.irs.SprayOperatorView.getAllForLocation(request, geoId);
      }        

	  var onValidGeoEntitySelected = function()
	  {
	    var geoId = document.getElementById('geoIdEl');

	    if(geoId.value != null)
	    {
	    	loadAssignedOperators(geoId.value);
	    }
	  }		  
               
    </script>    
