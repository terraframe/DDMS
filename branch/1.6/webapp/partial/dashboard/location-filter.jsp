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
