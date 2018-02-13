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
<div class="filter-block" id="location-filter-container">
  <div class="row-holder">
    <label for="filter-geo"><mdss:localize key="filter.geo"/></label>
  </div>
  <div class="geo">
	  <input id="filter-geo" type="text" class="gdb-attr-filter filter-geo" placeholder="<mdss:localize key="dashboard.entity.label"/>"></input>
  </div>
  <div class="geo" ng-repeat="location in filter.locations track by location.value">
    <p>{{location.label}}</p>
    <div class="cell pull-right">            
      <a href="#" class="fa fa-times ico-remove" ng-click="ctrl.remove($index)"></a>
    </div>
  </div>  
</div>
