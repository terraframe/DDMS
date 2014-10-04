<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_TextElement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.TextElement.form.id" name="dss.vector.solutions.query.TextElement.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="customTextElementId">
        ${item.customTextElementId}
      </mjl:dt>
      <mjl:dt attribute="fontColor">
        ${item.fontColor}
      </mjl:dt>
      <mjl:dt attribute="fontFamily">
        ${item.fontFamily}
      </mjl:dt>
      <mjl:dt attribute="fontSize">
        ${item.fontSize}
      </mjl:dt>
      <mjl:dt attribute="fontStyle">
        ${item.fontStyle}
      </mjl:dt>
      <mjl:dt attribute="textValue">
        ${item.textValue}
      </mjl:dt>
      <mjl:dt attribute="textXPosition">
        ${item.textXPosition}
      </mjl:dt>
      <mjl:dt attribute="textYPosition">
        ${item.textYPosition}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.TextElement.form.edit.button" value="Edit" action="dss.vector.solutions.query.TextElementController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.TextElement.viewAll.link" action="dss.vector.solutions.query.TextElementController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
