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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set scope="request" var="page_title" value="View_All_SystemAlert" />
<mjl:messages>
	<mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}"
	even="evenRow" odd="oddRow">
	<mjl:context
		action="dss.vector.solutions.general.SystemAlertController.viewPage.mojo" />
	<mjl:columns>
		<mjl:attributeColumn attributeName="alertType">
			<mjl:row>
				<ul>
					<c:forEach items="${item.alertTypeEnumNames}" var="enumName">
						<li> ${item.alertTypeMd.enumItems[enumName]} </li>
					</c:forEach>
				</ul>
			</mjl:row>
		</mjl:attributeColumn>
		<mjl:attributeColumn attributeName="isOnscreenActive">
		  <mjl:row>
            ${item.isOnscreenActive ? item.isOnscreenActiveMd.positiveDisplayLabel : item.isOnscreenActiveMd.negativeDisplayLabel}		    
		  </mjl:row>
		</mjl:attributeColumn>
		<mjl:attributeColumn attributeName="isEmailActive">
		  <mjl:row>
            ${item.isEmailActive ? item.isEmailActiveMd.positiveDisplayLabel : item.isEmailActiveMd.negativeDisplayLabel}		    
		  </mjl:row>
		</mjl:attributeColumn>
		<mjl:freeColumn>
			<mjl:header>

			</mjl:header>
			<mjl:row>
				<mjl:form name="dss.vector.solutions.SystemAlert.form.name"
					id="${item.id}" method="POST">
					<mjl:input value="${item.id}" type="hidden" param="id" />
					<mdss:localize key="Edit" var="Localized_Edit" />
					<mjl:command value="${Localized_Edit}"
						action="dss.vector.solutions.general.SystemAlertController.edit.mojo"
						name="dss.vector.solutions.SystemAlert.form.edit.button" />
				</mjl:form>
			</mjl:row>
			<mjl:footer>

			</mjl:footer>
		</mjl:freeColumn>
	</mjl:columns>
	<mjl:pagination>
		<mjl:page />
	</mjl:pagination>
</mjl:table>
