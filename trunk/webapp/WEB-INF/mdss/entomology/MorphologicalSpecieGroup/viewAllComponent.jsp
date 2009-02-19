<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="mdss.entomology.MorphologicalSpecieGroupController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="collection">
      <mjl:header>
        MosquitoCollection
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:header>
        Identification Method
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantity">
      <mjl:header>
        Quantity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="specie">
      <mjl:header>
        Specie
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="mdss.entomology.MorphologicalSpecieGroupController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Morphological Specie Group" action="mdss.entomology.MorphologicalSpecieGroupController.newInstance.mojo" name="MorphologicalSpecieGroupController.newInstance" />
