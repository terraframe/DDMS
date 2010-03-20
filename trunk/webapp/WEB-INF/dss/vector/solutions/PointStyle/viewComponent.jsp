<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.PointStyle.form.name" id="dss.vector.solutions.PointStyle.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.wellKnownNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.wellKnownNameEnumNames}">
          <li>
            ${item.wellKnownNameMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.strokeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.stroke}
    </dd>
    <dt>
      <label>
        ${item.strokeWidthMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.strokeWidth}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.PointStyleController.edit.mojo" name="dss.vector.solutions.PointStyle.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.PointStyleController.viewAll.mojo" name="dss.vector.solutions.PointStyle.viewAll.link" />
