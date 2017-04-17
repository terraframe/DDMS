<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="bccAddresses">
    <mjl:input param="bccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="body">
    <mjl:input param="body" type="text" />
  </mjl:dt>
  <mjl:dt attribute="ccAddresses">
    <mjl:input param="ccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="error">
    <mjl:input param="error" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fromAddress">
    <mjl:input param="fromAddress" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="sentDate" type="text" />
  <mjl:dt attribute="subject">
    <mjl:input param="subject" type="text" />
  </mjl:dt>
  <mjl:dt attribute="toAddresses">
    <mjl:input param="toAddresses" type="text" />
  </mjl:dt>
</mjl:component>
