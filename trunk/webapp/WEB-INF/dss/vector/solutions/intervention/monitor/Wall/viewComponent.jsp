<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.WallController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.parentQuery.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.parentQuery.link">
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
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.childQuery.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.WallController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Wall.viewAll.link" />
