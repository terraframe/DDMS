<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Spray_Team_View" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.teamIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.teamId}
    </dd>
    <dt>
      <label>
        ${item.sprayZoneMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sprayZone.entityName}
    </dd>
    <dt>
      <label>
        <fmt:message key="Spray_Team_Leader" />
      </label>
    </dt>
    <dd>
      <c:if test="${leader!=null}">${leader.firstName} ${leader.lastName}</c:if>
    </dd>
    <dt>
      <label>
        <fmt:message key="Spray_Team_Operators" />
      </label>
    </dt>
    <dd>
      <c:forEach items="${operators}" var="operator">
        ${operator.firstName} ${operator.lastName} <br/>
      </c:forEach>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.SprayTeamController.edit.mojo" name="dss.vector.solutions.irs.SprayTeam.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.SprayTeamController.viewAll.mojo" name="dss.vector.solutions.irs.SprayTeam.viewAll.link" />
