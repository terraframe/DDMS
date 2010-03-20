<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.LineStyleController.viewPage.mojo" />
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
        <mjl:commandLink display="View" action="dss.vector.solutions.LineStyleController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Line Style" action="dss.vector.solutions.LineStyleController.newInstance.mojo" name="LineStyleController.newInstance">
<fmt:message key="Create_a_new_Line_Style" />
</mjl:commandLink>
