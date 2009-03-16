<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.NonSentinalSite.form.name" id="dss.vector.solutions.geo.generated.NonSentinalSite.form.id" method="POST">
  <div class="fldContainer">
    <div class="fcTop"><div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    <div style="position:absolute; left:20px; top:25px;">

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
  </div>
</div>
  
  <div class="submitButton_bl"></div>
  <mjl:command value="Update" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.update.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.form.update.button" classes="submitButton"/>
  <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.delete.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.form.delete.button" classes="submitButton"/>
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.cancel.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.form.cancel.button" classes="submitButton"/>
</mjl:form>
