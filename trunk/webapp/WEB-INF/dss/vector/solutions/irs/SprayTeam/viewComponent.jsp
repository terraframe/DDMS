<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Spray_Team_View" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:component item="${view}" param="dto">
      <mjl:dt attribute="teamId">
        ${item.teamId}
      </mjl:dt>
      <mjl:dt attribute="sprayZone">
        ${item.sprayZone.displayString}
      </mjl:dt>
      <mjl:dt attribute="teamLeader">
        <c:if test="${leader != null}">
          ${leader.firstName} ${leader.lastName}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="currentOperators">
        <c:forEach items="${operators}" var="operator">
          ${operator.firstName} ${operator.lastName} <br/>
        </c:forEach>      
      </mjl:dt>      
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.irs.SprayTeamController.edit.mojo" name="dss.vector.solutions.irs.SprayTeam.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.SprayTeamController.viewAll.mojo" name="dss.vector.solutions.irs.SprayTeam.viewAll.link" />
