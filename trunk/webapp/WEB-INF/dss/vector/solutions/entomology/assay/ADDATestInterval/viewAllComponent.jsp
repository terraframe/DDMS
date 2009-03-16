<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.entomology.assay.ADDATestIntervalController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="assay">
      <mjl:header>
        Adult Discriminating Dose Assay
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="knockedDown">
      <mjl:header>
        Knocked Down
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="period">
      <mjl:header>
        Period
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.assay.ADDATestIntervalController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Test Interval" action="dss.vector.solutions.entomology.assay.ADDATestIntervalController.newInstance.mojo" name="ADDATestIntervalController.newInstance" />
