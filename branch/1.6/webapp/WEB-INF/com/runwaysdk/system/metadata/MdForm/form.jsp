<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="formMdClass">
    <mjl:select param="formMdClass" items="${formMdClass}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="formName">
    <mjl:input param="formName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="exported">
    <mjl:boolean param="exported" />
  </mjl:dt>
  <mjl:dt attribute="jsBase">
    <mjl:input param="jsBase" type="text" />
  </mjl:dt>
  <mjl:dt attribute="jsStub">
    <mjl:input param="jsStub" type="text" />
  </mjl:dt>
  <mjl:dt attribute="packageName">
    <mjl:input param="packageName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="typeName">
    <mjl:input param="typeName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="remove">
    <mjl:boolean param="remove" />
  </mjl:dt>
</mjl:component>
