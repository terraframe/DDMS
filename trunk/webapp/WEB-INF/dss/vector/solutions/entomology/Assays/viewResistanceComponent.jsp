<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.entomology.AssayController"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.DiagnosticAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.TimeResponseAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionController"%>


<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>

<c:set var="page_title" value="Enter_bioassays"  scope="request"/>

<jsp:include page="resistanceForm.jsp"/>

<dl>
  <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="collectionId">
      ${item.collectionId}
    </mjl:dt>    
    <mjl:dt attribute="collectionMethod">
      ${collectionMethod.displayLabel}
    </mjl:dt>
    <mjl:dt attribute="collectionDate">
      <span class="formatDate">${item.collectionDate}</span>
    </mjl:dt>
    <mjl:dt attribute="geoEntity">
      ${entity.displayString}
    </mjl:dt>
    <mjl:dt attribute="lifeStage">
      <ul>
        <c:forEach items="${item.lifeStageEnumNames}" var="enumName">
          <li>
            ${item.lifeStageMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </mjl:dt> 
    <mjl:dt attribute="abundance">
      ${item.abundance ? item.abundanceMd.positiveDisplayLabel :  item.abundanceMd.negativeDisplayLabel}
    </mjl:dt>
  </mjl:component>
</dl>

<dt>
  <label>${diagnostic.md.displayLabel} </label>
</dt>
<dd>
  <div id="DiagnosticAssay">
  </div>
</dd>
<dt>
  <label>${timeResponse.md.displayLabel} </label>
</dt>
<dd>
  <div id="TimeResponseAssay">
  </div>
</dd>

<fmt:message key="Save_Comments" var="Save_Comments" scope="request"/>

<mjl:form id="collection.update" name="collection.update" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="resistanceAssayComments">
      <input name="collectionId" type="hidden" value="${item.concreteId}" />
      <textarea name="comments" cols="30" rows="5">${item.resistanceAssayComments}</textarea>
      <input type="button" name="submit" id="setComments.button" value="${Save_Comments}"  />
    </mjl:dt>
  </mjl:component>  
</mjl:form>



<%
DiagnosticAssayViewDTO diagnostic = (DiagnosticAssayViewDTO) request.getAttribute(AssayController.DIAGNOSTIC);
DiagnosticAssayViewDTO[] diagnosticRows = (DiagnosticAssayViewDTO[]) request.getAttribute(AssayController.DIAGNOSTIC_ROWS);

String[] diagnosticKeys = (String[]) request.getAttribute(AssayController.DIAGNOSTIC_KEYS);
Map<String, ColumnSetup> diagnosticMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.DIAGNOSTIC_COLUMNS);

TimeResponseAssayViewDTO timeResponse = (TimeResponseAssayViewDTO) request.getAttribute(AssayController.TIME_RESPONSE);
TimeResponseAssayViewDTO[] timeResponseRows = (TimeResponseAssayViewDTO[]) request.getAttribute(AssayController.TIME_RESPONSE_ROWS);

String[] timeResponseKeys = (String[]) request.getAttribute(AssayController.TIME_RESPONSE_KEYS);
Map<String, ColumnSetup> timeResponseMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.TIME_RESPONSE_COLUMNS);

String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{DiagnosticAssayViewDTO.CLASS, TimeResponseAssayViewDTO.CLASS, MosquitoCollectionController.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var updateComments = function(request)  {
      var request = new MDSS.Request({});
            
      var params = Mojo.Util.collectFormValues('collection.update');
      Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionController.setResistanceAssayCommentMap(request, params);
    }

    YAHOO.util.Event.on('setComments.button', 'click', updateComments);   
       
    // SETUP THE INFECTION DATA GRID
    var biochemicalData = {
      rows:<%=Halp.getDataMap(diagnosticRows, diagnosticKeys, diagnostic)%>,
      columnDefs:<%=Halp.getColumnSetup(diagnostic, diagnosticKeys, deleteColumn, true, diagnosticMap)%>,
      defaults:<%=Halp.getDefaultValues(diagnostic, diagnosticKeys)%>,
      div_id: "DiagnosticAssay",
      data_type: "Mojo.$.<%=DiagnosticAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var biochemicalGrid = MojoGrid.createDataTable(biochemicalData);

    // SETUP THE POOLED DATA GRID
    var molecularData = {
      rows:<%=Halp.getDataMap(timeResponseRows, timeResponseKeys, timeResponse)%>,
      columnDefs:<%=Halp.getColumnSetup(timeResponse, timeResponseKeys, deleteColumn, true, timeResponseMap)%>,
      defaults:<%=Halp.getDefaultValues(timeResponse, timeResponseKeys)%>,
      div_id: "TimeResponseAssay",
      data_type: "Mojo.$.<%=TimeResponseAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var molecularGrid = MojoGrid.createDataTable(molecularData);    
  });
})();
        
</script>
