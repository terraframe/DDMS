<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_MOField" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.MOField.form.name" id="dss.vector.solutions.ontology.MOField.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="ontologyRelationships">
        <ul>
          <c:forEach var="enumName" items="${item.ontologyRelationshipsEnumNames}">
            <li>
              ${item.ontologyRelationshipsMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="mdAttribute">
        ${item.mdAttribute.id}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.MOFieldController.edit.mojo" name="dss.vector.solutions.ontology.MOField.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.MOFieldController.viewAll.mojo" name="dss.vector.solutions.ontology.MOField.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
