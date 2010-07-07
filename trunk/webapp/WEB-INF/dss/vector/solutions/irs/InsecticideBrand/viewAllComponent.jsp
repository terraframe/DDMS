<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_InsecticideBrand" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.irs.InsecticideBrandController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="activeIngredient">
      <mjl:row>
        ${item.activeIngredient.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="concentrationQualifier">
      <mjl:row>
        <ul>
          <c:forEach items="${item.concentrationQualifierEnumNames}" var="enumName">
            <li>
              ${item.concentrationQualifierMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="concentrationQuantifier">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="disease">
      <mjl:row>
        ${item.disease.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="insecticideUse">
      <mjl:row>
        <ul>
          <c:forEach items="${item.insecticideUseEnumNames}" var="enumName">
            <li>
              ${item.insecticideUseMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="productName">
      <mjl:row>
        ${item.productName.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="unitQualifier">
      <mjl:row>
        <ul>
          <c:forEach items="${item.unitQualifierEnumNames}" var="enumName">
            <li>
              ${item.unitQualifierMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="unitQuantifier">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="unitsPerApplication">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="useDetail">
      <mjl:row>
        ${item.useDetail.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo">
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
<mjl:commandLink name="InsecticideBrandController.newInstance" action="dss.vector.solutions.irs.InsecticideBrandController.newInstance.mojo">
  <fmt:message key="Create_a_new_Insecticide_brand" />
</mjl:commandLink>
