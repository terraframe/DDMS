<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_CategoryGen" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.query.CategoryGenController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="categoryCount">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="factoryType">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="layerId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStrokeEnd">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStrokeStart">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="precisionFigures">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.query.CategoryGenController.view.mojo">
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
<mjl:commandLink name="CategoryGenController.newInstance" action="dss.vector.solutions.query.CategoryGenController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Category_Gen" />
</mjl:commandLink>
