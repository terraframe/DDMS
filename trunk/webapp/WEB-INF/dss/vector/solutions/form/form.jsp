<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<mjl:component param="form" item="${form}">
  <mjl:dt attribute="formName">
    <mjl:input type="text" param="formName"/>
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input type="text" param="displayLabel"/>
  </mjl:dt>
</mjl:component>