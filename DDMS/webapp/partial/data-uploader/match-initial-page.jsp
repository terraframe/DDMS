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
  <div class="label-holder">
    <strong> </strong>
  </div>
  <div class="holder">
    <div class="row-holder">
    	<p><mdss:localize key="dataUploader.titleUploadToExistingOrNewSubtitle"/></p>
    </div>
  </div>
  
  <div class="label-holder">
    <strong><mdss:localize key="dataUploader.createNewLocationOrUpdateExistingLabel"/></strong>
  </div>
  <div class="holder">
  		<div class="large-icon-stack-wrapper">
    		<i class="fa-stack fa-3x" ng-click="ctrl.next('MATCH', 'MATCH-INITIAL')" title="<mdss:localize key="dataUploader.updateExistingDatasetButtonTooltip"/>">
    			<i class="fa fa-table fa-stack-2x"></i>
      			<i class="fa-stack-3x fa-stack-text file-text fa fa-pencil-square"></i>
  			</i>
  			<h4><mdss:localize key="dataUploader.updateExistingDatasetButtonLabel"/></h4>
  		</div>
  		<div class="large-icon-stack-wrapper">
  			<i class="fa-stack fa-3x" ng-click="ctrl.next('BEGINNING-INFO', 'MATCH-INITIAL')" title="<mdss:localize key="dataUploader.createNewDatasetButtonTooltip"/>">
    			<i class="fa fa-table fa-stack-2x"></i>
      			<i class="fa-stack-3x fa-stack-text file-text fa fa-plus-square"></i>
  			</i>
  			<h4><mdss:localize key="dataUploader.createNewDatasetButtonLabel"/></h4>
  		</div>
  </div> 
</div>