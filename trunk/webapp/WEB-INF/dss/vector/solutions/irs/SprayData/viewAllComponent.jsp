<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.SprayDataController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="brand">
      <mjl:header>
        Insecticide Brand
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayDate">
      <mjl:header>
        Spray Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayMethod">
      <mjl:header>
        Spray Method
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="surfaceType">
      <mjl:header>
        surfaceType
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.SprayDataController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Spray Data" action="dss.vector.solutions.irs.SprayDataController.newInstance.mojo" name="SprayDataController.newInstance" />
