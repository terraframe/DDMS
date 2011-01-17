<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.TextStyleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="fill">
      <mjl:header>
        Fill
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontFamily">
      <mjl:header>
        Font Family
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontSize">
      <mjl:header>
        Font Size
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fontStyle">
      <mjl:header>
        Font Style
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.query.TextStyleController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.query.TextStyleController.newInstance.mojo" name="TextStyleController.newInstance">
<mdss:localize key="Create_a_new_Text_Style" />
</mjl:commandLink>
