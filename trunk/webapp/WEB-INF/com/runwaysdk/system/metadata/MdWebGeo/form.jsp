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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
<c:if test="${isComposite == false}">
  <mjl:dt attribute="showOnSearch">
    <mjl:boolean param="showOnSearch" />
  </mjl:dt>    
</c:if>    
</mjl:component>
<mjl:component param="geoField" item="${geoField}">
  <mjl:dt attribute="isUnderSystemRoot">
    <mjl:boolean param="isUnderSystemRoot" />
  </mjl:dt>
  <mjl:dt attribute="isPoliticalHierarchy">
    <mjl:boolean param="isPoliticalHierarchy" />
  </mjl:dt>
  <mjl:dt attribute="isSprayHierarchy">
    <mjl:boolean param="isSprayHierarchy" />
  </mjl:dt>
  <mjl:dt attribute="isPopulationHierarchy">
    <mjl:boolean param="isPopulationHierarchy" />
  </mjl:dt>
  <mjl:dt attribute="isUrbanHierarchy">
    <mjl:boolean param="isUrbanHierarchy" />
  </mjl:dt>
  <mjl:dt attribute="filter">
    <mjl:select param="filter" items="${universals}" var="current" valueAttribute="geoHierarchyId" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
</mjl:component>
<br />
<dt>
  <label><mdss:localize key="Specific_Universals"/></label>
</dt>  
<mjl:group type="checkbox" param="extraUniversals" items="${universals}" var="current" valueAttribute="geoHierarchyId">
  <mjl:groupOption checked="${mjl:contains(selected, current.geoHierarchyId) ? 'checked' : 'false'}">
    ${current.displayLabel} <br />
  </mjl:groupOption>
</mjl:group>
