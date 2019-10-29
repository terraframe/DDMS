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
  <div class="text">
    <label><mdss:localize key="dashboardViewer.dates.from" /></label>
    <span class="data-text"> 
      <input ng-model="attribute.filter.startDate" id="{{attribute.mdAttributeId}}-start" class="checkin gdb-attr-filter filter-date DatePick" type="text" placeholder="" />
    </span>
  </div>
  <div class="text">
    <label><mdss:localize key="dashboardViewer.dates.to" /></label>
    <span class="data-text"> 
      <input ng-model="attribute.filter.endDate" id="{{attribute.mdAttributeId}}-end" class="checkout gdb-attr-filter filter-date DatePick" type="text" placeholder="" />
    </span>
  </div>
</div>