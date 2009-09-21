<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

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

<%
String sum = request.getAttribute("sumLastRow").toString();
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
ResourceTargetViewDTO[] rows = (ResourceTargetViewDTO[]) request.getAttribute("resourceTargetViews");
String[] attribs = {"TargetId","TargetYear","Targeter"};
ResourceTargetViewDTO mdView = new ResourceTargetViewDTO(clientRequest);
ResourceTargetDTO item = new ResourceTargetDTO(clientRequest) ;

String delete_row = "";

String colConfig = "{key:'TargetId',label:'TargetId',hidden:true}";
colConfig += "\n,{key:'Season',label:'Season',hidden:true}";
colConfig += "\n,{key:'Targeter',label:'" + "Targeter" + "',hidden:true}";
colConfig += "\n,{key:'TargeterName',label:'" + "Name" + "',resizeable:true}";


for(int i = 0;i<=52;i++)
{
    colConfig += ",\n{sum:" + sum  + ", key:'Target_" + i + "',label:'" + (i+1) + "',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})}";
}

%>

<script type="text/javascript" defer="defer">

ResourceTargetData = { rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
       columnDefs: [<%=colConfig%>],
              defaults: {},
              div_id: "ResourceTargets",
              data_type: "Mojo.$.dss.vector.solutions.irs.ResourceTargetView",
              saveFunction: "applyAll",
              width:"75em",
              addButton:false,
              excelButtons:false
          };
         MojoGrid.createDataTable(ResourceTargetData);

        

         var dt = ResourceTargetData.myDataTable;

         var numRows = dt.getRecordSet().getLength();

         var lastRow =  dt.getRecordSet().getRecord(numRows-1);

         for (var i =0; i<53 ;i++)
         {        
           if(! lastRow.getData('Target_'+i))
           {
             var sum = 0;
             for(var j=0; j < numRows - 1 ;j++)
             {
               var value = dt.getRecordSet().getRecord(j).getData('Target_'+i);          
               if(value)
               {
                 sum += parseInt(value,10);
               }
             }
             if(sum > 0)
             {
                dt.updateCell(lastRow, 'Target_'+i,'<span class="calculated">' + sum + '</span>');
             }
           }
         }
         
</script>
<mjl:commandLink action="dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo" name="dss.vector.solutions.irs.ResourceTarget.viewAll.link" >
  <fmt:message key="Back_To_Search"/>
</mjl:commandLink>  



<%String[] types_to_load ={"dss.vector.solutions.irs.ResourceTargetView"}; %>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>
