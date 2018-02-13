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
<c:set var="page_title" value="View_Insecticide"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.Insecticide.form.name" id="dss.vector.solutions.general.Insecticide.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activeIngredientMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink action="dss.vector.solutions.mo.ActiveIngredientController.view.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.view.link">
        <mjl:property value="${item.activeIngredient.id}" name="id" />
        ${item.activeIngredient.displayLabel}
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.amountMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.amount}
    </dd>
    <dt>
      <label>
        ${item.unitsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.units.displayLabel}
    </dd>
  </dl>
<%--
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.general.InsecticideController.edit.mojo" name="dss.vector.solutions.general.Insecticide.form.edit.button" />
  --%><br />
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.general.InsecticideController.viewAll.mojo" name="dss.vector.solutions.general.Insecticide.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
