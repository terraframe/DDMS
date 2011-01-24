<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_HasLayers" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.HasLayers.form.name" id="dss.vector.solutions.query.HasLayers.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Saved Map
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.query.SavedMapController.view.mojo" name="dss.vector.solutions.query.SavedMap.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Layer
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.query.LayerController.view.mojo" name="dss.vector.solutions.query.Layer.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.HasLayersController.edit.mojo" name="dss.vector.solutions.query.HasLayers.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.HasLayersController.viewAll.mojo" name="dss.vector.solutions.query.HasLayers.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
