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
  <mjl:dt attribute="dateOfBirth">
    <mjl:input param="dateOfBirth" type="text" />
  </mjl:dt>
  <mjl:dt attribute="firstName">
    <mjl:input param="firstName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="iptRecipientDelegate">
    <mjl:select param="iptRecipientDelegate" items="${iptRecipientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="itnRecipientDelegate">
    <mjl:select param="itnRecipientDelegate" items="${itnRecipientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lastName">
    <mjl:input param="lastName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="patientDelegate">
    <mjl:select param="patientDelegate" items="${patientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="residentialGeoEntity">
    <mjl:select param="residentialGeoEntity" items="${residentialGeoEntity}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mjl:select param="sex" items="${sex}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.sexMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sprayLeaderDelegate">
    <mjl:select param="sprayLeaderDelegate" items="${sprayLeaderDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sprayOperatorDelegate">
    <mjl:select param="sprayOperatorDelegate" items="${sprayOperatorDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="userDelegate">
    <mjl:select param="userDelegate" items="${userDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
