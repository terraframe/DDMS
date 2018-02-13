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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Add_New_Locale" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="LocalizationController.name" id="LocalizationController.form.id" method="POST">
  <dl>
    <dt>
      <mdss:localize key="language"/>
    </dt>
    <dd>
      <select name="language">
        <c:forEach
          items="${languages}"
          var="l">
          <option value="${l.language}">${l.displayLanguage}</option>
        </c:forEach>
      </select>
    </dd>
    <dt>
      <mdss:localize key="country_optional"/>
    </dt>
    <dd>
      <select name="country">
        <option value="" />
        <c:forEach
          items="${countries}"
          var="country">
          <option value="${country.country}">${country.displayCountry}</option>
        </c:forEach>
      </select>
    </dd>
    <dt>
      <mdss:localize key="variant_optional"/>
    </dt>
    <dd>
      <mjl:input type="text" param="variant"/>
    </dd>
    <mdss:localize key="installLocale" var="Localized_installLocale" />
    <mjl:command value="${Localized_installLocale}" action="dss.vector.solutions.util.LocalizationController.installLocale.mojo" name="LocalizationController.form.installLocale.button" />
  </dl>
</mjl:form>