<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="dss.vector.solutions.general.MalariaSeasonDTO"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.general.PopulationDataController"%>
<%@page import="dss.vector.solutions.general.PopulationDataViewDTO"%>


<%@page import="dss.vector.solutions.util.yui.DataGrid"%><c:set var="page_title" value="Population_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
PopulationDataViewDTO view = (PopulationDataViewDTO) request.getAttribute(PopulationDataController.ITEM);

DataGrid grid = (DataGrid) request.getAttribute("grid");

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

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    var populationType = <%=view.getPopulationType()%>;
    
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "PopulationData",
      data_type: "Mojo.$.<%=PopulationDataViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false,
      after_row_load:function(record){          
        if(populationType == true && record.getCount() < (data.rows.length))
        {
          var str = '<form method = "post"';
          str += ' id="'+record.getData('GeoEntity')+'">';
          str += '<input type="hidden" name="geoId" value="'+record.getData('GeoEntity')+'"/>';
          str += '<input type="hidden" name="yearOfData" value="'+record.getData('YearOfData')+'"/>';
          str += '<input type="hidden" name="populationType" value="true"/>';
          str += " <a href=\"#\" onclick=\"document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
          str += record.getData('EntityLabel')+'</a></form>';
          
          this.getDataTable().updateCell(record, 'EntityLabel', str);
        }
      },
    };        

    MojoGrid.createDataTable(data);
  });
})();
        
</script>
