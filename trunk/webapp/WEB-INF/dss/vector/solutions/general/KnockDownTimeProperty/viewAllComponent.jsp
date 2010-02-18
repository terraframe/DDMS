<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Manage_Knock_Down_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.KnockDownTimePropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="insecticide">
      <mjl:header>
        Insecticide
      </mjl:header>
      <mjl:row>
        ${item.insecticide.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lowerPercent">
      <mjl:header>
        Lower Percent
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lowerTime">
      <mjl:header>
        Lower Time
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="upperPercent">
      <mjl:header>
        Upper Percent
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="upperTime">
      <mjl:header>
        Upper Time
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.general.KnockDownTimePropertyController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create New" action="dss.vector.solutions.general.KnockDownTimePropertyController.newInstance.mojo" name="KnockDownTimePropertyController.newInstance">
  <fmt:message key="Create_New" />
</mjl:commandLink>
