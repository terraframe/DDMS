<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionController"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionViewDTO"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.LifeStageDTO"%>
<%@page import="java.util.List"%>

<c:set var="page_title" value="Create_Mosquito_Collection"  scope="request"/>
<mjl:messages>
<mjl:message />
</mjl:messages>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />

<dl>
  <mjl:form name="form.name" id="MosquitoCollection.form" method="POST">
    <input type="hidden" name="collection_id" value="${item.concreteId}" id="collection_id"/>
    <input type="hidden" value="${collectionMethod.id}" id="original.collectionMethod"/>
    <input type="hidden" value="${item.collectionDate}" id="original.collectionDate" class="DatePick"/>
    <input type="hidden" value="${entity.id}" id="original.geoEntity"/>
    <input type="hidden" value="${currentLifeStage}" id="original.lifeStage"/>
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" id="concreteId"/>
      <mjl:dt attribute="collectionMethod">
        <mdss:mo param="collectionMethod" value="${collectionMethod}"/>
      </mjl:dt>
      <mjl:dt attribute="collectionDate">
        <mjl:input param="collectionDate" type="text" classes="DatePick NoFuture" id="collectionDate"/>
       </mjl:dt>
       <mjl:dt attribute="geoEntity">
         <mdss:geo param="geoEntity" universals="${entityUniversals}" value="${entity}" />
       </mjl:dt>
       <mjl:dt attribute="lifeStage">
         <mjl:select param="lifeStage" items="${lifeStage}" var="current" valueAttribute="enumName" id="lifeStage">
           <mjl:option selected="${mjl:contains(item.lifeStageEnumNames, current.enumName) ? 'selected' : 'false'}">
             ${item.lifeStageMd.enumItems[current.enumName]}
           </mjl:option>
         </mjl:select>
       </mjl:dt> 
       <mjl:command name="Go" action="dss.vector.solutions.entomology.MosquitoCollectionController.forward.mojo" value="Go"/>
       <hr />
       <mjl:dt attribute="collectionId">
         <mjl:input type="text" param="collectionId" id="collectionId"/>
       </mjl:dt>
       <mjl:dt attribute="abundance">
         <mdss:selectBoolean param="abundance" id="abundance" includeBlank="true" value="${item.abundance == null ? '' : item.abundance}"/>
       </mjl:dt>
    </mjl:component>
    <mjl:command name="ada.button" id="ada.button" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo" value="Adult_DDA"/>
    <mjl:command name="lda.button" id="lda.button" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" value="Larvae_DDA"/>
    <mjl:command name="kda.button" id="kda.button" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" value="Knock_Down_Assay"/>
    <mjl:command name="ia.button" id="ia.button" action="dss.vector.solutions.entomology.AssayController.getInfectionAssays.mojo" value="Infection_Assays"/>
    <mjl:command name="ma.button" id="ma.button" action="dss.vector.solutions.entomology.AssayController.getMechanismAssays.mojo" value="Mechanism_Assays"/>
    <mjl:command name="delete" id="delete.button" action="dss.vector.solutions.entomology.MosquitoCollectionController.delete.mojo" value="Delete"/>
  </mjl:form>
</dl>

<div id="SubCollection"></div>
<br />

<c:if test="${ada.count > 0 || lda.count > 0 || kda.count > 0}">
<h2><fmt:message key="Collection_Assays"/></h2>
<dl>
  <c:if test="${ada.count > 0}">
  <dt>
    <fmt:message key="Adult_DDA" />
  </dt>
  <dd>
    <mjl:table var="current" query="${ada}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.sortAssays.mojo" >
        <mjl:property name="collectionId" value="${item.concreteId}"/>
        <mjl:property name="assayType" value="ada"/>
      </mjl:context>
      <mjl:columns>
        <mjl:attributeColumn attributeName="testDate">
          <mjl:row>
            <span class="formatDate">${current.testDate}</span>
          </mjl:row>        
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="insecticide">
          <mjl:row>
            ${current.insecticide.displayLabel}
          </mjl:row>
        </mjl:attributeColumn>
        <mjl:freeColumn>
          <mjl:header>
          </mjl:header>
          <mjl:row>
            <mjl:commandLink action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo" name="view.link">
              <fmt:message key="View"/>
              <mjl:property value="${current.id}" name="id" />
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
  </dd>
  </c:if>
  <c:if test="${lda.count > 0}">  
  <dt>
    <fmt:message key="Larvae_DDA" />
  </dt>
  <dd>
    <mjl:table var="current" query="${lda}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.sortAssays.mojo" >
        <mjl:property name="collectionId" value="${item.concreteId}"/>
        <mjl:property name="assayType" value="lda"/>        
      </mjl:context>
      <mjl:columns>
        <mjl:attributeColumn attributeName="testDate">
          <mjl:row>
            <span class="formatDate">${current.testDate}</span>
          </mjl:row>        
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="insecticide">
          <mjl:row>
            ${current.insecticide.displayLabel}
          </mjl:row>
        </mjl:attributeColumn>
        <mjl:freeColumn>
          <mjl:header>
          </mjl:header>
          <mjl:row>
            <mjl:commandLink action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.view.mojo" name="view.link">
              <fmt:message key="View"/>
              <mjl:property value="${current.id}" name="id" />
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
  </dd>  
  </c:if>
  <c:if test="${kda.count > 0}">  
  <dt>
    <fmt:message key="Knock_Down_Assay" />
  </dt>
  <dd>
    <mjl:table var="current" query="${kda}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.sortAssays.mojo" >
        <mjl:property name="collectionId" value="${item.concreteId}"/>
        <mjl:property name="assayType" value="kda"/>        
      </mjl:context>
      <mjl:columns>
        <mjl:attributeColumn attributeName="testDate">
          <mjl:row>
            <span class="formatDate">${current.testDate}</span>
          </mjl:row>        
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="insecticide">
          <mjl:row>
            ${current.insecticide.displayLabel}
          </mjl:row>
        </mjl:attributeColumn>
        <mjl:freeColumn>
          <mjl:header>
          </mjl:header>
          <mjl:row>
            <mjl:commandLink action="dss.vector.solutions.entomology.assay.KnockDownAssayController.view.mojo" name="view.link">
              <fmt:message key="View"/>
              <mjl:property value="${current.id}" name="id" />
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
  </dd>
  </c:if>
</dl>
</c:if>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

SubCollectionViewDTO view = (SubCollectionViewDTO) request.getAttribute("collection");
SubCollectionViewDTO[] rows = (SubCollectionViewDTO[]) request.getAttribute(MosquitoCollectionController.ROWS);

String[] attributes = (String[]) request.getAttribute(MosquitoCollectionController.KEYS);
Map<String, ColumnSetup> map = (Map<String, ColumnSetup>) request.getAttribute(MosquitoCollectionController.COLUMNS);

String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{LifeStageDTO.CLASS, MosquitoCollectionDTO.CLASS, MosquitoCollectionViewDTO.CLASS, SubCollectionViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
	  // SETUP THE DOM ELEMENTS
    var concreteId = document.getElementById('concreteId');
    var collection_id = document.getElementById('collection_id');
    var collectionId = document.getElementById('collectionId');
    var abundance = document.getElementById('abundance');

    var ada_button = document.getElementById('ada.button');
    var lda_button = document.getElementById('lda.button');
    var kda_button = document.getElementById('kda.button');
    var ia_button = document.getElementById('ia.button');    
    var ma_button = document.getElementById('ma.button');    
    var delete_button = document.getElementById('delete.button');    

    // BUTTON HANDLER: DISABLES LINK BUTTONS WHEN THE MOSQUITO COLLECTION HAS NOT BEEN APPLIED
    var buttonHandler = function() {
      if(concreteId.value != '') {
        collection_id.value = concreteId.value;
        ada_button.disabled = false;        
        lda_button.disabled = false;        
        kda_button.disabled = false;        
        ia_button.disabled = false;        
        ma_button.disabled = false;        
        delete_button.disabled = false;        
      }
      else {
        collection_id.vaule = '';
        ada_button.disabled = true;        
        lda_button.disabled = true;        
        kda_button.disabled = true;        
        ia_button.disabled = true;        
        ma_button.disabled = true;        
        delete_button.disabled = true;        
      }     
    }

    // FORM SCRAPPER USED TO POPULATE A MOSQUITO COLLECTION VIEW USED IN THE SAVE HANDLER
    var populateCollection = function() {
      var collection = new Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView();
      
      // IMPORTANT: If this is an existing collection then we do not update the searchable attributes        
      if(concreteId.value == '') {
        var stage = document.getElementById('lifeStage').value;
        var collectionDate = document.getElementById('collectionDate').value;
        collectionDate = MDSS.Calendar.parseDate(collectionDate);

        collection.setCollectionMethod(document.getElementById('collectionMethod').value);
        collection.setCollectionDate(collectionDate);
        collection.setGeoEntity(document.getElementById('geoEntity_geoEntityId').value);        
        collection.addLifeStage(stage);                   
      }
      else {
        var collectionDate = document.getElementById('original.collectionDate').value;
        collectionDate = MDSS.Calendar.parseDate(collectionDate);
        
        collection.setConcreteId(concreteId.value);
        collection.setCollectionMethod(document.getElementById('original.collectionMethod').value);
        collection.setCollectionDate(collectionDate);
        collection.setGeoEntity(document.getElementById('original.geoEntity').value);        
        collection.addLifeStage(document.getElementById('original.lifeStage').value);         
      }

      collection.setCollectionId(collectionId.value);        
      collection.setAbundance(abundance.value);        

      return collection;
    }

    var populateForm = function(collection) {
      concreteId.value = collection.getConcreteId();

      var stage = collection.getLifeStage();
      var method = collection.getValue('collectionMethod');
      var entity = collection.getValue('geoEntity');
      
      document.getElementById('original.collectionMethod').value = method;
      document.getElementById('original.collectionDate').value = collection.getCollectionDate();      
      document.getElementById('original.geoEntity').value = entity;
      document.getElementById('original.lifeStage').value = stage[0].name();           
      document.getElementById('collectionId').value = collection.getCollectionId();
      
      // Recheck the button status on this page
      buttonHandler();        
    }

    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveCollection = function(request, subCollections) {
      var collection = populateCollection();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedCollection) {
    	  oldOnSuccess.apply(request, [savedRows]);
        
        populateForm(returnedCollection);
      }

      request.onSuccess = newOnSuccess;

      
      collection.applyAll(request, subCollections);
    };


    // FUNCTION FOR CALCULATING THE TOTAL OF A GIVEN ROW
    calculateTotal = function(record){
    	var females = parseInt(record.getData('Female'), 10) || 0;
    	var males = parseInt(record.getData('Male'), 10)  || 0;
    	var larvae = parseInt(record.getData('Larvae'), 10)  || 0;
    	var pupae = parseInt(record.getData('Pupae'), 10)  || 0;
    	var unknowns = parseInt(record.getData('Unknowns'), 10)  || 0;
    	var eggs = parseInt(record.getData('Eggs'), 10)  || 0;
    	
      return males + females + larvae + pupae + unknowns + eggs;
    }

    // SETUP THE SUB COLLECTIONS DATA GRID
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
      div_id: "SubCollection",
      data_type: "Mojo.$.<%=SubCollectionViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true,
      copy_from_above: ["IdentMethod", "SubCollectionId"],      
      reloadKeys : ["SubCollectionId"],
      saveLabelKey : "Save_Collection",
      saveHandler : saveCollection,
      after_row_edit:function(record){this.myDataTable.updateCell(record, 'Total', calculateTotal(record))}
    };        
 
    var grid = MojoGrid.createDataTable(data);

    // SETUP SUBMIT BUTTON HANDLERS
    enableSave = function() {
      grid.enableSaveButton();      
    }

    YAHOO.util.Event.on(collectionId, 'change', enableSave);   
    YAHOO.util.Event.on(abundance, 'change', enableSave);   

    if(concreteId.value == '') {
      enableSave();
    }
    
    // INITIALIZE THE BUTTONS
    buttonHandler();

  });
})();
        
</script>

