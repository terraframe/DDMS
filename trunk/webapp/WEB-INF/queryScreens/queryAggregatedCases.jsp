<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@page import="com.runwaysdk.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.runwaysdk.transport.attributes.AttributeDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.surveillance.*"%>


<%@page import="com.runwaysdk.business.BusinessDTO"%>
<c:set var="page_title" value="Query_Aggregated_Cases"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{AggregatedCaseDTO.CLASS, CaseDiagnosisTypeAmountDTO.CLASS, CaseDiseaseManifestationAmountDTO.CLASS, CaseTreatmentMethodDTO.CLASS,CaseTreatmentDTO.CLASS,CaseTreatmentStockDTO.CLASS,CaseReferralDTO.CLASS,CaseStockReferralDTO.CLASS,CaseDiagnosticDTO.CLASS,CaseReferralDTO.CLASS,CaseStockReferralDTO.CLASS,CaseDiagnosisTypeDTO.CLASS,CaseDiseaseManifestation.CLASS,CasePatientType.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript"><!--
// Setting both values to false will select *all* univerals

YAHOO.util.Event.onDOMReady(function(){

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);

    
    
    var queryList = <%= (String) request.getAttribute("queryList") %>;
    
    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var aggregatedCase = new dss.vector.solutions.surveillance.AggregatedCase;
    var aggregatedCaseAttribs = ["geoEntity", "startDate","endDate","ageGroup","cases","positiveCases","negativeCases","deaths"];

    var calculations = ([
                         {
                           
                           key:"total_premises_visited",
                           type:"sqlfloat",
                           attributeName:"total_premises_visited",
                           isAggregate:true
                         },
                         {
                           
                           key:"total_premises_treated",
                           type:"sqlfloat",
                           attributeName:"total_premises_treated",
                           isAggregate:true
                         },
                         {
                           
                           key:"total_premises_not_treated",
                           type:"sqlfloat",
                           attributeName:"total_premises_not_treated",
                           isAggregate:true
                         },
                     
                      
                        ]);


    <%
    Halp.setReadableAttributes(request, "acAttribs", AggregatedCaseDTO.CLASS, requestIF);
    Halp.setReadableAttributes(request, "acvAttribs", AggregatedCaseViewDTO.CLASS, requestIF);
    %>
    
    var available = new MDSS.Set(<%= request.getAttribute("acAttribs") %>);
    aggregatedCaseAttribs = Mojo.Iter.filter(aggregatedCaseAttribs, function(attrib){
      return this.contains(attrib);
    }, available);

    // Many of the sections share the same terms, so this function namespaces the attribute name
    // so the column name/attribute name in the underlying SelectableSQL will be unique.
    var namespaceColumns = function(columns, suffix)
    {
      for(var i=0; i<columns.length; i++)
      {
        columns[i].attributeName += suffix;
      }
    };
    
    var aggregatedCaseColumns =   aggregatedCaseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggregatedCase, suffix:'_ac', dropDownMaps:{}});

    var caseTreatmentMethodColumns = orderedGrids.methods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.methods);
    namespaceColumns(caseTreatmentMethodColumns, '_tmc');
    
     var treatmentColumns = orderedGrids.treatments.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.treatments);
    namespaceColumns(treatmentColumns, '_tc');

     var stockColumns = orderedGrids.stocks.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.stocks);
    namespaceColumns(stockColumns, '_sc');
     
     var referralColumns = orderedGrids.referrals.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.referrals);
    namespaceColumns(referralColumns, '_rc');

     var stockReferralColumns = orderedGrids.stockReferrals.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.stockReferrals);
    namespaceColumns(stockReferralColumns, '_src');
      
     var diagnosticColumns = orderedGrids.diagnostics.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.diagnostics);
    namespaceColumns(diagnosticColumns, '_dc');

     // Prepend with "Tested"
     var ttDelim = ' '+MDSS.localize('Term_Separator')+' ';
     var testedPrepend = MDSS.localize('Tested_Prepend')+ttDelim;
     var positivePrepend = MDSS.localize('Positive_Prepend')+ttDelim;
     
     for(var l=0; l<diagnosticColumns.length; l++)
     {
       diagnosticColumns[l].displayLabel = testedPrepend+diagnosticColumns[l].displayLabel;
     }

     // Prepend with "Positive"
     var diagnosticPositiveColumns = orderedGrids.diagnosticsPositive.options.map(mapMo, orderedGrids.diagnosticsPositive);
     var diagnosticColumns = diagnosticColumns.concat(diagnosticPositiveColumns);


     function mapMo(term,index){

        var row = {};
       
        row.dtoType = "AttributeIntegerDTO";
        row.displayLabel = positivePrepend+term.displayLabel;
        
        row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;
        row.type = 'sqlinteger';
//        row.attributeName = this._relAttribute+'__term' + term.MOID.replace(':','');
        row.attributeName = this._relAttribute+'__term' + MDSS.QueryBase.aliasTerm(term.id);

       return row;
     };

     var mapterm = function(term,index){
       
       var row = {};
       row.displayLabel = term.displayLabel;
//       row.attributeName = this.ns+'__'+term.MOID.replace(':','');
       row.attributeName = this.ns+'__'+MDSS.QueryBase.aliasTerm(term.id);

       MDSS.Localized[row.attributeName]= term.displayLabel;

       var calculations = ([

                           ]);
       
       this.grid.options.forEach( function(stage){

        //var attributeName = stage.MOID.replace(':','');
        var attributeName = MDSS.QueryBase.aliasTerm(stage.id);

        var key = row.attributeName+attributeName;

        MDSS.Localized[key]= row.displayLabel + ttDelim + stage.displayLabel;
        
        calculations = calculations.concat([
                              
                              {
                                key:key,
                                type:"sqlfloat",
                                attributeName:key,
                                isAggregate:false,
                                dtoType : "AttributeFloatDTO"
                              },

                             ]);
         
       });
       
       var group = {title:row.attributeName, values:calculations, group:"ag", klass:dss.vector.solutions.surveillance.AggregatedCase.CLASS};
       
      return group;      
    }

     //{title:"Grid_data_by_diagnosis_type", values:caseDiagnosisTypeColumns, group:"dt", klass:aggregatedCase.CLASS},
     //{title:"Grid_data_by_patient_type", values:patientTypeColumns, group:"ap", klass:aggregatedCase.CLASS},
     var diagnosisTypeGroups = orderedGrids.diagnosisTypes.options.map(mapterm, {grid:orderedGrids.diagnosisTypeAmounts, ns:'<%=AggregatedCaseViewDTO.CASEDIAGNOSISTYPE%>'});

     var patientTypeGroups = orderedGrids.patientTypes.options.map(mapterm, {grid:orderedGrids.patientTypeAmounts, ns:'<%=AggregatedCaseViewDTO.CASEPATIENTTYPE %>'});

     var manifestationGroups = orderedGrids.manifestations.options.map(mapterm, {grid:orderedGrids.manifestationAmmounts, ns:'<%=AggregatedCaseViewDTO.CASEDISEASEMANIFESTATION %>'});
     
     var calculations = ([
                          {
                           
                           key:"totalCases",
                           type:"sqlfloat",
                           attributeName:"totalCases",
                           displayLabel:MDSS.localize("Adjusted_Case_Count"),
                           isAggregate:true
                          },                     
                          {
                            
                            key:"incidence_100",
                            type:"sqlfloat",
                            attributeName:"incidence_100",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_1000",
                            type:"sqlfloat",
                            attributeName:"incidence_1000",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_10000",
                            type:"sqlfloat",
                            attributeName:"incidence_10000",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_100000",
                            type:"sqlfloat",
                            attributeName:"incidence_100000",
                            //dropDownMap:{'100':'100','1,000':'1000','10,000':'10000','100,000':'100000'},
                            isAggregate:true
                          },
                          
                          {
                            
                            key:"cfr",
                            type:"sqlfloat",
                            attributeName:"cfr",
                            isAggregate:true
                          },

                         ]);      

      var availableGrids = new MDSS.Set(<%= request.getAttribute("acvAttribs") %>);
     
      var selectableGroups = ([{title:"Aggregated_Cases", values:aggregatedCaseColumns, group:"ag", klass:aggregatedCase.CLASS}]);

      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASETREATMENTS%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_treatment_by_drug", values:treatmentColumns, group:"ag",klass:aggregatedCase.CLASS});
      }

      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASETREATMENTMETHOD%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_treatment_by_method", values:caseTreatmentMethodColumns, group:"ag",klass:aggregatedCase.CLASS});
      }
      
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASESTOCKS%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_treatment_by_stock", values:stockColumns, group:"ag",klass:aggregatedCase.CLASS});
      }
            
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASESTOCKREFERRAL%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_referrals_and_Shortages", values:stockReferralColumns, group:"ag",klass:aggregatedCase.CLASS});
      }
      
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASEREFERRALS%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_referral_Reasons", values:referralColumns, group:"ag",klass:aggregatedCase.CLASS});
      }
      
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASEDIAGNOSTIC%>"))
      {
        selectableGroups = selectableGroups.concat({title:"Grid_diagnostic_methods", values:diagnosticColumns, group:"ag",klass:aggregatedCase.CLASS});
      }
      
      selectableGroups = selectableGroups.concat({title:"Calculations", values:calculations, group:"ag", klass:aggregatedCase.CLASS});

      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASEDIAGNOSISTYPE%>"))
      {
        selectableGroups = selectableGroups.concat(diagnosisTypeGroups);
      }
      
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASEPATIENTTYPE%>"))
      {
        selectableGroups = selectableGroups.concat(patientTypeGroups);
      }
      
      if(availableGrids.contains("<%=AggregatedCaseViewDTO.CASEDISEASEMANIFESTATION%>"))
      {
        selectableGroups = selectableGroups.concat(manifestationGroups);
      }      

    
    var query = new MDSS.QueryAggregatedCases(selectableGroups, queryList);
    query.render();

});

--></script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
