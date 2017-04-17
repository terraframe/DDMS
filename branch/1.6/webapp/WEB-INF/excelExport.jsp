<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageTitle"><mdss:localize key="Excel_Export_Header" /></div>
<table class="displayTable">
  <tr>
    <th><mdss:localize key="Excel_Class_Label" /></th>
    <th><mdss:localize key="Excel_Qualified_Classname" /></th>
    <th/>
  <tr>
  
  <% boolean altRow = true; %>
  <c:forEach var="class" items="${classes}">
    <% altRow = !altRow; %>
    <tr <% if (altRow) out.print("class=\"evenRow\""); else out.print("class=\"oddRow\""); %>>
    <td>${class.displayLabel}</td>
    <td>${class.packageName}.${class.typeName}</td>
    <td>
      <form method="post" action="excelexport">
        <input type="hidden" name="excelType" value="${class.packageName}.${class.typeName}"/>
        <input class="submitButton" type="submit" value="Export" style="margin-left: 0px; top: 0px;" />
      </form>
    </td>
  </tr>
  </c:forEach>
  
  </tr>
</table>
<jsp:include page="/WEB-INF/templates/footer.jsp" />