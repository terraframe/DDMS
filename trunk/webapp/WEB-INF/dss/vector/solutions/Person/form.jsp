<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="dateOfBirth">
    <mjl:input param="dateOfBirth" type="text" />
  </mjl:dt>
  <mjl:dt attribute="firstName">
    <mjl:input param="firstName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="iptRecipientDelegate">
    <mjl:select param="iptRecipientDelegate" items="${iptRecipientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="itnRecipientDelegate">
    <mjl:select param="itnRecipientDelegate" items="${itnRecipientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lastName">
    <mjl:input param="lastName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="patientDelegate">
    <mjl:select param="patientDelegate" items="${patientDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="residentialGeoEntity">
    <mjl:select param="residentialGeoEntity" items="${residentialGeoEntity}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mjl:select param="sex" items="${sex}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.sexEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.sexMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sprayLeaderDelegate">
    <mjl:select param="sprayLeaderDelegate" items="${sprayLeaderDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sprayOperatorDelegate">
    <mjl:select param="sprayOperatorDelegate" items="${sprayOperatorDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="userDelegate">
    <mjl:select param="userDelegate" items="${userDelegate}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
