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
<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetViewDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="Edit_Spray_Team_Target"  scope="request"/>

<div id="ResourceTargets"></div>
<br/>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{ResourceTargetViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    var calculatedTargets = <%=request.getAttribute("calculatedTargets")%>;
  
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "ResourceTargets",
      data_type: "Mojo.$.dss.vector.solutions.irs.ResourceTargetView",
      saveFunction: "applyAll",
      addButton:false,
      excelButtons:false,
      after_row_edit:function(record) {
        setRowCaluatedValues(record);
      }
    };
    
    var setRowCaluatedValues = function(row) {
      var targeterId = row.getData('Targeter');      
      var calulated = calculatedTargets[targeterId];

      if(calulated && calulated != '')
      {  
        for (var i =0; i<53 ;i++)
        {
          if(! row.getData('Target_'+i))
          {
            if(calulated[i])
            {
              var col = dt.getColumn('Target_'+i);
              dt.updateCell(row, col, calulated[i] );
  
              var lastTd = dt.getTdEl( {
                record : row,
                column : col
              });
              YAHOO.util.Dom.addClass(dt.getTdLinerEl(lastTd), "calculated");
            }
          }
        }
      }
    }

    var showCalculatedValues = function() {
      dt.getRecordSet().getRecords().map(setRowCaluatedValues);            
    }

    var listener = function(event) {
      if(event.getType() == MDSS.Event.AFTER_SAVE) {
        showCalculatedValues();
      }
    }

    var grid = MojoGrid.createDataTable(data);
    var dt = grid.getDataTable();
    
    grid.addListener(listener);
    
    showCalculatedValues();
  });
})();                  
</script>

<mjl:commandLink action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" >
  <mdss:localize key="Back_To_Search" />
</mjl:commandLink>
