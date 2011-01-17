<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
        <mjl:commandLink action="dss.vector.solutions.query.ThematicVariableController.view.mojo" name="view.link">
          <mdss:localize key="View" />
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
<mjl:commandLink action="dss.vector.solutions.query.ThematicVariableController.newInstance.mojo" name="ThematicVariableController.newInstance">
<mdss:localize key="Create_a_new_Thematic_Variable_View" />
</mjl:commandLink>
