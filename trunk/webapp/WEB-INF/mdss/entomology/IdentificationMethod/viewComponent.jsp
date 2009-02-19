<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.IdentificationMethod.form.name" id="mdss.entomology.IdentificationMethod.form.id" method="POST">
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
  <mjl:command value="Edit" action="mdss.entomology.IdentificationMethodController.edit.mojo" name="mdss.entomology.IdentificationMethod.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.IdentificationMethodController.viewAll.mojo" name="mdss.entomology.IdentificationMethod.viewAll.link" />
