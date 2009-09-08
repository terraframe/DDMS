<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.mo.AbstractTerm.form.name" id="dss.vector.solutions.mo.AbstractTerm.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.termNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.termName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.mo.AbstractTermController.edit.mojo" name="dss.vector.solutions.mo.AbstractTerm.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.mo.AbstractTermController.viewAll.mojo" name="dss.vector.solutions.mo.AbstractTerm.viewAll.link" />
