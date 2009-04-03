<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="assay">
      <mjl:header>
        Larvae Assay
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="period">
      <mjl:header>
        Period
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantityDead">
      <mjl:header>
        Quantity Dead
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Test Interval" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.newInstance.mojo" name="LarvaeTestIntervalController.newInstance" />
