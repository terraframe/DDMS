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

<div class="panel-group choice-color category-group">
  <div class="panel">
    <div id="choice-color02" class="panel-collapse">
      <ul class="color-list">
        <li ng-repeat="category in categories.catLiElems track by $index">
          <div class="category-container">
            <div class="text category-input-container">
              <div class="category-input-sub-container">
              	<input class="category-input left" type="text" ng-model="category.val" ng-required="categoryCheck()" placeholder="<mdss:localize key="DashboardLayer.form.catPlaceHolder"/>" autocomplete="on" category-auto-complete source="autoComplete()" number-only enforce="{{type === 'number'}}"></input>
              </div>
            </div>
            <div class="cell">
              <styled-color-picker model="category.color" scroll="#layer-modal"></styled-color-picker>             
            </div>
          </div>
        </li> <!-- end categories iterator -->
        
        <!-- Other category -->
        <li>
	        <div class="other-cat-container" ng-show="categories.otherEnabled && showOther == 'true'">
	          <div class="category-container">
	            <div class="text category-input-container">
	              <input class="category-input" ng-disabled="true" type="text" placeholder="<mdss:localize key="Other"/>"></input>
	            </div>
	            <div class="cell">
	              <styled-color-picker model="categories.other.color" scroll="#layer-modal"></styled-color-picker>             
	            </div>
	          </div> 
	        </div>         
        </li>
      </ul>
    </div>
  </div>
  
  <!-- enable/disable checkbox -->
  <div class="style-options-block" ng-show="dynamic == true">
    <a href="#" ng-click="ctrl.addOption()" class="fa fa-plus"><mdss:localize key="DashboardThematicLayer.form.addCategory"/></a>       
  </div>    

  <!-- enable/disable checkbox -->
  <div class="style-options-block" ng-show="showOther == 'true'">    
    <styled-check-box id="basic-cat-point-other-option" model="categories.otherEnabled" label="<mdss:localize key="DashboardThematicLayer.form.categoryOtherOptionLabel"/>"></styled-check-box>
  </div>  
  
</div>



