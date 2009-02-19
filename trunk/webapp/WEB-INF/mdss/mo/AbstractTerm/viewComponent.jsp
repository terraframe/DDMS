<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.mo.AbstractTerm.form.name" id="mdss.mo.AbstractTerm.form.id" method="POST">
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
  <mjl:command value="Edit" action="mdss.mo.AbstractTermController.edit.mojo" name="mdss.mo.AbstractTerm.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.mo.AbstractTermController.viewAll.mojo" name="mdss.mo.AbstractTerm.viewAll.link" />
