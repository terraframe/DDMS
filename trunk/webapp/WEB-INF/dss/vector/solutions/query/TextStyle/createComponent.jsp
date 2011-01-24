<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.TextStyle.form.name" id="dss.vector.solutions.query.TextStyle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.fillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fill" />
        <mjl:messages attribute="fill">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.fontFamilyMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontFamily" />
        <mjl:messages attribute="fontFamily">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.fontSizeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontSize" />
        <mjl:messages attribute="fontSize">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.fontStyleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontStyle" />
        <mjl:messages attribute="fontStyle">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mdss:localize key="Create" var="Localized_Create" />
  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.query.TextStyleController.create.mojo" name="dss.vector.solutions.query.TextStyle.form.create.button" />
</mjl:form>
