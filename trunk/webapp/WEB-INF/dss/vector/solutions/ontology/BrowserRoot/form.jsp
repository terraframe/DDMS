<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="term">
    <span class="clickable" id="termBtn">
      <fmt:message key="Browser" />
    </span>
    <div class="ontologyDisplay" id="termDisplay">
      ${term != null ? term.displayLabel : ''}
    </div>
    <mjl:input value="${term != null ? term.id : ''}" type="hidden" param="term" id="term" />
  </mjl:dt>
  <mjl:dt attribute="selectable">
    <mjl:boolean param="selectable" />
  </mjl:dt>
</mjl:component>
