<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:component item="${item}" param="team">
    <dl>
      <dt>
        <label>
          !! Spray Zone (GeoId)
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sprayZone" />
        <mjl:messages attribute="sprayZone">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  
    <dl>
      <dt>
        <label>
          !! Spray Leader
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
    </dl>
  
    <dl>
      <dt>
        <label>
          !! Spray Operator
        </label>
      </dt>
      <dd>
        <mjl:select var="operator" valueAttribute="id" items="${operators}" param="operatorId">
          <mjl:option>
            ${operator.person.firstName} ${operator.person.lastName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="operatorId">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  
  <mjl:command value="Create" action="dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo" name="dss.vector.solutions.irs.SprayTeam.form.createAndAssign.button" />
</mjl:form>
