<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionController"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.LifeStageDTO"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<script type="text/javascript">
Mojo.Meta.newClass("MDSS.ValidationBridge", {

  IsSingleton : true,
  
  Instance : {
  
    initialize : function()
    {
    },
    
    setHandler : function(handler) {
      this._handler = handler;
    },

    handleEvent : function(evt){      
      if(this._handler != null)
      {
        if(evt instanceof dss.vector.solutions.ontology.TermSelectedEvent)
        {        
          if(!evt.getOnOpen())
          {
            this._handler(evt);          
          }
        }
        else
        {        
          this._handler(evt);
        }
      }
    }    
  },
});


</script>    


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
      <mdss:localize key="Go" var="Localized_Go" /> 
      <mjl:command name="Go" action="dss.vector.solutions.entomology.MosquitoCollectionController.forward.mojo" value="${Localized_Go}" />
      <hr />
      <mjl:dt attribute="collectionId">
        <mjl:input type="text" param="collectionId" id="collectionId" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="abundance">
        <mdss:selectBoolean param="abundance" id="abundance" includeBlank="true" value="${item.abundance == null ? '' : item.abundance}" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="collectionRound">
         <mdss:mo param="collectionRound" value="${collectionRound}" listener="MDSS.ValidationBridge.getInstance()"/>
      </mjl:dt>       
      <mjl:dt attribute="collectionType">
         <mdss:mo param="collectionType" value="${collectionType}" listener="MDSS.ValidationBridge.getInstance()"/>
      </mjl:dt>       
      <mjl:dt attribute="dateLastSprayed">
        <mjl:input param="dateLastSprayed" type="text" classes="DatePick NoFuture component" id="dateLastSprayed"/>
      </mjl:dt>    
      <mjl:dt attribute="wallType">
         <mdss:mo param="wallType" value="${wallType}" listener="MDSS.ValidationBridge.getInstance()"/>
      </mjl:dt>             
      <mjl:dt attribute="insecticideBrand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="insecticideBrand" id="insecticideBrand" includeBlank="true" classes="component" >
          <mjl:option>
            ${current.label}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="numberOfHumanOccupants">
        <mjl:input type="text" param="numberOfHumanOccupants" id="numberOfHumanOccupants" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="numberOfAnimalOccupants">
        <mjl:input type="text" param="numberOfAnimalOccupants" id="numberOfAnimalOccupants" classes="component"/>
      </mjl:dt>
      <mjl:dt attribute="numberOfLLINs">
        <mjl:input type="text" param="numberOfLLINs" id="numberOfLLINs" classes="component"/>
      </mjl:dt>      
    </mjl:component>
    <c:if test="${adaFlag}">
      <mdss:localize key="Adult_DDA" var="Localized_Adult_DDA" />
      <mjl:command name="ada.button" id="ada.button" classes="button" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo" value="${Localized_Adult_DDA}" />
    </c:if>
    <c:if test="${ldaFlag}">
      <mdss:localize key="Larvae_DDA" var="Localized_Larvae_DDA" />
      <mjl:command name="lda.button" id="lda.button" classes="button" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" value="${Localized_Larvae_DDA}" />
    </c:if>
    <c:if test="${kdaFlag}">
      <mdss:localize key="Knock_Down_Assay" var="Localized_Knock_Down_Assay" />
      <mjl:command name="kda.button" id="kda.button" classes="button" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" value="${Localized_Knock_Down_Assay}" />      
    </c:if>
    <c:if test="${raFlag}">
      <mdss:localize key="Bioassays" var="Localized_Bioassays" />
      <mjl:command name="ra.button" id="ra.button" classes="button" action="dss.vector.solutions.entomology.AssayController.getResistanceAssays.mojo" value="${Localized_Bioassays}" />
    </c:if>
    <c:if test="${iaFlag}">
      <mdss:localize key="Infection_Assays" var="Localized_Infection_Assays" />
      <mjl:command name="ia.button" id="ia.button" classes="button" action="dss.vector.solutions.entomology.AssayController.getInfectionAssays.mojo" value="${Localized_Infection_Assays}" />
    </c:if>
    <c:if test="${maFlag}">
      <mdss:localize key="Mechanism_Assays" var="Localized_Mechanism_Assays" />
      <mjl:command name="ma.button" id="ma.button" classes="button" action="dss.vector.solutions.entomology.AssayController.getMechanismAssays.mojo" value="${Localized_Mechanism_Assays}" />
    </c:if>
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command name="delete" id="delete.button" classes="button" action="dss.vector.solutions.entomology.MosquitoCollectionController.delete.mojo" value="${Localized_Delete}" />
  </mjl:form>
</dl>

<div id="SubCollection"></div>
<br />

<c:if test="${ada.count > 0 || lda.count > 0 || kda.count > 0}">
<h2><mdss:localize key="Collection_Assays"/></h2>
<dl>
  <c:if test="${ada.count > 0}">
  <dt>
    <mdss:localize key="Adult_DDA" />
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
              <mdss:localize key="View"/>
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
    <mdss:localize key="Larvae_DDA" />
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
              <mdss:localize key="View"/>
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
    <mdss:localize key="Knock_Down_Assay" />
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
              <mdss:localize key="View"/>
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
DataGrid grid = (DataGrid) request.getAttribute("grid");
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

    // BUTTON HANDLER: DISABLES LINK BUTTONS WHEN THE MOSQUITO COLLECTION HAS NOT BEEN APPLIED
    var buttonHandler = function() {
      var buttons = YAHOO.util.Dom.getElementsByClassName("button");
      
      if(concreteId.value != '') {
        collection_id.value = concreteId.value;
        
        for(var i=0, len=buttons.length; i<len; i++){
          var el = buttons[i]; 
          el.disabled = false;
        }        
      }
      else {
        collection_id.value = '';
        
        for(var i=0, len=buttons.length; i<len; i++){
          var el = buttons[i];
          el.disabled = true;
        }        
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
      
      var dateLastSprayed = document.getElementById('dateLastSprayed').value;
      dateLastSprayed = MDSS.Calendar.parseDate(dateLastSprayed);     
      
      if(dateLastSprayed === '')
      {
        dateLastSprayed = null;        
      }

      collection.setCollectionId(collectionId.value);        
      collection.setAbundance(abundance.value);        
      collection.setCollectionRound(document.getElementById('collectionRound').value);
      collection.setCollectionType(document.getElementById('collectionType').value);
      collection.setWallType(document.getElementById('wallType').value);
      collection.setInsecticideBrand(document.getElementById('insecticideBrand').value);
      collection.setNumberOfHumanOccupants(document.getElementById('numberOfHumanOccupants').value);
      collection.setNumberOfAnimalOccupants(document.getElementById('numberOfAnimalOccupants').value);
      collection.setNumberOfLLINs(document.getElementById('numberOfLLINs').value);
      collection.setDateLastSprayed(dateLastSprayed);

      return collection;
    }

    var populateForm = function(collection) {
      concreteId.value = collection.getConcreteId();

      var stage = collection.getLifeStage();
      var method = collection.getValue('collectionMethod');
      var entity = collection.getValue('geoEntity');
      var collectionRound = collection.getValue('collectionRound');
      var collectionType = collection.getValue('collectionType');
      var wallType = collection.getValue('wallType');
      var insecticideBrand = collection.getValue('insecticideBrand');
      
      document.getElementById('original.collectionMethod').value = method;
      document.getElementById('original.collectionDate').value = collection.getCollectionDate();      
      document.getElementById('original.geoEntity').value = entity;
      document.getElementById('original.lifeStage').value = stage[0].name();           
      document.getElementById('collectionId').value = collection.getCollectionId();
      
      // Recheck the button status on this page
      buttonHandler();        
    }

    // THE SAVE HANDLER FOR THE SUB COLLECTIONS DATA GRID
    var saveCollection = function(request, parameters) {
      var collection = populateCollection();

      var oldOnSuccess = request.onSuccess;
      var newOnSuccess = function(savedRows, returnedCollection) {
        oldOnSuccess.apply(request, [savedRows]);
        
        populateForm(returnedCollection);
      }

      request.onSuccess = newOnSuccess;

      collection.applyAll(request, parameters[0]);
    };
    
    calculateFemaleTotal = function(record) {
      var unfed = MDSS.parseNumber(record.getData('FemalesUnfed'), true) || 0;
      var fed = MDSS.parseNumber(record.getData('FemalesFed'), true) || 0;
      var halfGravid = MDSS.parseNumber(record.getData('FemalesHalfGravid'), true) || 0;
      var gravid = MDSS.parseNumber(record.getData('FemalesGravid'), true) || 0;
      var unknown = MDSS.parseNumber(record.getData('FemalesUnknown'), true) || 0;
      
      return unfed + fed + halfGravid + gravid + unknown; 
    }

    // FUNCTION FOR CALCULATING THE TOTAL OF A GIVEN ROW
    calculateTotal = function(record){
      
      var males = MDSS.parseNumber(record.getData('Male'), true)  || 0;
      var larvae = MDSS.parseNumber(record.getData('Larvae'), true)  || 0;
      var pupae = MDSS.parseNumber(record.getData('Pupae'), true)  || 0;
      var unknowns = MDSS.parseNumber(record.getData('Unknowns'), true)  || 0;
      var eggs = MDSS.parseNumber(record.getData('Eggs'), true)  || 0;
      
      return males + larvae + pupae + unknowns + eggs;
    }

    // SETUP THE SUB COLLECTIONS DATA GRID
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "SubCollection",
      data_type: "Mojo.$.<%=SubCollectionViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true,
      copy_from_above: ["IdentMethod", "SubCollectionId"],      
      saveLabelKey : "Save_Collection",
      saveHandler : saveCollection,
      after_row_edit:function(record){
        var femaleTotal = calculateFemaleTotal(record);
        var total = calculateTotal(record);

        var dataTable = grid.getDataTable();

        dataTable.updateCell(record, 'FemalesTotal', femaleTotal);
        dataTable.updateCell(record, 'Total', total + femaleTotal);
      }
    };        
 
    var grid = MojoGrid.createDataTable(data);

    // SETUP SUBMIT BUTTON HANDLERS
    enableSave = function(e) {
      if(!Mojo.Util.isElement(e) || e.id === 'dateLastSprayed')
      {
        grid.enableSaveButton();
      }
    }

    var components = YAHOO.util.Dom.getElementsByClassName("component");

    for(var i=0, len=components.length; i<len; i++){
      YAHOO.util.Event.on(components[i], 'change', enableSave);   
    }       
    
    MDSS.ValidationBridge.getInstance().setHandler(enableSave);
    MDSS.GlobalDateListener = enableSave;

    if(concreteId.value == '') {
      enableSave();
    }
    
    // INITIALIZE THE BUTTONS
    buttonHandler();

  });
})();
        
</script>

