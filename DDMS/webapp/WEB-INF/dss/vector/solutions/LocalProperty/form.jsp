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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="editable">
    <mjl:boolean param="editable" />
  </mjl:dt>
  <mjl:dt attribute="propertyName">
    <mjl:input param="propertyName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyPackage">
    <mjl:input param="propertyPackage" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyType">
    <mjl:input param="propertyType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyValidator">
    <mjl:input param="propertyValidator" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyValue">
    <mjl:input param="propertyValue" type="text" />
  </mjl:dt>
  <mjl:dt attribute="validValues">
    <mjl:input param="validValues" type="text" />
  </mjl:dt>
</mjl:component>
