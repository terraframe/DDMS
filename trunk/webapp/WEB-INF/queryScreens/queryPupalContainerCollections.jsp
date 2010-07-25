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
<%@page import="dss.vector.solutions.entomology.PupalCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalPremiseDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalContainerDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>


<%@page import="com.runwaysdk.business.BusinessDTO"%>
<c:set var="page_title" value="Query_Pupae_by_Individual_Container"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ PupalPremiseDTO.CLASS, PupalContainerDTO.CLASS, PupalCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
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

    var collectionMaps = {<%=(String) request.getAttribute("collectionMap")%>};
    var containerMaps = {<%=(String) request.getAttribute("containerMap")%>};
    
    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var collection = new dss.vector.solutions.entomology.PupalCollection;
    var premise = new dss.vector.solutions.entomology.PupalPremise;
    var container = new dss.vector.solutions.entomology.PupalContainer;

    var collectionAttribs = ["geoEntity","startDate","endDate","collectionId"];
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", PupalCollectionDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:collection, suffix:'_col', dropDownMaps:collectionMaps});
    var colId = collectionColumns.pop();
    
    var premiseAttribs = ["premiseType", "numberExamined","premiseSize","numberInhabitants"];
    var premiseAttribsColumns = premiseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:premise, suffix:'_col', dropDownMaps:collectionMaps});
    var premiseTypeCol = premiseAttribsColumns.shift();
    
    collectionColumns =   collectionColumns.concat([premiseTypeCol, colId], premiseAttribsColumns);

    // NOTE that containerType, drawDownFrequency, fillMethod, and lid have dependencies with some of the calculations. Look at the calculations and DependencyManager
    // below to see the mappings.
    var containerType = 'containerType';
    var lid = 'lid';
    var fillMethod = 'fillMethod';
    var drawDownFrequency = 'drawdownFrequency';
    var CONTAINER_SUFFIX = '_cont';
    var contAttribs = [containerType, lid, fillMethod, drawDownFrequency];
    var containerAttribs = [ "containerId", containerType,"shape","height","width","containerLength","openingWidth","openingLength",
                             "diameter","openingDiameter","shading",lid, "roof",fillMethod,"fillFrequency",drawDownFrequency,"drawdownPercent",];

    var containerColumns =   containerAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:container, suffix:CONTAINER_SUFFIX, dropDownMaps:containerMaps});

    var taxonAmmountsColumns = orderedGrids.pupaeAmmount.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.pupaeAmmount);
    
   


       // An map where each value is an array of selectables that has a dependency
       // with attributes in the Dependency Manager
       var dmCalcs = {};
       dmCalcs[containerType] = [];
       dmCalcs[lid] = [];
       dmCalcs[fillMethod] = [];
       dmCalcs[drawDownFrequency] = [];
       
       mapterm = function(term,index){
     
       var row = {};
       row.dtoType = "AttributeIntegerDTO";
       row.displayLabel = term.displayLabel;
       
       row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
       row.type = 'sqlinteger';
       row.attributeName = 'term' + term.MOID.replace(':','');

       MDSS.Localized[row.attributeName]= term.displayLabel;

       var containerTypeCalc = "percent_pupae_contribution_by_type_"+row.attributeName;
       var lidCalc = "percent_pupae_contribution_by_lid_"+row.attributeName;
       var fillMethodCalc = "percent_pupae_contribution_by_fill_"+row.attributeName;
       var drawDownFrequencyCalc = "percent_pupae_contribution_by_frequency_"+row.attributeName;

       // add the calc keys to the structure used by the Dependency Manager at the end
       dmCalcs[containerType].push(containerTypeCalc);
       dmCalcs[lid].push(lidCalc);
       dmCalcs[fillMethod].push(fillMethodCalc);
       dmCalcs[drawDownFrequency].push(drawDownFrequencyCalc);

       var suffix = ' '+MDSS.localize('Term_Separator')+' '+term.displayLabel;
       var calculations = ([
                            
                            row,
                            {
                              key:"pupae_per_hectare_by_taxon_"+row.attributeName,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('pupae_per_hectare_by_taxon')+ suffix,
                              attributeName:"pupae_per_hectare_by_taxonrow__"+row.attributeName,
                              isAggregate:true
                            },

                            {
                              key:"pupae_per_person_per_taxon_"+row.attributeName,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('pupae_per_person_per_taxon')+ suffix,
                              attributeName:"pupae_per_person_per_taxonrow__"+row.attributeName,
                              isAggregate:true
                            },                            
                            {
                              key:"pupae_per_premise_by_taxon_"+row.attributeName,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('pupae_per_premise_by_taxon')+  suffix,
                              attributeName:"pupae_per_premise_by_taxonrow__"+row.attributeName,
                              isAggregate:true
                            },

                            {
                              key:"percent_pupae_contribution_"+row.attributeName,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('percent_pupae_contribution')+ suffix,
                              attributeName:"percent_pupae_contributionrow__"+row.attributeName,
                              isAggregate:true
                            },
                            {
                              key:containerTypeCalc,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('percent_pupae_contribution_by_type')+  suffix,
                              attributeName:"percent_pupae_contribution_by_typerow__"+row.attributeName,
                              isAggregate:true
                            },
                            {
                              key:lidCalc,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('percent_pupae_contribution_by_lid')+  suffix,
                              attributeName:"percent_pupae_contribution_by_lidrow__"+row.attributeName,
                              isAggregate:true
                            },
                            {
                              key:fillMethodCalc,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('percent_pupae_contribution_by_fill')+ suffix,
                              attributeName:"percent_pupae_contribution_by_fillrow__"+row.attributeName,
                              isAggregate:true
                            },
                            {
                              key:drawDownFrequencyCalc,
                              type:"sqlfloat",
                              displayLabel:MDSS.localize('percent_pupae_contribution_by_frequency')+  suffix,
                              attributeName:"percent_pupae_contribution_by_frequencyrow__"+row.attributeName,
                              isAggregate:true
                            }
                           ]);

       var group = {title:row.attributeName, values:calculations, group:"c", klass:collection.CLASS};
    	 
      return group;      
    }

    var taxonCalcGroups = orderedGrids.pupaeAmmount.options.map(mapterm, orderedGrids.pupaeAmmount);

      var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"c", klass:collection.CLASS},
                {title:"Container", values:containerColumns, group:"c", klass:collection.CLASS},
                //{title:"Pupae_Amount", values:taxonAmmountsColumns, group:"c",klass:collection.CLASS},
                //{title:"Container_Calculations", values:calculations, group:"c", klass:collection.CLASS},
      ];

      selectableGroups = selectableGroups.concat(taxonCalcGroups);

    
    var query = new MDSS.QueryPupalContainerCollection(selectableGroups, queryList);
    query.render();

    var dm = query.getDependencyManager();


    // For each container attribute that has a calculation associated with it,
    // grab all calculation keys and add checkbox dependencies.
    for(var i=0; i<contAttribs.length; i++)
    {
      var attrib = contAttribs[i];
      var key = attrib+CONTAINER_SUFFIX;
      var calcs = dmCalcs[attrib];
            
      dm.includes({
        independent: calcs,
        dependent: key,
        type: MDSS.Dependent.BOTH,
        bidirectional: false
      });

      dm.includes({
        independent: key,
        dependent: calcs,
        type: MDSS.Dependent.UNCHECKED,
        bidirectional: false
      });
    }    

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
