<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Create_MdWebGroup" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<h2 class="fieldTitle">${item.md.displayLabel}</h2>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebGroup.form.id" name="com.runwaysdk.system.metadata.MdWebGroup.form.name" method="POST">
<dl>
    <%@include file="form.jsp" %>
</dl>
      <mdss:localize var="Create_Localize" key="Create" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.createMdField.button" value="${Create_Localize}" action="dss.vector.solutions.form.MdFormAdminController.createMdField.mojo" />
      <mdss:localize var="Cancel_Localize" key="Cancel" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelMdField.mojo" />
  </mjl:form>
