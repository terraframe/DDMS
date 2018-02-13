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
  <a ng-click="ctrl.toggle()">
  	<i class="fa fa-caret-right" aria-hidden="true"></i>
  	<i class="fa fa-caret-down" style="display:none;" aria-hidden="true"></i>
    <mdss:localize key="dataUploader.resolvedSynonym"/> [{{action.label}}]
  </a>
  
  <span ng-show="show">
    <ul>
      <mdss:localize key="dataUploader.locatedWithin"/>
      <li ng-repeat="ancestor in action.ancestors">{{ancestor.label}}</li>
    </ul>
  </span>
</div>
