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
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="dashboard">
    <mjl:select valueAttribute="id" param="dashboard" var="current" items="${dashboard}">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="wrappedMdClass">
    <mjl:select valueAttribute="id" param="wrappedMdClass" var="current" items="${wrappedMdClass}">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
