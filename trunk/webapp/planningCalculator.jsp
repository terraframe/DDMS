<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<c:set var="page_title" value="About"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><fmt:message key="Planning_Calculator"/></div>

<dl id="planningCalculator">
	<dt>
		<label title="<fmt:message key="Premises_Manpower_Calculator"/>"><fmt:message key="Premises_Manpower_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Units_Per_Block"/></th>
				<th><fmt:message key="Days_Per_Unit"/></th>
				<th><fmt:message key="Days_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pmc.blocks" /></td>
				<td><input type="text" id="pmc.units-per-block" /></td>
				<td><input type="text" id="pmc.days-per-unit" /></td>
				<td><input type="text" id="pmc.days" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<fmt:message key="Premises_Granular_Insecticide_Calculator"/>"><fmt:message key="Premises_Granular_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Units_Per_Block"/></th>
				<th><fmt:message key="Grams_Per_Unit"/></th>
				<th><fmt:message key="Kilograms_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pgic.blocks" /></td>
				<td><input type="text" id="pgic.units-per-block" /></td>
				<td><input type="text" id="pgic.grams-per-unit" /></td>
				<td><input type="text" id="pgic.kilograms" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<fmt:message key="Premises_Spray_Insecticide_Calculator"/>"><fmt:message key="Premises_Spray_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Units_Per_Block"/></th>
				<th><fmt:message key="Liters_Per_Unit"/></th>
				<th><fmt:message key="Liters_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="psic.blocks" /></td>
				<td><input type="text" id="psic.units-per-block" /></td>
				<td><input type="text" id="psic.liters-per-unit" /></td>
				<td><input type="text" id="psic.liters" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<fmt:message key="Premises_Material_Insecticide_Calculator"/>"><fmt:message key="Premises_Material_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Units_Per_Block"/></th>
				<th><fmt:message key="ITMs_Per_Unit"/></th>
				<th><fmt:message key="ITMs_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pmic.blocks" /></td>
				<td><input type="text" id="pmic.units-per-block" /></td>
				<td><input type="text" id="pmic.itms-per-unit" /></td>
				<td><input type="text" id="pmic.itms" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<fmt:message key="Vehicle_Manpower_Calculator"/>"><fmt:message key="Vehicle_Manpower_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Minutes_Per_Block"/></th>
				<th></th>
				<th><fmt:message key="Hours_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="vmc.blocks" /></td>
				<td><input type="text" id="vmc.minutes-per-block" /></td>
				<td></td>
				<td><input type="text" id="vmc.hours" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<fmt:message key="Vehicle_Spray_Insecticide_Calculator"/>"><fmt:message key="Vehicle_Spray_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><fmt:message key="Blocks_Targeted"/></th>
				<th><fmt:message key="Liters_Per_Block"/></th>
				<th></th>
				<th><fmt:message key="Kiloliters_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="vsic.blocks" /></td>
				<td><input type="text" id="vsic.liters-per-block" /></td>
				<td></td>
				<td><input type="text" id="vsic.kiloliters" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

</dl>

</div>

<br/><br/>

<jsp:include page="/revision.html" />




<jsp:include page="/WEB-INF/templates/footer.jsp" />