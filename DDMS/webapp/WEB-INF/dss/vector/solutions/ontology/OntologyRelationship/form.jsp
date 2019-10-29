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
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="altId">
    <mjl:input type="text" param="altId" />
  </mjl:dt>
  <mjl:dt attribute="comment">
    <mjl:input type="text" param="comment" />
  </mjl:dt>
  <mjl:dt attribute="def">
    <mjl:input type="text" param="def" />
  </mjl:dt>
  <mjl:dt attribute="inverseOf">
    <mjl:select var="current" valueAttribute="id" items="${inverseOf}" param="inverseOf">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="inverseOfOnInstanceLevel">
    <mjl:select var="current" valueAttribute="id" items="${inverseOfOnInstanceLevel}" param="inverseOfOnInstanceLevel">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="isAntiSymmetric">
    <mjl:boolean param="isAntiSymmetric" />
  </mjl:dt>
  <mjl:dt attribute="isBuiltIn">
    <mjl:boolean param="isBuiltIn" />
  </mjl:dt>
  <mjl:dt attribute="isObsolete">
    <mjl:boolean param="isObsolete" />
  </mjl:dt>
  <mjl:dt attribute="isReflexive">
    <mjl:boolean param="isReflexive" />
  </mjl:dt>
  <mjl:dt attribute="isTransitive">
    <mjl:boolean param="isTransitive" />
  </mjl:dt>
  <mjl:dt attribute="keyName">
    <mjl:input type="text" param="keyName" />
  </mjl:dt>
  <mjl:dt attribute="name">
    <mjl:input type="text" param="name" />
  </mjl:dt>
  <mjl:dt attribute="namespace">
    <mjl:input type="text" param="namespace" />
  </mjl:dt>
  <mjl:dt attribute="relationshipId">
    <mjl:input type="text" param="relationshipId" />
  </mjl:dt>
</mjl:component>
