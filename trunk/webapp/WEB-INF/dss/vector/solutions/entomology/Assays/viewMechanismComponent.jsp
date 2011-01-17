<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.BiochemicalAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MolecularAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.AssayController"%>

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
  <label><mdss:localize key="Biochemical_Assay"/></label>
</dt>
<dd>
  <div id="BiochemicalAssay">
  </div>
</dd>
<dt>
  <LABEL><mdss:localize key="Molecular_Assay"/></LABEL>
</dt>
<dd>
  <div id="MolecularAssay">
  </div>
</dd>


<%
DataGrid biochemical = (DataGrid) request.getAttribute(AssayController.BIOCHEMICAL_GRID);
DataGrid molecular = (DataGrid) request.getAttribute(AssayController.MOLECULAR_GRID);
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{BiochemicalAssayViewDTO.CLASS, MolecularAssayViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // SETUP THE INFECTION DATA GRID
    var biochemicalData = {
      rows:<%=biochemical.getData()%>,
      columnDefs:<%=biochemical.getColumnSetupWithDelete()%>,
      defaults:<%=biochemical.getDefaultValues()%>,
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
      rows:<%=molecular.getData()%>,
      columnDefs:<%=molecular.getColumnSetupWithDelete()%>,
      defaults:<%=molecular.getDefaultValues()%>,
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
