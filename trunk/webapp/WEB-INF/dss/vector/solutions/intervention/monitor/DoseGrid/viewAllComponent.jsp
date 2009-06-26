<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:set var="page_title" value="View_All_Dose_Grids"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.DoseGridController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="active">
    </mjl:attributeColumn>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="optionName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.DoseGridController.view.mojo" name="view.link">                    
          <mjl:property value="${item.id}" name="id" />
          <fmt:message key="View"/>
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
<mjl:commandLink display="Create a new Dose Grid" action="dss.vector.solutions.intervention.monitor.DoseGridController.newInstance.mojo" name="DoseGridController.newInstance" />
