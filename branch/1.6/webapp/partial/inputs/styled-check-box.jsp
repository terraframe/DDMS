<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<div class="check-block">
  <div ng-attr-id="{{id || undefined}}" ng-attr-name="{{name}}" ng-click="ctrl.toggle()" ng-class="{'chk-checked' : model}" ng-style="chkstyle" class="jcf-unselectable chk-area">
    <span></span>
  </div>
  <label ng-if="label.length>0" ng-attr-for="{{id || undefined}}">{{label}}</label>
</div>