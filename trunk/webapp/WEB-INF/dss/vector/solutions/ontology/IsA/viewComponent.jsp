<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_IsA" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.IsA.form.id" name="dss.vector.solutions.ontology.IsA.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          Term
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.ontology.Term.form.view.link" action="dss.vector.solutions.ontology.TermController.view.mojo" display="${item.parent.keyName}">
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Term
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.ontology.Term.form.view.link" action="dss.vector.solutions.ontology.TermController.view.mojo" display="${item.parent.keyName}">
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.ontology.IsA.form.edit.button" value="Edit" action="dss.vector.solutions.ontology.IsAController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.ontology.IsA.viewAll.link" action="dss.vector.solutions.ontology.IsAController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
