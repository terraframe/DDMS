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

<c:forEach items="${item.alertTypeEnumNames}" var="enumName">
  <c:set scope="request" var="page_title_suffix" value=" - ${item.alertTypeMd.enumItems[enumName]}" />
</c:forEach>

<c:set scope="request" var="page_title" value="Edit_SystemAlert" />

<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="displayName">
    ${item.displayName}
  </mjl:dt>
  <mjl:dt attribute="isOnscreenActive">
    <mjl:boolean param="isOnscreenActive" />
  </mjl:dt>
  <mjl:dt attribute="isEmailActive">
    <mjl:boolean param="isEmailActive" />
  </mjl:dt>
  <mjl:dt attribute="emailFromAddress">
    <mjl:input param="emailFromAddress" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailToAddresses">
    <mjl:input param="emailToAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailCcAddresses">
    <mjl:input param="emailCcAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailBccAddresses">
    <mjl:input param="emailBccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailSubjectText">
    <mjl:input param="emailSubjectText" type="text" value="${item.emailSubjectText}"/>
  </mjl:dt>
  <mjl:dt attribute="emailBodyText">
    <mjl:textarea param="emailBodyText"  value="${item.emailBodyText}" rows="5" />
    <i><b>${templateLabel}:</b> ${templateVariables}</i>
  </mjl:dt>

</mjl:component>