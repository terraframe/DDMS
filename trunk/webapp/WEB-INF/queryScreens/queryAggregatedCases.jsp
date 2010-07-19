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
    var aggregatedCaseAttribs = [ "startDate","endDate","geoEntity","ageGroup","cases","deaths","negativeCases","positiveCases"];

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
    %>
    var available = new MDSS.Set(<%= request.getAttribute("acAttribs") %>);
    aggregatedCaseAttribs = Mojo.Iter.filter(aggregatedCaseAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var aggregatedCaseColumns =   aggregatedCaseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggregatedCase, suffix:'_ac', dropDownMaps:{}});

    //aggregatedCaseColumns = controlInterventionColumns.concat(calculations);
        

//    var caseTreatmentMethod = new dss.vector.solutions.surveillance.CaseTreatmentMethod;
//    var caseTreatmentMethodAttribs = [];
//    var caseTreatmentMethodColumns =   caseTreatmentMethodAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:caseTreatmentMethod, suffix:'_ic', dropDownMaps:{}});
    var caseTreatmentMethodColumns = orderedGrids.methods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.methods);

//     var  treatment = new dss.vector.solutions.surveillance.CaseTreatment;
//     var treatmentAttribs = [];
//     var treatmentColumns =   treatmentAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:treatment, suffix:'_ip1', dropDownMaps:{}});
     var treatmentColumns = orderedGrids.treatments.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.treatments);

//     var stock = new dss.vector.solutions.surveillance.CaseTreatmentStock;
//     var stockAttribs = [];
//     var stockColumns =   stockAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:stock, suffix:'_ip2', dropDownMaps:{}});
     var stockColumns = orderedGrids.stocks.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.stocks);
     
//     var referral = new dss.vector.solutions.surveillance.CaseReferral;
//     var referralAttribs = [];
//     var referralColumns =   referralAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:referral, suffix:'_ip3', dropDownMaps:{}});
     var referralColumns = orderedGrids.referrals.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.referrals);

//     var stockReferral = new dss.vector.solutions.surveillance.CaseStockReferral;
//     var stockReferralAttribs = [];
//     var stockReferralColumns =   stockReferralAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:stockReferral, suffix:'_ip4', dropDownMaps:{}});
     var stockReferralColumns = orderedGrids.stockReferrals.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.stockReferrals);
      
//     var diagnostic = new dss.vector.solutions.surveillance.CaseDiagnostic;
//     var diagnosticAttribs = [];
//     var diagnosticColumns =  diagnosticAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:diagnostic, suffix:'_ip5', dropDownMaps:{}});
     var diagnosticColumns = orderedGrids.diagnostics.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.diagnostics);

//     var diagnosticPositiveAttribs = [];
//     var diagnosticPositiveColumns =  diagnosticPositiveAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:diagnostic, suffix:'_ip6', dropDownMaps:{}});
     var diagnosticPositiveColumns = orderedGrids.diagnosticsPositive.options.map(mapMo, orderedGrids.diagnosticsPositive);
     var diagnosticColumns = diagnosticColumns.concat(diagnosticPositiveColumns);

 

     function mapMo(term,index){

        var row = {};
       
        row.dtoType = "AttributeIntegerDTO";
        row.displayLabel = term.displayLabel+' ('+MDSS.localize('Amount_Positive')+')';
        
        row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;
        row.type = 'sqlinteger';
        row.attributeName = this._relAttribute+'__term' + term.MOID.replace(':','');

       return row;
     };

     var mapterm = function(term,index){
       
       var row = {};
       row.displayLabel = term.displayLabel;
       row.attributeName = this.ns+'__'+term.MOID.replace(':','');

       MDSS.Localized[row.attributeName]= term.displayLabel;

       var calculations = ([

                           ]);
       
       this.grid.options.forEach( function(stage){

      	var attributeName = stage.MOID.replace(':','');

        var key = row.attributeName+attributeName;

        MDSS.Localized[key]= row.displayLabel + " " + stage.displayLabel;
        
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
                           displayLabel:MDSS.localize("cases"),
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
     
      var selectableGroups = ([
              {title:"Aggregated_Cases", values:aggregatedCaseColumns, group:"ag", klass:aggregatedCase.CLASS},
              {title:"Grid_treatment_by_drug", values:treatmentColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Grid_treatment_by_method", values:caseTreatmentMethodColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Grid_treatment_by_stock", values:stockColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Grid_referrals_and_Shortages", values:stockReferralColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Grid_referral_Reasons", values:referralColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Grid_diagnostic_methods", values:diagnosticColumns, group:"ag",klass:aggregatedCase.CLASS},
              {title:"Calculations", values:calculations, group:"ag", klass:aggregatedCase.CLASS}
      ]);

      selectableGroups = selectableGroups.concat(diagnosisTypeGroups);
      selectableGroups = selectableGroups.concat(patientTypeGroups);
      selectableGroups = selectableGroups.concat(manifestationGroups);

    
    var query = new MDSS.QueryAggregatedCases(selectableGroups, queryList);
    query.render();

});

--></script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
