<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroup"%>
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>
<c:set var="page_title" value="View_Mosquito_Collection"  scope="request"/>
<mjl:messages>

	<mjl:message />
</mjl:messages>
<h2><fmt:message key="Collection"/></h2>
<dl>
<mjl:form name="dss.entomology.MosquitoCollection.form.name" id="dss.entomology.MosquitoCollection.form.id" method="POST" style="display:inline;">

  <mjl:input value="${item.id}" type="hidden" param="id" />
  <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="collectionId">
      ${item.collectionId}
    </mjl:dt>
    <mjl:dt attribute="geoEntity">
      ${item.geoEntity.geoId}
    </mjl:dt>
    <mjl:dt attribute="dateCollected">
      <span class="formatDate">${item.dateCollected}</span>
    </mjl:dt>
    <mjl:dt attribute="collectionMethod">
      ${item.collectionMethod.displayLabel}
    </mjl:dt>
  </mjl:component>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.MosquitoCollectionController.edit.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.edit.button" classes="submitButton" />
</mjl:form>

<form method="get" action="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo"" style="display:inline;">
        <input value="${item.id}" name="id" type="hidden"/>
        <button type="submit"><fmt:message key="View_Assays" /></button>
</form>

<form method="get" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.newInstance.mojo" style="display:inline;">
        <input value="${item.id}" name="collection_id" type="hidden"/>
        <button type="submit"><fmt:message key="Adult_DDA" /></button>
</form>

<form method="get" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" style="display:inline;">
        <input value="${item.id}" name="collection_id" type="hidden"/>
        <button type="submit"><fmt:message key="Larvae_DDA" /></button>
</form>

<form method="get" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" style="display:inline;">
        <input value="${item.id}" name="collection_id" type="hidden"/>
        <button type="submit"><fmt:message key="Knock_Down_Assay" /></button>
</form>
</dl>
<br/>
<h2><fmt:message key="Specimens"/></h2>
<dl>
<div id="MorphologicalSpecieGroups"></div>
<br/>
</dl>

<h2><fmt:message key="Collection_Assays"/></h2>
<dl>
  <dt>
    <fmt:message key="Adult_DDA" />
  </dt>
  <dd>
    <mjl:table var="current" query="${ada}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.viewADAPage.mojo" >
        <mjl:property name="collectionId" value="${item.id}"/>
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
  <dt>
    <fmt:message key="Larvae_DDA" />
  </dt>
  <dd>
    <mjl:table var="current" query="${lda}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.viewLDAPage.mojo" >
        <mjl:property name="collectionId" value="${item.id}"/>
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
  <dt>
    <fmt:message key="Knock_Down_Assay" />
  </dt>
  <dd>
    <mjl:table var="current" query="${kda}" classes="displayTable" even="evenRow" odd="oddRow">
      <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.viewKDAPage.mojo" >
        <mjl:property name="collectionId" value="${item.id}"/>
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
</dl>


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) request.getAttribute("item");
MorphologicalSpecieGroupViewDTO[] rows = mosquito_collection.getMorphologicalSpecieGroups();
String[] attribs = { "GroupId","Collection", "Specie","IdentificationMethod","QuantityMale","QuantityFemale","Quantity"};
MorphologicalSpecieGroupViewDTO mdView = new MorphologicalSpecieGroupViewDTO(clientRequest);

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row));
%>

<script type="text/javascript">
    <%String[] types_to_load =
  {
     "dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
  };

  // THIS LINE CRASHES TOMCAT WITH Invalid memory access of location 00000000 eip=00000000
  //out.println(Halp.getDropDownMap(SpecieDTO.getAll(clientRequest)));
  //out.println(Halp.getDropDownMap2(Arrays.asList(SpecieDTO.getAll(clientRequest))));
   // SpecieDTO[] arrggg = SpecieDTO.getAll(clientRequest);

    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    //out.println( getDisplayLabels(SpecieDTO.getAll(clientRequest),"Specie"));
   // out.println(getDisplayLabels(IdentificationMethodDTO.getAll(clientRequest),"IdentificationMethod"));
    %>
    <%=Halp.getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>
    table_data = { rows:<%=Halp.getDataMap(rows,attribs,mdView)%>,
       columnDefs: <%=Halp.getColumnSetup(mdView,attribs,delete_row,false, 2)%>,
              defaults: {GroupId:"",Collection:"<%=mosquito_collection.getId()%>", Specie:"",IdentificationMethod:"",QuantityMale:"",QuantityFemale:"",Quantity:""},
              div_id: "MorphologicalSpecieGroups",
              excelButtons:false,
              copy_from_above: ["IdentificationMethod"],
              //collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroupView"

          };
    YAHOO.util.Event.addListener(window, 'load', MojoGrid.createDataTable(table_data));
</script>