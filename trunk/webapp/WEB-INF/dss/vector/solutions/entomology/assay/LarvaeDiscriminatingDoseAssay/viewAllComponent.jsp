<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="<%=request.getLocale()%>" />
<c:set var="page_title" value="Larvae_DDA"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}"  classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewPage.mojo" />
  <mjl:columns>

  <mjl:attributeColumn attributeName="collection">
       <mjl:row>
        ${item.collection.displayLabel}
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
      <mjl:row >
        ${item.specie.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

     <mjl:attributeColumn attributeName="identificationMethod">
      <mjl:row >
        ${item.identificationMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>


    <mjl:attributeColumn attributeName="quantityTested">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row >
        <mjl:commandLink
          display="View"
          action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.view.mojo"
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
<mjl:commandLink display="Create a new Larvae Discriminating Dose Assay" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" name="LarvaeDiscriminatingDoseAssayController.newInstance" />

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView" name="excelType"/>
</jsp:include>