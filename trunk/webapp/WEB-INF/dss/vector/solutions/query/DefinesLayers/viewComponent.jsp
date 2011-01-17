<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.DefinesLayers.form.name" id="dss.vector.solutions.query.DefinesLayers.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Saved Query
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.query.SavedSearchController.view.mojo" name="dss.vector.solutions.query.SavedSearch.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Universal Layer
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.query.UniversalLayerController.view.mojo" name="dss.vector.solutions.query.UniversalLayer.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.DefinesLayersController.edit.mojo" name="dss.vector.solutions.query.DefinesLayers.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.query.DefinesLayersController.viewAll.mojo" name="dss.vector.solutions.query.DefinesLayers.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
