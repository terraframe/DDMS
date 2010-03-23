<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.GeometryStyleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="stroke">
      <mjl:header>
        Stroke
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="strokeWidth">
      <mjl:header>
        Stroke Width
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.query.GeometryStyleController.view.mojo" name="view.link">
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
<mjl:commandLink  action="dss.vector.solutions.query.GeometryStyleController.newInstance.mojo" name="GeometryStyleController.newInstance">
<fmt:message key="Create_a_new_Geometry_Style" />
</mjl:commandLink>
