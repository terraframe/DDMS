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