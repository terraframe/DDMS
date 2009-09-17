<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_FieldRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.FieldRoot.form.name" id="dss.vector.solutions.ontology.FieldRoot.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Ontology Field
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.ontology.BrowserFieldController.view.mojo" name="dss.vector.solutions.ontology.BrowserField.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Ontology Root
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.ontology.BrowserRootController.view.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.ontology.FieldRootController.edit.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.FieldRootController.viewAll.mojo" name="dss.vector.solutions.ontology.FieldRoot.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
