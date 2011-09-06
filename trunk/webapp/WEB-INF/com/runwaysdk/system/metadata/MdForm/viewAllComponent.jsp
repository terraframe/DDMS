<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_MdForm" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="com.runwaysdk.system.metadata.MdFormController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="formMdClass">
      <mjl:row>
        ${item.formMdClass.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="formName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="exported">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="jsBase">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="jsStub">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="packageName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="typeName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="remove">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="com.runwaysdk.system.metadata.MdFormController.view.mojo">
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
<mjl:commandLink name="MdFormController.newInstance" action="com.runwaysdk.system.metadata.MdFormController.newInstance.mojo">
  <mdss:localize key="Create_a_new_" />
</mjl:commandLink>
