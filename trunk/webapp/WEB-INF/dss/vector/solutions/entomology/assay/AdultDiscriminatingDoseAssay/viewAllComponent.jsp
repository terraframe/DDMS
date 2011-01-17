<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Edit_Adult_DDA"  scope="request"/>


<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewPage.mojo" />
  <mjl:columns>

    <mjl:attributeColumn attributeName="collectionLabel">
      <mjl:row>
        ${item.collectionLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="testDate">
      <mjl:row>
        <fmt:formatDate value="${item.testDate}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="testMethod">
      <mjl:row>
        ${item.testMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="insecticide">
      <mjl:row>
        ${item.insecticide.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="exposureTime">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="specie">
      <mjl:row>
        ${item.specie.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:row>
        ${item.identificationMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="sex">
      <mjl:row>
        ${item.sex.displayLabel}
      </mjl:row>    
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="quantityTested">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.concreteId}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo" name="AdultDiscriminatingDoseAssayController.newInstance">
  <mdss:localize key="Enter_new_data" />
</mjl:commandLink>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView" name="excelType"/>
</jsp:include>