<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.ThematicVariableController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="attributeName">
      <mjl:header>
        Attribute Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
      <mjl:header>
        Display Label
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="entityAlias">
      <mjl:header>
        Entity Alias
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.query.ThematicVariableController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Thematic Variable View" action="dss.vector.solutions.query.ThematicVariableController.newInstance.mojo" name="ThematicVariableController.newInstance" />
