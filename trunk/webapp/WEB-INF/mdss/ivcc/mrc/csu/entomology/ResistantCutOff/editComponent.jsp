<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.ResistantCutOff.form.name" id="mdss.ivcc.mrc.csu.entomology.ResistantCutOff.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.aAKnockDownPRMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="aAKnockDownPR" />
        <mjl:messages attribute="aAKnockDownPR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.aAKnockDownRMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="aAKnockDownR" />
        <mjl:messages attribute="aAKnockDownR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.aDDARMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="aDDAR" />
        <mjl:messages attribute="aDDAR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.aDDASMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="aDDAS" />
        <mjl:messages attribute="aDDAS">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lAKnockDownPRMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lAKnockDownPR" />
        <mjl:messages attribute="lAKnockDownPR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lAKnockDownRMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lAKnockDownR" />
        <mjl:messages attribute="lAKnockDownR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lDDARMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lDDAR" />
        <mjl:messages attribute="lDDAR">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lDDASMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lDDAS" />
        <mjl:messages attribute="lDDAS">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.ResistantCutOffController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.ResistantCutOff.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.ResistantCutOffController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.ResistantCutOff.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.ResistantCutOffController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.ResistantCutOff.form.cancel.button" />
</mjl:form>
