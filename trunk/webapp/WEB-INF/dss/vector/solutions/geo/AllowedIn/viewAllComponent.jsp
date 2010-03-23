<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.geo.AllowedInController.viewPage.mojo" />
  <mjl:columns>
    <mjl:freeColumn>
      <mjl:header>
        Geo Hierarchy
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="parent.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        Geo Hierarchy
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="child.link">
          <mjl:property value="${item.childId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.geo.AllowedInController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.geo.AllowedInController.newRelationship.mojo" name="AllowedInController.newRelationship">
  <fmt:message key="Create_a_new_Allowed_In" />
</mjl:commandLink>
