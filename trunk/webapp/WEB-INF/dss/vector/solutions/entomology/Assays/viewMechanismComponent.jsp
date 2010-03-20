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
<%@page import="dss.vector.solutions.entomology.BiochemicalAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MolecularAssayViewDTO"%>

<c:set var="page_title" value="Enter_Mechanism_Assays"  scope="request"/>

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
  <label><fmt:message key="Biochemical_Assay"/></label>
</dt>
<dd>
  <div id="BiochemicalAssay">
  </div>
</dd>
<dt>
  <LABEL><fmt:message key="Molecular_Assay"/></LABEL>
</dt>
<dd>
  <div id="MolecularAssay">
  </div>
</dd>


<%
BiochemicalAssayViewDTO infection = (BiochemicalAssayViewDTO) request.getAttribute(AssayController.BIOCHEMICAL);
BiochemicalAssayViewDTO[] infectionRows = (BiochemicalAssayViewDTO[]) request.getAttribute(AssayController.BIOCHEMICAL_ROWS);

String[] infectionKeys = (String[]) request.getAttribute(AssayController.BIOCHEMICAL_KEYS);
Map<String, ColumnSetup> infectionMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.BIOCHEMICAL_COLUMNS);

MolecularAssayViewDTO pooled = (MolecularAssayViewDTO) request.getAttribute(AssayController.MOLECULAR);
MolecularAssayViewDTO[] pooledRows = (MolecularAssayViewDTO[]) request.getAttribute(AssayController.MOLECULAR_ROWS);

String[] pooledKeys = (String[]) request.getAttribute(AssayController.MOLECULAR_KEYS);
Map<String, ColumnSetup> pooledMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.MOLECULAR_COLUMNS);

String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{BiochemicalAssayViewDTO.CLASS, MolecularAssayViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // SETUP THE INFECTION DATA GRID
    var biochemicalData = {
      rows:<%=Halp.getDataMap(infectionRows, infectionKeys, infection)%>,
      columnDefs:<%=Halp.getColumnSetup(infection, infectionKeys, deleteColumn, true, infectionMap)%>,
      defaults:<%=Halp.getDefaultValues(infection, infectionKeys)%>,
      reloadKeys: ['MosquitoId'],
      copy_from_above : ['IdentMethod'],      
      div_id: "BiochemicalAssay",
      data_type: "Mojo.$.<%=BiochemicalAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var biochemicalGrid = MojoGrid.createDataTable(biochemicalData);

    // SETUP THE POOLED DATA GRID
    var molecularData = {
      rows:<%=Halp.getDataMap(pooledRows, pooledKeys, pooled)%>,
      columnDefs:<%=Halp.getColumnSetup(pooled, pooledKeys, deleteColumn, true, pooledMap)%>,
      defaults:<%=Halp.getDefaultValues(pooled, pooledKeys)%>,
      reloadKeys: ['MosquitoId'],
      copy_from_above : ['IdentMethod'],  
      div_id: "MolecularAssay",
      data_type: "Mojo.$.<%=MolecularAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var molecularGrid = MojoGrid.createDataTable(molecularData);    
  });
})();
        
</script>
