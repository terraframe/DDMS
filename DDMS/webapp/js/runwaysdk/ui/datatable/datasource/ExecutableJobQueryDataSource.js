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
//define(["../../../ClassFramework", "../../../Util", "../../factory/generic/datatable/datasource/ServerDataSource"], function(ClassFramework, Util, ServerDataSource) {
(function(){

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var InstanceQueryDataSource = com.runwaysdk.ui.datatable.datasource.InstanceQueryDataSource;
  
  var RW = ClassFramework.alias(Mojo.RW_PACKAGE + "*");
  var UI = ClassFramework.alias(Mojo.UI_PACKAGE + "*");
  var STRUCT = ClassFramework.alias(Mojo.STRUCTURE_PACKAGE + "*");
  
  /**
   * @class com.runwaysdk.ui.datatable.InstanceQueryDataSource A data source for data tables that reads instance data from a query dto.
   * 
   * @constructs
   * @param obj
   *   @param com.runwaysdk.business.BusinessQueryDTO queryDTO The query dto to display data from.
   */
  var queryDataSource = ClassFramework.newClass('com.runwaysdk.ui.datatable.datasource.ExecutableJobQueryDataSource', {
    
    Extends : InstanceQueryDataSource,
    
    Instance : {
      initialize : function(config) {
        this.$initialize(config);
        
        Util.requireParameter("className [QueryDataSource]", config.className);
        Util.requireParameter("columns [QueryDataSource]", config.columns);
        Util.requireParameter("jobsFilter [ExecutableJobQueryDataSource]", config.jobsFilter);
        
        this._config = config;
        
        this._type = config.className;
        this._metadataQueryDTO = null;
        this._resultsQueryDTO = null;
        this._requestEvent = null;
        this._attributeNames = [];
      },
      
      _performQuery : function(callback) {
      
        var thisDS = this;
        
        var clientRequest = new Mojo.ClientRequest({
          onSuccess : function(queryDTO){
            thisDS._resultsQueryDTO = queryDTO;
            callback.onSuccess(queryDTO);
          },
          onFailure : function(e){
            callback.onFailure(e);
          }
        });
        
        dss.vector.solutions.report.SchedulerUtil.instanceQuery(clientRequest, this._config.jobsFilter, this.getSortAttr(), !this.isAscending(), this._pageSize, this._pageNumber);
      }
    }
  });
  
  return queryDataSource;
})();