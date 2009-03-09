<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.generated.AbstractSite.form.name" id="csu.mrc.ivcc.mdss.geo.generated.AbstractSite.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activatedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="activated" />
      </dd>
      <dt>
        <label>
          ${item.entityNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="entityName" />
        <mjl:messages attribute="entityName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="geoId" />
        <mjl:messages attribute="geoId">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.geo.generated.AbstractSiteController.update.mojo" name="csu.mrc.ivcc.mdss.geo.generated.AbstractSite.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.geo.generated.AbstractSiteController.delete.mojo" name="csu.mrc.ivcc.mdss.geo.generated.AbstractSite.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.generated.AbstractSiteController.cancel.mojo" name="csu.mrc.ivcc.mdss.geo.generated.AbstractSite.form.cancel.button" />
</mjl:form>
