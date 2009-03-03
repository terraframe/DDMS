<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.mo.IdentificationMethod.form.name" id="ivcc.mrc.csu.mdss.mo.IdentificationMethod.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.definitionMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.definition}
    </dd>
    <dt>
      <label>
        ${item.inheritsTermMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inheritsTerm}
    </dd>
    <dt>
      <label>
        ${item.inheritsTermNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inheritsTermName}
    </dd>
    <dt>
      <label>
        ${item.oboNamespaceMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.oboNamespace}
    </dd>
    <dt>
      <label>
        ${item.termIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.termId}
    </dd>
    <dt>
      <label>
        ${item.termNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.termName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.mo.IdentificationMethodController.edit.mojo" name="ivcc.mrc.csu.mdss.mo.IdentificationMethod.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.mo.IdentificationMethodController.viewAll.mojo" name="ivcc.mrc.csu.mdss.mo.IdentificationMethod.viewAll.link" />
