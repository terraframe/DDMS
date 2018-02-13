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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.entomology.AssayController"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.InfectionAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.PooledInfectionAssayViewDTO"%>

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
  <label><mdss:localize key="Infection_Assay"/></label>
</dt>
<dd>
  <div id="InfectionAssay">
  </div>
</dd>
<dt>
  <LABEL><mdss:localize key="Pooled_Infection_Assay"/></LABEL>
</dt>
<dd>
  <div id="PooledInfectionAssay">
  </div>
</dd>


<%
DataGrid infection = (DataGrid) request.getAttribute(AssayController.INFECTION_GRID);
DataGrid pooled = (DataGrid) request.getAttribute(AssayController.POOLED_GRID);
%>


<%=Halp.loadTypes(Arrays.asList(new String[]{InfectionAssayViewDTO.CLASS, PooledInfectionAssayViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    // SETUP THE INFECTION DATA GRID
    var infectionData = {
      rows:<%=infection.getData()%>,
      columnDefs:<%=infection.getColumnSetupWithDelete()%>,
      defaults:<%=infection.getDefaultValues()%>,
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
      rows:<%=pooled.getData()%>,
      columnDefs:<%=pooled.getColumnSetupWithDelete()%>,
      defaults:<%=pooled.getDefaultValues()%>,
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
