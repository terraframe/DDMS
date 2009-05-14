<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Spray_Team_View_All" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.irs.SprayTeamController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="teamId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayZone">
      <mjl:row>
        ${item.sprayZone.entityName}
      </mjl:row>
    </mjl:attributeColumn>
    <%-- 5.13.09 - Marlize says we don't need Spray Leaders
    <mjl:freeColumn>
      <mjl:header>
        <fmt:message key="Spray_Team_Leader" />
      </mjl:header>
      <mjl:row>
        <c:if test="${item.allTeamLeader[0] != null}">
          ${item.allTeamLeader[0].person.firstName}
          ${item.allTeamLeader[0].person.lastName}
        </c:if>
      </mjl:row>
      <mjl:footer>
      
      </mjl:footer>
    </mjl:freeColumn>
    --%>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.SprayTeamController.view.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink display="Create a new Spray Team" action="dss.vector.solutions.irs.SprayTeamController.newInstance.mojo" name="SprayTeamController.newInstance" />
