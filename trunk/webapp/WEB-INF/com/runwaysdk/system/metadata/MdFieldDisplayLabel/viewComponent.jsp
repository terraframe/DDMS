<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_MdFieldDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdFieldDisplayLabel.form.id" name="com.runwaysdk.system.metadata.MdFieldDisplayLabel.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="dENGUE_defaultLocale">
        ${item.dENGUE_defaultLocale}
      </mjl:dt>
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
      <mjl:dt attribute="mALARIA_defaultLocale">
        ${item.mALARIA_defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.MdFieldDisplayLabel.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.MdFieldDisplayLabelController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.MdFieldDisplayLabel.viewAll.link" action="com.runwaysdk.system.metadata.MdFieldDisplayLabelController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
