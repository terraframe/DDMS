<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Confirm_Export_Types" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="ExportController.name" id="ExportController.form.id" method="POST">
  <dl>
    <table class="displayTable">
      <tr> 
        <th><mdss:localize key="Qualified_Type_Name"/></th>
        <th><mdss:localize key="Display_Label"/></th>
        <th><mdss:localize key="Description"/></th>
      </tr>      
      <mjl:components items="${views}" param="types" var="current" varStatus="status">
        <mjl:input type="hidden" param="mdTypeId" value="${current.mdTypeId}"/>
        <mjl:input type="hidden" param="exported" value="true"/>
        <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
          <td>
            ${current.qualifiedType}
          </td>
          <td>
            ${current.displayLabel}
          </td>
          <td>
            ${current.description}
          </td>
        </tr>
      </mjl:components>
    </table>
    <mdss:localize key="confirm" var="Localized_confirm" />
    <mjl:command value="${Localized_confirm}" action="dss.vector.solutions.synchronization.ExportController.confirm.mojo" name="ExportController.form.confirm.button" />
  </dl>
</mjl:form>