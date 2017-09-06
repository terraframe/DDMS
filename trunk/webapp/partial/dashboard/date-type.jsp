<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<div>
  <div class="text">
    <label><mdss:localize key="dashboardViewer.dates.from" /></label>
    <span class="data-text"> 
      <input ng-model="attribute.filter.startDate" id="test-start" class="checkin gdb-attr-filter filter-date DatePick" type="text" placeholder="" />
    </span>
  </div>
  <div class="text">
    <label><mdss:localize key="dashboardViewer.dates.to" /></label>
    <span class="data-text"> 
      <input ng-model="attribute.filter.endDate" id="test-end" class="checkout gdb-attr-filter filter-date DatePick" type="text" placeholder="" />
    </span>
  </div>
</div>