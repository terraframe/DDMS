<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_MdWebText" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="com.runwaysdk.system.metadata.MdWebTextController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="height">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="width">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definingMdAttribute">
      <mjl:row>
        ${item.definingMdAttribute.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definingMdForm">
      <mjl:row>
        ${item.definingMdForm.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fieldName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fieldOrder">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="required">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="remove">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="com.runwaysdk.system.metadata.MdWebTextController.view.mojo">
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
<mjl:commandLink name="MdWebTextController.newInstance" action="com.runwaysdk.system.metadata.MdWebTextController.newInstance.mojo">
  <mdss:localize key="Create_a_new_" />
</mjl:commandLink>
