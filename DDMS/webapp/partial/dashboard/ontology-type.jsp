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
  <div class="term">
    <label class="none"><mdss:localize key="dashboardViewer.text"/></label>  
    <input name="{{attribute.mdAttributeId}}" class="filter-term" type="text" placeholder="<mdss:localize key="dashboard.text.label"/>" />
  </div>
  
  <div class="term" ng-repeat="term in attribute.filter.value track by term.id">
    <p style="width: 220px; float: left;">{{term.label}}</p>
    <div class="cell pull-right">            
      <a href="#" class="fa fa-times ico-remove" ng-click="ctrl.remove($index)"></a>
    </div>
  </div>    
</div>

