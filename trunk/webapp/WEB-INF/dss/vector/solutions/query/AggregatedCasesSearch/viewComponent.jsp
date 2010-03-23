<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.AggregatedCasesSearch.form.name" id="dss.vector.solutions.query.AggregatedCasesSearch.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
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
        ${item.queryViewNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.queryViewName}
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
        ${item.thematicLayerMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.thematicLayer.keyName}" action="dss.vector.solutions.query.ThematicLayerController.view.mojo" name="dss.vector.solutions.query.ThematicLayer.form.view.link">
        <mjl:property value="${item.thematicLayer.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.viewCreatedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.viewCreated}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.AggregatedCasesSearchController.edit.mojo" name="dss.vector.solutions.query.AggregatedCasesSearch.form.edit.button" />
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
<mjl:commandLink action="dss.vector.solutions.query.AggregatedCasesSearchController.viewAll.mojo" name="dss.vector.solutions.query.AggregatedCasesSearch.viewAll.link">
<fmt:message key="View_All" />
</mjl:commandLink>
