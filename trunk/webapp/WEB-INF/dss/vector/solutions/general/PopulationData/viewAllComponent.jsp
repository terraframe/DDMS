<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_PopulationData" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.PopulationDataController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="estimated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.geoId}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="growthRate">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="population">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="yearOfData">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.general.PopulationDataController.view.mojo">
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
<mjl:commandLink name="PopulationDataController.newInstance" action="dss.vector.solutions.general.PopulationDataController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Population_Data" />
</mjl:commandLink>
