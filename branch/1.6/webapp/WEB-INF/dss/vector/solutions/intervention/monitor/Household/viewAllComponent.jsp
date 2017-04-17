<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.intervention.monitor.HouseholdController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="householdName">
      <mjl:header>
        name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastSprayed">
      <mjl:header>
        Months since last sprayed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="nets">
      <mjl:header>
        Nets
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="netsUsed">
      <mjl:header>
        Nets Used
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="people">
      <mjl:header>
        Number of people
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="roof">
      <mjl:header>
        Roof Surface
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="roofInfo">
      <mjl:header>
        Roof Information
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rooms">
      <mjl:header>
        Rooms
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sleptUnderNets">
      <mjl:header>
        Slept Under Nets
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="surveyPoint">
      <mjl:header>
        Survey Point
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="urban">
      <mjl:header>
        Household Type
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="wall">
      <mjl:header>
        Wall Surface
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="wallInfo">
      <mjl:header>
        Wall Information
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="windowType">
      <mjl:header>
        WindowType
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.newInstance.mojo" name="HouseholdController.newInstance">
<mdss:localize key="Create_a_new_Household" />
</mjl:commandLink>
