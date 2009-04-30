<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.PointStyleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="wellKnownName">
      <mjl:header>
        Well Known Name
      </mjl:header>
    </mjl:attributeColumn>
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
        <mjl:commandLink display="View" action="dss.vector.solutions.PointStyleController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Point Style" action="dss.vector.solutions.PointStyleController.newInstance.mojo" name="PointStyleController.newInstance" />
