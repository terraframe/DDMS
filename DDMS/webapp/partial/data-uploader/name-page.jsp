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
    <strong><mdss:localize key="dataUploader.label"/></strong>
  </div>
  <div class="holder">
    <span class="text">
      <input ng-model="sheet.label" name="label" ng-required="true" type="text" validate-unique validator="ctrl.isUniqueLabel"></input>
    </span>
    <div class="inline-error-message">
      <p ng-show="form.label.$error.unique">
        <mdss:localize key="dataUploader.unique"/>
      </p>    
    </div>      
  </div> 
  <div class="label-holder">
    <label><mdss:localize key="dataset.source"/></label>
  </div>          
  <div class="holder" >
    <span class="text">
      <textarea ng-model="sheet.source" name="source"></textarea>
    </span>
  </div>
  <div class="label-holder">
    <label><mdss:localize key="dataset.description"/></label>
  </div>          
  <div class="holder" >
    <span class="text">
      <textarea ng-model="sheet.description" name="description"></textarea>
    </span>
  </div>
  <div class="label-holder">
    <strong><mdss:localize key="dataUploader.country"/></strong>
  </div>
  <div class="holder">
    <div class="row-holder">
      <div class="box">
        <select class="select-area" ng-model="sheet.country" name="country" ng-options="opt.value as opt.label for opt in options.countries" ng-required="true"></select>      
      </div>
    </div>
  </div> 
</div>