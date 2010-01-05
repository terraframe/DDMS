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
     ${entity.displayString} 
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
population.setSum(view.getPopulationType());

Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("ConcreteId", new ColumnSetup(true, false));
map.put("GeoEntity", new ColumnSetup(true, false));
map.put("YearOfData", new ColumnSetup(true, false));
map.put("PopulationType", new ColumnSetup(true, false));
map.put("EntityLabel", new ColumnSetup(false, false));
map.put("Population", population);
map.put("GrowthRate", new ColumnSetup(false, true));
map.put("Estimated", new ColumnSetup(true, false));

%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

    var populationType = <%=view.getPopulationType()%>;
    
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
      div_id: "PopulationData",
      data_type: "Mojo.$.<%=PopulationDataViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false,
      after_row_load:function(record){          
        if(populationType == true && record.getCount() < (data.rows.length - 1))
        {
        	var str = '<form method = "post"';
          str += ' id="'+record.getData('GeoEntity')+'">';
          str += '<input type="hidden" name="geoId" value="'+record.getData('GeoEntity')+'"/>';
          str += '<input type="hidden" name="yearOfData" value="'+record.getData('YearOfData')+'"/>';
          str += '<input type="hidden" name="populationType" value="true"/>';
          str += " <a href=\"#\" onclick=\"document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
          str += record.getData('EntityLabel')+'</a></form>';
          data.myDataTable.updateCell(record, 'EntityLabel', str);
        }
      },
    };        

    var grid = MojoGrid.createDataTable(data);
    
    <%
      out.println("var calculatedValues = " + request.getAttribute("calculatedValues") + ";");
    %>

    var dt = data.myDataTable;

    dt.getRecordSet().getRecords().map( function(row) {

      if(!row.getData('Population'))
      {
        var calculated = calculatedValues[row.getData('GeoEntity')];

        if(calculated && calculated != '')
        {  
          var value = calculated[0];
          
          if(value)
          {
            var col = dt.getColumn('Population');

            dt.updateCell(row, col, value);
  
            var lastTd = dt.getTdEl( {record : row, column : col});
            
            YAHOO.util.Dom.addClass(dt.getTdLinerEl(lastTd), "calculated");
          }
        }
      }
    });
     

  });
})();
        
</script>
