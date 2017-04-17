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


<div>
  <div ng-if="ctrl.length() > 1">
    <div class="label-holder">
    </div>
    <div class="holder">  
      <div class="row-holder">
        <div class="error-message">
          <mdss:localize key="dataUploader.sheetWarning"/>
        </div>      
      </div>    
    </div>    
  </div>
  <div class="label-holder">
    <strong><mdss:localize key="dataUploader.uploadBeginningMessageRequirements"/></strong>
  </div>
  <div class="holder">
    <div class="row-holder">
    	<p><mdss:localize key="dataUploader.uploadBeginningRequirementsMessage"/></p>
    	<ol>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement1"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement2"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement3"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement4"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement5"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement6"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement7"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement8"/></li>
    		<li><mdss:localize key="dataUploader.uploadBeginningRequirement9"/></li>
		</ol>
    </div>  
  </div>
  
  <div class="label-holder">
    <strong><mdss:localize key="dataUploader.uploadBeginningExampleLabel"/></strong>
  </div>
  <div class="holder">
    <div class="row-holder">
    	<table class="table table-bordered">
		    <thead>
		      <tr>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader1"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader2"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader3"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader4"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader5"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader6"/></th>
		        <th><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleHeader7"/></th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		      	<td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_1"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_2"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_3"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_4"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_5"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_6"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent1_7"/></td>
		      </tr>
		      <tr>
		      	<td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_1"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_2"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_3"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_4"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_5"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_6"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent2_7"/></td>
		      </tr>
		      <tr>
		      	<td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_1"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_2"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_3"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_4"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_5"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_6"/></td>
		        <td><mdss:localize key="dataUploader.uploadBeginningSpreadsheetExampleContent3_7"/></td>
		      </tr>
		    </tbody>
		  </table>
    </div>  
  </div> 
</div>