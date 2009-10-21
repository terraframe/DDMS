<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="dss.vector.solutions.general.MalariaSeasonDTO"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.general.PopulationDataController"%>
<%@page import="dss.vector.solutions.general.PopulationDataViewDTO"%>

<c:set var="page_title" value="Population_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

PopulationDataViewDTO view = (PopulationDataViewDTO) request.getAttribute(PopulationDataController.ITEM);
PopulationDataViewDTO[] rows = (PopulationDataViewDTO[]) request.getAttribute(PopulationDataController.VIEWS);

String[] attributes = {"ConcreteId", "GeoEntity", "YearOfData", "EntityLabel", "Population", "GrowthRate", "Estimated"};

String deleteColumn = "";
%>

<dl>
  <dt>
    <label>
      ${item.geoEntityMd.displayLabel}
    </label>
  </dt>
  <dd>
     ${item.entityLabel} - ${item.geoEntity} 
  </dd>
  <dt>
    <label>
      ${item.yearOfDataMd.displayLabel}
    </label>
  </dt>
  <dd>
    ${item.yearOfData}
  </dd>
</dl>
<div id="PopulationData"></div>
<br />

<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.general.PopulationDataController.search.mojo" name="search.link" >
    <fmt:message key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{PopulationDataViewDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{PopulationDataViewDTO.CLASS, PopulationDataController.CLASS}))%>
<%
ColumnSetup population = new ColumnSetup(false, true);
population.setSum(true);

Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("ConcreteId", new ColumnSetup(true, false));
map.put("GeoEntity", new ColumnSetup(true, false));
map.put("YearOfData", new ColumnSetup(true, false));
map.put("EntityLabel", new ColumnSetup(false, false));
map.put("Population", population);
map.put("GrowthRate", new ColumnSetup(false, true));
map.put("Estimated", new ColumnSetup(true, false));
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>
        
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults: {},
      div_id: "PopulationData",
      data_type: "Mojo.$.<%=PopulationDataViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false
    };        

    var grid = MojoGrid.createDataTable(data);
  });
})();
        
</script>
