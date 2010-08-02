<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

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
    
    var grid = MojoGrid.createDataTable(data);

    var dt = grid.getDataTable();

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

    dt.getRecordSet().getRecords().map(setRowCaluatedValues);    
  });
})();                  
</script>

<mjl:commandLink action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" >
  <fmt:message key="Back_To_Search"/>
</mjl:commandLink>
