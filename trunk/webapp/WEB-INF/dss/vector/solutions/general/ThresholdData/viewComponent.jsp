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
<%@page import="dss.vector.solutions.general.ThresholdDataController"%>
<%@page import="dss.vector.solutions.general.ThresholdDataViewDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>


<%@page import="org.json.JSONObject"%>
<%@page import="dss.vector.solutions.irs.GeoTargetViewDTO"%>
<%@page import="org.json.JSONArray"%><c:set var="page_title" value="Threshold_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

ThresholdDataViewDTO view = (ThresholdDataViewDTO) request.getAttribute(ThresholdDataController.ITEM);
ThresholdDataViewDTO[] rows = (ThresholdDataViewDTO[]) request.getAttribute(ThresholdDataController.VIEWS);

String[] attributes = (String[]) request.getAttribute(ThresholdDataController.KEYS);
Map<String, ColumnSetup> map = (Map<String, ColumnSetup>) request.getAttribute(ThresholdDataController.COLUMNS);

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
      ${item.seasonMd.displayLabel}
    </label>
  </dt>
  <dd>
      ${season.seasonName} <span class="formatDate"> ${season.startDate} </span> - <span class="formatDate"> ${season.endDate} </span>
  </dd>
</dl>
<div id="ThresholdData"></div>
<br />

<div style="display:none;">
  <form method="POST" action="dss.vector.solutions.general.ThresholdDataController.exportThresholdData.mojo" name="threshold.export" id="threshold.export" >
  </form>
</div>

<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.general.ThresholdDataController.search.mojo" name="search.link" >
    <fmt:message key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{ThresholdDataViewDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{ThresholdDataViewDTO.CLASS, ThresholdDataController.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

    var thresholdValidator = function (oData) {
        if(oData === "")
        {
            return oData;
        }

        var value = 1 * oData;
        
        if(Mojo.Util.isNumber(value)) {
            if(value > 0) {
                return oData;
            }                
        }

        alert(MDSS.localize("Value_Not_Greater_Than_0"));
        return undefined;
    }

    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
      div_id: "ThresholdData",
      data_type: "Mojo.$.<%=ThresholdDataViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false,
      width:"80em",
      after_row_load:function(record){
        if(record.getCount() < (data.rows.length - 1))
        {
          var str = '<form method = "post"';
          str += ' id="'+record.getData('GeoEntity')+'">';
          str += '<input type="hidden" name="geoId" value="'+record.getData('GeoEntity')+'"/>';
          str += '<input type="hidden" name="season.componentId" value="'+record.getData('Season')+'"/>';
          str += " <a href=\"javascript: document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
          str += record.getData('EntityLabel')+'</a></form>';
          data.myDataTable.updateCell(record, 'EntityLabel', str);
        }
      },
      customButtons : [{
          id : 'Export',
          label : MDSS.localize('Export'),
          action : function() {
            var objects = grid.createObjectRepresentation();
            var form = document.getElementById('threshold.export');
            var innerHTML = '';

            if(Mojo.Util.isArray(objects)) {
                for(var i = 0; i < objects.length; i++) {
                    // Decompose the objects for the action parameters
                    var object = objects[i];
                                        
                    innerHTML += '<input type="hidden" name="views_' + i + '.componentId" value="' + object.getId() + '" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.isNew" value="true" />\n';
                    innerHTML += '<input type="hidden" name="#views_' + i + '.actualType" value="dss.vector.solutions.general.ThresholdDataView" />\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.entityLabel" value="' + object.getEntityLabel() + '"/>\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.season" value="' + object.getValue('<%=ThresholdDataViewDTO.SEASON%>') + '"/>\n';
                    innerHTML += '<input type="hidden" name="views_' + i + '.geoEntity" value="' + object.getGeoEntity() + '"/>\n';

                    for(var j = 0; j < 52; j++) {
                    	var outbreak = object['getOutbreak_'+j]();
                    	var identification = object['getIdentification_'+j]();

                    	if(!outbreak) {
                        	var value = calculatedTargets[object.getGeoEntity()][j*2];

                        	if(value) {
                            outbreak = value;
                        	}                            	
                    	}

                    	if(!identification) {
                    		var value = calculatedTargets[object.getGeoEntity()][j*2 + 1];

                    		if(value) {
                    		  identification = value;
                    		}
                    	}
                        	
                    	
                      innerHTML += '<input type="hidden" name="views_' + i + '.outbreak_' + j +'" value="' + outbreak + '"/>\n';
                      innerHTML += '<input type="hidden" name="views_' + i + '.identification_' + j + '" value="' + identification + '"/>\n'                    
                    }
                }                     
            }

            form.innerHTML = innerHTML;
            form.submit();
          }
      }]
          
    };        


    <%
    JSONObject calcuatedTargets = new JSONObject();
    for(ThresholdDataViewDTO threshold :rows)
    {
      calcuatedTargets.put
      (
          threshold.getGeoEntity(),
          new JSONArray(
              Arrays.asList(
                  threshold.getCalculatedThresholds()))); 
    }
    out.println("var calculatedTargets = "+calcuatedTargets+";");
    %>

    
    var grid = MojoGrid.createDataTable(data);

    var dt = data.myDataTable;

    dt.getRecordSet().getRecords().map( function(row) {
      var calulated = calculatedTargets[row.getData('GeoEntity')];
      if(calulated && calulated != '')
      {  
        for (var i =0; i<53 ;i++)
        {
          if(! row.getData('Outbreak_'+i))
          {
          	var calc = calulated[i*2];
            if(calc)
            {
              var col = dt.getColumn('Outbreak_'+i);
              dt.updateCell(row, col, calc );
  
              var lastTd = dt.getTdEl( {
                record : row,
                column : col
              });
              YAHOO.util.Dom.addClass(dt.getTdLinerEl(lastTd), "calculated");
            }
          }
          
          if(! row.getData('Identification_'+i))
          {
            var calc = calulated[i*2+1];
            if(calc)
            {
              var col = dt.getColumn('Identification_'+i);
              dt.updateCell(row, col, calc );
  
              var lastTd = dt.getTdEl( {
                record : row,
                column : col
              });
              YAHOO.util.Dom.addClass(dt.getTdLinerEl(lastTd), "calculated");
            }
          }

        }
      }
    });
    
  });
})();
        
</script>
