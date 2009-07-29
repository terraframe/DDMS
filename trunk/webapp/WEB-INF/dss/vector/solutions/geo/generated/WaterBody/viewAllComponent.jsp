<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.geo.generated.WaterBodyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="multiPolygon">
      <mjl:header>
        Multi Polygon
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="activated">
      <mjl:header>
        Activated
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="entityName">
      <mjl:header>
        Geo Entity Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gazId">
      <mjl:header>
        Gaz Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoId">
      <mjl:header>
        Geo Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.geo.generated.WaterBodyController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Water body" action="dss.vector.solutions.geo.generated.WaterBodyController.newInstance.mojo" name="WaterBodyController.newInstance" />
