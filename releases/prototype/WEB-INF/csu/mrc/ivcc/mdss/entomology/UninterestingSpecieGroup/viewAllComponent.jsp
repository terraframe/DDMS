<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="quanity">
      <mjl:header>
        Quanity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="collection">
      <mjl:header>
        Mosquito Collection
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="collectionId">
      <mjl:header>
        Collection Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:header>
        Identification Method
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
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Uninteresting Specie" action="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupController.newInstance.mojo" name="UninterestingSpecieGroupController.newInstance" />
