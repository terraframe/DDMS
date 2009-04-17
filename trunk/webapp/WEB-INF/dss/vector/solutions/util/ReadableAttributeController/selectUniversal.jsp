<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>

<mjl:form name="dss.vector.solutions.util.ReadableAttributeController.form.name">
  <mjl:input type="hidden" param="actor" value="${actor}"/>

  <mjl:table query="${query}" var="row" classes="displayTable" even="evenRow" odd="oddRow">
    <mjl:columns>
      <mjl:freeColumn>
        <mjl:header></mjl:header>
        <mjl:row>
          <mjl:input type="radio" param="universal" value="${row.packageName}.${row.typeName}"/>
        </mjl:row>
        <mjl:footer></mjl:footer>
      </mjl:freeColumn>
      <mjl:freeColumn>
        <mjl:header>
          <f:message key="Class_Label" />
        </mjl:header>
        <mjl:row>
          ${row.displayLabel}
        </mjl:row>
        <mjl:footer>
        </mjl:footer>
      </mjl:freeColumn>
    </mjl:columns>
  </mjl:table>
  
  <mjl:command name="Submit" action="dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo" />
</mjl:form>