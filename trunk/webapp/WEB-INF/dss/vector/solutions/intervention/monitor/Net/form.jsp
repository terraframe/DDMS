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
          ${item.isAbstractMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="isAbstract" />
        <mjl:messages attribute="isAbstract">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.netNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="netName" />
        <mjl:messages attribute="netName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.parentNetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${nets}" param="parentNet" includeBlank="true">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="parentNet">
          <mjl:message />
        </mjl:messages>
      </dd>
    </mjl:component>
