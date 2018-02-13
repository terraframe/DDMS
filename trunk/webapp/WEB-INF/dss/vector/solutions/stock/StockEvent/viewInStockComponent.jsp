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

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="dss.vector.solutions.general.MalariaSeasonDTO"%>
<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>


<%@page import="dss.vector.solutions.stock.StockEventViewDTO"%>
<%@page import="dss.vector.solutions.stock.StockEventController"%>


<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="In_Stock"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
%>

<dl>
  <mjl:component item="${item}" param="item">
    <mjl:dt attribute="stockDepot">
      ${entity.displayString}
    </mjl:dt>
    <mjl:dt attribute="item">
      ${term.displayLabel}
    </mjl:dt>
    <mjl:dt attribute="eventDate">
      <span class="formatDate">${date}</span>
    </mjl:dt>
    <mjl:dt attribute="staff">
      <mjl:select valueAttribute="id" param="staff" items="${staff}" id="staff" var="current" includeBlank="true">
        <mjl:option>
          ${current.person}        
        </mjl:option>
      </mjl:select>
    </mjl:dt>
    <mjl:dt attribute="otherParty">
      <mjl:input type="text" param="otherParty" id="otherParty"/>
    </mjl:dt>  
  </mjl:component>
</dl>
<div id="StockEvent"></div>
<br />

<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.stock.StockEventController.search.mojo" name="search.link" >
    <mjl:property name="geoId" value="${entity.geoId}"/>
    <mjl:property name="itemId" value="${term.id}"/>
    <mjl:property name="date" value="${searchDate}"/>
    <mdss:localize key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{StockEventViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
  
    var saveHandler = function(request, parameters) {
      var staff = document.getElementById('staff').value;
      var otherParty = document.getElementById('otherParty').value;
      var view_array = parameters[0];
      var valid = true;

      if(staff === "")  {
        valid = false;
        alert(MDSS.localize("Received_by_is_required"));          
      }

      if(valid) {
        for(var i = 0; i < view_array.length; i++) {
          view_array[i].setStaff(staff);
          view_array[i].setOtherParty(otherParty);
        }

        Mojo.$.<%=StockEventViewDTO.CLASS%>.applyAll(request, view_array);      
      }
      else {
        grid.enableSaveButton();         
      }
    };
      
    var data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetup("")%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "StockEvent",
      data_type: "Mojo.$.<%=StockEventViewDTO.CLASS%>",
      saveFunction:"applyAll",
      reloadKeys : ["AvailableStock", "Quantity", "Cost"],
      excelButtons:false,
      addButton:false,
      cleanDisable : false,
      saveHandler: saveHandler
    };        

    var grid = MojoGrid.createDataTable(data);    
  });
})();
        
</script>
