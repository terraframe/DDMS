/*
 * Copyright (c) 2013 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */

//define(["../../ClassFramework", "../../Util", "../RunwaySDK_UI", "../factory/runway/widget/Widget", "../datatable/datasource/InstanceQueryDataSource", "./CronPicker", "prettycron"], function(ClassFramework, Util, UI, Widget, InstanceQueryDataSource, CronPicker) {
(function(){

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  var InstanceQueryDataSource = com.runwaysdk.ui.datatable.datasource.InstanceQueryDataSource;
  
  // In miliseconds
  var JOBS_POLLING_INTERVAL = 600;
  var HISTORY_POLLING_INTERVAL = 6000;

  var JOB_QUERY_TYPE = "com.runwaysdk.system.scheduler.ExecutableJob";
  
  var schedulerName = 'com.runwaysdk.ui.scheduler.Scheduler';
  var jobTableName = 'com.runwaysdk.ui.scheduler.JobTable';
  var jobHistoryTableName = "com.runwaysdk.ui.scheduler.JobHistoryTable";

  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(schedulerName, {
    "jobs" : "Jobs",
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
        
        this._jobTable = new JobTable(this._config, this);
        this._tabPanel.addPanel(this.localize("jobs"), this._jobTable);
        
        this._historyTable = new JobHistoryTable(this._config, this);
        this._tabPanel.addPanel(this.localize("history"), this._historyTable);
        
        this._tabPanel.addSwitchPanelEventListener(Mojo.Util.bind(this, this.onSwitchPanel));
      },
      
      onSwitchPanel : function(switchPanelEvent) {
        var panel = switchPanelEvent.getPanel();
        
        if (panel.getPanelNumber() === 0) { // Jobs
          this._jobTable.getPollingRequest().enable();
          this._historyTable.getPollingRequest().disable();
        }
        else if (panel.getPanelNumber() === 1) { // History
          this._jobTable.getPollingRequest().disable();
          this._historyTable.getPollingRequest().enable();
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
      
      initialize : function(config, scheduler) {
        
        this.$initialize("table");
        
        this._config = config;
        this._scheduler = scheduler;
      },
      
      _onClickStartJob : function(contextMenu, contextMenuItem, mouseEvent) {
        var row = contextMenu.getTarget();
        var table = row.getParentTable();
        var jobDTO = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];
        var that = this;
        
        jobDTO.start(new Mojo.ClientRequest({
          onSuccess : function(jobHistoryDTO) {
            that._scheduler._tabPanel.switchToPanel(1);
          },
          onFailure : function(ex) {
            that.handleException(ex);
          }
        }));
      },
      
      _openContextMenu : function(row, event) {
        
        var jobMetadata = this._table.getDataSource().getMetadataQueryDTO();
        var statusRowNum = 3;
        
        // Create Runway's Context Menu
        var cm = this.getFactory().newContextMenu(row);
        var start = cm.addItem(this.localize("start"), "add", Mojo.Util.bind(this, this._onClickStartJob));
        
        cm.render();
        
        row.addClassName("row_selected");
        cm.addDestroyEventListener(function() {
          row.removeClassName("row_selected");
        });
        
        return false; // Prevents default (displaying the browser context menu)
      },
      
      _openEditMenu : function(row, job) {
        var fac = this.getFactory();
        var table = row.getParentTable();
        var jobMetadata = table.getDataSource().getMetadataQueryDTO();
        var jobDTO = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];
        
        var dialog = fac.newDialog(this._config.language.get("editJobTitle"), {width: "500px"});
        
        row.addClassName("row_selected");
        dialog.addDestroyEventListener(function() {
          row.removeClassName("row_selected");
        });
        
        var form = this.getFactory().newForm();
        
        var descriptionInput = this.getFactory().newFormControl('textarea', 'description');
        descriptionInput.setValue(jobDTO.getDescription().getLocalizedValue());
        form.addEntry(jobMetadata.getAttributeDTO("description").getAttributeMdDTO().getDisplayLabel(), descriptionInput);
        
        var cronInput = new com.runwaysdk.ui.CronInput("cron");
        cronInput.setValue(jobDTO.getCronExpression());
        form.addEntry(this._config.language.get("scheduledRun"), cronInput);
        
        dialog.appendContent(form);
        
        var Structure = com.runwaysdk.structure;
        var tq = new Structure.TaskQueue();
        
        var that = this;
        
        tq.addTask(new Structure.TaskIF({
          start : Mojo.Util.bind(this, function(){
            dialog.addButton(this._config.language.get("submit"), function() { tq.next(); });
            
            var cancelCallback = function() {
              dialog.close();
              tq.stop();
            };
            dialog.addButton(this._config.language.get("cancel"), cancelCallback);
            
            dialog.render();
          })
        }));
        
        tq.addTask(new Structure.TaskIF({
          start : Mojo.Util.bind(this, function(){
            dialog.close();
            
            var lockCallback = new Mojo.ClientRequest({
              onSuccess : function(retJobDTO) {
                jobDTO = retJobDTO;
                tq.next();
              },
              onFailure : function(ex) {
                tq.stop();
                that.handleException(ex);
              }
            });
            
            com.runwaysdk.Facade.lock(lockCallback, jobDTO.getId());
          })
        }));
        
        tq.addTask(new Structure.TaskIF({
          start : Mojo.Util.bind(this, function(){
            var values = form.accept(fac.newFormControl('FormVisitor'));
            
            jobDTO.getDescription().localizedValue = values.get("description");
            jobDTO.setCronExpression(values.get("cron"));
            
            var applyCallback = new Mojo.ClientRequest({
              onSuccess : function() {
                // Intentionally empty
              },
              onFailure : function(ex) {
                tq.stop();
                that.handleException(ex);
              }
            });
            
            jobDTO.apply(applyCallback);
          })
        }));
        
        tq.start();
        
        return false;
      },

      _lockRow : function (row, event) {
        var table = row.getParentTable();
        var job = table.getDataSource().getResultsQueryDTO().getResultSet()[row.getRowNumber()];        
      
        var lockCallback = new Mojo.ClientRequest({
          that : this,
          onSuccess : function(ret) {
            this.that._openEditMenu(row, ret);
          },
          onFailure : function(ex) {
            this.that.handleException(ex);
          }
        }, document.getElementById('scheduler'));
        
        job.lock(lockCallback);
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
        
        var ds = new InstanceQueryDataSource({
          className: JOB_QUERY_TYPE,
          columns: [
            { queryAttr: "jobId" },
            { queryAttr: "description",  customFormatter: function(jobDTO){ return jobDTO.getDescription().getLocalizedValue(); } },
            { header: this.localize("scheduledRun"), customFormatter: function(job) {
                return com.runwaysdk.ui.CronUtil.cronToHumanReadable(job.getCronExpression());
              }
            }
          ]
        });
        
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
        
        this._pollingRequest.enable();
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
        
        com.runwaysdk.system.scheduler.JobHistory.clearHistory(new Mojo.ClientRequest({
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
        
        // Allow them to specify a custom date formatter in our config, this is used for DDMS.
        var dateFormatter = Mojo.Util.bind(that, that.startTimeFormatter);
        if (Mojo.Util.isFunction(this._config.startTimeFormatter)) { dateFormatter = this._config.startTimeFormatter; }
        
        var ds = new com.runwaysdk.ui.datatable.datasource.MdMethodDataSource({
          method : function(clientRequest) {
            com.runwaysdk.system.scheduler.JobHistoryView.getJobHistories(clientRequest, this.getSortAttr(), this.isAscending(), this.getPageSize(), this.getPageNumber());
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
