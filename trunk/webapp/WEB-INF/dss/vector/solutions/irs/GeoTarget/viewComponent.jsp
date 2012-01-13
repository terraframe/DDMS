<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.GeoTargetViewDTO"%>

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
  <mdss:localize key="Back_To_Search"/>
</mjl:commandLink>  
<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>
<%=Halp.loadTypes(Arrays.asList(new String[]{GeoTargetViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    var calculatedTargets = <%=request.getAttribute("calculated")%>;

    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "GeoTargets",
      data_type: "Mojo.$.dss.vector.solutions.irs.GeoTargetView",
      saveFunction: "applyAll",
      addButton:false,
      excelButtons:false,
      after_row_edit:function(record) {
        setRowCaluatedValues(record);
      },
      after_row_load:function(record) {
        var length = this.getModel().length();
          
        if(record.getCount() < (length - 1)) {
          var str = '<form method = "post"';
          str += ' id="'+record.getData('GeoEntity')+'">';
          str += '<input type="hidden" name="geoEntity.componentId" value="'+record.getData('GeoEntity')+'"/>';
          str += '<input type="hidden" name="season.componentId" value="'+record.getData('Season')+'"/>';
          str += '<input type="hidden" name="season.componentId" value="true"/>';
          str += " <a href=\"javascript: document.getElementById('"+record.getData('GeoEntity')+"').submit();\">";
          str += record.getData('EntityLabel')+'</a></form>';
          this.getDataTable().updateCell(record, 'EntityLabel', str);
        }
      }
    };
    
    var grid = MojoGrid.createDataTable(data);

    var dt = grid.getDataTable();

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
    
  });
})();                      
</script>
