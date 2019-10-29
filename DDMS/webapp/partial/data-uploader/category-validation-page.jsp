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

<div>
  <div class="wide-holder">
    <div class="row-holder">    
      <div class="inline-value">
        <label><mdss:localize key="dataUploader.categoryAttribute"/></label>   
        <div class="label-help-ico-container">
          <i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.categoryAttributeHelpToolTip"/>"></i>
        </div>   
      </div>    
      <div class="inline-value">
        <label><mdss:localize key="dataUploader.unknownCategory"/></label>
        <div class="label-help-ico-container">
          <i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.unknownCategoryHelpToolTip"/>"></i>
      </div>   
      </div>
      <div class="inline-combo">
        <label><mdss:localize key="dataUploader.synonymn"/></label>
        <div class="label-help-ico-container">
          <i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.categorySynonymSearchHelpToolTip"/>"></i>
      </div> 
      </div>
      <div class="inline-actions">
        <label><mdss:localize key="dataUploader.actions"/></label>
        <div class="label-help-ico-container">
          <i class="fa fa-question-circle help-info-ico" title="<mdss:localize key="dataUploader.categoryProblemActionsHelpToolTip"/>"></i>
      </div> 
      </div>
    </div>  
    
    <div ng-repeat="problem in problems.categories">
      <category-validation-problem problem="problem" options="problems.options[problem.mdAttributeId]"></category-validation-problem>
    </div>
  </div>
  <div class="wide-holder">
    <div class="error-message">
      <p ng-show="form.$error.size"><mdss:localize key="dataUploader.existingProblems"/></p>
    </div>          
    <div>
      <p ng-show="!form.$error.size"><mdss:localize key="dataUploader.noCategoryProblem"/></p>
    </div>          
  </div>  
</div>
