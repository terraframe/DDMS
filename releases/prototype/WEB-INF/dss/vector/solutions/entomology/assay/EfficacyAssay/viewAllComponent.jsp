<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
        <mjl:row >
        ${item.geoEntity.entityName}
      </mjl:row>
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
    
     <mjl:attributeColumn attributeName="specie">
      <mjl:header>
        Specie
      </mjl:header>
    </mjl:attributeColumn>
  
    <mjl:attributeColumn attributeName="colonyName">
      <mjl:header>
        Colony Name
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
    
     <mjl:attributeColumn attributeName="sex">
      <mjl:header>
        Sex
      </mjl:header>
    </mjl:attributeColumn>
       
    <mjl:attributeColumn attributeName="gravid">
      <mjl:header>
        Gravid
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="fed">
      <mjl:header>
        Fed
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="insecticide">
      <mjl:header>
        Insecticide
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="insecticideLength">
      <mjl:header>
        Insecticide Time Length
      </mjl:header>
    </mjl:attributeColumn>
        
    <mjl:attributeColumn attributeName="surfacePostion">
      <mjl:header>
        Surface Position
      </mjl:header>
    </mjl:attributeColumn>    
        
   <mjl:attributeColumn attributeName="exposureTime">
      <mjl:header>
        Exposure Time (m)
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="holdingTime">
      <mjl:header>
        Holding Time (hr)
      </mjl:header>
    </mjl:attributeColumn>
        
    <mjl:attributeColumn attributeName="quantityLive">
      <mjl:header>
        Quantity Live
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="quantityDead">
      <mjl:header>
        Quantity Dead
      </mjl:header>
    </mjl:attributeColumn>
        
    <mjl:attributeColumn attributeName="mortality">
      <mjl:header>
        Mortality Rate
      </mjl:header>
    </mjl:attributeColumn>

  <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Efficacy Assay" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.newInstance.mojo" name="EfficacyAssayController.newInstance" />
