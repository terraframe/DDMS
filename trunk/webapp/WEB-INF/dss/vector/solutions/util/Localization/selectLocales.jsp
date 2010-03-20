<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Export_Localization" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<script>
var checked = 0;
function enableButton(checkbox) {
	if (checkbox.checked) {
		checked++;
	} else {
		checked--;
	}
	//alert(checked);
	if (checked > 0) {
		document.getElementsByName("LocalizationController.form.exportFile.button")[0].disabled=false;
	} else  {
		document.getElementsByName("LocalizationController.form.exportFile.button")[0].disabled=true;
	}
}
</script>
<mjl:form name="LocalizationController.name" id="LocalizationController.form.id" method="POST">
  <dl>
    <mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
      <mjl:freeColumn>
        <mjl:header>
          <fmt:message key="Locale" />
        </mjl:header>
        <mjl:row>
          ${item.localeLabel}
        </mjl:row>
      </mjl:freeColumn>
      <mjl:freeColumn>
        <mjl:header>
          <fmt:message key="Export" />
        </mjl:header>
        <mjl:row>
          <mjl:input type="checkbox" param="locales" value="${item.enumName}" onchange="enableButton(this);"/>
        </mjl:row>
      </mjl:freeColumn>
    </mjl:table>
    <mjl:command value="exportFile" action="dss.vector.solutions.util.LocalizationController.exportFile.mojo" name="LocalizationController.form.exportFile.button" />
  </dl>
</mjl:form>
<mjl:commandLink name="LocalizationController.add" action="dss.vector.solutions.util.LocalizationController.newLocale.mojo">
  <fmt:message key="Add_New_Locale" />
</mjl:commandLink>

<script>
document.getElementsByName("LocalizationController.form.exportFile.button")[0].disabled=true;
</script>

<div class="pageTitle"><fmt:message key="Import_Localization" /></div>
<dl>
<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.util.LocalizationController.importFile.mojo">
  <fmt:message key="xls_file"/>: <br />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="<fmt:message key="Import" />" style="margin-left: 0px; top: 0px;" />
</form>
</dl>