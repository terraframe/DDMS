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


<%@page import="dss.vector.solutions.util.yui.DataGrid"%><mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set var="page_title" value="Edit_Spray_Team_Target"  scope="request"/>
<div id="ResourceTargets"></div>
<br/>

<style type="text/css">
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a
{
  vertical-align:bottom;
  background-color:#DDDDDD;
  background:none;
}

.yui-dt-label
{
  /*writing-mode: tb-rl;*/
  -moz-transform: rotate(-90deg);
  width:10px;
  height:240px;
  display:block;
  position:relative;
  top:110px;
  left:110px;
}
</style>
<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{ResourceTargetViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    ResourceTargetData = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "ResourceTargets",
      data_type: "Mojo.$.dss.vector.solutions.irs.ResourceTargetView",
      saveFunction: "applyAll",
      addButton:false,
      excelButtons:false
    };
    
    var grid = MojoGrid.createDataTable(ResourceTargetData);
  });
})();                  
</script>

<mjl:commandLink action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" >
  <fmt:message key="Back_To_Search"/>
</mjl:commandLink>
