<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_OtherWall" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.geo.generated.OtherWallController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="point">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="activated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="entityName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gazId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoId">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.geo.generated.OtherWallController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.geo.generated.OtherWallController.newInstance.mojo" name="OtherWallController.newInstance">
  <fmt:message key="Create_a_new_Other_wall" />
</mjl:commandLink>
