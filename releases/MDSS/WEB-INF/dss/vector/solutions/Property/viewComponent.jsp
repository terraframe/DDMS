<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="View_All_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <div class="fldContainer">
<div class="fcTop">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.propertyNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.displayLabel}
    </dd>

    <dt>
      <label>
        ${item.descriptionMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.description}
    </dd>
    <dt>
      <label>
        ${item.propertyTypeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyType}
    </dd>
    <dt>
      <label>
        ${item.propertyValueMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.propertyValue}
    </dd>
  </dl>

<div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
</div>

  <mjl:command value="Edit" action="dss.vector.solutions.PropertyController.edit.mojo" name="dss.vector.solutions.Property.form.edit.button" classes="submitButton"/>
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.PropertyController.viewAll.mojo" name="dss.vector.solutions.Property.viewAll.link" />
