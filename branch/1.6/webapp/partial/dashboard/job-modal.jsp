<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div>
  <form class="modal-form" name="form">
    <div class="modal-header">
      <h3 class="modal-title">
        <mdss:localize key="dashboardViewer.jobHeader"/>
      </h3>
    </div>
    <div class="modal-body" id="modal-body">
      <fieldset>      
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageWidth"/></strong>
          </div>
          <div class="holder" >
            <span class="text">            
              <input type="text" ng-model="job.width" integer-only required>
            </span>
          </div>
        </div>  
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageHeight"/></strong>
          </div>
          <div class="holder" >
            <span class="text">            
              <input type="text" ng-model="job.height" integer-only required>
            </span>
          </div>
        </div>  
        <div class="row-holder">
          <div class="label-holder">
            <strong><mdss:localize key="dashboardViewer.imageLayer"/></strong>
          </div>
          <div class="holder">
            <div class="box">
              <div class="select-box">
                <select class="method-select" ng-model="job.layer" ng-options="opt.id as opt.label for opt in layers">
                  <option value=""></option>
                </select>
              </div>  
            </div>
          </div>
        </div>
        <div class="row-holder">
          <div class="label-holder"></div>
          <div class="holder">
            <div class="button-holder">
              <input type="button" class="btn btn-default" ng-click="cancel()" value="<mdss:localize key="dashboard.Cancel"/>"/>
              <input type="button" class="btn btn-primary" ng-click="submit()" ng-disabled="form.$invalid" value="<mdss:localize key="dashboard.Ok"/>" />
            </div>
          </div>
        </div>                
      </fieldset>
    </div>
  </form>  
</div>
