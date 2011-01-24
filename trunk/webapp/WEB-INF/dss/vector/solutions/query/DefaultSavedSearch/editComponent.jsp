<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.DefaultSavedSearch.form.name" id="dss.vector.solutions.query.DefaultSavedSearch.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.configMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="config" />
        <mjl:messages attribute="config">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.csvFileMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="csvFile" />
        <mjl:messages attribute="csvFile">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.queryNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="queryName" />
        <mjl:messages attribute="queryName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.queryTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="queryType" />
        <mjl:messages attribute="queryType">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.queryXmlMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="queryXml" />
        <mjl:messages attribute="queryXml">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.templateFileMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="templateFile" />
        <mjl:messages attribute="templateFile">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.thematicLayerMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_query_SavedSearch_thematicLayer}" param="thematicLayer">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="thematicLayer">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mdss:localize key="Update" var="Localized_Update" />
  <mjl:command value="${Localized_Update}" action="dss.vector.solutions.query.DefaultSavedSearchController.update.mojo" name="dss.vector.solutions.query.DefaultSavedSearch.form.update.button" />
  <mdss:localize key="Delete" var="Localized_Delete" />
  <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.query.DefaultSavedSearchController.delete.mojo" name="dss.vector.solutions.query.DefaultSavedSearch.form.delete.button" />
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.query.DefaultSavedSearchController.cancel.mojo" name="dss.vector.solutions.query.DefaultSavedSearch.form.cancel.button" />
</mjl:form>
