<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_MenuItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.MenuItemController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="disease">
      <mjl:row>
        <ul>
          <c:forEach items="${item.diseaseEnumNames}" var="enumName">
            <li>
              ${item.diseaseMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="term">
      <mjl:row>
        ${item.term.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="url">
      <mjl:row>
        ${item.url.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.general.MenuItemController.view.mojo">
          <fmt:message key="View" />
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
<mjl:commandLink name="MenuItemController.newInstance" action="dss.vector.solutions.general.MenuItemController.newInstance.mojo">
  <fmt:message key="Create_a_new_URL" />
</mjl:commandLink>
