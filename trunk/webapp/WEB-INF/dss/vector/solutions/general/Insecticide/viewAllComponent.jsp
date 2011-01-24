<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Insecticide_Definitions" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.InsecticideController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="activeIngredient">
      <mjl:row>
        ${item.activeIngredient.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="amount">
      <mjl:row>
        ${item.amount}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="units">
      <mjl:row>
        ${item.units.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.general.Insecticide.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="dto.componentId" />
          <mdss:localize key="Delete" var="Localized_Delete" />
          <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.general.InsecticideController.delete.mojo" name="delete.button"  />
        </mjl:form>
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
<mjl:commandLink action="dss.vector.solutions.general.InsecticideController.newInstance.mojo" name="InsecticideController.newInstance" >
  <mdss:localize key="Define_Insecticide"/>
</mjl:commandLink>
