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
<c:set var="page_title" value="Edit_GeoTarget"  scope="request"/>
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
      ${item.geoEntity.geoId } (${item.geoEntity.entityName})
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
<mjl:commandLink display="Back_To_Search" action="dss.vector.solutions.irs.GeoTargetController.viewAll.mojo" name="dss.vector.solutions.irs.GeoTarget.viewAll.link" />
<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
GeoTargetViewDTO[] rows = (GeoTargetViewDTO[]) request.getAttribute("geoTargetViews");
String[] attribs = {"TargetId","GeoEntity","EntityName","Season"};
GeoTargetViewDTO mdView = new GeoTargetViewDTO(clientRequest);
GeoTargetDTO item = (GeoTargetDTO) request.getAttribute("item");


String delete_row = "";

String[] types_to_load ={"dss.vector.solutions.irs.GeoTargetView"};

String colConfig = "{key:'TargetId',label:'TargetId',hidden:true}";
colConfig += "\n,{key:'GeoEntity',label:'" + item.getGeoEntityMd().getDisplayLabel() + "',hidden:true}";
colConfig += "\n,{key:'EntityName',label:'" + item.getGeoEntityMd().getDisplayLabel() + "',resizeable:true}";
colConfig += "\n,{key:'Season',label:'Season',hidden:true}";

Date epiStart = PropertyDTO.getDate(clientRequest,PropertyInfo.EPI_WEEK_PACKAGE,PropertyInfo.EPI_START);
long seasonStart = item.getSeason().getStartDate().getTime();
long seasonEnd =item.getSeason().getEndDate().getTime();
GregorianCalendar cal = new GregorianCalendar();
cal.setTime(item.getSeason().getStartDate());
int seasonStartYear = cal.get(Calendar.YEAR);
for(int i = 0;i<=106;i++)
{
  EpiDateDTO epiWeek = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, i, seasonStartYear);
  long weekStart = epiWeek.getStartDate().getTime();

  if(weekStart > seasonStart && weekStart < seasonEnd )
  {
    String startDate = Halp.getFormatedDate(request,epiWeek.getStartDate());
    String endDate = Halp.getFormatedDate(request,epiWeek.getEndDate());

    colConfig += ",\n{sum:true, key:'Target_" + i + "',label:'" + ((i%53)+1) + "',title:'" + startDate + " -> " + endDate + "',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})}";
  }
  else
  {
    colConfig += "\n,{key:'Target_" + i + "',hidden:true}";
  }
}

%>
<script type="text/javascript">

<%=com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true)%>

GeoTargetData = { rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
       columnDefs: [<%=colConfig%>],
              defaults: {},
              div_id: "GeoTargets",
              data_type: "Mojo.$.dss.vector.solutions.irs.GeoTargetView",
              saveFunction: "applyAll",
              width:"75em",
              addButton:false,
              excelButtons:false
          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(GeoTargetData));
</script>
