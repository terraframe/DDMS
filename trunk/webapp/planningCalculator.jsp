<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>
<c:set var="page_title" value="Planning_Calculator"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />

<div class="pageContent">
<div class="pageTitle"><mdss:localize key="Planning_Calculator"/></div>

<script>
function round(n, places) {
	var d = Math.pow(10,places);
	return Math.round(n * d)/d
}

function calculate(aName, bName, cName, d, resultName) {
	var a = aName ? parseFloat(document.getElementById(aName).value) : NaN;
	var b = bName ? parseFloat(document.getElementById(bName).value) : NaN;
	var c = cName ? parseFloat(document.getElementById(cName).value) : 1;
	var result = round(a * b * c / d, 2);
	if (isNaN(result) || a < 0 || b < 0 || c < 0) {
		document.getElementById(resultName).value = "";
	} else {
		document.getElementById(resultName).value = result;
	} 
}

function pmc() {
	calculate("pmc.blocks", "pmc.units-per-block", "pmc.days-per-unit", 1, "pmc.days");
}

function pgic() {
	calculate("pgic.blocks", "pgic.units-per-block", "pgic.grams-per-unit", 1000, "pgic.kilograms");
}

function psic() {
	calculate("psic.blocks", "psic.units-per-block", "psic.liters-per-unit", 1, "psic.liters");
}

function pmic() {
	calculate("pmic.blocks", "pmic.units-per-block", "pmic.itms-per-unit", 1, "pmic.itms");
}

function vmc() {
	calculate("vmc.blocks", "vmc.minutes-per-block", undefined, 60, "vmc.hours");
}

function vsic() {
	calculate("vsic.blocks", "vsic.liters-per-block", undefined, 1000, "vsic.kiloliters");
}

function checkGTE(field) {
	if (parseFloat(field.value) < 0) {
		alert('<mdss:localize key="Value_Not_GTE_0" />');
		setTimeout("document.getElementById('"+field.id+"').focus();",1);
		setTimeout("document.getElementById('"+field.id+"').select();",1);
	} 
}
</script>

<dl id="planningCalculator">
	<h2><mdss:localize key="Premises_Calculator_Subheading" /></h2>
      
	<dt>
		<label title="<mdss:localize key="Premises_Manpower_Calculator"/>"><mdss:localize key="Premises_Manpower_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Units_Per_Block"/></th>
				<th><mdss:localize key="Days_Per_Unit"/></th>
				<th><mdss:localize key="Days_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pmc.blocks" onkeyup="pmc();" /></td>
				<td><input type="text" id="pmc.units-per-block" onkeyup="pmc();" /></td>
				<td><input type="text" id="pmc.days-per-unit" onkeyup="pmc();" /></td>
				<td><input type="text" id="pmc.days" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<mdss:localize key="Premises_Granular_Insecticide_Calculator"/>"><mdss:localize key="Premises_Granular_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Units_Per_Block"/></th>
				<th><mdss:localize key="Grams_Per_Unit"/></th>
				<th><mdss:localize key="Kilograms_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pgic.blocks" onkeyup="pgic();" /></td>
				<td><input type="text" id="pgic.units-per-block" onkeyup="pgic();" /></td>
				<td><input type="text" id="pgic.grams-per-unit" onkeyup="pgic();" /></td>
				<td><input type="text" id="pgic.kilograms" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<mdss:localize key="Premises_Spray_Insecticide_Calculator"/>"><mdss:localize key="Premises_Spray_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Units_Per_Block"/></th>
				<th><mdss:localize key="Liters_Per_Unit"/></th>
				<th><mdss:localize key="Liters_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="psic.blocks" onkeyup="psic();" /></td>
				<td><input type="text" id="psic.units-per-block" onkeyup="psic();" /></td>
				<td><input type="text" id="psic.liters-per-unit" onkeyup="psic();" /></td>
				<td><input type="text" id="psic.liters" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<mdss:localize key="Premises_Material_Insecticide_Calculator"/>"><mdss:localize key="Premises_Material_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Units_Per_Block"/></th>
				<th><mdss:localize key="ITMs_Per_Unit"/></th>
				<th><mdss:localize key="ITMs_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="pmic.blocks" onkeyup="pmic();" /></td>
				<td><input type="text" id="pmic.units-per-block" onkeyup="pmic();" /></td>
				<td><input type="text" id="pmic.itms-per-unit" onkeyup="pmic();" /></td>
				<td><input type="text" id="pmic.itms" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<h2><mdss:localize key="Vehicle_Calculator_Subheading" /></h2>

	<dt>
		<label title="<mdss:localize key="Vehicle_Manpower_Calculator"/>"><mdss:localize key="Vehicle_Manpower_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Minutes_Per_Block"/></th>
				<th>&nbsp;</th>
				<th><mdss:localize key="Hours_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="vmc.blocks" onkeyup="vmc();" /></td>
				<td><input type="text" id="vmc.minutes-per-block" onkeyup="vmc();" /></td>
				<td>&nbsp;</td>
				<td><input type="text" id="vmc.hours" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

	<dt>
		<label title="<mdss:localize key="Vehicle_Spray_Insecticide_Calculator"/>"><mdss:localize key="Vehicle_Spray_Insecticide_Calculator"/></label>
	</dt>
	<dd>
		<table>
			<tr valign="top">
				<th><mdss:localize key="Blocks_Targeted"/></th>
				<th><mdss:localize key="Liters_Per_Block"/></th>
				<th>&nbsp;</th>
				<th><mdss:localize key="Kiloliters_To_Complete_Activity"/></th>
			</tr>
			<tr>
				<td><input type="text" id="vsic.blocks" onkeyup="vsic();" /></td>
				<td><input type="text" id="vsic.liters-per-block" onkeyup="vsic();" /></td>
				<td>&nbsp;</td>
				<td><input type="text" id="vsic.kiloliters" disabled="disabled"/></td>
			</tr>
		</table>
	</dd>

</dl>

</div>

<br/><br/>

<jsp:include page="/revision.html" />




<jsp:include page="/WEB-INF/templates/footer.jsp" />