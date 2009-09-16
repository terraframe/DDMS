<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_ITNHouseholdSurvey" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.startDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.endDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="surveyLocation">
      <mjl:row>
        ${item.surveyLocation}
      </mjl:row>
    </mjl:attributeColumn>  
    <mjl:attributeColumn attributeName="agentFirstName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="agentSurname">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="questionnaireNumber">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.view.mojo">
          <fmt:message key="View" />
          <mjl:property name="id" value="${item.concreteId}" />
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
<mjl:commandLink name="ITNHouseholdSurveyController.newInstance" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.newInstance.mojo">
  <fmt:message key="Create_a_new_ITN_household_survey" />
</mjl:commandLink>
