<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Molecular_Assay_Result_View_All" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.mo.MolecularAssayResultController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="definition">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTerm">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTermName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="oboNamespace">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termName">
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
