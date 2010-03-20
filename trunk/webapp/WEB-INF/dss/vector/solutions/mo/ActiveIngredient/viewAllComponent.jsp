<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Active_Ingredient_View_All" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.mo.ActiveIngredientController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="definition">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTerm">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTermName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="oboNamespace">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.mo.ActiveIngredientController.view.mojo" name="view.link">
          <fmt:message key="View" />
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
<mjl:commandLink display="Create a new Active Ingredient" action="dss.vector.solutions.mo.ActiveIngredientController.newInstance.mojo" name="ActiveIngredientController.newInstance" />
