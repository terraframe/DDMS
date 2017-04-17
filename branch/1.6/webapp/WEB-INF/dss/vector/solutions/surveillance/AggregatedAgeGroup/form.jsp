<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.activeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="active" />
        <mjl:messages attribute="active">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.startAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="startAge" />
        <mjl:messages attribute="startAge">
          <mjl:message />
        </mjl:messages>
      </dd>      
      <dt>
        <label>
          ${item.endAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="endAge" />
        <mjl:messages attribute="endAge">
          <mjl:message />
        </mjl:messages>
      </dd>
    </mjl:component>
