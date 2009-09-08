<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.ThematicLayerController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoHierarchy">
      <mjl:header>
        GeoHierarchy Reference
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geometryStyle">
      <mjl:header>
        Geometry Style
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sldFile">
      <mjl:header>
        SLD File
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="textStyle">
      <mjl:header>
        Text Style
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.query.ThematicLayerController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Thematic Layer" action="dss.vector.solutions.query.ThematicLayerController.newInstance.mojo" name="ThematicLayerController.newInstance" />
