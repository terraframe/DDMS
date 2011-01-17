<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_IsA" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.IsA.form.name" id="dss.vector.solutions.ontology.IsA.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Term
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.ontology.TermController.view.mojo" name="dss.vector.solutions.ontology.Term.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Term
        </label>
      </dt>
      <dd>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.ontology.TermController.view.mojo" name="dss.vector.solutions.ontology.Term.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.ontology.IsAController.edit.mojo" name="dss.vector.solutions.ontology.IsA.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.IsAController.viewAll.mojo" name="dss.vector.solutions.ontology.IsA.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
