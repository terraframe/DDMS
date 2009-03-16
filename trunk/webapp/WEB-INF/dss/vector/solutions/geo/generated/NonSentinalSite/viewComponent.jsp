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
  </div>
</div>
  
  <div class="submitButton_bl"></div>    
  <mjl:command value="Edit" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.edit.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.form.edit.button" classes="submitButton"/>
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.geo.generated.NonSentinalSiteController.viewAll.mojo" name="dss.vector.solutions.geo.generated.NonSentinalSite.viewAll.link" />
