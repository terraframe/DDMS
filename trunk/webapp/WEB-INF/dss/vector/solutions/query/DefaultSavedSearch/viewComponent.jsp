<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.DefaultSavedSearch.form.name" id="dss.vector.solutions.query.DefaultSavedSearch.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.configMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.config}
    </dd>
    <dt>
      <label>
        ${item.csvFileMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.csvFile}
    </dd>
    <dt>
      <label>
        ${item.queryNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.queryName}
    </dd>
    <dt>
      <label>
        ${item.queryTypeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.queryType}
    </dd>
    <dt>
      <label>
        ${item.queryXmlMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.queryXml}
    </dd>
    <dt>
      <label>
        ${item.templateFileMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.templateFile}
    </dd>
    <dt>
      <label>
        ${item.thematicLayerMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.thematicLayer.keyName}" action="dss.vector.solutions.query.ThematicLayerController.view.mojo" name="dss.vector.solutions.query.ThematicLayer.form.view.link">
        <mjl:property value="${item.thematicLayer.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.DefaultSavedSearchController.edit.mojo" name="dss.vector.solutions.query.DefaultSavedSearch.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.query.DefinesLayersController.parentQuery.mojo" name="dss.vector.solutions.query.DefinesLayers.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.query.PersistsSearchController.childQuery.mojo" name="dss.vector.solutions.query.PersistsSearch.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.DefaultSavedSearchController.viewAll.mojo" name="dss.vector.solutions.query.DefaultSavedSearch.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
