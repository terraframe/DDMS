<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.entomology.assay.InsecticideAssayController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="amount">
      <mjl:header>
        Amount
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="genericName">
      <mjl:header>
        Generic Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="insecticide">
      <mjl:header>
        Insecticide
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="units">
      <mjl:header>
        Units
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:structColumn attributeName="ageRange">
      <mjl:header>
        Age Range
      </mjl:header>
      <mjl:attributeColumn attributeName="endPoint">
        <mjl:header>
          Ending Age
        </mjl:header>
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="startPoint">
        <mjl:header>
          Beginning Age
        </mjl:header>
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="exposureTime">
      <mjl:header>
        Exposure Time (m)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fed">
      <mjl:header>
        Fed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="generation">
      <mjl:header>
        Generation
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="gravid">
      <mjl:header>
        Gravid
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantityTested">
      <mjl:header>
        Quantity Tested
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:header>
        Identification Method
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isofemale">
      <mjl:header>
        Isofemale
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sex">
      <mjl:header>
        Sex
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="specie">
      <mjl:header>
        Specie
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="testDate">
      <mjl:header>
        Test Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="testMethod">
      <mjl:header>
        Test Method
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.assay.InsecticideAssayController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Insecticide Assay" action="dss.vector.solutions.entomology.assay.InsecticideAssayController.newInstance.mojo" name="InsecticideAssayController.newInstance" />
