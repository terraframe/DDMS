<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.ResistantCutOff.form.name" id="mdss.entomology.ResistantCutOff.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.aAKnockDownPRMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.aAKnockDownPR}
    </dd>
    <dt>
      <label>
        ${item.aAKnockDownRMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.aAKnockDownR}
    </dd>
    <dt>
      <label>
        ${item.aDDARMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.aDDAR}
    </dd>
    <dt>
      <label>
        ${item.aDDASMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.aDDAS}
    </dd>
    <dt>
      <label>
        ${item.lAKnockDownPRMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lAKnockDownPR}
    </dd>
    <dt>
      <label>
        ${item.lAKnockDownRMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lAKnockDownR}
    </dd>
    <dt>
      <label>
        ${item.lDDARMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lDDAR}
    </dd>
    <dt>
      <label>
        ${item.lDDASMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lDDAS}
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.entomology.ResistantCutOffController.edit.mojo" name="mdss.entomology.ResistantCutOff.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.ResistantCutOffController.viewAll.mojo" name="mdss.entomology.ResistantCutOff.viewAll.link" />
