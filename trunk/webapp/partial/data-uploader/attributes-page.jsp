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

<div>
<!--   <div class="label-holder"> -->
<!--     <strong> </strong> -->
<!--   </div> -->
  <div class="wide-holder">
    <div class="row-holder">
    	<p><mdss:localize key="dataUploader.attributeConfiguration.heading.paragraph"/></p>
    </div>
  </div>
  
<!--   <div class="label-holder"> -->
<%--     <strong><mdss:localize key="dataUploader.fields"/></strong> --%>
<!--   </div> -->
  <div class="wide-holder">
    <div class="row-holder" ng-repeat="field in sheet.fields">
      <div class="inline-text">
        <label><mdss:localize key="dataUploader.label"/></label>
        <input ng-model="field.label" name="{{::$index + '-name'}}" ng-class="{textInputDisabled : field.type == 'IGNORE'}" ng-required="true" type="text" validate-unique validator="ctrl.isUniqueLabel"></input>
      </div>
      <div class="inline-box" ng-if="field.columnType == 'TEXT'">
        <label><mdss:localize key="dataUploader.type"/></label>
        <select class="select-area" ng-model="field.type" ng-class="{selectInputDisabled : field.type == 'IGNORE'}" name="{{::$index + '-type'}}" ng-required="true" validate-accepted field="field" ng-change="ctrl.accept(field)">
          <option value="LOCATION"><mdss:localize key="dataUploader.location"/></option>
          <option value="CATEGORY"><mdss:localize key="dataUploader.category"/></option>
          <option value="TEXT"><mdss:localize key="dataUploader.text"/></option>
          <option value="IGNORE"><mdss:localize key="dataUploader.ignore"/></option>
<%--           <option value=""><mdss:localize key="dataUploader.undefined"/></option> --%>
        </select>      
      </div>      
      <div class="inline-box" ng-if="field.columnType == 'NUMBER'">
        <label><mdss:localize key="dataUploader.type"/></label>
        <select class="select-area" ng-model="field.type" name="{{::$index + '-type'}}" ng-required="true" validate-accepted field="field" ng-change="ctrl.accept(field)">
          <option value="LONG"><mdss:localize key="dataUploader.long"/></option>
          <option value="DOUBLE"><mdss:localize key="dataUploader.double"/></option>
          <option value="LOCATION"><mdss:localize key="dataUploader.location"/></option>
          <option value="LATITUDE"><mdss:localize key="dataUploader.latitude"/></option>
          <option value="LONGITUDE"><mdss:localize key="dataUploader.longitude"/></option>
          <option value="TEXT"><mdss:localize key="dataUploader.text"/></option>     
          <option value="IGNORE"><mdss:localize key="dataUploader.ignore"/></option>     
<%--           <option value=""><mdss:localize key="dataUploader.undefined"/></option> --%>
        </select>      
      </div>      
      <div class="inline-box" ng-if="field.columnType == 'DATE'">
        <label><mdss:localize key="dataUploader.type"/></label>
        <select class="select-area" ng-model="field.type" name="{{::$index + '-type'}}" ng-required="true" validate-accepted field="field" ng-change="ctrl.accept(field)">
          <option value="DATE"><mdss:localize key="dataUploader.date"/></option>
          <option value="IGNORE"><mdss:localize key="dataUploader.ignore"/></option>
<%--           <option value=""><mdss:localize key="dataUploader.undefined"/></option> --%>
        </select>      
      </div>      
      <div class="inline-box" ng-if="field.columnType == 'BOOLEAN'">
        <label><mdss:localize key="dataUploader.type"/></label>
        <select class="select-area" ng-model="field.type" name="{{::$index + '-type'}}" ng-required="true" validate-accepted field="field" ng-change="ctrl.accept(field)">
          <option value="BOOLEAN"><mdss:localize key="dataUploader.boolean"/></option>
          <option value="IGNORE"><mdss:localize key="dataUploader.ignore"/></option>
<%--           <option value=""><mdss:localize key="dataUploader.undefined"/></option> --%>
        </select>      
      </div>      
      <div class="inline-box" ng-if="field.columnType == ''">
        <label><mdss:localize key="dataUploader.type"/></label>
        <select class="select-area" ng-model="field.type" name="{{::$index + '-type'}}" ng-required="true" validate-accepted field="field" ng-change="ctrl.accept(field)">
          <option value="IGNORE"><mdss:localize key="dataUploader.ignore"/></option>
<%--           <option value=""><mdss:localize key="dataUploader.undefined"/></option> --%>
        </select>      
      </div>      
      <div class="inline-box fade-ngIf" ng-if="field.type == 'LOCATION'">
        <label><mdss:localize key="dataUploader.locationType"/></label>
        <select class="select-area" ng-model="field.universal" name="{{::$index + '-universal'}}" ng-required="true" ng-options="opt.value as opt.label for opt in universals">
          <option value=""></option>          
        </select>
      </div>      
      <div class="inline-box fade-ngIf" ng-if="field.type == 'CATEGORY'">
        <label><mdss:localize key="dataUploader.domainRoot"/></label>
        <select class="select-area" ng-model="field.root" name="{{::$index + '-root'}}" ng-options="opt.value as opt.label for opt in classifiers | orderBy:'label'">
          <option value=""><mdss:localize key="dataUploader.new"/></option>          
        </select>
      </div>      
      <div class="inline-text fade-ngIf" ng-if="field.type == 'CATEGORY' && field.root == null">
        <label><mdss:localize key="dataUploader.categoryLabel"/></label>
        <input ng-model="field.categoryLabel" name="{{::$index + '-categoryLabel'}}" ng-init="ctrl.initializeField(field)" ng-required="true" type="text" validate-unique validator="ctrl.isUniqueCategory"></input>
      </div>      

      <div class="inline-error-message pull-right">
        <p ng-show="form[$index + '-name'].$error.unique">
          <mdss:localize key="dataUploader.unique"/>
        </p>    
        <p ng-show="form[$index + '-categoryLabel'].$error.unique">
          <mdss:localize key="dataUploader.uniqueCategory"/>
        </p>    
        <p ng-show="form[$index + '-type'].$error.accepted" style="padding-top: 26px;">
          <i class="fa fa-exclamation" ng-click="ctrl.accept(field)" title="<mdss:localize key="dataUploader.acceptType"/>"></i>
        </p>
      </div>      
    </div>
  </div> 
<!--   <div class="label-holder"></div> -->
  <div class="wide-holder">
    <div class="error-message">
      <p ng-show="form.$error.coordinate"><mdss:localize key="dataUploader.coordinateMismatch"/></p>
      <p ng-show="form.$error.coordinateText"><mdss:localize key="dataUploader.coordinateNoLabel"/></p>
    </div>          
  </div>    
</div>
