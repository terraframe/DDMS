<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_Larvacide" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.LarvacideController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <fmt:formatDate value="${item.startDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="completionDate">
      <mjl:row>
        <fmt:formatDate value="${item.completionDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.geoId}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="personCount">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="teamLeader">
      <mjl:row>
        ${item.teamLeader.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.LarvacideController.view.mojo" name="view.link">
          <mdss:localize key="View" />
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.LarvacideController.newInstance.mojo" name="LarvacideController.newInstance">
  <mdss:localize key="Create_a_new_Larvacide" />
</mjl:commandLink>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.LarvacideExcelView" name="excelType"/>
</jsp:include>
