<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="altId">
    <mjl:input type="text" param="altId" />
  </mjl:dt>
  <mjl:dt attribute="comment">
    <mjl:input type="text" param="comment" />
  </mjl:dt>
  <mjl:dt attribute="def">
    <mjl:input type="text" param="def" />
  </mjl:dt>
  <mjl:dt attribute="inverseOf">
    <mjl:select var="current" valueAttribute="id" items="${inverseOf}" param="inverseOf">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="inverseOfOnInstanceLevel">
    <mjl:select var="current" valueAttribute="id" items="${inverseOfOnInstanceLevel}" param="inverseOfOnInstanceLevel">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="isAntiSymmetric">
    <mjl:boolean param="isAntiSymmetric" />
  </mjl:dt>
  <mjl:dt attribute="isBuiltIn">
    <mjl:boolean param="isBuiltIn" />
  </mjl:dt>
  <mjl:dt attribute="isObsolete">
    <mjl:boolean param="isObsolete" />
  </mjl:dt>
  <mjl:dt attribute="isReflexive">
    <mjl:boolean param="isReflexive" />
  </mjl:dt>
  <mjl:dt attribute="isTransitive">
    <mjl:boolean param="isTransitive" />
  </mjl:dt>
  <mjl:dt attribute="keyName">
    <mjl:input type="text" param="keyName" />
  </mjl:dt>
  <mjl:dt attribute="name">
    <mjl:input type="text" param="name" />
  </mjl:dt>
  <mjl:dt attribute="namespace">
    <mjl:input type="text" param="namespace" />
  </mjl:dt>
  <mjl:dt attribute="relationshipId">
    <mjl:input type="text" param="relationshipId" />
  </mjl:dt>
</mjl:component>
