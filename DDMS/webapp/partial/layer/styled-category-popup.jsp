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

<div id="category-icon-modal-container">
  <div class="cell">
    <styled-check-box model="category.enableIcon" name="style.enableIcon" label="<mdss:localize key="DashboardThematicLayer.form.enableIcon"/>"></styled-check-box>
  </div>
<!--   <div class="cell"> -->
<!--     <styled-color-picker model="category.color" scroll="#layer-modal"></styled-color-picker>              -->
<!--   </div> -->

  <div ng-show="category.enableIcon" class="select-box">
    <select class="method-select" ng-model="category.icon" ng-options="opt.id as opt.label for opt in icons">
      <option value=""></option>    
    </select>
  </div>
<!--   <div class="fill-block"> -->
    <div ng-show="category.enableIcon" class="cell-holder">
      <div class="cell">
        <label for="custom-icon-radius-select"><mdss:localize key="DashboardLayer.form.size"/></label>
        <div class="text">
          <input id="custom-icon-radius-select" name="category.iconSize" type="text" ng-model="category.iconSize" placeholder="{{category.iconSize}}" required integer-only></input>
        </div>
      </div>
    </div>
<!--   </div> -->

</div>