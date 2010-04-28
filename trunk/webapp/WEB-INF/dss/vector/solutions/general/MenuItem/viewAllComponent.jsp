<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<c:set scope="request" var="page_title" value="View_All_MenuItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<dl style="width: 500px">
  <mjl:form id="dss.vector.solutions.general.DiseaseMaster.form.id" name="dss.vector.solutions.general.DiseaseMaster.form.name" method="POST">
    <mjl:input param="id" value="${diseaseMaster.id}" type="hidden" />
    <mjl:component param="dto" item="${diseaseMaster}">
      <mjl:dt attribute="menuRoot">
        ${diseaseMaster.menuRoot.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command localize="false" name="dss.vector.solutions.general.DiseaseMaster.form.create.button" value="Edit" action="dss.vector.solutions.general.MenuItemController.editDisease.mojo" />
  </mjl:form>
</dl>

<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.MenuItemController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="termDisplay">
      <mjl:row>
        ${item.termDisplay}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="urlDisplay">
      <mjl:row>
        ${item.urlDisplay}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.general.MenuItemController.view.mojo">
          <fmt:message key="View" />
          <mjl:property name="id" value="${item.menuItemId}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
<br />
<mjl:commandLink name="MenuItemController.newInstance" action="dss.vector.solutions.general.MenuItemController.newInstance.mojo">
  <fmt:message key="Create_a_new_URL" />
</mjl:commandLink>
