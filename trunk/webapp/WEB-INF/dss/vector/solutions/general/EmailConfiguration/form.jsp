<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="emailServer">
    <mjl:input param="emailServer" type="text" />
  </mjl:dt>
  <mjl:dt attribute="protocol">
    <mjl:select param="protocol" items="${protocol}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.protocolEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.protocolMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="emailUserid">
    <mjl:input param="emailUserid" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailPassword">
    <mjl:input param="emailPassword" type="text" />
  </mjl:dt>
  <mjl:dt attribute="retry">
    <mjl:input param="retry" type="text" />
  </mjl:dt>
  <mjl:dt attribute="timeout">
    <mjl:input param="timeout" type="text" />
  </mjl:dt>
</mjl:component>
