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
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="customTextElementId">
    <mjl:input param="customTextElementId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontColor">
    <mjl:input param="fontColor" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <mjl:input param="fontFamily" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontSize">
    <mjl:input param="fontSize" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontStyle">
    <mjl:input param="fontStyle" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textValue">
    <mjl:input param="textValue" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textXPosition">
    <mjl:input param="textXPosition" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textYPosition">
    <mjl:input param="textYPosition" type="text" />
  </mjl:dt>
</mjl:component>
