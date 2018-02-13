<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF"%>
<%@page import="com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO"%>
<%@page import="com.runwaysdk.dataaccess.MdBusinessDAOIF"%>
<%@page import="com.runwaysdk.dataaccess.metadata.MdBusinessDAO"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AbstractAssayDTO"%>
<%@page import="com.runwaysdk.transport.attributes.AttributeCharacterDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Resistance"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS, AdultDiscriminatingDoseAssayDTO.CLASS, LarvaeDiscriminatingDoseAssayDTO.CLASS, EfficacyAssayDTO.CLASS, KnockDownAssayDTO.CLASS, InsecticideDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    adultMaps = {<%=(String) request.getAttribute("adultMap")%>};

    larvaeMaps = {<%=(String) request.getAttribute("larvaeMap")%>};

    knockDownMaps = {<%=(String) request.getAttribute("knockDownMap")%>};

    insecticideMaps = {<%=(String) request.getAttribute("insecticideMap")%>};



    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;

    var collectionAttribs = ["collectionId", "collectionDate", "collectionMethod", "collectionRound","collectionType", "dateLastSprayed"];
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    
    var collectionColumns = [];

    var insectcide = new dss.vector.solutions.general.Insecticide;
    var insectcideAttribs = ["activeIngredient","amount","units"];
    <%
    Halp.setReadableAttributes(request, "insectcideAttribs", InsecticideDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("insectcideAttribs") %>);
    insectcideAttribs = Mojo.Iter.filter(insectcideAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    
    var collectionColumns = [];    

    var adultAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var adultAttribs = ["uniqueAssayId", "sex", "fed","gravid","holdingTime", "quantityLive","quantityDead", "correctedQuantityDead", "controlTestNumberExposed", "controlTestNumberDead", "controlTestMortality",
                        "observedMortality", "mortality", "kd50","kd95"];
    <%
    Halp.setReadableAttributes(request, "adultAttribs", AdultDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("adultAttribs") %>);
    adultAttribs = Mojo.Iter.filter(adultAttribs, function(attrib){
      return this.contains(attrib);
    }, available);   
       
    var adultCalulations = [];
    var adultColumns =   adultAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:adultAssay, suffix:'_adult', dropDownMaps:adultMaps});
    MDSS.QueryBase.filterFunctions(adultColumns, ['holdingTime_adult', 'kd50_adult','kd95_adult'], MDSS.QueryXML.F_SET1);
    MDSS.QueryBase.filterFunctions(adultColumns, ['controlTestMortality_adult','mortality_adult'], MDSS.QueryXML.F_SET2);
    
    var observedMortality = MDSS.QueryBase.getColumn(adultColumns, 'observedMortality');
    
    if(observedMortality != null)
    {      
      observedMortality.key = '<%=QueryConstants.OBSERVED_MORTALITY%>';
      observedMortality.type = 'sqldouble';
      observedMortality.isAggregate = true;    
      observedMortality.dtoType = "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO";
      delete observedMortality.includes;
    }
    
    var mortality = MDSS.QueryBase.getColumn(adultColumns, 'mortality');
    
    if(mortality != null)
    {      
      mortality.key = '<%=QueryConstants.CORRECTED_MORTALITY%>';
      mortality.type = 'sqldouble';
      mortality.isAggregate = true;    
      mortality.dtoType = "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO";
      delete mortality.includes;
    }
    
    var larvaeAssay = new  dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay();
    var larvaeAttribs = ["uniqueAssayId", "startPoint","endPoint", "holdingTime",
             "quantityLive","quantityDead","controlTestMortality","mortality","lt50","lt95"];
    <%
    Halp.setReadableAttributes(request, "larvaeAttribs", LarvaeDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("larvaeAttribs") %>);
    larvaeAttribs = Mojo.Iter.filter(larvaeAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var larvaeCalculations = ["quanityAlive","percentMortality"];
    var larvaeColumns =   larvaeAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:larvaeAssay, suffix:'_larvae', dropDownMaps:larvaeMaps});
    MDSS.QueryBase.filterFunctions(larvaeColumns, ['holdingTime_larvae', 'lt50_larvae','lt95_larvae'], MDSS.QueryXML.F_SET1);
    MDSS.QueryBase.filterFunctions(larvaeColumns, ['controlTestMortality_larvae','mortality_larvae'], MDSS.QueryXML.F_SET2);
    
    
    var knockDownAssay = new  dss.vector.solutions.entomology.assay.KnockDownAssay();
    var knockDownAttribs = ["uniqueAssayId", "sex","fed","gravid","kd50","kd95"];
    <%
    Halp.setReadableAttributes(request, "knockDownAttribs", KnockDownAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("knockDownAttribs") %>);
    knockDownAttribs = Mojo.Iter.filter(knockDownAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var knockDownCalulations = [];
    var knockDownColumns =   knockDownAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:knockDownAssay, suffix:'_knockDown', dropDownMaps:knockDownMaps});
    MDSS.QueryBase.filterFunctions(knockDownColumns, ['kd50_knockDown','kd95_knockDown'], MDSS.QueryXML.F_SET1);

    
    var pooledAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    <!-- todo: This species typo is necessary for the app to work correctly, so don't try and "fix" it... -->
    var pooledAttribs = ["specie","identificationMethod","generation","isofemale","testDate", "testMethod", "exposureTime","quantityTested"];
    <%
    Halp.setReadableAttributes(request, "pooledAttribs", AdultDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("pooledAttribs") %>);
    pooledAttribs = Mojo.Iter.filter(pooledAttribs, function(attrib){
      return this.contains(attrib);
    }, available);   

    var pooledCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_collection', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_collection', dropDownMaps:insecticideMaps}));
    var pooledColumns =  collectionColumns.concat(pooledAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pooledAssay, suffix:'_collection', dropDownMaps:adultMaps, type:'dss.vector.solutions.entomology.assay.CollectionAssay'}));

    MDSS.QueryBase.filterFunctions(pooledColumns, ['amount_collection','exposureTime_collection'], MDSS.QueryXML.F_SET1);
    
    // Create the field that will discriminate between AbstractAssay sub-types.
    var typeOptions ={};
    typeOptions[adultAssay.getType()] = MDSS.localize('adult_diagnostic');
    typeOptions[larvaeAssay.getType()] = MDSS.localize('larval_diagnostic');
    typeOptions[knockDownAssay.getType()] = MDSS.localize('adult_time_response');

    pooledColumns.unshift({
      entityAlias:'<%= QueryConstants.ASSAY_TYPE %>',
      key:'<%= QueryConstants.ASSAY_TYPE %>',
      attributeName:'<%= QueryConstants.ASSAY_TYPE %>',
      dropDownMap: typeOptions,
      type:'sqlcharacter',
      displayLabel:MDSS.localize('<%= QueryConstants.ASSAY_TYPE %>')
    });
    
    pooledColumns.push({
        key:"resistance_result_collection",
        attributeName:"resistance_result",
        type:"sqlcharacter",
        displayLabel: MDSS.localize('resistance_result'),
        description: MDSS.localize('resistance_result_desc')
       });

    pooledColumns = pooledColumns.map(function(attrib){attrib.type = attrib.type.replace('AdultDiscriminatingDose','Collection');return attrib;});

    var selectableGroups = [
              {title:"Pooled_Assays", values:pooledColumns, group:"assay", klass:dss.vector.solutions.entomology.assay.CollectionAssay.CLASS},
              {title:"Adult_DDA", values:adultColumns, group:"assay", klass:dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS},
              {title:"Larvae_DDA", values:larvaeColumns, group:"assay", klass:dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS},
              {title:"Knock_Down_Assay", values:knockDownColumns, group:"assay", klass:dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS}
    ];

    var query = new MDSS.QueryResistance(selectableGroups, queryList);
    query.render();

    var dm = query.getDependencyManager();
    dm.excludes({
      independent: adultColumns,
      dependent:larvaeColumns.concat(knockDownColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: adultColumns,
      dependent: '<%= QueryConstants.ASSAY_TYPE %>',
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    
    dm.excludes({
      independent: larvaeColumns,
      dependent:adultColumns.concat(knockDownColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: larvaeColumns,
      dependent: '<%= QueryConstants.ASSAY_TYPE %>',
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    
    dm.excludes({
      independent: knockDownColumns,
      dependent:larvaeColumns.concat(adultColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: knockDownColumns,
      dependent: '<%= QueryConstants.ASSAY_TYPE %>',
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });

    var adults = new MDSS.Set(Mojo.Iter.map(adultColumns, function(c){return c.key;}));
    var larvae = new MDSS.Set(Mojo.Iter.map(larvaeColumns, function(c){return c.key;}));
    var knockDown = new MDSS.Set(Mojo.Iter.map(knockDownColumns, function(c){return c.key;}));
    var handler = Mojo.Util.bind(query, query.ensureExclusion, adults, larvae, knockDown);
    dm.addAllTransactionsFinishListener(handler);
});

--></script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
