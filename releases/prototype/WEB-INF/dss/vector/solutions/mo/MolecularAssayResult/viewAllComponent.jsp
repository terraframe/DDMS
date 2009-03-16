<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.mo.MolecularAssayResultController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="definition">
      <mjl:header>
        Definition
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
      <mjl:header>
        Display Label
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
      <mjl:header>
        Enabled
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTerm">
      <mjl:header>
        Inherits Term
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTermName">
      <mjl:header>
        Inherits Term Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="oboNamespace">
      <mjl:header>
        Namespace
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termId">
      <mjl:header>
        Term Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termName">
      <mjl:header>
        Term Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.mo.MolecularAssayResultController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Molecular Assay Result" action="dss.vector.solutions.mo.MolecularAssayResultController.newInstance.mojo" name="MolecularAssayResultController.newInstance" />
