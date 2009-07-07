<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="View_Wall"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Wall.form.name" id="dss.vector.solutions.intervention.monitor.Wall.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.wallNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.wallName}
    </dd>    
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>        
    <dt>
      <label>
        ${item.parentWallMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.parentWall.displayLabel}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.WallController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.WallController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Wall.viewAll.link" />
