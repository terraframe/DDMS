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

<mjl:component item="${category}" param="category">
<dl>
  <mjl:dt attribute="lowerBoundStr">
    <mjl:input param="lowerBoundStr" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperBoundStr">
    <mjl:input param="upperBoundStr" type="text" />
  </mjl:dt>
</dl>
</mjl:component>  
  
<jsp:include page="../Styles/form.jsp"></jsp:include>
