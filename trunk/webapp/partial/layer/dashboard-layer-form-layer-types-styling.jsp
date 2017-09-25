<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
       
<div class="row-holder">
  <div class="label-holder">
    <strong><mdss:localize key="DashboardThematicLayer.form.styleTheLayer"/></strong>
  </div>
  <div class="holder">
    <div id="layer-type-styler-container" class="tab-content"> 
      
      <!-- BASICPOINT -->
      <basic-point></basic-point>
      
      <!-- GRADIENT POINT -->
      <div class="tab-pane" id="tab006gradientpoint" ng-class="{ 'active' : layerModel.layerType == 'GRADIENTPOINT' }">
      
        <!-- POINT GRADIENT FILL -->
        <style-gradient-fill min-fill="styleModel.gradientPointMinFill" max-fill="styleModel.gradientPointMaxFill" opacity="styleModel.gradientPointFillOpacity" number-of-categories="styleModel.numGradientPointCategories" class="point-gradient"></style-gradient-fill>
        
        <!-- POINT GRADIENT STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.gradientPointStroke" stroke-width="styleModel.gradientPointStrokeWidth" stroke-opacity="styleModel.gradientPointStrokeOpacity"></style-stroke>
        
        <!-- POINT GRADIENT SHAPE -->        
        <div class="fill-block">
         <strong class="title"><mdss:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
          <div class="cell-holder">
            <div class="cell">
              <label for="gradient-point-radius-select"><mdss:localize key="DashboardLayer.form.size"/></label>
              <div class="text">
                <input id="gradient-point-radius-select" name="style.gradientPointSize" type="text" ng-model="styleModel.gradientPointSize" required integer-only></input>
              </div>
            </div>
          </div>
          <div id="gradient-point-type-container" class="cell">
            <label for="gradient-point-type"><mdss:localize key="DashboardLayer.form.pointType"/></label>
            <styled-basic-select options="dynamicDataModel.pointTypes" model="styleModel.gradientPointWellKnownName" class="method-select"></styled-basic-select>            
          </div>
        </div>
      </div>
      
      <!-- CATEGORY POINT -->
      <div class="tab-pane" id="tab007categoriespoint" ng-class="{ 'active' : layerModel.layerType == 'CATEGORYPOINT' }">
      
        <div class="color-section">
          <strong class="title"><mdss:localize key="DashboardThematicLayer.form.fill"/></strong>
          <div class="heading-list">
            <span ng-if="!categoryWidget.basicPointCatOptionsObj.rangeCategoriesEnabled">
              <mdss:localize key="DashboardThematicLayer.form.category"/>
            </span>
            <span ng-if="categoryWidget.basicPointCatOptionsObj.rangeCategoriesEnabled">
              Upper Thresholds
            </span>
            <span><mdss:localize key="DashboardThematicLayer.form.color"/></span>
            <span></span>
          </div>
          
          <div class="category-block" id="category-point-colors-container">

            <!-- RENDER BASIC CATEGORIES -->
            <style-category-list dynamic="dynamicDataModel.dynamic" categories="categoryWidget.basicPointCatOptionsObj" auto-complete="ctrl.basicCategoryAutocompleteSource" type="{{dynamicDataModel.thematicAttributeDataType}}"></style-category-list>
          </div>
        </div>
        
        <div class="stroke-block">
          <!-- POINT CATEGORY STROKE -->        
          <style-stroke stroke="styleModel.categoryPointStroke" stroke-width="styleModel.categoryPointStrokeWidth" stroke-opacity="styleModel.categoryPointStrokeOpacity"></style-stroke>
        
          <div id="category-point-radius-block" class="fill-block">
            <strong class="title"><mdss:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
            <div class="cell-holder">
              <div class="cell">
                <label for="category-point-radius-select"><mdss:localize key="DashboardLayer.form.size"/></label>
                <div class="text">
                  <input id="category-point-radius-select" name="style.categoryPointSize" type="text" ng-model="styleModel.categoryPointSize" required integer-only></input>
                </div>
              </div>
              <div class="cell">
                <label for="category-point-fill-opacity-select"><mdss:localize key="DashboardLayer.form.opacity"/></label>
                <div class="text">
                  <select id="category-point-fill-opacity-select" class="tab-select" name="style.categoryPointFillOpacity"
                    ng-model="styleModel.categoryPointFillOpacity" convert-to-number>
                    <c:forEach step="5" begin="0" end="100" var="size">
                      <option value="${size/100}">${size}</option>
                    </c:forEach>                    
                  </select>
                </div>
              </div>
            </div>
          
            <div id="category-point-type-container" class="cell">
              <label for="category-point-type"><mdss:localize key="DashboardLayer.form.pointType"/></label>
              <styled-basic-select options="dynamicDataModel.pointTypes" model="styleModel.categoryPointWellKnownName" class="method-select"></styled-basic-select>            
            </div>
          </div>
          
          <!-- enable/disable range categories -->
          <div class="fill-block" ng-if="dynamicDataModel.thematicAttributeDataType === 'number'">    
            <styled-check-box id="cat-range-categories" model="categoryWidget.basicPointCatOptionsObj.rangeCategoriesEnabled" label="<mdss:localize key="DashboardThematicLayer.form.categoryRangeCategoriesLabel"/>"></styled-check-box>
          </div>  
        </div>
      </div>
    
      
      <!-- BUBBLE -->
      <div class="tab-pane" id="tab002bubble" ng-class="{ 'active' : layerModel.layerType == 'BUBBLE' }">
        <!-- BASIC FILL -->
        <style-basic-fill fill="styleModel.bubbleFill" opacity="styleModel.bubbleOpacity"></style-basic-fill>
      
        <!-- BASIC STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.bubbleStroke" stroke-width="styleModel.bubbleStrokeWidth" stroke-opacity="styleModel.bubbleStrokeOpacity"></style-stroke>
      
        <div class="fill-block">
          <strong class="title"><mdss:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
          <div class="cell-holder">
            <div class="cell">
              <label for="f76"><mdss:localize key="DashboardLayer.form.min"/></label>
              <div class="text">
                <input id="f76" name="style.bubbleMinSize" type="text" ng-model="styleModel.bubbleMinSize"/>
              </div>
            </div>
            <div class="cell">
              <label for="f77"><mdss:localize key="DashboardLayer.form.max"/></label>
              <div class="text">
                <input id="f77" name="style.bubbleMaxSize" type="text" ng-model="styleModel.bubbleMaxSize"></input>
              </div>
            </div>
            <div ng-show="!styleModel.bubbleContinuousSize" class="cell bubble-buckets-size">
		      <label><mdss:localize key="DashboardThematicLayer.form.gradientNumCategories"/></label>
		      <div class="text">
		        <select class="tab-select" ng-model="styleModel.numBubbleSizeCategories" ng-options="n for n in [] | range:1:ctrl.getMaxBubbleBucketSize()">
<%-- 		          <c:forEach step="1" begin="1" end="50" var="size"> --%>
<%-- 		            <option value="${size}">${size}</option> --%>
<%-- 		          </c:forEach>         --%>
		        </select>        
		      </div>
		    </div>
		    
            <styled-check-box model="styleModel.bubbleContinuousSize" label="<mdss:localize key="DashboardThematicLayer.form.continuousSize"/>"></styled-check-box>
          
          </div>
        </div>
        
        <div class="secondary-box">
          <strong class="title"><mdss:localize key="DashboardLayer.form.secondaryAttributeStyle"/></strong>
          <div class="cell-holder">        
            <label class="secondary-label" for="secondaryAttribute" ><mdss:localize key="DashboardLayer.form.secondaryAttribute"/></label>
            <div id="secondary-select-box" class="select-box">
              <select id="secondaryAttribute" class="method-select" name="secondaryAttribute"
                ng-model="styleModel.secondaryAggregation.attribute"
                ng-options="attr as attr.label for attr in dynamicDataModel.secondaryAttributes track by attr.id"
                ng-selected="styleModel.secondaryAggregation.attribute.id == attr.id"
                ng-change="ctrl.onAttributeChange()">
              </select>
            </div>
          </div>
          
          <div id="secondary-content" ng-show="ctrl.secondaryAttributeIsValid()" >
            <div class="cell-holder">
              <label class="secondary-label" for="secondaryAggregation"><mdss:localize key="DashboardLayer.form.secondaryAggregation"/></label>
              <div class="select-box" id="secondary-aggregation-container">
              
                <select id="secondaryAggregation" class="method-select" name="secondaryAggregation"
                  ng-model="styleModel.secondaryAggregation.method"
                  ng-options="agg as agg.label for agg in dynamicDataModel.secondaryAggregationMethods track by agg.value"
                  ng-selected="styleModel.secondaryAggregation.method.value == agg.value">
                </select>
                
              </div> 
            </div>
            
            <!-- enable/disable range categories -->
            <div class="cell-holder" ng-if="styleModel.secondaryAggregation.attribute.categoryType === 'number'">    
              <styled-check-box id="cat-range-categories" model="styleModel.secondaryAggregation.rangeCategoriesEnabled" label="<mdss:localize key="DashboardThematicLayer.form.categoryRangeCategoriesLabel"/>"></styled-check-box>
            </div>  
            
            <div id="secondary-cateogries">
              <div class="color-section">
                <div class="heading-list">
                  <span ng-if="!styleModel.secondaryAggregation.rangeCategoriesEnabled">
                    <mdss:localize key="DashboardThematicLayer.form.category"/>
                  </span>
                  <span ng-if="styleModel.secondaryAggregation.rangeCategoriesEnabled">
                    Upper Thresholds
                  </span>
                
                  <span><mdss:localize key="DashboardLayer.form.color"/></span>
                  <span></span>
                </div>
                <div class="category-block">
                  <!-- RENDER SECONDARY CATEGORIES -->
                  <style-category-list dynamic="ctrl.isSecondaryDynamic()" categories="styleModel.secondaryAggregation" auto-complete="ctrl.secondaryCategoryAutocompleteSource" show-other="false" type="{{styleModel.secondaryAggregation.attribute.categoryType}}" is-secondary-attribute-list="false"></style-category-list>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      
      <!-- BASICPOLYGON -->
      <basic-polygon></basic-polygon>            
      
      <!-- GRADIENT POLYGON -->
      <div class="tab-pane" id="tab004gradientpolygon" ng-class="{ 'active' : layerModel.layerType == 'GRADIENTPOLYGON' }">
        <!-- POINT GRADIENT FILL -->
        <style-gradient-fill min-fill="styleModel.gradientPolygonMinFill" max-fill="styleModel.gradientPolygonMaxFill" opacity="styleModel.gradientPolygonFillOpacity" number-of-categories="styleModel.numGradientPolygonCategories" class="point-gradient"></style-gradient-fill>
        
        <!-- POLYGON GRADIENT STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.gradientPolygonStroke" stroke-width="styleModel.gradientPolygonStrokeWidth" stroke-opacity="styleModel.gradientPolygonStrokeOpacity"></style-stroke>        
      </div>
      
     
      <!-- CATEGORY POLYGON -->
      <div class="tab-pane" id="tab005categoriespolygon" ng-class="{ 'active' : layerModel.layerType == 'CATEGORYPOLYGON' }">
        <div class="color-section">
          <strong class="title"><mdss:localize key="DashboardThematicLayer.form.fill"/></strong>
          <div class="heading-list">
            <span ng-if="!categoryWidget.basicPointCatOptionsObj.rangeCategoriesEnabled">
              <mdss:localize key="DashboardThematicLayer.form.category"/>
            </span>
            <span ng-if="categoryWidget.basicPointCatOptionsObj.rangeCategoriesEnabled">
              Upper Thresholds
            </span>
            <span><mdss:localize key="DashboardThematicLayer.form.color"/></span>
            <span></span>
          </div>
          
          <div class="category-block" id="category-colors-container">
            <!-- RENDER BASIC CATEGORIES -->
            <style-category-list dynamic="dynamicDataModel.dynamic" categories="categoryWidget.polygonCatOptionsObj" auto-complete="ctrl.basicCategoryAutocompleteSource" type="{{dynamicDataModel.thematicAttributeDataType}}"></style-category-list>
          </div>
        </div>
      
        <!-- POLYGON CATEGORY STROKE -->        
        <style-stroke class="stroke-block" stroke="styleModel.categoryPolygonStroke" stroke-width="styleModel.categoryPolygonStrokeWidth" stroke-opacity="styleModel.categoryPolygonStrokeOpacity"></style-stroke>

        <!-- enable/disable range categories -->
        <div class="fill-block" ng-if="dynamicDataModel.thematicAttributeDataType === 'number'">    
          <styled-check-box id="cat-range-categories" model="categoryWidget.polygonCatOptionsObj.rangeCategoriesEnabled" label="<mdss:localize key="DashboardThematicLayer.form.categoryRangeCategoriesLabel"/>"></styled-check-box>
        </div>  
        
      </div>    
    </div> <!--  end style container  -->
  </div>  <!--  end holder  -->
</div> 
