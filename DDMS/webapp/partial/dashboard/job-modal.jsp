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
  <form class="modal-form" name="form">
    <div class="modal-header">
      <h3 class="modal-title">
        <mdss:localize key="dashboardViewer.jobHeader"/>
      </h3>
    </div>
    <div class="modal-body" id="modal-body">
      <fieldset>      
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageWidth"/></strong>
          </div>
          <div class="holder" >
            <span class="text">            
              <input type="text" ng-model="job.width" integer-only required>
            </span>
          </div>
        </div>  
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageHeight"/></strong>
          </div>
          <div class="holder" >
            <span class="text">            
              <input type="text" ng-model="job.height" integer-only required>
            </span>
          </div>
        </div>  
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageLayer"/></strong>
          </div>
          <div class="holder">
            <div class="box">
              <div class="select-box">
                <select class="method-select" ng-model="job.layer" ng-options="opt.id as opt.label for opt in layers">
                  <option value=""></option>
                </select>
              </div>  
            </div>
          </div>
        </div>
        <div class="row-holder">
          <div class="label-holder"></div>
          <div class="holder">
            <div class="button-holder">
              <input type="button" class="btn btn-default" ng-click="cancel()" value="<mdss:localize key="dashboard.Cancel"/>"/>
              <input type="button" class="btn btn-primary" ng-click="submit()" ng-disabled="form.$invalid" value="<mdss:localize key="dashboard.Ok"/>" />
            </div>
          </div>
        </div>                
      </fieldset>
    </div>
  </form>  
</div>
