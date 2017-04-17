<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_HasImage" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.HasImage.form.id" name="dss.vector.solutions.query.HasImage.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          Saved map
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.query.SavedMap.form.view.link" action="dss.vector.solutions.query.SavedMapController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          MapImage
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.query.MapImage.form.view.link" action="dss.vector.solutions.query.MapImageController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.HasImage.form.edit.button" value="Edit" action="dss.vector.solutions.query.HasImageController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.HasImage.viewAll.link" action="dss.vector.solutions.query.HasImageController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
