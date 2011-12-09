<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.business.ComponentQueryDTO"%>
<c:set var="page_title" value="View_All_Efficacy_bioassay"  scope="request"/>

<fmt:setLocale value="<%=request.getLocale()%>" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:table var="item" query="${query}" classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoId">
        <mjl:row >
        ${item.geoId}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="surfaceType">
      <mjl:row >
          ${item.surfaceType.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
   <mjl:attributeColumn attributeName="testDate">
      <mjl:row>
        <fmt:formatDate value="${item.testDate}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="testMethod">
       <mjl:row >
        ${item.testMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

     <mjl:attributeColumn attributeName="specie">
      <mjl:row >
        ${item.specie.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="colonyName">
    </mjl:attributeColumn>

    <mjl:structColumn attributeName="ageRange">
      <mjl:attributeColumn attributeName="startPoint">
      </mjl:attributeColumn>
      <mjl:attributeColumn attributeName="endPoint">
      </mjl:attributeColumn>
    </mjl:structColumn>

    <mjl:attributeColumn attributeName="sex">
      <mjl:row>
        ${item.sex.displayLabel}
      </mjl:row>         
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="gravid">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="fed">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="insecticide">
      <mjl:row >
        ${item.insecticide.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="exposureTime">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="surfacePostion">
      <mjl:row>
        ${item.surfacePostion.displayLabel}
      </mjl:row>    
    </mjl:attributeColumn>

   <mjl:attributeColumn attributeName="timeOnSurface">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="holdingTime">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="quantityLive">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="quantityDead">
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="mortality">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2">${item.mortality}</fmt:formatNumber>
      </mjl:row>
    </mjl:attributeColumn>

  <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.entomology.assay.EfficacyAssayController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.entomology.assay.EfficacyAssayController.newInstance.mojo" name="EfficacyAssayController.newInstance">
  <mdss:localize key="Create_a_new_Efficacy_Assay" />
</mjl:commandLink>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.EfficacyAssayExcelView" name="excelType"/>
</jsp:include>