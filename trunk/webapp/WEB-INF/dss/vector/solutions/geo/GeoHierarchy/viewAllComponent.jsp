<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
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
        <mjl:commandLink display="View" action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Geo Hierarchy" action="dss.vector.solutions.geo.GeoHierarchyController.newInstance.mojo" name="GeoHierarchyController.newInstance">
  <fmt:message key="Create_a_new_Geo_Hierarchy" />
</mjl:commandLink>
