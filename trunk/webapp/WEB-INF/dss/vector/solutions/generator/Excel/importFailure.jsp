<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="Excel_Import_Fail" scope="request" />

<table class="displayTable">
  <tr>
    <th><mdss:localize key="Excel_Row" /></th>
    <th><mdss:localize key="Excel_Column" /></th>
    <th><mdss:localize key="Excel_Error" /></th>
  </tr>
  <% boolean altRow = true; %>
  <c:forEach var="problem" items="${problems}">
    <% altRow = !altRow; %>
    <tr <% if (altRow) out.print("class=\"evenRow\""); else out.print("class=\"oddRow\""); %>>
      <td>${problem.row}</td>
      <td>${problem.column}</td>
      <td>${problem.message}</td>
    </tr>
  </c:forEach>
</table>
