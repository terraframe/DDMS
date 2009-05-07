<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Spray_Team_Create" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <dl>
    <dt>
      <label>
        ${item.teamIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:component item="${item}" param="team">
        <mjl:input type="text" param="teamId" />
        <mjl:messages attribute="teamId">
          <mjl:message />
        </mjl:messages>
      </mjl:component>
    </dd>
    <dt>
      <label>
        ${item.sprayZoneMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:input type="text" param="geoId" />
      <mjl:messages attribute="geoId">
        <mjl:message />
      </mjl:messages>
    </dd>
    <dt>
      <label>
        <fmt:message key="Spray_Team_Leader" />
      </label>
    </dt>
    <dd>
      <mjl:select var="leader" valueAttribute="id" items="${leaders}" param="leaderId">
        <mjl:option>
          ${leader.person.firstName} ${leader.person.lastName}
        </mjl:option>
      </mjl:select>
      <mjl:messages attribute="leaderId">
        <mjl:message />
      </mjl:messages>
    </dd>
    <dt>
      <label>
        <fmt:message key="Spray_Team_Add_Operators" />
      </label>
    </dt>
    <dd>
      <table>
        <tr>
          <th><fmt:message key="Spray_Team_Available_Operators" /></th>
          <th><fmt:message key="Spray_Team_Assigned_Operators" /></th>
        </tr>
        <tr>
          <td><fmt:message key="Spray_Team_Available_Operators_Instructions" /></td>
          <td><fmt:message key="Spray_Team_Assigned_Operators_Instructions" /></td>
        </tr>
        <tr>
          <td>
            <mjl:select var="operator" valueAttribute="operatorId" items="${available}" param="operatorIds" multiple="true">
              <mjl:option>
                ${operator.firstName} ${operator.lastName}
              </mjl:option>
            </mjl:select>
            <mjl:messages attribute="operatorId">
              <mjl:message />
            </mjl:messages>
          </td>
          <td>
            <mjl:select var="operator" valueAttribute="operatorId" items="${assigned}" param="operatorIds" multiple="true">
              <mjl:option>
                [${operator.teamId}] ${operator.firstName} ${operator.lastName}
              </mjl:option>
            </mjl:select>
            <mjl:messages attribute="operatorId">
              <mjl:message />
            </mjl:messages>
          </td>
        </tr>
      </table>
    </dd>
  </dl>
  
  <mjl:command value="Create" action="dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo" name="dss.vector.solutions.irs.SprayTeam.form.createAndAssign.button" />
</mjl:form>
