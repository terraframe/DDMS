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
  <div class="select-holder">
    <select ng-model="attribute.filter.operation" name="{{attribute.mdAttributeId}}-operation" ng-class="{'filter-select':true, 'field-error': form[attribute.mdAttributeId + '-operation'].$invalid }" ng-required='attribute.filter.value'>
      <option value="eq">=</option>
      <option value="neq">!=</option>
    </select>
  </div>
  <div class="text">
    <label class="none"><mdss:localize key="dashboardViewer.text"/></label>
    <input ng-model="attribute.filter.value" name="{{attribute.mdAttributeId}}" ng-class="{'field-error':(form[attribute.mdAttributeId].$invalid && !form[attribute.mdAttributeId].$pristine) }" type="text" placeholder="<mdss:localize key="dashboard.text.label"/>" category-auto-complete source="ctrl.getTextSuggestions"></input>
  </div>
</div>