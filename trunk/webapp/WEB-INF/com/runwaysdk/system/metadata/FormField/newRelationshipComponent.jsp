<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Select  Participants"/>
<mjl:form id="com.runwaysdk.system.metadata.FormField.form.id" name="com.runwaysdk.system.metadata.FormField.form.name" method="POST">
  <dl>
    <dt>
      <label>
        
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
        
      </label>
    </dt>
    <dd>
      <mjl:select param="childId" items="${childList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="com.runwaysdk.system.metadata.FormField.form.newInstance.button" value="New Instance" action="com.runwaysdk.system.metadata.FormFieldController.newInstance.mojo" />
  </dl>
</mjl:form>
