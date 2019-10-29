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
<c:set scope="request" var="page_title" value="Export_Localization" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="LocalizationController.name" id="LocalizationController.form.id" method="POST">
  <dl>
    <mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
      <mjl:freeColumn>
        <mjl:header>
          <mdss:localize key="Locale" />
        </mjl:header>
        <mjl:row>
          ${item.localeLabel}
        </mjl:row>
      </mjl:freeColumn>
      <mjl:freeColumn>
        <mjl:header>
          <mdss:localize key="Export" />
        </mjl:header>
        <mjl:row>
          <mjl:input type="checkbox" param="locales" value="${item.enumName}" />
        </mjl:row>
      </mjl:freeColumn>
    </mjl:table>
    <mdss:localize key="exportFile" var="Localized_exportFile" />
    <mjl:command value="${Localized_exportFile}" action="dss.vector.solutions.util.LocalizationController.exportFile.mojo" name="LocalizationController.form.exportFile.button" />
  </dl>
</mjl:form>
<mjl:commandLink name="LocalizationController.add" action="dss.vector.solutions.util.LocalizationController.newLocale.mojo">
  <mdss:localize key="Add_New_Locale" />
</mjl:commandLink>

<div class="pageTitle"><mdss:localize key="Import_Localization" /></div>
<dl>
<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.util.LocalizationController.importFile.mojo">
  <mdss:localize key="xls_file"/>: <br />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="<mdss:localize key="Import" />" style="margin-left: 0px; top: 0px;" />
</form>
</dl>