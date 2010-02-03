<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Export_Localization" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="LocalizationController.name" id="LocalizationController.form.id" method="POST">
  <dl>
    <table class="displayTable">
      <tr> 
        <th><fmt:message key="Locale"/></th>
        <th><fmt:message key="Export"/></th>
      </tr>      
      <%--<mjl:components items="${supported}" param="types" var="current" varStatus="status">--%>
      <c:forEach var="current" items="${supported}">
        <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
          <td>
            ${current.displayName}
          </td>
          <td>
            <mjl:input type="checkbox" param="locales" value="${current}"/>
          </td>
        </tr>
      </c:forEach>
      <%--</mjl:components>--%>
    </table>
    <mjl:command value="exportFile" action="dss.vector.solutions.util.LocalizationController.exportFile.mojo" name="LocalizationController.form.exportFile.button" />
  </dl>
</mjl:form>

<div class="pageTitle"><fmt:message key="Import_Localization" /></div>
<dl>
<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.util.LocalizationController.importFile.mojo">
  XLS File: <br />
  <input type="file" name="upfile"/> <br />
  <input class="submitButton" type="submit" value="Import" style="margin-left: 0px; top: 0px;" />
</form>
</dl>