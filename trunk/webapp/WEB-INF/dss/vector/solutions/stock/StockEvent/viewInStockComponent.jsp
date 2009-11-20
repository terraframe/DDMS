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


<%@page import="dss.vector.solutions.stock.StockEventViewDTO"%>
<%@page import="dss.vector.solutions.stock.StockEventController"%>

<c:set var="page_title" value="In_Stock"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

StockEventViewDTO view = (StockEventViewDTO) request.getAttribute(StockEventController.ITEM);
StockEventViewDTO[] rows = (StockEventViewDTO[]) request.getAttribute(StockEventController.VIEWS);

String[] attributes = {"ConcreteId", "StockDepot", "Staff", "StaffLabel", "OtherParty", "EventDate", "Item", "TransactionType", "ItemLabel", "AvailableStock", "Quantity", "Cost"};

String deleteColumn = "";
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
    <mjl:property name="date" value="${date}"/>
    <fmt:message key="Back_To_Search"/>
  </mjl:commandLink>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{StockEventViewDTO.CLASS}))%>
<%
Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("ConcreteId", new ColumnSetup(true, false));
map.put("StockDepot", new ColumnSetup(true, false));
map.put("Staff", new ColumnSetup(true, false));
map.put("StaffLabel", new ColumnSetup(true, false));
map.put("OtherParty", new ColumnSetup(true, true));
map.put("EventDate", new ColumnSetup(true, false));
map.put("Item", new ColumnSetup(true, false));
map.put("TransactionType", new ColumnSetup(true, false));
map.put("ItemLabel", new ColumnSetup(false, false));
map.put("AvailableStock", new ColumnSetup(false, false));
map.put("Quantity", new ColumnSetup(false, true));
map.put("Cost", new ColumnSetup(false, true));
%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
	  
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

    var saveHandler = function(request, view_array) {
      var staff = document.getElementById('staff').value;
      var otherParty = document.getElementById('otherParty').value;
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
    };
      
    var data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults: {},
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
