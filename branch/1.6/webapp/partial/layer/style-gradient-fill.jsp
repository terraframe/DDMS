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

<div class="fill-block" ng-class="clazz">
  <strong class="title"><mdss:localize key="DashboardThematicLayer.form.fill"/></strong>
  <div class="cell-holder">
    <div class="cell">
      <span><mdss:localize key="DashboardLayer.form.minFill"/></span>
      <styled-color-picker model="minFill" scroll="#layer-modal"></styled-color-picker>                   
    </div>
    <div class="cell">
      <span><mdss:localize key="DashboardLayer.form.maxFill"/></span>
      <styled-color-picker model="maxFill" scroll="#layer-modal"></styled-color-picker>                   
    </div>
    <div class="cell opacity">
      <label><mdss:localize key="DashboardLayer.form.opacity"/></label>
      <div class="text">
        <select class="tab-select" ng-model="opacity" convert-to-number>
          <c:forEach step="5" begin="0" end="100" var="size">
            <option value="${size/100}">${size}</option>
          </c:forEach>        
        </select>        
      </div>
    </div>
    <div class="cell gradient-buckets-size">
      <label><mdss:localize key="DashboardThematicLayer.form.gradientNumCategories"/></label>
      <div class="text">
        <select class="tab-select" ng-model="numberOfCategories" convert-to-number>
          <c:forEach step="1" begin="1" end="100" var="size">
            <option value="${size}">${size}</option>
          </c:forEach>        
        </select>        
      </div>
    </div>
    
  </div>
</div>

