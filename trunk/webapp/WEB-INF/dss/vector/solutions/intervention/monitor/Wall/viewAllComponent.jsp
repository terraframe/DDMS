<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="View_All_Wall"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.WallController.viewPage.mojo" />
  <mjl:columns>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="wallName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>        
    <mjl:attributeColumn attributeName="parentWall">
      <mjl:row>
        ${item.parentWall != null ? item.parentWall.displayLabel : ''}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.WallController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Wall" action="dss.vector.solutions.intervention.monitor.WallController.newInstance.mojo" name="WallController.newInstance" />
