<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.name" id="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Roof
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.intervention.monitor.WallController.view.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Roof
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.intervention.monitor.WallController.view.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.edit.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.viewAll.link" />
