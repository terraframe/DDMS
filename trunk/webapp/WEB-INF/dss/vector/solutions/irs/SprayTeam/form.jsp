<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%>

<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');
      var geoEntityName = document.getElementById('entityName');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityName.innerHTML = selected.getEntityName();
      }
      else
      {
        geoId.value = '';
        geoEntityName.innerHTML = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    var searchFilter = '<%=SprayZoneDTO.CLASS%>';
    selectSearch.setFilter(searchFilter);



    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });
  }, null, true);
</script>

  <dl>
    <dt><label> ${item.teamIdMd.displayLabel} </label></dt>
    <dd>
    <mjl:component item="${item}" param="team">
      <mjl:input type="text" param="teamId" />
      <mjl:messages attribute="teamId">
        <mjl:message />
      </mjl:messages>
    </mjl:component>
    </dd>
    <dt><label> ${item.sprayZoneMd.displayLabel} </label></dt>
    <dd><mjl:input type="text" param="geoId" value="${item.sprayZone.geoId}" id="geoIdEl"/>
    <a href="#" id="searchOpener"><img src="./imgs/icons/world.png" /></a>
<br/>(<span id ="entityName"></span>)
    </dd>
    <dt><label> <fmt:message key="Spray_Team_Leader" /> </label></dt>
    <dd><mjl:select var="leader" valueAttribute="id" items="${leaders}" param="leaderId" includeBlank="true">
      <mjl:option >
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
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveSelectedOptions(onTeam,notOnTeam,true);Selectbox.moveSelectedOptions(notOnTeam,onOtherTeam,true,teamRegex);"><br>
        <br>
        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(notOnTeam,onTeam,true)"><br><br>
        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex);Selectbox.moveAllOptions(onTeam,notOnTeam,true)">
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
                <input type="button" name="left" value="&lt;&lt;" onClick="Selectbox.moveSelectedOptions(onOtherTeam,onTeam,true)"><br>
        <br>
        <input type="button" name="right" value="&gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="left" value="&lt;&lt; <fmt:message key="All"/>" onClick="Selectbox.moveAllOptions(onOtherTeam,onTeam,true,teamRegex)"><br>
        <br>

        <input type="button" name="right" value="<fmt:message key="All"/> &gt;&gt;" onClick="Selectbox.moveAllOptions(onTeam,onOtherTeam,true,teamRegex)">

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

<c:set var="add_to_footer" value="
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
" scope="request"/>
