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

<form class="modal-form" name="ctrl.form">    
  <div class="modal-dialog">
    <div class="modal-content" show-on-ready>
      <div class="heading">
        <h1><mdss:localize key="category.management.editTooltip"/></h1>
      </div>
      <fieldset>
        <div class="row-holder" ng-show="errors.length > 0 && show">
          <div class="label-holder">
          </div>
          <div class="holder">
            <div class="alert alertbox" ng-repeat="error in errors track by $index">
              <p class="error-message">{{error}}</p>
            </div>
          </div>
        </div>
        <div class="row-holder">
          <div class="label-holder">
            <label><mdss:localize key="category.management.label"/></label>
          </div>    
          <div class="holder" >
            <span class="text">
              <input type="text" ng-model="category.label" name="label" required validate-unique validator="ctrl.isUniqueLabel">
            </span>
            <div class="inline-error-message">
              <p ng-show="ctrl.form.label.$error.unique">
                <mdss:localize key="dataUploader.unique"/>
              </p>
            </div>         
          </div>
        </div>
        <div class="row-holder">
          <div class="label-holder">
            <label><mdss:localize key="category.management.descendants"/></label>
          </div>    
          <div class="holder" >
            <div class="list-table-wrapper">
	            <table class="list-table table table-bordered table-striped">
	              <tbody>
	                <tr ng-repeat="descendant in category.descendants" class="fade-ngRepeat-item">
	                  <td class="button-column">
	                    <a class="fa fa-pencil ico-edit" ng-click="ctrl.edit(descendant)" title="<mdss:localize key="category.management.editTooltip"/>"></a>
	                    <a class="fa fa-trash-o ico-remove" ng-click="ctrl.remove(descendant)" title="<mdss:localize key="category.management.removeTooltip"/>"></a>                               
	                  </td>
	                  <td class="label-column">{{descendant.label}}</td>
	                </tr>
	                <tr>
	                  <td class="button-column">
	                    <a class="fa fa-plus" ng-show="!instance.isNew" ng-click="ctrl.newInstance()" title="<mdss:localize key="category.management.createCategoryOptionTooltip"/>"></a>
	                  </td>                 
	                  <td ng-show="instance.isNew" class="submit-form">
	                    <input class="list-table-input" type="text" ng-model="instance.label" ng-show="instance.isNew" press-esc="ctrl.cancel()" press-enter="ctrl.apply()" focus-on-show></input>                    
	                  </td>                
	                </tr>
	              </tbody>
	            </table>
	        </div>
          </div>
        </div>
        <div class="row-holder" fire-on-ready>
          <div class="label-holder">
          </div>  
          <div class="holder">
            <div class="button-holder">
              <input type="button" value="<mdss:localize key="category.management.done"/>" class="btn btn-primary" ng-click="ctrl.ok()" ng-disabled="ctrl.form.$invalid" />
            </div>
          </div>
        </div>
      </fieldset>
    </div>
  </div>
</form>