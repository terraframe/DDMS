<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
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
					<mjl:command value="Edit"
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
