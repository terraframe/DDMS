<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.irs.GeoTargetViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="dss.vector.solutions.PropertyDTO"%>
<%@page import="dss.vector.solutions.PropertyInfo"%>
<%@page import="dss.vector.solutions.irs.GeoTargetDTO"%>

<%@page import="dss.vector.solutions.PropertyInfo"%>
<%@page import="java.util.Date"%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>

<%@page import="dss.vector.solutions.surveillance.PeriodTypeDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>

<%@page import="dss.vector.solutions.general.MalariaSeasonDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Arrays"%><c:set var="page_title" value="Edit_GeoTarget"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

  <dl>
    <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
       ${item.geoEntity.displayString} 
    </dd>
    <dt>
      <label>
        ${item.seasonMd.displayLabel}
        <fmt:formatDate value="${item.season.startDate}" pattern="<%=Halp.getDateFormatString(request)%>"/>
        -
        <fmt:formatDate value="${item.season.endDate}" pattern="<%=Halp.getDateFormatString(request)%>"/>

      </label>
    </dt>
    <dd>
      ${item.season.seasonName}
    </dd>
  </dl>
<br/>
<div id="GeoTargets"></div>
<br />
<mjl:commandLink  action="dss.vector.solutions.irs.GeoTargetController.viewAll.mojo" name="dss.vector.solutions.irs.GeoTarget.viewAll.link" >
  <fmt:message key="Back_To_Search"/>
</mjl:commandLink>  
<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
GeoTargetViewDTO[] rows = (GeoTargetViewDTO[]) request.getAttribute("geoTargetViews");
String[] attribs = {"TargetId","GeoEntity","EntityName","Season"};
GeoTargetViewDTO mdView = new GeoTargetViewDTO(clientRequest);
GeoTargetViewDTO item = (GeoTargetViewDTO) request.getAttribute("item");


String delete_row = "";

String[] types_to_load ={"dss.vector.solutions.irs.GeoTargetView"};

String colConfig = "{key:'TargetId',label:'TargetId',hidden:true}";
colConfig += "\n,{key:'GeoEntity',label:'" + item.getGeoEntityMd().getDisplayLabel() + "',hidden:true}";
colConfig += "\n,{key:'EntityName',label:'" + item.getGeoEntityMd().getDisplayLabel() + "',resizeable:true}";
colConfig += "\n,{key:'Season',label:'Season',hidden:true}";

MalariaSeasonDTO season = item.getSeason();


  long seasonStart = season.getStartDate().getTime();
  long seasonEnd = season.getEndDate().getTime();
  GregorianCalendar cal = new GregorianCalendar();
  cal.setTime(season.getStartDate());
  Integer seasonStartYear = cal.get(Calendar.YEAR);

  EpiDateDTO[] weeks = season.getEpiWeeks(); 
  int numWeeks =  weeks[0].getNumberOfEpiWeeks();
  int startWeek = weeks[0].getPeriod();
  

int i = 0;

while(i<startWeek)
{
  colConfig += "\n,{key:'Target_" + i + "',hidden:true}";
  i++;
}

for (EpiDateDTO epiWeek : weeks){
  String startDate = Halp.getFormatedDate(request,epiWeek.getStartDate());
  String endDate = Halp.getFormatedDate(request,epiWeek.getEndDate());
  colConfig += ",\n{width:20, sum:true, key:'Target_" + i%numWeeks + "',label:'" + ((epiWeek.getPeriod()%numWeeks)+1) + "',title:'" + startDate + " -> " + endDate + "',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})}";
  i++;
}
while(i<54)
{
  colConfig += "\n,{key:'Target_" + i + "',hidden:true}";
  i++;
}
%>
<script type="text/javascript" defer ="defer">

<%
   JSONObject calcuatedTargets = new JSONObject();
   for(GeoTargetViewDTO geoTarget :rows)
   {
     calcuatedTargets.put(geoTarget.getGeoEntity().getId(),new JSONArray(Arrays.asList(geoTarget.getCalculatedTargets()))); 
   }
   out.println("var calculatedTargets = "+calcuatedTargets+";");
%>

<%=com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true)%>

GeoTargetData = { rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
       columnDefs: [<%=colConfig%>],
       defaults:<%=Halp.getDefaultValues(mdView, attribs)%>,
              div_id: "GeoTargets",
              data_type: "Mojo.$.dss.vector.solutions.irs.GeoTargetView",
              saveFunction: "applyAll",
              //width:"75em",
              addButton:false,
              excelButtons:false,
              after_row_edit:function(record){
      	         setRowCaluatedValues(record);
              },
              after_row_load:function(record){
                if(record.getCount() < (GeoTargetData.rows.length - 1))
                {
                var str = '<form method = "post"';
                str += ' id="'+record.getData('GeoEntity')+'">';
                str += '<input type="hidden" name="geoEntity.componentId" value="'+record.getData('GeoEntity')+'"/>';
                str += '<input type="hidden" name="season.componentId" value="'+record.getData('Season')+'"/>';
                str += '<input type="hidden" name="season.componentId" value="true"/>';
                str += " <a href=\"javascript: document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
                str += record.getData('EntityName')+'</a></form>';
                GeoTargetData.myDataTable.updateCell(record, 'EntityName', str);
                }
              }
          };
    MojoGrid.createDataTable(GeoTargetData);

    var dt = GeoTargetData.myDataTable;

    var setRowCaluatedValues = function(row) {
      var calulated = calculatedTargets[row.getData('GeoEntity')];
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
    

    
</script>
