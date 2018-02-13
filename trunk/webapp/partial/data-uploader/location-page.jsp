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
  <div class="label-holder"></div>
  <div class="holder">
    <div class="row-holder">
      <p><mdss:localize key="dataUploader.textLocationConfiguration.heading.paragraph"/></p>
    </div>
  </div>

  <div ng-if="unassignedFields.length > 0">
    <div class="label-holder">
      <strong><mdss:localize key="dataUploader.unassignedLocationFields"/></strong>
    </div>
    <div class="holder">
      <div class="row-holder">
        <div ng-repeat="field in unassignedFields" class="location-selector-container">
          <h3 class="location-field-info-card-title">{{field.label}}</h3>
        </div>
      </div> <!-- end row-holder -->
    </div> <!-- end holder -->
  </div> 
  	
  <ng-form name="ctrl.attributeForm" isolate-form ng-if="attribute != null">
    <div class="label-holder">
      <strong><mdss:localize key="dataUploader.locationCreatorWidgetLabel"/></strong>
    </div>    
    <div class="holder">
      <div class="location-selector-container">
	      <div class="row-holder">
	      	<h4 class="location-select-container-heading-text"><mdss:localize key="dataUploader.locationContainerHeadingToolTip"/> {{attribute.label}}</h4>
<%-- 	      	<i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.locationContainerHeadingHelpInfoToolTip"/>"></i>       --%>
	      </div>
          <span class="text">
	        <input ng-model="attribute.label" name="label" ng-required="true" type="text" validate-unique validator="ctrl.isUniqueLabel"></input>
	      </span>
	      <div class="error-message">
	        <p ng-show="ctrl.attributeForm.label.$error.unique">
	          <mdss:localize key="dataUploader.unique"/>
	        </p>    
	      </div>
	      <div class="row-holder">
	        <hr>
	      </div>	      
	      <div class="row-holder" ng-repeat="universal in universalOptions track by $index">
	      	<div class="label-help-ico-container">
	      		<i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.fieldHelp01ToolTip"/> {{universal.label}} <mdss:localize key="dataUploader.fieldHelp02ToolTip"/> {{attribute.label}} <mdss:localize key="dataUploader.fieldHelp03ToolTip"/>"></i>      
	      		<p class="select-label">{{universal.label}} <mdss:localize key="dataUploader.selectLabelToolTip"/></p>
	      	</div>
	      	<div class="location-selector-box-right">
		        <div class="box">
		          <select class="select-area" ng-model="attribute.fields[universal.value]" ng-change="ctrl.change(attribute.fields)" name="{{::$index + '-universal'}}" ng-required="true">
		            <option value=""></option>          
		            <option ng-repeat="field in locationFields[universal.value]" value="{{field.label}}">{{field.label}}</option>   
		            <option value="EXCLUDE"><mdss:localize key="dataUploader.exclude"/></option>
		          </select>
		        </div>
	      	</div>
	      </div>
      	  <div class="row-holder">
        	<div class="button-holder">
          		<input type="button" value="+" class="btn btn-primary set-location-btn pull-right"  ng-click="ctrl.newAttribute()" ng-disabled="ctrl.attributeForm.$invalid" title="<mdss:localize key="dataUploader.createBtnToolTip"/>" />
        	</div>
      	  </div>  
      </div>  
    </div>
  </ng-form>  
  
  <div ng-if="sheet.attributes.ids.length > 0">
    <div class="label-holder">
      <strong><mdss:localize key="dataUploader.attributes"/></strong>
    </div>
    <div class="holder">
      <div class="row-holder">
        
        <div ng-repeat="(id, attribute) in sheet.attributes.values" ng-show="!attribute.editing" class="location-selector-container scale-fade">
            <h3 class="location-field-info-card-title">{{attribute.label}}</h3>
            <div class="cell" style="float: right;">            
            	<i class="fa fa-pencil ico-edit" ng-click="ctrl.edit(attribute)" title="<mdss:localize key="dataUploader.editToolTip"/>"></i>
            	<i class="fa fa-trash-o ico-remove" ng-click="ctrl.remove(attribute)" title="<mdss:localize key="dataUploader.deleteToolTip"/>"></i>
          	</div>
          	<div class="row-holder"></div>
            <ul class="location-field-list-display">
              <li class="" ng-repeat="universal in universals track by $index" ng-if="attribute.fields[universal.value] != null && attribute.fields[universal.value] != 'EXCLUDE'">
                <i class="fa fa-check-square"></i>{{attribute.fields[universal.value]}}            
              </li>
            </ul>   
        </div>
        
      </div> <!-- end row-holder -->
    </div> <!-- end holder -->
  </div> <!-- end ng-if -->
  
  <div class="label-holder"></div>
  <div class="holder">
    <div class="error-message">
      <p ng-show="form.$error.size"><mdss:localize key="dataUploader.unassignedLocationFields"/></p>
    </div>          
  </div>  
</div>
