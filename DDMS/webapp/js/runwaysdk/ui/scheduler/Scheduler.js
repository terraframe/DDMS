/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
(function(){

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  var InstanceQueryDataSource = com.runwaysdk.ui.datatable.datasource.InstanceQueryDataSource;
  var ExecutableJobQueryDataSource = com.runwaysdk.ui.datatable.datasource.ExecutableJobQueryDataSource;
  
  // In miliseconds
  var JOBS_POLLING_INTERVAL = 2000;
  var HISTORY_POLLING_INTERVAL = 4000;

  var schedulerName = 'com.runwaysdk.ui.scheduler.Scheduler';
  var jobTableName = 'com.runwaysdk.ui.scheduler.JobTable';
  var jobHistoryTableName = "com.runwaysdk.ui.scheduler.JobHistoryTable";

  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(schedulerName, {
    "all_jobs" : "All Jobs",
    "cycle_jobs" : "Cycle Jobs",
    "refresh_view_jobs" : "Refresh View Jobs",
    "report_jobs" : "Report Jobs",
    "odk_data_pull" : "Mobile Data Upload Jobs",
    "history" : "History",
  });
  
  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(jobTableName, {
    "editJobTitle" : "Edit Job",
    "scheduledRun" : "Scheduled Run",
    "submit" : "Submit",
    "cancel" : "Cancel",
    "never" : "Never",
    "progress" : "Progress",
    
    "duration" : "Duration",
    "problems" : "Problems",
    "seconds" : "seconds",    
    
//  "clearHistory" : "Clear History",
    
    // The metadata for MdMethods is not included in the Javascript query results, which is why we have to hardcode these values here (for now at least).
    "start" : "Start",
    "stop" : "Stop",
    "pause" : "Pause",
    "resume" : "Resume",
    
    // When we refactor the Job metadata from flags into a status enum these will be removed.
    "stopped" : "Stopped",
    "status" : "Status",
    "sSortAscending" : ": activate to sort column ascending",
    "sSortDescending" : ": activate to sort column descending",
    "sFirst" : "First",
    "sLast" : "Last",
    "sNext" : "Next",
    "sPrevious" : "Previous",
    "sEmptyTable" : "No data available in table",
    "sInfo" : "Showing _START_ to _END_ of _TOTAL_ entries",
    "sInfoEmpty" : "Showing 0 to 0 of 0 entries",
    "sInfoFiltered" : "(filtered from _MAX_ total entries)",
    "sLengthMenu" : "Show _MENU_ entries",
    "sLoadingRecords" : "Loading...",
    "sProcessing" : "Processing...",
    "sSearch" : "Search:",
    "sZeroRecords" : "No matching records found"    
  });
  
  com.runwaysdk.Localize.defineLanguage(jobHistoryTableName, {
    "duration" : "Duration",
    "problems" : "Problems",
    "seconds" : "seconds",
    "sSortAscending" : ": activate to sort column ascending",
    "sSortDescending" : ": activate to sort column descending",
    "sFirst" : "First",
    "sLast" : "Last",
    "sNext" : "Next",
    "sPrevious" : "Previous",
    "sEmptyTable" : "No data available in table",
    "sInfo" : "Showing _START_ to _END_ of _TOTAL_ entries",
    "sInfoEmpty" : "Showing 0 to 0 of 0 entries",
    "sInfoFiltered" : "(filtered from _MAX_ total entries)",
    "sLengthMenu" : "Show _MENU_ entries",
    "sLoadingRecords" : "Loading...",
    "sProcessing" : "Processing...",
    "sSearch" : "Search:",
    "sZeroRecords" : "No matching records found"    
  });
  
  var scheduler = ClassFramework.newClass(schedulerName, {
    
    Extends : Widget,
    
    Instance : {
      
      initialize : function(config) {
        this._tabPanel = this.getFactory().newTabPanel();
        
        this.$initialize(this._tabPanel);
        
        this._config = config || {};
        this._config.language = this._config.language || {};
        Util.merge(com.runwaysdk.Localize.getLanguage(schedulerName), this._config.language);
        
        this._allJobsTable = new JobTable(Mojo.Util.deepMerge({isAllJobs: true}, this._config), "com.runwaysdk.system.scheduler.ExecutableJob", this);
        this._tabPanel.addPanel(this.localize("all_jobs"), this._allJobsTable);
        
        this._cycleJobTable = new JobTable(this._config, "dss.vector.solutions.query.CycleJob", this);
        this._tabPanel.addPanel(this.localize("cycle_jobs"), this._cycleJobTable);
        
        this._dashboardJobTable = new JobTable(this._config, "dss.vector.solutions.kaleidoscope.dashboard.DashboardJob", this);
        this._tabPanel.addPanel(this.localize("dashboard_jobs"), this._dashboardJobTable);
        
        this._refreshViewTable = new JobTable(this._config, "dss.vector.solutions.query.RefreshViewJob", this);
        this._tabPanel.addPanel(this.localize("refresh_view_jobs"), this._refreshViewTable);
        
        this._reportTable = new JobTable(this._config, "dss.vector.solutions.report.ReportJob", this);
        this._tabPanel.addPanel(this.localize("report_jobs"), this._reportTable);
        
        this._odkDataTable = new JobTable(this._config, "dss.vector.solutions.odk.MobileDataUploadJob", this);
        this._tabPanel.addPanel(this.localize("odk_data_pull"), this._odkDataTable);
        
        this._historyTable = new JobHistoryTable(this._config, this);
        this._tabPanel.addPanel(this.localize("history"), this._historyTable);
        
        this._tabPanel.addSwitchPanelEventListener(Mojo.Util.bind(this, this.onSwitchPanel));
      },
      
      setPage : function(pageName) {
        if (pageName != null && pageName == "history")
        {
          this._tabPanel.switchToPanel(6);
        }
      },
      
      onSwitchPanel : function(switchPanelEvent) {
        var panel = switchPanelEvent.getPanel();
        
        this.configureListeners(panel.getPanelNumber());
      },
      
      configureListeners : function(panelNumber)
      {
        if (panelNumber === 0) { // All Jobs
          this._allJobsTable.getPollingRequest().enable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
        else if (panelNumber === 1) { // Cycle jobs
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().enable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
        else if (panelNumber === 2) { // Dashboard jobs
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().enable();
        }
        else if (panelNumber === 3) { // Refresh View jobs
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().enable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
        else if (panelNumber === 4) { // Report jobs
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().enable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
        else if (panelNumber === 5) { // Mobile data upload job
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().disable();
          this._odkDataTable.getPollingRequest().enable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
        else if (panelNumber === 6) { // History
          this._allJobsTable.getPollingRequest().disable();
          this._cycleJobTable.getPollingRequest().disable();
          this._refreshViewTable.getPollingRequest().disable();
          this._reportTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().enable();
          this._odkDataTable.getPollingRequest().disable();
          this._dashboardJobTable.getPollingRequest().disable();
        }
      },
      
      render : function(parent) {
        this._tabPanel.render(parent);
      }
    }
  });
  
  var JobTable = ClassFramework.newClass(jobTableName, {
    
    Extends : Widget,
    
    Instance : {
      
      initialize : function(config, typename, scheduler) {
        
        this.$initialize("table");
        
        this._config = config;
        this._scheduler = scheduler;
        this._typename = typename;
      },
      
      _onClickStartJob : function(contextMenu, contextMenuItem, mouseEvent) {
        var row = contextMenu.getTarget();
        var table = row.getParentTable();
        var jobDTO = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];
        var that = this;
        
        jobDTO.start(new Mojo.ClientRequest({
          onSuccess : function(jobHistoryDTO) {
            that._scheduler._tabPanel.switchToPanel(6);
          },
          onFailure : function(ex) {
            that.handleException(ex);
          }
        }));
      },
      
      _openContextMenu : function(row, event) {
        var that = this;
        var jobMetadata = this._table.getDataSource().getMetadataQueryDTO();
        var statusRowNum = 3;
        
        // Create Runway's Context Menu
        var cm = this.getFactory().newContextMenu(row);
        var start = cm.addItem(this.localize("start"), "add", Mojo.Util.bind(this, this._onClickStartJob));
        
        cm.render();
        
        this._selectedRow = row;
        row.setSelected(true);
        cm.addDestroyEventListener(function() {
          row.setSelected(false);
          that._selectedRow = null;
        });
        
        return false; // Prevents default (displaying the browser context menu)
      },
      
      _openEditMenu : function(row, jobViewDTO) {
        var that = this;
        var fac = this.getFactory();
        var table = row.getParentTable();
        var jobMetadata = table.getDataSource().getMetadataQueryDTO();
        var jobDTO = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];
        
        var dialog = fac.newDialog(this._config.language.get("editJobTitle"), {width: "500px"});
        
        row.setSelected(true);
        this._selectedRow = row;
        dialog.addDestroyEventListener(function() {
          row.setSelected(false);
          that._selectedRow = null;
        });
        
        var form = this.getFactory().newForm();
        
        var descriptionInput = this.getFactory().newFormControl('textarea', 'description');
        descriptionInput.setValue(jobDTO.getDescription().getLocalizedValue());
        form.addEntry(jobMetadata.getAttributeDTO("description").getAttributeMdDTO().getDisplayLabel(), descriptionInput);
        
        var cronInput = new com.runwaysdk.ui.CronInput("cron", {localizedLabel: this._config.language.get("scheduledRun")});
        cronInput.setValue(jobDTO.getCronExpression());
        form.addEntry("", cronInput);
        
        var dsjInput = new com.runwaysdk.ui.DownstreamJobInput("downstreamJob", jobViewDTO);
        form.addEntry("", dsjInput);
        
        dialog.appendContent(form);
        
        var submitHandler = function(){
          var values = form.accept(fac.newFormControl('FormVisitor'));
          
          jobDTO.getDescription().localizedValue = values.get("description");
          jobDTO.setCronExpression(values.get("cron"));
          jobViewDTO.setDownstreamJob(values.get("downstreamJob"));
          jobViewDTO.setTriggerOnFailure(values.get("triggerOnFailure"));
          
          var applyCallback = new Mojo.ClientRequest({
            onSuccess : function() {
              dialog.close();
            },
            onFailure : function(ex) {
              dialog.close();
              that.handleException(ex);
            }
          });
          
          jobViewDTO.applyWithJob(applyCallback, jobDTO);
        };
        dialog.addButton(this._config.language.get("submit"), function() { submitHandler(); });
        
        var cancelCallback = function() {
          var unlockCallback = new Mojo.ClientRequest({
            onSuccess : function(retJobDTO) {
              dialog.close();
            },
            onFailure : function(ex) {
              that.handleException(ex);
            }
          });
          
          com.runwaysdk.Facade.unlock(unlockCallback, jobDTO.getId());
        };
        dialog.addButton(this._config.language.get("cancel"), cancelCallback);
        
        dialog.render();
        
        return false;
      },

      _lockRow : function (row, event) {
        var table = row.getParentTable();
        var job = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];        
      
        var lockCallback = new Mojo.ClientRequest({
          that : this,
          onSuccess : function(jobViewDTO) {
            this.that._openEditMenu(row, jobViewDTO);
          },
          onFailure : function(ex) {
            this.that.handleException(ex);
          }
        }, document.getElementById('scheduler'));
        
        com.runwaysdk.system.scheduler.JobView.lockJob(lockCallback, job.getId());
      },      
      
      _onNewRowEvent : function(newRowEvent) {
        var row = newRowEvent.getRow();
        
        var onContextMenu =  Mojo.Util.bind(this, function(event) {
          this._openContextMenu(row, event);
          
          return false;
        });

        var onClick =  Mojo.Util.bind(this, function(event) {
          this._lockRow(row, event);

          return false;
        });
                
        row.addEventListener("click", onClick);
        row.addEventListener("contextmenu", onContextMenu);
        
        if (this._selectedRow != null && this._selectedRow.getRowNumber() === row.getRowNumber())
        {
          row.setSelected(true);
          this._selectedRow = row;
        }
      },
      
      
      formatProgress : function(jobDTO) {
        if (jobDTO.getWorkTotal() == null || jobDTO.getWorkProgress() == null || jobDTO.getWorkTotal() == 0) {
          return null;
        }
        
        return ((jobDTO.getWorkProgress() / jobDTO.getWorkTotal()) * 100) + "%";
      },
      
      formatScheduledRun : function(jobDTO) {
        var cronStr = jobDTO.getCronExpression();
        
        if (cronStr == null || cronStr === "") {
          return this.localize("never");
        }
        else {
          return prettyCron.toString(cronStr);
        }
      },
      
      render : function(parent) {
        
        var dsConfig = {
          className: this._typename,
          columns: [
            { queryAttr: "jobId" },
            { queryAttr: "description",  customFormatter: function(jobDTO){ return jobDTO.getDescription().getLocalizedValue(); } },
            { header: this.localize("scheduledRun"), customFormatter: function(job) {
                return com.runwaysdk.ui.CronUtil.cronToHumanReadable(job.getCronExpression());
              }
            }
          ]
        };
        
        var ds = null;
        if (this._config.isAllJobs != null)
        {
          var jobsFilter = ["dss.vector.solutions.basemap.OfflineBasemapJob", "dss.vector.solutions.ExcelImportJob", "dss.vector.solutions.FormSurveyImportJob", "dss.vector.solutions.DataUploaderImportJob"];
          dsConfig = Mojo.Util.deepMerge({jobsFilter: jobsFilter}, dsConfig)
          
          ds = new ExecutableJobQueryDataSource(dsConfig);
        }
        else
        {
          ds = new InstanceQueryDataSource(dsConfig);
        }
        
        ds.setAscending(true);
        
        // Create the DataTable impl
        this._config.el = this;
        this._config.dataSource = ds;
        this._config.sDom = '<"top"i>rt<"bottom"lp><"clear">';
        
        // Localize the datatable widget
        this._config.oLanguage = {
          oAria: {
            sSortAscending: this.localize("sSortAscending"),
            sSortDescending: this.localize("sSortDescending")
          },
          oPaginate: {
            sFirst: this.localize("sFirst"),
            sLast: this.localize("sLast"),
            sNext: this.localize("sNext"),
            sPrevious: this.localize("sPrevious")
          },
          sEmptyTable: this.localize("sEmptyTable"),
          sInfo: this.localize("sInfo"),
          sInfoEmpty: this.localize("sInfoEmpty"),
          sInfoFiltered: this.localize("sInfoFiltered"),
          sLengthMenu: this.localize("sLengthMenu"),
          sLoadingRecords: this.localize("sLoadingRecords"),
          sProcessing: this.localize("sProcessing"),
          sSearch: this.localize("sSearch"),
          sZeroRecords: this.localize("sZeroRecords")
        };        
        
        
        this._table = this.getFactory().newDataTable(this._config);
        
        this._table.addNewRowEventListener(Mojo.Util.bind(this, this._onNewRowEvent));
        
        this._table.render(parent);
        
        var that = this;
        this._pollingRequest = new com.runwaysdk.ui.PollingRequest({
          callback: {
            onSuccess: function(data) {
              
            },
            onFailure: function(ex) {
              that.handleException(ex);
            }
          },
          performRequest : function(callback) {
            that._table.refresh(callback);
          },
          pollingInterval : JOBS_POLLING_INTERVAL
        });
        
        if (this._config.isAllJobs != null)
        {
          this._pollingRequest.enable();
        }
        else
        {
          this._pollingRequest.disable();
        }
      },
      
      getPollingRequest : function() {
        return this._pollingRequest;
      },
      
      destroy : function() {
        this.$destroy();
        this._pollingRequest.destroy();
      }
    }    
  });

  var JobHistoryTable = ClassFramework.newClass(jobHistoryTableName, {
    
    Extends : Widget,
    
    Instance : {
      
      initialize : function(config, scheduler) {
        this.$initialize("table");
        
        this._config = config;
        this._scheduler = scheduler;        
      },
      
      getPollingRequest : function() {
        return this._pollingRequest;
      },
      
      _onClickClearHistory : function()
      {
        var that = this;
        
        that._hasClearHistoryRequestReturned = false;
        that._clearHistoryBusy.addClassName("scheduler_small_busy_spinner");
        
        dss.vector.solutions.report.SchedulerUtil.clearHistory(new Mojo.ClientRequest({
          onSuccess : function() {
            that._hasClearHistoryRequestReturned = true;
          },
          onFailure : function(ex) {
            that.handleException(ex);
            that._clearHistoryBusy.removeClassName("scheduler_small_busy_spinner");
          }
        }));
      },
      
      createClearHistoryButton : function()
      {
        var container = this.getFactory().newElement("div");
        
        var but = this.getFactory().newButton(this._config.language.get("clearHistory"), Mojo.Util.bind(this, this._onClickClearHistory));
        but.setStyle("margin-bottom", "20px");
        container.appendChild(but);
        
        this._clearHistoryBusy = this.getFactory().newElement("div");
        container.appendChild(this._clearHistoryBusy);
        
        this.appendChild(container);
      },
      
      formatDuration : function(view)
      {
        var end = view.getEndTime() == null ? new Date() : view.getEndTime();
        
        var duration = ((end - view.getStartTime()) / 1000);
        
        return duration + " " + this.localize("seconds") + ".";
      },
      
      startTimeFormatter : function(view) {
        return  MDSS.Calendar.getLocalizedDateTime(view.getStartTime());
      },
      
      render : function(parent) {
        var that = this;
        
        this.createClearHistoryButton();
        
        var dateFormatter = Mojo.Util.bind(that, that.startTimeFormatter);
        if (Mojo.Util.isFunction(this._config.startTimeFormatter)) { dateFormatter = this._config.startTimeFormatter; }
        
        var ds = new com.runwaysdk.ui.datatable.datasource.MdMethodDataSource({
          method : function(clientRequest) {
            dss.vector.solutions.report.SchedulerUtil.getJobHistories(clientRequest, this.getSortAttr(), this.isAscending(), this.getPageSize(), this.getPageNumber());
          },
          columns : [
                     { queryAttr: "startTime", customFormatter: dateFormatter },
                     {queryAttr: "status", customFormatter: function(view){ return view.getStatusLabel(); }},
                     {queryAttr: "jobId"},
                     {header: this.localize("duration"), customFormatter: Mojo.Util.bind(that, that.formatDuration)},
                     {queryAttr: "description"},
                     {header: this.localize("problems"), customFormatter : function(view) {
                       // This is a workaround to a bug in runway, the value isn't getting set to the localized value.
                       return view.getAttributeDTO('historyInformation').getValue();
                       // return view.getHistoryInformation().getLocalizedValue();
                     }}
                    ]
        });
        
        // Create the element that will contain the DataTable
        var tableEl = this.getFactory().newElement("table");
        this.appendChild(tableEl);
        this._config.el = tableEl;
        
        // Create the DataTable impl
        this._config["iDisplayLength"] = 20;
        if (this._config["oLanguage"] == null) {
          this._config["oLanguage"] = {};
        }
        this._config.dataSource = ds;
        this._config.sDom = '<"top"i>rt<"bottom"lp><"clear">'; // This statement hides a (datatables.net) search bar that isn't hooked up yet.
        this._config.bLengthChange = false;
        // Localize the datatable widget
        
        this._table = this.getFactory().newDataTable(this._config);
        this._table.render(parent);
        
        this._pollingRequest = new com.runwaysdk.ui.PollingRequest({
          callback: {
            onSuccess: function(data) {
              if (that._hasClearHistoryRequestReturned)
              {
                that._clearHistoryBusy.removeClassName("scheduler_small_busy_spinner");
              }
            },
            onFailure: function(ex) {
              // that.handleException(ex);
            }
          },
          performRequest : function(callback) {
            that._table.refresh(callback);
          },
          pollingInterval : HISTORY_POLLING_INTERVAL
        });
      }
    }
  });
      
  return scheduler;
  
})();
