<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

<%@page import="dss.vector.solutions.EpiWeek"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ResourceTargetDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set var="page_title" value="Edit_Spray_Team_Target"  scope="request"/>
<div id="ResourceTargets"></div>
<br/>

<div id="buttons" class="noprint">

<span id="ResourceTargetsSaverows" class="yui-button yui-push-button">
<span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span>

<form method="get" action="excelimport" style="display:inline;">
       <span class="yui-button yui-push-button">
       <span class="first-child">
        <button type="submit"><fmt:message key="Excel_Import_Header" /></button>
        </span>
        </span>
</form>
<form method="post" action="excelexport" style="display:inline;">
        <input type="hidden" name="type" value="dss.vector.solutions.entomology.MorphologicalSpecieGroupView"/>
        <span class="yui-button yui-push-button">
        <span class="first-child">
        <button type="submit"><fmt:message key="Excel_Export_Header" /></button>
        </span>
        </span>
</form>
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>

</div>


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
ResourceTargetViewDTO[] rows = (ResourceTargetViewDTO[]) request.getAttribute("resourceTargetViews");
String[] attribs = {"TargetId","TargetYear","Targeter"};
ResourceTargetViewDTO mdView = new ResourceTargetViewDTO(clientRequest);
ResourceTargetDTO item = new ResourceTargetDTO(clientRequest) ;

String delete_row = "";

String colConfig = "{key:'TargetId',label:'TargetId',hidden:true}";
colConfig += "\n,{key:'TargetYear',label:'Season',hidden:true}";
colConfig += "\n,{key:'Targeter',label:'" + "Targeter" + "',hidden:true}";
colConfig += "\n,{key:'TargeterName',label:'" + "Name" + "',resizeable:true}";


for(int i = 0;i<=52;i++)
{
    colConfig += ",\n{sum:true, key:'Target_" + i + "',label:'" + (i+1) + "',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})}";
}

%>

<script type="text/javascript">

ResourceTargetData = { rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
       columnDefs: [<%=colConfig%>],
              defaults: {},
              div_id: "ResourceTargets",
              data_type: "Mojo.$.dss.vector.solutions.irs.ResourceTargetView",
              saveFunction: "applyAll",
              width:"75em"
          };
    //YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(ResourceTargetData));
    window.addEventListener('load', MojoGrid.createDataTable(ResourceTargetData) , false);
   //document.addEventListener('load', MojoGrid.createDataTable(ResourceTargetData), false);
</script>
<mjl:commandLink display="Back_To_Search" action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" />


<%String[] types_to_load ={"dss.vector.solutions.irs.ResourceTargetView"}; %>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>
