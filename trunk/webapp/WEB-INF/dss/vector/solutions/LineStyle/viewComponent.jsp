<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.LineStyle.form.name" id="dss.vector.solutions.LineStyle.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.strokeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.stroke}
    </dd>
    <dt>
      <label>
        ${item.strokeWidthMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.strokeWidth}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.LineStyleController.edit.mojo" name="dss.vector.solutions.LineStyle.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.LineStyleController.viewAll.mojo" name="dss.vector.solutions.LineStyle.viewAll.link">
<fmt:message key="View_All" />
</mjl:commandLink>
