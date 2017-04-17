<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Roles_View_All_Users" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.RoleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="firstName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lastName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="username">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.RoleController.${item.userId}" method="POST" >
          <mjl:input type="hidden" param="id" value="${item.userId}"/>
          <mdss:localize key="Roles_Edit_Button" var="Localized_Roles_Edit_Button" />
          <mjl:command value="${Localized_Roles_Edit_Button}" name="dss.vector.solutions.RoleController.edit.button" action="dss.vector.solutions.RoleController.edit.mojo" />
        </mjl:form>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>