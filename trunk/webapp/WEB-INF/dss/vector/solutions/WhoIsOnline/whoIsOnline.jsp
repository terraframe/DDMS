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
<%@page import="dss.vector.solutions.WhoIsOnlineViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<c:set var="page_title" value="See_Who_Is_Logged_In" scope="request" />

<jwr:style src="/bundles/jqueryStyle.css" useRandomParam="false"/>
<jwr:style src="/bundles/scheduler.css" useRandomParam="false"/>

<jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/genericDatatable.js" useRandomParam="false"/>
<jwr:script src="/bundles/whoIsOnline.js" useRandomParam="false"/>

<style>

.ui-widget-header {
  border: none;
  background: #ffffff;
  color: rgb(102, 102, 102);
  font-weight: bold;
}

.ui-state-active a,
.ui-state-active a:link,
.ui-state-active a:visited {
  color: #333;
  text-decoration: none;
} 

.ui-state-default a,
.ui-state-default a:link,
.ui-state-default a:visited {
    color: #333;
    text-decoration: none;
}

.ui-state-default,
.ui-widget-content .ui-state-default,
.ui-widget-header .ui-state-default {
  border: none;
  background: #ffffff;
  font-weight: bold;
  color: #333;
  cursor:pointer; 
}

.ui-state-hover,
.ui-widget-content .ui-state-hover,
.ui-widget-header .ui-state-hover,
.ui-state-focus,
.ui-widget-content .ui-state-focus,
.ui-widget-header .ui-state-focus {
  border: none;
  background: #ffffff;
  font-weight: bold;
  color: #333;
  cursor:pointer; 
}

.ui-state-active,
.ui-widget-content .ui-state-active,
.ui-widget-header .ui-state-active {
  border: 1px solid rgb(213, 214, 215);
  background: #ffffff;
  font-weight: bold;
  color: #333;
  cursor:pointer; 
}

.ui-state-default .ui-icon {
  background-image: none;
}

table.com-runwaysdk-ui-scheduler-JobTable tr {
    cursor:pointer;
}

</style>

<%
    String[] types = new String[]{WhoIsOnlineViewDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(types));
%>

<%=Halp.loadTypes(loadables)%>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<br />

<div id='whoIsOnlineDiv'>
</div>

<script type="text/javascript">
(function(){
 YAHOO.util.Event.onDOMReady(function(){
   
   var oldFac = com.runwaysdk.ui.Manager.getFactoryName();
   com.runwaysdk.ui.Manager.setFactory("JQuery");

   var config = {
     oLanguage : {
       oAria: {
         sSortAscending: MDSS.localize("sort_column_ascending"),
         sSortDescending: MDSS.localize("sort_column_descending")
       },
       oPaginate: {
         sFirst: MDSS.localize("First"),
         sLast: MDSS.localize("Last"),
         sNext: MDSS.localize("Next"),
         sPrevious: MDSS.localize("Previous")
       },
       sEmptyTable: MDSS.localize("No_data_in_table"),
       sInfo: MDSS.localize("Showing_records"),
       sInfoEmpty: MDSS.localize("Showing_zero_records"),
       sInfoFiltered: MDSS.localize("Filtered_entries"),
       sLengthMenu: MDSS.localize("Show_menu"),
       sLoadingRecords: MDSS.localize("Loading"),
       sProcessing: MDSS.localize("Processing"),
       sSearch: MDSS.localize("Search"),
       sZeroRecords: MDSS.localize("No_matching_records")
     }
   };
   
   com.runwaysdk.Localize.defineLanguage('com.runwaysdk.ui.PollingRequest', {
     "timeout" : MDSS.localize("Polling_timeout"),
     "dialogTitle" : MDSS.localize("Polling_Failed"),
     "errLabel" : MDSS.localize("Error_Label"),
     "failText1" : MDSS.localize("Polling_text_1"),
     "failText2" : MDSS.localize("Polling_text_2"),
     "failText3" : MDSS.localize("Polling_text_3"),
     "failText4" : MDSS.localize("Polling_text_4")
   });
   
   var parent = document.getElementById('whoIsOnlineDiv');
   var widget = new dss.vector.solutions.WhoIsOnline(config);
   widget.render(parent);
   
   if (oldFac != null)
   {
     com.runwaysdk.ui.Manager.setFactory(oldFac);
   }
 });
})();
 
</script>