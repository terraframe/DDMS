<%@page import="com.runwaysdk.system.scheduler.JobHistoryViewDTO"%>
<%@page import="com.runwaysdk.system.scheduler.QualifiedTypeJobDTO"%>
<%@page import="com.runwaysdk.system.scheduler.ExecutableJobDescriptionDTO"%>
<%@page import="com.runwaysdk.system.scheduler.ExecutableJobDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.report.ReportJobDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<c:set scope="request" var="page_title" value="View_All_ReportJob" />

<jwr:style src="/bundles/jqueryStyle.css" useRandomParam="false"/>
<jwr:style src="/bundles/scheduler.css" useRandomParam="false"/>

<jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/genericDatatable.js" useRandomParam="false"/>
<jwr:script src="/bundles/runwayDatatable.js" useRandomParam="false"/>

<%
    String[] types = new String[]{ReportJobDTO.CLASS, ExecutableJobDTO.CLASS, ExecutableJobDescriptionDTO.CLASS, QualifiedTypeJobDTO.CLASS, JobHistoryViewDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(types));
%>

<%=Halp.loadTypes(loadables)%>



<mjl:messages>
  <mjl:message />
</mjl:messages>
<div id='schedulerDiv'>
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
   
   /**
    * LANGUAGE
    */
   com.runwaysdk.Localize.defineLanguage('com.runwaysdk.ui.scheduler.Scheduler', {
     "jobs" : MDSS.localize("Jobs"),
     "history" : MDSS.localize("History"),
     
     "editJobTitle" : MDSS.localize("Edit Job"),
     "scheduledRun" : MDSS.localize("Scheduled Run"),
     "submit" : MDSS.localize("Submit"),
     "cancel" : MDSS.localize("Cancel"),
     "never" : MDSS.localize("Never"),
     "progress" : MDSS.localize("Progress"),
     
     "duration" : MDSS.localize("Duration"),
     "problems" : MDSS.localize("Problems"),
     "seconds" : MDSS.localize("seconds"),
      
     "start" : MDSS.localize("Start"),
     "stop" : MDSS.localize("Stop"),
     "pause" : MDSS.localize("Pause"),
     "resume" : MDSS.localize("Resume"),
     
     "stopped" : MDSS.localize("Stopped"),
     "status" : MDSS.localize("Status"),
   });
   
   com.runwaysdk.Localize.defineLanguage('com.runwaysdk.ui.CronPicker', {
     "disabled" : MDSS.localize("Disabled"),
     "enabled" : MDSS.localize("Enabled")
   });
   
   com.runwaysdk.Localize.defineLanguage('com.runwaysdk.ui.CronUtil', {
     "never" : MDSS.localize("Never"),
     "everyMinute" : MDSS.localize("everyMinute"),
     "everyHour" : MDSS.localize("everyHour"),
     "everyDay" : MDSS.localize("everyDay"),
     "everyWeek" : MDSS.localize("everyWeek"),
     "everyMonth" : MDSS.localize("everyMonth"),
     
     "minute" : MDSS.localize("minute"),
     "hour" : MDSS.localize("hour"),
     "day" : MDSS.localize("day"),
     "week" : MDSS.localize("week"),
     "month" : MDSS.localize("month"),
     
     "sunday" : MDSS.localize("sunday"),
     "monday" : MDSS.localize("monday"),
     "tuesday" : MDSS.localize("tuesday"),
     "wednesday" : MDSS.localize("wednesday"),
     "thursday" : MDSS.localize("thursday"),
     "friday" : MDSS.localize("friday"),
     "saturday" : MDSS.localize("saturday")
   });
   
   var parent = document.getElementById('schedulerDiv');
   var scheduler = new com.runwaysdk.ui.scheduler.Scheduler(config);
   scheduler.render(parent);
   
   if (oldFac != null)
   {
     com.runwaysdk.ui.Manager.setFactory(oldFac);
   }
 });
})();
 
</script>