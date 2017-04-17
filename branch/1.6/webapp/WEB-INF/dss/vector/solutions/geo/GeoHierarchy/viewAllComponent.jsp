<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.geo.GeoHierarchyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoEntityClass">
      <mjl:header>
        Geo Entity Class
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="political">
      <mjl:header>
        political
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.newInstance.mojo" name="GeoHierarchyController.newInstance">
  <mdss:localize key="Create_a_new_Geo_Hierarchy" />
</mjl:commandLink>
