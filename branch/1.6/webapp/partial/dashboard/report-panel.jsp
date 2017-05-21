<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<article id="reporticng-container" class="reporticng-container report-panel-closed">
    <div ng-if="ctrl.canEdit() || hasReport" id="report-toolbar">
      <div id="report-toggle-container">
        <i ng-show="ctrl.state != 'min'" ng-click="ctrl.collapse()" id="report-collapse-toggle" class="fa fa-angle-double-down report-height-toggle"></i>
        <i ng-show="ctrl.state != 'max'" ng-click="ctrl.expand()" id="report-expand-toggle" class="fa fa-angle-double-up report-height-toggle"></i>
      </div>      
    </div>
    <div id="report-viewport" ng-class="{'opaque' : opaque, 'translucent' : !opaque }" context-menu="menuOptions">    
      <div id="report-content">
      </div>   
    </div>
</article>
