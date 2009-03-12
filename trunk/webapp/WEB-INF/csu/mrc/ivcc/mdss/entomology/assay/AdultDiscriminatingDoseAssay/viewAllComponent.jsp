<%@ taglib
  uri="/WEB-INF/tlds/mojoLib.tld"
  prefix="mjl"%>
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context
    action="csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayController.viewPage.mojo" />
  <mjl:columns>
  
  <mjl:attributeColumn attributeName="collection">
      <mjl:header>
        Mosquito Collection
      </mjl:header>
       <mjl:row>
        ${item.collection.keyName}
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
     
    <mjl:attributeColumn attributeName="insecticide">
      <mjl:header>
        Insecticide
      </mjl:header>
      <mjl:row>
        ${item.insecticide.termName}
      </mjl:row>
    </mjl:attributeColumn>
       
    <mjl:attributeColumn attributeName="genericName">
      <mjl:header>
        Generic Name
      </mjl:header>
     </mjl:attributeColumn>
     
      <mjl:attributeColumn attributeName="amount">
      <mjl:header>
        Amount
      </mjl:header>
     <mjl:row>
        ${item.amount} 
        <c:forEach var="enumName" items="${item.unitsEnumNames}">
            ${item.unitsMd.enumItems[enumName]}
        </c:forEach>
      </mjl:row>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="exposureTime">
      <mjl:header>
        Exposure Time
      </mjl:header>
    </mjl:attributeColumn>
    
     <mjl:attributeColumn attributeName="specie">
      <mjl:header>
        Specie
      </mjl:header>
      <mjl:row >
        ${item.specie.termName}
      </mjl:row>
    </mjl:attributeColumn>
    
     <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:header>
        Identification Method
      </mjl:header>
      <mjl:row >
        ${item.identificationMethod.termName}
      </mjl:row>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="sex">
      <mjl:header>
        Sex
      </mjl:header>
    </mjl:attributeColumn>
    
    <mjl:attributeColumn attributeName="quantityTested">
      <mjl:header>
        Quantity Tested
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row >
        <mjl:commandLink
          display="View"
          action="csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo"
          name="view.link">
          <mjl:property
            value="${item.id}"
            name="id" />
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
<mjl:commandLink
  display="Create a new Adult Discriminating Dose Assay"
  action="csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo"
  name="AdultDiscriminatingDoseAssayController.newInstance" />
