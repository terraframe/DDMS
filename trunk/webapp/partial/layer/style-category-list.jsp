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



