<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.ActorSprayStatusController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="bedNets">
      <mjl:header>
        Total number of bed nets
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="households">
      <mjl:header>
        Total # of households
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="locked">
      <mjl:header>
        Totals for reasons for not spraying (locked)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="other">
      <mjl:header>
        Totals for reasons for not spraying (other)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="people">
      <mjl:header>
        Number of people protected
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="prevSprayedHouseholds">
      <mjl:header>
        Households sprayed last year
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="prevSprayedStructures">
      <mjl:header>
        Structures sprayed last year
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="refused">
      <mjl:header>
        Totals for reasons for not spraying (refused)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="rooms">
      <mjl:header>
        Total # of rooms
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="roomsWithBedNets">
      <mjl:header>
        Total number of rooms with bed nets
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="spray">
      <mjl:header>
        Spray
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayedHouseholds">
      <mjl:header>
        Total # of households sprayed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayedRooms">
      <mjl:header>
        Total # of rooms sprayed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayedStructures">
      <mjl:header>
        Total # of structures sprayed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="structures">
      <mjl:header>
        Total # of structures
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.ActorSprayStatusController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Household Spray Status" action="dss.vector.solutions.irs.ActorSprayStatusController.newInstance.mojo" name="ActorSprayStatusController.newInstance" />
