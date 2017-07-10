<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<mjl:component param="mdField" item="${item}">
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="required">
    <mjl:boolean param="required" />
  </mjl:dt>  
  <mjl:dt attribute="remove">
    <mjl:boolean param="remove" />
  </mjl:dt>
  <mjl:dt attribute="numeratorAggregation">
    <mjl:select param="numeratorAggregation" items="${aggregations}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="numeratorField">
    <mjl:select param="numeratorField" items="${numerics}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="denominatorAggregation">
    <mjl:select param="denominatorAggregation" items="${aggregations}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="denominatorField">
    <mjl:select param="denominatorField" items="${numerics}" var="current" valueAttribute="id" includeBlank="true">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="percentage">
    <mjl:boolean param="percentage" />
  </mjl:dt>      
  
</mjl:component>
