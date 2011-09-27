<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Create_MdWebDouble" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebDouble.form.id" name="com.runwaysdk.system.metadata.MdWebDouble.form.name" method="POST">
    <%@include file="form.jsp" %>
    <dt></dt>
    <dd>
      <mdss:localize var="Create_Localize" key="Create" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.createMdField.button" value="${Create_Localize}" action="dss.vector.solutions.form.MdFormAdminController.createMdField.mojo" />
      <mdss:localize var="Cancel_Localize" key="Cancel" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelMdField.mojo" />
    </dd>
  </mjl:form>
</dl>
