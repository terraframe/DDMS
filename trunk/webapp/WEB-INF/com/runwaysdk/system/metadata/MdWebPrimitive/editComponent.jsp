<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Edit_MdWebPrimitive" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebPrimitive.form.id" name="com.runwaysdk.system.metadata.MdWebPrimitive.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebPrimitive.form.update.button" value="${Update_Localize}" action="com.runwaysdk.system.metadata.MdWebPrimitiveController.update.mojo" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebPrimitive.form.delete.button" value="${Delete_Localize}" action="com.runwaysdk.system.metadata.MdWebPrimitiveController.delete.mojo" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebPrimitive.form.cancel.button" value="${Cancel_Localize}" action="com.runwaysdk.system.metadata.MdWebPrimitiveController.cancel.mojo" />
  </mjl:form>
</dl>