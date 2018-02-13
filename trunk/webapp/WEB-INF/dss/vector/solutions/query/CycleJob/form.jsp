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
<mjl:component param="view" item="${item}">  
  <mjl:input param="concreteId" type="hidden" value="${item.concreteId}" />
  <mjl:input param="savedMap" type="hidden" value="${item.savedMapId}" />
  <mjl:dt attribute="createJob">
    <mjl:boolean param="createJob" />
  </mjl:dt>  
  <mjl:dt attribute="layerId">
    <mjl:select param="layerId" id="job-layer-id" items="${layers}" var="current" valueAttribute="semanticId">
      <mjl:option selected="${(layers != null && item.layerId != null && current.semanticId != null && current.semanticId == item.layerId) ? true : false}">
        ${current.layerName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="imageWidth">
    <mjl:input param="imageWidth" type="text" /> <mdss:localize key="px"/>
  </mjl:dt>
  <mjl:dt attribute="imageHeight">
    <mjl:input param="imageHeight" type="text" /> <mdss:localize key="px"/>
  </mjl:dt>  
</mjl:component>
