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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Search_Lethal_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="search" method="POST" id ="searchForm">
    <dt>
      <label>
        <mdss:localize key="Insecticide_Active_Ingredient"/>
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticideId">
        <mjl:option selected="${(insecticideId != null && current.id == insecticideId) ? true : false}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
  <mdss:localize key="Search" var="Localized_Search" />
  <mjl:command classes="submitButton" action="dss.vector.solutions.general.LethalTimePropertyController.searchByInsecticide.mojo" name="search.button" value="${Localized_Search}" />
  </mjl:form>
</dl>