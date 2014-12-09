<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        ${item.sprayZone.entityLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.irs.SprayTeamController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.concreteId}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.irs.SprayTeamController.newInstance.mojo" name="SprayTeamController.newInstance">
<mdss:localize key="Create_a_new_Spray_Team" />
</mjl:commandLink>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.irs.SprayTeamExcelView" name="excelType"/>
</jsp:include>
