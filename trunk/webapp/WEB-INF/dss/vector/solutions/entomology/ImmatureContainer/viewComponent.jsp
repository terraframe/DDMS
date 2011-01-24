<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page_title" value="Enter_Immatures_by_Container_Type"  scope="request"/>

<!-- Removed because MdInformation messages were being duplicated in the header and in the popups
<mjl:messages>
<mjl:message />
</mjl:messages>
  -->
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
      
      <button id="button.go"><mdss:localize key="Go" /></button>
         
      <hr />
      
       <mjl:dt attribute="collectionId">
         <mjl:input type="text" param="collectionId" classes="mutable" id="collectionId"/>
       </mjl:dt>
       <mjl:dt attribute="notes">
         <mjl:textarea param="notes" classes="mutable" rows="5" cols="15">${item.notes}</mjl:textarea>
       </mjl:dt>
       
       <hr />
       
       <mjl:dt attribute="numberExamined">
         <mjl:input type="text" classes="mutable" param="numberExamined"/>
       </mjl:dt>
       <mjl:dt attribute="numberWithLarvae">
         <mjl:input type="text" classes="mutable" param="numberWithLarvae"/>
       </mjl:dt>
       <mjl:dt attribute="numberWithPupae">
         <mjl:input type="text" classes="mutable" param="numberWithPupae"/>
       </mjl:dt>
       <mjl:dt attribute="numberWithImmatures">
         <mjl:input type="text" classes="mutable" param="numberWithImmatures"/>
       </mjl:dt>
       <mjl:dt attribute="premiseSize">
         <mjl:input type="text" classes="mutable" param="premiseSize"/>
       </mjl:dt>
       <mjl:dt attribute="numberInhabitants">
         <mjl:input type="text" classes="mutable" param="numberInhabitants"/>
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
                <mjl:input type="text" param="numberContainers" classes="mutable" size="9" />
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberWithWater" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberDestroyed" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberWithLarvicide" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberImmatures" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberLarvae" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberPupae" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberLarvaeCollected" classes="mutable" size="9"/>
              </td>
              <td class="integerColumn">
                <mjl:input type="text" param="numberPupaeCollected" classes="mutable" size="9"/>
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
    
    <mdss:localize key="save" var="Localized_save" />
    
    <mjl:command name="Save" action="dss.vector.solutions.entomology.ImmatureContainerController.update.mojo" value="${Localized_save}" />    
    <c:if test="${item.taxonId != ''}">
      <mdss:localize key="Delete" var="Localized_Delete" />
      <mjl:command name="Delete" action="dss.vector.solutions.entomology.ImmatureContainerController.delete.mojo" value="${Localized_Delete}" />
    </c:if>
  </mjl:form>
</dl>

<script>
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var dirty = false;

    var valueChange = function() {
      dirty = true;
    };
    
    var mutables = YAHOO.util.Dom.getElementsByClassName("mutable");
     
    for each (el in mutables) {
      YAHOO.util.Event.on(el, 'change', valueChange);
    }

    var submitForm = function() {
      var formEl = document.getElementById("ImmatureContainer.form");
      formEl.action = "dss.vector.solutions.entomology.ImmatureContainerController.forward.mojo";
      formEl.submit();        
    }

    var onGoHandler = function(e) {
      if(dirty) {
        var x=window.confirm(MDSS.localize("Unsaved_Data"));

        if (x) {
          submitForm();
        }
        else {
          YAHOO.util.Event.preventDefault(e);
        }        
      }    
      else {
        submitForm();          
      }
    }

    YAHOO.util.Event.on('button.go', 'click', onGoHandler);          
  });
})();

</script>