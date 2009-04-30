<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.InsecticideBrandController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="activeIngredient">
      <mjl:header>
        Active Ingredient
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="amount">
      <mjl:header>
        Concentration
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
      <mjl:header>
        Enabled
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sachetsPerRefill">
      <mjl:header>
        Sachets per Refill
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="units">
      <mjl:header>
        Units
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="weight">
      <mjl:header>
        Weight (g)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new InsecticideBrand" action="dss.vector.solutions.irs.InsecticideBrandController.newInstance.mojo" name="InsecticideBrandController.newInstance" />
