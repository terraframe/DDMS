<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_MapImage" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.MapImage.form.id" name="dss.vector.solutions.query.MapImage.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="imageFilePath">
        ${item.imageFilePath}
      </mjl:dt>
      <mjl:dt attribute="imageName">
        ${item.imageName}
      </mjl:dt>
      <mjl:dt attribute="imageXPosition">
        ${item.imageXPosition}
      </mjl:dt>
      <mjl:dt attribute="imageYPosition">
        ${item.imageYPosition}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.MapImage.form.edit.button" value="Edit" action="dss.vector.solutions.query.MapImageController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.MapImage.viewAll.link" action="dss.vector.solutions.query.MapImageController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
