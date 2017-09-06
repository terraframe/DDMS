<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div class="row-holder">    
  <div>
<!--     <hr> -->
    <div class="error-message" ng-repeat="error in errors track by $index">
      <p >{{error}}</p>
    </div>
    <ng-form name="ctrl.problemForm" isolate-form>
      <div class="inline-value">{{problem.attributeLabel}}</div>    
      <div class="inline-value error-message">{{problem.label}}</div>
      <div ng-if="!problem.resolved">      
        <div class="inline-combo">
          <select class="select-area" ng-model="problem.synonym" ng-change="ctrl.setSynonym()" ng-options="opt.id as opt.label for opt in options">
            <option value=""></option>          
          </select>          
        </div>
        <div class="inline-actions">
          <i aria-hidden="true" data-icon="&#xe900;" class="icon-synonym_icon" ng-class="{disabled: ctrl.problemForm.$invalid}" ng-click="ctrl.problemForm.$invalid || ctrl.createSynonym()" title="<mdss:localize key="dataUploader.createSynonymCategoryTooltip"/>" ></i>
          <i aria-hidden="true" data-icon="&#xe901;" class="icon-new_location_icon" ng-click="ctrl.createOption()" title="<mdss:localize key="dataUploader.createNewOptionTooltip"/>" ></i>          
          <span class="fa-stack fa-lg" title="<mdss:localize key="dataUploader.ignoreCategoryTooltip"/>" ng-click="ctrl.ignoreValue()">
            <i class="fa fa-square fa-stack-2x"></i>
            <i class="fa fa-times fa-stack-1x"></i>
          </span>
        </div>
      </div>
      <div ng-if="problem.resolved">
        <div class="inline-combo" ng-if="problem.action.name == 'OPTION'">
          <mdss:localize key="dataUploader.resolvedCategoryOption"/>
        </div>            
        <div class="inline-combo" ng-if="problem.action.name == 'SYNONYM'">
          <mdss:localize key="dataUploader.resolvedSynonym"/> [{{problem.action.label}}]
        </div> 
        <div class="inline-combo" ng-if="problem.action.name == 'IGNORE'">
          <mdss:localize key="dataUploader.resolvedIgnoreCategory"/> [{{problem.label}}]
        </div> 
        <div class="inline-actions">    
          <i class="fa fa-undo" ng-click="ctrl.undoAction()" title="<mdss:localize key="dataUploader.undoFixedCategoryTooltip"/>" ></i> 
        </div>
      </div>
    </ng-form>
  </div>
</div>