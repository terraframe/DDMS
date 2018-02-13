<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="org.json.JSONObject"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.general.ThresholdDataController"%>
<%@page import="dss.vector.solutions.general.ThresholdDataViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<c:set var="page_title" value="Threshold_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
JSONObject calculatedTargets = (JSONObject) request.getAttribute("calculatedTargets");
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
      ${item.seasonMd.displayLabel}
    </label>
  </dt>
  <dd>
      ${season.seasonLabel.value} <span class="formatDate"> ${season.startDate} </span> - <span class="formatDate"> ${season.endDate} </span>
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
    <mdss:localize key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{ThresholdDataViewDTO.CLASS, ThresholdDataController.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 

    var thresholdValidator = function (oData) {
      if(oData === "")
      {
        return oData;
      }

      var value = MDSS.parseNumber(oData);
        
      if(Mojo.Util.isNumber(value)) {
        if(value > 0) {
          return oData;
        }                
      }

      alert(MDSS.localize("Value_Not_Greater_Than_0"));
      
      return undefined;
    }

    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "ThresholdData",
      data_type: "Mojo.$.<%=ThresholdDataViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false,
      after_row_load:function(record){
        var _threshold = record.getData('ThresholdType');
        var thresholdType = (_threshold == 'true');
        
        if(thresholdType == true && (record.getCount() < (this.getModel().length() - 1)))
        {
          var str = '<form method = "post"';
          str += ' id="'+record.getData('GeoEntity')+'">';
          str += '<input type="hidden" name="geoId" value="'+record.getData('GeoEntity')+'"/>';
          str += '<input type="hidden" name="season.componentId" value="'+record.getData('Season')+'"/>';
          str += '<input type="hidden" name="thresholdType" value="'+thresholdType+'"/>';
          str += " <a href=\"javascript: document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
          str += record.getData('EntityLabel')+'</a></form>';
          this.getDataTable().updateCell(record, 'EntityLabel', str);
        }
      },
      customButtons : [{
          id : 'Export',
          label : MDSS.localize('Export'),
          action : function() {
            var parameters = grid.getModel().getParameters(); 
            var objects = parameters[0];
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

                    for(var j = 0; j < 53; j++) {
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
                          

                      if(outbreak != null) {
                        innerHTML += '<input type="hidden" name="views_' + i + '.outbreak_' + j +'" value="' + outbreak + '"/>\n';
                      }

                      if(identification != null) {
                        innerHTML += '<input type="hidden" name="views_' + i + '.identification_' + j + '" value="' + identification + '"/>\n'                    
                      }
                    }
                }                     
            }

            form.innerHTML = innerHTML;
            form.submit();
          }
      }]
          
    };        


    <%
    out.println("var calculatedTargets = " + calculatedTargets + ";");
    %>

    
    var grid = MojoGrid.createDataTable(data);

    var dt = grid.getDataTable();

    dt.getRecordSet().getRecords().map( function(row) {
      var geoId = row.getData('GeoEntity')
      var calulated = calculatedTargets[geoId];
        
      if(calulated && calulated != '')
      {  
        for (var i =0; i<53 ;i++)
        {
          var outbreak = row.getData('Outbreak_'+i);
        
          if(!outbreak)
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

          var identification = row.getData('Identification_'+i)
          if(!identification)
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
