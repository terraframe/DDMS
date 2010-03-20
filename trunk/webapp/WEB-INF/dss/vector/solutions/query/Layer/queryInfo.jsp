<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:component item="${info}" param="info">
<dl>
  <mjl:dt attribute="minimum">
    ${info.minimum}
  </mjl:dt>
  <mjl:dt attribute="maximum">
    ${info.maximum}
  </mjl:dt>
  <mjl:dt attribute="totalResults">
    ${info.totalResults}
  </mjl:dt>
</dl>
</mjl:component>