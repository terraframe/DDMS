<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Add_New_Export_Types" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="ExportController.name" id="ExportController.form.id" method="POST">
  <dl>
<mjl:table classes="displayTable" var="item" query="${results}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.synchronization.ExportController.search.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="qualifiedType"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel"></mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description"></mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        <fmt:message key="Export" />
      </mjl:header>
      <mjl:row>
        <mjl:input type="checkbox" param="types" value="${item.mdTypeId}"/>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
    <mjl:command value="checkDependencies" action="dss.vector.solutions.synchronization.ExportController.checkDependencies.mojo" name="ExportController.form.checkDependencies.button" />
  </dl>
</mjl:form>
      <%--
<mjl:form name="ExportController.name" id="ExportController.form.id" method="POST">
  <dl>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Qualified_Type_Name"/></th>
          <th><fmt:message key="Export"/></th>
        </tr>      
        <mjl:components items="${results}" param="types" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.qualifiedType}
            </td>
            <td>
              <mjl:boolean param="enabled"/>
              <mjl:messages attribute="enabled">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    <mjl:command value="checkDependencies" action="dss.vector.solutions.synchronization.ExportController.checkDependencies.mojo" name="ExportController.form.checkDependencies.button" />
  </dl>
</mjl:form>
      --%>