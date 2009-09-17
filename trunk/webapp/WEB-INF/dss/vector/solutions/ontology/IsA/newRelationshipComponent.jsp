<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form id="dss.vector.solutions.ontology.IsA.form.id" name="dss.vector.solutions.ontology.IsA.form.name" method="POST">
  <dl>
    <dt>
      <label>
        Term
      </label>
    </dt>
    <dd>
      <mjl:select param="parentId" items="${parentList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label>
        Term
      </label>
    </dt>
    <dd>
      <mjl:select param="childId" items="${childList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.ontology.IsA.form.newInstance.button" value="New Instance" action="dss.vector.solutions.ontology.IsAController.newInstance.mojo" />
  </dl>
</mjl:form>
