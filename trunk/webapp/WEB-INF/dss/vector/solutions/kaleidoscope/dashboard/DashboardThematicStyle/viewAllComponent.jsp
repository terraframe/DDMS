<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_DashboardThematicStyle" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="bubbleContinuousSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleMaxSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleMinSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleRotation">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="bubbleWellKnownName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointStyles">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPointWellKnownName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPolygonFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPolygonStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPolygonStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPolygonStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="categoryPolygonStyles">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointMaxFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointMinFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPointWellKnownName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonMaxFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonMinFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gradientPolygonStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numBubbleSizeCategories">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numGradientPointCategories">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numGradientPolygonCategories">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="secondaryAggregationType">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${item.secondaryAggregationTypeEnumNames}">
            <li>
              ${item.secondaryAggregationTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="secondaryAttribute">
      <mjl:row>
        ${item.secondaryAttribute.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="secondaryCategories">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="basicPointSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enableLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enableValue">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelColor">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelFont">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelHalo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelHaloWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lineOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lineStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lineStrokeCap">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lineStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="name">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointRotation">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointWellKnownName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueColor">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueFont">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueHalo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueHaloWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueSize">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyleController.view.mojo">
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
<mjl:commandLink name="DashboardThematicStyleController.newInstance" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyleController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Thematic_Attribute" />
</mjl:commandLink>
