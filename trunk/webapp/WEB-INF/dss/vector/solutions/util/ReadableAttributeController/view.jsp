<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.util.ReadableAttributeController.form.name" id="dss.vector.solutions.util.ReadableAttributeController.form.id" method="POST">
  <mjl:input type="hidden" param="universal" value="${universal}" />
  <mjl:input type="hidden" param="actor" value="${actor}" />
  
      <table class="displayTable">
        <mjl:components items="${views}" param="attributeViews" var="view" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              <mjl:input type="text" param="displayLabel"/>
            </td>
            <td>
              <mjl:boolean param="readPermission" trueLabel="show" falseLabel="hide" value="${view.readPermission}"/>
              <mjl:messages attribute="readPermission">
                <mjl:message/>
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
      
  <mjl:command value="save" action="dss.vector.solutions.util.ReadableAttributeController.setAttributes.mojo" name="dss.vector.solutions.util.ReadableAttributeController.form.create.button" />
</mjl:form>