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
<mjl:component param="dto" item="${item}">
  <mjl:input param="insecticide" type="hidden" value="${item.insecticide.id}"/>
  <mjl:dt attribute="insecticide">
    ${item.insecticide.displayLabel}      
  </mjl:dt>
  <mjl:dt attribute="lowerPercent">
    <mjl:input param="lowerPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lowerTime">
    <mjl:input param="lowerTime" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperPercent">
    <mjl:input param="upperPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperTime">
    <mjl:input param="upperTime" type="text" />
  </mjl:dt>
</mjl:component>
