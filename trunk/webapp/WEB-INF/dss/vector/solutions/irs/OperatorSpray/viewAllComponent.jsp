<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.OperatorSprayController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="operatorSprayWeek">
      <mjl:header>
        Operator Spray Week
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="received">
      <mjl:header>
        Number of sachets received
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="refills">
      <mjl:header>
        Number of pump/can refills
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="returned">
      <mjl:header>
        Number of sachets returned
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayOperator">
      <mjl:header>
        Operator
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="used">
      <mjl:header>
        Number of sachets used
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="target">
      <mjl:header>
        Target
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="teamSprayWeek">
      <mjl:header>
        Team Spray Week
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayData">
      <mjl:header>
        Spray Data
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.OperatorSprayController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Operator Spray" action="dss.vector.solutions.irs.OperatorSprayController.newInstance.mojo" name="OperatorSprayController.newInstance">
<fmt:message key="Create_a_new_Operator_Spray" />
</mjl:commandLink>
