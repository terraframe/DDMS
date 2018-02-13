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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>

<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>

<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayView"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandDTO"%>


<%@page import="dss.vector.solutions.geo.generated.SurfaceDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Efficacy"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{  EfficacyAssayDTO.CLASS, InsecticideBrandDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

//    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
    loadables.add(SurfaceDTO.CLASS);
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">

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

    var efficacyMaps = {<%//=(String) request.getAttribute("adultMap")%>};

    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrand();

    var insectcideAttribs = [
                             "productName",
                             "activeIngredient",
                             "concentrationQuantifier",
                             "concentrationQualifier",
                             "insecticideUse",
                             "useDetail",
                             "unitsPerApplication",
                             "unitQuantifier",
                             "unitQualifier",
                             ];
    <%
      Halp.setReadableAttributes(request, "insectcideAttribs", InsecticideBrandDTO.CLASS, requestIF);
    %>
    
    var available = new MDSS.Set(<%= request.getAttribute("insectcideAttribs") %>);
    insectcideAttribs = Mojo.Iter.filter(insectcideAttribs, function(attrib){
        return this.contains(attrib);
    }, available);

    var insecticideBrandMap = {<%=(String) request.getAttribute("insecticideBrandMap")%>};
    var insectcideColumns =   insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_eff', dropDownMaps:insecticideBrandMap});
    MDSS.QueryBase.filterFunctions(insectcideColumns, ['concentrationQuantifier_eff'], MDSS.QueryXML.F_SET1);
    MDSS.QueryBase.filterFunctions(insectcideColumns, ['unitsPerApplication_eff'], MDSS.QueryXML.F_SET2);
    MDSS.QueryBase.filterFunctions(insectcideColumns, ['unitQuantifier_eff'], []);
    
    
    var efficacyAssay = new Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay();

    <%
      Halp.setReadableAttributes(request, "efficacyAttribs", EfficacyAssayViewDTO.CLASS, requestIF);
    %>
    
    //public static java.lang.String GEOENTITY = "geoEntity";
    var efficacyAttribs = [
                           "uniqueAssayId",
                           "geoEntity", 
                           "surfaceType",
                           "testDate",
                           "controlTestMortality",
                           "testMethod", 
                           "specie",
                           "colonyName",
                           "sex",
                           "gravid",
                           "fed",
                           "timeOnSurface",
                           "surfacePostion", 
                           "holdingTime",
                           "quantityTested",
                           "quantityDead",
                           "quantityLive",
                           "mortality"
                           ];
    available = new MDSS.Set(<%= request.getAttribute("efficacyAttribs") %>);
    efficacyAttribs = Mojo.Iter.filter(efficacyAttribs, function(attrib){
        if(attrib === 'geoEntity')
        {
          return this.contains('<%= EfficacyAssayViewDTO.GEOID %>');
        }
        else
        {
          return this.contains(attrib);
        }
    }, available);

    //var efficacyCalculations = ["quanityAlive","percentMortality","controlTestMortality"];

    var efficacyColumns =  efficacyAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:efficacyAssay, suffix:'_efficacy', dropDownMaps:efficacyMaps});
    MDSS.QueryBase.filterFunctions(efficacyColumns, ['timeOnSurface_efficacy', 'holdingTime_efficacy'], MDSS.QueryXML.F_SET1);
    MDSS.QueryBase.filterFunctions(efficacyColumns, ['mortality_efficacy'], MDSS.QueryXML.F_SET2);
    
    efficacyColumns.push({
      key:'<%= QueryConstants.OVERALL_MORTALITY %>',
      attributeName:'<%= QueryConstants.OVERALL_MORTALITY %>',
      displayLabel:MDSS.localize('Overall_Mortality_for_surface'),
      description:MDSS.localize('Overall_Mortality_for_surface'),
      type:'sqldouble',
      dtoType:'AttributeDoubleDTO',
      includes:MDSS.QueryXML.F_SET2
    });
    
    if(available.contains('<%= EfficacyAssayDTO.AGERANGE %>'))
    {
      efficacyColumns.splice(6, 0,
          {
            key:'<%= QueryConstants.AGE_LOWEST %>',
            attributeName:'<%= QueryConstants.AGE_LOWEST %>',
            displayLabel:MDSS.localize('age_range_lowest'),
            description:MDSS.localize('age_range_lowest_desc'),
            type:'sqlinteger',
            dtoType:'AttributeIntegerDTO',
            includes: MDSS.QueryXML.F_SET1
          },
          {
            key:'<%= QueryConstants.AGE_HIGHEST %>',
            attributeName:'<%= QueryConstants.AGE_HIGHEST %>',
            displayLabel:MDSS.localize('age_range_highest'),
            description:MDSS.localize('age_range_highest_desc'),
            type:'sqlinteger',
            dtoType:'AttributeIntegerDTO',
            includes: MDSS.QueryXML.F_SET1
          }
      );
    }

    //efficacyColumns[0].type = Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS;

    var selectableGroups = [
              {title:"Efficacy", values:efficacyColumns, group:"eff", klass:Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS},
              {title:"Insecticide_Detail", values:insectcideColumns, group:"eff", klass:Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS},
    ];

    var query = new MDSS.QueryEfficacyAssay(selectableGroups, queryList);
    query.render();

    var dm = query.getDependencyManager();
    dm.includes({
      independent: 'concentrationQuantifier_eff',
      dependent: 'concentrationQualifier_eff',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });
    dm.includes({
      independent: 'unitQuantifier_eff',
      dependent: 'unitQualifier_eff',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });
});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>




<jsp:include page="../templates/footer.jsp"></jsp:include>
