<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.entomology.InfectionAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.AssayController"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.PooledInfectionAssayViewDTO"%>


<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeBooleanMdDTO"%>

<c:set var="page_title" value="Enter_Infection_Assays"  scope="request"/>

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
  <label><fmt:message key="Infection_Assay"/></label>
</dt>
<dd>
  <div id="InfectionAssay">
  </div>
</dd>
<dt>
  <LABEL><fmt:message key="Pooled_Infection_Assay"/></LABEL>
</dt>
<dd>
  <div id="PooledInfectionAssay">
  </div>
</dd>


<%
InfectionAssayViewDTO infection = (InfectionAssayViewDTO) request.getAttribute(AssayController.INFECTION);
InfectionAssayViewDTO[] infectionRows = (InfectionAssayViewDTO[]) request.getAttribute(AssayController.INFECTION_ROWS);

String[] infectionKeys = (String[]) request.getAttribute(AssayController.INFECTION_KEYS);
Map<String, ColumnSetup> infectionMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.INFECTION_COLUMNS);

PooledInfectionAssayViewDTO pooled = (PooledInfectionAssayViewDTO) request.getAttribute(AssayController.POOLED);
PooledInfectionAssayViewDTO[] pooledRows = (PooledInfectionAssayViewDTO[]) request.getAttribute(AssayController.POOLED_ROWS);

String[] pooledKeys = (String[]) request.getAttribute(AssayController.POOLED_KEYS);
Map<String, ColumnSetup> pooledMap = (Map<String, ColumnSetup>) request.getAttribute(AssayController.POOLED_COLUMNS);

String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{InfectionAssayViewDTO.CLASS, PooledInfectionAssayViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // SETUP THE INFECTION DATA GRID
    var infectionData = {
      rows:<%=Halp.getDataMap(infectionRows, infectionKeys, infection)%>,
      columnDefs:<%=Halp.getColumnSetup(infection, infectionKeys, deleteColumn, true, infectionMap)%>,
      defaults:<%=Halp.getDefaultValues(infection, infectionKeys)%>,
      reloadKeys: ['MosquitoId'],
      copy_from_above : ['IdentMethod'],
      div_id: "InfectionAssay",
      data_type: "Mojo.$.<%=InfectionAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var infectionGrid = MojoGrid.createDataTable(infectionData);

    // SETUP THE POOLED DATA GRID
    var pooledData = {
      rows:<%=Halp.getDataMap(pooledRows, pooledKeys, pooled)%>,
      columnDefs:<%=Halp.getColumnSetup(pooled, pooledKeys, deleteColumn, true, pooledMap)%>,
      defaults:<%=Halp.getDefaultValues(pooled, pooledKeys)%>,
      reloadKeys: ['PoolId'],
      copy_from_above : ['IdentMethod'],      
      div_id: "PooledInfectionAssay",
      data_type: "Mojo.$.<%=PooledInfectionAssayViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:true
    };        
 
    var pooledGrid = MojoGrid.createDataTable(pooledData);    
  });
})();
        
</script>
