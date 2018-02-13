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
<div class="term-tree-buttons-wrapper">

	<a ng-show="geomType == 'POINT'" class="term-tree-config-link" href="#">
	  <span class="ico" ng-click="ctrl.configure()"><mdss:localize key="DashboardThematicLayer.form.category.configure.label"/></span>
	</a>
	
	<div class="term-tree-icon-wrapper" >
		<a ng-show="geomType == 'POINT' && category.enableIcon" href="#" class="term-tree-icon">
	  		<img style="width:20px;height:20px;" class="thumb" ng-src="${pageContext.request.contextPath}/iconimage/getCategoryIconImage?iconId={{ category.icon }}&''" alt="Icon">
		</a>
		<simple-color-picker ng-show="!category.enableIcon" category="category" scroll="#layer-modal"></simple-color-picker>
	</div>
</div>