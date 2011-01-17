<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_Styles" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.Styles.form.name" id="dss.vector.solutions.query.Styles.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="fill">
        ${item.fill}
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
      <mjl:dt attribute="pointMarker">
        <ul>
          <c:forEach var="enumName" items="${item.pointMarkerEnumNames}">
            <li>
              ${item.pointMarkerMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="pointStroke">
        ${item.pointStroke}
      </mjl:dt>
      <mjl:dt attribute="pointWidth">
        ${item.pointWidth}
      </mjl:dt>
      <mjl:dt attribute="polygonFill">
        ${item.polygonFill}
      </mjl:dt>
      <mjl:dt attribute="polygonStroke">
        ${item.polygonStroke}
      </mjl:dt>
      <mjl:dt attribute="polygonWidth">
        ${item.polygonWidth}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.query.StylesController.edit.mojo" name="dss.vector.solutions.query.Styles.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.StylesController.viewAll.mojo" name="dss.vector.solutions.query.Styles.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
