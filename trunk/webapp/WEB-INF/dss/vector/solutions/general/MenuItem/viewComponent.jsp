<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_MenuItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.MenuItem.form.id" name="dss.vector.solutions.general.MenuItem.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="disease">
        <ul>
          <c:forEach items="${item.diseaseEnumNames}" var="enumName">
            <li>
              ${item.diseaseMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="term">
        ${item.term.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="url">
        ${item.url.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command localize="false" name="dss.vector.solutions.general.MenuItem.form.edit.button" value="Edit" action="dss.vector.solutions.general.MenuItemController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.MenuItem.viewAll.link" action="dss.vector.solutions.general.MenuItemController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
