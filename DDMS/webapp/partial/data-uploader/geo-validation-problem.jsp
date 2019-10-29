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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div class="row-holder">    
  <div>
<!--     <hr> -->
    <div class="error-message" ng-repeat="error in errors track by $index">
      <p >{{error}}</p>
    </div>
    <ng-form name="ctrl.problemForm" isolate-form>
      <div class="inline-value">
        <ol ng-if="problem.context.length > 0">
          <li ng-repeat="context in problem.context">{{context.label}} ({{context.universal}})</li>        
        </ol>
      </div>
      <div class="inline-value error-message">{{problem.label}} ({{problem.universalLabel}})</div>
      <div ng-if="!problem.resolved">      
        <div class="inline-combo">
          <input class="synonym" name="{{::$index + '-name'}}" type="text" placeholder="<mdss:localize key="dataUploader.synonymSearchPlaceholder"/>" autocomplete="on" ng-required="true" callback-auto-complete source="ctrl.getGeoEntitySuggestions" setter="ctrl.setSynonym"></input>
        </div>
        <div class="inline-actions">
            <i aria-hidden="true" data-icon="&#xe900;" class="icon-synonym_icon" ng-class="{disabled: ctrl.problemForm.$invalid}" ng-click="ctrl.problemForm.$invalid || ctrl.createSynonym()" title="<mdss:localize key="dataUploader.createSynonymFromLocationTooltip"/>" ></i>
        	<i aria-hidden="true" data-icon="&#xe901;" class="icon-new_location_icon" ng-click="ctrl.createEntity()" title="<mdss:localize key="dataUploader.createNewLocationTooltip"/>" ></i>
        	<span class="fa-stack fa-lg" title="<mdss:localize key="dataUploader.ignoreAtLocationTooltip"/>" ng-click="ctrl.ignoreDataAtLocation()">
        		<i class="fa fa-square fa-stack-2x"></i>
        		<i class="fa fa-times fa-stack-1x"></i>
        	</span>
        </div>
      </div>
      <div ng-if="problem.resolved">
        <div class="inline-combo" ng-if="problem.action.name == 'ENTITY'">
          <mdss:localize key="dataUploader.resolvedEntity"/>
        </div>      
        <div class="inline-combo" ng-if="problem.action.name == 'SYNONYM'">
          <synonym-action action="problem.action"></synonym-action>
        </div> 
        <div class="inline-combo" ng-if="problem.action.name == 'IGNOREATLOCATION'">
          <mdss:localize key="dataUploader.resolvedIgnoreAtLocation"/> [{{problem.action.label}}]
        </div> 
        <div class="inline-actions">    
        	<i class="fa fa-undo" ng-click="ctrl.undoAction()" title="<mdss:localize key="dataUploader.undoFixedLocationTooltip"/>" ></i> 
        </div>
      </div>
    </ng-form>
  </div>
</div>