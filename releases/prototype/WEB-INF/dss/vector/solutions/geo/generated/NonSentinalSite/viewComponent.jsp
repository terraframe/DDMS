<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.NonSentinalSite.form.name" id="dss.vector.solutions.geo.generated.NonSentinalSite.form.id" method="POST">
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
  <mjl:command value="Edit" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.edit.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.form.edit.button" classes="submitButton"/>
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.viewAll.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.viewAll.link" />
