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
  <mjl:dt attribute="reportItem">
    <mjl:select param="reportItem" items="${_reportItem}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="cancelable">
    <mjl:boolean param="cancelable" />
  </mjl:dt>
  <mjl:dt attribute="canceled">
    <mjl:boolean param="canceled" />
  </mjl:dt>
  <mjl:dt attribute="completed">
    <mjl:boolean param="completed" />
  </mjl:dt>
  <mjl:dt attribute="cronExpression">
    <mjl:input param="cronExpression" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="endTime" type="text" />
  <mjl:dt attribute="jobId">
    <mjl:input param="jobId" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="lastRun" type="text" />
  <mjl:dt attribute="maxRetries">
    <mjl:input param="maxRetries" type="text" />
  </mjl:dt>
  <mjl:dt attribute="pauseable">
    <mjl:boolean param="pauseable" />
  </mjl:dt>
  <mjl:dt attribute="paused">
    <mjl:boolean param="paused" />
  </mjl:dt>
  <mjl:dt attribute="removeOnComplete">
    <mjl:boolean param="removeOnComplete" />
  </mjl:dt>
  <mjl:dt attribute="repeated">
    <mjl:boolean param="repeated" />
  </mjl:dt>
  <mjl:dt attribute="retries">
    <mjl:input param="retries" type="text" />
  </mjl:dt>
  <mjl:dt attribute="running">
    <mjl:boolean param="running" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="startTime" type="text" />
  <mjl:dt attribute="timeout">
    <mjl:input param="timeout" type="text" />
  </mjl:dt>
  <mjl:dt attribute="workProgress">
    <mjl:input param="workProgress" type="text" />
  </mjl:dt>
  <mjl:dt attribute="workTotal">
    <mjl:input param="workTotal" type="text" />
  </mjl:dt>
</mjl:component>
