<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_TextElement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.query.TextElementController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="customTextElementId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontColor">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontFamily">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontStyle">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="textValue">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="textXPosition">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="textYPosition">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.query.TextElementController.view.mojo">
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
<mjl:commandLink name="TextElementController.newInstance" action="dss.vector.solutions.query.TextElementController.newInstance.mojo">
  <mdss:localize key="Create_a_new_TextElement" />
</mjl:commandLink>
