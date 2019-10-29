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

<div class="ontology-category-input-container">

  <div class="ontology-tree"></div>
  
  <div class="other-cat-container" ng-show="categories.otherEnabled && showOther == 'true'">
    <ul class="color-list other-cat">
      <li>
        <div class="category-container">
          <div class="text category-input-container">
            <p><mdss:localize key="Other"/></p>
          </div>
          <styled-category geom-type="geomType" category="categories.other" scroll="#layer-modal"></styled-category>
        </div>
      </li>
    </ul>
  </div>
  <div class="check-block" ng-show="showOther == 'true'">
    <styled-check-box model="categories.otherEnabled" label="<mdss:localize key="DashboardThematicLayer.form.categoryOtherOptionLabel"/>"></styled-check-box>
  </div>
</div>
