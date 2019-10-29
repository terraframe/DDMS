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
  <div ng-if="show">
    <div class="modal-backdrop fade in"></div>
    <div id="clone-modal" style="display: block;" class="modal fade in" role="dialog" aria-hidden="false" data-backdrop="static" data-keyboard="false">
      <dl>
        <form class="modal-form" name="form">
          <div class="modal-dialog">
            <div class="modal-content" style="display: none;" show-on-ready>
              <div class="heading">
                <h1><mdss:localize key="dashboard.clone.label"/> [{{dashboard.label}}]</h1>
              </div>
              <fieldset>
                <div class="row-holder" ng-show="errors.length > 0">
                  <div class="label-holder"></div>      
                  <div class="holder">
                    <div class="alert alertbox" ng-repeat="error in errors track by $index">
                      <p >{{error}}</p>
                    </div>
                  </div>
                </div>
                <div class="row-holder">
                  <div class="label-holder">
                    <strong><mdss:localize key="dashboard.label"/></strong>
                  </div>
                  <div class="holder">
                    <span class="text">
                      <input type="text" ng-model="dashboard.name" name="dashboard.name" required fire-on-ready>
                    </span>
                  </div>
                </div>
                <div class="row-holder">
                  <div class="label-holder"></div>
                  <div class="holder">
                    <div class="button-holder">
                      <input type="button" class="btn btn-default" ng-click="ctrl.cancel()" value="<mdss:localize key="dashboard.Cancel"/>"/>
                      <input type="button" class="btn btn-primary" ng-click="ctrl.submit()" ng-disabled="form.$invalid" value="<mdss:localize key="dashboard.Ok"/>" />
                    </div>
                  </div>
                </div>                
              </fieldset>
            </div>
          </div>
        </form>    
      </dl>
    </div>
  </div>
</div>