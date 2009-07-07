<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <dl>
          <mjl:struct param="displayLabel">
            <dt>
              <label>
                ${item.displayLabel.defaultLocaleMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="defaultLocale" />
              <mjl:messages attribute="defaultLocale">
                <mjl:message />
              </mjl:messages>
            </dd>
          </mjl:struct>
        </dl>
      </dd>
      <dt>
        <label>
          ${item.roofNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="roofName" />
        <mjl:messages attribute="roofName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enabledMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="enabled" />
        <mjl:messages attribute="enabled">
          <mjl:message />
        </mjl:messages>
      </dd>      
      <dt>
        <label>
          ${item.parentRoofMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${roofs}" param="parentRoof" includeBlank="true">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="parentRoof">
          <mjl:message />
        </mjl:messages>
      </dd>      
    </mjl:component>
