<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>

<mjl:messages>
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
String sum = request.getAttribute("sumLastRow").toString();
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
ResourceTargetDTO item = new ResourceTargetDTO(clientRequest) ;


ResourceTargetViewDTO mdView = (ResourceTargetViewDTO) request.getAttribute("item");
ResourceTargetViewDTO[] rows = (ResourceTargetViewDTO[]) request.getAttribute("resourceTargetViews");

String[] keys = (String[]) request.getAttribute("keys");
Map<String, ColumnSetup> map = (Map<String, ColumnSetup>) request.getAttribute("columns");
%>

<%=Halp.loadTypes(Arrays.asList(new String[]{ResourceTargetViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    ResourceTargetData = {
      rows:<%=Halp.getDataMap(rows, keys, mdView)%>,
      columnDefs:<%=Halp.getColumnSetup(mdView, keys, "", true, map)%>,
      defaults:<%=Halp.getDefaultValues(mdView, keys)%>,
      div_id: "ResourceTargets",
      data_type: "Mojo.$.dss.vector.solutions.irs.ResourceTargetView",
      saveFunction: "applyAll",
      addButton:false,
      excelButtons:false
    };
    
    MojoGrid.createDataTable(ResourceTargetData);

    var dt = ResourceTargetData.myDataTable;
    var numRows = dt.getRecordSet().getLength();
    var lastRow =  dt.getRecordSet().getRecord(numRows-1);

    for (var i =0; i<53 ;i++) {        
      if(! lastRow.getData('Target_'+i)) {
        var sum = 0;
        
        for(var j=0; j < numRows - 1 ;j++) {
          var value = dt.getRecordSet().getRecord(j).getData('Target_'+i);          

          if(value) {
            sum += parseInt(value,10);
          }
        }
        
        if(sum > 0) {
          dt.updateCell(lastRow, 'Target_'+i,'<span class="calculated">' + sum + '</span>');
        }
      }
    }
  });
})();                  
</script>

<mjl:commandLink action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" >
  <fmt:message key="Back_To_Search"/>
</mjl:commandLink>
