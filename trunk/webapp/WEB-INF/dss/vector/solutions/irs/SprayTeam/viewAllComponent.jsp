<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.irs.SprayTeamController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="sprayZone">
      <mjl:header>
        !! Spray Zone
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        !! Leader
      </mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.allTeamLeader[0] != null}">
            ${item.allTeamLeader[0].person.firstName}
            ${item.allTeamLeader[0].person.lastName}
          </c:when>
          <c:otherwise>
            None Assigned
          </c:otherwise>
        </c:choose>
      </mjl:row>
      <mjl:footer>
      
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        !! Operator
      </mjl:header>
      <mjl:row>
        <c:choose>
          <c:when test="${item.allSprayTeamMembers[0] != null}">
            ${item.allSprayTeamMembers[0].person.firstName}
            ${item.allSprayTeamMembers[0].person.lastName}
          </c:when>
          <c:otherwise>
            None Assigned
          </c:otherwise>
        </c:choose>
      </mjl:row>
      <mjl:footer>
      
      </mjl:footer>
    </mjl:freeColumn>
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
<mjl:commandLink display="Create a new Spray Operator" action="dss.vector.solutions.irs.SprayTeamController.newInstance.mojo" name="SprayTeamController.newInstance" />
