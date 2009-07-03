<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%>


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
    <dd><mjl:input type="text" param="geoId" value="${item.sprayZone.geoId}" id="geoIdEl" classes="geoInput"/>  </dd>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders --%>
    <dt><label> <fmt:message key="Spray_Team_Leader" /> </label></dt>
    <dd><mjl:select var="leader" valueAttribute="id" items="${leaders}" param="leaderId" includeBlank="true">
      <mjl:option selected="${leader.id==leaderId?'selected':'false'}">
          ${leader.person.firstName} ${leader.person.lastName}
        </mjl:option>
    </mjl:select>
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
                ${operator.firstName} ${operator.lastName}
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
                ${operator.firstName} ${operator.lastName}
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
        <td><mjl:select var="operator" valueAttribute="actorId" items="${assigned}" param="onOtherTeam" multiple="true" size="12" id="onOtherTeam" style="width:15em">
          <mjl:option>[${operator.teamId}] ${operator.firstName} ${operator.lastName}</mjl:option>
        </mjl:select> <mjl:messages attribute="actorId">
          <mjl:message />
        </mjl:messages></td>
      </tr>
    </table>
    </dd>
