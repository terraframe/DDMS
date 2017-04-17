<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_DashboardThematicLayer" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="aggregationStrategy">
      <mjl:row>
        ${item.aggregationStrategy.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="aggregationType">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${item.aggregationTypeEnumNames}">
            <li>
              ${item.aggregationTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoNode">
      <mjl:row>
        ${item.geoNode.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="mdAttribute">
      <mjl:row>
        ${item.mdAttribute.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="BBoxIncluded">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="activeByDefault">
    </mjl:attributeColumn>
    <mjl:structColumn attributeName="dashboardLegend">
      <mjl:header>
        
      </mjl:header>
      <mjl:attributeColumn attributeName="groupedInLegend">
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="legendXPosition">
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="legendYPosition">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="dashboardMap">
      <mjl:row>
        ${item.dashboardMap.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayInLegend">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastPublishDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.lastPublishDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="layerEnabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="layerType">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${item.layerTypeEnumNames}">
            <li>
              ${item.layerTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="nameLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="viewName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="virtual">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
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
<mjl:commandLink name="DashboardThematicLayerController.newInstance" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Thematic_Layer" />
</mjl:commandLink>
