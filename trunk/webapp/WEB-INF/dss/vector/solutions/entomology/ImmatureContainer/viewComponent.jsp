<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page_title" value="Immatures_by_Container_Type"  scope="request"/>
<mjl:messages>
<mjl:message />
</mjl:messages>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<dl>
  <mjl:form name="form.name" id="ImmatureContainer.form" method="POST">
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />
      <mjl:input type="hidden" param="premiseId" value="${item.premiseId}" />
      <mjl:input type="hidden" param="taxonId" value="${item.taxonId}" />
       <mjl:dt attribute="geoEntity">
         <mdss:geo param="geoEntity" universals="${entityUniversals}" value="${entity}" />
       </mjl:dt>
      <mjl:dt attribute="startDate">
        <mjl:input param="startDate" type="text" classes="DatePick NoFuture" id="startDate"/>
       </mjl:dt>
      <mjl:dt attribute="endDate">
        <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
       </mjl:dt>
      <mjl:dt attribute="premiseType">
        <mdss:mo param="premiseType" value="${premiseType}"/>
      </mjl:dt>
      <mjl:dt attribute="taxon">
        <mdss:mo param="taxon" value="${taxon}"/>
      </mjl:dt>      
      
      <mjl:command name="Go" action="dss.vector.solutions.entomology.ImmatureContainerController.forward.mojo" value="Go"/>
         
      <hr />
      
       <mjl:dt attribute="collectionId">
         <mjl:input type="text" param="collectionId" id="collectionId"/>
       </mjl:dt>
       <mjl:dt attribute="notes">
         <mjl:textarea param="notes" rows="5" cols="15">${item.notes}</mjl:textarea>
       </mjl:dt>
       
       <hr />
       
       <mjl:dt attribute="numberExamined">
         <mjl:input type="text" param="numberExamined"/>
       </mjl:dt>
       <mjl:dt attribute="premiseSize">
         <mjl:input type="text" param="premiseSize"/>
       </mjl:dt>
       <mjl:dt attribute="numberInhabitants">
         <mjl:input type="text" param="numberInhabitants"/>
       </mjl:dt>
       
    </mjl:component>    
    
    <hr />
    
    <c:if test="${item.isContainerGridReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th>${item.containerGridMd.displayLabel}</th>
            <th>${relationship.numberContainersMd.displayLabel}</th>
            <th>${relationship.numberWithWaterMd.displayLabel}</th>
            <th>${relationship.numberDestroyedMd.displayLabel}</th>
            <th>${relationship.numberWithLarvicideMd.displayLabel}</th>
            <th>${relationship.numberImmaturesMd.displayLabel}</th>
            <th>${relationship.numberLarvaeMd.displayLabel}</th>
            <th>${relationship.numberPupaeMd.displayLabel}</th>
            <th>${relationship.numberLarvaeCollectedMd.displayLabel}</th>
            <th>${relationship.numberPupaeCollectedMd.displayLabel}</th>
            <th></th>
          </tr>
          <mjl:components items="${containers}" param="containers" var="current" varStatus="status">
            <mjl:input type="hidden" param="concreteId" value="${current.concreteId}"/>
            <mjl:input type="hidden" param="term" value="${current.term.id}"/>
            <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
              <td>
                ${current.term.displayLabel}
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberContainers" size="9" />
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberWithWater" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberDestroyed" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberWithLarvicide" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberImmatures" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberLarvae" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberPupae" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberLarvaeCollected" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberPupaeCollected" size="9"/>
              </td>
              <td>
                <mjl:messages attribute="*">
                  <mjl:message/> <br />
                </mjl:messages>
              </td>
            </tr>
          </mjl:components>
        </table>
      </dd>
    </c:if>
    
    <mjl:command name="Save" action="dss.vector.solutions.entomology.ImmatureContainerController.update.mojo" value="save"/>    
    <c:if test="${item.taxonId != ''}">
      <mjl:command name="Delete" action="dss.vector.solutions.entomology.ImmatureContainerController.delete.mojo" value="Delete"/>
    </c:if>
  </mjl:form>
</dl>