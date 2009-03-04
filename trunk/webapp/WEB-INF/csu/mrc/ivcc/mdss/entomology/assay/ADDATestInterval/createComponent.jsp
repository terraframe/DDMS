<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.ADDATestInterval.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.ADDATestInterval.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.assayMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_ADDATestInterval_assay}" param="assay">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.knockedDownMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="knockedDown" />
        <mjl:messages attribute="knockedDown">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.periodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="period" />
        <mjl:messages attribute="period">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalController.create.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.ADDATestInterval.form.create.button" />
</mjl:form>
