<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="View_All_Net"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.NetController.viewPage.mojo" />
  <mjl:columns>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isAbstract">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="netName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="parentNet">
      <mjl:row>
        ${item.parentNet != null ? item.parentNet.displayLabel : ''}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.NetController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Net" action="dss.vector.solutions.intervention.monitor.NetController.newInstance.mojo" name="NetController.newInstance" />
