<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Edit_MdWebSingleTerm" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebSingleTerm.form.id" name="com.runwaysdk.system.metadata.MdWebSingleTerm.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebSingleTerm.form.update.button" value="${Update_Localize}" action="com.runwaysdk.system.metadata.MdWebSingleTermController.update.mojo" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebSingleTerm.form.delete.button" value="${Delete_Localize}" action="com.runwaysdk.system.metadata.MdWebSingleTermController.delete.mojo" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="com.runwaysdk.system.metadata.MdWebSingleTerm.form.cancel.button" value="${Cancel_Localize}" action="com.runwaysdk.system.metadata.MdWebSingleTermController.cancel.mojo" />
  </mjl:form>
</dl>