<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_PupalContainer" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.PupalContainerController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="containerId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="containerLength">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="containerType">
      <mjl:row>
        ${item.containerType.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="diameter">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="drawdownFrequency">
      <mjl:row>
        ${item.drawdownFrequency.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="drawdownPercent">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fillFrequency">
      <mjl:row>
        ${item.fillFrequency.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fillMethod">
      <mjl:row>
        ${item.fillMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="height">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lid">
      <mjl:row>
        ${item.lid.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="openingDiameter">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="openingLength">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="openingWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="premise">
      <mjl:row>
        ${item.premise.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="roof">
      <mjl:row>
        ${item.roof.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="shading">
      <mjl:row>
        ${item.shading.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="shape">
      <mjl:row>
        <ul>
          <c:forEach items="${item.shapeEnumNames}" var="enumName">
            <li>
              ${item.shapeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="width">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.entomology.PupalContainerController.view.mojo">
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
<mjl:commandLink name="PupalContainerController.newInstance" action="dss.vector.solutions.entomology.PupalContainerController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Pupal_container" />
</mjl:commandLink>
