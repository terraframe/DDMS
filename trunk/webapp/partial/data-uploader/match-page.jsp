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
    <strong><mdss:localize key="dataUploader.existingDataset"/></strong>
  </div>
  <div class="holder">
  	<ul id="match-datasets-list" class="list-group">   
        <li class="list-group-item" ng-repeat="match in sheet.matches track by $index">
        	{{match.label}}            
  		
  		    <div class="medium-icon-wrapper">
  				<i class="fa fa-plus-square" ng-click="ctrl.select(match, false)" title="<mdss:localize key="dataUploader.selectDataset"/>"></i>
  			</div>
  			<div class="medium-icon-wrapper">
	    		<i class="fa fa-minus-square" ng-click="ctrl.select(match, true)" title="<mdss:localize key="dataUploader.replaceDataset"/>"></i>
  			</div>
  		</li>
    </ul>
  </div> 
</div>