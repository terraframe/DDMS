<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="dENGUE_defaultLocale">
    <mjl:input param="dENGUE_defaultLocale" type="text" />
  </mjl:dt>
  <mjl:dt attribute="defaultLocale">
    <mjl:input param="defaultLocale" type="text" />
  </mjl:dt>
  <mjl:dt attribute="mALARIA_defaultLocale">
    <mjl:input param="mALARIA_defaultLocale" type="text" />
  </mjl:dt>
</mjl:component>
