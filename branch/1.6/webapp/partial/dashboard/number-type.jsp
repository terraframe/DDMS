<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<div>
  <div class="select-holder">
    <select ng-model="attribute.filter.operation" name="{{attribute.mdAttributeId}}-operation" ng-class="{'filter-select':true, 'field-error': form[attribute.mdAttributeId + '-operation'].$invalid }" ng-required='attribute.filter.value' >
      <option value="gt">&gt;</option>
      <option value="ge">&gt;=</option>
      <option value="lt">&lt;</option>
      <option value="le">&lt;=</option>
    </select>
  </div>
  <div class="text">
    <label class="none"><mdss:localize key="dashboardViewer.number"/></label>
         
    <input ng-if="whole" ng-model="attribute.filter.value" name="{{attribute.mdAttributeId}}" ng-class="{'gdb-attr-filter':true, 'filter-number':true, 'field-error':(form[attribute.mdAttributeId].$invalid && !form[attribute.mdAttributeId].$pristine) }" type="text" placeholder="<mdss:localize key="dashboard.number.label" />" integer-only></input>
    <input ng-if="!whole" ng-model="attribute.filter.value" name="{{attribute.mdAttributeId}}" ng-class="{'gdb-attr-filter':true, 'filter-number':true, 'field-error':(form[attribute.mdAttributeId].$invalid && !form[attribute.mdAttributeId].$pristine) }" type="text" placeholder="<mdss:localize key="dashboard.number.label" />" number-only></input>
  </div>
</div>
