<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.TextStyle.form.name" id="dss.vector.solutions.query.TextStyle.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.fillMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fill}
    </dd>
    <dt>
      <label>
        ${item.fontFamilyMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fontFamily}
    </dd>
    <dt>
      <label>
        ${item.fontSizeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fontSize}
    </dd>
    <dt>
      <label>
        ${item.fontStyleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fontStyle}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.TextStyleController.edit.mojo" name="dss.vector.solutions.query.TextStyle.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.query.TextStyleController.viewAll.mojo" name="dss.vector.solutions.query.TextStyle.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
