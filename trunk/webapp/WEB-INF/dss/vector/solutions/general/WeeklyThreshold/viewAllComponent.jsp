<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_WeeklyThreshold" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.general.WeeklyThresholdController.viewPage.mojo" />
  <mjl:columns>
    <mjl:freeColumn>
      <mjl:header>
        Population Data
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.general.ThresholdDataController.view.mojo" name="parent.link">
          <fmt:message key="${item.parent.keyName}" />
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        Epi Week
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.general.EpiWeekController.view.mojo" name="child.link">
          <fmt:message key="${item.child.keyName}" />
          <mjl:property value="${item.childId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:attributeColumn attributeName="identification">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastIdentification">
      <mjl:row>
        ${item.lastIdentification.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastNotification">
      <mjl:row>
        ${item.lastNotification.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="notification">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.general.WeeklyThresholdController.view.mojo" name="view.link">
          <fmt:message key="View" />
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
<mjl:commandLink action="dss.vector.solutions.general.WeeklyThresholdController.newRelationship.mojo" name="WeeklyThresholdController.newRelationship">
  <fmt:message key="Create_a_new_" />
</mjl:commandLink>
