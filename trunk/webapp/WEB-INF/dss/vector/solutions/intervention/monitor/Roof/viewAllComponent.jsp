<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_Roofs"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.RoofController.viewPage.mojo" />
  <mjl:columns>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="roofName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="parentRoof">
      <mjl:row>
        ${item.parentRoof.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.RoofController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Roof" action="dss.vector.solutions.intervention.monitor.RoofController.newInstance.mojo" name="RoofController.newInstance" />
