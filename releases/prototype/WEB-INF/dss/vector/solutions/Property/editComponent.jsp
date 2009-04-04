<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <div class="fldContainer">
    <div class="fcTop">

  <mjl:component item="${item}" param="dto">
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
          ${item.propertyValueMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="propertyValue" />
        <mjl:messages attribute="propertyValue">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
      <div class="fcTopLeft"></div>
    </div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
</div>
  
  <div class="submitButton_bl"></div>
  <mjl:command value="Update" action="dss.vector.solutions.PropertyController.update.mojo" name="dss.vector.solutions.Property.form.update.button" classes="submitButton"/>
  
  <mjl:command value="Cancel" action="dss.vector.solutions.PropertyController.cancel.mojo" name="dss.vector.solutions.Property.form.cancel.button" classes="submitButton"/>
</mjl:form>
