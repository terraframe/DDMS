<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:header>
        End Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberNatalCare">
      <mjl:header>
        Number of Ante Natal Care visits for period
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberPregnant">
      <mjl:header>
        Number of pregnant females who received IPT
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberPregnantITN">
      <mjl:header>
        Number of pregnant females who received ITN when receiving IPT
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberPregnantIron">
      <mjl:header>
        Number of pregnant females who received iron supplement when receiving IPT
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:header>
        Start Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="totalITN">
      <mjl:header>
        Total number of ITNs distributed during IPT administration
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.newInstance.mojo" name="AggregatedIPTController.newInstance">
<mdss:localize key="Create_a_new Aggregated_IPT_Information" />
</mjl:commandLink>
