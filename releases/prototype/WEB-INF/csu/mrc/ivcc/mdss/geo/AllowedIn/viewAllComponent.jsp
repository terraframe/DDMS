<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="csu.mrc.ivcc.mdss.geo.AllowedInController.viewPage.mojo" />
  <mjl:columns>
    <mjl:freeColumn>
      <mjl:header>
        Geo Hierarchy
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.parent.keyName}" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.view.mojo" name="parent.link">
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
        <mjl:commandLink display="${item.child.keyName}" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.view.mojo" name="child.link">
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
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.geo.AllowedInController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Allowed In" action="csu.mrc.ivcc.mdss.geo.AllowedInController.newRelationship.mojo" name="AllowedInController.newRelationship" />
