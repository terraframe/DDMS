<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.generated.District.form.name" id="csu.mrc.ivcc.mdss.geo.generated.District.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activatedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.activated}
    </dd>
    <dt>
      <label>
        ${item.entityNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.entityName}
    </dd>
    <dt>
      <label>
        ${item.geoIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.geoId}
    </dd>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.geo.generated.DistrictController.edit.mojo" name="csu.mrc.ivcc.mdss.geo.generated.District.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="csu.mrc.ivcc.mdss.geo.LocatedInController.parentQuery.mojo" name="csu.mrc.ivcc.mdss.geo.LocatedIn.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="csu.mrc.ivcc.mdss.geo.LocatedInController.childQuery.mojo" name="csu.mrc.ivcc.mdss.geo.LocatedIn.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.geo.generated.DistrictController.viewAll.mojo" name="csu.mrc.ivcc.mdss.geo.generated.District.viewAll.link" />
