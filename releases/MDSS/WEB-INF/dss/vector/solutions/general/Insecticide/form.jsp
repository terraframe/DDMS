<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="activeIngredient">
    <mjl:select param="activeIngredient" items="${activeIngredient}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="amount">
    <mjl:input param="amount" type="text" />
  </mjl:dt>
  <mjl:dt attribute="units">
    <mjl:select param="units" items="${units}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.unitsEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.unitsMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
