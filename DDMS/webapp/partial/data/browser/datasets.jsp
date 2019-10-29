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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div id="app-container" class="container">

  <h2> <mdss:localize key="dataset.title"/> </h2>
  
  <div ng-if="errors.length > 0" class="error-container" ng-cloak>
    <div class="label-holder">
      <strong style="color: #8c0000;"><mdss:localize key='dashboard.errorsLabel'/></strong>
    </div>
    <div class="holder">
      <div ng-repeat="error in errors" >
        <p class="error-message">{{error}}</p>
      </div>
    </div>
  </div>
  
  <div ng-if="datasets === null"><mdss:localize key='dataset.loadingData'/></div>
  <div class="list-table-wrapper">
	  <table id="manage-datasets-table" class="list-table table table-bordered table-striped">        
	    <thead>
	      <tr>
	        <th></th>
	        <th class="label-column"><mdss:localize key='dataset.label'/></th>
	        <th class="label-column"><mdss:localize key='dataset.description'/></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr ng-repeat="dataset in datasets" class="fade-ngRepeat-item">
	        <td class="button-column">
	          <a class="fa fa-pencil ico-edit" ng-click="ctrl.edit(dataset)" title="<mdss:localize key="dataset.editTooltip"/>"></a>                             
	          <a class="fa fa-trash-o ico-remove" ng-click="ctrl.remove(dataset)" title="<mdss:localize key="dataset.removeTooltip"/>"></a>           
	        </td>
	        <td class="submit-form label-column">
	          <ng-form name="form{{$index}}">
  	          <input class="dataset-list-input" type="text" name="datasetListInput.{{$index}}" ng-model="dataset.label" value="{{ dataset.label }}" ng-attr-title="{{ datasetListInputTitle }}" ng-mouseover="ctrl.datasetElementHover($event)" ng-click="orignialDatasetState || ctrl.setDatasetState(dataset, this)" press-enter="ctrl.applyWithUniqueCheck(dataset,this)" ng-readonly="!dataset.editMode" validate-unique validator="ctrl.isUniqueLabel">
	              <i class="fa fa-pencil ico-edit" ng-show="!dataset.editMode"></i>
	            </input>
<%-- 	            <button type="button" class="btn btn-primary btn" role="button" aria-disabled="false" ng-show="dataset.editMode" ng-click="ctrl.apply(dataset)" ng-disabled="form{{$index}}.$invalid"><mdss:localize key="dataset.submit"/></button> --%>
<%-- 	            <button type="button" class="btn btn-default" role="button" aria-disabled="false" ng-show="dataset.editMode" ng-click="ctrl.cancelDatasetEdit(dataset)"><mdss:localize key="dataset.cancel"/></button> --%>
	          </ng-form>
	        </td>
	        <td class="description-column"> {{dataset.description}} </td>
	      </tr>
	    </tbody>    
	  </table>
   </div>
  
  <div class="drop-box-container" ngf-drag-over-class="'drop-active'" ngf-select="ctrl.uploadFile($files)" ngf-drop="ctrl.uploadFile($files)" ngf-multiple="false" ngf-drop-available="dropAvailable" fire-on-ready>
    <div class="drop-box">
      <div class="inner-drop-box">
        <i class="fa fa-cloud-upload">
          <p class="upload-text"><mdss:localize key="dashboardbuilder.uploadDataSet"/></p>
        </i>
      </div>
    </div>
  </div>
  
  <dataset-modal></dataset-modal>  
  
  <uploader-dialog></uploader-dialog>  
</div>
