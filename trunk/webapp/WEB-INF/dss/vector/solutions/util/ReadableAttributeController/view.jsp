<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Assign_Attribute_Permissions" scope="request" />

<mjl:form name="dss.vector.solutions.util.ReadableAttributeController.form.name" id="dss.vector.solutions.util.ReadableAttributeController.form.id" method="POST">
  <mjl:input type="hidden" param="universal" value="${universal}" />
  <mjl:input type="hidden" param="actor" value="${actor}" />

      <table class="displayTable">
        <mjl:components items="${views}" param="attributeViews" var="view" varStatus="status">
          <mjl:input type="hidden" param="attributeName" value="${view.attributeName}"/>
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
            ${view.attributeRequired == true ? "*" : ""}
            </td>
            <td>
              <mjl:input type="text" param="displayLabel" size="70"/>
            </td>
            <td>
              <c:choose >
                <c:when test="${view.attributeRequired}">
                  <mjl:boolean param="readPermission" value="${view.readPermission}" disabled="disabled"/>
                </c:when>
                <c:otherwise>
                  <mjl:boolean param="readPermission" value="${view.readPermission}" />
                </c:otherwise>
              </c:choose>
              <mjl:messages attribute="readPermission">
                <mjl:message/>
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>

  <mjl:command value="save" action="dss.vector.solutions.util.ReadableAttributeController.setAttributes.mojo" name="dss.vector.solutions.util.ReadableAttributeController.form.create.button" />
</mjl:form>