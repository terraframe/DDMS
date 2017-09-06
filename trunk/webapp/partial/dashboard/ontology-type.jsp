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

