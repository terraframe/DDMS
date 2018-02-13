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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<mjl:component param="mdField" item="${item}">
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="required">
    <mjl:boolean param="required" />
  </mjl:dt>  
  <mjl:dt attribute="remove">
    <mjl:boolean param="remove" />
  </mjl:dt>
  <mjl:dt attribute="numeratorAggregation">
    <mjl:select param="numeratorAggregation" items="${aggregations}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="numeratorField">
    <mjl:select param="numeratorField" items="${numerics}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="denominatorAggregation">
    <mjl:select param="denominatorAggregation" items="${aggregations}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="denominatorField">
    <mjl:select param="denominatorField" items="${numerics}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="percentage">
    <mjl:boolean param="percentage" />
  </mjl:dt>      
  
</mjl:component>
